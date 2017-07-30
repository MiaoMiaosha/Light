//添加收入
function add_Customer() {
    //时间转时间戳处理
    $('#addTable').on('click', function() {
        var formDate = $('#customerForm').serialize();
        $.post(urlInfo() + '/admin/customer/commit', formDate, function(res) {
            if (res.status.code == 200) {
                alert('添加成功');
                window.location.href = "customer-list.html";
            } else {
                alert('添加失败');
            }
        }, 'json');
    })
}
// add_Customer();

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
        $.get(urlInfo() + '/admin/customer/list', {
            'cid': id
        }, function(res) {
            //市场名称不可编辑
            $('#companyName').val(res.data[0].companyName).attr('readonly', true);
            $('#manager').val(res.data[0].manager);
            $('#mobile').val(res.data[0].mobile);
            $('#address').val(res.data[0].address);
            $('#receivePerson').val(res.data[0].receivePerson);
            $('#receiveMobile').val(res.data[0].receiveMobile);
            $('#remark').val(res.data[0].remark);
            $('#username').val(res.data[0].username);
            $('#password').val(res.data[0].password);
            $('#status').val(res.data[0].status);
            $('#cid').val(res.data[0].cid);
        })
    }
}
edit();
//提交修改数据
function edit_Customer() {
    $('#editTable').on('click', function() {
        var formDate = $('#customerForm').serialize();
        $.post(urlInfo() + '/admin/customer/edit', formDate, function(res) {
            if (res.status.code == 200) {
                alert('修改成功');
                window.location.href = "customer-list.html";
            } else {
                alert('修改失败');
                location.reload();
            }
        }, 'json');
    })
}
edit_Customer();