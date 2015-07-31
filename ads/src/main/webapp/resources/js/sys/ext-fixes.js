

 if(Ext.isIE7 && document.documentMode == "8") {
   Ext.apply(Ext, {
      isIE7: false,
      isIE8: true
   });
}

Ext.override ( Ext.Layer, {
    // private
    destroy : function(){
        this.hideShim();
        this.shadow && this.shadow.hide();
        this.dom && Ext.Layer.superclass.remove.call(this);
    },

    remove : function(){
        this.destroy();
    }
});
 

 Ext.override(Ext.data.JsonReader, {
    createAccessor : function(){
        var re = /[\[\.]/;
        return function(expr) {
            if(Ext.isEmpty(expr)){
                return Ext.emptyFn;
            }
            if(Ext.isFunction(expr)){
                return expr;
            }
            var i = String(expr).search(re);
            if(i >= 0){
                return new Function('obj', 'return obj' + (i > 0 ? '.' : '') + expr);
            }
            return function(obj){
                return obj[expr];
            };
        };
    }()
});


// Private utility class that manages the internal Shadow cache
Ext.Shadow.Pools = function(){
    var p = [];
    var markup = Ext.isIE ?
                 '<div class="x-ie-shadow"></div>' :
                 '<div class="x-shadow"><div class="xst"><div class="xstl"></div><div class="xstc"></div><div class="xstr"></div></div><div class="xsc"><div class="xsml"></div><div class="xsmc"></div><div class="xsmr"></div></div><div class="xsb"><div class="xsbl"></div><div class="xsbc"></div><div class="xsbr"></div></div></div>';
    return {
        pull : function(){
            var sh = p.shift();
            if(!sh){
                sh = Ext.get(Ext.DomHelper.insertHtml("beforeBegin", document.body.firstChild, markup));
                sh.autoBoxAdjust = false;
            }
            return sh;
        },

        push : function(sh){
            p.push(sh);
        },
        destroy : function(){
          !!p.length && Ext.destroy(p);    
        }
    };
}();

    /**
     * @argument work Array to act as argument for each fn Function call
     */
 window.Yield = function(work, fn, fnScope, callback, cbScope, quantas){
        var w = [].concat(work), q = quantas || 100, results=null, index = 0;
        if(typeof fn === 'function'){
            if(!!w.length){
               setTimeout(function(){
                    var item = w.shift();
                    var r= fn.call(fnScope||item, item, index++);
                    if(callback && r !== undefined){
                        (results || (results = [])).push(r);
                    }
                    if (!!w.length){
                        setTimeout(arguments.callee, q);
                        return;
                    }
                    if(typeof callback === 'function'){
                        callback.call(cbScope,results);
                    }
               }, q);
            }else if(typeof callback === 'function'){
                 callback.call(cbScope,results);
            }
        }
    };

    
 
    Ext.override(Ext.tree.TreeEventModel, { 
	    trackExit : function(e){
	        if(this.lastOverNode){
	            if(this.lastOverNode.ui && !e.within(this.lastOverNode.ui.getEl())){
	                this.onNodeOut(e, this.lastOverNode);
	            }
	            delete this.lastOverNode;
	            Ext.getBody().un('mouseover', this.trackExit, this);
	            this.trackingDoc = false;
	        }
	    },
	    beforeEvent : function(e){
	        var node = this.getNode(e);
	        if(this.disabled || !node || !node.ui){
	            e.stopEvent();
	            return false;
	        }
	        return true;
	    }
	});

  /*
     * Windows XP timeZone database update instruction: http://support.microsoft.com/kb/914387
     */
Ext.override(Date, {
    /**
     * Whether or not the current date is on Daylight Saving Time (DST)
     * @return {Boolean} True if the current date is on DST.
     */
    isDST: function() {
        // adapted from http://extjs.com/forum/showthread.php?p=247172#post247172
        // courtesy of @geoffrey.mcgill
        return new Date(this.getFullYear(), 0, 1).getTimezoneOffset() != this.getTimezoneOffset();
    },

    clearTime : function(clone) {
        if (clone) {
            return this.clone().clearTime();
        }

        // check if clearing the time will move
        // this date into / out of DST
        var hr = 0;
        if (this.isDST() || this.add(Date.DAY, -1).isDST()) {
            var c = this.clone(); // clear the time on a cloned date first
            c.setHours(0);
            c.setMinutes(0);
            c.setSeconds(0);
            c.setMilliseconds(0);

            for (; c.getTimezoneOffset() != this.getTimezoneOffset(); hr++) {
                // increment zero hour until cloned date's DST mode = current date's DST mode
                c.setHours(hr);
            }
        }

        this.setHours(hr);
        this.setMinutes(0);
        this.setSeconds(0);
        this.setMilliseconds(0);

        return this;
    },
    getFirstDateOfWeek : function(){
        var value = this.clone();
        var dayOfWeek = this.getDay();
        dayOfWeek = (dayOfWeek + 6) % 7;
        value.setDate(value.getDate() - dayOfWeek);
        return value;
    },
    /**
     Returns the number of milliseconds between this date and date
     @param {Date} date (optional) Defaults to now
     @return {Number} The diff in milliseconds
     @member Date getElapsed
     */
    getElapsed : function(date) {
       return Math.abs((date || new Date()).getTime()-this.getTime());
    }
});

  /**
     * @class Ext.util.Format
     * Reusable data formatting functions
     * @singleton
     */
    Ext.apply( Ext.util.Format , {
        
            nl2br : function(v){
		        return Ext.isEmpty(v) ? '' : v.replace(/\r?\n/g, '<br/>');
		    },
        
            capitalizeAll : function(value){
                return (value || '').split(' ').map(Ext.util.Format.capitalize).join(' ');
            },
        
            yesNo : function(value){
                return !!value ? 'Yes':'No';  
            },
            
            /**
             * Convert either a UTC ISO 8601 Date object/string to the Browser's local time.
             * @argument Date/ISODateString utcDate
             * @argument string Date format string
             * @argument object tz timezone config object  
             * @return Date (adjusted)
             */
            UTCtoLocal : function(utcDate, format, tz){
                               
                tz || (tz = {auto:true});
                if(Ext.type(utcDate) == 'string'){
                   //will adjust to local time
                   utcDate= Date.parseDate(utcDate ,"c");
                }
                
                if( Ext.isDate(utcDate)){
                    var tzOffset = Ext.apply({},tz.offset || {});
                    if(tz.auto){
                        var o = String(new Date().getGMTOffset(true)).split(':')||[];
                        tzOffset.hours = parseInt(o[0],10);
                        tzOffset.minutes = parseInt(o[1],10);
                    }
                        
                    var d = utcDate.add(Date.HOUR, tzOffset.hours || 0).add(Date.MINUTE, tzOffset.minutes || 0);
                    return Ext.type(format) =='string' ? d.format(format) : d;
                    
                }
               
                return null;
      
            },
            
            /**
             * Convert Browser's local time to UTC Time
             * @argument Date/DateString localDate
             * @argument string Date format string 
             * @argument object tz timezone config object  
             * @return string/Date (adjusted) returns formated string if format code is specified, Date
             * object otherwise
             */
            LocalToUTC : function(localDate, format, tz){
                tz || (tz = {auto:true});               
                if(Ext.type(localDate) == 'string'){
                   localDate= new Date(localDate);
                }
                
                if( Ext.isDate(localDate)){
                    var tzOffset = Ext.apply({},tz.offset || {});
                    if(tz.auto){
                        var o = String(new Date().getGMTOffset(true)).split(':')||[];
                        tzOffset.hours = parseInt(o[0],10);
                        tzOffset.minutes = parseInt(o[1],10);
                    }
                    var d = localDate.add(Date.HOUR, -1 * (tzOffset.hours || 0)).add(Date.MINUTE, -1 * (tzOffset.minutes || 0));
                    return Ext.type(format) =='string' ? d.format(format) : d;
                    
                }
               
                return null;
      
            }
       
    });
    
  Ext.override(Ext.data.Store,{
      clone : function(){
        var records = [];
        this.each(function(record){ records.push( record.copy() ); });
            
        var tStore = new (this.constructor)({
            recordType: this.recordType,
            reader    : this.reader
        });
        
        tStore.add(records);
        return tStore;
      }
  });

   
/**
 * @class Ext.util.CSS
 * Utility class for manipulating CSS rules
 * @singleton
 */
Ext.util.CSS = function(){
    var rules = null;
    var doc = document;

    var camelRe = /(-[a-z])/gi;
    var camelFn = function(m, a){ return a.charAt(1).toUpperCase(); };

   return {


  /**
   * Sets the document context for the next CSS operation
   * @param {Document} docum The target document context
   */
   setDocument  :  function(docum){
       if(docum) {
           if(doc != docum){
               //reset the cached Rules on document change
               rules = null;
           }
           doc = docum;
       }
   },

   /**
    * Creates a stylesheet from a text blob of rules.
    * These rules will be wrapped in a STYLE tag and appended to the HEAD of the document.
    * @param {String} cssText The text containing the css rules
    * @param {String} id An id to add to the stylesheet for later removal
    * @return {StyleSheet}
    */
   createStyleSheet : function(cssText, id){
       var ss;
       var head = doc.getElementsByTagName("head")[0];
       var rules = doc.createElement("style");
       rules.setAttribute("type", "text/css");
       if(id){
           rules.setAttribute("id", id);
       }
       if(Ext.isIE){
           head.appendChild(rules);
           ss = rules.styleSheet;
           ss.cssText = cssText;
       }else{
           try{
                rules.appendChild(doc.createTextNode(cssText));
           }catch(e){
               rules.cssText = cssText;
           }
           head.appendChild(rules);
           ss = rules.styleSheet ? rules.styleSheet : (rules.sheet || doc.styleSheets[doc.styleSheets.length-1]);
       }
       this.cacheStyleSheet(ss);
       return ss;
   },

   /**
    * Removes a style or link tag by id
    * @param {String} id The id of the tag
    */
   removeStyleSheet : function(id){
       var existing = doc.getElementById(id);
       if(existing){
           existing.parentNode.removeChild(existing);
       }
   },

   /**
    * Dynamically swaps an existing stylesheet reference for a new one
    * @param {String} id The id of an existing link tag to remove
    * @param {String} url The href of the new stylesheet to include
    */
   swapStyleSheet : function(id, url){
       this.removeStyleSheet(id);
       var ss = doc.createElement("link");
       ss.setAttribute("rel", "stylesheet");
       ss.setAttribute("type", "text/css");
       ss.setAttribute("id", id);
       ss.setAttribute("href", url);
       doc.getElementsByTagName("head")[0].appendChild(ss);
   },

   /**
    * Refresh the rule cache if you have dynamically added stylesheets
    * @return {Object} An object (hash) of rules indexed by selector
    */
   refreshCache : function(){
       return this.getRules(true);
   },

   // private
   cacheStyleSheet : function(ss, media){
       if(!rules){
           rules = {};
       }
       
       try{// try catch for cross domain access issue
          
          Ext.each(ss.cssRules || ss.rules || [], 
            function(rule){ 
              this.hashRule(rule, ss, media);
          }, this);  
          
          //IE @imports
          Ext.each(ss.imports || [], 
	       function(sheet){
	          sheet && this.cacheStyleSheet(sheet,this.resolveMedia([sheet, sheet.parentStyleSheet]));
	       }
	      ,this);
          
        }catch(e){}
   },
   
   // @private
   hashRule  :  function(rule, sheet, mediaOverride){
      
      var mediaSelector = mediaOverride || this.resolveMedia(rule);
      
      //W3C @media
      if( rule.cssRules || rule.rules){
          this.cacheStyleSheet(rule, this.resolveMedia([rule, rule.parentRule ]));
      } 
      
       //W3C @imports
      if(rule.styleSheet){ 
         this.cacheStyleSheet(rule.styleSheet, this.resolveMedia([rule, rule.ownerRule, rule.parentStyleSheet]));
      }
      
      rule.selectorText && 
        Ext.each((mediaSelector || '').split(','), 
           function(media){
            rules[((media ? media.trim() + ':' : '') + rule.selectorText).toLowerCase()] = rule;
        });
      
   },

   /**
    * @private
    * @param {Object/Array} rule CSS Rule (or array of Rules/sheets) to evaluate media types.
    * @return a comma-delimited string of media types. 
    */
   resolveMedia  : function(rule){
        var media;
        Ext.each([].concat(rule),function(r){
            if(r && r.media && r.media.length){
                media = r.media;
                return false;
            }
        });
        return media ? (Ext.isIE ? String(media) : media.mediaText ) : '';
     },
   /**
    * Gets all css rules for the document
    * @param {Boolean} refreshCache true to refresh the internal cache
    * @return {Object} An object (hash) of rules indexed by selector
    */
   getRules : function(refreshCache){
        if(rules === null || refreshCache){
            rules = {};
            var ds = doc.styleSheets;
            for(var i =0, len = ds.length; i < len; i++){
                try{
                    this.cacheStyleSheet(ds[i]);
                }catch(e){}
            }
        }
        return rules;
    },

    /**
    * Gets an an individual CSS rule by selector(s)
    * @param {String/Array} selector The CSS selector or an array of selectors to try. The first selector that is found is returned.
    * @param {Boolean} refreshCache true to refresh the internal cache if you have recently updated any rules or added styles dynamically
    * @param {String} mediaSelector Name of optional CSS media context (eg. print, screen)
    * @return {CSSRule} The CSS rule or null if one is not found
    */
   getRule : function(selector, refreshCache, mediaSelector){
        var rs = this.getRules(refreshCache);

        if(Ext.type(mediaSelector) == 'string'){
            mediaSelector = mediaSelector.trim() + ':';
        }else{
            mediaSelector = '';
        }

        if(!Ext.isArray(selector)){
            return rs[(mediaSelector + selector).toLowerCase()];
        }
        var select;
        for(var i = 0; i < selector.length; i++){
            select = (mediaSelector + selector[i]).toLowerCase();
            if(rs[select]){
                return rs[select];
            }
        }
        return null;
    },


    /**
    * Updates a rule property
    * @param {String/Array} selector If it's an array it tries each selector until it finds one. Stops immediately once one is found.
    * @param {String} property The css property
    * @param {String} value The new value for the property
    * @param {String} mediaSelector Name(s) of optional media contexts. Multiple may be specified, delimited by commas (eg. print,screen)
    * @return {Boolean} true If a rule was found and updated
    */
   updateRule : function(selector, property, value, mediaSelector){
    
	     Ext.each((mediaSelector || '').split(','), function(mediaSelect){    
	        if(!Ext.isArray(selector)){
	            var rule = this.getRule(selector, false, mediaSelect);
	            if(rule){
	                rule.style[property.replace(camelRe, camelFn)] = value;
	                return true;
	            }
	        }else{
	            for(var i = 0; i < selector.length; i++){
	                if(this.updateRule(selector[i], property, value, mediaSelect)){
	                    return true;
	                }
	            }
	        }
	        return false;
	     }, this);
    },
    
    removeRule : function(selector, mediaSelector){
        
        var rule, removed;
        if(!Ext.isArray(selector)){
	        if(Ext.type(selector) == 'string'){
                rule = this.getRule(selector, false, mediaSelect);    	            
	        }
            
        }else{
            removed = [];
            for(var i = 0; i < selector.length; i++){
                if(rule = this.removeRule(selector[i], mediaSelector)){
                    removed.push(rule);
                }
            }
        }
        return removed;
    }
    
   };
}();


Ext.override(Ext.Element, {
    
    getAttribute : 'getAttribute' in document.createElement('div') ? 
	    function(name, ns){
	        var d = this.dom;
            return (ns && d.getAttributeNS ? d.getAttributeNS(ns, name) : null) ||  
                 (ns ? d.getAttribute(ns + ":" + name) : d.getAttribute(name) || d[name]);
	    }:
	    function(name, ns){  //likely IE < 8
	        var d = this.dom, 
    	        attrib = ns ? 
                    (!(/undefined|unknown/).test(typeof (attrib = d[ ns + ":" + name ])) ? attrib : undefined): 
                      d[name];
            return attrib || '';
	    },

    
    fireDOMEvent: (function() {
        var HTMLEvts = /^(scroll|resize|load|unload|abort|error)$/,
            mouseEvts = /^(click|dblclick|mousedown|mouseup|mouseover|mouseout|contextmenu|mousenter|mouseleave)$/,
            UIEvts = /^(focus|blur|select|change|reset)$/,
            keyEvts = /^(keypress|keydown|keyup)$/,
            onPref = /^on/;

        var H = Ext.isIE ? 
        
         function Handler(e, props, winContext) {
            e = e.toLowerCase();
            if (!onPref.test(e)) {
                e = 'on' + e;
            }
            var D = this.getDocument(), EV = D ? D.createEventObject() : null;
            EV && Ext.isObject(props) && Ext.apply(EV, Ext.clone(props.browserEvent || props)); //removes any prototypes and function
            return EV ? this.dom.fireEvent(e, EV) : false;
          } 
          :
         function Handler(e, props, winContext) {
            var attribs = Ext.clone((props ? props.browserEvent || props : props) || {}),
                bubbles = !!Ext.value(attribs.bubbles, true),
                cancelable = !!Ext.value(attribs.cancelable, true),
                evt, 
                D = this.getDocument(), 
                win = winContext || ( D ? D.defaultView || window : window );
            
            D || (D = win.document);
            
            if(!D)return false;
            
            e = e.toLowerCase();
            e.replace(onPref, '');
            
            if (mouseEvts.test(e)) {
                var b = this.getBox(),
                    x = b.x + b.width / 2,
                    y = b.y + b.height / 2;
                evt = D.createEvent("MouseEvents");
                evt && evt.initMouseEvent(e, bubbles, cancelable, win, (e=='dblclick')?2:1, x, y, x, y, false, false, false, false, 0, null);
            } else if (UIEvts.test(e)) {
                evt = D.createEvent("UIEvents");
                evt && evt.initUIEvent(e, bubbles, cancelable, win, 1);
            } else if (HTMLEvts.test(e)) {
                evt = D.createEvent("HTMLEvents");
                evt && evt.initEvent(e, bubbles, cancelable);
            } else if (keyEvts.test(e)){
                evt = D.createEvent("KeyboardEvent");
                evt && evt.initKeyEvent(e, 
                                 bubbles, 
                                 cancelable, 
                                 win,  
                                 attribs.ctrlKey || false,  
                                 attribs.altKey  || false,  
                                 attribs.shiftKey  || false, 
                                 attribs.metaKey || false, 
                                 attribs.keyCode || 0, 
                                 attribs.charCode || 0 
                                 );
            } else { //generic fallback 
                evt = D.createEvent("Event");
                evt && evt.initEvent(e, bubbles , cancelable);
            }
            //Returns false if preventDefault was called or returnValue == false 
            return evt ? this.dom.dispatchEvent(evt) : false;
        };
        var Handler = null;
        return H;
    })()
});

Ext.apply(Ext, {
        /**
         * Appends content to the query string of a URL, handling logic for whether to place
         * a question mark or ampersand.
         * @param {String} url The URL to append to.
         * @param {String/Object} s The content (string or hash object) to append to the URL.
         * @return (String) The resulting URL
         */
    urlAppend : function(url, s){
        if(!Ext.isEmpty(s)){
            var parts = url ? String(url).split('#') : [''];
            parts[0] += (parts[0].indexOf('?') === -1 ? '?' : '&') + (Ext.isObject(s) ? Ext.urlEncode(s) : s);
            return parts.length > 1 ? parts.join('#') : parts[0]; 
        }
        return url;
    }
}); 

 
 
 /**
   Sample:

asImage = function(){
    var img=new Ext.BoxComponent({
     height : 300,
     width  : 400,
     id:'myImage',
     autoEl:{
      tag:'img',
      border : 1,
      src:Ext.BLANK_IMAGE_URL
     },
     boundEvents:
        {el : [
            ['click', function(c) {alert('clicked'); }],
            {load:  function(c) {alert('loaded'); }, single : true }
           ]
     }
  });
  img.render(Demo.canvas.body);
  return img;
};
*/
  Ext.override( Ext.Component , {
   
    afterRender : function(){
       
       if(Ext.isObject(this.boundEvents)){
            Ext.iterate(this.boundEvents, function(ref, listeners){
                if(ref = this[ref]){
                    Ext.each([].concat(listeners), function(listener){
                         this.mon.apply(this,[ref].concat(listener)) 
                   },this);
                }
           }, this);
           delete this.boundEvents;
        }
    }
  });
  
