$(function() {
    $('.com_body').css('min-height', $('html').height() - 108);
});

//点击add 弹出添加帖子内容的弹窗
//Ps:可能需要再添加 帖子内容的输入框
function showDialog() {
    var dialogCtn = '<div class="dialog d_none"><div class="mask"></div>' +
        '<div class="dialogCtn trans"><div class="title"><p>市场发帖</p></div>' +
        '<div class="body"><textarea rows="4"></textarea></div>' +
        '<div class="footer"><ul class="clearfix"><li class="f_left flis">' +
        '<button type="button" class="btnOk btnFooter">确认</button></li>' +
        '<li class="f_left flis"><button type="button" class="btnFooter cancel">取消</button></li></ul></div></div></div>';
    var addlist = $('.pmarketBox');
    $(addlist).on('click', '.icon-add', function() {
        var _lithis = this;
        $('.dialog').remove();
        $('body').append(dialogCtn);
        var pli = $(_lithis).parents('.mlis');
        //取消
        $('.cancel').on('click', function() {
            $(this).parents('.dialog').addClass('d_none');
        });
        //确定
        $('.btnOk').on('click', function() {
            var tV = $(this).parents('.dialog').find('textarea'),
                textCtn = '<li>' + $(tV).val() + '</li>';
            $(pli).find('.pmarketCtn').append(textCtn);
            $(this).parents('.dialog').addClass('d_none');
        });
        dialogAnimation();
    });
}
showDialog();