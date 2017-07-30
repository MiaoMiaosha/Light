/**
 * 添加账户
 */
function accountAdd() {
    $('#addTable').on('click', function() {
        var typeVal = $('#type').val();
        if (typeVal == 0) {
            var formDate = $('#setfenhongForm').serialize();
            $.post(urlInfo() + '/admin/bonus/extraadd', formDate, function(res) {
                if (res.status.code == 200) {
                    alert('添加成功!');
                    location.href = 'setfenhong-list.html';
                } else {
                    alert('添加失败');
                    location.reload();
                }
            })
        } else {
            $('#bonusLevelMoney').val(toInt($('#bonusLevelMoney').val()));
            $('#bonusRate').val(toInt($('#bonusRate').val()));
            var formDate = $('#setfenhongForm').serialize();
            $.post(urlInfo() + '/admin/bonus/leveladd', formDate, function(res) {
                if (res.status.code == 200) {
                    alert('添加成功!');
                    location.href = 'setfenhong-list.html';
                } else {
                    alert('添加失败');
                    location.reload();
                }
            })
        }

    })
}
accountAdd();

/**
 * 编辑
 */
function edit() {
    var params = window.location.href.split('?');
    var id = "",
        type = "";
    if (typeof params[1] == 'undefined') {
        //嘿嘿 我就是啥也不做 你咋地
        $('.changeBtn').removeClass('d_none').siblings('.btn').addClass('d_none');
    } else {
        params2 = params[1].split('&');
        id = params2[1].split('=')[1];
        type = params2[0].split('=')[1];
        // $('.lockBox').removeClass('d_none');
        $('.changeBtn').addClass('d_none').siblings('.btn').removeClass('d_none');
        //哎 不得不做了
        if (typeof type != 'undefined') {
            if (type == 0) {
                $('.rate').addClass('d_none');
                $('.fenhong').removeClass('d_none');
                $.get(urlInfo() + '/admin/bonus/extra', {
                    'id': id
                }, function(res) {
                    $('#type').val(type).prop('disabled', true);
                    $('#bonusType').val(res.data[0].bonusType);
                    $('#setfenhongForm input[name=id]').val(res.data[0].id);
                })
            } else {
                $('.fenhong').addClass('d_none');
                $('.rate').removeClass('d_none');
                $.get(urlInfo() + '/admin/bonus/levellist', {
                    'id': id
                }, function(res) {
                    $('#type').val(type).prop('disabled', true);
                    $('#bonusLevelName').val(res.data[0].bonusLevelName);
                    $('#bonusLevelMoney').val(toFloat(res.data[0].bonusLevelMoney));
                    $('#bonusRate').val(toFloat(res.data[0].bonusRate));
                    $('#setfenhongForm input[name=id]').val(res.data[0].id);
                })
            }
        }

    }
}
edit()

/**
 * 编辑保存
 */
function saveAccount() {
    $('#editTable').on('click', function() {
        var type = $('#type').val();
        if (type == 0) {
            var formDate = $('#setfenhongForm').serialize();
            $.post(urlInfo() + '/admin/bonus/extraedit', formDate, function(res) {
                if (res.status.code == 200) {
                    alert('修改成功!');
                    location.href = 'setfenhong-list.html';
                } else {
                    alert('修改失败');
                    location.reload();
                }
            })
        } else {
            $('#bonusLevelMoney').val(toInt($('#bonusLevelMoney').val()));
            $('#bonusRate').val(toInt($('#bonusRate').val()));
            var formDate = $('#setfenhongForm').serialize();
            $.post(urlInfo() + '/admin/bonus/leveledit', formDate, function(res) {
                if (res.status.code == 200) {
                    alert('修改成功!');
                    location.href = 'setfenhong-list.html';
                } else {
                    alert('修改失败');
                    location.reload();
                }
            })
        }
    })
}
saveAccount();

/**
 * 增加类型
 */
function selectAddType(event) {
    var sVal = $(event).val();
    if (sVal != 0) {
        $('.rate').removeClass('d_none');
        $('.fenhong').addClass('d_none');
    } else {
        $('.rate').addClass('d_none');
        $('.fenhong').removeClass('d_none');
    }
}