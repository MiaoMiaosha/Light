function addPic() {
    var addBtn = $('.com_add');
    $(addBtn).on('click', function() {
        $(this).siblings('.com_fileup').trigger('click');
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
                var li = '<li class="f_left plis"><img src="' + imgUrl() + upJson.data.key + getShuiYin() + '" alt="">' +
                    '<p data-delkey="' + upJson.data.key + '" class="del">删除</p></li>';
                $(event).siblings('input').val(upJson.data.key);
                $(event).siblings('.com_add').prop('disabled', true)
                $(picGroup).append(li);
                // delPic();
                $(event).val('');
            }
        };
        xhr.upload.onprogress = function(evt) {
            if (evt.lengthComputable) {
                var percentComplete = Math.round(evt.loaded * 100 / evt.total);
                if (percentComplete != 100) {
                    $(event).siblings('.com_add').prop('disabled', true).text('正在上传中..');
                    $('#addTable').prop('disabled', true);
                } else {
                    $(event).siblings('.com_add').prop('disabled', false).text('上传头像');
                    $('#addTable').prop('disabled', false);
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
 * 添加账户
 */
function accountAdd() {
    $('#addTable').on('click', function() {
        var formDate = $('#accountForm').serialize();
        $.post(urlInfo() + '/admin/config/addacc', formDate, function(res) {
            if (res.status.code == 200) {
                alert('添加成功!');
                location.href = 'account-list.html';
            } else {
                alert('添加失败');
                location.reload();
            }
        })
    })
}
accountAdd();

/**
 * 编辑
 */
function edit() {
    var id = window.location.href.split('?id=')[1];
    if (typeof id == 'undefined') {
        //嘿嘿 我就是啥也不做 你咋地
        $('.changeBtn').removeClass('d_none').siblings('.btn').addClass('d_none');
    } else {
        $('.lockBox').removeClass('d_none');
        $('.changeBtn').addClass('d_none').siblings('.btn').removeClass('d_none');
        //哎 不得不做了
        $.get(urlInfo() + '/admin/login/userlist', {
            'id': id
        }, function(res) {
            $('#roleIds').val(res.data[0].roleIds);
            $('#isLocked').val(res.data[0].isLocked);
            $('#username').val(res.data[0].username);
            $('#nickName').val(res.data[0].nickName);
            $('#password').val(res.data[0].password);
            $('#headImg').val(res.data[0].headImg);
            $('#accountForm input[name=id]').val(res.data[0].id);

            //显示头像
            if (res.data[0].roleIds == 1 || res.data[0].roleIds == 2) {
                if (res.data[0].headImg != '') {
                    var headpic = res.data[0].headImg;
                    var headbox = '<li class="f_left plis"><img src="' + imgUrl() + headpic + '" alt="">' +
                        '<p data-delkey="' + headpic + '" class="del">删除</p></li>';
                    $('#picGroup ul').html(headbox);
                    $('#addPic').prop('disabled', true);
                }
            } else {
                if (res.data[0].headImg != '') {
                    var headpic = res.data[0].headImg;
                    var headbox = '<li class="f_left plis"><img src="' + headpic + '" alt="">' +
                        '</li>';
                    $('#picGroup ul').html(headbox);
                    $('#addPic').prop('disabled', true);
                }
            }
        })
    }
}
edit()

/**
 * 编辑保存
 */
function saveAccount() {
    $('#editTable').on('click', function() {

        // var isLock = $('#isLocked').val(),
        //     username = $("#username").val(),
        //     nickname = $('#nickName').val(),
        //     password = $('#password').val(),
        //     headImg = $('#headImg').val(),
        //     id = $('#accountForm input[name=id]').val();
        var formDate = $('#accountForm').serialize();
        $.post(urlInfo() + '/admin/config/editacc', formDate, function(res) {
            if (res.status.code == 200) {
                alert('修改成功!');
                location.href = 'account-list.html';
            } else {
                alert('修改失败');
                location.reload();
            }
        })
    })
}
saveAccount();

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
                $('#addPic').prop('disabled', false);
            } else {
                alert(res.status.msg);
            }
        })
    })
}
delPic();