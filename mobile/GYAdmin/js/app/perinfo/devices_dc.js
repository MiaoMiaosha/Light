$(function() {
    // $('.devices_body').height($('html').height() - 168);
    $('#comment').on('click', function() {
        $('.main_body').load('../html/comment.html');
    });
    $('#device').on('click', function() {
        $('.main_body').load('../html/devices.html');
    });
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

//获取设备详情
function getDeviceInfo() {
    var dot_box = $('#dotlist'),
        pic_box = $('#piclist');
    $.get(urlInfo() + '/goods/detail', {
        goodsId: getCookie('goodsId')
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
                    dot_item += '<li data-target="#devicePic" data-slide-to="' + i + '"></li>';
                    pic_item += '<div class="item"><img src="' + imgUrl() + imglist[i] +getShuiYin()+'" alt="' + res.data.goodsName + '" title="' + res.data.goodsName + '" /></div>';
                }
            }
            $(dot_box).html(dot_item);
            $(pic_box).html(pic_item);
        }
        //显示数据
        var info_box = '';
        info_box = '<h3>' + res.data.goodsName + '</h3><p class="notes"><i class="icon-warn-copy"></i>本商品不属于定制购买，您可以在线提交咨询。</p>' +
            '<p class="intro">' + res.data.description + '</p><div class="prices"><span class="tehui">商城价：<i>¥' + (res.data.goodsPrice / 100) +
            '</i></span><span>市场价：<i>¥' + (res.data.goodsPrice / 100 * 1.5) + '</i></span></div>';
        $('#device_info').html(info_box);
    }, 'json')
}
getDeviceInfo();