//获取员工信息列表
function getStaffList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['姓名', '身份证号', '手机', '操作'],
        hCtn = '',
        bCtn = '',
        job = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    $.get(urlInfo() + '/admin/employee/list', {
        'page': 1,
        'rows': 10
    }, function(res) {
        setStr('page', 1);
        setCookie('loginStatues', res.status.code);
        $.each(res.data, function(key, data) {
            if (data.job == 1) {
                job = '设计师';
            } else {
                job = '合同人';
            }
            if ((key + 1) % 2 == 0) {
                bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.name + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.idcardNumber + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.mobile + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right"><a href="staff-table.html?id=' + data.eid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.eid + '" onclick="del(this)">删除</a></td></tr>';
            } else {
                bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.name + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.idcardNumber + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.mobile + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right"><a href="staff-table.html?id=' + data.eid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.eid + '" onclick="del(this)">删除</a></td></tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}
getStaffList();

//删除
function del(event) {
    var delid = $(event).attr('data-id');
    if (confirm('确认删除?')) {
        $.post(urlInfo() + '/admin/employee/delete', {
            'eid': delid
        }, function(res) {
            if (res.status.code == 200) {
                alert('删除成功');
                location.href = "staff-list.html";
            }
        })
    }
}

//搜索
function searchResult() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['姓名', '身份证号', '手机', '操作'],
        hCtn = '',
        bCtn = '',
        job = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var statusId = $('#searchKey').val(),
        stallName = $('#search').val();
    $.get(urlInfo() + '/admin/employee/list', {
        'status': statusId,
        'name': stallName,
        'page': 1,
        'rows': 10
    }, function(res) {
        if (res.data.length != 0) {
            setStr('page', 1);
            $.each(res.data, function(key, data) {
                if (data.job == 1) {
                    job = '设计师';
                } else {
                    job = '合同人';
                }
                if ((key + 1) % 2 == 0) {
                    bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.name + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.idcardNumber + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + data.mobile + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right"><a href="staff-table.html?id=' + data.eid +
                        '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.eid + '" onclick="del(this)">删除</a></td></tr>';
                } else {
                    bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.name + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.idcardNumber + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + data.mobile + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right"><a href="staff-table.html?id=' + data.eid +
                        '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.eid + '" onclick="del(this)">删除</a></td></tr>';
                }
            })
            $(tableBody).html(bCtn);
        } else {
            $('.nextpage').text('没有更多数据了').prop('disabled', true);
        }
    })
}


/**
 * 加载更多
 */
function getMoreData() {
    $('.nextpage').on('click', function() {
        var page = parseInt(getStr('page')) + 1;
        var tablebox = $('#datatable-default'),
            tableHeader = $(tablebox).find('thead tr'),
            tableBody = $(tablebox).find('tbody'),
            tableArr = ['姓名', '身份证号', '手机', '操作'],
            hCtn = '',
            bCtn = '',
            job = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        var statusId = $('#searchKey').val(),
            stallName = $('#search').val();
        $.get(urlInfo() + '/admin/employee/list', {
            'status': statusId,
            'name': stallName,
            'page': page,
            'rows': 10
        }, function(res) {
            if (res.data.length != 0) {
                setStr('page', page);
                $.each(res.data, function(key, data) {
                    if (data.job == 1) {
                        job = '设计师';
                    } else {
                        job = '合同人';
                    }
                    if ((key + 1) % 2 == 0) {
                        bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.name + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.idcardNumber + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + data.mobile + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right"><a href="staff-table.html?id=' + data.eid +
                            '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.eid + '" onclick="del(this)">删除</a></td></tr>';
                    } else {
                        bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.name + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.idcardNumber + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + data.mobile + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right"><a href="staff-table.html?id=' + data.eid +
                            '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.eid + '" onclick="del(this)">删除</a></td></tr>';
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