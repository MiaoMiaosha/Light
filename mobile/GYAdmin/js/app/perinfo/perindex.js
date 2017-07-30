$(function() {
    $('.main_body').load('../perInfo/perinfo.html'); //默认加载home.html
    removeStr('historyArr');
});
//个人中心的页面切换
function perPageCur() {
    var boxCtn = $('.main_body'),
        hrefLi = $('.perInfo_body .hlis'),
        // hrefLi2 = $('.perInfo_body .oplis'),
        // dataListArr = ['staff-table', 'customer-table', 'project-table', 'shouru-table', 'baoxiao-table', 'fenhong-table'],
        // dataListArr = $('#rType').attr('data-arr').split(','),
        dataChooseArr = ['addbannerpic', 'addadpic', 'addzzpic'];
    var href = window.location.href;
    if (href.split('#').length > 1 && href.split('#')[1] != '') {
        var target = href.split('#')[1].split('?');
        var historyArr = [{ 'hpage': 'perinfo' }, { 'hpage': 'usercontract' }];
        historyArr = JSON.stringify(historyArr);
        setStr('historyArr', historyArr);
        $(boxCtn).load('../perInfo/' + target[0] + '.html');
    }
    $.each(hrefLi, function(key, data) {
        $(data).on('click', function() {
            if (typeof $(this).attr('data-list') != 'undefined') {
                var _this = this;
                var list = $(_this).attr('data-list').split(','),
                    litem = '',
                    dataListArr = $('#rType').attr('data-arr').split(',');
                for (var i in list) {
                    litem += '<li class="dlis" data-target="' + dataListArr[i] + '">' + list[i] + '</li>';
                }
                $('.dialog1').remove();
                var dialogCtn = '<div class="dialog1 d_none"><div class="mask"></div>' +
                    '<div class="dialogCtn trans" style="height:auto">' +
                    '<ul>' + litem +
                    '</ul></div></div>';
                $('body').append(dialogCtn);
                getTable();
                dialogAnimation();
            } else if (typeof $(this).attr('data-choose') != 'undefined') {
                var _this = this;
                var list = $(_this).attr('data-choose').split(','),
                    litem = '';
                for (var i in list) {
                    litem += '<li class="clis" data-target="' + dataChooseArr[i] + '">' + list[i] + '</li>';
                }
                $('.dialog1').remove();
                var dialogCtn = '<div class="dialog1 d_none"><div class="mask"></div>' +
                    '<div class="dialogCtn trans" style="height:auto">' +
                    '<ul>' + litem +
                    '</ul></div></div>';
                $('body').append(dialogCtn);
                getPage();
                dialogAnimation();
            } else if (typeof $(this).attr('data-go') != 'undefined') {
                var _this = this;
                location.href = '../perInfo/' + $(_this).attr('data-go') + '.html';
            } else {
                var targetHref = $(this).attr('data-href');
                if (targetHref == '' || targetHref == undefined) {
                    $(boxCtn).load('../perInfo/perinfo.html');
                } else {
                    var historyArr = [{ 'hpage': 'perinfo' }];
                    historyArr = JSON.stringify(historyArr);
                    setStr('historyArr', historyArr);
                    $(boxCtn).load('../perInfo/' + targetHref + '.html');
                    $('#titleName').text($(this).attr('data-title'));
                }
            }
        })
    });
    // $.each(hrefLi2, function(key, data) {
    //     $(data).on('click', function() {
    //         var targetHref = $(this).attr('data-href');
    //         if (targetHref == '' || targetHref == undefined) {
    //             $(boxCtn).load('../perInfo/perinfo.html');
    //         } else {
    //             $(boxCtn).load('../perInfo/' + targetHref + '.html');
    //             $('#titleName').text($(this).attr('data-title'));
    //         }
    //     })
    // })
}

//返回个人中心
function backPerInfo() {
    var backBtn = $('#backper');
    $(backBtn).on('click', function() {
        var href = window.location.href;
        if (href.split('#').length > 1 && href.split('#')[1] != '') {
            location.hash = "";
        }
        if (getStr('historyArr') != '') {
            var historyArr = '';
            historyArr = getStr('historyArr');
            historyArr = JSON.parse(historyArr);
            $('.main_body').load(historyArr[historyArr.length - 1].hpage + '.html');
            historyArr.pop(); //删除最后一个对象
            historyArr = JSON.stringify(historyArr);
            setStr('historyArr', historyArr);
        } else {
            location.href = 'http://tobyhan.cn/mobile/GYAdmin/html/perInfo/index.html';
        }
        // $('#titleName').text('个人中心');
    });
}
backPerInfo();

//点击弹窗条目条状到对应的页面
function getTable() {
    var state = $('.dialog1 .dlis');
    $(state).on('click', function() {
        var target = $(this).attr('data-target');
        window.location.href = "../" + target + '.html';
    })
}

function getPage() {
    var state = $('.dialog1 .clis');
    $(state).on('click', function() {
        var target = $(this).attr('data-target');
        $(this).parents('.dialog1').remove();
        $('.main_body').load(target + '.html');
        // window.location.href = target + '.html';
    })
}