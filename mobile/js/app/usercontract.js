$(function() {
    $('.com_body').css('min-height', $('html').height() - 108);
});
//切换类型
function getTypeCtn() {
    var lis = $('.ucType .uclis');
    $.each(lis, function() {
        $(this).on('click', function() {
            $(this).addClass('cur').siblings('li').removeClass('cur');
        })
    })
}
getTypeCtn();