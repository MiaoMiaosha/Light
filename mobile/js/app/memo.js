$(function() {
    $('.memo_body').css('min-height', $('html').height() - 108);
});
//点击add 弹出添加备忘的弹窗
function showDialog() {
    var dialogCtn = '<div class="dialog d_none"><div class="mask"></div>' +
        '<div class="dialogCtn trans"><div class="title"><p>添加备忘录</p></div>' +
        '<div class="body"><textarea rows="4"></textarea></div>' +
        '<div class="footer"><ul class="clearfix"><li class="f_left flis">' +
        '<button type="button" class="btnOk btnFooter">确认</button></li>' +
        '<li class="f_left flis"><button type="button" class="btnFooter cancel">取消</button></li></ul></div></div></div>';
    var addlist = $('.memoBox');
    $(addlist).on('click', '.icon-add', function() {
        var _lithis = this;
        $('.dialog').remove();
        $('body').append(dialogCtn);
        //判断是否已经存在备忘
        var pli = $(_lithis).parents('.mlis');
        // if ($(pli).find('.memoCtn').length > 0) {
        //     var oldValue = $(pli).find('.ctnText').text();
        //     $('.dialog').find('textarea').val(oldValue);
        // }
        //取消
        $('.cancel').on('click', function() {
            $(this).parents('.dialog').addClass('d_none');
        });
        //确定
        $('.btnOk').on('click', function() {
            var tV = $(this).parents('.dialog').find('textarea');
            var textCtn = '<div class="memoCtn p_rel">' +
                '<div class="ctnText">' + $(tV).val() + '</div><div class="ctnOp">' +
                '<ul class="clearfix"><li class="f_left colis"><i class="icon-editor"></i></li>' +
                '<li class="f_left colis"><i class="icon-close"></i></li></ul></div></div>';
            $(pli).append(textCtn);
            removeNotes();
            $(this).parents('.dialog').addClass('d_none');
        });
        dialogAnimation();
    });
}
showDialog();

//移除/编辑备忘
function removeNotes() {
    var mlis = $('.memoBox .mlis');
    if ($(mlis).find('.memoCtn').length > 0) {
        var close = $(mlis).find('.icon-close'),
            edit = $(mlis).find('.icon-editor');
        //删除
        $.each(close, function() {
            $(this).on('click', function() {
                $(this).parents('.memoCtn').remove();
            })
        });
        //修改
        var dialogCtn = '<div class="dialog d_none"><div class="mask"></div>' +
            '<div class="dialogCtn trans"><div class="title"><p>添加备忘录</p></div>' +
            '<div class="body"><textarea rows="4"></textarea></div>' +
            '<div class="footer"><ul class="clearfix"><li class="f_left flis">' +
            '<button type="button" class="btnOk btnFooter">确认</button></li>' +
            '<li class="f_left flis"><button type="button" class="btnFooter cancel">取消</button></li></ul></div></div></div>';
        $.each(edit, function() {
            $(this).on('click', function() {
                var _edit = this;
                $('.dialog').remove();
                $('body').append(dialogCtn);
                var oldValue = $(_edit).parents('.memoCtn').find('.ctnText');
                $('.dialog').find('textarea').val($(oldValue).text());
                //取消
                $('.cancel').on('click', function() {
                    $(this).parents('.dialog').addClass('d_none');
                });
                //确定
                $('.btnOk').on('click', function() {
                    var tV = $(this).parents('.dialog').find('textarea');
                    $(oldValue).text($(tV).val());
                    $(this).parents('.dialog').addClass('d_none');
                });
                dialogAnimation();
            })
        });
    }
}
removeNotes();