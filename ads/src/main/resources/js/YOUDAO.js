(function(e, g, c) {
    c.YOUDAO = {
        invoke: function(h, k) {
            function f(a, b) {
                a && c.LazyLoad().js(a,
                function(b) {
                    c.rmLazyScript(a);
                    b && b()
                },
                b)
            }
            function m(a, b, l) {
                var d = c.randomFunName("_jsonpCallBack");
                b.callback = d;
                for (p in b) b.hasOwnProperty(p) && (a = c.appendUrl(a, p, b[p]));
                e[d] = function() {
                    l.apply(e, Array.prototype.slice.call(arguments));
                    c.rmCall(d)
                };
                f(a)
            }
            function d(a, b) {
                var c = e[a];
                if ("function" === typeof c) return c(b);
                e.setTimeout(function() {
                    d(a, b)
                },
                50)
            }
            function n(a) {
                function b() {
                    c.length ? f(c.pop(), b) : d(a.callback, a.config)
                }
                var c;
                if (!a.outerJs) return d(a.callback, a.config);
                c = a.outerJs.split(",");
                f(c.pop(), b)
            }
            e.innerAd = function(a) {
                var b;
                b = g.getElementById(k.target);
                a = c.format('<inc><iframe style="width:{{width}}px;height:{{height}}px;" scrolling="no" frameborder="0" src="{{adSrc}}" allowtransparency></iframe></inc>', a);
                b.innerHTML = a
            }; (function(a) {
                a = a || {};
                a.startTime = (new Date).getTime();
                a.req = c.getRequest();
                a.encode = c.getEncode();
                var b = c.getReferrer();
                b && (a.ref2 = b);
                m("http://impservice.union.youdao.com/imp/slot.s", a, n)
            })(h)
        }
    }
})(window, document, window.Sellbuyads || {});