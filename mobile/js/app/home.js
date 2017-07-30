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
                caseCtn += '<img src="' + imgUrl() + getFirstImg(data.imgUrl) + '" alt="' + data.marketName + '" title="' + data.marketName + '" />';
                caseCtn += '<div class="introbox"><p class="name">' + data.marketName +
                    '</p><p class="com_p">面积：<i class="com_num">' + data.area + '平米</i></p>' +
                    '<p class="com_p">电话：<i class="com_num">' + data.contactMobile + '</i></p></div></div></div>';
            }
        });
        $(caseBox).html(caseCtn);
    }, 'json');
}
// getMarketList();
//获取首页设备列表
function getGoodsList() {
    var goodsBox = $('#goodsList'),
        goodsCtn = '';
    $.get(urlInfo() + '/goods/list', function(res) {
        $.each(res.data, function(key, data) {
            //首页取两条设备信息
            if (key < 2) {
                goodsCtn += '<div class="col-xs-6"><div class="deviceItem" data-id="' + data.goodsId + '">' +
                    '<img src="' + imgUrl() + getFirstImg(data.img) + '" alt="' + data.goodsName + '" title="' + data.goodsName + '" />' +
                    '<div class="infobox"><p class="name">' + data.goodsName + '</p>' +
                    '<p class="price">价格：<i>¥ ' + data.goodsPrice + '</i></p></div></div></div>';
            }
        });
        $(goodsBox).html(goodsCtn);
    }, 'json');
}
// getGoodsList();

//获取招商案例
function getCaseList() {
    var caseBox = $('#caseList'),
        caseCtn = '';
    $.get(urlInfo() + '/case/list', function(res) {
        $.each(res.data, function(key, data) {
            //首页取四条招商案例信息
            if (key < 4) {
                caseCtn += '<div class="col-xs-3"><div class="zsbox" data-id="' + data.cid + '" data-userid="' + data.userId + '">' +
                    '<img src="' + imgUrl() + getFirstImg(data.imgUrl) + '" alt="' + data.caseName + '" title="' + data.caseName + '" />' +
                    '<p>' + data.caseName + '</p>' +
                    '</div></div>';
            }
        });
        $(caseBox).html(caseCtn);
    }, 'json');
}
// getCaseList();

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
// goHref();

/**
 * 根据以上的内容进行修改 不使用跳到对应的页面 统一进入case.html
 * 但是要根据内容切换
 * 2017年3月2日10:16:38
 */
function goCase() {
    var lis = $('#homeMenu .mItem'),
        ctnBox = $('.main_body');
    $.each(lis, function(key, data) {
        $(data).on('click', function() {
            var href = $(this).attr('data-go');
            setCookie('targetHref', href);
            $(ctnBox).load('../html/case.html');
        })
    })
    $('#ad1').on('click', function() {
        var href = $(this).attr('data-go');
        setCookie('targetHref', href);
        $(ctnBox).load('../html/case.html');
    })
}
goCase()

$(function() {
    $('#moreCase').on('click', function() {
        $('.main_body').load('../html/case.html');
    });
    $('#moreDevice').on('click', function() {
        $('.main_body').load('../html/devices.html');
    });
});

/**
 * 首页广告图 -- 轮播图 单独做
 */
