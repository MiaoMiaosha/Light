function addPic() {
    var addBtn = $('#addPic'),
        hideIpt = $('#picup');
    $(addBtn).on('click', function() {
        $(hideIpt).trigger('click');
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
        var pic64 = this.result.split(',')[1], //暂时可以分割逗号得到
            xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4) {
                // var upJson = JSON.parse(xhr.responseText);
                var upJson = eval('(' + xhr.responseText + ')');
                var li = '<li class="f_left plis" data-keys="' + upJson.data.key + '"><img src="' + imgUrl() + upJson.data.key + getShuiYin()+ '" alt="">' +
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
                    $('#addPic').prop('disabled', true).text('正在上传中..');
                    $('#upload').prop('disabled', true);
                } else {
                    $('#addPic').prop('disabled', false).text('上传图片');
                    $('#upload').prop('disabled', false);
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
 * word上传
 */
function addFile() {
    var addBtn = $('#addFile'),
        hideIpt = $('#fileup');
    $(addBtn).on('click', function() {
        $(hideIpt).trigger('click');
    })
}
addFile();

function getFile(event) {
    var file = event.files[0],
        reader = new FileReader(),
        tokenVal = getToken(),
        randomKey = getRandomKey(),
        files = $('#uploadDoc').val();
    // $(event).next().text('已选择' + file.name);
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        var pic64 = this.result.split(',')[1], //暂时可以分割逗号得到
            xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4) {
                // var upJson = JSON.parse(xhr.responseText);
                var upJson = eval('(' + xhr.responseText + ')');
                files += upJson.data.key + '#';
                $('#uploadDoc').val(files);
            }
        };
        xhr.upload.onprogress = function(evt) {
            if (evt.lengthComputable) {
                var percentComplete = Math.round(evt.loaded * 100 / evt.total);
                if (percentComplete != 100) {
                    $('#addFile').prop('disabled', true).text('正在上传中..');
                    $('#upload').prop('disabled', true);
                } else {
                    $('#addFile').prop('disabled', false).text('上传文件');
                    $('#upload').prop('disabled', false);
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
 * 获取基本信息
 */
function getBasicInfo() {
    //获取省市-市场名称
    // $.get(urlInfo() + '/admin/project/cusdetail', function(res) {
    //     if (res.status.code == 500) {
    //         alert(res.status.msg);
    //         $('.main_body').load('../perInfo/perinfo.html');
    //         $('#titleName').text('个人中心');
    //     } else {
    //         $('#psheng').val(res.province.name);
    //         $('#pcity').val(res.district.name);
    //         $('#pmname').val(res.element.marketName);
    //         $('#projectId').val(res.element.pid);
    //         $('#loginUserId').val(res.element.loginUserId);
    //     }
    //     // setStr('projectId', res.element.pid);
    // }, 'json');
    //获取阶段
    var typeBox = $('#type'),
        typeCtn = "";
    $.get(urlInfo() + '/admin/project/processlist', {
        'level': 1
    }, function(res) {
        $.each(res.data, function(i) {
            typeCtn += '<option value="' + this.id + '">' + this.name + '</option>';
        })
        $(typeBox).html(typeCtn);
    }, 'json');
    //员工列表
    var eBox = $('#employee-box ul'),
        eCtn = "";
    $.get(urlInfo() + '/admin/employee/worklist', function(res) {
        $.each(res.data, function(key, data) {
            eCtn += '<li class="slis" data-id="' + data.eid + '">' + data.name + '</li>';
        })
        $(eBox).html(eCtn);
    }, 'json');
}
getBasicInfo();

/**
 * 员工多选
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
moreSelect();

/**
 * 提交图面信息
 */
function commitPicAreaInfo() {
    $('#upload').on('click', function() {
        var employeeIds = '',
            employee = $('#employeebox span');
        for (var i = 0; i < employee.length; i++) {
            employeeIds += $(employee[i]).attr('data-id') + ":";
        }
        employeeIds = employeeIds.substring(0, employeeIds.length - 1);
        $('#employeeIds').val(employeeIds);
        var piclis = $('#picGroup li'),
            uploadImg = '';
        for (var i = 0; i < piclis.length; i++) {
            uploadImg += $(piclis[i]).attr('data-keys') + '#';
        }
        uploadImg = uploadImg.substring(0, uploadImg.length - 1);
        $('#uploadImg').val(uploadImg);
        var docFile = $('#uploadDoc').val();
        docFile = docFile.substring(0, docFile.length - 1);
        $('#uploadDoc').val(docFile);

        var userid = getCookie('userId');
        var openId = getStr('openId');
        $('#loginUserId').val(userid);
        $('#openId').val(openId);
        var formDate = $('#basicForm').serialize();
        $.post(urlInfo() + '/admin/post/commit', formDate, function(res) {
            if (res.status.code == 200) {
                alert('提交成功');
            } else {
                alert(res.status.msg);
            }
        }, 'json')
    })
}
commitPicAreaInfo();

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
 * 获取工程
 * status 0-未开始 1-进行中 2-审核失败 3-审核成功；4-成功结束；5-标记删除
 */

function getProjectInfo() {
    var cid = getStr('customerId'),
        cBox = $('#pmname'),
        cCtn = '';
    $.get(urlInfo() + '/admin/project/getcuslist', {
        'customerId': cid
    }, function(res) {
        $.each(res.data, function(i) {
            if (i == 0) {
                $('#projectId').val(this.element.pid);
                $('#psheng').val(this.province.name);
                // $('#loginUserId').val(this.element.loginUserId);
                $('#pcity').val(this.city.name);
            }
            cCtn += '<option value="' + this.element.pid + '" data-province="' + this.province.name +
                '" data-city="' + this.city.name + '" data-loginId="' + this.element.loginUserId + '">' +
                this.element.marketName + '</option>';
        })
        cBox.html(cCtn);
    }, 'json')
}
getProjectInfo();

/**
 * 根据选择的名称显示对应的信息
 */
function getMarketInfo(event) {
    var selectValue = $(event).find('option:selected');
    var projectId = $(selectValue).val(),
        province = $(selectValue).attr('data-province'),
        city = $(selectValue).attr('data-city'),
        loginId = $(selectValue).attr('data-loginId');
    $('#projectId').val(projectId);
    $('#psheng').val(province);
    // $('#loginUserId').val(loginId);
    $('#pcity').val(city);
}