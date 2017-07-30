//添加操作
function cooperate_Add() {
    $('#addTable').click(function() {
        var cooperateName = $('#cooperateName').val(),
            cooperateContent = $('#cooperateContent').val(),
            chiefPerson = $('#chiefPerson').val(),
            mobile = $('#mobile').val(),
            address = $('#address').val(),
            bankAccount = $('#bankAccount').val(),
            bankAccountNo = $('#bankAccountNo').val(),
            bankBranch = $('#bankBranch').val(),
            remark = $('#remark').val();
        if (cooperateName == '') {
            alert('请输入合作方');
        } else if (cooperateContent == '') {
            alert('请输入合作内容');
        } else if (mobile == '') {
            alert('请输入合作电话');
        } else {
            $.post(urlInfo() + '/admin/partner/commit', {
                'cooperateName': cooperateName,
                'cooperateContent': cooperateContent,
                'chiefPerson': chiefPerson,
                'mobile': mobile,
                'address': address,
                'bankAccount': bankAccount,
                'bankAccountNo': bankAccountNo,
                'bankBranch': bankBranch,
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
                        window.location.href = "cooperate-list.html";
                    }
                } else {
                    alert(res.status.msg);
                }
            }, 'json')
        }
    });
}
cooperate_Add()

//是否编辑
function edit() {
    var id = window.location.href.split('?id=')[1];
    if (typeof id == 'undefined') {
        //嘿嘿 我就是啥也不做 你咋地
        $('.cstatus').addClass('d_none');
        $('.changeBtn').removeClass('d_none').siblings('.btn').addClass('d_none');
    } else {
        $('.cstatus').removeClass('d_none');
        $('.changeBtn').addClass('d_none').siblings('.btn').removeClass('d_none');
        //哎 不得不做了
        $.get(urlInfo() + '/admin/partner/list', {
            'pid': id
        }, function(res) {
            $('#cooperateName').val(res.data[0].cooperateName);
            $('#cooperateContent').val(res.data[0].cooperateContent);
            $('#chiefPerson').val(res.data[0].chiefPerson);
            $('#mobile').val(res.data[0].mobile);
            $('#address').val(res.data[0].address);
            $('#bankAccount').val(res.data[0].bankAccount);
            $('#bankAccountNo').val(res.data[0].bankAccountNo);
            $('#bankBranch').val(res.data[0].bankBranch);
            $('#remark').val(res.data[0].remark);
            $('#status').val(res.data[0].status);
            $('#pid').val(res.data[0].pid);
        })
    }
}
edit();
//提交修改数据
function edit_Cooperate() {
    $('#editTable').on('click', function() {
        var formDate = $('#cooperateForm').serialize();
        $.post(urlInfo() + '/admin/partner/edit', formDate, function(res) {
            if (res.status.code == 200) {
                alert('修改成功');
                window.location.href = "cooperate-list.html";
            } else {
                alert('修改失败');
                location.reload();
            }
        }, 'json');
    })
}
edit_Cooperate();