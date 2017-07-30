//是否编辑
function edit() {

    var eid = getStr('employeeId');
    //哎 不得不做了
    $.get(urlInfo() + '/admin/employee/list', {
        'eid': eid
    }, function(res) {
        $('#birthday1').val(getTime(res.data[0].birthday));
        $('#birthday').val(res.data[0].birthday);
        $('#entryTime1').val(getTime(res.data[0].entryTime));
        $('#entryTime').val(res.data[0].entryTime);
        $('#name').val(res.data[0].name);
        $('#job').val(res.data[0].job);
        // $('#companyName').val(res.data[0].companyName);
        // $('#companyAddress').val(res.data[0].companyAddress);
        $('#companyAccount').val(res.data[0].companyAccount);
        $('#accountBank').val(res.data[0].accountBank);
        $('#sex').val(res.data[0].sex);
        $('#idcardNumber').val(res.data[0].idcardNumber);
        $('#idcardAddress').val(res.data[0].idcardAddress);
        $('#probationBaseSalary').val(toFloat(res.data[0].probationBaseSalary));
        $('#officialSalaryOne').val(toFloat(res.data[0].officialSalaryOne));
        $('#officialSalaryTwo').val(toFloat(res.data[0].officialSalaryTwo));
        $('#officialSalaryThree').val(toFloat(res.data[0].officialSalaryThree));
        $('#officialSalaryFour').val(toFloat(res.data[0].officialSalaryFour));
        $('#idcardFrontUrl').val(res.data[0].idcardFrontUrl);
        $('#idcardBehindUrl').val(res.data[0].idcardBehindUrl);
        $('#education').val(res.data[0].education);
        $('#contact').val(res.data[0].contact);
        $('#mobile').val(res.data[0].mobile);
        $('#qq').val(res.data[0].qq);
        $('#wx').val(res.data[0].wx);
        $('#dearFriendName').val(res.data[0].dearFriendName);
        $('#dearFriendMobile').val(res.data[0].dearFriendMobile);
        $('#username').val(res.data[0].username);
        $('#status').val(res.data[0].status);
        $('#password').val(res.data[0].password);
        $('#eid').val(res.data[0].eid);

        //显示身份证正面照
        var car1 = $('#sfzz ul');
        var carflist = res.data[0].idcardFrontUrl.split('#');
        var carfbox = '';
        if (carflist.length != 0 && carflist != '') {
            for (var i = 0; i < carflist.length; i++) {
                carfbox += '<li class="f_left plis"><img src="' + imgUrl() + carflist[i] + getShuiYin() + '" alt="">' +
                    '<p data-delkey="' + carflist[i] + '" class="del">删除</p></li>'
            }
            car1.html(carfbox);
        }
        //身份证反面照
        var car2 = $('#sfzf ul');
        var carblist = res.data[0].idcardBehindUrl.split('#');
        var carbbox = '';
        if (carblist.length != 0 && carblist != '') {
            for (var i = 0; i < carblist.length; i++) {
                carbbox += '<li class="f_left plis"><img src="' + imgUrl() + carblist[i] + getShuiYin() + '" alt="">' +
                    '<p data-delkey="' + carblist[i] + '" class="del">删除</p></li>'
            }
            car2.html(carbbox);
        }
        //学历
        var edu2 = $('#xl ul');
        var edulist = res.data[0].education.split('#');
        var edubox = '';
        if (edulist.length != 0 && edulist != '') {
            for (var i = 0; i < edulist.length; i++) {
                edubox += '<li class="f_left plis"><img src="' + imgUrl() + edulist[i] + getShuiYin() + '" alt="">' +
                    '<p data-delkey="' + edulist[i] + '" class="del">删除</p></li>'
            }
            edu2.html(edubox);
        }
        //合同
        var contract2 = $('#ht ul');
        var contractlist = res.data[0].contact.split('#');
        var contractbox = '';
        if (contractlist.length != 0 && contractlist != '') {
            for (var i = 0; i < contractlist.length; i++) {
                contractbox += '<li class="f_left plis"><img src="' + imgUrl() + contractlist[i] + getShuiYin() + '" alt="">' +
                    '<p data-delkey="' + contractlist[i] + '" class="del">删除</p></li>'
            }
            contract2.html(contractbox);
        }
    })
}
edit();


/**
 * 员工提交自身档案 不显示账号密码
 */
function hideAccount() {
    var roleName = getStr('showPage');
    if (roleName == 'yg')
        $('.' + roleName).addClass('d_none');
}
// hideAccount();

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
                $(picGroup).html(li);
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
                    $(event).siblings('.com_add').prop('disabled', false).text('上传文件');
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


//提交修改数据
function edit_Salary() {
    $('#editTable').on('click', function() {
        var bTime = isNaN(getUnix($('#birthday1').val())) ? '' : getUnix($('#birthday1').val()),
            eTime = isNaN(getUnix($('#entryTime1').val())) ? '' : getUnix($('#entryTime1').val());
        $('#birthday').val(bTime);
        $('#entryTime').val(eTime);

        $('#probationBaseSalary').val(toInt($('#probationBaseSalary').val()));
        $('#officialSalaryOne').val(toInt($('#officialSalaryOne').val()));
        $('#officialSalaryTwo').val(toInt($('#officialSalaryTwo').val()));
        $('#officialSalaryThree').val(toInt($('#officialSalaryThree').val()));
        $('#officialSalaryFour').val(toInt($('#officialSalaryFour').val()));

        var formDate = $('#staffFrom').serialize();
        $.post(urlInfo() + '/admin/employee/edit', formDate, function(res) {
            if (res.status.code == 200) {
                alert('修改成功');
                window.location.href = "index.html";
            } else {
                alert('修改失败');
                location.reload();
            }
        }, 'json');
    })
}
edit_Salary();