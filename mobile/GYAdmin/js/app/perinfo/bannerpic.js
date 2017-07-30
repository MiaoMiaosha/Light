function addPic() {
    var addBtn = $('#addPic');
    $(addBtn).on('click', function() {
        $(this).siblings('#fileup').trigger('click');
    })
}
addPic();
//上传即时显示图片
function getImg(event) {
    var file = event.files[0],
        reader = new FileReader(),
        picGroup = $('#picGroup ul'),
        tokenVal = getToken(),
        randomKey = getRandomKey();
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        //七牛云跨域问题
        var pic64 = this.result.split(',')[1], //暂时可以分割逗号得到
            xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4) {
                // var upJson = JSON.parse(xhr.responseText);
                var upJson = eval('(' + xhr.responseText + ')');
                var li = '<li class="f_left plis"><img src="' + imgUrl() + upJson.data.key + getShuiYin()+ '" alt="">' +
                    '<p data-delkey="' + upJson.data.key + '" class="del">删除</p></li>';
                $(event).siblings('#urlKey').val(upJson.data.key);
                $(event).siblings('#addPic').prop('disabled', true);
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
                    $('#save').prop('disabled', true);
                } else {
                    $(event).siblings('#addPic').prop('disabled', false).text('上传文件');
                    $('#save').prop('disabled', false);
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
 * 保存
 */
function savePic() {
    $('#save').on('click', function() {
        var formData = $('#saveForm').serialize();
        $.post(urlInfo() + '/admin/config/setindex', formData, function(res) {
            if (res.status.code == 200) {
                alert('添加成功!');
                location.href = uuuu() + "/GYAdmin/html/perInfo/index.html"
            } else {
                alert(res.status.msg);
            }
        }, 'json')
    })
}
savePic();

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

/**
 * 第二张广告图的链接设置
 */
function showAd2Link(event){
    var thisVal = $(event).val();
    if(thisVal == 1){
        $('.addlink').addClass('d_none').find('input').val('');
    }else{
        $('.addlink').removeClass('d_none');
    }
}