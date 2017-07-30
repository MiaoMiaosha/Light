$(function() {
    hideBottomMenu();
})

/**
 * 添加设备
 */
function goodsAdd() {
    $('#goodsApply').on('click', function() {
        var userid = getCookie('wxUserId');
        $('#goodsForm input[name=userId]').val(userid);
        var imglis = $('#goodsForm .picGroup img'),
            imgUrls = '';
        $.each(imglis, function() {
            imgUrls += $(this).attr('data-key') + '#';
        });
        imgUrls = imgUrls.substring(0, imgUrls.length - 1);
        $('#goodsPrice').val(toInt($('#goodsPrice').val()));
        $('#goodsForm').find('input[name=img]').val(imgUrls);
        var formDate = $('#goodsForm').serialize();
        $.post(urlInfo() + '/goods/add', formDate, function(res) {
            if (res.status.code == 200) {
                alert('添加成功');
                location.href = 'perInfo/index.html';
            } else {
                alert(res.status.msg);
            }
        })
    })
}
goodsAdd();

/**
 * 图片添加
 */
//上传图片
function addPic() {
    var addBtn = $('#goodsAdd');
    $(addBtn).on('click', function() {
        // var hideIpt = $(this).siblings('.com_fileup');
        var picGroupElm = $(this).parents('.filebox').next('.picbox');
        if (limitUploadNum(picGroupElm)) {
            $(this).siblings('.com_fileup').trigger('click');
        } else {
            alert('每次上传的数量不得超过十张');
        }
    })
}
addPic();
//上传即时显示图片
function getGoodsImg(event) {
    var file = event.files[0],
        reader = new FileReader(),
        picGroup = $(event).parents('.filebox').next('.picbox').find('ul'),
        tokenVal = getToken(),
        randomKey = getRandomKey();
    // console.log(file);
    // console.log(file.name.split('.')[1]);
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
                var upJson = eval('(' + xhr.responseText + ')');;
                var li = '<li class="f_left plis"><img data-key="' + upJson.data.key + '" src="' + imgUrl() + upJson.data.key + getShuiYin()+ '" alt="">' +
                    '<p data-delkey="' + upJson.data.key + '" class="del">删除</p></li>';
                $(picGroup).append(li);
                // delGoodsPic();
                $(event).val('');
            }
        };
        xhr.upload.onprogress = function(evt) {
            if (evt.lengthComputable) {
                var percentComplete = Math.round(evt.loaded * 100 / evt.total);
                if (percentComplete != 100) {
                    $(event).siblings('.com_add').prop('disabled', true).text('正在上传中..');
                    $('#goodsApply').prop('disabled', true);
                } else {
                    $(event).siblings('.com_add').prop('disabled', false).text('上传文件');
                    $('#goodsApply').prop('disabled', false);
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
 * 删除图片
 */
function delGoodsPic() {
    var delBtn = $('.picGroup');
    $(delBtn).on('click', '.del', function() {
        var _this = this;
        var key = $(_this).attr('data-delkey');
        $.post(urlInfo() + '/upload/delete', {
            'key': key
        }, function(res) {
            if (res.status.code == 200) {
                $(_this).parents('.plis').remove();
            }
        })
    })
}
delGoodsPic();