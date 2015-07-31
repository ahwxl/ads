var js_version = 1.1; (function(j, p, m) {
    var l, i = "http://js.miaozhen.com/mz_ad_render_",
    g = "mzadx",
    k = "__mz_render_data_queue",
    e = "__mz_rpq";
    j.__MZ_SERVERS || (j.__MZ_SERVERS = {
        MZHKY: {
            url: "http://a.tpg.cn.miaozhen.com/a.gif",
            callback: "window.__mz_collect_hky",
            params: {}
        },
        MZHKY3: {
            url: "http://a.hpg.cn.miaozhen.com/a.gif",
            callback: "window.__mz_collect_hky3",
            params: {}
        },
        MZTAC: {
            url: "http://a.tac.miaozhen.com/a.gif",
            callback: "window.__mz_collect_tac",
            params: {}
        },
        MZADX: {
            url: "http://s.x.cn.miaozhen.com/ax",
            callback: "window.__mz_collect_adx",
            params: {}
        }
    });
    j.__mz_collect_hky = function(q) {
        j.__mz_materials_collect(q, "MZHKY")
    };
    j.__mz_collect_hky3 = function(q) {
        j.__mz_materials_collect(q, "MZHKY3")
    };
    j.__mz_collect_tac = function(q) {
        j.__mz_materials_collect(q, "MZTAC")
    };
    j.__mz_collect_adx = function(q) {
        a(q, "MZADX");
        j.__mz_iframe_callback && j.__mz_iframe_callback(q);
        j.__mz_materials_collect(q, "MZADX")
    };
    j.__mz_materials_collect = function(z, s, w) {
        var x = {},
        y, q, v, r = z.constructor;
        if (r == Array) {
            for (var t = 0,
            u = z.length; t < u; t++) {
                v = z[t];
                v.server = s;
                v.nodeId = j.__MZ_SERVERS[s].params[v.pid].nodeId;
                v.style = j.__MZ_SERVERS[s].params[v.pid].style;
                if (j.__MZ_SERVERS[s].params[v.pid].click_google) {
                    v.cm.push(decodeURIComponent(j.__MZ_SERVERS[s].params[v.pid].click_google))
                }
                if (j.__MZ_SERVERS[s].params[v.pid].click_asci) {
                    v.cm.push(decodeURIComponent(j.__MZ_SERVERS[s].params[v.pid].click_asci))
                }
                if (!j.__MZ_SERVERS[s].params[v.pid].renderstate) {
                    j.__MZ_SERVERS[s].params[v.pid].renderstate = true;
                    if (!w) {
                        clearTimeout(j.__MZ_SERVERS[s].params[v.pid].timeout)
                    }
                } else {
                    if (!j.__MZ_SERVERS[s].params[v.pid].rendercover) {
                        continue
                    }
                }
                y = v.etype;
                q = x[y];
                if (!q) {
                    q = x[y] = []
                }
                q.push(v)
            }
        } else {
            v = z;
            v.server = s;
            v.nodeId = j.__MZ_SERVERS[s].params[v.pid].nodeId;
            v.style = j.__MZ_SERVERS[s].params[v.pid].style;
            if (j.__MZ_SERVERS[s].params[v.pid].click_google) {
                v.cm.push(decodeURIComponent(j.__MZ_SERVERS[s].params[v.pid].click_google))
            }
            if (j.__MZ_SERVERS[s].params[v.pid].click_asci) {
                v.cm.push(decodeURIComponent(j.__MZ_SERVERS[s].params[v.pid].click_asci))
            }
            if (!j.__MZ_SERVERS[s].params[v.pid].renderstate) {
                j.__MZ_SERVERS[s].params[v.pid].renderstate = true;
                if (!w) {
                    clearTimeout(j.__MZ_SERVERS[s].params[v.pid].timeout)
                }
            } else {
                if (!j.__MZ_SERVERS[s].params[v.pid].rendercover) {
                    return
                }
            }
            y = v.etype;
            q = x[y] = [];
            q.push(v)
        }
        d(x, "N");
        for (y in x) {
            f(y);
            d(x, y)
        }
    };
    function a(s, v) {
        var u = s.constructor;
        if (u == Array) {
            for (var t = 0; t < u.length; t++) {
                var r = s[t].succ,
                q = (s[t].src != "");
                var w = j.__MZ_SERVERS[v].params[s[t].pid].callback;
                w && w(r, q)
            }
        } else {
            var r = s.succ,
            q = (s.src != "");
            var w = j.__MZ_SERVERS[v].params[s.pid].callback;
            w && w(r, q)
        }
    }
    function h(r, u) {
        var q, t = r.constructor;
        if (t == Array) {
            for (var s = 0,
            q = r.length; s < q; s++) {
                if (!j.__MZ_SERVERS[u].params[r[s].pid].renderstate) {
                    j.__mz_materials_collect(r[s], u, false)
                }
            }
        } else {
            if (!j.__MZ_SERVERS[u].params[r.pid].renderstate) {
                j.__mz_materials_collect(r, u, false)
            }
        }
    }
    function d(s, r) {
        var q = s[r];
        if (q) {
            j[k + r].push(q);
            delete s[r];
            q = null
        }
    }
    function c(s) {
        var u = s._srv,
        q = s._material,
        t = s._timeout,
        r = s._baseurl;
        if (u) {
            if (r) {
                b(r + n(s))
            } else {
                b(j.__MZ_SERVERS[u]["url"] + n(s))
            }
            if (q) {
                if (!t) {
                    t = 5000
                }
                j.__MZ_SERVERS[u].params[q.pid].timeout = setTimeout(function() {
                    h(q, u)
                },
                t)
            }
        }
    }
    function n(z) {
        var C = "",
        s, B, x = "",
        A = "",
        r;
        if (z && (s = z.l)) {
            r = z._srv;
            if (s.constructor == Array) {
                var y = z._id;
                for (var u = 0,
                v = s.length; u < v; u++) {
                    var q = s[u];
                    var t = {};
                    t.nodeId = (y ? y[u] : r + "_" + q);
                    t.renderstate = false;
                    if (!z._rc) {
                        t.rendercover = false
                    } else {
                        t.rendercover = true
                    }
                    if (z._callback) {
                        t.callback = z._callback
                    }
                    if (z._style) {
                        t.style = z._style
                    }
                    if (z._click_google && (z._click_google.indexOf("http") != -1)) {
                        t.click_google = z._click_google
                    }
                    if (z._click_asci && (z._click_asci.indexOf("http") != -1)) {
                        t.click_asci = z._click_asci
                    }
                    j.__MZ_SERVERS[r].params[q] = t
                }
            } else {
                var t = {};
                t.nodeId = (z._id ? z._id: r + "_" + s);
                t.renderstate = false;
                if (!z._rc) {
                    t.rendercover = false
                } else {
                    t.rendercover = true
                }
                if (z._callback) {
                    t.callback = z._callback
                }
                if (z._style) {
                    t.style = z._style
                }
                if (z._click_google && (z._click_google.indexOf("http") != -1)) {
                    t.click_google = z._click_google
                }
                if (z._click_asci && (z._click_asci.indexOf("http") != -1)) {
                    t.click_asci = z._click_asci
                }
                j.__MZ_SERVERS[r].params[s] = t
            }
            try {
                if (z.u) {
                    x = z.u
                } else {
                    x = encodeURIComponent(j.top.document.location.href)
                }
                if (z.f) {
                    A = z.f
                } else {
                    A = encodeURIComponent(j.top.document.referrer)
                }
            } catch(w) {
                x = encodeURIComponent(p.referrer)
            }
            delete z._srv;
            delete z._id;
            delete z.u;
            delete z.f;
            delete z._timeout;
            delete z._rc;
            delete z._baseurl;
            delete z._callback;
            delete z._material;
            delete z._style;
            delete z._click_google;
            delete z._click_asci;
            for (B in z) {
                C += "&" + B + "=" + encodeURIComponent(z[B])
            }
            C += "&c=" + j.__MZ_SERVERS[r]["callback"] + "&v=2";
            if (!z.r) {
                C += "&r=1"
            }
            C += "&f=" + A + "&u=" + x + "&mv=j" + m
        }
        return "?" + C.substr(1)
    }
    function f(s) { ! j[k + s] && (j[k + s] = []);
        if (!j[g + s] && s !== "N") {
            var r = parseInt((new Date()).getTime() / 1000);
            var q = r - r % 300;
            b(i + s + ".js?v=" + q)
        }
    }
    function b(s) {
        var r = p.createElement("script"),
        q = p.getElementsByTagName("script")[0];
        r.charset = "utf-8";
        r.type = "text/javascript";
        r.async = true;
        r.src = s;
        q.parentNode.insertBefore(r, q)
    }
    function o() {
        this.push = function(q) {
            q.constructor == Object && c(q)
        }
    }
    f("N"); ! j[e] && (j[e] = []);
    if (j[e].constructor == Array) {
        l = j[e].length;
        while (l--) {
            c(j[e].shift())
        }
        j[e] = new o()
    }
})(window, document, js_version);
var mzadxN = (function(o, g, v, d) {
    var f = false,
    n = g.onload,
    a = {},
    p = "__mz_render_data_queue",
    s = "__mz_ad_rsq",
    q = "http://js.miaozhen.com/mzad_rp.swf";
    var c = false;
    try {
        var i = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
        if (i) {
            c = true
        }
    } catch(r) {
        if (navigator.mimeTypes && navigator.mimeTypes["application/x-shockwave-flash"] != undefined && navigator.mimeTypes["application/x-shockwave-flash"].enabledPlugin) {
            c = true
        }
    }
    o.render = function(w) {
        var x = 0,
        y, e;
        if (!w) {
            return
        }
        y = w.constructor;
        if (y == Array) {
            for (e = w.length; x < e;) {
                o.render(w[x++])
            }
        } else {
            if (y == Object) {
                k(w)
            }
        }
    };
    g.onload = function() {
        f = true;
        g.onload = n;
        n && n.call(g)
    };
    g.__get_params = function(e) {
        return a[e]
    };
    function h(w, z, A) {
        var y = 0,
        x, e;
        for (e = w.length; y < e;) {
            x = new Image();
            x.onload = x.onerror = function() {};
            x.src = t(w[y++])
        }
    }
    function t(w) {
        if (w.indexOf(".miaozhen.com") < 0) {
            return w
        }
        if (w.indexOf("?") < 0) {
            w += "?"
        }
        var e = w.slice( - 1);
        if (! (e == "?" || e == "&")) {
            w += "&"
        }
        w += "mv=j" + d;
        return w
    }
    function u(H, K) {
        var G = H.adw,
        L = H.adh,
        D = H.src,
        E = H.src_backup,
        F = H.ldp,
        N = H.pm,
        A = H.cm,
        y = H.type,
        M = H.style,
        J = H.server,
        T = ps = "",
        C, P, x, O = false,
        Q = H.pid,
        S = J + "_OBJ_" + Q,
        z = J + "_MASK_" + Q,
        e = '<div id="' + z + '" style="position:absolute;width:' + G + "px;height:" + L + 'px;cursor:pointer;background-color:rgb(255,255,255);opacity:0;filter:alpha(opacity=0);"></div>',
        B = l(D, ps, G, L, S, false);
        switch (y) {
        case "I":
            T += e + '<img height="' + L + '" width="' + G + '" border="0" src="' + D + '" style="width:' + G + "px;height:" + L + "px;line-height:" + L + 'px" />';
            break;
        case "F":
            if (!c && E != undefined) {
                T += e + '<img height="' + L + '" width="' + G + '" border="0" src="' + E + '" style="width:' + G + "px;height:" + L + "px;line-height:" + L + 'px" />';
                y = "I"
            } else {
                T += e + B
            }
            break;
        case "X":
            H.width = G;
            H.height = L;
            H.hc = 0;
            delete H.pm;
            a[Q] = H;
            ps = "pid=" + Q;
            T = l(q, ps, G, L, S, false);
            break;
        case "V":
            H.width = G;
            H.height = L;
            H.hc = 0;
            delete H.pm;
            a[Q] = H;
            ps = "pid=" + Q;
            T = l(q, ps, G, L, S, true);
            break;
        default:
            break
        }
        if (M) {
            var V = {};
            V.material = H;
            V.contentHTML = T;
            j(V)
        } else {
            K.style.width = G + "px";
            K.style.height = L + "px";
            K.innerHTML = T
        }
        if (y == "I" || y == "F") {
            if (N.constructor == Object) {
                var U = N["0"];
                if (U && U.constructor == Array) {
                    h(U, "imp", J);
                    delete N["0"]
                }
                for (var R in N) {
                    var I = N[R];
                    if (I.constructor == Array) { (function(w, W) {
                            g.setTimeout(function() {
                                h(w, "imp", J)
                            },
                            parseInt(W) * 1000)
                        })(I, R)
                    }
                }
            }
            x = v.getElementById(z);
            if (F) {
                C = function() {
                    g.open(F, "_blank")
                };
                O = true
            }
            if (A.constructor == Array) {
                P = function() {
                    h(A, "clk", J)
                };
                O = true
            }
            if (O) {
                x && (x.onclick = function() {
                    P && P();
                    C && C()
                })
            }
        }
        if (y == "X" || y == "V") {
            if (N.constructor == Object) {
                var U = N["0"];
                if (U && U.constructor == Array) {
                    h(U, "imp", J);
                    delete N["0"]
                }
                for (var R in N) {
                    var I = N[R];
                    if (I.constructor == Array) { (function(w, W) {
                            g.setTimeout(function() {
                                h(w, "imp", J)
                            },
                            parseInt(W) * 1000)
                        })(I, R)
                    }
                }
            }
        }
    }
    function k(e) {
        var x = e.src;
        if (x.replace(/^\s+|\s+$/g, "") == "") {
            return
        }
        var w = v.getElementById(e.nodeId);
        if (w) {
            u(e, w)
        } else {
            if (!f) {
                g.setTimeout(function() {
                    k.call(null, e)
                },
                50)
            }
        }
    }
    function j(e) {
        g[s] || (g[s] = []);
        g[s].push(e)
    }
    function b() {
        this.push = function(e) {
            e.constructor == Array && o.render(e)
        }
    }
    function l(D, C, e, y, x, B) {
        var A = "",
        z = "";
        if (B) {
            A = '<param name="allowFullScreen" value="true" />';
            z = 'allowfullscreen="true"'
        } else {
            A = '<param name="wmode" value="transparent" />';
            z = 'wmode="transparent"'
        }
        return '<object id="' + x + '" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,0,0,0" align="middle" width="' + e + '" height="' + y + '"> ' + A + ' <param name="allowScriptAccess" value="always" /><param name="movie" value="' + D + '"/><param name="quality" value="high"/><param name="bgcolor" value="#000"/><param name="width" value="' + e + '" /><param name="height" value="' + y + '" />' + (C ? '<param name="FlashVars" value="' + C + '" />': "") + '<embed src="' + D + '" quality="high" bgcolor="#000" width="' + e + '" height="' + y + '" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" allowscriptaccess="always" loop="true" ' + z + ' name="' + x + '" ' + (C ? 'flashvars="' + C + '" ': "") + "></embed></object>"
    }
    var m = g[p + "N"];
    if (m && m.constructor == Array) {
        o.render(m);
        g[p + "N"] = new b()
    }
})(mzadxN || {},
window, document, js_version); (function(i, m) {
    var g = "__mz_ad_rsq";
    var d = false,
    k = false,
    a = navigator.userAgent;
    if (a.indexOf("MSIE 6.0") > 0) {
        d = true
    }
    function n(r, p, q, o) {
        r.addEventListener ? r.addEventListener(p, q, o) : r.attachEvent && r.attachEvent("on" + p, q)
    }
    function b(r, p, q, o) {
        r.removeEventListener ? r.removeEventListener(p, q, o) : r.detachEvent && r.detachEvent("on" + p, q)
    }
    var e = {
        properties: {
            div_id: "MZAD_POP_PLACEHOLDER",
            close_btn: "MZAD_POP_CLOSE_BTN"
        },
        show: function(s, t) {
            var q = m.getElementById(e.properties.div_id),
            r = s.adw,
            p = s.adh + 25;
            if (!q) {
                q = e.createPopWin(r, p);
                var o = e.createCloseBtn(r);
                var u = e.createContentDiv(r, p, t);
                q.appendChild(o);
                q.appendChild(u);
                m.body.appendChild(q)
            }
            q.style.width = r + "px";
            if (d) {
                q.style.height = p + "px"
            } else {
                q.style.height = "0px";
                GLOBAL_POP_ANIMATE = setInterval(function() {
                    e.move(q, p)
                },
                2)
            }
        },
        move: function(p, o) {
            var q = parseInt(p.style.height);
            if (q < o) {
                p.style.height = (q + 5).toString() + "px"
            } else {
                clearInterval(GLOBAL_POP_ANIMATE)
            }
        },
        createPopWin: function(q, o) {
            var p = m.createElement("div");
            p.id = e.properties.div_id;
            p.style.padding = "0px";
            p.style.display = "block";
            p.style.visibility = "visible";
            p.style.border = "medium none";
            p.style.background = "none repeat scroll 0% 0% transparent";
            p.style.cssFloat = "none";
            p.style.styleFloat = "none";
            p.style.overflow = "hidden";
            p.style.zIndex = "2147483647";
            if (d) {
                p.style.position = "absolute";
                p.style.left = (m.documentElement.scrollLeft + m.documentElement.clientWidth - q - 5).toString() + "px";
                p.style.top = (m.documentElement.scrollTop + m.documentElement.clientHeight - o).toString() + "px";
                n(i, "scroll", e.fixedIE6Position);
                n(i, "resize", e.fixedIE6Position)
            } else {
                p.style.position = "fixed";
                p.style.right = "5px";
                p.style.bottom = "5px"
            }
            return p
        },
        createCloseBtn: function(q) {
            var o = m.createElement("div");
            o.id = e.properties.close_btn;
            o.style.position = "absolute";
            o.style.width = "61px";
            o.style.height = "20px";
            o.style.top = "0px";
            o.style.left = (q - 61).toString() + "px";
            o.style.margin = "0px 0px 5px";
            o.style.padding = "0px";
            o.style.cursor = "pointer";
            o.style.overflow = "hidden";
            var p = m.createElement("div");
            p.style.width = "40px";
            p.style.height = "20px";
            p.style.backgroundColor = "rgb(153, 153, 153)";
            p.style.color = "rgb(255, 255, 255)";
            p.style.cssFloat = "left";
            p.style.styleFloat = "left";
            p.style.marginRight = "1px";
            p.style.fontSize = "12px";
            p.style.fontFamily = "΢���ź�";
            p.style.textAlign = "center";
            p.style.lineHeight = "20px";
            p.innerHTML = "�ر�";
            var r = m.createElement("a");
            r.style.padding = "0px";
            r.style.display = "inline-block";
            r.style.border = "medium none";
            r.style.overflow = "hidden";
            r.style.height = "20px";
            r.style.lineHeight = "20px";
            r.style.cursor = "pointer";
            r.style.width = "20px";
            r.style.background = "url('http://js.miaozhen.com/close.png')";
            r.style.marginBottom = "3px";
            r.style.cssFloat = "left";
            r.style.styleFloat = "left";
            o.appendChild(p);
            o.appendChild(r);
            n(o, "click", e.close);
            return o
        },
        createContentDiv: function(p, o, q) {
            var r = m.createElement("div");
            r.style.width = p + "px";
            r.style.height = o + "px";
            r.style.overflow = "hidden";
            r.style.position = "absolute";
            r.style.left = "0px";
            r.style.top = "25px";
            r.innerHTML = q;
            return r
        },
        close: function() {
            var o = m.getElementById(e.properties.div_id);
            if (o) {
                var p = m.getElementById(e.properties.close_btn);
                p && b(p, "click", e.close);
                if (d) {
                    b(i, "scroll", e.fixedIE6Position);
                    b(i, "resize", e.fixedIE6Position)
                }
                o.parentNode.removeChild(o)
            }
        },
        fixedIE6Position: function() {
            var o = m.getElementById(e.properties.div_id);
            if (o) {
                o.style.position = "absolute";
                o.style.left = (m.documentElement.scrollLeft + m.documentElement.clientWidth - o.offsetWidth - 5).toString() + "px";
                o.style.top = (m.documentElement.scrollTop + m.documentElement.clientHeight - o.offsetHeight).toString() + "px"
            }
        }
    };
    var f = {
        properties: {
            MZAD_COUPLET_LEFT: "MZAD_COUPLET_LEFT",
            MZAD_COUPLET_RIGHT: "MZAD_COUPLET_RIGHT",
            MZAD_COUPLET_CLOSE_BTN_SUFFIX: "_CLOSE_BTN",
            MZAD_COUPLET_CONTENT_DIV_SUFFIX: "_CONTENT_DIV",
            MZAD_CLOSEBTN_HEIGHT: 18
        },
        show: function(r, s, q) {
            var p = r.adw,
            o = r.adh;
            if (!q || q != 2) {
                var u = this.createRootElement(p, o, f.properties.MZAD_COUPLET_LEFT, s);
                document.body.appendChild(u)
            }
            if (!q || q != 1) {
                var t = this.createRootElement(p, o, f.properties.MZAD_COUPLET_RIGHT, s);
                document.body.appendChild(t)
            }
            if (d) {
                f.resetRootElementPositionWithTimeout();
                n(window, "resize", f.resetRootElementPositionWithTimeout);
                n(window, "scroll", f.resetRootElementPositionWithTimeout)
            } else {
                f.onWinResize();
                n(window, "resize", f.onWinResize)
            }
        },
        createRootElement: function(r, o, p, s) {
            var q = document.createElement("div");
            q.id = p;
            q.style.width = r + "px";
            q.style.height = o + f.properties.MZAD_CLOSEBTN_HEIGHT + "px";
            q.style.zIndex = "66666";
            q.style.padding = "0px";
            q.style.display = "block";
            q.style.visibility = "visible";
            q.style.border = "none";
            q.style.cssFloat = "none";
            q.style.styleFloat = "none";
            q.style.overflow = "hidden";
            if (p == f.properties.MZAD_COUPLET_LEFT) {
                q.style.left = "10px"
            } else {
                if (p == f.properties.MZAD_COUPLET_RIGHT) {
                    q.style.right = "10px"
                }
            }
            if (a.indexOf("MSIE 6.0") > 0) {
                q.style.position = "absolute";
                q.style.border = "none";
                q.style.background = "none transparent scroll repeat 0% 0%"
            } else {
                if (a.indexOf("MSIE 8.0") > 0 || a.indexOf("MSIE 7.0") > 0) {
                    q.style.position = "fixed";
                    q.style.border = "medium none";
                    q.style.background = "none transparent scroll repeat 0% 0%"
                } else {
                    q.style.position = "fixed";
                    q.style.backgroundPosition = "initial initial";
                    q.style.backgroundRepeat = "initial initial"
                }
            }
            q.appendChild(f.createCloseBtn(r, o, p));
            q.appendChild(f.createContentDiv(r, o, p, s));
            return q
        },
        createCloseBtn: function(q, o, p) {
            var r = document.createElement("div");
            r.id = p + f.properties.MZAD_COUPLET_CLOSE_BTN_SUFFIX;
            r.style.position = "absolute";
            r.style.width = q + "px";
            r.style.height = f.properties.MZAD_CLOSEBTN_HEIGHT;
            r.style.top = o + "px";
            r.style.cursor = "pointer";
            r.style.backgroundColor = "#999999";
            r.style.color = "#fff";
            r.style.fontSize = "12px";
            r.style.fontFamily = "΢���ź�";
            r.style.textAlign = "center";
            r.style.lineHeight = "20px";
            r.innerHTML = "�ر�";
            n(r, "click", f.onAdClose);
            return r
        },
        createContentDiv: function(r, o, p, s) {
            var q = document.createElement("div");
            q.id = p + f.properties.MZAD_COUPLET_CONTENT_DIV_SUFFIX;
            q.style.width = r + "px";
            q.style.height = o + "px";
            q.style.overflow = "hidden";
            q.innerHTML = s;
            return q
        },
        onAdClose: function(p) {
            p = p ? p: window.event;
            var o = p.srcElement ? p.srcElement: p.target;
            b(o, "click", f.onAdClose);
            document.body.removeChild(o.parentNode);
            var r = document.getElementById(f.properties.MZAD_COUPLET_LEFT);
            var q = document.getElementById(f.properties.MZAD_COUPLET_RIGHT);
            if (!r && !q) {
                b(window, "resize", f.onWinResize);
                b(window, "resize", f.resetRootElementPositionWithTimeout);
                b(window, "scroll", f.resetRootElementPositionWithTimeout)
            }
        },
        onWinResize: function() {
            var q = document.getElementById(f.properties.MZAD_COUPLET_LEFT);
            var p = document.getElementById(f.properties.MZAD_COUPLET_RIGHT);
            var o = 0;
            if (document.documentElement && document.documentElement.clientHeight) {
                o = document.documentElement.clientHeight
            } else {
                if (window.innerHeight) {
                    o = window.innerHeight
                } else {
                    if ((document.body) && (document.body.clientHeight)) {
                        o = document.body.clientHeight
                    }
                }
            }
            if (q) {
                if (o >= q.offsetHeight) {
                    q.style.top = (o - q.offsetHeight) / 2 + "px"
                } else {
                    q.style.top = "0px"
                }
            }
            if (p) {
                if (o >= p.offsetHeight) {
                    p.style.top = (o - p.offsetHeight) / 2 + "px"
                } else {
                    p.style.top = "0px"
                }
            }
        },
        resetRootElementPositionWithTimeout: function() {
            setTimeout(f.resetRootElementPosition, 100)
        },
        resetRootElementPosition: function() {
            var q = document.getElementById(f.properties.MZAD_COUPLET_LEFT);
            var p = document.getElementById(f.properties.MZAD_COUPLET_RIGHT);
            var o = 0;
            if (document.documentElement && document.documentElement.clientHeight) {
                o = document.documentElement.clientHeight
            } else {
                if (window.innerHeight) {
                    o = window.innerHeight
                } else {
                    if ((document.body) && (document.body.clientHeight)) {
                        o = document.body.clientHeight
                    }
                }
            }
            if (q) {
                q.style.left = document.documentElement.scrollLeft + 10 + "px";
                if (o >= q.offsetHeight) {
                    q.style.top = document.documentElement.scrollTop + (m.documentElement.clientHeight - q.offsetHeight) / 2 + "px"
                } else {
                    q.style.top = document.documentElement.scrollTop + "px"
                }
            }
            if (p) {
                p.style.left = document.documentElement.scrollLeft + m.documentElement.clientWidth - p.offsetWidth - 10 + "px";
                if (o >= p.offsetHeight) {
                    p.style.top = document.documentElement.scrollTop + (m.documentElement.clientHeight - p.offsetHeight) / 2 + "px"
                } else {
                    p.style.top = document.documentElement.scrollTop + "px"
                }
            }
        }
    };
    function j() {
        this.push = function(o) {
            l(o)
        }
    }
    function l(o) {
        if (o.constructor == Object) {
            if (o.material.style == "pop") {
                e.show(o.material, o.contentHTML)
            } else {
                if (o.material.style == "couplet") {
                    f.show(o.material, o.contentHTML)
                }
            }
        }
    } ! i[g] && (i[g] = []);
    if (i[g].constructor == Array) {
        var h = i[g].length,
        c;
        while (h--) {
            l(i[g].shift())
        }
        i[g] = new j()
    }
})(window, document);