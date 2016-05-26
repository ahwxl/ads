(function(h, d, b) {
    var e = {
            baseUrl: "http://cmarket.kejet.net/exchange2.js?a=RDFDNzM4OTlDQzU5NEJB",
            prefix: "haiyunx"
        };
    b.WISEMEDIA2 = {
        invoke: function(f, g) {
        	//alert(c.height);
            //a._kexchange  = a._kexchange  || [];
            //var _kenid = 'F2FOC3YQDHUXVZEDNZ8Z_'+Math.random();
            //a._kexchange.push(['_addSlot',_kenid,'300','250','2','9']);
            
            var c = b.merge(b.merge({},e), f),
                    a = d.getElementById(g.target);
            //alert(a.id);
                    a.innerHTML = "";
                    a.innerHTML = b.format("<iframe src='about:blank' id='{{prefix}}_{{l}}_if' name='{{prefix}}_{{l}}_if' width='{{width}}' height='{{height}}' border='0' frameborder='0' scrolling='no' marginwidth='0' marginheight='0' vspace='0' hspace='0' allowtransparency='true' style='overflow:hidden'></iframe>", c);
                    a = document.getElementById(c.prefix + "_" + c.l + "_if");
                    a = a.contentDocument || a.contentWindow.document;
                    a.open();
                    a.write(b.format('var _kexchange = _kexchange || [];_dmid="RDFDNzM4OTlDQzU5NEJB"_kexchange.push(["_setAccount", _dmid]);  <script>document.write(\'<script type="text/javascript" charset="utf-8" src="{{baseUrl}}"></scr\'+\'ipt>\'); _kenid = "F2FOC3YQDHUXVZEDNZ8Z_"+Math.random();_kexchange.push(["_addSlot",_kenid,"300","250","2","9"]);   \x3c/script>', c))
        
        }
    }
})(window, document, window.jyswads || {});