$(function() {
    $('.main_body').load('../perInfo/perinfo.html'); //默认加载home.html
});
//个人中心的页面切换
function perPageCur() {
    var boxCtn = $('.main_body'),
        hrefLi = $('.perInfo_body .hlis'),
        hrefLi2 = $('.perInfo_body .oplis');
    $.each(hrefLi, function(key, data) {
        $(data).on('click', function() {
            var targetHref = $(this).attr('data-href');
            if (targetHref == '' || targetHref == undefined) {
                $(boxCtn).load('../perInfo/perinfo.html');
            } else {
                $(boxCtn).load('../perInfo/' + targetHref + '.html');
                $('#titleName').text($(this).attr('data-title'));
            }
        })
    });
    $.each(hrefLi2, function(key, data) {
        $(data).on('click', function() {
            var targetHref = $(this).attr('data-href');
            if (targetHref == '' || targetHref == undefined) {
                $(boxCtn).load('../perInfo/perinfo.html');
            } else {
                $(boxCtn).load('../perInfo/' + targetHref + '.html');
                $('#titleName').text($(this).attr('data-title'));
            }
        })
    })
}

//返回个人中心
function backPerInfo() {
    var backBtn = $('#backper');
    $(backBtn).on('click', function() {
        $('.main_body').load('../perInfo/perinfo.html');
        $('#titleName').text('个人中心');
    });
}
backPerInfo();