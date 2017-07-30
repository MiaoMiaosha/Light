//添加操作
function enter_Add() {
    $('#addTable').click(function() {
        var receiveDate = isNaN(getUnix($('#receiveDate').val())) ? '' : getUnix($('#receiveDate').val()),
            invoiceTime = isNaN(getUnix($('#invoiceTime').val())) ? '' : getUnix($('#invoiceTime').val()),
            marketId = $('#marketId').val(),
            marketName = $('#marketId').find('option:selected').text(),
            typeId = $('#typeId').val(),
            receiveBank = $('#receiveBank').val(),
            receiveMoney = toInt($('#receiveMoney').val()),
            invoiceStatus = $('#invoiceStatus').val(),
            invoicePic = $('#invoicePic').val(),
            remark = $('#remark').val();
        $.post(urlInfo() + '/admin/account/commit', {
            'receiveDate': receiveDate,
            'invoiceTime': invoiceTime,
            'marketId': marketId,
            'marketName': marketName,
            'typeId': typeId,
            'receiveBank': receiveBank,
            'receiveMoney': receiveMoney,
            'invoiceStatus': invoiceStatus,
            'invoicePic': invoicePic,
            'remark': remark
        }, function(res) {
            var tRole = getStr('showPage');
            if (res.status.code == 200) {
                alert('添加成功');
                if (tRole == 'yg') {
                    location.href = 'perInfo/index.html';
                } else {
                    window.location.href = "enter-list.html";
                }
            } else {
                alert(res.status.msg);
            }
        })
    });
}
enter_Add()

//获取入账额外信息
function getOtherInfo() {
    var marketBox = $('#marketId'),
        mBox = '';
    $.get(urlInfo() + '/admin/project/marketname', function(res) {
        $.each(res.data, function(key, data) {
            mBox += '<option value="' + data.pid + '">' + data.marketName + '</option>';
        })
        $(marketBox).html(mBox);
    });
    var typeBox = $('#typeId'),
        tBox = '';
    $.get(urlInfo() + '/admin/account/extralist', {
        'typeContent': 0
    }, function(res) {
        $.each(res.data, function(key, data) {
            tBox += '<option value="' + data.id + '">' + data.typeName + '</option>';
        })
        $(typeBox).html(tBox);
    });
    var bankBox = $('#receiveBank'),
        bBox = '';
    $.get(urlInfo() + '/admin/account/extralist', {
        'typeContent': 1
    }, function(res) {
        $.each(res.data, function(key, data) {
            bBox += '<option value="' + data.id + '">' + data.typeName + '</option>';
        })
        $(bankBox).html(bBox);
    })
}
getOtherInfo()

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
                $(event).siblings('#invoicePic').val(upJson.data.key);
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
                    $(event).siblings('.com_add').prop('disabled', false).text('上传图片');
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

//是否编辑
function edit() {
    var id = window.location.href.split('?id=')[1];
    if (typeof id == 'undefined') {
        //嘿嘿 我就是啥也不做 你咋地
        $('.changeBtn').removeClass('d_none').siblings('.btn').addClass('d_none');
    } else {
        $('.check-box').removeClass('d_none');
        $('.changeBtn').addClass('d_none').siblings('.btn').removeClass('d_none');
        //哎 不得不做了
        $.get(urlInfo() + '/admin/account/list', {
            'aid': id
        }, function(res) {
            //要显示图片
            $('#receiveDate').val(getTime(res.data[0].receiveDate));
            $('#invoiceTime').val(getTime(res.data[0].invoiceTime));
            $('#marketId').val(res.data[0].marketId);
            $('#typeId').val(res.data[0].typeId);
            $('#receiveBank').val(res.data[0].receiveBank);
            $('#receiveMoney').val(toFloat(res.data[0].receiveMoney));
            $('#invoiceStatus').val(res.data[0].invoiceStatus);
            $('#invoicePic').val(res.data[0].invoicePic);
            $('#remark').val(res.data[0].remark);
            $('#status').val(res.data[0].status);
            $('#aid').val(res.data[0].aid);
            if (res.data[0].invoicePic != '') {
                var picBox = $('#picGroup ul'),
                    picList = '';
                if (res.data[0].invoicePic.split('#').length == 1) {
                    picList = '<li class="f_left plis"><img src="' + imgUrl() + res.data[0].invoicePic + getShuiYin() + '" alt=""></li>'
                } else {
                    $.each(res.data[0].invoicePic.split('#'), function(key1, data1) {
                        picList += '<li class="f_left plis"><img src="' + imgUrl() + data1 + getShuiYin() + '" alt=""></li>'
                    })
                }
                $(picBox).html(picList);
            }
        })
    }
}
edit();

//提交修改后的数据

function enter_edit() {
    $('#editTable').click(function() {
        var receiveDate = isNaN(getUnix($('#receiveDate').val())) ? '' : getUnix($('#receiveDate').val()),
            invoiceTime = isNaN(getUnix($('#invoiceTime').val())) ? '' : getUnix($('#invoiceTime').val()),
            marketId = $('#marketId').val(),
            marketName = $('#marketId').find('option:selected').text(),
            typeId = $('#typeId').val(),
            receiveBank = $('#receiveBank').val(),
            receiveMoney = toInt($('#receiveMoney').val()),
            invoiceStatus = $('#invoiceStatus').val(),
            invoicePic = $('#invoicePic').val(),
            remark = $('#remark').val(),
            status = $('#status').val(),
            aid = $('#aid').val();
        $.post(urlInfo() + '/admin/account/edit', {
            'aid': aid,
            'receiveDate': receiveDate,
            'invoiceTime': invoiceTime,
            'marketId': marketId,
            'marketName': marketName,
            'typeId': typeId,
            'receiveBank': receiveBank,
            'receiveMoney': receiveMoney,
            'invoiceStatus': invoiceStatus,
            'invoicePic': invoicePic,
            'remark': remark,
            'status': status
        }, function(res) {
            if (res.status.code == 200) {
                alert('修改成功');
                window.location.href = "enter-list.html";
            } else {
                alert('修改失败');
                location.reload();
            }
        })
    });
}
enter_edit();

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