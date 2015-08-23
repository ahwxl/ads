(function(g, c, b) {
    b.MEDIAV = {
        invoke: function(e, f) {
            var d = b.merge({
                baseUrl: "https://static-ssl.mediav.com/js/mvf_g2.js",
                ref: c.referrer
            },
            e),
            a = c.getElementById(f.target);
            a.innerHTML = "";
            a.innerHTML = b.format("<iframe src='about:blank' id='{{pub}}_if' name='{{pub}}_if' width='{{width}}' height='{{height}}' border='0' frameborder='0' scrolling='no' marginwidth='0' marginheight='0' vspace='0' hspace='0' allowtransparency='true' style='overflow:hidden'></iframe>", d);
            a = c.getElementById(d.pub + "_if");
            a = a.contentDocument || a.contentWindow.document;
            a.open();
            a.write(b.format("<script>var mediav_ad_ref ='{{ref}}';var mediav_ad_pub ='{{pub}}';var mediav_ad_width ='{{width}}';var mediav_ad_height ='{{height}}';document.write('<script type=\"text/javascript\" charset=\"utf-8\" src=\"{{baseUrl}}\"></scr'+'ipt>');\x3c/script>", d))
        }
    }
})(window, document, window.Sellbuyads || {});