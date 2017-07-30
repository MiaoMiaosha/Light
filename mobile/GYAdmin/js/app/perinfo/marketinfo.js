//添加活动内容
function addActivity() {
    var edit = $('.basePart .edit'),
        dialogCtn = '<div class="dialog1 d_none"><div class="mask"></div>' +
        '<div class="dialogCtn trans"><div class="title"><p>添加项目管理活动内容</p></div>' +
        '<div class="body"><textarea rows="4"></textarea></div>' +
        '<div class="footer"><ul class="clearfix"><li class="f_left flis">' +
        '<button type="button" class="btnOk btnFooter">确认</button></li>' +
        '<li class="f_left flis"><button type="button" class="btnFooter cancel">取消</button></li></ul></div></div></div>';
    $(edit).on('click', function(e) {
        var _this = this;
        $('.dialog1').remove();
        $('body').append(dialogCtn);
        //取消
        $('.cancel').on('click', function() {
            $(this).parents('.dialog1').addClass('d_none');
        });
        //确定
        $('.btnOk').on('click', function() {
            var tV = $(this).parents('.dialog1').find('textarea');
            $(_this).parent().siblings('p').text($(tV).val());
            $(this).parents('.dialog1').addClass('d_none');
        });
        dialogAnimation();
    })
}
addActivity();