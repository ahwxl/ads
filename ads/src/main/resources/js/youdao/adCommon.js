/*
 * 请求广告数据
 * hucq@rd.netease.com
 * 2013-08-22
 *
 * 1. 普通广告位, 直接渲染
 * 2. 外部样式广告位, 则需要载入对应的渲染脚本, 然后渲染
 */

(function (win, doc) {

    // 载入script脚本(回调)
    function loadJS(url, callback) {
        if(!url) {
            return false;
        }
        var script = doc.createElement('script');
        script.src = url;
        script.onload = script.onreadystatechange = function() {
            var state = script.readyState;
            if (!state || /^loaded|complete$/.test(state)) {
                callback && callback();
                script.onload = script.onreadystatechange = null;
                script.parentNode.removeChild(script);
            }
        }
        doc.getElementsByTagName('head')[0].appendChild(script);
    }


    // 模板工具
    function templ(templStr, data) {
        var reg = new RegExp('\\{\\{=' + '([^}]+)' + '\\}\\}', 'g');
        templStr = templStr.replace(reg, function(wrapKey, key) {
            return data[key] !== undefined ? data[key] : wrapKey;
        });
        return templStr;
    }


    // jsonp 请求数据
    function JSONP(url, params, callback) {
        var search  = [],funName, p;
        // 序列化参数
        funName = '_jsonpCallBack_' + (new Date()).valueOf();
        params.callback = funName;
        for (p in params) {
            if (params.hasOwnProperty(p)) {
                search.push(p + '=' + win.encodeURIComponent(params[p]));
            }
        }
        // 回调函数
        win[funName] = function () {
            callback.apply(win, Array.prototype.slice.call(arguments));
            win[funName] = function () {};
            try {
                delete win[funName];
            } catch(e) {}
        };
        // 开始载入
        url = url + (/\?/.test(url) ? '&' : '?') + search.join('&');
        loadJS(url);
    }


    // 延迟执行
    function deffer(handledName, data) {
        var func = win[handledName];
        if (typeof func === 'function') {
            return func(data);
        }
        win.setTimeout(function(){
            deffer(handledName, data);
        }, 50);
    }


    // 普通广告位广告
    win.innerAd = function(data) {
        var slotId, holder, adElem, adTemp, html;
        // 插入位置
        slotId = 'yd_slot_id_' + data.id;
        holder = doc.getElementById(slotId);
        adElem = doc.createElement('inc');
        // 模板
        adTemp = ['<iframe ', 
            'style="width:{{=width}}px;height:{{=height}}px;" ',
            'scrolling="no" frameborder="0" src="{{=adSrc}}" allowtransparency></iframe>'
        ].join('');
        // 填充数据
        html = templ(adTemp, data);
        adElem.innerHTML = html;
        holder.parentNode.insertBefore(adElem, holder);
    }


    // 渲染广告
    function renderAd(data) {
        var jsAry;
        function callback() {
            if (jsAry.length) {
                loadJS(jsAry.pop(), callback);
            } else {
                deffer(data.callback, data.config);
            }
        }
        // 直接渲染
        if (!data.outerJs) {
            return deffer(data.callback, data.config);
        }
        // 载入依赖后渲染
        jsAry = data.outerJs.split(',');
        loadJS(jsAry.pop(), callback);
    }

    // 获取页面编码
    function getEncode() {
        var encode = doc.characterSet || doc.charset || doc.defaultCharset;
        return encode || '';
    }

    // 获取referrer
    function getReferrer() {
        var result = doc.referrer;
        if (!result) {
            try {
                result = win.opener.location.href;
            } catch (e) {}
        }
        return result || '';
    }

    // 获取
    function getReq() {
        var loc = doc.location, topLoc;
        try {
            topLoc = win.top.location;
        } catch (e) {}

        loc = (loc !== topLoc) ? doc.referrer : loc.href;
        return loc || '';
    }


    function init() {
        var url    = 'http://impservice.union.youdao.com/imp/slot.s',
            data   = {'slotid' : yd_slot_id, 'startTime' : new Date().getTime()},
            reg    = getReq(),
            ref2   = getReferrer(),
            encode = getEncode();

        data.req = reg;
        data.encode = encode;
        // ref2 为空不传递
        ref2 && (data.ref2 = ref2);

        // 请求广告数据
        JSONP(url, data, renderAd);
    }

    init();

}(window, document));