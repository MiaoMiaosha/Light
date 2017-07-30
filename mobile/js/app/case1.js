$(function() {
    // $('.caseDc_body').height($('html').height() - 168);
    // tabCur();
});

/**
 * 获取简介类型
 */
function getCompanyType() {
    var typebox = $('#getCompanyType'),
        typeCtn = '';
    $.ajaxSetup({
        async: false
    })
    $.get(urlInfo() + '/config/introlist', function(res) {
        $.each(res.data, function(i) {
            if (i == 0) {
                typeCtn += '<li class="f_left slis alis cur" data-about="' + this.id + '">' + this.typename + '</li>';
            } else {
                typeCtn += '<li class="f_left slis alis" data-about="' + this.id + '">' + this.typename + '</li>';
            }
        })
        $(typebox).html(typeCtn);
    })
}
getCompanyType()


//获取案例列表
function getCaseList() {
    //切换
    var lis = $('.com_lip li'),
        items = $('.com_boxp .com_itemp'),
        targetUrl = 'market',
        default_ctn = '';
    //默认加载市场列表的
    //进行判断getCookie是否存在 不存在的话那么就用默认的market 
    //存在的话根据点的内容 去获取对应接口里的数据
    if (getCookie('targetHref').length > 0 && getCookie('targetHref') != 'undefined') {
        var cookieTarget = getCookie('targetHref');
        switch (cookieTarget) {
            case "market":
                $.get(urlInfo() + '/market/list?status=1', function(res) {
                    var mCtn = '';
                    $.each(res.data, function(key, data) {
                        mCtn += '<li class="p_rel tlis" data-id="' + data.mid + '" data-userid="' + data.userId + '" data-href="marketDc?marketId=' + data.mid + '"><div class="thBox">' +
                            '<img src="' + imgUrl() + getFirstImg(data.imgUrl) + getShuiYin() + '" alt="' + data.marketName + '" title="' + data.marketName + '" />' +
                            '<div class="title"><div class="mask"></div><p>' + data.provinceName + data.cityName + '--' + data.marketName + '</p></div></div></li>';
                    })
                    $('#market ul').html(mCtn);
                    MaketDetails();
                }, 'json');
                break;
            case "case":
                $.get(urlInfo() + '/case/list?state=1', function(res) {
                    var cCtn = '';
                    $.each(res.data, function(key, data) {
                        if (data.level == 5) {
                            cCtn += '<li class="p_rel tlis" data-id="' + data.cid + '" data-userid="' + data.userId + '" data-href="caseDc?caseid=' + data.cid + '"><div class="thBox">' +
                                '<img src="' + imgUrl() + getFirstImg(data.imgUrl) + getShuiYin() + '" alt="' + data.caseName + '" title="' + data.caseName + '" />' +
                                '<div class="title"><div class="mask"></div><p>' + data.caseName + '</p></div></div></li>';
                        }
                    })
                    $('#case ul').html(cCtn);
                    goDetails();
                }, 'json');
                break;
            case "goods":
                $.get(urlInfo() + '/goods/list?status=1&type=1', function(res) {
                    var gCtn = '';
                    $.each(res.data, function(key, data) {
                        gCtn += '<li class="f_left p_rel glis tlis" data-id="' + data.goodsId + '"  data-userid="' + data.userId + '" data-href="devicesDc?devicesid=' + data.goodsId + '"><div class="thBox">' +
                            '<img src="' + imgUrl() + getFirstImg(data.img) + getShuiYin() + '" alt="' + data.goodsName + '" title="' + data.goodsName + '" />' +
                            '<div class="title"><div class="mask"></div><p>' + data.goodsName + '</p></div></div></li>';
                    })
                    $('#goods ul').html(gCtn);
                    GoodsDetails();
                }, 'json');
                break;
            case "news":
                $('#news').load('../html/zx.html');
                break;
            case 'join':
                $.get(urlInfo() + '/cooperate/list?status=1', function(res) {
                    var gCtn = '';
                    $.each(res.data, function(key, data) {
                        var major = data.list,
                            majorList = '';
                        for (var i = 0; i < major.length; i++) {
                            majorList += major[i] + ',';
                        }
                        majorList = majorList.substring(0, majorList.length - 1);
                        gCtn += '<li class="p_rel jlis" data-id="' + data.element.cid + '" data-userid="' + data.element.userId + '"><div class="thBox"><p class="title">' + data.element.fullName + '</p>' +
                            '<p class="addr">公司地址:' + data.province.name + data.city.name + data.district.name + '</p><p class="major">公司主营:' + majorList + '</p><p class="intro">公司简介:' + data.element.companyIntro + '</p>' +
                            '</div></li>';
                    })
                    $('#join ul').html(gCtn);
                }, 'json');
                break;
            case 'aboutgy':
                $.get(urlInfo() + '/config/introlist?id=1', function(res) {
                    var gCtn = '';
                    $.each(res.data, function(key, data) {
                        gCtn = data.content;
                    })
                    $('#aboutgy').html(gCtn);
                }, 'json');
                break;
            default:
                break;
        }
    } else {
        $.get(urlInfo() + '/case/list?state=1', function(res) {
            $.each(res.data, function(key, data) {
                if (data.level == 5) {
                    default_ctn += '<li class="p_rel tlis" data-id="' + data.cid + '" data-userid="' + data.userId + '" data-href="caseDc?caseid=' + data.cid + '"><div class="thBox">' +
                        '<img src="' + imgUrl() + getFirstImg(data.imgUrl) + getShuiYin() + '" alt="' + data.caseName + '" title="' + data.caseName + '" />' +
                        '<div class="title"><div class="mask"></div><p>' + data.caseName + '</p></div></div></li>';
                }
            });
            $('#case ul').html(default_ctn);
            goDetails();
        });
    }
    //点击加载的
    $.each(lis, function(key, data) {
        $(data).on('click', function() {
            $(this).addClass('cur').siblings('li').removeClass('cur');
            $(items[key]).removeClass('d_none').siblings('.com_itemp').addClass('d_none');
            if (typeof $(this).attr('data-type') != 'undefined') {
                targetUrl = $(this).attr('data-type');
                //要用switch 每个接口的字段不同
                var ctn_box = $(items[key]).find('.com_item ul'),
                    ctn = '';
                $(ctn_box).html('');
                if (targetUrl == 'news') {
                    $('#news').load('../html/zx.html');
                } else {
                    if (targetUrl == 'case') {
                        targetUrl += '/list?state=1';
                    } else if (targetUrl == 'goods') {
                        targetUrl += '/list?status=1&type=1';
                    } else if (targetUrl == 'aboutgy') {
                        targetUrl = 'config/introlist?id=1';
                    } else {
                        targetUrl += '/list?status=1';
                    }
                    $.get(urlInfo() + '/' + targetUrl, function(res) {
                        $.each(res.data, function(key, data) {
                            switch (targetUrl.split('/')[0]) {
                                case 'market':
                                    ctn += '<li class="p_rel tlis" data-id="' + data.mid + '" data-userid="' + data.userId + '" data-href="marketDc?marketId=' + data.mid + '"><div class="thBox">' +
                                        '<img src="' + imgUrl() + getFirstImg(data.imgUrl) + getShuiYin() + '" alt="' + data.marketName + '" title="' + data.marketName + '" />' +
                                        '<div class="title"><div class="mask"></div><p>' + data.provinceName + data.cityName + '--' + data.marketName + '</p></div></div></li>';
                                    break;
                                case 'case': //三级// //默认加载五星级的
                                    if (data.level == 5) {
                                        ctn += '<li class="p_rel tlis" data-id="' + data.cid + '" data-userid="' + data.userId + '" data-href="caseDc?caseid=' + data.cid + '"><div class="thBox">' +
                                            '<img src="' + imgUrl() + getFirstImg(data.imgUrl) + getShuiYin() + '" alt="' + data.caseName + '" title="' + data.caseName + '" />' +
                                            '<div class="title"><div class="mask"></div><p>' + data.caseName + '</p></div></div></li>';
                                    }
                                    break;
                                case 'goods':
                                    // $(ctn_box).html('');
                                    ctn += '<li class="f_left p_rel glis tlis" data-id="' + data.goodsId + '" data-userid="' + data.userId + '" data-href="devicesDc?devicesid=' + data.goodsId + '"><div class="thBox">' +
                                        '<img src="' + imgUrl() + getFirstImg(data.img) + getShuiYin() + '" alt="' + data.goodsName + '" title="' + data.goodsName + '" />' +
                                        '<div class="title"><div class="mask"></div><p>' + data.goodsName + '</p></div></div></li>';
                                    break;
                                case 'cooperate':
                                    var major = data.list,
                                        majorList = '';
                                    for (var i = 0; i < major.length; i++) {
                                        majorList += major[i] + ',';
                                    }
                                    majorList = majorList.substring(0, majorList.length - 1);
                                    ctn += '<li class="p_rel jlis" data-id="' + data.element.cid + '" data-userid="' + data.element.userId + '"><div class="thBox"><p class="title">' + data.element.fullName + '</p>' +
                                        '<p class="addr">公司地址:' + data.province.name + data.city.name + data.district.name + '</p><p class="major">公司主营:' + majorList + '</p><p class="intro">公司简介:' + data.element.companyIntro + '</p>' +
                                        '</div></li>';
                                    break;
                                case 'config':
                                    ctn = data.content;
                                    break;
                                default:
                                    break;
                            }
                        });
                        if (targetUrl.split('/')[0] != 'config') {
                            $(ctn_box).html(ctn);
                        } else {
                            $('#aboutgy').html(ctn);
                        }
                        goDetails();
                        GoodsDetails();
                        MaketDetails();
                    });
                }
            }
        })
    });
    //三级
    var lis3 = $('.com_li li'),
        items3 = $('.com_box .com_item'),
        c_box = $('#case ul');
    $.each(lis3, function(key, data) {
        $(data).on('click', function() {
            if (typeof $(this).attr('data-type') != 'undefined') {
                $(this).addClass('cur').siblings('li').removeClass('cur');
                var levelType = $(this).attr('data-type');
                var ctn = '';
                $(c_box).html('');
                // $(items3[key]).removeClass('d_none').siblings('.com_item').addClass('d_none');
                $.get(urlInfo() + '/case/list?state=1', function(res) {
                    $.each(res.data, function(key1, data1) {
                        if (data1.level == levelType) {
                            ctn = '<li class="p_rel tlis" data-id="' + data1.cid + '" data-userid="' + data1.userId + '" data-href="caseDc?caseid=' + data1.cid + '"><div class="thBox">' +
                                '<img src="' + imgUrl() + getFirstImg(data1.imgUrl) + getShuiYin() + '" alt="' + data1.caseName + '" title="' + data1.caseName + '" />' +
                                '<div class="title"><div class="mask"></div><p>' + data1.caseName + '</p></div></div></li>';
                            $(c_box).append(ctn);
                        }
                    });
                    goDetails();
                })
            } else if (typeof $(this).attr('data-about') != 'undefined') {
                $(this).addClass('cur').siblings('li').removeClass('cur');
                var levelType = $(this).attr('data-about');
                var ctn = '';
                $.get(urlInfo() + '/config/introlist?id=' + levelType, function(res) {
                    var gCtn = '';
                    $.each(res.data, function(key1, data1) {
                        gCtn = data1.content;
                    })
                    $('#aboutgy').html(gCtn);
                }, 'json');
            } else if (typeof $(this).attr('data-status') != 'undefined') {
                $(this).addClass('cur').siblings('li').removeClass('cur');
                var levelType = $(this).attr('data-status');
                var ctn = '';
                $(c_box).html('');
                // $(items3[key]).removeClass('d_none').siblings('.com_item').addClass('d_none');
                $.get(urlInfo() + '/goods/list?status=1&type=' + levelType, function(res) {
                    $.each(res.data, function(key1, data1) {
                        ctn = '<li class="f_left p_rel glis tlis" data-id="' + data1.goodsId + '"  data-userid="' + data1.userId + '" data-href="devicesDc?devicesid=' + data1.goodsId + '"><div class="thBox">' +
                            '<img src="' + imgUrl() + getFirstImg(data1.img) + getShuiYin() + '" alt="' + data1.goodsName + '" title="' + data1.goodsName + '" />' +
                            '<div class="title"><div class="mask"></div><p>' + data1.goodsName + '</p></div></div></li>';
                        $('#goods ul').html(ctn);
                    });
                    GoodsDetails();
                })
            }
        })
    });
}
getCaseList();


//可能需要给案例详情单独一个js 之后看是否能得到数据
//跳转详情
function goDetails() {
    var item = $('#case li'),
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
    var item = $('#goods li'),
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
    var item = $('#market li'),
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
 * 根据传过来的#xxx 进行解析
 * 实现对应模块的选中 
 */
function showCurPage() {
    var plis = $('.com_lip .olis'),
        pitems = $('.com_boxp .com_itemp'),
        curPart = getCookie('targetHref');
    if (curPart.length > 0 && curPart != 'undefined')
        $.each(plis, function(i) {
            var targetHref = $(this).attr('data-target');
            if (curPart === targetHref)
                $(this).addClass('cur').siblings('li').removeClass('cur'),
                $(pitems[i]).removeClass('d_none').siblings('.com_itemp').addClass('d_none'),
                removeCookie('targetHref');
        })
}
showCurPage();