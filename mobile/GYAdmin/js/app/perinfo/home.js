//获取首页案例市场列表
function getMarketList() {
    var caseBox = $('#marketList'),
        caseCtn = '';
    // $.ajax({
    //     url: urlInfo() + '/market/list',
    //     dataType: 'json',
    //     type: 'GET',
    //     success: function(res) {
    //         $.each(res.data, function(key, data) {
    //             alert(data.mid);
    //         })
    //     },
    //     error: function(XMLHttpRequest, textStatus, errorThrown) {
    //         alert(XMLHttpRequest.status);
    //         alert(XMLHttpRequest.readyState);
    //         alert(textStatus);
    //     }
    // });
    // var xhr = new XMLHttpRequest(),
    //     xhrget = new XMLHttpRequest();
    // xhr.onreadystatechange = function() {
    //     if (xhr.readyState == 4) {
    //         // var upJson = JSON.parse(xhr.responseText);
    //         var upJson = eval('(' + xhr.responseText + ')');;
    //         console.log(upJson.data[0].mid);
    //         alert(upJson.data[0].mid);
    //     }
    // };
    // xhr.open("GET", urlInfo() + '/market/list', true);
    // xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    // xhr.send(null);
    $.get(urlInfo() + '/market/list', function(res) {
        $.each(res.data, function(key, data) {
            //获取前三个市场数据
            if (key < 3) {
                // alert(data.mid);
                caseCtn += '<div class="col-xs-4"><div class="casebox" data-id="' + data.mid + '" data-userid="' + data.userId + '">';
                caseCtn += '<img src="' + imgUrl() + data.imgUrl + getShuiYin()+'" alt="' + data.marketName + '" title="' + data.marketName + '" />';
                caseCtn += '<div class="introbox"><p class="name">' + data.marketName +
                    '</p><p class="com_p">面积：<i class="com_num">' + data.area + '</i></p>' +
                    '<p class="com_p">电话：<i class="com_num">' + data.contactMobile + '</i></p></div></div></div>';
            }
        });
        $(caseBox).html(caseCtn);
    }, 'json');
}
getMarketList();
//获取首页设备列表
function getGoodsList() {
    var goodsBox = $('#goodsList'),
        goodsCtn = '';
    $.get(urlInfo() + '/goods/list', function(res) {
        $.each(res.data, function(key, data) {
            //首页取两条设备信息
            if (key < 2) {
                goodsCtn += '<div class="col-xs-6"><div class="deviceItem" data-id="' + data.goodsId + '">' +
                    '<img src="' + imgUrl() + data.img + getShuiYin()+'" alt="' + data.goodsName + '" title="' + data.goodsName + '" />' +
                    '<div class="infobox"><p class="name">' + data.goodsName + '</p>' +
                    '<p class="price">价格：<i>¥ ' + data.goodsPrice + '</i></p></div></div></div>';
            }
        });
        $(goodsBox).html(goodsCtn);
    }, 'json');
}
getGoodsList();

//获取招商案例
function getCaseList() {
    var caseBox = $('#caseList'),
        caseCtn = '';
    $.get(urlInfo() + '/case/list', function(res) {
        $.each(res.data, function(key, data) {
            //首页取四条招商案例信息
            if (key < 4) {
                caseCtn += '<div class="col-xs-3"><div class="zsbox" data-id="' + data.cid + '" data-userid="' + data.userId + '">' +
                    '<img src="' + imgUrl() + data.imgUrl + getShuiYin()+'" alt="' + data.caseName + '" title="' + data.caseName + '" />' +
                    '<p>' + data.caseName + '</p>' +
                    '</div></div>';
            }
        });
        $(caseBox).html(caseCtn);
    }, 'json');
}
getCaseList();

//首页内容跳转
function goHref() {
    var lis = $('#homeMenu .mItem'),
        ctnBox = $('.main_body');
    $.each(lis, function(key, data) {
        $(data).on('click', function() {
            var href = $(this).attr('data-href');
            $(ctnBox).load('../html/' + href + '.html');
        })
    })
}
goHref();

$(function() {
    $('#moreCase').on('click', function() {
        $('.main_body').load('../html/case.html');
    });
    $('#moreDevice').on('click', function() {
        $('.main_body').load('../html/devices.html');
    });
})