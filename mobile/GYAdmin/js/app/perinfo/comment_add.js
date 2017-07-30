/**
 * 添加图片
 */
function addPic() {
    var addBtn = $('#addPic');
    $(addBtn).on('click', function() {
        var picGroupElm = $(this).parents('.filebox').next('.picbox');
        if (limitUploadNum(picGroupElm))
            $(this).siblings('#fileup').trigger('click');
        else alert('每次上传的数量不得超过十张');
    })
}
addPic();
//上传即时显示图片
function getImg(event) {
    var file = event.files[0],
        reader = new FileReader(),
        picGroup = $(event).parents('.filebox').next('.picbox').find('ul'),
        tokenVal = getToken(),
        randomKey = getRandomKey();
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        //七牛云跨域问题
        var pic64 = this.result.split(',')[1], //暂时可以分割逗号得到
            xhr = new XMLHttpRequest(),
            xhrget = new XMLHttpRequest(),
            obj;
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4) {
                // var upJson = JSON.parse(xhr.responseText);
                var upJson = eval('(' + xhr.responseText + ')');
                var li = '<li class="f_left plis" data-keys="' + upJson.data.key + '"><img src="' + imgUrl() + upJson.data.key + getShuiYin() +'" alt="">' +
                    '<p data-delkey="' + upJson.data.key + '" class="del">删除</p></li>';
                // $(event).siblings('input').val(upJson.data.key);
                $(picGroup).append(li);
                // delPic();
                $(event).val('');
            }
        };
        xhr.upload.onprogress = function(evt) {
            if (evt.lengthComputable) {
                var percentComplete = Math.round(evt.loaded * 100 / evt.total);
                if (percentComplete != 100) {
                    $(event).siblings('#addPic').prop('disabled', true).text('正在上传中..');
                    $('#reply').prop('disabled', true);
                } else {
                    $(event).siblings('#addPic').prop('disabled', false).text('上传文件');
                    $('#reply').prop('disabled', false);
                }
            }
        }
        xhr.open("POST", getImgServer(file.size), true);
        xhr.setRequestHeader("Content-Type", "application/octet-stream");
        xhr.setRequestHeader("Authorization", "UpToken " + tokenVal);
        xhr.send(pic64);
    };
}

/**
 * 多选员工
 */
function moreSelect() {
    var cBox = $('.devide-box'),
        sBox = $('.select-box');
    $(cBox).on('click', function() {
        if ($(this).attr('data-open') == 0) {
            $(this).attr('data-open', 1);
            $(this).siblings('.select-box').removeClass('d_none');
        } else {
            $(this).attr('data-open', 0);
            $(this).siblings('.select-box').addClass('d_none');
        }
    });
    $(sBox).on('click', '.slis', function() {
        var spanBox = '<span data-id="' + $(this).attr('data-id') + '">' + $(this).text() + '<i class="icon-guanbifuzhi"></i></span>';
        $(this).parents('.select-box').siblings('.devide-box').append(spanBox);
        $(this).parents('.select-box').addClass('d_none').siblings('.devide-box').attr('data-open', 0);
        $(this).remove();
    });
    $(cBox).on('click', '.icon-guanbifuzhi', function(e) {
        e.stopPropagation(); //阻止事件向上冒泡
        var sName = $(this).parent().text(),
            sId = $(this).parent().attr('data-id'),
            sli = '';
        sli = '<li class="slis" data-id="' + sId + '">' + sName + '</li>';
        $(this).parents('.devide-box').siblings('.select-box').find('ul').append(sli);
        $(this).parent().remove();
    })
}

/**
 * 员工列表
 */
function getStaffList() {
    //获取员工/合同人列表
    var eBox = $('#employee-box ul'),
        eCtn = '';
    $.get(urlInfo() + '/admin/employee/list', function(res) {
        $.each(res.data, function(key, data) {
            eCtn += '<li class="slis" data-id="' + data.eid + '">' + data.name + '</li>';
        })
        $(eBox).html(eCtn);
        moreSelect();
    }, 'json');
}
getStaffList();

/**
 * 显示员工下拉框
 */
function showStaffList(event) {
    if ($(event).val() == 2) {
        $('.emlist').removeClass('d_none');
    } else {
        $('.emlist').addClass('d_none');
    }
}

/**
 * 通用回复--帖子
 */
function replyContent() {
    var prePage = getStr('commentAdd').split('-'),
        postId = '',
        commentId = '',
        replyBackPage = prePage[0];
    if (prePage.length > 2) {
        postId = prePage[2];
        commentId = prePage[1];
    } else {
        postId = prePage[1];
        commentId = 0;
    }
    $('#reply').on('click', function() {
        var userid = getStr('userid');
        var openId = getStr('openId');
        var piclis = $(this).parent().siblings('.picbox').find('li'),
            picUrl = '';
        for (var i = 0; i < piclis.length; i++) {
            picUrl += $(piclis[i]).attr('data-keys') + '#';
        }
        picUrl = picUrl.substring(0, picUrl.length - 1);

        var employeeId = '',
            employee = $('#employeebox span');
        if (employee.length != 0) {
            for (var i = 0; i < employee.length; i++) {
                employeeId += $(employee[i]).attr('data-id') + ":";
            }
            employeeId = employeeId.substring(0, employeeId.length - 1);
        }
        $('#replyForm').find('input[name=imgUrl]').val(picUrl);
        $('#replyForm').find('input[name=openId]').val(openId);
        $.post(urlInfo() + '/admin/pcomment/reply', {
            'loginUserId': userid,
            'openId': openId,
            'postId': postId,
            'content': $('#content').val(),
            'parentId': commentId,
            'imgUrl': picUrl,
            'type': $('#type').val(),
            'employeeIds': employeeId
        }, function(res) {
            if (res.status.code == 200) {
                alert('回复成功'); //需要父页面标记 不然不知道回复成功后跳转回哪个页面
                $('.main_body').load('../perInfo/perinfo.html');
            } else {
                alert(res.status.msg);
            }
        })
    })
}
replyContent();

/**
 * 删除图片
 */
function delPic() {
    var delBtn = $('.picGroup');
    $(delBtn).on('click', '.del', function() {
        var _this = this;
        var key = $(_this).attr('data-delkey');
        $.post(urlInfo() + '/upload/delete', {
            'key': key
        }, function(res) {
            if (res.status.code == 200) {
                $(_this).parents('.plis').remove();
            } else {
                alert(res.status.msg);
            }
        })
    })
}
delPic();