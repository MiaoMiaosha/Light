$(function() {
    $('.login_body').css('height', $('html').height() - 168);
});
//注册
function reg() {
    var regBox = $('#regbox');
    $(regBox).on('click', '.loginli', function() {
        var hash = $(this).find('a').attr('data-href');
        $('.main_body').load('../html/' + hash + '.html');
    })
}
reg();

//测试登录到个人中心
function logined() {
    $('#enter').on('click', function() {
        if ($('#uname').val() != '' && $('#upsd').val() != '') {
            window.location.href = '../html/perInfo/index.html';
        }
    });
}
logined();