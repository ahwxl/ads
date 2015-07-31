(function(){
    
     var methodRE = /^(function|object)$/i;
     
     Ext.applyIf (Ext, {
        iterate : function(obj, fn, scope){
            if(Ext.isEmpty(obj)){
                return;
            }
            if(Ext.isIterable(obj)){
                Ext.each(obj, fn, scope);
                return;
            }else if(Ext.isObject(obj)){
                for(var prop in obj){
                    if(obj.hasOwnProperty(prop)){
                        if(fn.call(scope || obj, prop, obj[prop], obj) === false){
                            return;
                        };
                    }
                }
            }
        },
        isIterable : function(v){
            //check for array or arguments
            if(Ext.isArray(v) || v.callee){
                return true;
            }
            //check for node list type
            if(/NodeList|HTMLCollection/.test(toString.call(v))){
                return true;
            }
            //NodeList has an item and length property
            //IXMLDOMNodeList has nextNode method, needs to be checked first.
            return ((v.nextNode || v.item) && Ext.isNumber(v.length));
        },
        
        isObject : function(obj){
            return !!obj && Object.prototype.toString.apply(obj) == '[object Object]';
        },
        
        /**
         * Returns true if an object's member reference is a callable function
         * @argument {Object} object
         * @argument {String} member The string name of the referenced object's member to be tested
         * (Member should be limited to those universally implemented as methods) 
         */
        isHostMethod : function(object, member){
            var t = typeof object[member];
            return !!((methodRE.test(t) && object[member]) || t == 'unknown');
        },
        
        isHostObjectProperty : function(object, member) {
            var t = typeof object[member];
            return !!((methodRE.test(t) && object[member]));
        }

     });

     Ext.onReady(function(){
        
        Ext.ns('Ext.capabilities');
        
        /**
         * Check Basic HTML5 Element support for the <audio> tag and/or Audio object.
         */
        if(typeof Ext.capabilities.hasAudio == 'undefined'){
            var aTag = !!document.createElement('audio').canPlayType,
                aAudio = window.Audio ? new Audio('') : {},
                mime,
                chk,
                mimes = {
                        mp3 : 'audio/mpeg', //mp3
                        ogg : 'audio/ogg',  //Ogg Vorbis
                        wav : 'audio/x-wav', //wav 
                        basic : 'audio/basic', //au, snd
                        aif  : 'audio/x-aiff' //aif, aifc, aiff
                    };
                    
            var caps = Ext.capabilities.hasAudio = (aTag || Ext.isHostMethod(aAudio, 'canPlayType') ? 
                { tag : aTag, object : Ext.isHostMethod(aAudio, 'play')} : false);
                
            if(caps && Ext.isHostMethod(aAudio, 'canPlayType')){
               for (chk in mimes){ 
                    caps[chk] = (mime = aAudio.canPlayType(mimes[chk])) != 'no' && (mime != '');
                }
            } 
        }  
     });

    Ext.ns('Ext.ux.Media.plugin');
    
    /**
     * @class Ext.ux.Media.plugin.AudioEvents
     * @extends Ext.ux.Media.Component
     * @version 1.1
     * @author Doug Hendricks. doug[always-At]theactivegroup.com
     * @copyright 2007-2010, Active Group, Inc.  All rights reserved.
     * @donate <a target="tag_donate" href="http://donate.theactivegroup.com"><img border="0" src="http://www.paypal.com/en_US/i/btn/x-click-butcc-donate.gif" border="0" alt="Make a donation to support ongoing development"></a>
     * @license <a href="http://www.gnu.org/licenses/gpl.html">GPL 3.0</a>
     * @constructor
     * @param {Object} config The config object
     * 
     * @description Add simple audio streams to any Observable event on any component.
     */
    Ext.ux.Media.plugin.AudioEvents = Ext.extend(Ext.ux.Media.Component,{
    
       autoEl  : {tag:'div' },
       
       disableCaching : false,
       
       /**
        * @cfg {Boolean} useNative False value disables HTML5 audio support (if available)
        */
       useNative : true,
       
       /**
        * @cfg {Object} audioEvents An object hash mapping URL of audio resources to
        * DOM Ext.Element or Ext.util.Observable events.
        * @example
        * var audio = new Ext.ux.Media.plugin.AudioEvents({
              controls : true,
              height  : 60,
              style  : 'padding-top:12px',
              audioEvents :{
                 beforecollapse : 'demos/media/audio/Utopia Restore Down.WAV',
                 beforeexpand : 'demos/media/audio/Utopia Restore Up.WAV',
                 beforeclose : 'demos/media/audio/Utopia Windows Exit.WAV',
                 tabchange : 'demos/media/audio/Utopia Open.WAV',
                 change : 'demos/media/audio/Utopia Question.WAV',
                 maximize : 'demos/media/audio/Utopia Maximize.WAV',
                 minimize : 'demos/media/audio/Utopia Minimize.WAV',
                 restore : 'demos/media/audio/Utopia Restore Up.WAV',
                 mouseover : 'demos/media/audio/Utopia Question.WAV'
              },
              volume : .7
            });
            new Ext.Panel({
               plugins : [audio],
               items : [....]
            }).show();
        */
       audioEvents : {},
       
       /**
        * @cfg {Float} volume Desired Volume level in the range (0.0 - 1)
        * @default .5
        * 
        */
       volume     : .5,
       
       
       /**
        * Determines whether to render UI controls (if available)
        * @cfg {Boolean} controls True to render visible audio controls.
        * @default false  
        */
       controls : false,
       
       /**
        * @cfg (Object} mediaCfg A uxMedia media configuration object (used as fallback
        * where native HTML5 Audio support is unavailable or disabled by the useNative config
        * option)  Specify any desired configuration to support pre-defined/or custom mediaType 
        * profiles.
        */
       mediaCfg : null,
       
       ptype      : 'audioevents',
       
       /** @private
        * 
        */
       initComponent : function(){
          
          this.useNative = !!(this.useNative && Ext.capabilities.hasAudio && Ext.capabilities.hasAudio.object);
          
          this.useNative || (this.mediaCfg = Ext.apply({
              mediaType : 'WAV',
              start     : false,
              url       : '',
              controls  : !!this.controls
          }, this.mediaCfg || {}));
          
          this.setVolume(this.volume);
          
          //render hidden if not expected to render controls
          this.controls || this.addClass('x-hide-offsets');
          
          Ext.ux.Media.plugin.AudioEvents.superclass.initComponent.apply(this,arguments);
          
          this.addEvents(
          /**
            * Fires immediately preceeding an Audio Event. Returning false within this handler
            * cancels the audio playback.
            * @event beforeaudio
            * @memberOf Ext.ux.Media.plugin.AudioEvents
            * @param {Object} plugin This Media plugin instance.
            * @param {Ext.Component/Ext.Element} target The target Ext.Component or Ext.Element instance.
            * @param {String} eventName The eventName linked to the Audio stream.
           */
           'beforeaudio');
           
       },
       
       /** @private
        * 
        */
       init : function( target ){
            
            if(target.dom || target.ctype){
                var plugin = this;
                Ext.iterate(this.audioEvents || {}, 
                 function(event){
                   /* if the plugin init-target is an Observable, 
                    * assert the eventName, exiting if not defined
                    */
                    if(target.events && !target.events[event]) return;
                    
                    /**
                     * Mixin Audio Management methods to the target
                     */
                    Ext.applyIf(target, {
                       audioPlugin : plugin,
                       audioListeners : {},
                       
                       /**
                        * @method removeAudioListener 
                        * 
                        */
                       removeAudioListener : function(audioEvent){
                          if(audioEvent && this.audioListeners[audioEvent]){ 
                               this.removeListener && 
                                 this.removeListener(audioEvent, this.audioListeners[audioEvent], this);
                               delete this.audioListeners[audioEvent];
                          }
                       },
                       /**
                        * Removes all Audio Listeners from the Element or Component
                        * @method removeAudioListeners
                        */
                       removeAudioListeners : function(){
                          var c = [];
                          Ext.iterate(this.audioListeners, function(audioEvent){c.push(audioEvent)});
                          Ext.iterate(c, this.removeAudioListener, this);
                       },
                       
                       addAudioListener : function(audioEvent){
                           if(this.audioListeners[audioEvent]){
                               this.removeAudioListener(audioEvent);
                           }
                           this.addListener && 
                             this.addListener (audioEvent, 
                               this.audioListeners[audioEvent] = function(){
                               this.audioPlugin.onEvent(this, audioEvent);
                             }, this);
                        
                       } ,

                       enableAudio : function(){
                          this.audioPlugin && this.audioPlugin.enable();
                       },
                       
                       disableAudio : function(){
                          this.audioPlugin && this.audioPlugin.stop();
                          this.audioPlugin && this.audioPlugin.disable();
                       },
                       
                       setVolume : function(volume){
                          this.audioPlugin && this.audioPlugin.setVolume(volume);
                       },
                       
                       stopAudio  : function(){
                          this.audioPlugin && this.audioPlugin.stop();  
                       
                       },
                       
                       pauseAudio  : function(){
                          this.audioPlugin && this.audioPlugin.pause();  
                       
                       },
                       
                       playAudio  : function(){
                          this.audioPlugin && this.audioPlugin.play();
                       }
                       
                    });
                    
                    target.addAudioListener(event);
                    
                },this);
                
                target.on('beforedestroy', function(){
                    if(this.audioPlugin && this.audioPlugin.audioTarget == this){
                        this.stopAudio();
                    }
                }, target);
            }
       },
       
       /**
        * @param {Float} volume The volume range 0-1(louder)
        * @return {Object} this
        */
       setVolume   : function(volume){
            var O, AO = this.audioObject, 
                v = Math.max(Math.min(parseFloat(volume)||0, 1),0);
            this.mediaCfg && (this.mediaCfg.volume = v*100);
            this.volume = v;
            AO && (AO.volume = v);
            if(O = this.getInterface()){
                if(Ext.isHostObjectProperty(O, 'object')){
                    O = O.object; 
                    //IE Player scales -2300 to 0(full volume)
                    ('Volume' in O) && (O.Volume = -parseInt(( 1 - v) * 2300, 10) );
                }
            }
            return this;
       },
       
       /**
        * Stops playback of the current audio stream
        * @return {Object} this
        */
       stop  : function(){
            var O= this.audioObject || this.getInterface();
            if(O){
                O = Ext.isHostObjectProperty(O, 'object') ? O.object : O;
                if(Ext.isHostMethod(O, 'stop')){ O.stop(); }
                else if(Ext.isHostMethod(O, 'Stop')) {O.Stop();}
                else this.pause();
            }
            return this;
       },
       
       /**
        * Starts/resumes playback of the current audio stream
        * @return {Object} this
        */
       play  : function(){
            
            var O = this.audioObject || this.getInterface();
            if(O){
                O = Ext.isHostObjectProperty(O, 'object') ? O.object : O;
                if(Ext.isHostMethod(O, 'play')) O.play();
                else if(Ext.isHostMethod(O, 'Play')) O.Play();
            }
            return this;
       },
       
       /**
        * Pauses playback of the current audio stream
        * @return {Object} this
        */
       pause  : function(){
            var O = this.audioObject || this.getInterface();
            if(O){
                O = Ext.isHostObjectProperty(O, 'object') ? O.object : O;
                if(Ext.isHostMethod(O, 'pause')) O.pause();
                else if(Ext.isHostMethod(O, 'Pause')) O.Pause();
            }
            return this;
       },
       
       destroy : function(){
            this.stop();
            this.clearMedia();
            delete this.audioObject;
       },
       
       /**
        * @private
        */
       onEvent : function(comp, event){
           if(!this.disabled && this.audioEvents && this.audioEvents[event]){
              if(this.fireEvent('beforeaudio',this, comp, event) !== false ){
                  this.audioTarget = comp;
                  var O, url = this.audioEvents[event];
                  
                  this.rendered || this.render(Ext.getBody());
                  
                  if(this.useNative){  //HTML5 Audio support?
                        
                        if(this.lastUrl != url){
                            this.audioObject = new Audio(url);
                            
                            if(this.controls){
                                this.audioObject.id = 'audio-'+this.id;
                                this.audioObject.controls= true;
                                (this.mediaObject = Ext.get(this.mediaObject ?  
                                    this[this.mediaEl].dom.replaceChild(this.audioObject, Ext.getDom(this.audioObject.id)) : 
                                    this[this.mediaEl].dom.appendChild (this.audioObject))).show();
                            }
                            
                        }
                        this.setVolume(this.volume);
                        this.play();
                        
                  } else { 
                       O = this.getInterface();
                       if(O && this.mediaCfg){ 
                            this.mediaCfg.url = url;
                            if(Ext.isHostObjectProperty(O, 'object')){  //IE ActiveX
                                O= O.object;
                                (this.lastUrl != url) && Ext.isHostMethod(O, 'Open') && O.Open(url);
                            }else {  //All Others - just rerender the tag
                                
                                (this.lastUrl != url) && this.refreshMedia();
                                this.setVolume(this.volume);
                            }
                            this.play();
                        }
                  }
                  this.lastUrl = url;
              }
              
           }
       }
    
    });
    
    Ext.preg && Ext.preg('audioevents', Ext.ux.Media.plugin.AudioEvents);

})();