$(function() {
    tabCur();
});
//获取新闻列表
function getNewsList() {
    var newsbox = $('#newList'),
        lis = $('.com_li'),
        newCtn = '';
    $.get(urlInfo() + '/news/list', {
        'type': 1
    }, function(res) {
        if (res.data == '') {
            newCtn = '<p class="t_center">没有数据</p>';
        } else {
            $.each(res.data, function(key, data) {
                newCtn += '<div class="col-xs-12"><div class="nItem" data-id="' + data.nid + '" data-userName="' + data.userName +
                    '" data-title="' + data.newsTitle + '" data-pTime="' + getTime(data.createTime) + '" data-vCount="' + data.viewCount +
                    '" data-content="' + data.content + '">' +
                    '<div class="imgbox"><img src="' + imgUrl() + data.imgUrl + getShuiYin()+'" alt="" title="" /></div>' +
                    '<div class="nTitle"><h3>' + data.newsTitle + '</h3><div class="time"><span>' + getTime(data.createTime) + '</span>' +
                    '<div class="view f_right"><span>浏览：' + data.viewCount + '</span><span>评论：32</span></div></div></div></div></div>';
            });
        }
        $(newsbox).html(newCtn);
        getNewsDetails();
    });
    $(lis).on('click', '.tlis', function() {
        var curCtn = '';
        $(newsbox).html('');
        $.get(urlInfo() + '/news/list', {
            'type': $(this).attr('data-type')
        }, function(res) {
            if (res.data == '') {
                curCtn = '<p class="t_center">没有数据</p>';
            } else {
                $.each(res.data, function(key, data) {
                    curCtn += '<div class="col-xs-12"><div class="nItem" data-id="' + data.nid + '" data-userName="' + data.userName +
                        '" data-title="' + data.newsTitle + '" data-pTime="' + getTime(data.createTime) + '" data-vCount="' + data.viewCount +
                        '" data-content="' + data.content + '">' +
                        '<div class="imgbox"><img src="' + imgUrl() + data.imgUrl + getShuiYin()+'" alt="" title="" /></div>' +
                        '<div class="nTitle"><h3>' + data.newsTitle + '</h3><div class="time"><span>' + getTime(data.createTime) + '</span>' +
                        '<div class="view f_right"><span>浏览：' + data.viewCount + '</span><span>评论：32</span></div></div></div></div></div>';
                });
            }
            $(newsbox).html(curCtn);
            getNewsDetails();
        });
    });
}
getNewsList();

//新闻详情
function getNewsDetails() {
    var itemBox = $('.newsboxs'),
        items = $(itemBox).find('.nItem'),
        lis = $('.com_li');
    $.each(items, function(key, data) {
        $(data).on('click', function() {
            $(itemBox).html('');
            var id = $(this).attr('data-id'),
                uName = $(this).attr('data-userName'),
                title = $(this).attr('data-title'),
                ptime = $(this).attr('data-pTime'),
                count = $(this).attr('data-vCount'),
                content = $(this).attr('data-content');
            var ctn = '<div class="nD_body"><div class="nd_title" data-nid="' + id + '">' +
                '<h3>' + title + '</h3>' +
                '<p>作者：' + uName + ' 发布时间：' + ptime + ' 浏览量：' + count + '</p></div>' +
                '<div class="nd_content">' + content + '</div></div>';
            $(itemBox).html(ctn);
        })
    });
}