$(function() {
    $('.com_body').css('min-height', $('html').height() - 48);
});

/**
 * 根据角色显示tab
 */
function showTabRole() {
    var roleId = getStr('showPage'),
        type = $('#ubType ul'),
        typeCtn = '';
    if (roleId == 'kh') {
        typeCtn = '<li class="f_left ulis b1" data-type="0">与我相关</li><li class="f_left ulis" data-type="2">我的发布</li>';
    } else if (roleId == 'yg') {
        typeCtn = '<li class="f_left ulis b1" data-type="0">与我相关</li><li class="f_left ulis" data-type="1">@我的</li>';
    }
    type.html(typeCtn);
}
showTabRole();

/**
 * 获取我解答列表
 */
function getAnswerList() {
    var answerBox = $('#answerList'),
        answerCtn = '',
        tabCur = $('.ubType .ulis');
    $.get(urlInfo() + '/admin/post/mylist', {
        'type': 0
    }, function(res) {
        var html1 = '';
        $.each(res.data, function() {
            html1 += '<li class="uitem" data-id="' + this.pid + '" data-target="bbsinfo"><div class="ubBox"><div class="ititle" data-type="' + this.type + '"><input type="checkbox">合同阶段' +
                '<p>' + this.marketName + '</p></div><div class="ctn"><p>' + this.postTitle + '</p></div><div class="footerbtn">' +
                '<p>' + getTime(this.createTime) + '<span><i class="icon-comment1 comment"></i>' + this.commentCount + '评论</span></p></div></div></li>';
        })
        $(answerBox).empty().html(html1);
        postInfo();
    }, 'json');
    $(tabCur).on('click', function() {
        $(this).addClass('cur').siblings('li').removeClass('cur');
        var type = $(this).attr('data-type');
        $.get(urlInfo() + '/admin/post/mylist', {
            'type': type
        }, function(res) {
            var html2 = '';
            $.each(res.data, function() {
                html2 += '<li class="uitem" data-id="' + this.pid + '" data-target="bbsinfo"><div class="ubBox"><div class="ititle" data-type="' + this.type + '"><input type="checkbox">合同阶段' +
                    '<p>' + this.marketName + '</p></div><div class="ctn"><p>' + this.postTitle + '</p></div><div class="footerbtn">' +
                    '<p>' + getTime(this.createTime) + '<span><i class="icon-comment1 comment"></i>' + this.commentCount + '评论</span></p></div></div></li>';
            })
            $(answerBox).empty().html(html2);
            postInfo();
        }, 'json');
    })
}
getAnswerList();


function postInfo() {
    var prePage = 'userbbs';
    var clis = $('#answerList .uitem');
    $(clis).on('click', function() {
        var id = $(this).attr('data-id'),
            target = $(this).attr('data-target');
        setStr('bbsinfo', prePage + '-' + id);
        $('.main_body').load('../perInfo/' + target + '.html');
    })
}