$(function() {
    // $('.comment_body').height($('body').height() - 168);
});
//添加评论
function addComment() {
    var items = $('.commentItem'),
        ctnBox = $('.main_body');
    $.each(items, function(key, data) {
        $(data).on('click', '.c_header i', function() {
            $(ctnBox).load('../html/commentAdd.html');
        })
    })
}
addComment();

/**
 * 得到评论--根据type得到
 */
function getCommentList() {
    var type = getCookie('commentType'),
        id = getCookie('commentId'),
        userid = getCookie('wxUserId'),
        commentBox = $('#commentBox'),
        commentCtn = '';
    $.get(urlInfo() + '/comment/list', {
        'type': type,
        'typeId': id
            // 'userId': userid
    }, function(res) {
        $.each(res.data, function() {
            var piclist = '';
            if (this.imgUrl != '') {
                var pics = this.imgUrl.split('#');
                for (var i = 0; i < pics.length; i++) {
                    piclist += '<li class="f_left plis"><img src="' + imgUrl() + pics[i] + getShuiYin()+'" alt="" title="" /></li>';
                }
            }
            var defaultImg = '';
            if (this.headImg == '' || this.headImg == null) {
                defaultImg = '../images/user1.png';
            } else {
                defaultImg = this.headImg;
            }
            commentCtn += '<div class="commentItem"><div class="row"><div class="col-xs-3"><img src="' + defaultImg + '" alt="" title="" />' +
                '</div><div class="col-xs-9"><div class="commentCtn"><div class="c_header"><p>' + this.nickName +
                '<span class="title"></span></p></div><div class="c_body"><p>' + this.content + '</p></div>' +
                '<div class="picBox"><ul class="clearfix">' + piclist + '</ul></div><div class="c_footer"><p>' + getTime(this.createTime) + '</p>' +
                '</div></div></div></div></div>';
        })
        commentBox.html(commentCtn);
        zoomPic();
    }, 'json');
}
getCommentList();