$(function() {
    $('.collect_body').css('min-height', $('html').height() - 108);
});
//删除收藏
function delCollect() {
    var colList = $('.collectList .clis');
    $.each(colList, function() {
        $(this).on('click', '.close', function(e) {
            e.stopPropagation();
            var _this = this;
            var id = $(_this).attr('data-id');
            //取消收藏
            $.post(urlInfo() + '/like/delete', {
                'id': id
            }, function(res) {
                if (res.status.code == 200) {
                    $(_this).parent().remove();
                }
            });
        })
    })
}


/**
 * 获取收藏列表
 */
function getCollectList() {
    var userId = getCookie('wxUserId');
    var cBox = $('#collectList'),
        cCtn = '',
        ictn = '',
        typedata = '';
    $.get(urlInfo() + '/like/list', {
        'userId': userId
    }, function(res) {
        $.each(res.data, function(i) {
            if (this.type == 1) {
                ictn = '<i class="icon-news"></i>';
                typedata = 'data-go="caseDc?caseId=' + this.contentId + '"';
            } else if (this.type == 2) {
                ictn = '<i class="icon-icon-test"></i>';
                typedata = 'data-go="marketDc?marketId=' + this.contentId + '"';
            } else if (this.type == 3) {
                ictn = '<i class="icon-shebeiyunwei"></i>';
                typedata = 'data-go="devicesDc?goodsId=' + this.contentId + '"';
            }
            cCtn += '<li class="clis p_rel" ' + typedata + ' data-id="' + this.id + '" data-cid="' + this.contentId + '" data-type="' + this.type + '">' +
                '<h3>' + ictn + this.content + '</h3><p>收藏时间：' + getTime(this.createTime) + '</p><i class="icon-close close" data-id="' + this.id + '"></i></li>';
        })
        cBox.html(cCtn);
        delCollect();
        goDetails();
    }, 'json')
}
getCollectList();

/**
 * 去对应的详情
 */
function goDetails() {
    var item = $('#collectList .clis'),
        ctnBox = $('.main_body');
    $.each(item, function(key, data) {
        $(data).on('click', function() {
            var href = $(this).attr('data-go').split('?')[0],
                Id = $(this).attr('data-go').split('=')[1];
            if (href == 'caseDc') {
                setStr('caseId', Id, 1);
            } else if (href == 'devicesDc') {
                setStr('goodsId', Id, 1);
            } else if (href == 'marketDc') {
                setStr('marketId', Id, 1);
            }
            location.href = "../index.html#" + href;
            //需要保存一个值 用于其他页面使用
            // setCookie('caseId', caseId, 1);
        })
    })
}