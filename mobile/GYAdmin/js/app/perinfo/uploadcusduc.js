$(function() {
    $('.com_body').css('min-height', $('html').height() - 108);
});
//移除/删除条目
function removeItem() {
    var lis = $('.ucdBox .ulis');
    $.each(lis, function() {
        var _this = this;
        $(_this).on('click', '.close', function() {
            $(_this).remove();
        })
    })
}
removeItem();