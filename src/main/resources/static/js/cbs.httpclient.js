/**
 * @Author ZQ
 * ==== 接口访问====
 * @Description //互联网核心接口访问
 * @Date 2019/3/25 19:53
 * @Param
 * @return
 **/
+function ($, win, undefined) {
    'use strict';

    window.cbs = window.cbs || {};
    var showMessage = true, loadingMessage = "正在加载中", loadingList = [], layerIndex;

    var config = {
        cache: true,
        async: true,// 默认异步请求
        error: function (request) {
            if (this.showMessage != false) {
                loadingList.shift();
            }
            if (loadingList.length == '0') {
                layer.close(layerIndex);
            }
            this.callBackError(request);
        },
        success: function (data) {
            if (this.showMessage != false) {
                loadingList.shift();
            }
            if (loadingList.length == '0') {
                layer.close(layerIndex);
            }
            this.callBackSuccess(data);
        },
        callBackError: function (data) {
            console.log(data);
        },
        callBackSuccess: function (data) {
            console.log(data);
        },
        showMessage: true
    };

   /**
    * @Author ZQ
    * @Description //统一调用接口
    * @Date 2019/3/25 20:08
    * @Param
    * @return
    **/
    function setConfig(c, args) {
        c.url = args[0];
        switch (args.length) {
            case 2:
                if (cbs.isFunction(args[1])) {
                    c.callBackSuccess = args[1];
                } else {
                    c.data = args[1];
                }
                break;
            case 3:
                if (cbs.isFunction(args[1])) {
                    c.callBackSuccess = args[1];
                    c.callBackError = args[2];
                } else {
                    c.data = args[1];
                    c.callBackSuccess = args[2];
                }
                break;
            case 4:
                c.data = args[1];
                c.callBackSuccess = args[2];
                c.callBackError = args[3];
                break;
            default:

        }

    }

    function ajaxClient(_config) {
        if (showMessage && _config.showMessage !== false) {
            layerIndex = layer.load(0, {
                content: loadingMessage,
                success: function (layero) {
                    layero.find('.layui-layer-content').css({
                        "padding-top": "40px",
                        "background-color": "rgba(0,0,0,0.5)",
                        "width": "90px",
                        "height": "75px",
                        "text-align": "center",
                        "color": "#fff",
                        "border-radius": "5px"
                    });
                }
            });
            loadingList.push(layerIndex);
        }
        $.ajax(_config);
    }

    cbs.httpclient = {
        get: function (url, data, callback, shoLoading) {
            var _config = $.extend({}, config);
            setConfig(_config, arguments);
            _config.type = 'get';
            if (shoLoading === false) {
                _config.showMessage = false;
            }
            ajaxClient(_config);
        },
        post: function (url, data, callback, shoLoading) {
            var _config = $.extend({}, config);
            setConfig(_config, arguments);
            _config.type = 'post';
            if (shoLoading === false) {
                _config.showMessage = false;
            }
            ajaxClient(_config);
        },
        postWithData: function (url, data, callback, shoLoading) {
            var _config = $.extend({}, config);
            setConfig(_config, arguments);
            _config.contentType = 'application/json; charset=UTF-8';
            _config.processData = false;
            _config.type = 'post';
            _config.data = JSON.stringify(_config.data);
            if (shoLoading === false) {
                _config.showMessage = false;
            }
            ajaxClient(_config);
        },
        syncGet: function (url, data, callback, shoLoading) {
            var _config = $.extend({}, config);
            setConfig(_config, arguments);
            _config.type = 'get';
            _config.async = false;// 同步请求
            if (shoLoading === false) {
                _config.showMessage = false;
            }
            ajaxClient(_config);
        },
        syncPost: function (url, data, callback, shoLoading) {
            var _config = $.extend({}, config);
            setConfig(_config, arguments);
            _config.type = 'post';
            _config.async = false;// 同步请求
            if (shoLoading === false) {
                _config.showMessage = false;
            }
            ajaxClient(_config);
        },
        setMessage: function (msg) {
            loadingMessage = msg;
        },
        closeMessage: function () {
            showMessage = false;
        }
    };

}(jQuery, window)