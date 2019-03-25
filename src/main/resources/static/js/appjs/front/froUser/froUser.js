+function ($, window, undefined) {

    'use strict';

    var prefix = "/front/froUser", sparr = {};

    $(function () {
        $("input[name='s_permission']").each(function (index, element) {
            sparr[element.value] = true;
        });//设置权限
        load();
        //为按钮添加事件
        $("#btn_search").on("click", reLoad);
        $("#btn_new").on("click", add);
        $("#btn_batch_remove").on("click", batchRemove);
    });

    window.load = function () {
        cbs.dataTable($("#exampleTable"), {
            method: 'get',
            url: prefix + "/list",
            pageSize: 10,
            paramsForm: $("#searchForm"),
            onDblClickRow: function (row, $element) {
                // edit(row.id);
            },
            formatters: {
                id: function (value, row, index) {
                    var a = '<a class="btn btn-primary btn-sm ' + sparr.s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                        + row.id + '\')"><i class="fa fa-edit"></i></a> ';
                    var b = '<a class="btn btn-warning btn-sm ' + sparr.s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                        + row.id + '\')"><i class="fa fa-remove"></i></a> ';
                    return a + b;
                }
            }
        });
    };

    window.reLoad = function () {
        $('#exampleTable').bootstrapTable('refresh');
    }

    window.add = function () {
        layer.open({
            type: 2,
            title: '添加用户',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '490px'],
            content: prefix + '/add' // iframe的url
        });
    }

    window.edit = function (id) {
        layer.open({
            type: 2,
            title: '编辑',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '490px'],
            content: prefix + '/edit/' + id // iframe的url
        });
    }

    window.remove = function (id) {
        cbs.remove(prefix + '/remove', id);
    }

    window.batchRemove = function () {
        cbs.batchRemove($('#exampleTable'), prefix + '/batchRemove');
    }
}(jQuery, window)