function getAdList() {
    var ad1 = $('#ad1'),
        ad1Box = '',
        ad2 = $('.adHref a'),
        ad2Box = "",
        pbox1 = $('#pbox1'),
        pboxCtn1 = "",
        pbox2 = $('#pbox2'),
        pboxCtn2 = "";
    $.get(urlInfo() + '/config/index', function(res) {
        ad1Box = '<img src="' + imgUrl() + getFirstImg(res.data.index_ad1) + '">';
        ad2Box = '<img src="' + imgUrl() + getFirstImg(res.data.index_ad2) + '">';
        $.each(res.data.picList, function(i) {
            switch (i) {
                case 0:
                    pboxCtn1 += '<div class="col-xs-12 b10"><img src="' + imgUrl() + getFirstImg(this.value) + '" alt="" title="" /></div>';
                    break;
                case 1:
                    pboxCtn1 += '<div class="col-xs-6 p2"><img src="' + imgUrl() + getFirstImg(this.value) + '" alt="" title="" /></div>';
                    break;
                case 2:
                    pboxCtn1 += '<div class="col-xs-6 p3"><img src="' + imgUrl() + getFirstImg(this.value) + '" alt="" title="" /></div>';
                    break;
                case 3:
                    pboxCtn2 += '<div class="col-xs-6 p4"><img src="' + imgUrl() + getFirstImg(this.value) + '" alt="" title="" /></div>';
                    break;
                case 4:
                    pboxCtn2 += '<div class="col-xs-6 p5"><img src="' + imgUrl() + getFirstImg(this.value) + '" alt="" title="" /></div>';
                    break;
                case 5:
                    pboxCtn2 += '<div class="col-xs-12"><img src="' + imgUrl() + getFirstImg(this.value) + '" alt="" title="" /></div>';
                    break;
                default:
                    break;
            }
        })
        $(pbox1).html(pboxCtn1);
        $(pbox2).html(pboxCtn2);
        $(ad1).html(ad1Box);
        $(ad2).html(ad2Box);

        //获取设备列表
        var goodsBox = $('#goodsList'),
            goodsCtn = '';
        $.each(res.data.goodsList, function(i) {
            goodsCtn += '<div class="col-xs-6"><div class="deviceItem" data-id="' + this.goodsId + '" data-href="devicesDc?goodsId=' + this.goodsId + '">' +
                '<img src="' + imgUrl() + this.imgUrl + '" alt="' + this.goodsName + '" title="' + this.goodsName + '" />' +
                '<div class="infobox"><p class="name">' + this.goodsName + '</p>' +
                '<p class="price">价格：<i>¥ ' + this.price + '</i></p></div></div></div>';
        })
        goodsBox.html(goodsCtn);
        //获取案例列表
        var caseBox = $('#marketList'),
            caseCtn = '';
        $.each(res.data.caseList, function(i) {
            caseCtn += '<div class="col-xs-4"><div class="casebox" data-id="' + this.cid + '" data-href="caseDc?caseid=' + this.cid + '">';
            caseCtn += '<img src="' + imgUrl() + this.imgUrl + '" alt="' + this.caseName + '" title="' + this.caseName + '" />';
            caseCtn += '<div class="introbox"><p class="name">' + this.caseName +
                '</p><p class="com_p">面积：<i class="com_num">' + this.area + '</i></p>' +
                '</div></div></div>';
        })
        caseBox.html(caseCtn);
        //获取市场列表
        var marketBox = $('#caseList'),
            marketCtn = '';
        $.each(res.data.marketList, function(i) {
            marketCtn += '<div class="col-xs-3"><div class="zsbox" data-id="' + this.mid + '"  data-href="marketDc?marketId=' + this.mid + '">' +
                '<img src="' + imgUrl() + this.imgUrl + '" alt="' + this.marketName + '" title="' + this.marketName + '" />' +
                '<p>' + this.marketName + '</p>' +
                '</div></div>';
        })
        marketBox.html(marketCtn);
        //获取新闻列表
        var newsBox = $('#newsList'),
            newsCtn = '';
        $.each(res.data.newsLit, function(i) {
            var defaultimg = '';
            if (this.imgUrl == '' || this.imgUrl == null) {
                defaultimg = '../images/default.png';
            } else {
                defaultimg = imgUrl() + getFirstImg(this.imgUrl);
            }
            newsCtn += '<div class="col-xs-12"><div class="nItem" data-id="' + this.nid + '" data-go="news">' +
                '<div class="imgbox"><img src="' + defaultimg + '" alt="' + this.newsTitle + '" title="' + this.newsTitle + '" /></div>' +
                '<div class="nTitle"><h3>' + this.newsTitle + '</h3><div class="time"><span>类型：' + this.typeName + '</span>' +
                '</div></div></div></div>';
        })
        newsBox.html(newsCtn);
        CaseDetails();
        GoodsDetails();
        MaketDetails();
        showNewsList();
    }, 'json');
}
getAdList();

