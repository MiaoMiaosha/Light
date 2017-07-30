$(function() {
    // $('.devices_body').height($('html').height() - 168);
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
    $('#device').on('click', function() {
        $('.main_body').load('../html/devices.html');
    });
});

/**
 * 判断是否收藏
 */
function isCollect() {
    var id = getStr('goodsId'),
        userId = getCookie('wxUserId');
    var likeBtn = $('#isLike');
    $.post(urlInfo() + '/like/islike', {
        'type': 3,
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
            setCookie('goodslikeid', res.data.id, 1);
        }
    }, 'json')
}
isCollect();

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
        var url = location.href + '#devicesDc@goodsId=' + id;
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
 * 收藏
 */
function isLike() {
    var isLikeBtn = $('#isLike');
    var id = getStr('goodsId'),
        userId = getCookie('wxUserId');
    $(isLikeBtn).on('click', function() {
        var _this = this;
        if ($(this).attr('data-islike') == 0) {
            //加入收藏
            var title = $('#device_info h3').text();
            $.post(urlInfo() + '/like/add', {
                'type': 3,
                'userId': userId,
                'contentId': id,
                'content': title
            }, function(res) {
                if (res.status.code == 200) {
                    $(_this).attr('data-islike', 1).css('color', '#f8a766').find('i').removeClass('icon-star').addClass('icon-star2');
                    setCookie('goodslikeid', res.data, 1);
                }
            }, 'json');
            // $(this).attr('data-islike', 1);
            // $(this).css('color', '#f8a766').find('i').removeClass('icon-star').addClass('icon-star2');
        } else {
            //取消收藏
            $.post(urlInfo() + '/like/delete', {
                'id': getCookie('goodslikeid')
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

//获取设备详情
function getDeviceInfo() {
    var dot_box = $('#dotlist'),
        pic_box = $('#piclist'),
        dImglist = '',
        dBox = $('.otherDetails');
    $.get(urlInfo() + '/goods/detail', {
        'goodsId': getStr('goodsId')
    }, function(res) {
        //判断图片的数量 放置到轮播模块里
        if (res.data.img.split('#').length > 1) {
            var imglist = res.data.img.split('#');
            for (var i in imglist) {
                if (imglist[i] == '') {
                    imglist.splice(i, 1); //移除空的
                }
            }
            var dot_item = '',
                pic_item = '';
            for (var i = 0; i < imglist.length; i++) {
                if (i == 0) {
                    dot_item += '<li data-target="#devicePic" data-slide-to="' + i + '" class="active "></li>';
                    pic_item += '<div class="item active"><img src="' + imgUrl() + imglist[i] + getShuiYin()+'" alt="' + res.data.goodsName + '" title="' + res.data.goodsName + '" /></div>';
                } else {
                    if (i == 3) break;
                    dot_item += '<li data-target="#devicePic" data-slide-to="' + i + '"></li>';
                    pic_item += '<div class="item"><img src="' + imgUrl() + imglist[i] + getShuiYin()+'" alt="' + res.data.goodsName + '" title="' + res.data.goodsName + '" /></div>';
                }
            }
            $(dot_box).html(dot_item);
            $(pic_box).html(pic_item);
            for (var i = 0; i < imglist.length; i++) {
                dImglist += '<img src="' + imgUrl() + imglist[i] + getShuiYin()+'">';
            }
            $(dBox).html(dImglist);
        } else {
            var dot_item = '',
                pic_item = '';
            dot_item = '<li data-target="#devicePic" data-slide-to="0" class="active "></li>';
            pic_item = '<div class="item active"><img src="' + imgUrl() + res.data.img +getShuiYin()+ '" alt="' + res.data.goodsName + '" title="' + res.data.goodsName + '" /></div>';
            dImglist = '<img src="' + imgUrl() + res.data.img + getShuiYin()+'">';
            $(dBox).html(dImglist);
            $(dot_box).html(dot_item);
            $(pic_box).html(pic_item);
        }
        //显示数据
        var info_box = '';
        info_box = '<h3>' + res.data.goodsName + '</h3><p class="notes"><i class="icon-warn-copy"></i>本商品不属于定制购买，您可以在线提交咨询。</p>' +
            '<p class="intro">' + res.data.description + '</p><div class="prices"><span class="tehui">商城价：<i>¥' + (res.data.goodsPrice / 100) +
            '</i></span><span>市场价：<i>¥' + (res.data.goodsPrice / 100 * 1.5) + '</i></span></div>';
        $('#comment').attr('data-id', res.data.goodsId);
        $('.com_comment').attr('data-id', res.data.goodsId);
        $('#device_info').html(info_box);
        //ids, title1, intro1, imgs
        shareLink(res.data.goodsId, res.data.goodsName, res.data.description, imgUrl() + getFirstImg(res.data.img));
    }, 'json')
}
getDeviceInfo();

/**
 * 返回设备列表
 */
function backDeviceList() {
    $('.fixedBack').on('click', function() {
        location.hash = "";
        $('.main_body').load('../html/devices.html');
    })
}
backDeviceList();