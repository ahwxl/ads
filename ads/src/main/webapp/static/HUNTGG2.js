(function(a, e, b) {
    b.LazyLoad().js("http://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js",
    function() {
        b.rmLazyScript("http://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js")
    });
    b.HUNTGG2 = {
        invoke: function(c, d) {
        	var idiv = e.getElementById(d.target);
        	idiv.innerHTML = "";
        	idiv.innerHTML = b.format("<ins class='adsbygoogle' style='display:inline-block;width:{{width}}px;height:{{height}}px' data-ad-client='{{adclient}}' data-ad-slot='{{slot}}'></ins>", c);
            (adsbygoogle = a.adsbygoogle || []).push({});
        }
    }
})(window, document, window.jyswads || {});