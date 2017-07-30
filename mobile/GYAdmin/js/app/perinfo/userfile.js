$(function() {
    $('.com_body').css('min-height', $('html').height() - 48);
});
//伸缩展开内容
function toggleExp() {
    var lis = $('.ufileBox .mlis');
    $.each(lis, function() {
        //程序运行的时候先自行判断列表是否存在着已经展开的 如果有的话将展开列表并进行对应的操作
        if ($(this).attr('data-open') == 1) {
            $(this).find('.icon-right').css('transform', 'rotate(90deg)');
            $(this).find('.ufileCtn').removeClass('d_none');
        } else {
            $(this).find('.icon-right').css('transform', 'rotate(0deg)');
            $(this).find('.ufileCtn').addClass('d_none');
        }
        $(this).on('click', function() {
            var isOpen = $(this).attr('data-open'),
                ar = $(this).find('.icon-right');
            if (isOpen == 0) { //未展开
                $(this).attr('data-open', 1); //变成展开状态
                $(ar).css('transform', 'rotate(90deg)');
                //显示文本
                $(this).find('.ufileCtn').removeClass('d_none').css('opacity', 0).animate({}, function() {
                    $(this).css({ 'height': 'auto', 'opacity': 1 });
                })
            } else { //展开
                $(this).attr('data-open', 0); //变成未展开状态
                $(ar).css('transform', 'rotate(0deg)');
                //隐藏文本
                $(this).find('.ufileCtn').animate({
                    'height': 0,
                    'opacity': 0
                }, function() {
                    $(this).addClass('d_none');
                })
            }
        })
    })
}
toggleExp();