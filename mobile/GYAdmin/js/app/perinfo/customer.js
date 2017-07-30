//获取顾客信息列表
function getSalaryList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['单位名称', '电话', '操作'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var userid = getStr('employeeId');
    $.get(urlInfo() + '/admin/customer/list', {
        'page': 1,
        'rows': 10
    }, function(res) {
        setStr('page', 1);
        setCookie('loginStatues', res.status.code);
        $.each(res.data, function(key, data) {
            if ((key + 1) % 2 == 0) {
                bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.companyName + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right"><a href="customer-table.html?id=' + data.cid +
                    '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.cid + '" onclick="del(this)">删除</a></td></tr>';
            } else {
                bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.companyName + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right"><a href="customer-table.html?id=' + data.cid +
                    '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.cid + '" onclick="del(this)">删除</a></td></tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}
getSalaryList();

//删除
function del(event) {
    var delid = $(event).attr('data-id');
    if (confirm('确认删除?')) {
        $.post(urlInfo() + '/admin/customer/delete', {
            'cid': delid
        }, function(res) {
            if (res.status.code == 200) {
                alert('删除成功');
                location.href = "customer-list.html";
            } else {
                alert('删除失败');
            }
        })
    }
}
//搜索
function searchResult() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['单位名称', '电话', '操作'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var startTime = isNaN(getUnix($('#startTime').val())) ? '' : getUnix($('#startTime').val()),
        endTime = isNaN(getUnix($('#endTime').val())) ? '' : getUnix($('#endTime').val()),
        statusId = $('#searchKey').val(),
        keyWords = $('#search').val();
    $.get(urlInfo() + '/admin/customer/list', {
        'startTime': startTime,
        'endTime': endTime,
        'status': statusId,
        'companyName': keyWords,
        'page': 1,
        'rows': 10
    }, function(res) {
        if (res.data.length != 0) {
            setStr('page', 1);
            $.each(res.data, function(key, data) {
                if ((key + 1) % 2 == 0) {
                    bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.companyName + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right"><a href="customer-table.html?id=' + data.cid +
                        '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.cid + '" onclick="del(this)">删除</a></td></tr>';
                } else {
                    bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.companyName + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right"><a href="customer-table.html?id=' + data.cid +
                        '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.cid + '" onclick="del(this)">删除</a></td></tr>';
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
            tableArr = ['单位名称', '电话', '操作'],
            hCtn = '',
            bCtn = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        var startTime = isNaN(getUnix($('#startTime').val())) ? '' : getUnix($('#startTime').val()),
            endTime = isNaN(getUnix($('#endTime').val())) ? '' : getUnix($('#endTime').val()),
            statusId = $('#searchKey').val(),
            keyWords = $('#search').val();
        $.get(urlInfo() + '/admin/customer/list', {
            'startTime': startTime,
            'endTime': endTime,
            'status': statusId,
            'companyName': keyWords,
            'page': page,
            'rows': 10
        }, function(res) {
            if (res.data.length != 0) {
                setStr('page', page);
                $.each(res.data, function(key, data) {
                    if ((key + 1) % 2 == 0) {
                        bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.companyName + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right"><a href="customer-table.html?id=' + data.cid +
                            '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.cid + '" onclick="del(this)">删除</a></td></tr>';
                    } else {
                        bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.companyName + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right"><a href="customer-table.html?id=' + data.cid +
                            '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.cid + '" onclick="del(this)">删除</a></td></tr>';
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