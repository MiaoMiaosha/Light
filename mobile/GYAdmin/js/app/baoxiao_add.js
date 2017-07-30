//添加操作
function baoxiao_Add() {
    $('#addTable').click(function() {
        var createTime = isNaN(getUnix($('#createTime1').val())) ? '' : getUnix($('#createTime1').val()),
            reimburseUserType = $('#reimburseUserType').val(),
            reimburseUserId = $('#reimburseUserId').val(),
            money = toInt($('#money').val()),
            marketName = $('#marketNameType').find('option:selected').text(),
            typeId = $('#typeId').val() ? $('#typeId').val() : 0,
            invoiceStatus = $('#invoiceStatus').val(),
            event = $('#event').val(),
            remark = $('#remark').val();
        $.post(urlInfo() + '/admin/reimburse/commit', {
            'createTime': createTime,
            'reimburseUserType': reimburseUserType,
            'reimburseUserId': reimburseUserId,
            'money': money,
            'marketName': marketName,
            'typeId': typeId,
            'invoiceStatus': invoiceStatus,
            'event': event,
            'remark': remark
        }, function(res) {
            // var res1 = JSON.parse(res);
            // console.log(res1);
            var tRole = getStr('showPage');
            if (res.status.code == 200) {
                alert('添加成功');
                if (tRole == 'yg') {
                    location.href = 'perInfo/index.html';
                } else {
                    window.location.href = "baoxiao-list.html";
                }
            } else {
                alert(res.status.msg);
            }
        }, 'json')
    });
}
baoxiao_Add()

//先获取额外信息
function getOtherInfo() {
    $.ajaxSetup({
        async: false
    })
    var typeId = $('#typeId'),
        tBox = '';
    $.get(urlInfo() + '/admin/reimburse/extralist', function(res) {
        $.each(res.data, function(key, data) {
            tBox += '<option value="' + data.id + '">' + data.typeName + '</option>';
        })
        $(typeId).html(tBox);
    }, 'json');
    //默认取员工列表
    var ruid = $('#reimburseUserId'),
        rBox = '';
    $.get(urlInfo() + '/admin/employee/list', function(res1) {
        $.each(res1.data, function(key, data) {
            rBox += '<option value="' + data.eid + '">' + data.name + '</option>';
        })
        $(ruid).html(rBox);
    }, 'json');
    //获取市场
    var mbox = $('#marketNameType'),
        mctn = '';
    $.get(urlInfo() + '/admin/project/marketname', function(res) {
        $.each(res.data, function(key, data) {
            mctn += '<option value="' + data.pid + '">' + data.marketName + '</option>';
        })
        mbox.html(mctn);
    })
}
getOtherInfo();

//根据选择的报销人类型 加载对应的员工数据
function getUserId(event) {
    var sVal = $(event).val();
    if (sVal == 1) { //员工档案
        var ruid = $('#reimburseUserId'),
            rBox = '';
        $.get(urlInfo() + '/admin/employee/list', function(res) {
            $.each(res.data, function(key, data) {
                rBox += '<option value="' + data.eid + '">' + data.name + '</option>';
            })
            $(ruid).html(rBox);
        })
    } else if (sVal == 2) { //合作方档案
        var cuid = $('#reimburseUserId'),
            cBox = '';
        $.get(urlInfo() + '/admin/partner/list', function(res) {
            $.each(res.data, function(key, data) {
                cBox += '<option value="' + data.pid + '">' + data.cooperateName + '</option>';
            })
            $(cuid).html(cBox);
        })
    }
}
//是否编辑
//骷髅头
function edit() {
    var id = window.location.href.split('?id=')[1];
    if (typeof id == 'undefined') {
        //嘿嘿 我就是啥也不做 你咋地
        $('.changeBtn').removeClass('d_none').siblings('.btn').addClass('d_none');
    } else {
        $('.check-box').removeClass('d_none');
        $('.changeBtn').addClass('d_none').siblings('.btn').removeClass('d_none');
        $.ajaxSetup({
            async: false
        });
        //哎 不得不做了
        $.get(urlInfo() + '/admin/reimburse/list', {
            'rid': id
        }, function(res) {
            $('#createTime1').val(getTime(res.data[0].createTime));
            $('#createTime').val(res.data[0].createTime);
            $('#reimburseUserType').val(res.data[0].reimburseUserType);
            $('#reimburseUserId').val(res.data[0].reimburseUserId);
            $('#money').val(toFloat(res.data[0].money));
            $('#marketName').val(res.data[0].marketName);
            $('#typeId').val(res.data[0].typeId);
            $('#invoiceStatus').val(res.data[0].invoiceStatus);
            $('#event').val(res.data[0].event);
            $('#remark').val(res.data[0].remark);
            $('#status').val(res.data[0].status);
            $('#rid').val(res.data[0].rid);

            var mklist = $('#marketNameType').find('option');
            $.each(mklist, function(i) {
                if ($(this).text() == res.data[0].marketName) {
                    $(this).prop('selected', true);
                    $('#marketNameType').prop('disabled', true);
                }
            })
        })
    }
}
edit();
//提交修改数据
function edit_Baoxiao() {
    $('#editTable').on('click', function() {
        $('#createTime').val(getUnix($('#createTime1').val()));
        $('#money').val(toInt($('#money').val()));
        var formDate = $('#baoxiaoForm').serialize();
        $.post(urlInfo() + '/admin/reimburse/edit', formDate, function(res) {
            var tRole = getStr('showPage');
            if (res.status.code == 200) {
                alert('修改成功');
                if (tRole == 'yg') {
                    location.href = 'perInfo/index.html';
                } else {
                    window.location.href = "baoxiao-list.html";
                }
            } else {
                alert('修改失败');
                if (tRole == 'yg') {
                    location.href = 'perInfo/index.html';
                } else {
                    window.location.href = "baoxiao-list.html";
                }
            }
        }, 'json');
    })
}
edit_Baoxiao();


/**
 * 员工和会计的报销人列表不同
 */
function getTypeInfo() {
    var typeRole = getStr('showPage');
    if (typeRole == 'yg') {
        var typeList = '<option value="1">调用员工档案</option>';
        $('#reimburseUserType').html(typeList);
    } else if (typeRole == 'kj') {
        var typeList = '<option value="1">调用员工档案</option><option value="2">调用合作方档案</option>';
        $('#reimburseUserType').html(typeList);
    }
}
getTypeInfo();