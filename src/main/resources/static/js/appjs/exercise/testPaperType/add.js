+function ($, window, undefined) {

    'use strict';
    var id = 'undefined';
    $(function () {
        validateRule();

        //初始化上传文件
        initUploadFile();
    });

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
                url: _ctx + '/exercise/testPaperType/save', //上传接口
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
                    parent.reLoad();
                    parent.layer.close(parent.layer.getFrameIndex(window.name));
                },
                error: function (r) {
                    layer.msg(r.msg);
                }
            });
        });
    }

    $.validator.setDefaults({
        submitHandler: function () {
            save();
        }
    });

    function save() {
        var hidId = Math.round(Math.random() * 100000000);
        $('#id').val(hidId);
        id = hidId;
        $('#btn_new').attr("disabled", false);
        var _data = $('#signupForm').serialize(), _url = _ctx + "/exercise/testPaperType/saveType";
        cbs.httpclient.post(_url, _data, function (data) {
            if (data.code == 0) {
                parent.layer.msg("保存试卷成功，请导入相应试题");
                // parent.reLoad();
                $('#saveBut').attr("disabled", true);
                // parent.layer.close(parent.layer.getFrameIndex(window.name));
            } else {
                parent.layer.alert(data.msg)
            }
        });

    }

    function validateRule() {
        var icon = "<i class='fa fa-times-circle'></i> ";
        cbs.validate("#signupForm", {
            rules: {
                exerciseTitle: {
                    required: true
                },
                exerciseTime: {
                    required: true
                },
                exerciseNumber: {
                    required: true
                },
                exerciseType: {
                    required: true
                },
                exerciseMode:{
                    required: true
                }
            },
            messages: {
                exerciseTitle: {
                    required: icon + "试卷题目不能为空"
                },
                exerciseTime: {
                    required: icon + "试题时间不能为空"
                },
                exerciseNumber: {
                    required: icon + "试卷总分不能为空"
                },
                exerciseType: {
                    required: icon + "题库类型不能为空"
                },
                exerciseMode: {
                    required: icon + "试卷模型不能为空"
                }
            }
        })
    }
}(jQuery, window)