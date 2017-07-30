//获取其他额外的信息
function getOtherInfo() {
    $.ajaxSetup({
        async: false
    });
    //获取市场
    var marketbox = $('#projectId'),
        mBox = '';
    $.get(urlInfo() + '/admin/project/marketname', function(res) {
        $.each(res.data, function(key, data) {
            mBox += '<option value="' + data.pid + '">' + data.marketName + '</option>'
        })
        $(marketbox).html(mBox);
    }, 'json');
    //获取员工档案
    var employee = $('#employeeId'),
        eBox = '';
    $.get(urlInfo() + '/admin/employee/list', function(res) {
        $.each(res.data, function(key, data) {
            eBox += '<option value="' + data.eid + '">' + data.name + '</option>'
        })
        $(employee).html(eBox);
    }, 'json');
    //获取收入类型
    var shouruType = $('#salaryType'),
        sBox = '';
    $.get(urlInfo() + '/admin/salary/extra', {
        'type': 0
    }, function(res) {
        $.each(res.data, function(key, data) {
            sBox += '<option value="' + data.tid + '">' + data.typeName + '</option>'
        })
        $(shouruType).html(sBox);
    }, 'json');

    if (getStr('employeeId') != '') {
        $('#employeeId').val(getStr('employeeId'));
    }
    //获取设计内容
    var designerType = $('#designContentType'),
        dBox = '';
    $.get(urlInfo() + '/admin/salary/extra', {
        'type': 1
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (key == 0) {
                $('#salaryMoney').val(toFloat(data.typeMoney));
            }
            dBox += '<option value="' + data.tid + '">' + data.typeName + '</option>'
        })
        $(designerType).html(dBox);
    }, 'json')
}
getOtherInfo();

//显示收入其他备注
function getSalaryRemark(event) {
    var selectValue = $(event).find('option:selected').text();
    if (selectValue == '其他') {
        $('.salaryOther').removeClass('d_none');
    } else {
        $('.salaryOther').addClass('d_none');
    }
}
//显示设计备注
function getDesignerRemark(event) {
    var selectValue = $(event).find('option:selected').text(),
        sId = $(event).val();
    if (selectValue == '修改') {
        $('.designModify').removeClass('d_none');
    } else {
        $('.designModify').addClass('d_none');
    }
    if (selectValue == '其他') {
        $('.designOther').removeClass('d_none');
    } else {
        $('.designOther').addClass('d_none');
    }

    $.get(urlInfo() + '/admin/salary/extra', {
        'tid': sId
    }, function(res) {
        if (res.data.length > 0) {
            $('#salaryMoney').val(toFloat(res.data[0].typeMoney));
        }
    }, 'json');

}
//添加收入
function add_Salary() {
    var roleName = getStr('showPage');
    if (roleName == 'yg') {
        $('.kjshow').addClass('d_none');
    } else if (roleName == 'kj') {
        $('.kjshow').removeClass('d_none');
    }
    //时间转时间戳处理
    $('#addTable').on('click', function() {
        if (roleName == 'kj') {
            if ($('#finishTime1').val() == '') {
                alert('请选择工程审批通过时间');
                return;
            }
        }
        var pTime = isNaN(getUnix($('#personCommitTime1').val())) ? '' : getUnix($('#personCommitTime1').val());
        $('#personCommitTime').val(pTime);
        $('#salaryMoney').val(toInt($('#salaryMoney').val()));

        //工程审批通过时间
        var finishTime = '';
        if ($('#finishTime1').val() == '') {
            finishTime = '';
        } else {
            finishTime = getLastDay($('#finishTime1').val().split('-')[0], $('#finishTime1').val().split('-')[1]);
        }
        $('#finishTime').val(finishTime);

        var formDate = $('#shouruForm').serialize();
        $.post(urlInfo() + '/admin/salary/commit', formDate, function(res) {
            var tRole = getStr('showPage');
            if (res.status.code == 200) {
                alert('添加成功');
                if (tRole == 'yg') {
                    location.href = 'perInfo/index.html';
                } else {
                    window.location.href = "shouru-list.html";
                }
            } else {
                alert(res.status.msg);
            }
        }, 'json');
    })
}
add_Salary();

//是否编辑
function edit() {
    var id = window.location.href.split('?id=')[1];
    if (typeof id == 'undefined') {
        //嘿嘿 我就是啥也不做 你咋地
        $('.changeBtn').removeClass('d_none').siblings('.btn').addClass('d_none');
    } else {
        // $('.kjshow').addClass('d_none');
        $('.check-box').removeClass('d_none');
        $('.changeBtn').addClass('d_none').siblings('.btn').removeClass('d_none');
        //哎 不得不做了
        $.get(urlInfo() + '/admin/salary/list', {
            'sid': id
        }, function(res) {
            if (res.data[0].finishTime == null || res.data[0].finishTime == '' || typeof res.data[0].finishTime == 'undefined') {
                $('#finishTime1').val('');
                $('#finishTime').val('');
            } else {
                $('#finishTime1').val(formatDate(res.data[0].finishTime));
                $('#finishTime').val(res.data[0].finishTime);
            }
            $('#personCommitTime1').val(getTime(res.data[0].personCommitTime));
            $('#personCommitTime').val(res.data[0].personCommitTime);
            $('#employeeId').val(res.data[0].employeeId);
            $('#projectId').val(res.data[0].projectId);
            // $('#salaryType').val(res.data[0].salaryType);
            $('#designContentType').val(res.data[0].designContentType);
            $('#salaryMoney').val(toFloat(res.data[0].salaryMoney));
            $('#remark').val(res.data[0].remark);
            $('#salaryOther').val(res.data[0].salaryOther);
            $('#designOther').val(res.data[0].designOther);
            $('#designModify').val(res.data[0].designModify);
            $('#status').val(res.data[0].status);
            $('#sid').val(res.data[0].sid);
        })
    }
}
edit();
//提交修改数据
function edit_Salary() {
    $('#editTable').on('click', function() {
        var pTime = isNaN(getUnix($('#personCommitTime1').val())) ? '' : getUnix($('#personCommitTime1').val());
        //工程审批通过时间
        var finishTime = '';
        if ($('#finishTime1').val() == '') {
            finishTime = '';
            alert('请选择计入时间');
        } else {
            finishTime = getLastDay($('#finishTime1').val().split('-')[0], $('#finishTime1').val().split('-')[1]);
        }
        $('#finishTime').val(finishTime);
        $('#personCommitTime').val(pTime);
        $('#salaryMoney').val(toInt($('#salaryMoney').val()));
        var formDate = $('#shouruForm').serialize();
        $.post(urlInfo() + '/admin/salary/edit', formDate, function(res) {
            if (res.status.code == 200) {
                alert('修改成功');
                window.location.href = "shouru-list.html";
            } else {
                alert('修改失败');
                location.reload();
            }
        }, 'json');
    })
}
edit_Salary();