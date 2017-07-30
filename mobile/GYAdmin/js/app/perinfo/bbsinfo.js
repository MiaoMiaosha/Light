$(function() {
    $('.bbsInfo_body ').on('click', 'h3', function() {
        console.log(123);
        console.log(222);
        console.log($(this));
    })
});

/**
 * 获取帖子详情
 */
function getPostDetail() {
    var postBox = $('#infobox'),
        postCtn = '';
    var target, pid;
    var urlParam = window.location.href.split('#');
    if (urlParam.length > 1 && urlParam[1] != '') {
        pid = urlParam[1].split('=')[1];
    } else {
        target = getStr('bbsinfo').split('-');
        pid = target[1];
    }
    $.get(urlInfo() + '/admin/post/list', {
        'pid': pid
    }, function(res) {
        //判断是否存在img以及word
        var imgBox = "",
            docBox = "";
        if (res.data[0].element.uploadImg != '') {
            var imgLen = res.data[0].element.uploadImg.split('#');
            for (var i in imgLen) {
                if (imgLen[i] != '')
                // <p>' + imgLen[i] + '</p>
                    imgBox += '<li class="ilis"><img src="' + imgUrl() + imgLen[i] +getShuiYin()+'"></li>';
            }
        }
        if (res.data[0].element.uploadDoc != '') {
            var docLen = res.data[0].element.uploadDoc.split('#');
            for (var i in docLen) {
                if (docLen[i] != '')
                // <p>' + docLen[i] + '</p>
                    docBox += '<li class="ilis"><a href="' + imgUrl() + docLen[i] + getShuiYin()+'"><img src="../../imgs/word.png"></a></li>';
            }
        }
        postCtn = '<h3>合同阶段</h3><div class="lvbox"><div class="lv-ctn"><p class="lv-title">' + res.data[0].element.postTitle + '</p>' +
            '<p class="lv-text">' + res.data[0].element.content + '</p><ul class="clear">' + imgBox + '</ul><ul class="clear">' + docBox +
            '<ul/></div><div class="lv-user"><div class="ititle"><div class="uinfo">' +
            '<img src="' + res.data[0].userImg + '" alt=""><p class="name">' + res.data[0].element.nickName + '</p></div>' +
            '<p>' + getTimeToDetail(res.data[0].element.createTime) + '</p><p><i class="icon-comment1"></i>' + res.data[0].commentCount + '评论</p></div></div></div>' +
            '<div class="lv-islike"><ul class="clearfix"><li class="f_left ilis" data-id="' + res.data[0].element.pid + '"><i class="icon-share"></i><span>分享</span></li>' +
            '<li class="f_left ilis collect" data-iscollect="0"  data-id="' + res.data[0].element.pid + '"><i class="icon-star"></i><span>收藏</span></li>' +
            '<li class="f_left ilis com_comment" data-id="' + res.data[0].element.pid + '"><i class="icon-comment"></i><span>回复</span></li>' +
            '</ul></div></div>';
        $(postBox).html(postCtn);
        $('.com_comment').attr('data-id', res.data[0].element.pid);
        zoomPic();
        replyCommentForPost();
        collect();
    }, 'json');
}
getPostDetail();


/**
 * 收藏
 */
function collect() {
    var collectBtn = $('.collect');
    $(collectBtn).on('click', function() {
        if ($(this).attr('data-iscollect') == 0) {
            $(this).attr('data-iscollect', 1).css('color', '#fda80f').find('i').css('color', '#fda80f');
        } else {
            $(this).attr('data-iscollect', 0).css('color', '#999').find('i').css('color', '#999');
        }
    })
}

/**
 * 获取评论列表 
 */
