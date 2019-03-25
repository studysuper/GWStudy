+function ($, window, undefined) {
    'use strict';

    var prefix = "/exercise/testPaperType", sparr = {};

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
        cbs.dataTable($("#testPaperTypeTable"), {
            method: 'get',
            url: prefix + "/list",
            pageSize: 10,
            paramsForm: $("#searchForm"),
            onDblClickRow: function (row, $element) {
                edit(row.id);
            }
        });
    };

    window.reLoad = function () {
        $('#testPaperTypeTable').bootstrapTable('refresh');
    };

    window.add = function () {
        layer.open({
            type: 2,
            title: '增加',
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

    function remove(id) {
        cbs.remove(prefix + '/remove', id);
    }

    function batchRemove() {
        cbs.batchRemove($("#testPaperTypeTable"), prefix + '/batchRemove');
    }
}(jQuery, window)