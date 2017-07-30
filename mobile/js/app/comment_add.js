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
                    $('#addComment').prop('disabled', true);
                } else {
                    $(event).siblings('#addPic').prop('disabled', false).text('上传文件');
                    $('#addComment').prop('disabled', false);
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
 * 发布评论
 */
function commentAdd() {
    $('#addComment').on('click', function() {
        var piclis = $(this).parent().siblings('.picbox').find('li'),
            picUrl = '';
        for (var i = 0; i < piclis.length; i++) {
            picUrl += $(piclis[i]).attr('data-keys') + '#';
        }
        picUrl = picUrl.substring(0, picUrl.length - 1);
        $('#commentForm').find('input[name=imgUrl]').val(picUrl);
        $('#userId').val(getCookie('wxUserId'));
        $('#type').val(getCookie('addCommentType'));
        $('#typeId').val(getCookie('addCommentTypeId'));
        var formDate = $('#commentForm').serialize();
        $.post(urlInfo() + '/comment/add', formDate, function(res) {
            if (res.status.code == 200) {
                alert('添加成功');
                $('.main_body').load('../html/comment.html')
            } else {
                alert(res.status.msg);
            }
        }, 'json');
    })

}
commentAdd();

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