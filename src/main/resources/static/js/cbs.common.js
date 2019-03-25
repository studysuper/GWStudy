/**
 * @Author ZQ
 * @Description //基础组件定义
 * @Date 2019/3/25 18:46
 * @Param
 * @return
 **/
+function ($, window, undefined) {

    'use strict';

    window.cbs = window.cbs || {};

    cbs.rsapubkey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZomUQ7J41JT4cIi9ZMh1xFjJQ3cjicx1+bNUce8gZzJPrI5j6YjILsbUzxBjuJFQMGTIiPmAHm0mPmlWS7yPQ1LRIp/WMCT+hNXn1gIdQFqLDI4B9sGCPOc5VVh6uNbDLn3WU2wcLAC4XJLMzKdbjYsA/yuHgSv07tlXiykAKOwIDAQAB";

    var dateConfig = {
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        pickerPosition: 'bottom-left',
        startView: 2,
        minView: 2,
        minuteStep: 5,
        todayBtn: 1,
        autoclose: 1,
        startInitDate: '',
        endInitDate: '',
        clearBtn: true,
        todayHighlight: true
    }, codeUrl = _ctx + '/common/sysDict/childDictList/', areaUrl = _ctx + '/system/sysArea/children/';

    function calMinView(format) {
        if (!format) {
            return 2;
        } else if (format.indexOf('ss') != -1 || format.indexOf('ii') != -1) {
            return 0;
        } else if (format.indexOf('hh') != -1) {
            return 1;
        } else if (format.indexOf('dd') != -1) {
            return 2;
        } else if (format.indexOf('mm') != -1) {
            return 3;
        } else if (format.indexOf('yy') != -1) {
            return 4;
        }
        return 2;
    }

    function checkInputDate(dateStr, format) {
        var reg = new RegExp(format.replace(/y+/, '((19|20|30)[0-9][0-9])').replace(/m+/, '(0[1-9]|1[012])').replace(
            /d+/, '(0[1-9]|[12][0-9]|3[01])').replace(/h+/, '([01][0-9]|2[0-3])').replace(/i+/, '([0-5][0-9])')
            .replace(/s+/, '([0-5][0-9])'));
        return reg.test(dateStr);
    }

    // 日期
    // element:默认.date;format:默认'yyyy-mm-dd';startDate:开始时间;endDate:结束时间
    // 注意：.date类标签上有ID的，需要单独初始化，默认element不会初始化有ID的标签
    // input需要禁止输入，需要手动加readonly或者disabled；图标点击不显示日期插件：父级DIV标签上添加自定义属性：isDisable="Y"
    cbs.date = function (element, format, startDate, endDate) {
        var e = !!element ? $(element) : $('.date'), c, i, input, initdate, now = new Date();
        e.each(function (index, item) {
            c = $.extend({}, dateConfig);
            i = $(item);
            if (!element && i[0].id) {
                return;
            }
            if (!!format) {
                c.format = format;
                c.minView = calMinView(format);
            }
            c.startInitDate = startDate ? startDate : '';
            c.endInitDate = endDate ? endDate : '';
            if (i.attr('isDisable')) {
                c.isDisable = i.attr('isDisable');
            }
            i.find("input").each(function (id, it) {
                input = $(it);
                input.attr('placeholder', '日期格式:' + c.format);
                initdate = input.attr('initdate');
                input.removeAttr('readonly');
                if (!!initdate) {
                    if (initdate == 'now') {
                        input.val(now.Format(format));
                    } else {
                        input.val(initdate);
                    }
                }
                if (input.attr('picker-position')) {
                    c.pickerPosition = input.attr('picker-position');
                }
                input.bind('change', function () {
                    var _this = $(this), val = _this.val();
                    if (!!val && !checkInputDate(val, c.format)) {
                        layer.msg('输入日期:' + val + "不满足格式:" + c.format + ',请修改录入');
                        _this.val('');
                        _this.focus();
                    }
                });
            });
            i.datetimepicker(c);
        });
    };

    // 地区管理
    cbs.areas = new function () {
        return {
            get: function (parentCode) {
                var key = (!parentCode ? 'root' : parentCode);
                var areas = cbs.storage.get('area-' + key, cbs.storage.SESSION);
                if (!areas) {
                    cbs.httpclient.syncGet(areaUrl + key, function (data) {
                        if (!!data && data.length > 0) {
                            areas = {};
                            $.each(data, function (index, item) {
                                areas[item.areaCode] = item.areaName;
                            });
                            cbs.storage.set('area-' + key, areas, cbs.storage.SESSION);
                        }
                    });
                }
                return areas;
            },
            value: function (areaCode, parentCode) {
                var areas = this.get(parentCode);
                if (!!areas) {
                    return areas[areaCode];
                }
            }
        }
    };

    // 数据字典
    cbs.codes = new function () {
        return {
            get: function (code, parentValue) {
                var key = 'code-' + code, url = codeUrl + '/' + code;
                if (!!parentValue) {
                    key += '-' + parentValue;
                    url += '/' + parentValue;
                }
                var codeValues = cbs.storage.get(key, cbs.storage.SESSION);
                if (!codeValues) {
                    cbs.httpclient.syncGet(url, function (data) {
                        if (!!data && data.length > 0) {
                            codeValues = {};
                            $.each(data, function (index, item) {
                                codeValues[item.value] = item.name;
                            });
                            cbs.storage.set(key, codeValues, cbs.storage.SESSION);
                        }
                    });
                }
                return codeValues;
            },
            value: function (code, field, parentValue) {
                var codeValues = this.get(code, parentValue);
                if (!!codeValues) {
                    return codeValues[field];
                }
            }
        }
    };

    // 供应商字典
    cbs.suppliers = new function () {
        var createKey = function (config) {
            var key = 'suppler-code';
            if (!!config.supplierType) {
                key += '-t' + config.supplierType;
            }
            if (!!config.supplierClass) {
                key += '-c' + config.supplierClass;
            }
            if (!!config.saasId) {
                key += '-s' + config.saasId;
            }
            if (!!config.parentCode) {
                key += '-p' + config.parentCode;
            }
            if (!!config.managecom) {
                key += '-m' + config.managecom;
            }
            return key;
        };
        var url = _ctx + '/product/cpSupplier/code';

        return {
            get: function (config) {
                var key, codes, param = {
                    supplierType: '01',
                    supplierClass: '01'
                };
                param = $.extend({}, param, config);
                key = createKey(param);
                codes = cbs.storage.get(key, cbs.storage.SESSION);
                if (!codes) {
                    cbs.httpclient.syncGet(url, param, function (data) {
                        if (!!data && data.code == 0 && data.codes.length > 0) {
                            codes = data.codes;
                            cbs.storage.set(key, codes, cbs.storage.SESSION);
                        }
                    });
                }
                return codes;
            },
            name: function (code, isshort) {
                if (!code) {
                    return '';
                }
                var supplierName, supplier, suppliers = this.get({
                    supplierType: '',
                    supplierClass: '',
                    saasId: '-'
                });
                $.each(suppliers, function (i, s) {
                    if (s.supplierCode == code) {
                        supplier = s;
                        return;
                    }
                });
                if (!!supplier) {
                    if (!isshort) {
                        supplierName = supplier.supplierName;
                    } else {
                        supplierName = supplier.supplierShortName;
                    }
                }
                return supplierName;
            },
            init: function () {
                var param = {
                    supplierType: '',
                    supplierClass: '',
                    saasId: '-'
                }, key = createKey(param);
                cbs.httpclient.get(url, param, function (data) {
                    if (!!data && data.code == 0 && data.codes.length > 0) {
                        cbs.storage.set(key, data.codes, cbs.storage.SESSION);
                    }
                }, false);
            }
        };
    };

    // 产品字典
    cbs.risks = new function () {

        var callback = function (data) {
            if (!!data && data.code == 0 && data.codes.length > 0) {
                cbs.storage.set('riskcodes', data.codes, cbs.storage.SESSION);
            }
        }, url = _ctx + '/product/cpRisk/codes';

        return {
            get: function () {
                var codes = cbs.storage.get('riskcodes', cbs.storage.SESSION);
                if (!codes) {
                    cbs.httpclient.syncGet(url, callback);
                    codes = cbs.storage.get('riskcodes', cbs.storage.SESSION);
                }
                return codes;
            },
            name: function (riskcode, isshort) {
                if (!riskcode) {
                    return '';
                }
                var riskName, risks = this.get();
                $.each(risks, function (i, risk) {
                    if (risk.riskCode == riskcode) {
                        if (isshort) {
                            riskName = risk.riskShortName;
                        } else {
                            riskName = risk.riskName;
                        }
                        return;
                    }
                });
                return riskName;
            },
            init: function () {
                var codes, codeMap = {};
                cbs.httpclient.get(url, callback, false);
            }
        };
    };

    // 机构字典
    cbs.depts = new function () {

        var callback = function (data) {
            if (data.code == 0) {
                cbs.storage.set('userdepts', data.data, cbs.storage.SESSION);
            }
        }, url = _ctx + "/usercenter/uDept/getAllDepts";

        return {
            get: function () {
                var depts = cbs.storage.get('userdepts', cbs.storage.SESSION);
                if (!depts) {
                    cbs.httpclient.syncGet(url, callback);
                    depts = cbs.storage.get('userdepts', cbs.storage.SESSION);
                }
                return depts;
            },
            name: function (deptcode) {
                if (!deptcode) {
                    return '';
                }
                var deptName, depts = this.get();
                $.each(depts, function (i, dept) {
                    if (dept.deptcode == deptcode) {
                        deptName = dept.deptShortname;
                        return;
                    }
                });
                return deptName;
            },
            init: function () {
                var depts = cbs.storage.get('userdepts', cbs.storage.SESSION);
                if (!depts) {
                    cbs.httpclient.get(url, callback, false);
                }
            }
        };
    };

    // 浏览器判断
    cbs.browser = function () {
        var userAgent = navigator.userAgent; // 取得浏览器的userAgent字符串
        var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; // 判断是否IE<11浏览器
        var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; // 判断是否IE的Edge浏览器
        var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
        if (isIE) {
            var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
            reIE.test(userAgent);
            var fIEVersion = parseFloat(RegExp["$1"]);
            if (fIEVersion == 7) {
                return 7;
            } else if (fIEVersion == 8) {
                return 8;
            } else if (fIEVersion == 9) {
                return 9;
            } else if (fIEVersion == 10) {
                return 10;
            } else {
                return 6;// IE版本<=7
            }
        } else if (isEdge) {
            return 'edge';// edge
        } else if (isIE11) {
            return 11; // IE11
        } else {
            return -1;// 不是ie浏览器
        }
    };

    // 缓存
    cbs.storage = new function () {
        function isObject(value) {
            return value !== null && typeof value === 'object';
        }

        function toObject(datastr) {
            var obj = datastr;
            if (!!datastr) {
                try {
                    obj = JSON.parse(datastr);
                } catch (e) {
                    obj = datastr;
                }
            }
            return obj;
        }

        function toString(obj) {
            if (isObject(obj)) {
                return JSON.stringify(obj);
            }
            return obj;
        }

        return {
            LOCAL: 1,
            SESSION: 2,
            get: function (key, scope) {
                var _data = undefined;
                if (1 == scope) {
                    _data = localStorage.getItem(key);
                } else if (2 == scope) {
                    _data = sessionStorage.getItem(key);
                }
                if (!!_data) {
                    _data = toObject(_data);
                }
                return _data;
            },
            set: function (key, value, scope) {
                if (1 == scope) {
                    localStorage.setItem(key, toString(value));
                } else if (2 == scope) {
                    sessionStorage.setItem(key, toString(value));
                }
            },
            del: function (key, scope) {
                if (1 == scope) {
                    localStorage.removeItem(key);
                } else if (2 == scope) {
                    sessionStorage.removeItem(key);
                }
            },
            clear: function (scope) {
                if (1 == scope) {
                    localStorage.clear();
                } else if (2 == scope) {
                    sessionStorage.clear();
                } else {
                    localStorage.clear();
                    sessionStorage.clear();
                }
            }
        };
    };

    // 判断是否为Array
    cbs.isArray = function (obj) {
        return Object.prototype.toString.call(obj) == '[object Array]';
    };

    // 判断是否为String
    cbs.isString = function (obj) {
        return Object.prototype.toString.call(obj) == '[object String]';
    };

    // 判断是否为Function
    cbs.isFunction = function (obj) {
        return Object.prototype.toString.call(obj) == '[object Function]';
    };

    // 判断是否为Object
    cbs.isObject = function (obj) {
        return Object.prototype.toString.call(obj) == '[object Object]';
    };

    // 判断是否为Number
    cbs.isNumber = function (obj) {
        return typeof obj === 'number' && !isNaN(obj);
    };

    // 查询条件区域隐藏
    $.prototype.slide = function (slideDiv) {
        var _this = $(this), _div = $(slideDiv);
        _this.hide();
        if (_div.height() > 180) {
            _div.addClass('slide-height');
            _this.show();
            _this.children('span').bind('click', function () {
                if ($(this).hasClass("slide-down")) {
                    $(".cx-box").addClass("slide-full").removeClass("slide-height");
                    $(this).removeClass("slide-down").addClass("slide-up");
                } else {
                    $(".cx-box").addClass("slide-height").removeClass("slide-full");
                    $(this).removeClass("slide-up").addClass("slide-down");
                }
            });
        }
    };

    // 按钮防重复点击
    $.fn.notReSubmit = function (timeout) {
        var that = this[0], timeout = timeout || 10, clone = this.clone();
        clone.hide();
        this.after(clone);
        that.addEventListener('click', function () {
            $(that).hide();
            clone.show();
            setTimeout(function () {
                $(that).show();
                clone.hide();
            }, timeout * 1000);
        }, false);
    };

    // 表单序列化程data
    $.fn.serializeData = function () {
        var data = {}, arres = $(this).serializeArray(), value;
        if (cbs.isArray(arres) && arres.length > 0) {
            $.each(arres, function (index, item) {
                value = data[item.name];
                if (!value) {
                    data[item.name] = item.value;
                } else {
                    if (cbs.isArray(value)) {
                        value.push(item.value);
                    } else {
                        value = data[item.name] = [value];
                        value.push(item.value);
                    }
                }
            });
        }
        return data;
    };

    if (typeof String.prototype.startsWith != 'function') {
        String.prototype.startsWith = function (prefix) {
            if (!prefix) {
                return false;
            }
            return this.slice(0, prefix.length) == prefix;
        };
    }

    if (typeof String.prototype.endsWith != 'function') {
        String.prototype.endsWith = function (suffix) {
            if (!prefix) {
                return false;
            }
            return this.indexOf(suffix, this.length - suffix.length) !== -1;
        };
    }

    if (typeof String.prototype.trims != 'function') {
        String.prototype.trims = function () {
            return this.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, '');
        };
    }

    // 导出按钮
    $.fn.exportBtn = function (tableId, actionUrl, params) {
        var that = $(this), tableEle = $('#' + tableId), code, value, selectArry = [], pageArry = [], dataModel = [],
            rows = [], initial = params.limit, total, openDiv;
        if (cbs.storage.get('paramsValue', cbs.storage.SESSION))
            cbs.storage.del('paramsValue', cbs.storage.SESSION);

        function getTableRows(actionUrl, params, dealData) {
            cbs.httpclient.get(actionUrl, params, function (data) {
                rows = data.rows;
                total = data.total;
                if (dealData) {
                    dealData();
                }
            })
        }

        getTableRows(actionUrl, params);
        openDiv = $('<div class="hx-openw2 spe2 bg1 hide" id="openWin">'
            + '<div class="close c-p"></div>'
            + '<div class="cx-box cx-box5 mar6">'
            + '<div class="clearfix">'
            + '<dl class="col-xs-12">'
            + '<dt class="f-left">列表项选择</dt>'
            + '<dd>'
            + '<select id="demoSelectPick" class="selectpicker form-control chosen-select valid" multiple title="请选择" data-size="4">'
            + '</select>' + '</dd>' + '</dl>' + '<dl class="col-xs-6">' + '<dt class="f-left">导出页数</dt>' + '<dd>'
            + '<select class="form-control chosen-select valid" id="selectPage">' + '</select>' + '</dd>' + '</dl>'
            + '</div>' + '<div class="t-center mar5">' + '<span class="hx-d-btn cencel-b"><b></b>取消</span>'
            + '<span class="hx-d-btn submit-b"><b></b>保存</span>' + '</div>' + '</div>' + '</div>');
        $('body').append(openDiv);
        that.bind('click', function () {
            if (selectArry.length == 0) {
                tableEle.find('th').each(function () {
                    var _this = $(this);
                    code = _this.attr('data-field');
                    value = _this[0].textContent;
                    if (code != '0') {
                        selectArry.push({
                            code: code,
                            value: value
                        });
                    }
                })
                $('.page-number.active').each(function () {
                    pageArry.push($(this)[0].textContent);
                    $("#selectPage").append('<option value=" ' + $(this)[0].textContent + '">当前页</option>');
                })
                $("#selectPage").append('<option value="all">全部页</option>');
                var demoSelectPick = $('#demoSelectPick');
                for (var i = 0; i < selectArry.length; i++) {
                    demoSelectPick.append("<option value='" + selectArry[i].code + "'>" + selectArry[i].value
                        + "</option>");
                }
                demoSelectPick.selectpicker("refresh");
            }
            $("#openWin").removeClass("hide");
            console.log(selectArry);
        })
        $('.close.c-p').on("click", function () {
            $(this).parent().addClass("hide");
        });
        $('.hx-d-btn.cencel-b').on("click", function () {
            $("#openWin").addClass("hide");
        });
        $('.hx-d-btn.submit-b').on(
            "click",
            function () {
                params = !!cbs.storage.get('paramsValue', cbs.storage.SESSION) ? cbs.storage.get('paramsValue',
                    cbs.storage.SESSION) : params;
                var mutilList = $('#demoSelectPick').val();
                var selectVal = $("#selectPage").val();
                if (selectVal == 'all') {
                    params.limit = total;
                    params.offset = '0';
                    getTableRows(actionUrl, params, dealData);
                } else {
                    params.limit = initial;
                    $('.page-number.active').each(function () {
                        if ($(this)[0].textContent == '1') {
                            params.offset = '0';
                        } else {
                            params.offset = (parseInt($(this)[0].textContent) - 1) * initial;
                        }
                        getTableRows(actionUrl, params, dealData);
                    })
                }

                function dealData() {
                    dataModel = [];
                    $.each(mutilList, function (i, mutilItem) {
                        $.each(selectArry, function (j, slectItem) {
                            if (mutilItem == slectItem.code) {
                                var map = {};
                                map.columName = mutilItem;
                                map.fieldName = slectItem.value;
                                dataModel.push(map);
                            }
                        })
                    })
                    postExcelFile({
                        rows: rows,
                        dataModel: dataModel
                    }, _ctx + "/export/exportExcel");
                    $("#openWin").addClass("hide");
                }
            });

        function postExcelFile(params, url) { // params是post请求需要的参数，url是请求url地址
            var form = document.createElement("form");
            form.style.display = 'none';
            form.action = url;
            form.method = "post";
            document.body.appendChild(form);

            var input = document.createElement("input");
            input.type = "hidden";
            input.name = "exportData";
            input.value = JSON.stringify(params);
            form.appendChild(input);

            form.submit();
            form.remove();
        }
    };

    Date.prototype.Format = function (fmt) {
        var o = {
            "m+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "i+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S": this.getMilliseconds()
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
                    .substr(("" + o[k]).length)))
            }
        }
        return fmt;
    };

}(jQuery, window);
