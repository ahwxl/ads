

    /**
     * @class Ext.ux.IntelliMask
     * @version 1.0.2
     * @author Doug Hendricks. doug[always-At]theactivegroup.com
     * @copyright 2007-2010, Active Group, Inc.  All rights reserved.
     * @donate <a target="tag_donate" href="http://donate.theactivegroup.com"><img border="0" src="http://www.paypal.com/en_US/i/btn/x-click-butcc-donate.gif" border="0" alt="Make a donation to support ongoing development"></a>
     * @constructor
     * Create a new LoadMask
     * @desc A custom utility class for generically masking elements while providing delayed function
     * execution during masking operations.
     */
    Ext.ux.IntelliMask = function(el, config){

        Ext.apply(this, config || {msg : this.msg});
        this.el = Ext.get(el);

    };

    Ext.ux.IntelliMask.prototype = {

        /**
         * @cfg {String} msg The default text to display in a centered loading message box
         * @default 'Loading Media...'
         */
        msg : 'Loading...',
        /**
         * @cfg {String} msgCls
         * The CSS class to apply to the loading message element.
         * @default "x-mask-loading"
         */
        msgCls : 'x-mask-loading',


        /** @cfg {Number} zIndex The optional zIndex applied to the masking Elements
         */
        zIndex : null,

        /**
         * Read-only. True if the mask is currently disabled so that it will not be displayed (defaults to false)
         * @property {Boolean} disabled
         * @type Boolean
         */
        disabled: false,

        /**
         * Read-only. True if the mask is currently applied to the element.
         * @property {Boolean} active
         * @type Boolean
         */
        active: false,

        /**
         * @cfg {Boolean/Integer} autoHide  True or millisecond value hides the mask if the {link #hide} method is not called within the specified time limit.
         */
        autoHide: false,

        /**
         * Disables the mask to prevent it from being displayed
         */
        disable : function(){
           this.disabled = true;
        },

        /**
         * Enables the mask so that it can be displayed
         */
        enable : function(){
            this.disabled = false;
        },

        /**
         * Show this Mask over the configured Element.
         * @param {String/ConfigObject} msg The message text do display during the masking operation
         * @param {String} msgCls The CSS rule applied to the message during the masking operation.
         * @param {Function} fn The callback function to be invoked after the mask is displayed.
         * @param {Integer} fnDelay The number of milleseconds to wait before invoking the callback function
         * @return {Object} A hash containing Element references to the mask container element and 
         *   the element containing the message text.
         *   { mask : Element,
         *     msgEl : Element
         *   }
         * @example
           mask.show({autoHide:3000});   //show defaults and hide after 3 seconds.
         * @example
           mask.show('Loading Content', null, loadContentFn); //show msg and execute fn
         * @example
           mask.show({
               msg: 'Loading Content',
               msgCls : 'x-media-loading',
               fn : loadContentFn,
               fnDelay : 100,
               scope : window,
               autoHide : 2000   //remove the mask after two seconds.
           });
         */
        show: function(msg, msgCls, fn, fnDelay ){

            var opt={}, autoHide = this.autoHide, mask, maskMsg;
            fnDelay = parseInt(fnDelay,10) || 20; //ms delay to allow mask to quiesce if fn specified

            if(Ext.isObject(msg)){
                opt = msg;
                msg = opt.msg;
                msgCls = opt.msgCls;
                fn = opt.fn;
                autoHide = typeof opt.autoHide != 'undefined' ?  opt.autoHide : autoHide;
                fnDelay = opt.fnDelay || fnDelay ;
            }
            if(!this.active && !this.disabled && this.el){
                msg = msg || this.msg;
                msgCls = msgCls || this.msgCls;
                mask = this.el.mask(msg, msgCls);
                
                if(this.active = !!mask){
                    maskMsg = this.el.child('.'+ msgCls);
                    if(this.zIndex){
                        mask.setStyle("z-index", this.zIndex);
                        if(maskMsg){
                            maskMsg.setStyle("z-index", this.zIndex+1);
                        }
                    }
                }
            } else {fnDelay = 0;}

            //passed function is called regardless of the mask state.
            if(typeof fn === 'function'){
                fn.defer(fnDelay ,opt.scope || null);
            } else { fnDelay = 0; }

            if(autoHide && (autoHide = parseInt(autoHide , 10)||2000)){
                this.hide.defer(autoHide+(fnDelay ||0),this );
            }

            return this.active? {mask: mask , msgEl: maskMsg } : null;
        },

        /**
         * Hide this Mask.
         * @param {Boolean} remove  True to remove the mask element from the DOM after hide.
         */
        hide: function(){
            this.el && this.el.unmask();
            this.active = false;
            return this;
        },

        // private
        destroy : function(){this.hide(); this.el = null; }
     };

     if (Ext.provide) { Ext.provide('uxmask');}