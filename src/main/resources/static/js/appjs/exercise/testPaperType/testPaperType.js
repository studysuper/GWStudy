+function ($, window, undefined) {
    'use strict';

    var prefix = "/exercise/testPaperType", sparr = {};
    var id = 'undefined';
    $(function () {
        $("input[name='s_permission']").each(function (index, element) {
            sparr[element.value] = true;
        });//设置权限
        load();
        //为按钮添加事件
        $("#btn_search").on("click", reLoad);
        $("#btn_add").on("click", add);
        $("#btn_batch_remove").on("click", batchRemove);
        $("#btn_new").on("click", refreshNew);
        $("#down_file").on("click", downFile);

        //初始化上传文件
        initUploadFile();
    });
    
    function downFile() {
        var url = _ctx + "/api/testpaper/createTestPaper";
        $.ajax({
            type: 'POST',
            data: {
                "id": "31d54cafd95c4eb5a7abaa3186678b2f"
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

    }

    function refreshNew() {
        var rows = $("#testPaperTypeTable").bootstrapTable('getSelections');
        if (rows != 0) {
            id = rows[0].id;
        }
    }

    /**
     * @Author ZQ
     * @Description //导入excel文档
     * @Date 2019/3/26 18:57
     * @Param
     * @return
     **/
    function initUploadFile() {
        layui.use('upload', function () {
            var upload = layui.upload;
            //执行实例
            var uploadInst = upload.render({
                elem: '#btn_new', //绑定元素
                url: prefix + '/save', //上传接口
                size: 1000,
                accept: 'file',
                before: function (input) {
                    var data = {};
                    data.id = id;
                    this.data = data;
                },
                done: function (r) {
                    layer.msg(r.msg);
                    // app.getData();
                    reLoad();
                },
                error: function (r) {
                    layer.msg(r.msg);
                }
            });
        });
    }

    window.load = function () {
        cbs.dataTable($("#testPaperTypeTable"), {
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
                        + row.topicId + '\')"><i class="fa fa-remove"></i></a> ';
                    return b;
                }
            }
        });
    };

    window.reLoad = function () {
        $('#testPaperTypeTable').bootstrapTable('refresh');
    };

    window.add = function () {
        var page = layer.open({
            type: 2,
            title: '增加',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '490px'],
            content: prefix + '/add' // iframe的url
        });
        // layer.full(page);
    }

    window.edit = function (id) {
        var page = layer.open({
            type: 2,
            title: '编辑',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '490px'],
            content: prefix + '/edit/' + id // iframe的url
        });
        layer.full(page);

    }

    window.remove = function (id) {
        cbs.remove(prefix + '/remove', id);
    }

    window.batchRemove = function () {
        cbs.batchRemove($("#testPaperTypeTable"), prefix + '/batchRemove');
    }
}(jQuery, window)