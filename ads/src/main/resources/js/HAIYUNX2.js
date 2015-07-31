(function(e, g, c) {
    c.HAIYUNX = {
        invoke: function(h, k) {
            function f(a, b) {
                a && c.LazyLoad().js(a,
                function(b) {
                    c.rmLazyScript(a);
                    b && b()
                },
                b)
            }
            function  p(el, htmlCode) { 
            	var ua = navigator.userAgent.toLowerCase(); 
            	if (ua.indexOf('msie') >= 0 && ua.indexOf('opera') < 0) { 
            	htmlCode = '<div style="display:none">for IE</div>' + htmlCode; 
            	htmlCode = htmlCode.replace(/<script([^>]*)>/gi, 
            	'<script$1 defer>'); 
            	el.innerHTML = htmlCode; 
            	el.removeChild(el.firstChild); 
            	} else { 
            	var el_next = el.nextSibling; 
            	var el_parent = el.parentNode; 
            	el_parent.removeChild(el); 
            	el.innerHTML = htmlCode; 
            	if (el_next) { 
            	el_parent.insertBefore(el, el_next) 
            	} else { 
            	el_parent.appendChild(el); 
            	} 
            	} 
            	}
            var global_html_pool = []; 
            var global_script_pool = []; 
            var global_script_src_pool = []; 
            var global_lock_pool = []; 
            var innerhtml_lock = null; 
            var document_buffer = ""; 

            function set_innerHTML(obj_id, html, time) { 
            if (innerhtml_lock == null) { 
            innerhtml_lock = obj_id; 
            } 
            else if (typeof(time) == "undefined") { 
            global_lock_pool[obj_id + "_html"] = html; 
            window.setTimeout("set_innerHTML('" + obj_id + "', global_lock_pool['" + obj_id + "_html']);", 10); 
            return; 
            } 
            else if (innerhtml_lock != obj_id) { 
            global_lock_pool[obj_id + "_html"] = html; 
            window.setTimeout("set_innerHTML('" + obj_id + "', global_lock_pool['" + obj_id + "_html'], " + time + ");", 10); 
            return; 
            } 

            function get_script_id() { 
            return "script_" + (new Date()).getTime().toString(36) 
            + Math.floor(Math.random() * 100000000).toString(36); 
            } 

            document_buffer = ""; 

            document.write = function (str) { 
            document_buffer += str; 
            } 
            document.writeln = function (str) { 
            document_buffer += str + "\n"; 
            } 

            global_html_pool = []; 

            var scripts = []; 
            html = html.split(/<\/script>/i); 
            for (var i = 0; i < html.length; i++) { 
            global_html_pool[i] = html[i].replace(/<script[\s\S]*$/ig, ""); 
            scripts[i] = {text: '', src: '' }; 
            scripts[i].text = html[i].substr(global_html_pool[i].length); 
            scripts[i].src = scripts[i].text.substr(0, scripts[i].text.indexOf('>') + 1); 
            scripts[i].src = scripts[i].src.match(/src\s*=\s*(\"([^\"]*)\"|\'([^\']*)\'|([^\s]*)[\s>])/i); 
            if (scripts[i].src) { 
            if (scripts[i].src[2]) { 
            scripts[i].src = scripts[i].src[2]; 
            } 
            else if (scripts[i].src[3]) { 
            scripts[i].src = scripts[i].src[3]; 
            } 
            else if (scripts[i].src[4]) { 
            scripts[i].src = scripts[i].src[4]; 
            } 
            else { 
            scripts[i].src = ""; 
            } 
            scripts[i].text = ""; 
            } 
            else { 
            scripts[i].src = ""; 
            scripts[i].text = scripts[i].text.substr(scripts[i].text.indexOf('>') + 1); 
            scripts[i].text = scripts[i].text.replace(/^\s*<\!--\s*/g, ""); 
            } 
            } 

            var s; 
            if (typeof(time) == "undefined") { 
            s = 0; 
            } 
            else { 
            s = time; 
            } 

            var script, add_script, remove_script; 

            for (var i = 0; i < scripts.length; i++) { 
            var add_html = "document_buffer += global_html_pool[" + i + "];\n"; 
            add_html += "document.getElementById('" + obj_id + "').innerHTML = document_buffer;\n"; 
            script = document.createElement("script"); 
            if (scripts[i].src) { 
            script.src = scripts[i].src; 
            if (typeof(global_script_src_pool[script.src]) == "undefined") { 
            global_script_src_pool[script.src] = true; 
            s += 2000; 
            } 
            else { 
            s += 10; 
            } 
            } 
            else { 
            script.text = scripts[i].text; 
            s += 10; 
            } 
            script.defer = true; 
            script.type = "text/javascript"; 
            script.id = get_script_id(); 
            global_script_pool[script.id] = script; 
            add_script = add_html; 
            add_script += "document.getElementsByTagName('head').item(0)"; 
            add_script += ".appendChild(global_script_pool['" + script.id + "']);\n"; 
            window.setTimeout(add_script, s); 
            remove_script = "document.getElementsByTagName('head').item(0)"; 
            remove_script += ".removeChild(document.getElementById('" + script.id + "'));\n"; 
            remove_script += "delete global_script_pool['" + script.id + "'];\n"; 
            window.setTimeout(remove_script, s + 10000); 
            } 

            var end_script = "if (document_buffer.match(/<\\/script>/i)) {\n"; 
            end_script += "set_innerHTML('" + obj_id + "', document_buffer, " + s + ");\n"; 
            end_script += "}\n"; 
            end_script += "else {\n"; 
            end_script += "document.getElementById('" + obj_id + "').innerHTML = document_buffer;\n"; 
            end_script += "innerhtml_lock = null;\n"; 
            end_script += "}"; 
            window.setTimeout(end_script, s); 
            } 
            (function(a) {
                a = a || {};
                var jsurl = "http://js.haiyunx.com/SSP/"+a.l+".js";
                var b;
                b = g.getElementById(k.target);
                var s = document.createElement('script');
                s.defer = true;
                s.src = jsurl;
                //b.appendChild(s);
                var b;
                b = g.getElementById(k.target);
                //b.appendChild(s);
                b.innerHTML = '<script src="'+jsurl+'" type="text/javascript"></script>';
                set_innerHTML(b,'<script src="'+jsurl+'" type="text/javascript"></script>');
                //g.write('<script src="'+jsurl+'" type="text/javascript"></script>');
                //b.insertAdjacentHTML("afterBegin",'<script src="'+jsurl+'" type="text/javascript"></script>');
                //alert(s.innerHTML);
                //g.write('<script src="'+jsurl+'" type="text/javascript"></script>');
                //f(jsurl)
            })(h)
        }
    }
})(window, document, window.Sellbuyads || {});