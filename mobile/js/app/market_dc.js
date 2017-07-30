$(function() {
    // $('.caseDc_body').height($('html').height() - 168);
    $('#comment').on('click', function() {
        var type = $(this).attr('data-type');
        var typeid = $(this).attr('data-id');
        setCookie('commentType', type, 1);
        setCookie('commentId', typeid, 1);
        $('.main_body').load('../html/comment.html');
    });
    $('.com_comment').on('click', function() {
        var type = $(this).attr('data-type'),
            typeid = $(this).attr('data-id');
        setCookie('addCommentType', type, 1);
        setCookie('addCommentTypeId', typeid, 1);
        $('.main_body').load('../html/commentAdd.html');
    })
    $('#case').on('click', function() {
        var href = $(this).attr('data-go');
        setCookie('targetHref', 'market', 1);
        $('.main_body').load('../html/case.html');
    });
});

/**
 * 分享
 */
function shareLink(ids, title1, intro1, imgs) {
    $('#J-share-WXTips').remove();
    var shareWXTipsHtml = '<aside id="J-share-WXTips" class="dialog-share-wrapper" style="display: none;"><div class="share-tips"></div></aside>';
    $('body').append(shareWXTipsHtml);
    // location.hash = "";
    $('.share').on('click', function() {
        $('#J-share-WXTips').show();
        var id = ids;
        var url = location.href + '#marketDc@marketId=' + id;
        var intro = intro1,
            title = title1,
            img = imgs;
        useWxJSSDK(title, url, intro, imgs);
    });
    $('#J-share-WXTips').on('click', function() {
        $('#J-share-WXTips').hide();
    });
}

/**
 * 判断是否收藏
 */
function isCollect() {
    var id = getStr('marketId'),
        userId = getCookie('wxUserId');
    var likeBtn = $('#oplist .islike');
    $.post(urlInfo() + '/like/islike', {
        'type': 2,
        'userId': userId,
        'contentId': id
    }, function(res) {
        if (res.status.code == 200) {
            if (res.data.isLike == 0) {
                //未收藏
                $(likeBtn).attr('data-islike', 0).css('color', '#000').find('i').removeClass('icon-star2').addClass('icon-star')
            } else {
                //收藏
                $(likeBtn).attr('data-islike', 1).css('color', '#f8a766').find('i').removeClass('icon-star').addClass('icon-star2')
            }
            setCookie('marketlikeid', res.data.id, 1);
        }
    }, 'json')
}
isCollect();

//收藏
function isLike() {
    var likeBtn = $('#oplist .islike');
    var id = getStr('marketId'),
        userId = getCookie('wxUserId');
    $(likeBtn).on('click', function() {
        var _this = this;
        if ($(this).attr('data-islike') == 0) {
            //加入收藏
            var title = $('#case_info h3').text();
            $.post(urlInfo() + '/like/add', {
                'type': 2,
                'userId': userId,
                'contentId': id,
                'content': title
            }, function(res) {
                if (res.status.code == 200) {
                    $(_this).attr('data-islike', 1).css('color', '#f8a766').find('i').removeClass('icon-star').addClass('icon-star2');
                    setCookie('marketlikeid', res.data, 1);
                }
            }, 'json');
            // $(this).attr('data-islike', 1);
            // $(this).css('color', '#f8a766').find('i').removeClass('icon-star').addClass('icon-star2');
        } else {
            //取消收藏
            $.post(urlInfo() + '/like/delete', {
                'id': getCookie('marketlikeid')
            }, function(res) {
                if (res.status.code == 200) {
                    $(_this).attr('data-islike', 0).css('color', '#000').find('i').removeClass('icon-star2').addClass('icon-star')
                }
            });
            // $(this).attr('data-islike', 0);
            // $(this).css('color', '#000').find('i').removeClass('icon-star2').addClass('icon-star');
        }
    })
}
isLike();

