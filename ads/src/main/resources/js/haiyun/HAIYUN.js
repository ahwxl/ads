(function(h, d, b) {
    var e = {
        baseUrl: "http://js.haiyunx.com/SSP",
        prefix: "haiyunx"
    };
    b.HAIYUNX = {
        invoke: function(f, g) {
            var c = b.merge(b.merge({},
            e), f),
            a = d.getElementById(g.target);
            a.innerHTML = "";
            a.innerHTML = b.format("<iframe src='about:blank' id='{{prefix}}_{{id}}_if' name='{{prefix}}_{{id}}_if' width='{{width}}' height='{{height}}' border='0' frameborder='0' scrolling='no' marginwidth='0' marginheight='0' vspace='0' hspace='0' allowtransparency='true' style='overflow:hidden'></iframe>", c);
            a = document.getElementById(c.prefix + "_" + c.id + "_if");
            a = a.contentDocument || a.contentWindow.document;
            a.open();
            a.write(b.format('<script>document.write(\'<script type="text/javascript" charset="utf-8" src="{{baseUrl}}/{{id}}.js"></scr\'+\'ipt>\');\x3c/script>', c))
        }
    }
})(window, document, window.Sellbuyads || {});