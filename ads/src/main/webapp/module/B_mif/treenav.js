 /*
  ************************************************************************************
  * Author: Doug Hendricks. doug[at]theactivegroup.com
  * Copyright 2007-2009, Active Group, Inc.  All rights reserved.
  ************************************************************************************

  License: This demonstration is licensed under the terms of
  GNU Open Source GPL 3.0 license:

  Commercial use is prohibited without contacting licensing[at]theactivegroup.com.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   see < http://www.gnu.org/licenses/gpl.html >

   Donations are welcomed: http://donate.theactivegroup.com

  */

(function(){
    //alert("");
    var vizPlugin = Ext.ux.plugin && Ext.ux.plugin.VisibilityMode 
	       ? new Ext.ux.plugin.VisibilityMode({hideMode:'nosize', elements:['bwrap']}) 
	       : {init: Ext.emptyFn};
    
	       
	
	vizPlugin ={init: Ext.emptyFn};
	
	//alert('treenav1');
	
	//alert(Demo.Window);
	
	Demo.MIFTreeNav || (Demo.MIFTreeNav = 
	 Ext.extend( Demo.Window, {
      title       : 'ManagedIFrames: A Westside Story',
      width       : 845,
      height      : 490,
      maximizable : true,
      hideMode    : 'display',
      border      : false,
      collapsible : true,
      animCollapse : false,
      closeAction : 'close',
      defaults      : {
	      split   : true,
	      border  : false,
	      frame   :true,
          cmargins    : '0 1 1 1'
	    },
      
      plugins     : [vizPlugin],
      layout      : 'border',
      
      /**/
      initComponent: function(){
          var me = this;
          this.items = [
             {
               region      : 'west',
               ref         : 'navTree',
               xtype       : 'treepanel',
               title       :'Westside Menu',
               id          : 'westmenu',
               minWidth    : 120,
               width       : 150,
               maxSize     : 300,
               split       : true,
               collapseMode: 'mini',
               collapsible : true,
               enableDD    : false,
               useArrows   : true,
               autoScroll  : true,
               containerScroll: true,
               cmargins    : '0 1 1 1',
               rootVisible : false,
               border      : false,
               root        : {
                   nodeType  : 'async',
                   draggable  : false,
                   expanded   : true,
                    text      : 'sites',
                    leaf      : false,
                    children  : this.staticNodes
                },
               loader : new Ext.tree.TreeLoader({
                      preloadChildren : true,
                      baseAttrs       : {iconCls : 'demo-http', leaf : true, frameTitle : 'Loading...' }
                   }),
               
               //Give the standard Tools qtips
                   /*
               plugins : [
	             {ptype: 'toolstips', 
                     tips:{
	                   expand : 'Restore the menu',
	                   toggle : 'Hide the menu'
	                 }},
                 //{ptype : 'uxvismode', elements : ['bwrap']},
                 Demo.audioPlugin || {}
               ],
               */
               listeners: {
                 beforerender: function(tree){
                    new Ext.tree.TreeSorter(tree);
                    tree.getSelectionModel().on('selectionchange', this.onSelectNode, this);
                 },
                 scope: this
               }
             },
             { xtype     :'tabpanel',
               id        : this.getId() + '-center',
               ref       : 'demoTabs',
               activeTab : 0,
               frame     : true,
               defaults  : {
			         split   : true,
			         border  : false,
			         frame   : true
			         },
               region    : 'center',
               frame   :true,
               //inline JIT request for the 'mif' and 'crc32' packages and dependencies
               require   : ['@mif', 'resources/js/sys/crc32'],  
               layoutOnTabChange : true,
               defaults  : {
                  xtype       : 'iframepanel',
                  hideMode    : 'display',
                  autoScroll  : true,
                  cmargins    : '0 1 1 1',
                  frame       : true,
                  focusOnLoad : true, //Ext.isIE, //some external sites use document.createRange
                  title :'abc',
                  closable    : true,
                  //plugins     : Demo.audioPlugin || {},
                  /*
                   * Give all iframepanels a standard listener config
                   */
                  listeners : {
                      
                     scroll : {   //raised for "same-origin" frames only
                       fn: function(frame){  
                     
                        var MIF = frame.ownerCt;
                        Demo.balloon(null,'Tab: '+MIF.title+' reports: ','buffered-scroll ');
                       },
                       buffer : 100
                     },
                     
                     domready : function(frame){  //raised for "same-origin" frames only
                        
                        if(frame.getDoc()){ 
                           var MIF = frame.ownerCt;
                           Demo.balloon(null,'Tab: '+MIF.title+' reports: ','domready ');
                            
                           /* Since this is likely a same-origin frame, intercept <a> links
                            * and load them as new tabPanels instead (if they have a target attribute)
                            */
                           frame.getDoc().on('click', me.interceptLinks, me, {delegate:'a[target]'});
                           
                        }else{
                            Demo.balloon(null,'Tab: '+MIF.title+' reports: ','reset problem '+frame.isReset);
                        }
                        
                     },
                     
                     documentloaded : function(frame){
                        
                        var MIF = frame.ownerCt,
                            doc = MIF.getFrameDocument(),
                            sameOrigin = frame.domWritable(); 
                        /**
                         * Here, we try to determine the title of the frame's document
                         * and update the tab's title with the value.  If we don't have
                         * privilege to access the document, fall back to the selected
                         * tree node for it's text value.
                         */
                        var title =  sameOrigin ? //is this an accesible same-origin frame?
                            doc.title : 
                            null;
                        var node = MIF.selectedNode;
                        if(node){
	                        title || (title = node.attributes.text); //default to the Tree definition
	                        node.getUI().afterLoad();
                        }
                        title && MIF.setTitle(title, sameOrigin ? 'frame-open':'frame-locked');
                        Demo.balloon(null,'Tab: "'+MIF.title+'" reports:','documentloaded ');
                                                
                     },
                     
                     scope : this
                  }
               }
               
             }];
            Demo.MIFTreeNav.superclass.initComponent.apply(this, arguments);
        }
        ,
        /**/
        staticNodes  : [
            {text: 'Remote Sites', leaf : false, expanded : true,
                iconCls : 'demo-folder',
                children: [
                { text:'Google Code: MIF', tabRef: 'gcode',  url : 'http://code.google.com/p/managediframe/'},
                { text:'Google', tabRef: 'google' ,  url : 'http://www.google.com/'},
                { text:'Google Search: MIF', tabRef: 'mifsrch',  url : 'http://www.google.com/search?hl=en&q=%22ManagedIframe%22'},
                { text:'Yahoo', tabRef: 'yahoo', url : 'http://www.yahoo.com/'},
                { text:'Ajaxian', tabRef: 'ajaxian', url : 'http://www.ajaxian.com/'},
                { text:'Sencha.com', tabRef: 'sencha', url : 'http://www.sencha.com/'},
                { text:'Sencha Forums', tabRef: 'senchaforum', url : 'http://sencha.com/forum/search.php?do=getnew'},
                { text:'abc', tabRef: 'abc', url : 'http://localhost:9292/travel/getInfo?searchString=&pageSize=5&page=1'}
                ]
             },
            { text     : 'Local Sites', 
              leaf     : false,
              iconCls  : 'demo-folder',
              expanded : true,
              children : [
              { text:'Link Interception', tabRef: 'links', url : 'demos/mif/pagelinks.htm'},
              { text:'Local Feed Viewer', tabRef: 'feedview', url : 'lib/ext-3.0+/examples/feed-viewer/view.html'},
              { text:'Ext Feed Viewer', tabRef: 'efeedview', url : 'http://sencha.com/deploy/dev/examples/feed-viewer/view.html'},
              { text:'Frame Writing', tabRef: 'framewriting', html : {tag:'b', html:'This is a test'} },
              { text:'Frame Template', 
                tabRef: 'frametpl', 
                data : ['zack_hat.jpg', 
                        'sara_smile.jpg',
                        'up_to_something.jpg'
                       ],
                tpl : [
                  '<html><head>',
                  '<style>',
                  '',
                  '</style>',
                  '</head><body>',
                  '<tpl for=".">',
                    '<p><img src="http://demos.theactivegroup.com/lib/ext-3.3+/examples/image-organizer/images/thumbs/{.}" /></p>',
                  '</tpl>',
                  '</body></html>'
                 ]
               }
              
              ]
            }
         ],
         
         /*
          * Dynamically adds a ManagedIFramePanel tab, when the treeNode is selected
          */
         onSelectNode : function(sm, node){
            if(!node || !node.leaf)return false;
            var NA    = node.attributes,
                URL   = NA.url,
                tabId = 'frame-'+ NA.tabRef,
                title = (URL ? NA.frameTitle : null)|| NA.text,
                tabs  = this.demoTabs,  //using the 'ref' config defined above
                tab   = tabs.getItem(tabId);
                tab || (tab = tabs.add({
                    id          : tabId,
                    frameConfig : {id : 'fr-'+NA.tabRef},
                    title       : title,
                    defaultSrc  : URL,
                    html        : NA.html,
                    data        : NA.data,
                    tpl         : NA.tpl,
                    iconCls     : URL ? 'loading-indicator' : null,
                    loadMask    : URL ? {
                        msg: 'Loading: ' + NA.text
                        } : false,
                    listeners : NA.listeners,
                    //pass along reference to the treeNode (for tab title updates later)
                    selectedNode: node
                    
                }));
                
            if(!tab.rendered){
                URL && node.getUI().beforeLoad(); //set loading indicator if its a full page
                tabs.doLayout();
            }
            node.ownerTree.getSelectionModel().clearSelections();
            tabs.setActiveTab(tab);
         },
         
         /**
          * Filter 'click' events in "accessible" child documents and direct link
          * actions to new tabs.
          * @param {Ext.EventObjectImpl} e
          * @param {HTMLElement} target
          */
         interceptLinks : function(e,targetEl){
             
            var tabs = this.demoTabs;  //using the 'ref' config defined above
		        if( targetEl && tabs && e.button == 0){ 
              var title = targetEl.getAttribute('title'), 
                  href = targetEl.getAttribute('href'),
                  //generate a unique Comp Id based on href or title
		          idKey = 'dynaTab-' + ((title || href).trim().crc32() || Ext.id());
                  
		          e.stopEvent();    //prevent the default action of the link
		      
              tabs.setActiveTab( 
                tabs.getItem(idKey) || tabs.add({
		              xtype      : 'iframepanel', 
                  iconCls    : 'loading-indicator',
                  id         : idKey,
		              defaultSrc : href,
		              title      : title || 'Unknown Document' ,
		              loadMask   : {msg : 'Loading Intercepted Link...'}
		            })
            );
		     }
         },
         sourceModule : Demo.resolveURL( 'mif', 'treenav.js')
      })
	  );
	  
	
	  //alert("treenav is ok");
	  $JIT.provide('treenav');  //logical module registration
	  
	  //alert(Demo.MIFTreeNav);
	  //(new Demo.MIFTreeNav()).show();
	  //var mifw = new Demo.MIFTreeNav();
	  //alert(mifw);
  
  })();