function getCommentList() {
    var commentBox = $('#commentList'),
        commentCtn = '';
    var target = '',
        postId = '';
    if(getStr('bbsinfo') != ''){
        target = getStr('bbsinfo').split('-');
        postId = target[1];
    }else{
        if (location.href.split('?pid=').length > 1) {
            target = location.href.split('?pid=');
            postId = target[1];
        }
    }
    $.post(urlInfo() + '/admin/pcomment/list', {
        'postId': postId
    }, function(res) {
        $.each(res.data, function() {
            var imgBox = "";
            if (this.element.imgUrl != '') {
                var imgLen = this.element.imgUrl.split('#');
                for (var i in imgLen) {
                    if (imgLen[i] != '')
                    // <p>' + imgLen[i] + '</p>
                        imgBox += '<li class="ilis"><img src="' + imgUrl() + imgLen[i] + getShuiYin()+'"></li>';
                }
            }
            if (this.element.parentId == 0) {
                commentCtn += '<li class="clis" data-uid="' + this.element.loginUserId + '" data-id="' + this.element.pcid + '"><div class="comItem">' +
                    '<div class="ititle"><div class="uinfo"><img src="' + this.userImg + '" alt="">' + '<p class="name">' +
                    this.element.loginUserName + '</p></div><p><a href="javascript:void(0)" data-target="commentAdd#' + this.element.pcid +
                    '#' + this.element.postId + '">回复</a></p></div>' +
                    '<div class="ctn"><p>' + this.element.content + '</p><ul class="clear">' + imgBox + '</ul>' +
                    '</div><div class="footerbtn"><p>' + getTimeToDetail(this.element.createTime) + '</p></div></div></li>';
            } else {
                commentCtn += '<li class="clis" data-uid="' + this.element.loginUserId + '" data-id="' + this.element.pcid + '"><div class="comItem">' +
                    '<div class="ititle"><div class="uinfo"><img src="' + this.userImg + '" alt="">' + '<p class="name">' +
                    this.element.loginUserName + '</p></div><p><a href="javascript:void(0)" data-target="commentAdd#' + this.element.pcid +
                    '#' + this.element.postId + '">回复</a></p></div>' +
                    '<div class="ctn"><p class="replyCbyl">回复 ' + this.element.parentUserName + ':</p><p>' + this.element.content + '</p><ul class="clear">' + imgBox + '</ul>' +
                    '</div><div class="footerbtn"><p>' + getTimeToDetail(this.element.createTime) + '</p></div></div></li>';
            }
        })
        $(commentBox).html(commentCtn);
        zoomPic();
        replyCommentForPerson();
    }, 'json');

}
getCommentList();
/**
 * 帖子回复某个人-->comment_add 通用
 */

function replyCommentForPerson() {
    var replyBtn = $('#commentList .clis');
    var prePage = 'bbsinfo';
    $(replyBtn).on('click', 'a', function() {
        var target = $(this).attr('data-target').split('#');
        var tPage = target[0],
            tid = target[1],
            pid = target[2];
        setStr('commentAdd', prePage + '-' + tid + '-' + pid);
        var href = window.location.href;
        if (href.split('#').length > 1 && href.split('#')[1] != '') {
            // window.location.href = href.split('#')[0];
            // location.replace(href.split('#')[0]);
            location.hash = "";
        }
        $('.main_body').load('../perInfo/' + tPage + '.html');
    })
}
/**
 * 回复帖子
 */
function replyCommentForPost() {
    var replyBtn = $('.com_comment');
    var prevPage = 'bbsinfo';
    $(replyBtn).on('click', function() {
        var target = $(this).attr('data-id');
        setStr('commentAdd', prevPage + '-' + target);
        var href = window.location.href;
        if (href.split('#').length > 1 && href.split('#')[1] != '') {
            // window.location.href = href.split('#')[0];
            // location.replace(href.split('#')[0]);
            location.hash = "";
        }
        $('.main_body').load('../perInfo/commentAdd.html');
    })
}

/**
 * 放大图片
 */
function zoomPic() {
    var prevImg = $('img');
    $(prevImg).on('click', function() {
        if ($(this).parent()[0].tagName.toLowerCase() != 'a') {
            var prevImgSrc = $(this).attr('src');
            $('.dialog1').remove();
            var dialogCtn = '<div class="dialog1 d_none"><div class="mask"></div>' +
                '<div class="dialog1Img trans"><img class="trans" src="' + prevImgSrc + '">' +
                '</div></div>';
            $('body').append(dialogCtn);
            var imgWh = getImgInitWH(prevImgSrc).split('-'),
                iWidth = imgWh[0],
                iHeight = imgWh[1];
            dialogAnimation();
            var bodyImgH = $('.dialog1 img').height();
            $('.dialog1 .dialog1Img').css({
                'top': '50%',
                'margin-top': -bodyImgH / 2 + 'px'
            })
        }
    })
}

function getImgInitWH(imgSrc) {
    var images = new Image();
    images.src = imgSrc;
    var initWidth = images.width,
        initHeight = images.height;
    return initWidth + '-' + initHeight;
}