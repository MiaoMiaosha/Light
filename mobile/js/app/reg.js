$(function() {
    $('.reg_body').css('min-height', $('html').height() - 168);
});
//完善信息
function getCompleteInfo() {
    var regBtn = $('#regNow');
    $(regBtn).on('click', function() {
        $('.main_body').load('../html/reg_cpl.html');
    })
}
getCompleteInfo();