/**
 * 获取账户列表
 */
function getAccountList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['登录账号', '账号昵称', '账号角色', '状态', '密码', '操作'],
        hCtn = '',
        bCtn = '',
        status = '',
        roleName = '',
        operateBox = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    $.get(urlInfo() + '/admin/login/userlist', {
        'page': 1,
        'rows': 30
    }, function(res) {
        setStr('page', 1);
        setCookie('loginStatues', res.status.code);
        $.each(res.data, function(key, data) {
            if (data.isLocked == 0) {
                status = '正常';
            } else {
                status = '冻结';
            }
            if (data.roleIds == 1) {
                roleName = '管理员';
                operateBox = '<a href="account-table.html?id=' + data.id +
                    '" onclick="edit(this)">编辑</a>';
            } else if (data.roleIds == 2) {
                roleName = '会计';
                operateBox = '<a href="account-table.html?id=' + data.id +
                    '" onclick="edit(this)">编辑</a>';
            } else if (data.roleIds == 3) {
                roleName = '员工';
                operateBox = '<a href="account-table.html?id=' + data.id +
                    '" onclick="edit(this)">编辑</a>';
            } else {
                roleName = '客户';
                operateBox = '<a href="account-table.html?id=' + data.id +
                    '" onclick="edit(this)">编辑</a>';
            }
            if ((key + 1) % 2 == 0) {
                bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.username + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.nickName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + roleName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + status + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.password + '</td>' +
                    '<td data-title="' + tableArr[5] + '" class="text-right">' + operateBox + '</td></tr>';
            } else {
                bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.username + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.nickName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + roleName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + status + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.password + '</td>' +
                    '<td data-title="' + tableArr[5] + '" class="text-right">' + operateBox + '</td></tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}
getAccountList();


/**
 * 加载更多
 */
function getMoreData() {
    $('.nextpage').on('click', function() {
        var sVal = $('#searchKey').val();
        var page = parseInt(getStr('page')) + 1;
        var tablebox = $('#datatable-default'),
            tableHeader = $(tablebox).find('thead tr'),
            tableBody = $(tablebox).find('tbody'),
            tableArr = ['登录账号', '账号昵称', '账号角色', '状态', '密码', '操作'],
            hCtn = '',
            bCtn = '',
            status = '',
            roleName = '',
            operateBox = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        $.get(urlInfo() + '/admin/login/userlist', {
            'page': page,
            'rows': 30,
            'roleIds': sVal
        }, function(res) {
            if (res.data.length != 0) {
                setStr('page', page);
                setCookie('loginStatues', res.status.code);
                $.each(res.data, function(key, data) {
                    if (data.isLocked == 0) {
                        status = '正常';
                    } else {
                        status = '冻结';
                    }
                    if (data.roleIds == 1) {
                        roleName = '管理员';
                        operateBox = '<a href="account-table.html?id=' + data.id +
                            '" onclick="edit(this)">编辑</a>';
                    } else if (data.roleIds == 2) {
                        roleName = '会计';
                        operateBox = '<a href="account-table.html?id=' + data.id +
                            '" onclick="edit(this)">编辑</a>';
                    } else if (data.roleIds == 3) {
                        roleName = '员工';
                        operateBox = '<a href="account-table.html?id=' + data.id +
                            '" onclick="edit(this)">编辑</a>';
                    } else {
                        roleName = '客户';
                        operateBox = '<a href="account-table.html?id=' + data.id +
                            '" onclick="edit(this)">编辑</a>';
                    }
                    if ((key + 1) % 2 == 0) {
                        bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.username + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.nickName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + roleName + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + status + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right">' + data.password + '</td>' +
                            '<td data-title="' + tableArr[5] + '" class="text-right">' + operateBox + '</td></tr>';
                    } else {
                        bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.username + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.nickName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + roleName + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + status + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right">' + data.password + '</td>' +
                            '<td data-title="' + tableArr[5] + '" class="text-right">' + operateBox + '</td></tr>';
                    }
                })
                $(tableBody).append(bCtn);
            } else {
                $('.nextpage').text('没有更多数据了').prop('disabled', true);
            }
        })
    })
}
getMoreData()


function searchResult(event) {
    $('.nextpage').text('点击加载').prop('disabled', false);
    var sVal = $(event).val();
    var page = parseInt(getStr('page')) + 1;
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['登录账号', '账号昵称', '账号角色', '状态', '密码', '操作'],
        hCtn = '',
        bCtn = '',
        status = '',
        roleName = '',
        operateBox = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    $.get(urlInfo() + '/admin/login/userlist', {
        'page': 1,
        'rows': 30,
        'roleIds': sVal
    }, function(res) {
        if (res.data.length != 0) {
            setStr('page', 1);
            setCookie('loginStatues', res.status.code);
            $.each(res.data, function(key, data) {
                if (data.isLocked == 0) {
                    status = '正常';
                } else {
                    status = '冻结';
                }
                if (data.roleIds == 1) {
                    roleName = '管理员';
                    operateBox = '<a href="account-table.html?id=' + data.id +
                        '" onclick="edit(this)">编辑</a>';
                } else if (data.roleIds == 2) {
                    roleName = '会计';
                    operateBox = '<a href="account-table.html?id=' + data.id +
                        '" onclick="edit(this)">编辑</a>';
                } else if (data.roleIds == 3) {
                    roleName = '员工';
                    operateBox = '<a href="account-table.html?id=' + data.id +
                        '" onclick="edit(this)">编辑</a>';
                } else {
                    roleName = '客户';
                    operateBox = '<a href="account-table.html?id=' + data.id +
                        '" onclick="edit(this)">编辑</a>';
                }
                if ((key + 1) % 2 == 0) {
                    bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.username + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.nickName + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + roleName + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right">' + status + '</td>' +
                        '<td data-title="' + tableArr[4] + '" class="text-right">' + data.password + '</td>' +
                        '<td data-title="' + tableArr[5] + '" class="text-right">' + operateBox + '</td></tr>';
                } else {
                    bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.username + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.nickName + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + roleName + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right">' + status + '</td>' +
                        '<td data-title="' + tableArr[4] + '" class="text-right">' + data.password + '</td>' +
                        '<td data-title="' + tableArr[5] + '" class="text-right">' + operateBox + '</td></tr>';
                }
            })
            $(tableBody).html(bCtn);
        } else {
            $('.nextpage').text('没有更多数据了').prop('disabled', true);
        }
    })

}