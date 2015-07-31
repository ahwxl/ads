/* global Ext */
/*
 * Copyright 2007-2009, Doug Hendricks, Active Group, Inc.  All rights reserved.
 * ******************************************************************************
 * This file is distributed on an AS IS BASIS WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * ***********************************************************************************
 * @version .11
 * @license: ux.Workers is offered under an MIT License
 * Donations are welcomed: http://donate.theactivegroup.com
 *
 */
 
(function(){
    Ext.ns('Ext.ux');
    Ext.extendX || (Ext.extendX = function(supr, fn){
            return Ext.extend(supr, fn(supr.prototype));
        });
    
    var GPM,
            browser = {
                version : (navigator.userAgent.toLowerCase().match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1]  
            },
            gearsPoolManager = GPM = {
            workers : {},
            init : function(caller){
                google.gears.factory.hasPermission || google.gears.factory.getPermission();
                if(!google.gears.factory.hasPermission){
                    //TODO: Convert to Ext.Error subclass
                    return caller && caller.onError ? caller.onError('Gears User says Permission-denied') : false;
                }
               (this.workPool = google.gears.factory.create("beta.workerpool")) &&
                Ext.apply(this.workPool,{
                    onmessage : this.onMessage.createDelegate(this)
                });    
                delete this.init;
                return !!this.workPool;
            },
            getWorkerPool : function(caller){
                this.init && this.init(caller);
                return this.workPool || null;
            },
            register : function(workerInstance, id){
                workerInstance && (id || workerInstance.id) && 
                   (this.workers[id || workerInstance.id] = workerInstance);
                return !!this.getWorkerPool(workerInstance);
            },
            
            deRegister : function(workerInstance){
                this.workers && workerInstance && workerInstance.id && 
                   delete this.workers[workerInstance.id];  
            },
            /**
             *  @private
             * Dispatches any messages received for Workers assigned to this Pool.
             * This also synthesizes an exception event. 
             */
            onMessage : function(messageText, senderId, message) {
	           var worker;
	           if(worker = this.workers[message.sender]){
	                if (message.body.name == "__workerException__") {
                          message.body.preventDefault && message.body.preventDefault();
	                      worker.onError(message.body );
	                }else{
	                      worker.onMessage(Ext.apply({data: message.body}, message));
	                }
	           }
             },
            onError   : function(){}
        },
	    hasWebWorker = function(){
	        return typeof window.Worker != "undefined";
	    },
        
        defeatCache = function(url){
                var parts = url ? String(url).split('#') : [''];
                if(!(/_dc=/i).test(parts[0]) ){
                    parts[0] = Ext.urlAppend(parts[0], "_dc=" + (new Date().getTime()));
                }
                return parts.length > 1 ? parts.join('#') : parts[0];  
        },
                
        getURLBase = function(url){
            var d= '\/', 
                u =(url||''),
                parts = u.split('?'),
                params = parts[1];
                
            u =parts[0].split(d);
            return { resource: u.pop(), 
                         base: u.join(d),
                         params : params||''
                         };
         },
         
        // Synthesize a HTML5 XMLHttpRequest interface for IE and others 
        // (XDR may not be available depending on the browser)
        requestObject = 
                window.XMLHttpRequest || 
                function(){
                   return new window.ActiveXObject('MSXML2.XMLHTTP');
                },
                
        /**
         * x-browser XMLHttpRequest pattern (supports IE8-XDomainRequest, Gecko/HTML5 features, or  
         * conventional XHR request interfaces).
         * @method xhrRequest
         * @param {String} url The URL of the request
         * @param {String} method The desired HTTP verb used for the transaction (GET, POST, HEAD, etc)
         * @param {Function} callback The desired callback Function invoked upon completion of the request.
         * <p>Called with the following arguments:<p><br /><ul>
         * <li>request : the request object used to service the request</li>
         * <li>success : True if the request was successful, false if and exception or timeout occured.
         * <li>response : the Response object used to service the request</li>
         * </ul>
         * @param {Object} scope Context used when the callback is invoked
         * @param {Boolean} async True to make the request asynchronously, synchronously otherwise
         * @param {Mixed} postData encoded Payload for PUT or POST requests.
         * @param {Integer} timeout Timeout duration is milliseconds (asynchronous request only)
         * @return {Object} The request object used to service the request. 
         */
                
        xhrRequest = function (url, method, callback, scope, async, postData, timeout ){
           var request;
           timeout = timeout || 30000;
           request = {
                   conn     : new requestObject(),
                   url      : url || '',
                   async    : async !== false,  //async default
                   method   : (method || 'get').toUpperCase(),
                   callback : callback,
                   scope    : scope,
                   responseHandler : function(){
                       request.timer && clearTimeout(request.timer);
                       delete request.timer;
                       request.isSuccess() || request.exception || request.onError();
                       var C;
                       if(C = request.conn){ //already handled?
                          typeof request.callback == 'function' &&
                                request.callback.call(request.scope||null, request, request.isSuccess(), C );
                          
                           C = request.conn = null;
                       }
                   },
                   start : function(async){
                       this.timedOut = this.exception = false;
                       this.status = undefined;
                       async = (async === undefined)? this.async : !!async;
                       var C;
                       if(this.response = C = this.conn){
                           
                           C.open(this.method, this.url, async); 
                           
                           'onload' in C ? 
                              C.onload = this.responseHandler :  
                               'onreadystatechange' in C && (C.onreadystatechange = this.onReadyState);
                           
                           ('timeout' in C && (C.timeout = timeout));  
                           ('ontimeout' in C ? 
                              C.ontimeout = this.onTimeout :
                                request.timer = setTimeout(this.onTimeout, timeout)
                                );
                                
                           'onerror' in C && (C.onerror = this.onError);
                           'onprogress' in C && typeof this.onProgress == 'function' && 
                                (C.onprogress = this.onProgress.createDelegate(this));
                           
                           C.send(postData || null);
                           async || this.responseHandler();
                       }
                       return this;
                   },
                   isSuccess : function (){
                        var status, C=request.conn;
                        request.statusText || (request.statusText = 'Unknown');
                        if(!C || !!request.exception)return false;
                        
                        try{
                            request.status = status = ('status' in C ? C.status : 200);
                            request.statusText = ('statusText' in C ? C.statusText : request.statusText);
                        }catch(ee){ return false;}
                        
                        return !request.timedOut && ( 
	                            (!status && location.protocol == "file:") ||
	                            (status >= 200 && status < 300) ||
	                            status == 304 ||
	                            status == 1223 ||
	                            (Ext.isSafari && status == undefined)
                              );
                      },
                      
                   onError : function(e){
                      if(!request.exception){
                        var C = this.conn || {};
                        try{
                            request.exception = {
	                           status :  'status' in C ? C.status : request.status || 'Unknown',
	                           statusText : 'statusText' in C ? C.statusText : request.statusText || 'Unknown',
                               error : e
                            };
                        }catch(ee){request.exception ={error : e}; }
                      }
                   },
                   onTimeout : function(){
                       request.timedOut = true;
                       request.responseHandler();
                   },
                   /**
                    * Aborts the active request
                    */
                   abort : function(){
                       request.timer && clearTimeout(request.timer);
                       delete request.timer;
                       request.response && 'abort' in request.response && request.response.abort(); 
                       request.conn = null;
                   },
                   
                   onReadyState : function(){
                     request.conn && request.conn.readyState == 4 && request.responseHandler();
                   }
            };
            request.start= request.start.createDelegate(request);
            return request;
        
          }, 
        /*
         * Asynch XHR pattern to support importScripts(..)
         * This implementation permits IE8's XDomainRequest (async only) 
         * (This method emulates the W3C spec in that all scripts are retrieved
         * before Worker execution begins.)
         */  
        resolveImports = function(scriptText, worker, baseURI, completeCb, scope){
           scriptText = scriptText || ''; 
           completeCb = completeCb || Ext.emptyFn;
           var imported, 
               importsRe = /(?:importScripts\s*\()((\s|.)*?)(?:\)\s*;?)/g, 
               match,
               imports,
               scripts=[],
               uri,
               nextImports = function(){
                  imports = [];
                  (match = importsRe.exec(scriptText)) && (scripts = match[1].split(',')||[]);
                  return match;
               },
               fetchImport = function(){
                 if(uri = scripts.shift() ){
                    
                  if(uri = uri.replace(/['"]|[^ \S]/g,'').trim()){ 
                    var req = xhrRequest(
	                    (baseURI||'') + uri, 
	                    'GET', 
	                    function(request, success, response){
	                      if(success){
	                        imports.push(response.responseText);
	                        fetchImport.defer(1);
	                        
	                      }else{
	                        completeCb.call(
                                scope, 
                                false, 
	                           'Retrieval of Worker import '+request.url+' failed: \n' + 
                                   (request.timedOut ? 'Request timed out' : 
                                      ((response.status||'') + ' ' + (response.statusText||'')).trim()  
                                       || 'Status Unavailable')
	                          );
	                      }
	                    },
	                    worker,
	                    true,
	                    null, 
	                    worker.timeout
                     );
                     req.conn = new ((worker.enableIEXdr ? window.XDomainRequest : null ) || requestObject)();
                     req.start();
                  }else{
                      fetchImport.defer(1);
                  }
                     
                    
                 }else{
                    scriptText = scriptText.replace(match[0],imports.join('\n'));
                    nextImports() ? fetchImport.defer(1) : completeCb.call(scope, true, scriptText);
                 }
               };
               
               nextImports() ? fetchImport() : completeCb.call(scope, true, scriptText);
                
         },
         
	    workerShell = [
	       "   var __worker, self;",
	       "   (function __worker_setup() {",
	       "      __worker = this;",
	       "      self = this.__context;",
	       "     (__worker._onmessageHandler = function() {",
	       "      var message, LE;",
	       "      with(__worker.__context){",
	       "        while (!!__worker._messageQueue.length && onmessage) {",
	       "            message = __worker._messageQueue.shift()",
	       "            try {",
	       "                onmessage(message);",
	       "            }catch(e){",
	       "                __worker._lastError = e; break;",
	       "            }",
	       "        }",
	       "        if(LE = __worker._lastError){",
	       "          var __handled = false;",
	       "          LE = __worker._lastError = __worker._toErrorEvent(LE);", 
	       "          if(onerror){", 
	       "             __handled = onerror(LE) || LE.returnValue == false;",
	       "          }",
	       "          __handled || __worker._handleError();",
	       "          __worker._lastError = null;",
	       "        }" ,
	       "       }",
	       "       __worker._terminating || (__worker._timeoutId = window.setTimeout(arguments.callee, __worker.quantas || 100));",
	       "      })();",
	       "    }).call($worker);",
	       "  var close = $worker.__context.close, postMessage = $worker.__context.postMessage;", //necessary for IE
	       "  with($worker.__context){ {0} }"
	        ].join("\n");
        
        /**
	     * @class GenericWorker
	     * @extends Object
         * A Generic HTML5 Worker implementation for Browsers without Worker object support. 
	     * @version 0.1 
	     * @license MIT 
	     * @author Doug Hendricks. Forum ID: <a href="http://extjs.com/forum/member.php?u=8730">hendricd</a> 
	     * @donate <a target="tag_donate" href="http://donate.theactivegroup.com"><img border="0" src="http://www.paypal.com/en_US/i/btn/x-click-butcc-donate.gif" border="0" alt="Make a donation to support ongoing development"></a>
	     * @copyright 2007-2009, Active Group, Inc. All rights reserved.
	     * @constructor  
	     * @param {String} url The relative/absolute URI of the Workers script source
	     */
        
        GenericWorker = function(url) {
            
            var B = getURLBase(url), 
                uriBase = B.base ? B.base + '/' : '';
            
            Ext.apply(this,{
                _messageQueue : [],
                _lastError : null,
                id  : 'worker_'+(++GenericWorker._index),
                url : B.resource,
                __context : {  //WorkerGlobalScope
		            //Constrain all child Workers to the parent Worker's baseURI
		            Worker : function(url){
                        var b = getURLBase(url);
		                return new GenericWorker((uriBase ? uriBase + '/' : '')+b.resource);
		            },
                    
                    XMLHttpRequest : (this.enableIEXdr ? window.XDomainRequest : null) || requestObject,
	                location    : window.location, //TODO: Harden to Workers absolute URL
	                navigator   : window.navigator,
                    document    : undefined,
                    window      : undefined,
                    console     : window.console,
                    close       : this.terminate.createDelegate(this),
                    closing     : false,
                    onmessage   : null,  //must define for scoped searches
                    onerror     : null,
                    onconnect   : null,
                    postMessage : function(message){
                        typeof this.onmessage == 'function' && this.onmessage({"data" : message, "target": this});
                    }.createDelegate(this)
                },
                onmessage : (this.onmessage || Ext.emptyFn).createDelegate(this),
                onerror   : (this.onerror || Ext.emptyFn).createDelegate(this),
                onconnect : (this.onconnect || Ext.emptyFn).createDelegate(this)
            });
            //emulate a WorkerGlobalScope 'self' context
            this.__context.self = this.__context;
            this.__context.id = this.id;
            
            var req = xhrRequest(
                    uriBase + B.resource + (B.params ? '?'+B.params : ''), 
                    'GET', 
	                function(request, success, response){
                        var w=  this;
                       if(success){  
                        //collect all imports and insert on callback
                        resolveImports(
                            response.responseText, 
                            this, 
                            uriBase, 
                            function(complete, script){
                        
	                           if(complete){
                                 new Function(
	                                '$worker',
	                                String.format(workerShell, script , uriBase)
	                              ).defer(1,this,[this])
                               }else{
                                  this._terminating = true;
                                  this._lastError = this._toErrorEvent(script);
                                  this._handleError();  
                               }
                            
                            }, 
                            this
                          );
                        
                       }else{
                            this._terminating = true;
                            this._lastError = this._toErrorEvent(
                                'Retrieval of Worker '+request.url+' failed: \n' + 
                                   (request.timedOut ? 'Request timed Out!!': 
                                   ((response.status||'') + ' ' + (response.statusText||'')).trim() || 
                                       'Status Unavailable')
                                      );
                            this._handleError();                        
                       }

	                 }, 
                    this, 
                    true, 
                    null, 
                    this.timeout
             );
             req.conn = new ((this.enableIEXdr ? window.XDomainRequest : null ) || requestObject)();
             req.start(); 
           
        };
        
        GenericWorker._index = 0;
        GenericWorker.prototype = {
            
            /**
             * @cfg {Integer} quantas Message Queue Poll interval in ms
             * @default 100 milliseconds
             */
            quantas     : 100,
            
            /**
             * @cfg {Boolean} True to enable IE8 XDomainRequest object to allow support
             * for Cross-domain requests within Worker scripts.
             * 
             * IE's XDR implementation REQUIRES the remote server respond with a response
             * header of either '*' or the exact URL of the requesting page
             * <% Response.AddHeader("Access-Control-Allow-Origin","*") %>
             *
             * Note: To enable globally, set the Class prototype: GenericWorker.prototype.enableIEXdr = true;
             * @default false
             */
            enableIEXdr : false,
            
            /**
             * @cfg {Integer} timeout time in milliseconds to wait for Worker script load
             * @default 30000
             */
            timeout     : 30000,
            
            /**
             * Sends a string message to the Worker script's onmessage handler.
             */
            postMessage : function(message) {
                 this._messageQueue.push({'data' : message, 'origin': document.domain, target : this});
            },
            
            /**
             * Terminates the Worker regardless of state.
             */
            terminate : function() {
                //process all messages before termination
                this.__context.closing = this._terminating = true;
                this._lastError = null;
                this._onmessageHandler && this._onmessageHandler();
                this._timeoutId && window.clearTimeout(this._timeoutId);
                delete this._timeoutId;
            },
                        
            /**
             * @private
             * Default class errorHandler
             */
            _handleError : function() {
                if(this._lastError){
                    if(typeof this.onerror == 'function')this.onerror(this._lastError);
                    else throw this._lastError;
                }
                this._lastError = null;
                
            },
            
            /**
             * @private
             *  Create a synthetic W3C compat 'error' Event
             *  for use in Worker scripts
             */
            _toErrorEvent : function(err){
                
                return Ext.apply( 
                      new Ext.ux.Worker.Error(err.message || err, err),
                      err,
                      {
                       filename : this.url,
                       target   : this,
                       message  : err.message || err,
                       name     : 'Worker',
                       type     : 'error',
                       returnValue : undefined,
                       preventDefault : function(){this.returnValue = false;}
                    });
                 
            },
            
            onerror : function(error){},
            
            onmessage : function(message){}
        };
        
    hasWebWorker() || ( window.Worker = GenericWorker);

    /**
     * @class Ext.ux.Worker
     * @extends Ext.util.Observable
     * A wrapper class for the HTML5 Worker implementation  
     * @version 0.1 
     * @license MIT 
     * @author Doug Hendricks. Forum ID: <a href="http://extjs.com/forum/member.php?u=8730">hendricd</a> 
     * @donate <a target="tag_donate" href="http://donate.theactivegroup.com"><img border="0" src="http://www.paypal.com/en_US/i/btn/x-click-butcc-donate.gif" border="0" alt="Make a donation to support ongoing development"></a>
     * @copyright 2007-2009, Active Group, Inc. All rights reserved.
     * @constructor creates a new Ext.ux.Worker instance. 
     * @param {Object} config 
     * @example
      var worker = new Ext.ux.Worker({
         url:'workers/echo.js',
         listeners : {
               message : function(worker, message, payload){ 
                    console.log('received message ', message); 
                    },
               exception : function(worker, error){ 
                    console.warn("exception ",error.message); 
                    }
          }
      });
      var handler = function(worker, success, message){
          if(success) 
              console.info('Worker Says ' + message.data);
          else 
              console.warn('Worker reports a problem: ' + message.lineno + ", " + message.message );
      };
      
      worker.postMessage('start', handler);
     
     */
    
    Ext.ux.Worker = Ext.extendX(Ext.util.Observable, function(suprProto){
        
        var notImplemented = function(){ throw 'Worker feature not implemented'; },
            
            slice = function(arr,index){return Array.prototype.slice.call(arr||[],index||0);},
	        /**
	         * Convert a named function reference to a source string with the function (and arguments) declaration removed
	         * eg: fnToString(' function (){ var a=1; a++; } ')  
	         * yields: "var a=1; a++; "
	         */
	        fnToString = function (fn){
	            if(Ext.type(fn) == 'string'){
	               return fn;
	            } else if(Ext.isFunction(fn)){
	                var s = (fn+'').replace(/(function[^\{]*\{)/,'').split(/\}/);
	                s.pop();
	                return s.join('}');
	            }else{
	                return '';
	            }
	        },
            
            gearsActive = function(){
	            return !!(window.google && google.gears && google.gears.factory);
	        },
	        
	        /**
	         * Gears Notes:
	         * Cross-origin workers
	
	        Workers can be loaded across origins using the createWorkerFromUrl() method. Workers 
	        loaded across origins this way run in the security context of the origin they are 
	        loaded from. This can be used to allow controlled communication between pages in 
	        different origins.
	
	        In order for Gears to execute cross-origin workers, the following restrictions 
	        must be met:
	
	        * The worker must be served with the content type application/x-gears-worker.
	        * The worker code must call google.gears.workerPool.allowCrossOrigin(). 
	
	        Once allowCrossOrigin() is called, cross-origin workers automatically inherit the permissions of the calling origin.
	
	        Known Bug: Cross-origin workers do not correctly inherit permissions when the worker URL redirects across origins. 
	         */
	        gearsHarness = [ 
	          "function gatherScript(url,callback,scope){",
	          "    var request;",
	          "    if(request = google.gears.factory.create('beta.httprequest')){",
	          "       request.open('GET', url);",
	          "       request.onreadystatechange = function() {",
	          "       if (request.readyState == 4) {",
	          "           callback.success && callback.success.call(scope,request);",
	          "           request.onreadystatechange = function(){};",
	          "       }}",
	          "       request.send();",
	          "     }else{",
	          "       callback.failure && callback.failure.call(scope,'Request Not Possible');",
	          "     }",
	          "}",
	          "var __GWP=google.gears.workerPool;",
	          "function postMessage(message, targetWorker) {",
	          "  __GWP.sendMessage(message, targetWorker || 0);",
	          "}",
	          "var allowCrossOrigin = __GWP.allowCrossOrigin",
	          "__GWP.onmessage = function(messageText, senderId, messageObject) {",
	          "  typeof onmessage == 'function' && onmessage({'data' : messageObject.body, 'sender':messageObject.sender, 'origin':messageObject.origin});",
	          "};",
	          "__GWP.onerror = function(error) {",
	          "  var __noBubble, __handled = false;",
	          "  error.preventDefault = function(){ __handled = true; }",
	          "  __noBubble = typeof onerror == 'function' ? !!onerror(error) : false;",
	          "  error.name = '__workerException__';",
	          "  __handled || __noBubble || postMessage(error);",
	          "  return true;",  //signal handled (no-bubble)        
	          "};",
	           "{0}"
	          ].join('\n'),
              
	        /**
	         * @private
	         */
	        setWorkerbyURL = function(config){
	            var _url, WP;
	            
	            if(_url = config ? config.url || config : this.url ){
	                _url  = Ext.isFunction(_url) ? _url() : _url;
	                
	                if(Ext.ux.Worker.enableGears){
	                    if(!gearsActive() || !google.gears.factory.getPermission()){
	                        return this.onError('The Google Gears framework is not initialized or permission is denied for domain:'+document.domain);
	                    }
	                    this.isGears = true;
	                    if(GPM && (WP = GPM.getWorkerPool(this))){
	                         this._worker || (this._worker = WP);
	                         
	                         if(Ext.ux.Worker.enableGearsWorkerEmulation){ 
	                             Ext.Ajax.request(
	                               Ext.apply({},{
	                                     url : _url,
	                                     disableCaching : this.disableCaching,
	                                     callback : function(opts, success, response){
	                                        if(success){
	                                           this.setWorkerbyFN(response.responseText);
	                                        }else{
	                                           this.onError('Retrieval of Worker '+opts.url+' failed.',response);
	                                        }
	                                     },
	                                     scope   :this
	                                },config)
	                              ); 
	                        }else {
	                            this.disableCaching && (_url = defeatCache(_url));
	                            GPM.register(this, this.id = WP.createWorkerFromUrl(_url));
	                            playPostQueue.call(this);
	                        }
	                     }
	                    return;
	                }
	                
	                this.disableCaching && (_url = defeatCache(_url));
	                
	                Ext.apply((this._worker = new window.Worker(_url)) || {},{
	                    onmessage : this.onMessage,
	                     onerror  : this.onError
	                });
	                
	                playPostQueue.call(this);
	            }
	      },
          
          /**
	       * @private
	       */      
	      setWorkerbyFN = function(config){
	             config = config || '';
	            if(Ext.ux.Worker.enableGears && gearsActive()){
	                var WP, fnText = fnToString(config.fn || config || '');
	                this.isGears = true;
	                if(GPM && (WP = GPM.getWorkerPool())){
	                     this._worker || (this._worker = WP);
	                     Ext.ux.Worker.enableGearsWorkerEmulation && (fnText = String.format(gearsHarness, fnText));
	                     GPM.register(this, this.id = WP.createWorker(fnText));
	                }
	                playPostQueue.call(this);
	                return;
	            }
	            return notImplemented.call(this);
	        },
            /**
	         * @private
	         */
	       playPostQueue  = function(){
	           
	           if(!this.getWorker())return;
	           
	           var PQ = this.postQueue || [];
	           while(!!PQ.length){
	             this.postMessage.apply(this, PQ.shift());
	           }
	        };
        
        
        return {
	     constructor : function(config){
            
	        config = Ext.isString(config) ? {url:config} : config || {};
	        
	        Ext.apply(this, {
	            onMessage : this.onMessage.createDelegate(this),
	            onError : this.onError.createDelegate(this)
	        },config);
	        
	        this.addEvents (
            /**
             * Fires when the a message is recieved from a Worker script.
             * @event message
             * @param {Ext.ux.Worker} this
             * @param {Mixed} message The received data intended for the Worker
             * @param {Object} payload The structure message payload object as received from the Worker.
             */
            
            'message', 
            
            /**
             * Fires when an unhandled error is propogated to the Worker class.
             * @event exception
             * @param {Ext.ux.Worker} this
             * @param {Event} error The Error event generated by the Worker.
             */
            
            'exception',
            
            /**
             * Fires when the Worker is about to terminate.  <p>A false return value will prevent termination of the Worker.</p>
             * @event terminate
             * @param {Ext.ux.Worker} this
             */
            
            'terminate');
	        
	        Ext.ux.Worker.superclass.constructor.call(this);
	        
	        delete config.listeners;
	        
	        if(this.fn){
	            setWorkerbyFN.call(this,this.fn);
	            delete this.fn;
	        }else { 
	            setWorkerbyURL.call(this,config);
	        }
            
            Ext.ux.Worker._pool.push(this);
	    },
	    
	    disableCaching  : true,
        
        /**
         * @cfg {Boolean} stringifyMessage True to serialize outgoing messages as strings (JSON-encoding where applicable).<p>Note: Firefox 3.5 can handle
         * native javascript object and Arrays. 
         * @default true
         */
        stringifyMessage : !Ext.isGecko3,
        
        /**
	     * <p>Sends a message payload to the Worker for processing.
	     * <br><p>Notes:</p><div class="mdetail-params"><ul>
	     * <li><b><u>Important</u></b>: postMessage is asynchronous! This call will return before the Worker has complete
	     * any actions as a result of the message. To perform any post-processing where information from the postMessage call is required, specify
	     * the <tt>callback</tt> function to be called, or use a {@link Ext.util.Observable#listeners a 'message' event handler}.</li></div>
	     * @param {Mixed} message The message to be sent. If the message is function, it will be executed and results used and postMessage payload.
         * If the message format is a serializable object or Array it will be converted to a String prior to transmission. 
	     * @param {Function} callback <div class="sub-desc"><p>An optional function to be called after a response or error condition is received
         * as a result of the posted message. The <tt>callback</tt> is passed the following arguments:<ul>
	     * <li><tt>worker</tt> : Ext.ux.Worker</li>
	     * <li><tt>success</tt>: Boolean success indicator</li>
         * <li><tt>message</tt>: The response message (or Error Object)</li></ul>
	     * @param {Object} scope<div class="sub-desc"><p>Optional scope with which to call the callback </div>
	     */       
	    postMessage : function(message, callback, scope){
        
           var msg= Ext.type(message) == 'function' ? message(): message;
           msg =  this.stringifyMessage && !this.isGears ? 
               (Ext.isArray(msg) || Ext.isObject(msg) ? Ext.encode(message) : String(message))
                 : message ;
           
	       var w, qm = [callback,scope].concat( this.isGears ? [msg, this.id]: msg) ;
           
	       if(w = this.getWorker()){
               if(Ext.type(qm[0]) == 'function'){
                  var cb = function(worker,messageIn){
                         this.un('exception', ecb, this);
                         qm[0].call(qm[1], worker, true, messageIn);
                      },
                      ecb = function(worker,error){
                         this.un('message', cb, this);
                         qm[0].call(qm[1], worker, false, error);
                      };
                  this.on('message',cb,this,{single:true}); 
                  this.on('exception',ecb,this,{single:true});
               }
               try{
	               return (w.sendMessage || w.postMessage).apply(w, slice(qm,2));
               }catch(workerErr){
                   return this.onError(workerErr);
               }
	       }
           
           this.postQueue || (this.postQueue = []).push(qm);
	    },
         
        
        /**
         * Terminates the active Worker
         * @param {Boolean} forced true to forcefully terminate the Worker
         */
	    terminate   : function(forced){
	       var w;
	       if((w = this.getWorker()) && (forced || this.fireEvent('terminate',this) !== false)){ 
              this.isGears ? GPM.deRegister(this) : w.terminate && w.terminate();
              this._worker = null;
	       }
	    },
        
        /**
         * @return {Worker} A reference to the active Worker
         */
	    getWorker   : function(){
            this.id || (this.id = Ext.id(this._worker,'Wrkr_'));
            return this._worker||null
            },
        
        /**
         * @private
         */
	    onMessage   : function(e){
            this.fireEvent.apply(this,['message',this, e.data].concat(slice(arguments)));
        },
        /**
         * @private
         */
	    onError     : function(e){
            e && e.preventDefault && e.preventDefault();
            e && this.fireEvent.apply(this,['exception',this].concat(slice(arguments)));
        },
        
	     /**
         * 
         */
	    destroy : function(){
	        this.terminate(true);
            this.purgeListeners();
            this.isDestroyed = true;
	        
	    }
      };
  });
  /**
   * @cfg {Boolean} enableGears True to use the Google Gears workerPool implementation 
   */
  Ext.ux.Worker.enableGears = false;
  
  /**
   * @cfg {Boolean} enableGearsWorkerEmulation True to enable use HTML5 implementation of 
   * the Worker objects using the GEARS workerPools.
   */
  Ext.ux.Worker.enableGearsWorkerEmulation = true;
  
  Ext.ux.Worker._pool = [];
  
  /**
   * Class method to destroy (terminate) all active Workers
   */
  Ext.ux.Worker.destroy = function(){
     Ext.destroy.apply(Ext, Ext.ux.Worker._pool);
     Ext.ux.Worker._pool = [];
  };
  
  //Terminate all class Workers on shutdown
  Ext.EventManager.on(window,'beforeunload', Ext.ux.Worker.destroy, Ext.ux.Worker);

 /**
     * Internal Error class for ux.Workers
     * @class Ext.ux.Worker.Error
     * @extends Ext.Error
     * @version 0.1 
     * @donate <a target="tag_donate" href="http://donate.theactivegroup.com"><img border="0" src="http://www.paypal.com/en_US/i/btn/x-click-butcc-donate.gif" border="0" alt="Make a donation to support ongoing development"></a>
     * @license MIT 
     * @author Doug Hendricks. Forum ID: <a href="http://extjs.com/forum/member.php?u=8730">hendricd</a> 
     * @copyright 2007-2009, Active Group, Inc. All rights reserved.
     * @constructor 
     * @param {String} message
     * @param {Mixed} arg optional argument to include in Error object.
     * @param {Object} overrides optional instance overrides to include in Error object.
     */
    Ext.ux.Worker.Error = Ext.extend(Ext.Error, {
        constructor : function(message) {
            Ext.Error.call(this, message);
        },
        name : 'Ext.ux.Worker',
        
        lang: {
            
        }
        
    });
    
})();


