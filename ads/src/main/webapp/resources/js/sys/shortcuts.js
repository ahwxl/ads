
  Ext.ns('Ext.ux.Shortcuts.actions');

  /**
   * required iconCls list for buttons and menus
   * 
   * shortcut-add, shortcut-edit, shortcut-remove
   * 
   * 
   */
  
  Ext.reg('shortcuts',
	Ext.ux.Shortcuts.Panel = Ext.extend(Ext.Panel, {
	    
        defaultScale    : 'medium',
        defaultType     : 'splitbutton',
        /**
         * @cfg default className to be added to each shortcut
         */
        defaultCls     : null,
        /**
         * @cfg default iconCls to be added to each shortcut
         */
        defaultIconCls     : null,
        defaultIconAlign   : null,
	    enableToggle   : undefined,
        split          : false,
        autoScroll     : true,
        arrowAlign     : 'bottom',
        stateful       : true,
        disableEditing : false,
        
	    initComponent : function(){ 
	        
            this.editingItems = [
              {text:'Edit...', 
                iconCls : 'shortcut-edit', 
                handler: function(menuItem){
                    this.editShortcut(menuItem.parentMenu.targetButton); 
                },
                scope:this
               },
              {text:'Remove', 
               iconCls : 'shortcut-remove', 
               handler: function(menuItem){
                    this.removeShortcut(menuItem.parentMenu.targetButton); 
                }, 
               scope:this
               }
            ];
            
            
            this.defaults = 
              Ext.applyIf( this.defaults || {}, {
                
                enableToggle : this.enableToggle,
                split : this.split,
                arrowAlign : this.arrowAlign,
                scale   : this.defaultScale || 'medium',
                xtype   : this.defaultType || 'splitbutton',
                cls     : this.defaultCls,
                iconCls : this.defaultIconCls,
                iconAlign : this.defaultIconAlign || 'top',
                scope   : this,        
                handler : this.onShortcut,
                style   : "float:left;margin:4px 0 4px 4px;",
                listeners : {
                    afterrender : function(item){ item.menu && (item.menu.targetButton = item); },
                    single : true
                }
               });
            
            var shortcuts = this.items ? [].concat(this.items) : [];
            this.items = null;
            
            Ext.each(shortcuts, this.addShortcut, this);
            
            Ext.applyIf(this, 
             this.disableEditing ? {} :
              {
	           tbar : ['->',{
                text    : 'Add', 
                iconCls : 'shortcut-add', 
                handler: function(){ this.addShortcut(); }, 
                scope   :this
                }]
            });
                
            Ext.ux.Shortcuts.Panel.superclass.initComponent.call(this);
	         
           
            this.addEvents(
             /**
             * Fired when a shortcut is clicked.
             * @event shortcut
             */
             'shortcut',
             
             /**
             * Fired when a shortcut is selected for editing.
             * @event editshortcut
             */
             'editshortcut'
            );
	    },
        /**
         * @private
         */
        onRender : function(){
            Ext.ux.Shortcuts.Panel.superclass.onRender.apply(this, arguments);
            this.mon(this.getEl(),"contextmenu", Ext.emptyFn, null, {preventDefault: true});
        },
        
        /**
         * @private
         * @param {Ext.Button} shortcut
         */
        onShortcut : function(shortcut, e){
            
           this.fireEvent('shortcut', this, shortcut); 
           
        },
        
        /**
         * Add a new shortcut
         * @param {Object} cfg (optional) button config
         */
        addShortcut : function(cfg){
            var S;
            cfg = cfg || {text:'New Shortcut'};
            
            if(!this.disableEditing && !cfg.isStatic){
                cfg.menu || (cfg.menu = {});
                var MI = cfg.menu.items || [];
                var sep = !!MI.length ? ['-'] : [];
                cfg.menu.items = MI.concat(sep.concat(this.editingItems));
                cfg.split = true;
            }
            cfg.xtype = cfg.menu && cfg.menu.items ? 'splitbutton' : 'button';
            
            if(this.items && this.items.items){
                S = this.add(cfg);
                if(S && this.rendered){
	                this.doLayout();
	                S.isStatic || this.disableEditing || this.editShortcut(S, true);
	            }
            } else {
                this.items || (this.items = []);
                this.items.push(cfg)
            }
            
            
        },
        /**
         * @private
         * @param {Ext.menu.Item} menuItem
         * @param {Ext.EventObject} e
         * @param {Ext.Button} shortCut the selected shortcut
         */
        editShortcut : function( shortCut, isNew){
            var ed = Ext.ux.Shortcuts._editor || 
            (Ext.ux.Shortcuts._editor = 
              new Ext.ux.Shortcuts.Editor(
              {renderTo: Ext.getBody()}
                ));
            
            shortCut && ed.editShortCut(shortCut, isNew);
            shortCut && this.fireEvent('editshortcut', this, shortCut, isNew); 
        },
        
        /**
         * 
         * @param {Ext.Button} shortCut the selected shortcut to remove
         * 
        */
        removeShortcut : function(shortCut){
            
            if(shortCut){
                this.remove.defer(20,this,[shortCut, true]);
            }
            
        }
        
	}));
    
    Ext.ux.Shortcuts.store = new Ext.data.ArrayStore({
                    fields: ['action', 'description'],
                    data:[]
                });
   
    Ext.ux.Shortcuts.Editor = Ext.extend(Ext.Window, {
    
        title : 'Editing...',
        iconCls :  'shortcut-edit',
        width : 300,
        height : 150,
        constrain : true,
        modal : true,
        layout : 'fit',
        hidden : true,
        closeAction : 'hide',
        
        initComponent : function(){
            
           Ext.apply(this, {
            items : {
	            xtype : 'form',
	            defaults: {xtype:'textfield'},
	            items:[
                  {
                    fieldLabel :'Shortcut Title',
                    id : 'text'
                  },  
                  {
                    id : 'action',
                    fieldLabel :'Select an Action',
	                xtype : 'combo',
			        store: Ext.ux.Shortcuts.store,
			        displayField :'description',
	                valueField : 'action',
			        typeAhead: true,
			        mode: 'local',
			        triggerAction: 'all',
			        emptyText:'Select an action...',
			        selectOnFocus:true
			        
			    }],        
             buttons: [{
                        text: 'Save',
                        handler : function(button){
                            var S;
                            if(S = this.editor.shortcut){
                                var V= this.editor.getForm().getFieldValues();
                                V.text && S.setText(V.text);
                                S.action = V.action;
                                
                            }
                            this[this.closeAction]();
                        },
                        scope: this
                    },{
                        text: 'Cancel',
                        handler : function(button){
                            this[this.closeAction]();
                            
                        },scope:this
                    }] 
	            }
            });
                
            Ext.ux.Shortcuts.Editor.superclass.initComponent.call(this);
            
            this.on( {
	            destroy : function(){ Ext.ux.Shortcuts._editor = null;},
                beforeshow : this.refreshStore,
                afterrender : this.refreshStore,
                scope : this
	        });
            
            this.editor = this.items.first();
            
        },
        editShortCut : function(shortcut, isAdd){
            var S;
            if(shortcut && !shortcut.isStatic){
       
                this.setTitle('Editing '+ shortcut.text);
                this.editor.shortcut = shortcut;
                this.editor.getForm().setValues({
                    text : shortcut.text,
                    action :  shortcut.action
                });
                this.setIconClass(isAdd?'shortcut-add':'shortcut-edit');
                this.show(shortcut.getEl());
            }
        },
        refreshStore : function(){
            var S = Ext.ux.Shortcuts;
                     
            //refresh the combo store with the latest actions avail
            var data = [];
            Ext.iterate(
                Ext.ux.Shortcuts.actions, 
                function(name, value){
                    value && value.description && data.push([name, value.description]);
             });
            
            S.store && S.store.loadData(data);
        
        }
        
    });
    Ext.provide && Ext.provide('shortcuts');