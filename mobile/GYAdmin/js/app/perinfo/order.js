$(function() {
    $('.order_body').css('min-height', $('html').height() - 48);
});
//切换类型
function getTypeList() {
    var typelist = $('.oplist .oplis');
    $.each(typelist, function() {
        $(this).on('click', function() {
            $(this).addClass('cur').siblings('li').removeClass('cur');
        })
    })
}
getTypeList();