//获取案例详情
function getCaseList() {
    var dot_box = $('#cdot_box'),
        pic_box = $('#cpic_box'),
        dBox = $('.otherDetails'),
        dImglist = '';
    $.get(urlInfo() + '/market/list', {
        'mid': getStr('marketId')
    }, function(res) {
        //判断图片的数量 放置到轮播模块里
        if (res.data[0].imgUrl.split('#').length > 1) {
            var imglist = res.data[0].imgUrl.split('#');
            for (var i in imglist) {
                if (imglist[i] == '') {
                    imglist.splice(i, 1); //移除空的
                }
            }
            var dot_item = '',
                pic_item = '';
            for (var i = 0; i < imglist.length; i++) {
                if (i == 0) {
                    dot_item += '<li data-target="#casePic" data-slide-to="' + i + '" class="active "></li>';
                    pic_item += '<div class="item active"><img src="' + imgUrl() + imglist[i] + getShuiYin()+'" alt="' + res.data[0].marketName + '" title="' + res.data[0].marketName + '" /></div>';
                } else {
                    if (i == 3) break;
                    dot_item += '<li data-target="#casePic" data-slide-to="' + i + '"></li>';
                    pic_item += '<div class="item"><img src="' + imgUrl() + imglist[i] + getShuiYin()+'" alt="' + res.data[0].marketName + '" title="' + res.data[0].marketName + '" /></div>';
                }
            }
            $(dot_box).html(dot_item);
            $(pic_box).html(pic_item);
            for (var i = 0; i < imglist.length; i++) {
                dImglist += '<img src="' + imgUrl() + imglist[i] + getShuiYin()+'">';
                // console.log(dImglist);
            }
            $(dBox).html(dImglist);
        } else {
            var dot_item = '',
                pic_item = '';
            dot_item = '<li data-target="#casePic" data-slide-to="0" class="active "></li>';
            pic_item = '<div class="item active"><img src="' + imgUrl() + res.data[0].imgUrl + getShuiYin()+'" alt="' + res.data[0].marketName + '" title="' + res.data[0].marketName + '" /></div>';
            dImglist = '<img src="' + imgUrl() + res.data[0].imgUrl + getShuiYin()+'">';
            // dImglist = '<img src="' + imgUrl() + res.data.imgUrl + '">';
            // console.log(dImglist);
            $(dBox).html(dImglist);
            $(dot_box).html(dot_item);
            $(pic_box).html(pic_item);
        }
        //显示数据
        var info_box = '',
            lvbox = '';
        //id,名称,省份,城市,面积,地址
        info_box = '<h3>' + res.data[0].marketName + '<span data-id="' + res.data[0].mid + '" data-name="' + res.data[0].marketName + '" data-province="' + res.data[0].provinceName +
            '" data-city="' + res.data[0].cityName + '" data-addr="' + res.data[0].address + '" data-area="' + res.data[0].area + '" data-userid="' + res.data[0].userId +
            '" data-imgkey="' + res.data[0].imgUrl + '">出租摊位</span></h3><div class="infolist"><p>市场描述：<i>' + res.data[0].marketIntro + '</i></p><p>发布时间：<i>' +
            getTime(res.data[0].createTime) + '</i></p><p>面积：<i>' + res.data[0].area + '</i></p><p>地址：<i>' + res.data[0].provinceName +
            res.data[0].cityName + res.data[0].districtName + res.data[0].townName + res.data[0].address + '</i></p>' +
            '<p>联系人：' + res.data[0].contactName + '</p><p>联系电话：' + res.data[0].contactMobile + '</p></div>';
        $('#comment').attr('data-id', res.data[0].mid);
        $('.com_comment').attr('data-id', res.data[0].mid);
        $('#case_info').html(info_box);

        goTanwei();
        //ids, title1, intro1, imgs
        shareLink(res.data[0].mid, res.data[0].marketName, res.data[0].marketIntro, imgUrl() + getFirstImg(res.data[0].imgUrl));

    })
}
getCaseList();

/**
 * 求租摊位
 */
function goTanwei() {
    var spanBtn = $('#case_info h3');
    $(spanBtn).on('click', 'span', function() {
        var marketId = $(this).attr('data-id'),
            marketName = $(this).attr('data-name'),
            marketProvince = $(this).attr('data-province'),
            marketCity = $(this).attr('data-city'),
            marketAddr = $(this).attr('data-addr'),
            marketArea = $(this).attr('data-area'),
            marketUserId = $(this).attr('data-userid'),
            marketImgKey = $(this).attr('data-imgkey');
        var marketJson = '{\"marketId\":\"' + marketId + '\",\"marketName\":\"' + marketName + '\",\"marketProvince\":\"' + marketProvince +
            '\",\"marketCity\":\"' + marketCity + '\",\"marketAddr\":\"' + marketAddr + '\",\"marketArea\":\"' + marketArea +
            '\",\"marketUserId\":\"' + marketUserId + '\",\"marketImgKey\":\"' + marketImgKey + '\"}';
        setCookie('marketJson', marketJson);
        $('.main_body').load('stall.html');
    })
}

/**
 * 返回设备列表
 */
function backDeviceList() {
    $('.fixedBack').on('click', function() {
        setCookie('targetHref', 'market', 1);
        location.hash = "";
        $('.main_body').load('../html/case.html');
    })
}
backDeviceList();