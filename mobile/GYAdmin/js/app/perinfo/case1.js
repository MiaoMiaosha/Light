$(function() {
    $('.caseDc_body').height($('html').height() - 168);
    // tabCur();
});

//获取案例列表
function getCaseList() {
    //切换
    var lis = $('.com_lip li'),
        items = $('.com_boxp .com_itemp'),
        targetUrl = 'market',
        default_ctn = '';
    //默认加载市场列表的
    $.get(urlInfo() + '/market/list', function(res) {
        $.each(res.data, function(key, data) {
            default_ctn += '<li class="p_rel tlis" data-id="' + data.mid + '"><div class="thBox">' +
                '<img src="' + imgUrl() + data.imgUrl +getShuiYin()+ '" alt="' + data.marketName + '" title="' + data.marketName + '" />' +
                '<div class="title"><div class="mask"></div><p>' + data.marketName + '</p></div></div></li>';
        });
        $('#market').html(default_ctn);
    });
    //点击加载的
    $.each(lis, function(key, data) {
        $(data).on('click', function() {
            $(this).addClass('cur').siblings('li').removeClass('cur');
            targetUrl = $(this).attr('data-type');
            $(items[key]).removeClass('d_none').siblings('.com_itemp').addClass('d_none');
            //要用switch 每个接口的字段不同
            var ctn_box = $(items[key]).find('.com_item ul'),
                ctn = '';
            $(ctn_box).html('');
            $.get(urlInfo() + '/' + targetUrl + '/list', function(res) {
                $.each(res.data, function(key, data) {
                    switch (targetUrl) {
                        case 'market':
                            ctn += '<li class="p_rel tlis" data-id="' + data.mid + '"><div class="thBox">' +
                                '<img src="' + imgUrl() + data.imgUrl + getShuiYin()+'" alt="' + data.marketName + '" title="' + data.marketName + '" />' +
                                '<div class="title"><div class="mask"></div><p>' + data.marketName + '</p></div></div></li>';
                            break;
                        case 'case': //三级// //默认加载五星级的
                            if (data.level == 5) {
                                ctn += '<li class="p_rel tlis" data-id="' + data.cid + '" data-userid="' + data.userId + '" data-href="caseDc?caseid=' + data.cid + '"><div class="thBox">' +
                                    '<img src="' + imgUrl() + data.imgUrl + getShuiYin()+'" alt="' + data.caseName + '" title="' + data.caseName + '" />' +
                                    '<div class="title"><div class="mask"></div><p>' + data.caseName + '</p></div></div></li>';
                            }
                            break;
                        case 'goods':
                            // $(ctn_box).html('');
                            ctn += '<li class="p_rel tlis" data-id="' + data.goodsId + '"><div class="thBox">' +
                                '<img src="' + imgUrl() + data.img + getShuiYin()+'" alt="' + data.goodsName + '" title="' + data.goodsName + '" />' +
                                '<div class="title"><div class="mask"></div><p>' + data.goodsName + '</p></div></div></li>';
                            break;
                        default:
                            break;
                    }
                });
                $(ctn_box).html(ctn);
            });
        })
    });
    //三级
    var lis3 = $('.com_li li'),
        items3 = $('.com_box .com_item'),
        c_box = $('#cbox ul');
    $.each(lis3, function(key, data) {
        $(data).on('click', function() {
            $(this).addClass('cur').siblings('li').removeClass('cur');
            var levelType = $(this).attr('data-type');
            var ctn = '';
            $(c_box).html('');
            // $(items3[key]).removeClass('d_none').siblings('.com_item').addClass('d_none');
            $.get(urlInfo() + '/case/list', function(res) {
                $.each(res.data, function(key1, data1) {
                    if (data1.level == levelType) {
                        ctn = '<li class="p_rel tlis" data-id="' + data1.cid + '" data-userid="' + data1.userId + '" data-href="caseDc?caseid=' + data1.cid + '"><div class="thBox">' +
                            '<img src="' + imgUrl() + data1.imgUrl + getShuiYin()+'" alt="' + data1.caseName + '" title="' + data1.caseName + '" />' +
                            '<div class="title"><div class="mask"></div><p>' + data1.caseName + '</p></div></div></li>';
                        $(c_box).append(ctn);
                    }
                });
                goDetails();
            })
        })
    });
}
getCaseList();


//可能需要给案例详情单独一个js 之后看是否能得到数据
//跳转详情
function goDetails() {
    var item = $('.com_item li'),
        ctnBox = $('.main_body');
    $.each(item, function(key, data) {
        $(data).on('click', function() {
            var href = $(this).attr('data-href').split('?')[0],
                caseId = $(this).attr('data-href').split('=')[1];
            $(ctnBox).load('../html/' + href + '.html');
            //需要保存一个值 用于其他页面使用
            setCookie('caseId', caseId, 1);
        })
    })
}