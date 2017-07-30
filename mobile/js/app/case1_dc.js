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
        setCookie('targetHref', href);
        $('.main_body').load('../html/case.html');
    });
});
//星级
function lvClick() {
    var lvList = $('#lvList .slis');
    $.each(lvList, function() {
        //没点之前判断亮了几个星星
        if ($(this).attr('data-islv') == 1) {
            //$(this).prevAll().find('i').removeClass('icon-star').addClass('icon-star2');
            $(this).find('i').removeClass('icon-star').addClass('icon-star2');
        } else if ($(this).attr('data-islv') == 0) {
            $(this).find('i').removeClass('icon-star2').addClass('icon-star');
        }
        $(this).on('click', function() {
            if ($(this).attr('data-islv') == 0) {
                $(this).attr('data-islv', 1).prevAll().attr('data-islv', 1);
                $(this).prevAll().find('i').removeClass('icon-star').addClass('icon-star2');
                $(this).find('i').removeClass('icon-star').addClass('icon-star2');
            } else if ($(this).attr('data-islv') == 1) {
                $(this).nextAll().attr('data-islv', 0);
                $(this).nextAll().find('i').removeClass('icon-star2').addClass('icon-star');
            }
        })
    })
}

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
        var url = location.href + '#caseDc@caseId=' + id;
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
    var id = getStr('caseId'),
        userId = getCookie('wxUserId');
    var likeBtn = $('#oplist .islike');
    $.post(urlInfo() + '/like/islike', {
        'type': 1,
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
            setCookie('caselikeid', res.data.id, 1);
        }
    }, 'json')
}
isCollect();

/**
 * 收藏
 */
function isLike() {
    var likeBtn = $('#oplist .islike');
    var id = getStr('caseId'),
        userId = getCookie('wxUserId');
    $(likeBtn).on('click', function() {
        var _this = this;
        if ($(_this).attr('data-islike') == 0) {
            //加入收藏
            var title = $('#case_info h3').text();
            $.post(urlInfo() + '/like/add', {
                'type': 1,
                'userId': userId,
                'contentId': id,
                'content': title
            }, function(res) {
                if (res.status.code == 200) {
                    $(_this).attr('data-islike', 1).css('color', '#f8a766').find('i').removeClass('icon-star').addClass('icon-star2');
                    setCookie('caselikeid', res.data, 1);
                }
            }, 'json');
        } else {
            //取消收藏
            $.post(urlInfo() + '/like/delete', {
                'id': getCookie('caselikeid')
            }, function(res) {
                if (res.status.code == 200) {
                    $(_this).attr('data-islike', 0).css('color', '#000').find('i').removeClass('icon-star2').addClass('icon-star')
                }
            });
            // $(_this).attr('data-islike', 0);
            // $(_this).css('color', '#000').find('i').removeClass('icon-star2').addClass('icon-star');
        }
    })
}
isLike();

//访客评论
function commentList() {

}
//更多案例
function moreCase() {

}

//获取案例详情
function getCaseList() {
    var dot_box = $('#cdot_box'),
        pic_box = $('#cpic_box'),
        dBox = $('.otherDetails'),
        dImglist = '';
    $.get(urlInfo() + '/case/detail', {
        'caseId': getStr('caseId')
    }, function(res) {
        // console.log(res);
        //判断图片的数量 放置到轮播模块里
        // console.log(res.data.imgUrl);
        // console.log(res.data.imgUrl.split('#').length);
        if (res.data.imgUrl.split('#').length > 1) {
            var imglist = res.data.imgUrl.split('#');
            // console.log(imglist);
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
                    pic_item += '<div class="item active"><img src="' + imgUrl() + imglist[i] + getShuiYin()+'" alt="' + res.data.caseName + '" title="' + res.data.caseName + '" /></div>';
                } else {
                    if (i == 3) break;
                    dot_item += '<li data-target="#casePic" data-slide-to="' + i + '"></li>';
                    pic_item += '<div class="item"><img src="' + imgUrl() + imglist[i] +getShuiYin()+ '" alt="' + res.data.caseName + '" title="' + res.data.caseName + '" /></div>';
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
            pic_item = '<div class="item active"><img src="' + imgUrl() + res.data.imgUrl + getShuiYin()+'" alt="' + res.data.caseName + '" title="' + res.data.caseName + '" /></div>';
            dImglist = '<img src="' + imgUrl() + res.data.imgUrl + getShuiYin()+'">';
            // console.log(dImglist);
            $(dBox).html(dImglist);
            $(dot_box).html(dot_item);
            $(pic_box).html(pic_item);
        }
        //显示数据
        var info_box = '',
            lvbox = '';
        for (var i = 0; i < res.data.level; i++) {
            lvbox += '<li class="f_left slis" data-islv="1"><i class="icon-star"></i></li>';
        }
        if (res.data.level < 5) {
            for (var i = 0; i < (5 - res.data.level); i++) {
                lvbox += '<li class="f_left slis" data-islv="0"><i class="icon-star"></i></li>';
            }
        }
        info_box = '<h3>' + res.data.caseName + '</h3><div class="starLv"><span>星级：</span><div class="lvList" id="lvList"><ul class="clearfix">' +
            lvbox + '</ul></div></div><div class="infolist"><p>市场描述：<i>' + res.data.description + '</i></p><p>发布时间：<i>' +
            getTime(res.data.createTime) + '</i></p><p>面积：<i>' + res.data.area + '平米</i></p></div>';
        $('#comment').attr('data-id', res.data.cid);
        $('.com_comment').attr('data-id', res.data.cid);
        $('.islike').attr('data-id', res.data.cid);
        $('#case_info').html(info_box);
        lvClick();
        //ids, title1, intro1, imgs
        shareLink(res.data.cid, res.data.caseName, res.data.description, imgUrl() + getFirstImg(res.data.imgUrl));
    })
}
getCaseList();

/**
 * 返回设备列表
 */
function backDeviceList() {
    $('.fixedBack').on('click', function() {
        setCookie('targetHref', 'case', 1);
        location.hash = "";
        $('.main_body').load('../html/case.html');
    })
}
backDeviceList();