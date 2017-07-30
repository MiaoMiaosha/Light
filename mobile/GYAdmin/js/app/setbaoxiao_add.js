/**
 * 添加账户
 */
function accountAdd() {
    $('#addTable').on('click', function() {
        var formDate = $('#setbaoxiaoForm').serialize();
        $.post(urlInfo() + '/admin/reimburse/extraadd', formDate, function(res) {
            if (res.status.code == 200) {
                alert('添加成功!');
                location.href = 'setbaoxiao-list.html';
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
        // $('.lockBox').removeClass('d_none');
        $('.changeBtn').addClass('d_none').siblings('.btn').removeClass('d_none');
        //哎 不得不做了
        $.get(urlInfo() + '/admin/reimburse/extralist', {
            'id': id
        }, function(res) {
            $('#typeName').val(res.data[0].typeName);
            $('#setbaoxiaoForm input[name=id]').val(res.data[0].id);
        })
    }
}
edit()

/**
 * 编辑保存
 */
function saveAccount() {
    $('#editTable').on('click', function() {
        var formDate = $('#setbaoxiaoForm').serialize();
        $.post(urlInfo() + '/admin/reimburse/extraedit', formDate, function(res) {
            if (res.status.code == 200) {
                alert('修改成功!');
                location.href = 'setbaoxiao-list.html';
            } else {
                alert('修改失败');
                location.reload();
            }
        })
    })
}
saveAccount();