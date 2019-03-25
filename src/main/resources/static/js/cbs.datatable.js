/**
 * @Author ZQ
 * =========依赖于jQuery===
 * @Description //列表展示
 * @Date 2019/3/25 18:46
 * @Param
 * @return
 **/
+function ($, win, undefined) {

    'use strict';

    window.cbs = window.cbs || {};

    var baseconfig = {
        method: 'get', // 服务器数据的请求方式 get or post
        iconSize: 'outline',
        striped: true, // 设置为true会有隔行变色效果
        dataType: "json", // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        singleSelect: false, // 设置为true将禁止多选
        pageSize: 10, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        showColumns: false, // 是否显示内容下拉框（选择显示的列）
        sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        queryParams: function (params) {
            var p = {
                limit: params.limit,
                offset: params.offset
            };
            if (!!this.paramsForm) {
                addParams(this.paramsForm, p);
            }
            if (this.sortable) {
                $.each(this.columns[0], function (i, column) {
                    if (column.field == params.sort) {
                        p.sort = column.sortfield || column.field;
                        return false;
                    }
                });
                p.order = params.order;
            }
            return p;
        },
        onLoadSuccess: function (data) {
            if (this.exports == 'true') {
                addExport(this.tableid);
            }
            if (cbs.isObject(this.editFunctions)) {
                $.each(this.editFunctions, function (field, func) {
                    $.each(data.rows, function (index, item) {
                        $(".edit" + field + index).bind('change', function () {
                            func($(this).val(), item, field);
                        });
                    });
                });
            }
        },
        onLoadError: function (status, jqXHR) {
            console.error('列表查询失败，状态：' + status);
        },
        bindEvents: {}
    };

    // 将页面解析为配置信息
    function initConfig(element, config) {
        var tr = element.find('tr')[0], td, column, hasParamReg = new RegExp(), formatters = config.formatters,
            editFunctions = config.editFunctions;
        if (!tr) {
            throw '没有模板tr配置'
        }
        element.bind('load-error', function (status, error) {
            console.log(status);
            consoel.log(error);
            window.location.href = _ctx + '/logout';
        });
        if (!config.columns) {
            tr = $(tr);
            config.columns = [];
            if (tr.attr('checkbox')) {
                config.columns.push({
                    checkbox: true
                });
            } else if (tr.attr('radio')) {
                config.columns.push({
                    radio: true
                });
            }
            if (tr.attr('export')) {
                config.exports = tr.attr('export');
                config.tableid = element.attr('id');
            }
            tr.find('td').each(
                function (index, item) {
                    td = $(item);
                    column = {};
                    $.each(item.attributes, function () {
                        if (this.specified) {
                            column[this.name] = (this.value == "false" ? false : (this.value == "true" ? true
                                : this.value));
                        }
                    });
                    if (!!editFunctions && !!column.editable && cbs.isFunction(editFunctions[column.field])) {
                        column.editFunction = editFunctions[column.field];
                    }
                    if (!!formatters && !!formatters[column.field]) {
                        column.formatter = formatters[column.field];
                    } else {
                        addFormatter(td, column, config.isTree);
                    }
                    config.columns.push(column);
                });
        }
        tr.remove();
    }

    // 初始化treetable基础信息
    function initTreeConfig(element, config) {
        config.code = element.attr('code');
        config.parentCode = element.attr('parent-code');
        config.expandColumn = element.attr('expand-column') || 1;
        config.isTree = true;
        config.expandAll = element.attr('expand-all') == 'true' ? true : false;
        if (!!config.paramsForm) {
            config.ajaxParams = function () {
                var p = {};
                addParams(config.paramsForm, p);
                return p;
            };
        }
    }

    // 统一处理最大长度问题
    function showText(text, length) {
        if (!!text && !!length && text.length > length) {
            return '<span title="' + text + '">' + text.substr(0, length - 1) + '...</span>';
        }
        return text;
    }

    // 添加格式化信息
    function addFormatter(ele, column, isTree) {
        var format = ele.html(), dateFormat = ele.attr('date-format'), codeFormat = ele.attr('code-format'),
            codeParent = ele
                .attr("code-parent"), areaFormat = ele.attr('area-format'), maxLength = ele.attr('max-length'),
            supplierFormat = ele
                .attr('supplier-format'), riskFormat = ele.attr('risk-format'), deptFormat = ele.attr('dept-format');
        if (!!maxLength && maxLength > 0) {
            column.maxLength = maxLength;
        }
        if (!!format) { // 内容格式化
            column.formatHtml = format;
            column.formatter = function (value, row, index) {
                if (isTree) {
                    index = row;
                    row = value;
                    value = row[column.field];
                }
                var fhtml = this.formatHtml;
                if (/\${/g.test(fhtml)) {
                    var a = fhtml.replace(/\${value}/g, value);
                    a = a.replace(/\${index}/g, index);
                    a = a.replace(/\${[^\\}]*}/g, function (str) {
                        return eval(str.substring(2, str.length - 1));
                    });
                    fhtml = a;
                }
                if (!!column.editable && cbs.isFunction(column.editFunction)) {
                    var e = $(fhtml);
                    e.addClass("edit" + column.field + index);
                    if (!!codeFormat) {
                        cbs.codeSelect(e, codeFormat);
                    }
                    return e.prop("outerHTML");
                } else {
                    return fhtml;
                }
            }
        }
        if (!!dateFormat && !column.formatter) { // 日期格式化
            column.dateFormat = dateFormat;
            column.formatter = function (value, row, index) {
                if (isTree) {
                    index = row;
                    row = value;
                    value = row[column.field];
                }
                if (!value) {
                    return "";
                }
                if (typeof value == 'string') {
                    return value.substring(0, column.dateFormat.length);
                } else if (typeof value == 'object') {
                    return Date.Format(column.dateFormat);
                }
                return value;
            }
        }
        if (!!codeFormat && !column.formatter) {
            column.codeFormat = codeFormat;
            column.formatter = function (value, row, index) {
                if (isTree) {
                    index = row;
                    row = value;
                    value = row[column.field];
                }
                var parent;
                if (!!codeParent) {
                    parent = row[codeParent];
                }
                return showText(cbs.codes.value(codeFormat, value, parent), column.maxLength);
            }
        }
        if (!!areaFormat && !column.formatter) {
            column.areaFormat = areaFormat;
            column.formatter = function (value, row, index) {
                if (isTree) {
                    index = row;
                    row = value;
                    value = row[column.field];
                }
                if (this.areaFormat == 'root') {
                    return showText(cbs.areas.value(value, 'root'), column.maxLength);
                } else {
                    return showText(cbs.areas.value(value, row[areaFormat]), column.maxLength);
                }
            }
        }
        if (!!supplierFormat && !column.formatter) {
            column.supplierFormat = supplierFormat;
            column.formatter = function (value, row, index) {
                if (isTree) {
                    index = row;
                    row = value;
                    value = row[column.field];
                }
                if (!!value && value != '0') {
                    return showText(cbs.suppliers.name(value), column.maxLength);
                }
                if (value == '0') {
                    return '全部';
                }
                return '';
            }
        }
        if (!!riskFormat && !column.formatter) {
            column.riskFormat = riskFormat;
            column.formatter = function (value, row, index) {
                if (isTree) {
                    index = row;
                    row = value;
                    value = row[column.field];
                }
                if (!!column.riskFormat) {
                    return showText(cbs.risks.name(eval('row.' + column.riskFormat)), column.maxLength);
                }
                return '';
            }
        }
        if (!!maxLength && !column.formatter && maxLength > 0) {
            column.maxLength = maxLength;
            column.formatter = function (value, row, index) {
                if (isTree) {
                    index = row;
                    row = value;
                    value = row[column.field];
                }
                if (!!value && value.length > maxLength) {
                    return showText(value, column.maxLength);
                }
                return value;
            }
        }
        if (!!deptFormat && !column.formatter) { // 日期格式化
            column.deptFormat = deptFormat;
            column.formatter = function (value, row, index) {
                if (isTree) {
                    index = row;
                    row = value;
                    value = row[column.field];
                }
                if (!!column.deptFormat) {
                    return showText(cbs.depts.name(eval('row.' + column.deptFormat)), column.maxLength);
                }
                return value;
            }
        }
    }

    function addExport(tableid) {
        $.contextMenu({
            selector: '#' + tableid,
            items: {
                exports: {
                    name: '导出excel',
                    callback: function (key, opt) {
                        $('#' + tableid).table2excel({
                            exclude: ".noExl",
                            name: "exports",
                            filename: "exports",
                            exclude_img: true,
                            exclude_links: true,
                            exclude_inputs: true
                        });
                    }
                }
            }
        });
    }

    function addParams(form, params) {
        $(form).find('input,select').each(function (i, item) {
            item = $(item);
            if (!!item.val()) {
                params[item.attr('id')] = item.val().trims();
            }
        });
    }

    // table
    cbs.dataTable = function (element, config) {
        var _config = $.extend({}, baseconfig, config);
        initConfig(element, _config);
        element.show();
        $(element).bootstrapTable(_config);
    };
    //删除
    cbs.remove = function (url, id) {
        layer.confirm('确定要删除选中的记录？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                url: url,
                type: "post",
                data: {
                    'id': id
                },
                success: function (r) {
                    if (r.code == 0) {
                        layer.msg(r.msg);
                        reLoad();
                    } else {
                        layer.msg(r.msg);
                    }
                }
            });
        })
    };
    //批量删除
    cbs.batchRemove = function (element, url) {
        var rows = element.bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
        if (rows.length == 0) {
            layer.msg("请选择要删除的数据");
            return;
        }
        layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
            btn: ['确定', '取消']
            // 按钮
        }, function () {
            var ids = new Array();
            // 遍历所有选择的行数据，取每条数据对应的ID
            $.each(rows, function (i, row) {
                ids[i] = row['id'];
            });
            $.ajax({
                type: 'POST',
                data: {
                    "ids": ids
                },
                url: url,
                success: function (r) {
                    if (r.code == 0) {
                        layer.msg(r.msg);
                        reLoad();
                    } else {
                        layer.msg(r.msg);
                    }
                }
            });
        }, function () {
        });
    };
    // tree table
    cbs.treeTable = function (element, config) {
        var _config = $.extend({}, baseconfig, config);
        initTreeConfig(element, _config);
        initConfig(element, _config);
        element.show();
        $(element).bootstrapTreeTable(_config);
        $(element).data('config', _config);
    };

    // 刷新 table
    cbs.refreshDataTable = function (element) {
        $(element).bootstrapTable('refresh');
    };

    // 刷新tree table
    cbs.refreshTreeTable = function (element) {
        var config = $(element).data('config');
        if (!!config) {
            if (!!config.paramsForm) {
                config.ajaxParams = {};
                addParams(config.paramsForm, config.ajaxParams);
            }
            $(element).bootstrapTreeTable(config);
        }
    };

    $.fn.getSelections = function (fieldName) {
        var rows = $(this).bootstrapTable('getSelections'), ids = [];
        if (!!rows && rows.length > 0) {
            $.each(rows, function (i, row) {
                ids[i] = eval('row.' + fieldName);
            });
        }
        return ids;
    };

}(jQuery, window)