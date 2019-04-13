+function ($, window, undefined) {

    'use strict';

    $(function () {
        validateRule();
    });

    $.validator.setDefaults({
        submitHandler: function () {
            save();
        }
    });

    function save() {
        var _data = $('#signupForm').serialize(), _url = _ctx + "/exercise/testPaperType/saveType";
        cbs.httpclient.post(_url, _data, function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                parent.layer.close(parent.layer.getFrameIndex(window.name));
            } else {
                parent.layer.alert(data.msg)
            }
        });

    }

    function validateRule() {
        var icon = "<i class='fa fa-times-circle'></i> ";
        cbs.validate("#signupForm", {
            rules: {},
            messages: {}
        })
    }
}(jQuery, window)