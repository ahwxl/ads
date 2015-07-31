(function(e, g, c) {
    c.MEDIAV = {
        invoke: function(h, k) {
            function f(a, b) {
                a && c.LazyLoad().js(a,
                function(b) {
                    c.rmLazyScript(a);
                    b && b()
                },
                b)
            }
            (function(a) {
                a = a || {};
                e.mediav_ad_pub = 'DUUsCx_1035888';
                e['mediav_ad_pub'] = 'DUUsCx_1035888';
                e['mediav_ad_width'] = '300';
                e['mediav_ad_height'] = '250';
                var jsurl = "http://static.mediav.com/js/mvf_g2.js";
                
                var a = g.getElementById(k.target);
                a.innerHTML = "";
                a.style.position = "relative";
                a.style.display = "inline";
                a.style.border = "none";
                a.style.padding = "0px";
                a.style.margin = "0px";
                a.style.visibility = "visible";
                a.style.overflow = "hidden";
                var c = g.createElement("script");
                c.async = !1;
                c.src = "http://static.mediav.com/js/mvf_g2.js";
                //c.id = b.format("allyes_mm_ad_{{allyes_siteid}}_{{allyes_channedid}}_{{allyes_adspaceid}}", h);
                a.appendChild(c);
                
                //var b;
                //b = g.getElementById(k.target);
                //var s = document.createElement('script');
                //s.defer = true;
                //s.src = jsurl;
                //b.appendChild(s);
                //var b;
                //b = g.getElementById(k.target);
                //b.appendChild(s);
                //b.innerHTML = '<script src="'+jsurl+'" type="text/javascript"></script>';
                //g.write('<script src="'+jsurl+'" type="text/javascript"></script>');
                //b.insertAdjacentHTML("afterBegin",'<script src="'+jsurl+'" type="text/javascript"></script>');
                //alert(s.innerHTML);
                //g.write('<script src="'+jsurl+'" type="text/javascript"></script>');
                //f(jsurl);
            })(h)
        }
    }
})(window, document, window.Sellbuyads || {});