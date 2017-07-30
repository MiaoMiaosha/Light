$(function() {
    $('.com_body').css('min-height', $('html').height() - 108);
});
//添加管理内容
function addMananger() {
    var lis = $('.pmBox .mlis'),
        dialogCtn = '<div class="dialog d_none"><div class="mask"></div>' +
        '<div class="dialogCtn trans"><div class="title"><p>添加项目管理</p></div>' +
        '<div class="body"><textarea rows="4"></textarea></div>' +
        '<div class="footer"><ul class="clearfix"><li class="f_left flis">' +
        '<button type="button" class="btnOk btnFooter">确认</button></li>' +
        '<li class="f_left flis"><button type="button" class="btnFooter cancel">取消</button></li></ul></div></div></div>';
    $.each(lis, function() {
        var _this = this;
        $(_this).on('click', '.icon-add', function() {
            var _add = this;
            $('.dialog').remove();
            $('body').append(dialogCtn);
            //取消
            $('.cancel').on('click', function() {
                $(this).parents('.dialog').addClass('d_none');
            });
            //确定
            $('.btnOk').on('click', function() {
                var tV = $(this).parents('.dialog').find('textarea');
                //已经存在条目,存在就追加
                if ($(_add).parent().next().length > 0) {
                    var textCtn = '<li><div class="ctnText clearfix"><p class="f_left">' + $(tV).val() + '</p>' +
                        '<div class="isdone f_right"><label><input type="checkbox" class="cbk">已完成</label></div></div></li>';
                    $(_add).parent().next().find('ul').append(textCtn);
                } else { //没有存在条目 不存在就创建 连同按钮一起加入
                    var textCtn = '<div class="pmCtn p_rel"><ul><li><div class="ctnText clearfix"><p class="f_left">' + $(tV).val() + '</p>' +
                        '<div class="isdone f_right"><label><input type="checkbox" class="cbk">已完成</label></div></div></li></ul></div>' +
                        '<div class="saveBox"><button type="button" class="btn btnAdd btnSave">确认修改</button></div>';
                    $(_this).append(textCtn);
                }
                $(this).parents('.dialog').addClass('d_none');
            });
            dialogAnimation();
        })
    })
}
addMananger()