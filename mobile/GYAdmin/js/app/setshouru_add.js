/**
 * 添加账户
 */
function accountAdd() {
    $('#addTable').on('click', function() {
        var shouruMoney = $('#typeMoney').val();
        $('#typeMoney').val(toInt(shouruMoney));
        var formDate = $('#setshouruForm').serialize();
        $.post(urlInfo() + '/admin/salary/extraadd', formDate, function(res) {
            if (res.status.code == 200) {
                alert('添加成功!');
                location.href = 'setshouru-list.html';
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
        $.get(urlInfo() + '/admin/salary/extra', {
            'tid': id
        }, function(res) {
            $('#typeName').val(res.data[0].typeName);
            // $('#type').val(res.data[0].type).prop('disabled', true);
            //     var getType = res.data[0].type;
            //     if (getType == 1) {
            //         $('.moneybox').removeClass('d_none');
            //     } else {
            //         $('.moneybox').addClass('d_none');
            //     }
            $('#typeMoney').val(toFloat(res.data[0].typeMoney));
            $('#setshouruForm input[name=tid]').val(res.data[0].tid);
        })
    }
}
edit()

/**
 * 编辑保存
 */
function saveAccount() {
    $('#editTable').on('click', function() {
        var shouruMoney = $('#typeMoney').val();
        $('#typeMoney').val(toInt(shouruMoney));
        var formDate = $('#setshouruForm').serialize();
        $.post(urlInfo() + '/admin/salary/extraedit', formDate, function(res) {
            if (res.status.code == 200) {
                alert('修改成功!');
                location.href = 'setshouru-list.html';
            } else {
                alert('修改失败');
                location.reload();
            }
        })
    })
}
saveAccount();


/**
 * 显示金额
 */
function showMoney(event) {
    var type = $(event).val();
    if (type == 1) {
        $('.moneybox').removeClass('d_none');
    } else {
        $('.moneybox').addClass('d_none');
    }
}