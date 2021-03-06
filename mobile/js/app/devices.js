$(function() {
    // $('.devices_body').height($('html').height() - 168);
    // tabCur();
});
//是否收藏
function isLike() {
    var isLikeBtn = $('#isLike');
    $(isLikeBtn).on('click', function() {
        if ($(this).attr('data-isLike') == 0) {
            $(this).attr('data-isLike', 1);
            $(this).css('color', '#f8a766').find('i').removeClass('icon-star').addClass('icon-star2');
        } else {
            $(this).attr('data-isLike', 0);
            $(this).css('color', '#000').find('i').removeClass('icon-star2').addClass('icon-star');
        }
    })
}
isLike();

//跳转详情
function goDetial() {
    var item = $('#deviceList .dbox'),
        ctnBox = $('.main_body');
    $.each(item, function(key, data) {
        $(data).on('click', function() {
            var href = $(this).attr('data-href').split('?')[0],
                goodsId = $(this).attr('data-href').split('=')[1];
            //需要保存一个值 用于其他页面使用
            setStr('goodsId', goodsId);
            $(ctnBox).load('../html/' + href + '.html');
        })
    })
}

//获取设备列表
function getDeviceList() {
    var goodsBox = $('#deviceList'),
        goodsCtn = '';
    $.get(urlInfo() + '/goods/list?status=1&type=1', function(res) {
        $.each(res.data, function(key, data) {
            goodsCtn += '<div class="col-xs-6"><div class="dbox" data-href="devicesDc?goodsId=' + data.goodsId + '" data-id="' + data.goodsId + '">' +
                '<img src="' + imgUrl() + data.img + getShuiYin() + '" alt="' + data.goodsName + '" title="' + data.goodsName + '" />' +
                '<div class="dinfo"><p class="name">' + data.goodsName + '</p>' +
                '<p class="price">价格：<i>¥ ' + toFloat(data.goodsPrice) + '</i></p></div></div></div>';
        });
        $(goodsBox).html(goodsCtn);
        goDetial();
    }, 'json');
}
getDeviceList();

/**
 * 设备切换
 */
function tabDevices() {
    var lis = $('.condition li');
    $(lis).on('click', function() {
        $(this).addClass('cur').siblings('li').removeClass('cur');
        var typeId = $(this).attr('data-type');
        var goodsBox = $('#deviceList'),
            goodsCtn = '';
        $.get(urlInfo() + '/goods/list', {
            'status': 1,
            'type': typeId
        }, function(res) {
            $.each(res.data, function(key, data) {
                goodsCtn += '<div class="col-xs-6"><div class="dbox" data-href="devicesDc?goodsId=' + data.goodsId + '" data-id="' + data.goodsId + '">' +
                    '<img src="' + imgUrl() + data.img + getShuiYin() + '" alt="' + data.goodsName + '" title="' + data.goodsName + '" />' +
                    '<div class="dinfo"><p class="name">' + data.goodsName + '</p>' +
                    '<p class="price">价格：<i>¥ ' + toFloat(data.goodsPrice) + '</i></p></div></div></div>';
            });
            $(goodsBox).html(goodsCtn);
            goDetial();
        }, 'json');
    })
}
tabDevices();