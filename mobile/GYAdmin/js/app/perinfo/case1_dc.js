$(function() {
    // $('.caseDc_body').height($('html').height() - 168);
    $('#comment').on('click', function() {
        $('.main_body').load('../html/comment.html');
    });
    $('#case').on('click', function() {
        $('.main_body').load('../html/case.html');
    });
});
//星级
function lvClick() {
    var lvList = $('#lvList .slis');
    $.each(lvList, function() {
        //没点之前判断亮了几个星星
        if ($(this).attr('data-isLv') == 1) {
            //$(this).prevAll().find('i').removeClass('icon-star').addClass('icon-star2');
            $(this).find('i').removeClass('icon-star').addClass('icon-star2');
        } else if ($(this).attr('data-isLv') == 0) {
            $(this).find('i').removeClass('icon-star2').addClass('icon-star');
        }
        $(this).on('click', function() {
            if ($(this).attr('data-isLv') == 0) {
                $(this).attr('data-isLv', 1).prevAll().attr('data-isLv', 1);
                $(this).prevAll().find('i').removeClass('icon-star').addClass('icon-star2');
                $(this).find('i').removeClass('icon-star').addClass('icon-star2');
            } else if ($(this).attr('data-isLv') == 1) {
                $(this).nextAll().attr('data-isLv', 0);
                $(this).nextAll().find('i').removeClass('icon-star2').addClass('icon-star');
            }
        })
    })
}
//收藏
function isLike() {
    var likeBtn = $('#oplist .islike');
    $(likeBtn).on('click', function() {
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

//访客评论
function commentList() {

}
//更多案例
function moreCase() {

}

//获取案例详情
function getCaseList() {
    var dot_box = $('#cdot_box'),
        pic_box = $('#cpic_box');
    $.get(urlInfo() + '/case/detail', {
        caseId: getCookie('caseId')
    }, function(res) {
        //判断图片的数量 放置到轮播模块里
        if (res.data.imgUrl.split('#').length > 1) {
            var imglist = res.data.imgUrl.split('#');
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
                    pic_item += '<div class="item active"><img src="' + imgUrl() + imglist[i] +getShuiYin()+ '" alt="' + res.data.caseName + '" title="' + res.data.caseName + '" /></div>';
                } else {
                    dot_item += '<li data-target="#casePic" data-slide-to="' + i + '"></li>';
                    pic_item += '<div class="item"><img src="' + imgUrl() + imglist[i] + getShuiYin()+'" alt="' + res.data.caseName + '" title="' + res.data.caseName + '" /></div>';
                }
            }
            $(dot_box).html(dot_item);
            $(pic_box).html(pic_item);
        }
        //显示数据
        var info_box = '',
            lvbox = '';
        for (var i = 0; i < res.data.level; i++) {
            lvbox += '<li class="f_left slis" data-isLv="1"><i class="icon-star"></i></li>';
        }
        if (res.data.level < 5) {
            for (var i = 0; i < (5 - res.data.level); i++) {
                lvbox += '<li class="f_left slis" data-isLv="0"><i class="icon-star"></i></li>';
            }
        }
        info_box = '<h3>' + res.data.caseName + '</h3><div class="starLv"><span>星级：</span><div class="lvList" id="lvList"><ul class="clearfix">' +
            lvbox + '</ul></div></div><div class="infolist"><p>市场描述：<i>' + res.data.description + '</i></p><p>发布时间：<i>' +
            getTime(res.data.createTime) + '</i></p><p>面积：<i>' + res.data.area + '</i></p> <p>摊位号：<i>25445678</i></p></div>';
        $('#case_info').html(info_box);
        lvClick();
    })
}
getCaseList();