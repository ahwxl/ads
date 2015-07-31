
 /*
  ************************************************************************************
  * Author: Doug Hendricks. doug[always-At]theactivegroup.com
  * Copyright 2007-2009, Active Group, Inc.  All rights reserved.
  ************************************************************************************
  */

Ext.ns('Demo');

/*
 * The default Demo.Window class.  Designed to be extended for various demos
 */

Demo.Window = Ext.extend(Ext.Window,{
  height        : 500,
  width         : 900,
  constrain     : true,
  monitorResize : true,
  
  layout        :'border',
  iconCls       :'demo',
  bufferResize  : 20,
  bodyBorder    : false,
  border        : false,
  hideBorders   : true,
  closeAction   : 'hide',  
  hideMode      : 'nosize',
  animCollapse  : false,
  defaults      : {
      split   : true,
      border  : false,
      frame   :true
  },
  initComponent : function(){
    
      
      this.items || (this.items=[
         { region       : 'center'
          ,id           : this.id + '-center'
          ,xtype        : 'tabpanel'
          ,activeItem    : 0
          ,deferredRender : false
          ,items        : {title:'tab1',html:"<b>Test</b>"}
         },
         {
          region    : 'west',
          title     : 'Variations',
          id        : this.id + '-west',
          width     : 100,
          split   :true,
          html      : "<b>Test</b>"
          },
          {
            region  : 'east',
            id      : this.id + '-east',
            width   : 100,
            split   :true,
            collapsed : true,
            title   : 'Notes',
            html    : "<b>Test</b>"
           }
        ]);
        
       this.plugins = [
           new Ext.ux.ToolsTips({
            tips:{
              toggle: 'Expand/Collapse this window'
              }
           }),
           Demo.audioPlugin || {}
           ].concat(this.plugins || []); 
        
        //Add Tab Scroller and Closemenu plugins for tabPanels
        Ext.each( [].concat(this.items) , 
           function(comp){
	        comp.xtype == 'tabpanel' && 
              Ext.apply(comp,{
	             plugins : [new Ext.ux.TabCloseMenu()].concat(comp.plugins || []),
	             enableTabScroll:true,
	             minTabWidth: 115
	          });
         });
        Demo.Window.superclass.initComponent.call(this);
        
        Demo.register(this, true); 

    },
    getWindowRegion : function(which){
        return this.getComponent(this.id+'-'+which);
    },

    show : function(){
        Demo.Window.superclass.show.apply(this, arguments);
    },

    onDestroy : function(){
        Demo.deRegister(this);
        Demo.Window.superclass.onDestroy.call(this);

    }
       
    
});

$JIT.provide('demowin');  //logical module registration


