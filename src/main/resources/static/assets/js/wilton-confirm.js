/**
 *	boostrap-confirm.js v1.0  author: fengzy
 */
;(function($,window,document,undefined){
    // var jconfirm, Jconfirm;

    //对外提供的方法
    $.alert = function (options) {
        return jconfirm(options);
    };
    $.confirm = function (options) {
        options.cancelButton = true;
        options.confirmButton = true;
        return jconfirm(options);
    };

    //参数配置
    var jconfirm = function (options) {
        var options = $.extend({}, jconfirm.defaults, options);
        return new Jobj(options);
    };

    //构造对象
    var Jobj = function (options) {
        $.extend(this, options);
        this.init();
    };

    //构造对象方法
    Jobj.prototype = {
        init: function () {
            var that = this;
            this.buildHTML();
            this.bindEvents();
        },
        buildHTML: function () {
            var that = this;
            $(this.template).appendTo(this.container)
        },
        bindEvents: function () {
            var that = this;
            $("#confirmModal .modal-title").html(this.title);
            $("#confirmModal .jconfrim-message").html(this.content);
            $("#confirmModal #jconfrimId").val(this.id);
            $('#jconfrimName').val(this.jconfrimName);

            //显示btn
            if(that.cancelButton){
                if(this.content != 'false'){
                    $("#confirmModal .jconfrim-input").hide();
                    $("#confirmModal .jconfrim-alert").show();
                }else{
                    $("#confirmModal .jconfrim-alert").hide();
                    $("#confirmModal .jconfrim-input").show();
                }
                $("#confirmModal .jconfrim-cancel").show();
            }else{
                $("#confirmModal .jconfrim-input").show();
                $("#confirmModal .jconfrim-cancel").hide();
            }

            //自动关闭
            if(this.autoClose){
                this._startCountDown(this.autoClose);
            }
            //回调
            if (that.confirmButton) {

                $("#confirmModal .jconfrim-submit").unbind( "click" );
                $('#confirmModal .jconfrim-submit').click(function (e) {
                    e.preventDefault();
                    var r = that.confirm();
                    if (typeof r === 'undefined' || r){
                        that.close();
                    }
                });
            }else{
                $("#confirmModal .jconfrim-submit").unbind( "click" );
            }
            $('#confirmModal').modal('show');

        },
        _startCountDown: function (second) {
            var that = this;
            var time = second*1000;
            this.title = '信息提示'
            this.content = 'false'
            this.interval = setInterval(function () {
                $('#confirmModal .jconfrim-submit').trigger('click');
                clearInterval(that.interval);
            }, time);
        },
        close: function () {
            this.title = '信息提示'
            this.content = 'false'
            $('#confirmModal').modal('hide');
        }
    };

    //设置默认参数
    jconfirm.defaults = {
        template:
            '<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">\n' +
            '                        <div class="modal-dialog" role="document">\n' +
            '                            <div class="modal-content">\n' +
            '                                <div class="modal-header" style="border-bottom:none">\n' +
            '                                    <h5 class="modal-title" id="renameModalLabel" style="font-size: 18px; margin-left: 10px;">重命名文件夹</h5>\n' +
            '                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
            '                                        <span aria-hidden="true">&times;</span>\n' +
            '                                    </button>\n' +
            '                                </div>\n' +
            '                                <div class="jconfrim-alert">' +
            '                                   <div class="col-md-12 jconfrim-message" style="margin-left: 35px; font-size: 18px; font-weight: 300;"></div>' +
            '                                </div>' +
            '                                <div class="jconfrim-input">\n' +
            '                                    <div class="form-group col-md-12">\n' +
            '                                        <input type="text" hidden id="jconfrimId">\n' +
            '                                        <input type="text" autocomplete="off" class="form-control" id="jconfrimName">\n' +
            '                                    </div>\n' +
            '                                </div>\n' +
            '                                <div class="modal-footer" style="border-top:none">\n' +
            '                                    <button type="button" class="btn btn-secondary jconfrim-cancel" data-dismiss="modal" style="width: 25%;">取消</button>\n' +
            '                                    <button type="button" class="btn btn-primary jconfrim-submit" data-dismiss="modal" style="width: 25%;">确认</button>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>',
        title: '信息提示',
        id: 1,
        content: 'false',
        jconfrimName: '',
        icon: '',
        container: 'body',
        autoClose:'',
        confirmButton:false,
        cancelButton:false,
        confirm: function () {
        },
        cancel: function () {
        },
        contentLoaded: function () {
        }
    }


})(jQuery,window,document);

$("#button1").click(function(){
    $.alert({
        content: '请输入用户名'
    });
});

$("#button2").click(function(){

    $.confirm({
        content: '确认要删除吗？',
        confirm: function () {
            alert('删除');
        }
    });
});
