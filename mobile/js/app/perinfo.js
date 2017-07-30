$(function() {
    $('.perInfo_body').css('min-height', $('html').height() - 108);
    // perPageCur();
    // curPage();
});

//获取个人信息
function getPerInfo() {
    // <img src="../../images/user1.jpg" alt="" title="" />
    var uInfo = '',
        uBox = $('#s_info');
    // && getCookie('wxUserId') != 'undefined'
    if (getCookie('wxUserId').length > 0 && getCookie('wxUserId') != 'undefined') {
        var userId = getCookie('wxUserId');
        $.get(urlInfo() + '/wxuser/detail', {
            'userId': userId
        }, function(res) {
            uInfo = '<img src="' + res.data.headimgurl + '" alt="' + res.data.userName + '" title="" />' +
                '<p class="name">' + res.data.userName + '</p><p><span>' + res.data.country + '</span>' +
                '<span> ' + res.data.province + '</span><span> ' + res.data.city + '</span></p>';
            $(uBox).html(uInfo);
        }, 'json');
    } else {
        alert('获取用户信息失败');
    }
}
getPerInfo();


//根据选择的内容 跳转到对应的页面
//页面切换
function curPage() {
    var boxCtn = $('.main_body'),
        hrefLi = $('.s_oplist .oplis'),
        nowHref = window.location.href;
    if (nowHref.split('#').length == 1) {
        $(boxCtn).load('../html/home.html');
    } else if (nowHref.split('#').length == 2) {
        $(boxCtn).load('../html/' + nowHref.split('#')[1] + '.html');
    }
    $.each(hrefLi, function(key, data) {
        $(data).on('click', function() {
            var targetHref = $(this).attr('data-href'),
                targetType = $(this).attr('data-type'),
                linkHref = $(this).attr('data-go');
            if (typeof targetHref == 'undefined') {
                if (typeof linkHref != 'undefined') {
                    location.href = '../../' + linkHref;
                } else {
                    // $(boxCtn).load('../html/home.html');
                    if (window.location.href.split('perInfo/index.html').length > 1) {
                        location.href = "http://tobyhan.cn/mobile/html/perInfo/index.html";
                    }
                }
            } else {
                if (targetHref.split('/').length == 2) {
                    if (typeof targetType != 'undefined') {
                        setCookie('targetType', targetType, 1);
                    }
                    $(boxCtn).load('../' + targetHref + '.html');
                } else if (targetHref.split('/').length == 1) {
                    window.location.href = '../index.html#' + targetHref;
                }
            }
        })
    })
}
curPage();