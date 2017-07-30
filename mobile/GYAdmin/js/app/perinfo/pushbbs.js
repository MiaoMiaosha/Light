// function addFile() {
//     var addBtn = $('#addFile'),
//         hideIpt = $('#fileup');
//     $(addBtn).on('click', function() {
//         $(hideIpt).trigger('click');
//     })
// }
// addFile();
// //上传即时显示图片
// function getFile(event) {
//     var file = event.files[0];
//     $(event).next().text('已选择' + file.name);
// }



/**
 * 获取帖子列表
 */
function getPostList(typeid) {
    var postBox = $('#postList'),
        postCtn = '';
    var projectId = getStr('projectId'),
        type = '';
    if (typeid == 'undefined' || typeid == null || typeid == '') {
        type = '';
    } else {
        type = typeid;
    }
    $.ajaxSetup({
        async: false
    })
    $.get(urlInfo() + '/admin/project/postlist', {
        'projectId': projectId,
        'type': type
    }, function(res) {
        $.each(res.data, function() {
            postCtn += '<li class="clis" data-id="' + this.element.pid + '" data-target="bbsinfo"><div class="comItem"><div class="ititle"><div class="uinfo">' +
                '<img src="' + this.userImg + '" alt=""><p class="name">' + this.element.nickName + '</p></div>' +
                // <p>' + this.element.postTitle + '</p>
                '</div><div class="ctn"><p>' + this.element.postTitle + '</p></div><div class="footerbtn">' +
                '<p>' + getTimeToDetail(this.element.createTime) + '<span><i class="icon-comment1 comment"></i>' + this.commentCount + '评论</span></p></div></div></li>';
        });
        $(postBox).html(postCtn);
        postInfo();
    }, 'json');
}
getPostList();

/**
 * 获取阶段列表
 */
function getLvList() {
    var lvBox = $('#infobox ul'),
        lvList = '<li class="f_left inlis bb br bg4bacc6" data-id="">所有</li>',
        lvId = '';
    var processid = getStr('processId');
    $.get(urlInfo() + '/admin/project/processlist', {
        'level': 1
    }, function(res) {
        $.each(res.data, function(i) {
            if (i == 0) {
                if (this.id >= processid) {
                    lvList += '<li class="f_left inlis bb bg4bacc6" data-id="' + this.id + '">' + this.name + '</li>';
                } else {
                    lvList += '<li class="f_left inlis bb bgdone" data-id="' + this.id + '">' + this.name + '</li>';
                }
            } else if (i == 1) {
                if (this.id >= processid) {
                    lvList += '<li class="f_left inlis bb br bg49d1a3" data-id="' + this.id + '">' + this.name + '</li>';
                } else {
                    lvList += '<li class="f_left inlis bb br bgdone" data-id="' + this.id + '">' + this.name + '</li>';
                }
            } else if (i == 2) {
                if (this.id >= processid) {
                    lvList += '<li class="f_left inlis bb bg47dc57" data-id="' + this.id + '">' + this.name + '</li>';
                } else {
                    lvList += '<li class="f_left inlis bb bgdone" data-id="' + this.id + '">' + this.name + '</li>';
                }
            } else if (i == 3) {
                if (this.id >= processid) {
                    lvList += '<li class="f_left inlis bb br bg8ce646" data-id="' + this.id + '">' + this.name + '</li>';
                } else {
                    lvList += '<li class="f_left inlis bb br bgdone" data-id="' + this.id + '">' + this.name + '</li>';
                }
            } else if (i == 4) {
                if (this.id >= processid) {
                    lvList += '<li class="f_left inlis bb bgedef45" data-id="' + this.id + '">' + this.name + '</li>';
                } else {
                    lvList += '<li class="f_left inlis bb bgdone" data-id="' + this.id + '">' + this.name + '</li>';
                }
            } else {
                if (this.id >= processid) {
                    lvList += '<li class="f_left inlis br bgf79846" data-id="' + this.id + '">' + this.name + '</li>';
                } else {
                    lvList += '<li class="f_left inlis br bgdone" data-id="' + this.id + '">' + this.name + '</li>';
                }
            }
            // lvList += this.name + ','; //存阶段名称
            // lvId += this.id + ','; //存阶段对应ID
        });
        // lvList = lvList.substring(0, lvList.length - 1);
        // lvId = lvId.substring(0, lvId.length - 1);
        // $(lvBox).attr('data-title', lvList);
        // $(lvBox).attr('data-ids', lvId);
        lvBox.html(lvList);
        showLevList();
        // showLvDialog();
    }, 'json')
}
getLvList();


/**
 * 
 */


/**
 * 阶段弹窗
 */
function showLvDialog() {
    var h3Box = $('#infobox h3');
    $(h3Box).on('click', function() {
        var _this = this;
        var list = $(_this).attr('data-title').split(','),
            listId = $(_this).attr('data-ids').split(','),
            litem = '';
        for (var i in list) {
            litem += '<li class="dlis" data-target="' + listId[i] + '">' + list[i] + '</li>';
        }
        $('.dialog1').remove();
        var dialogCtn = '<div class="dialog1 d_none"><div class="mask"></div>' +
            '<div class="dialogCtn trans" style="height:auto">' +
            '<ul>' + litem +
            '</ul></div></div>';
        $('body').append(dialogCtn);
        dialogLvChoose();
        dialogAnimation();
    })
}

/**
 * 选择弹窗中的阶段
 */
function dialogLvChoose() {
    var dlis = $('.dialog1 .dlis'),
        h3Box = $('#infobox h3');
    $(dlis).on('click', function() {
        var cId = $(this).attr('data-target'),
            cName = $(this).text();
        $(h3Box).attr('data-id', cId).text(cName);
        getPostList(cId);
        $(this).parents('.dialog1').remove();
    })
}

/**
 * 查看帖子详情
 */
function postInfo() {
    var prePage = 'pushbbs';
    var clis = $('#postList .clis');
    $(clis).on('click', function() {
        var id = $(this).attr('data-id'),
            target = $(this).attr('data-target');
        setStr('bbsinfo', prePage + '-' + id);
        var historyArr = [];
        if (getStr('historyArr') != null) {
            historyArr = getStr('historyArr');
            historyArr = JSON.parse(historyArr);
            var abc = { 'hpage': 'usercontract' };
            historyArr.push(abc);
            historyArr = JSON.stringify(historyArr);
            setStr('historyArr', historyArr);
        }
        $('.main_body').load('../perInfo/' + target + '.html');
    })
}

/**
 * 根据阶段显示对应的列表
 */
function showLevList() {
    var levBt = $('#infobox ul');
    $(levBt).on('click', '.inlis', function() {
        var _this = this;
        var typeid = $(_this).attr('data-id');
        getPostList(typeid);
    })
}