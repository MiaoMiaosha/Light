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
                var li = '<li class="f_left plis"><img src="' + imgUrl() + upJson.data.key + getShuiYin() +'" alt="">' +
                    '<p data-delkey="' + upJson.data.key + '" class="del">删除</p></li>';
                $(event).siblings('input').val(upJson.data.key);
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

//添加操作
function staff_Add() {
    $('#addTable').click(function() {
        var birthday = isNaN(getUnix($('#birthday1').val())) ? '' : getUnix($('#birthday1').val()),
            entryTime = isNaN(getUnix($('#entryTime1').val())) ? '' : getUnix($('#entryTime1').val()),
            name = $('#name').val(),
            username = $('#username').val(),
            password = $('#password').val(),
            job = $('#job').val(),
            // companyName = $('#companyName').val(),
            // companyAddress = $('#companyAddress').val(),
            companyAccount = $('#companyAccount').val(),
            accountBank = $('#accountBank').val(),
            idcardNumber = $('#idcardNumber').val(),
            idcardAddress = $('#idcardAddress').val(),
            idcardFrontUrl = $('#idcardFrontUrl').val(),
            idcardBehindUrl = $('#idcardBehindUrl').val(),
            education = $('#education').val(),
            contact = $('#contact').val(),
            probationBaseSalary = toInt($('#probationBaseSalary').val()),
            officialSalaryOne = toInt($('#officialSalaryOne').val()),
            officialSalaryTwo = toInt($('#officialSalaryTwo').val()),
            officialSalaryThree = toInt($('#officialSalaryThree').val()),
            officialSalaryFour = toInt($('#officialSalaryFour').val()),
            mobile = $('#mobile').val(),
            qq = $('#qq').val(),
            wx = $('#wx').val(),
            dearFriendName = $('#dearFriendName').val(),
            dearFriendMobile = $('#dearFriendMobile').val(),
            sex = $('#sex').val();
        // /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/
        var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
        var isIdCard = idcardNumber.match(reg);
        if (isIdCard == null) alert('身份证格式不正确,请确保身份证格式正确以及18位');
        if ($('#idcardFrontUrl').val() == '') {
            alert('请上传你的身份证正面照');
        } else if ($('#idcardBehindUrl').val() == '') {
            alert('请上传你的身份证反面照');
        } else if ($('#idcardAddress').val() == '') {
            alert('请输入身份证地址');
        } else if ($('#entryTime1').val() == '') {
            alert('请选择入职时间');
        } else {
            $.post(urlInfo() + '/admin/employee/commit', {
                'birthday': birthday,
                'entryTime': entryTime,
                'name': name,
                'username': username,
                'password': password,
                'job': job,
                // 'companyName': companyName,
                // 'companyAddress': companyAddress,
                'companyAccount': companyAccount,
                'accountBank': accountBank,
                'idcardNumber': idcardNumber,
                'idcardAddress': idcardAddress,
                'idcardFrontUrl': idcardFrontUrl,
                'idcardBehindUrl': idcardBehindUrl,
                'education': education,
                'contact': contact,
                'probationBaseSalary': probationBaseSalary,
                'officialSalaryOne': officialSalaryOne,
                'officialSalaryTwo': officialSalaryTwo,
                'officialSalaryThree': officialSalaryThree,
                'officialSalaryFour': officialSalaryFour,
                'mobile': mobile,
                'qq': qq,
                'wx': wx,
                'dearFriendName': dearFriendName,
                'dearFriendMobile': dearFriendMobile,
                'sex': sex
            }, function(res) {
                // var res1 = JSON.parse(res);
                // console.log(res1);
                var tRole = getStr('showPage');
                if (res.status.code == 200) {
                    alert('添加成功');
                    if (tRole == 'yg') {
                        location.href = 'perInfo/index.html';
                    } else {
                        window.location.href = "staff-list.html";
                    }
                } else {
                    alert(res.status.msg);
                }
            })
        }
    });
}
staff_Add()

//是否编辑
function edit() {
    var id = window.location.href.split('?id=')[1];
    if (typeof id == 'undefined') {
        //嘿嘿 我就是啥也不做 你咋地
        $('.editbox').removeClass('d_none');
        $('.changeBtn').removeClass('d_none').siblings('.btn').addClass('d_none');
    } else {
        $('.check-box').removeClass('d_none');
        $('.editbox').addClass('d_none');
        $('.changeBtn').addClass('d_none').siblings('.btn').removeClass('d_none');
        //哎 不得不做了
        $.get(urlInfo() + '/admin/employee/list', {
            'eid': id
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
            $('#mobile').val(res.data[0].mobile);
            $('#qq').val(res.data[0].qq);
            $('#wx').val(res.data[0].wx);
            $('#dearFriendName').val(res.data[0].dearFriendName);
            $('#dearFriendMobile').val(res.data[0].dearFriendMobile);
            $('#username').val(res.data[0].username);
            $('#status').val(res.data[0].status);
            $('#password').val(res.data[0].password);
            $('#eid').val(res.data[0].eid);
        })
    }
}
edit();
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
                window.location.href = "staff-list.html";
            } else {
                alert('修改失败');
                location.reload();
            }
        }, 'json');
    })
}
edit_Salary();

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

/**
 * 员工提交自身档案 不显示账号密码
 */
function hideAccount() {
    var roleName = getStr('showPage');
    if (roleName == 'yg')
        $('.' + roleName).addClass('d_none');
}
hideAccount();