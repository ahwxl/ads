(function(h, d, b) {
    var e = {
            prefix: "WISEMEDIA2"
    };
    b.WISEMEDIA2 = {
        invoke: function(f, g) {
            var c = b.merge(b.merge({},e), f),
                    a = d.getElementById(g.target);
                    a.innerHTML = "";
                    a.innerHTML = b.format("<iframe src='about:blank' id='{{prefix}}_if' name='{{prefix}}_if' width='{{width}}' height='{{height}}' border='0' frameborder='0' scrolling='no' marginwidth='0' marginheight='0' vspace='0' hspace='0' allowtransparency='true' style='overflow:hidden'></iframe>", c);
                    a = document.getElementById(c.prefix + "_if");
                    a = a.contentDocument || a.contentWindow.document;
                    a.open();
                    a.write(b.format('<script> var _kexchange = _kexchange || [];var _dmid="{{_dmid}}";_kexchange.push(["_setAccount", _dmid]);  document.write(\'<script type="text/javascript" charset="utf-8" src="http://cmarket.kejet.net/exchange2.js?a={{_dmid}}"></scr\'+\'ipt>\');var _kenid = "{{_kenid}}_"+Math.random();_kexchange.push(["_addSlot",_kenid,"{{width}}","{{height}}","2","9"]);   \x3c/script>', c))
             h._huntsCallback = function() {
                b.nextPush(g)
            }
        }
    }
})(window, document, window.jyswads || {});