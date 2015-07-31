
 /*
  ************************************************************************************
  * Author: Doug Hendricks. doug[always-At]theactivegroup.com
  * Copyright 2007-2009, Active Group, Inc.  All rights reserved.
  ************************************************************************************
  */

//Demo Site Startup shell.

//Ext.Container.prototype.bufferResize = true;

Ext.USE_NATIVE_JSON = false;
 (function(){

    var F = Ext.util.Format;
    
    //Google Analytics hash
    var gaKeys = {
        basex   : 'UA-4905757-1',
        media   : 'UA-6286277-1',
        flash   : 'UA-6286277-1',
        flex    : 'UA-6286277-1',
        mif     : 'UA-5567859-1',
        charts  : 'UA-6286277-1',
        workers  : 'UA-11723726-1'
    };

   /*
    * Setup the Demo namespace
    */
    Ext.ns('Demo');

    //scan the URL for specific demo targets  eg. ?demo=flickr
    var url = location.href.split('#')[0],
        params = url.split('?')[1] || '',
        args = Ext.urlDecode(params) || {}; 
        
    //alert("ok1"); 
    var announce =function(ecode,title){
          if(!ecode)return;

          var ec = ecode.error || ecode;
          var msg = [(ec.message || ec.description || ec.name || ec.statusText || String(ec))] ;
          Ext.type(ec) != 'string' && 
             forEach(ec, function(val, key){
              msg.push(key + " : " + val);
             });

          if(msg){
            
              if(Ext.MessageBox){
                  Ext.MessageBox.alert(title,msg.join('<br/>'));
              } else {
                  alert((title?title+'\n':'')+msg.join('\n'));
              }

          }
      };
     //alert("ok2");
      //alert(args.demo);
      Ext.apply(Demo,{
          cleanup : [],
          script  : args.script || args.demo ,
          klass   : args.demo,
          
          map     : {
              mif  :  {
                treenav : { klass : 'mif', script: 'treenav', demoCls : 'MIFTreeNav' , id:'treeNav'},
                mifsubmit : { klass : 'mif', script: 'mifsubmit', demoCls : 'MIFSubmit' , id:'mifsubmit'},
                mifsimple : { klass : 'mif', script: 'mifsimple', id:'mifsimple' },
                target    : { klass : 'mif', script: 'target', id:'miftarget', demoCls : 'MIFTarget'},
                heartbeat : { klass : 'mif', script: 'heartbeat', id:'mifmsg' }
                
              },
              media: {
                acrobat  : { klass : 'media', script: 'acrobat', demoCls : 'Acrobat' , id:'acrobatWin'},
                excel    : { klass : 'media', script: 'excel', demoCls : 'Excel' , id:'excelWin'},
                odt      : { klass : 'media', script: 'odt', demoCls : 'ODT' , id:'odtWin'},
                'oo-xls' : { klass : 'media', script: 'oo-xls', demoCls : 'OOXLS' , id:'ooxlsWin'},
                outlook  : { klass : 'media', script: 'outlook', demoCls : 'Outlook' , id:'outlookWin'},
                jwplayer : { klass : 'media', script: 'jwplayer', demoCls : 'JWPlayer' , id:'JWPWin'},
                jwplayer5demo : { klass : 'media', script: 'jwplayer5demo', demoCls : 'JWPlayer5' , id:'JWP5Win'},
                flowplayer : { klass : 'media', script: 'flowplayer', demoCls : 'FlowPlayer' , id:'FlowWin'},
                audioev  : { klass : 'media', script: 'audioev', id:'audioev'}
                
              },
              flash : {
                fusionw : { klass : 'flash', script: 'fusionw', demoCls : 'FusionWidgets' , id:'fusionWidgetWin'}
              },
              charts : {
                fusion : { klass : 'charts', script: 'fusion', demoCls : 'FusionWindow' , id:'fusionWin'},
                ofc    : { klass : 'charts', script: 'ofc', demoCls : 'OFCWindow' , id:'ofcWin'}
              },
              basex  : {
                flickr : { klass : 'basex', script: 'flickr', demoCls : 'FlickerWindow' , id:'flickrWin'},
                multipart : { klass : 'basex', script: 'multipart', demoCls : 'MultiPartWindow' , id:'multipart'}
              },
              workers : {
                'base64-demo' : { klass : 'workers', script: 'base64-demo', demoCls : 'CryptoWorker' , id:'base64Win'}
              },
              flex: {
                bridging  : { klass : 'flex', script: 'bridging', demoCls : 'Flex' , id:'flexWin'}
              }
            
          }
          ,
          
          
          debug   : (Ext.ResourceLoader.debug = !!args.debug),
          destroy : function(){
              //forEach(this.cleanup, function(comp){ Ext.destroy(comp); }, Ext);
              this.store && Ext.destroy(this.store);
              Ext.destroy(Ext.ResourceLoader, Ext.QuickTips.getQuickTip(),Ext.Shadow.Pool);
              //Ext.Element._flyweights = {};
              this.codeLife = null;
              
          },
          /**
           * Loads a specified Demo module either directly
           * or as a menu handler
           */
          launch  : function(cfg, e){
              
              cfg = cfg.launch || cfg;  
            
              var klass = cfg.klass;
              var script = cfg.script;
              var anim = Ext.getDom(cfg.anim);
              
              var module = Demo.resolveURL(klass, script);//获取js路径
              
              if(module){ 
                $JIT.loaded(module) || (window._gat && _gat._getTracker(gaKeys[klass] || "UA-5706578-1")._trackPageview()); 
                $JIT.script(module);
                //Assign each demo to its respective shortcut group
                //alert(klass);
                var group = Ext.getCmp('shortcut-'+klass);//mif
                
                if(group){
                    
                    anim || (anim = group.btnEl);
                    group.winGroup || (group.winGroup = 
                      Ext.apply(
                       new Ext.WindowGroup(),
                        {
                           showAll : function(){
                        	  alert('show');
                              this.each(function(win){ win.show(); });
                           }
                        })
                       );
                }
                
                if(cfg.demoCls){
                	//alert('11');
                    //Wait until the class arrives
	                $JIT.onClassAvailable('Demo.'+cfg.demoCls, function(ok, Class){
	                  if(!ok){
                        alert('Couldn\'t Locate Demo.'+cfg.demoCls );
                        return;
                      }
	                  if( cfg.id){ 
	                    var comp = Ext.getCmp(cfg.id) || 
	                        new Class({
	                            id:cfg.id,
	                            //closeAction: 'hide',
	                            manager : group ? group.winGroup : null
	                           });
	                    alert(cfg.id);
	                    comp.show(comp.animCollapse && anim ? anim : null);
                      }
                    });        
                  } else { 
                  
                   $JIT.onAvailable(module, function(loaded){
		                   if(loaded && cfg.id){
		                     var comp = Ext.getCmp(cfg.id);
		                     if(comp){
		                       comp.show(group && comp.animCollapse ? group.el : null);
		                       if(group && Ext.isDefined(comp.manager) && comp.manager !== group.winGroup){
		                          comp.manager.unregister(comp);
		                          group.winGroup.register(comp);
		                          comp.manager = group.winGroup; 
		                          group.winGroup.bringToFront(comp); 
		                       }
		                     }
		                  }
	                });
                  }
              }    
                    
              return module;
          },
          
          
          /**/
          register : function(comp, prepend){
              var smod, mod, tabs, newTabs = [];
              tabs = comp ? comp.demoTabs || (comp.getWindowRegion ? (tabs = comp.getWindowRegion('center')): null) : null;
              if(tabs){
                 //Load any defined public server-side scripts into a tab
                 if(mod = comp.sourceModule){
                      forEach([].concat(mod).compact() , function(s){
                          newTabs.push(this.loadRemoteSource(s, tabs, {closable:true} ));
                       },this);
                 }
                 newTabs.length && tabs.doLayout();
              }
              comp && this.cleanup[prepend?'unshift':'push'](comp);
              return comp;

          },
          
          /**
           * Add a source-file (eg. php script) dump to a Container.
           */
          loadRemoteSource : function(url, container, cfg){
            var file = (url||'').split('\/').last(), 
                extension = file.split('.').last().toLowerCase(),
                extensionMap = {js:'javascript'};
            
	        var viewer = file && container && container.add ? 
               container.add(
                Ext.apply( 
                {
                   xtype       : 'panel',
	               title       : 'Demo Source: '+file,
	               iconCls     : 'source-icon',
	               autoScroll  : true,
	               preventBodyReset : true,
                   hideMode    : 'nosize',
                   plugins : Demo.audioPlugin,
	               renderer    : {
                      render : function(el, response, updateManager, callback) {
	                    extension = extensionMap[extension] || extension;
                        if(Demo.codeLifeWorker){
	                        Demo.codeLifeWorker.postMessage({ styleSet:extension ,text: response.responseText},
	                           function(worker, success, markup){
	                                el.addClass(['codelife',extension]);
	                                el.update(String.format('<pre class=\"code\"><code class="pre {1}">{0}</code></pre>'+
                                        Demo.workerLogo ,
	                                    success? markup : 'Error, lineno '+markup.lineno + ", " + markup.message ,extension));
	                                Ext.isFunction(callback) && callback();      
	                           }
	                        );
                        }else{
                        
		                    el.update(String.format('<pre class=\"code\"><code class="pre {1}">{0}</code></pre>',
	                                F.htmlEncode(response.responseText),extension));
		
		                    Demo.codeLife.highlightElements (extension, 'code.'+extension, false, Ext.getDom(el));
		                    Ext.isFunction(callback) && callback();
	                    }
                      }
	               },
	               autoLoad    : {
	                   url : 'getScript.php',
	                   method :'POST',
	                   params : {file : url, mime: 'text/plain' }
	               }
	             }, cfg || {})) 
                : null;
               
               return viewer;
              
          },
          
          
          deRegister : function(comp){

            if(comp && comp.id) { this.cleanup.remove(comp);}

          },
          
          /**
           * 亲们，下面这块，是偶注释的，如果要放开请加载相应的js，
           */
          
          /*
          codeLife : Ext.CodeLife,
          
          codeLifeWorker : new Ext.ux.Worker({
            url:'demos/workers/codelife-worker-debug.js',
            listeners : {
                exception : function(worker, error){
                    alert('CodeLife Worker Error: ' + error.message +'\nContinuing with default CodeLife class');
                    worker.destroy();
                    Demo.codeLifeWorker = null; //Fallback to Ext.CodeLife class
                }
            }
          }),
          */
          workerLogo : '<span class="codelife-logo"><i>Source Formatting provided by the CodeLife Worker</i><br/></span>',
          
          resolveURL : function(klass, script){
             //return klass ? String.format('demos/{0}/{1}', klass, script || klass): null;
             return klass ? String.format('module/B_mif/{1}', klass, script || klass): null;
          },
          
          balloon : function(){
		       createBox = function(t, s) {
		          return ["<div class=\"msg\">"
		                , "<div class=\"x-box-tl\"><div class=\"x-box-tr\"><div class=\"x-box-tc\"></div></div></div>"
		                , "<div class=\"x-box-ml\"><div class=\"x-box-mr\"><div class=\"x-box-mc\"><h3>"
		                , t, "</h3>", s
		                , "</div></div></div>"
		                , "<div class=\"x-box-bl\"><div class=\"x-box-br\"><div class=\"x-box-bc\"></div></div></div>"
		                , "</div>"].join("");
		           };
		       return function(options, title, format){
		            options || (options = {});
		            if(!this.balloonCt){
		                this.balloonCt = Ext.DomHelper.insertFirst(document.body, 
		                    {id:'demo-balloon-div', style:{'z-index':11000}}, true);
		              }
		              this.balloonCt.alignTo(Ext.getBody(), options.align ||'t-t');
		              var s = String.format.apply(String, Array.prototype.slice.call(arguments, 2));
		              var m = Ext.DomHelper.append(this.balloonCt, {html:createBox(title||'', s)}, true);
		              m.slideIn('t').pause(options.wait || 1.5).ghost("t", {remove:true});
		            };
		     }()
		  
      });
  
  //alert("ok3");
  
  Ext.EventManager.on(window,   "beforeunload",  Demo.destroy ,Demo,{single:true});
  
  Ext.onReady(function() {
     
    Ext.QuickTips.init();
    document.title += ' for Ext '+Ext.version;
        
    $JIT.on(
       {'timeout': function( MM, module ){
              announce('While waiting for: ' + module?module.name+":"+module.url:'' ,'JIT Timeout');
         },

        'loadexception' : function(MM, module, errorCode ){
              announce(errorCode ,module?module.name+":"+module.url:'');
         }
     });
//alert("");
    /*     
     setTimeout(function(){
                Ext.get('loading').remove();
                Ext.get('loading-mask').fadeOut({remove:true});
            }, 2000);
    */
    
    /**/
    $JIT.onAvailable(['demowin','@uxflash'],   //the base class for all demo Windows    

    
      function(loaded){
        var module;
        //alert(Ext.ux.Media);
        if(loaded){
	       
            
           //Shared Audio Plugin instance 
        	/*
            Demo.audioPlugin = new Ext.ux.Media.plugin.AudioEvents({
              audioEvents :{
                 beforecollapse : 'demos/media/audio/Utopia Restore Down.WAV',
                 beforeexpand : 'demos/media/audio/Utopia Restore Up.WAV',
                 beforeclose : 'demos/media/audio/Utopia Windows Exit.WAV',
                 beforeshow : 'demos/media/audio/Utopia Open.WAV',
                 maximize : 'demos/media/audio/Utopia Maximize.WAV',
                 minimize : 'demos/media/audio/Utopia Minimize.WAV',
                 restore : 'demos/media/audio/Utopia Restore Up.WAV'
              }
            });
            */
	        //If URL params are present, launch the desired script
	        if(Demo.klass && Demo.script){
              var M = Demo.map, cfg = M[Demo.klass][Demo.script];
              cfg && Demo.launch( cfg ); 
            }
            
	        /*
		    Demo.register(  new Ext.Viewport({
		          layout : 'border',
		          items :[
			          { 
				       region: 'center',
		               layout : 'fit',	               
				       listeners : {
				          render : function(P){
				        	 //alert(P);
		                     Demo.canvas = P;
		                     //Demo.Window instances are constrained to the body
		                     Demo.Window.prototype.renderTo = P.body;
                             
                             mclock=new Ext.ux.Media.Flash(
                                {mediaCfg:{
		                            url    : 'demos/media/clock.swf'
		                            ,id     : 'inlineClock'
		                            ,style  : {display:'inline', width:'100px',height:'80px'}
		                            ,start    : true
		                            ,loop     : true
		                            ,controls : false
		                            ,params: {
		                                wmode     :'transparent'
		                               ,scale     :'exactfit'
		                               ,salign    :'t'
		                            }
		                       }});
                             
                             this.body.update( mclock.asMarkup() );
                          }
				       }
				      }
				      ,
			          { xtype:'shortcuts', 
		               region:'west',
		               iconCls : 'demo-action',
		               cls : 'x-plain',
		               split : true,
		               title : 'Other Demos',
		               collapsible : true,
		               collapsed : Demo.demo && Demo.script,
		               width : 125, 
		               plugins : [
		                   Demo.audioPlugin, 
		                   {ptype: 'toolstips', 
		                     tips:{
		                       expand : 'See more Demos (Experimental)',
		                       toggle : 'Hide these'
		                     }}
		                 ],
		               disableEditing : true, 
		              
		               defaults : {
		                  arrowAlign : 'right',
		                  iconCls    : 'demo-action',
		                  iconAlign  : 'top',
		                  enableToggle : true,
		                  pressed    : false
		               },
		               listeners : {
		                  shortcut : function(shortcuts, shortcut){
		                    if(shortcut.href){
		                        window.open(shortcut.href,shortcut.hrefTarget);
		                    }else if(shortcut.enableToggle){ //Hide/show all demos in the shortcut group 
		                        var group = shortcut.winGroup;
		                        if(group){
		                            group[shortcut.pressed? 'hideAll' : 'showAll']();
		                        }
		                    }
		                          
		                  }
		               },
		               items  : [
			               {text  :'MIF 2.0',
		                   id     : 'shortcut-mif',
		                   tooltip : 'Hide/Show active MIF Demos',
		                       menu : { 
		                         items: [
		                            {text     : 'Westside Story', 
		                             iconCls  : 'demo-folder' ,
		                             tip      : 'Tree drives IFRAME tabs',
		                             launch   : Demo.map['mif']['treenav'],
		                             handler  : Demo.launch
		                             
		                             },
		                             {text     : 'Submit Form (Targeted PDF)', 
		                             iconCls  : 'media-pdf',
		                             launch   : Demo.map['mif']['mifsubmit'],
		                             handler  : Demo.launch
		                             
		                             },
		                             {text   : 'MIF Simple (Frame Writing)',
		                            launch   : Demo.map['mif']['mifsimple'],
		                            handler  : Demo.launch
		                             },
		                             {text   : 'MIF Link Target',
			                            launch   : Demo.map['mif']['target'],
			                            handler  : Demo.launch
		                             },
		                            '-',
		                            {text : 'Documentation' ,
		                            iconCls : 'frustrated',
		                            href   : 'htt' +'p://uxdocs.theactivegroup.com/index.html?class=Ext.ux.ManagedIFrame.Element',
		                            hrefTarget : 'uxdocsmif'
		                            
		                            } 
		                          ]
		                        }
		                       },
			               {text    : 'ux.Media',
		                     id     : 'shortcut-media',
		                      tooltip : 'Hide/Show active ux.Media Demos',
		                       menu : { 
		                         items: [
		                            {text    : 'JWP5 Flash Player Sample',
		                            launch   : Demo.map['media']['jwplayer5demo'],
		                            iconCls : 'media-flash',
		                            handler  : Demo.launch
		                             },
		                             {text    : 'Flow Player Sample',
		                            launch   : Demo.map['media']['flowplayer'],
		                            iconCls : 'media-flash',
		                            handler  : Demo.launch
		                             },
		                             {text   : 'Acrobat (PDF) Documents',
		                             iconCls : 'media-pdf',
		                            launch   : Demo.map['media']['acrobat'],
		                            handler  : Demo.launch
		                             },
		                             {text   : 'MSOffice - Excel Documents',
		                             iconCls : 'media-excel',
		                              disabled : !Ext.isIE,
		                            launch   : Demo.map['media']['excel'],
		                            handler  : Demo.launch
		                             },
		                             {text   : 'Outlook Calendar',
		                             disabled : !Ext.isIE,
		                             iconCls : 'media-outlook',
		                            launch   : Demo.map['media']['outlook'],
		                            handler  : Demo.launch
		                             },
		                             {text    : 'Open Office Text',
		                             iconCls : 'media-openoffice-txt',
		                            launch   : Demo.map['media']['odt'],
		                            handler  : Demo.launch
		                             },
		                            {text    : 'Open Office XLS Document',
		                             iconCls : 'media-excel',
		                            launch   : Demo.map['media']['oo-xls'],
		                            handler  : Demo.launch
		                             },                             
		                             {text   : 'Audio Events',
		                             iconCls : 'demo-audio',
		                            launch   : Demo.map['media']['audioev'],
		                            handler  : Demo.launch
		                             },
                                    {text   : 'Flash',
                                    iconCls : 'media-flash',
                                      menu: {
                                         items: [  
	                                         {text : 'Fusion Widgets', 
	                                         iconCls : 'media-flash',
	                                          launch   : Demo.map['flash']['fusionw'],
	                                          handler  : Demo.launch
	                                         },
                                             {text : 'Flex (FABridge)', 
                                             iconCls : 'media-flash',
                                              launch   : Demo.map['flex']['bridging'],
                                              handler  : Demo.launch
                                             }
                                             ]
                                      }
                                    },
		                            '-',
		                            {text   : 'Documentation' ,
		                            iconCls : 'frustrated',
		                            href    : 'http://uxdocs.theactivegroup.com/index.html?class=Ext.ux.Media.Flash',
		                            hrefTarget : 'uxdocsmedia'
		                            
		                            } 
		                          ]
		                        }
		                      },
		                   {text    :'ux.Media.Charts',
		                     id     : 'shortcut-charts',
		                    tooltip : 'Hide/Show active Chart Demos',
		                       iconCls : 'media-chart',
		                       menu : { 
		                         items: [
		                            {text     : 'Fusion Charts' , iconCls : 'media-chart',
		                             launch   : Demo.map['charts']['fusion'],
		                             handler  : Demo.launch
		                            },
		                            {text     : 'OpenFlash Charts' , iconCls : 'media-chart',
		                             launch   : Demo.map['charts']['ofc'],
		                             handler  : Demo.launch
		                            },
		                            '-',
		                            {text   : 'Documentation' ,
		                            iconCls : 'frustrated',
		                            href    : 'http://uxdocs.theactivegroup.com/index.html?class=Ext.ux.Chart.FlashAdapter',
		                            hrefTarget : 'uxdocscharts'
		                            
		                            } 
		                          ]
		                        }
		                       
		                       },
		                   {text   :'ext-basex',
		                   id      : 'shortcut-basex',
		                   tooltip : 'Hide/Show active ext-basex Demos',
		                    menu : { 
		                         items: [
		                            {text     : 'Flickr JSONP Demo' , 
		                             launch   : Demo.map['basex']['flickr'],
		                             handler  : Demo.launch
		                            },
		                            {text     : 'Multipart Responses' , 
		                             launch   : Demo.map['basex']['multipart'],
		                             handler  : Demo.launch
		                            },
		                            '-',
		                            {text   : 'Documentation' ,
		                            iconCls : 'frustrated',
		                            href    : 'http://uxdocs.theactivegroup.com/index.html?class=Ext.ux.Chart.FlashAdapter',
		                            hrefTarget : 'uxdocscharts'
		                            
		                            } 
		                          ]
		                        }
		                   
		                   },
		                   { 
		                    iconCls : 'demo-donate',
		                    id      : 'shortcut-donate',
		                    scale: 'large',
		                    autoWidth : true,
		                    autoHeight : true,
		                    tooltip : 'Donations and Licensing',
		                    href    : 'http://donate.theactivegroup.com/',
		                    hrefTarget : 'uxdocscharts'
		                    }
		                    
		                  ]
		              }//west 结束
				      
				      
		          ]
		        }));
		    */
		    
		    var tabs = new Ext.TabPanel({
	            region: 'center',
	            margins:'3 3 3 0', 
	            activeTab: 0,
	            defaults:{autoScroll:true},

	            items:[
	                   {
	                    title: 'Bogus Tab',
	                    html: 'a'
	                   }
	                   ,
	                   {
						title: 'Another Tab',
						html: 'b'
						},{
						    title: 'Closable Tab',
						html: 'c',
						    closable:true
						}
						]
	        });

	        // Panel for the west
	        var nav = new Ext.Panel({
	            title: 'Navigation',
	            region: 'west',
	            split: true,
	            width: 200,
	            collapsible: true,
	            margins:'3 0 3 3',
	            cmargins:'3 3 3 3',
	            items:[
						{   text  :'MIF 2.0',
							xtype: 'button',
						    id     : 'shortcut-mif',
						    tooltip : 'Hide/Show active MIF Demos',
						        menu : { 
						          items: [
						             {text     : 'Westside Story', 
						              iconCls  : 'demo-folder' ,
						              tip      : 'Tree drives IFRAME tabs',
						              launch   : Demo.map['mif']['treenav'],
						              handler  : Demo.launch
						              
						              },
						              {text     : 'Submit Form (Targeted PDF)', 
						              iconCls  : 'media-pdf',
						              launch   : Demo.map['mif']['mifsubmit'],
						              handler  : Demo.launch
						              
						              },
						              {text   : 'MIF Simple (Frame Writing)',
						             launch   : Demo.map['mif']['mifsimple'],
						             handler  : Demo.launch
						              },
						              {text   : 'MIF Link Target',
						                 launch   : Demo.map['mif']['target'],
						                 handler  : Demo.launch
						              },
						             '-',
						             {text : 'Documentation' ,
						             iconCls : 'frustrated',
						             href   : 'htt' +'p://uxdocs.theactivegroup.com/index.html?class=Ext.ux.ManagedIFrame.Element',
						             hrefTarget : 'uxdocsmif'
						             
						             } 
						           ]
						         }
					   } 
	                  ]
	        });

	        var win = new Ext.Window({
	            title: 'Layout Window',
	            closable:true,
	            width:600,
	            height:350,
	            //border:false,
	            plain:true,
	            layout: 'border',

	            items: [nav, tabs]
	        });

	        //win.show(this);
		    
		    
		    
		    
		    
		    
             }

    });
    
    

  });
})();