/**
 * 跳转详情
 */
function CaseDetails() {
    var item = $('#marketList .casebox'),
        ctnBox = $('.main_body');
    $.each(item, function(key, data) {
        $(data).on('click', function() {
            var href = $(this).attr('data-href').split('?')[0],
                caseId = $(this).attr('data-href').split('=')[1];
            //需要保存一个值 用于其他页面使用
            setStr('caseId', caseId);
            $(ctnBox).load('../html/' + href + '.html');
        })
    })
}
/**
 * 设备详情
 */
function GoodsDetails() {
    var item = $('#goodsList .deviceItem'),
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
/**
 * 市场详情
 */
function MaketDetails() {
    var item = $('#caseList .zsbox'),
        ctnBox = $('.main_body');
    $.each(item, function(key, data) {
        $(data).on('click', function() {
            var href = $(this).attr('data-href').split('?')[0],
                marketId = $(this).attr('data-href').split('=')[1];
            //需要保存一个值 用于其他页面使用
            setStr('marketId', marketId);
            $(ctnBox).load('../html/' + href + '.html');
        })
    })
}

/**
 * 新闻列表
 */
function showNewsList() {
    var item = $('#newsList .nItem');
    $(item).on('click', function() {
        var href = $(this).attr('data-go');
        setCookie('targetHref', href, 1);
        $('.main_body').load('../html/case.html');
    })
}

/**
 * 更多新闻
 */
function showMoreNewsList() {
    var item = $('.f_news #moreNews');
    $(item).on('click', function() {
        setCookie('targetHref', 'news', 1);
        $('.main_body').load('../html/case.html');
    })
}
showMoreNewsList();


/**
 * 获取首页轮播图
 */

function getSliderList() {
    var dot_box = $('#dotlist'),
        pic_box = $('#piclist');
    var dot_item = '',
        pic_item = '';
    $.get(urlInfo() + '/config/index', function(res) {
        $.each(res.data.lastCase, function(i) {
            if (i == 0) {
                dot_item += '<li data-target="#homePic" data-slide-to="' + i + '" class="active"></li>';
                pic_item += '<div class="item active" data-go="caseDc?caseid=' + this.cid + '"><img src="' + imgUrl() + getFirstImg(this.imgUrl) + '" /></div>';
            } else {
                dot_item += '<li data-target="#homePic" data-slide-to="' + i + '"></li>';
                pic_item += '<div class="item" data-go="caseDc?caseid=' + this.cid + '"><img src="' + imgUrl() + getFirstImg(this.imgUrl) + '"/></div>';
            }
        })
        $(dot_box).html(dot_item);
        $(pic_box).html(pic_item);
        goDetails();
    })
}
getSliderList();

/**
 * 跳转案例详情
 */
function goDetails() {
    var item = $('#piclist .item'),
        ctnBox = $('.main_body');
    $.each(item, function(key, data) {
        $(data).on('click', function() {
            var href = $(this).attr('data-go').split('?')[0],
                caseId = $(this).attr('data-go').split('=')[1];
            //需要保存一个值 用于其他页面使用
            setStr('caseId', caseId);
            $(ctnBox).load('../html/' + href + '.html');
        })
    })
}

function getAdHref() {
    var rightsText = '',
        rights = $('.adHref');
    $.get(urlInfo() + '/config/index', function(res) {
        rights.find('a').attr('href', res.data.settingMap.index_ad2_url);
    }, 'json');
}
getAdHref();



/**
 * 轮播时间
 */
$(function() {
    $('#homePic').carousel({
        interval: 2678
    });
})