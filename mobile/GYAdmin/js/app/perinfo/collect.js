$(function() {
    $('.collect_body').css('min-height', $('html').height() - 48);
});
//删除收藏
function delCollect() {
    var colList = $('.collectList .clis');
    $.each(colList, function() {
        $(this).on('click', '.close', function() {
            $(this).parent().remove();
        })
    })
}
delCollect();