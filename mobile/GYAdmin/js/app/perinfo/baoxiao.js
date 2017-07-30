//获取员工信息列表
function getBaoxiaoList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['日期', '报销人', '报销金额', '市场名称', '报销类型'],
        hCtn = '',
        bCtn = '',
        status = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var userid = getStr('employeeId');
    $.get(urlInfo() + '/admin/reimburse/list?reimburseUerType=1&reimburseUserId=' + userid, function(res) {
        setCookie('loginStatues', res.status.code);
        $.each(res.data, function(key, data) {
            if (data.invoiceStatus == 0) {
                status = '未提供';
            } else if (data.invoiceStatus == 1) {
                status = '提供';
            } else {
                status = '部分提供';
            }
            if ((key + 1) % 2 == 0) {
                bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                    '</tr>';
            } else {
                bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                    '</tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}
getBaoxiaoList();

//删除
function del(event) {
    var delid = $(event).attr('data-id');
    if (confirm('确认删除?')) {
        $.post(urlInfo() + '/admin/reimburse/delete', {
            'rid': delid
        }, function(res) {
            if (res.status.code == 200) {
                alert('删除成功');
                location.href = "baoxiao-list.html";
            }
        })
    }
}
//搜索
function searchResult() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['日期', '报销人', '报销金额', '市场名称', '报销类型'],
        hCtn = '',
        bCtn = '',
        status = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var statusId = $('#searchKey').val(),
        userid = getStr('employeeId');
    $.get(urlInfo() + '/admin/reimburse/list', {
        'status': statusId,
        'reimburseUserId': userid,
        'reimburseUerType': 1
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (data.invoiceStatus == 0) {
                status = '未提供';
            } else if (data.invoiceStatus == 1) {
                status = '提供';
            } else {
                status = '部分提供';
            }
            if ((key + 1) % 2 == 0) {
                bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                    '</tr>';
            } else {
                bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                    '</tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}


/**
 * 输入市场名称搜索
 */
function useMarketNameSearch() {
    $('#searchbtn').on('click', function() {
        var tablebox = $('#datatable-default'),
            tableHeader = $(tablebox).find('thead tr'),
            tableBody = $(tablebox).find('tbody'),
            tableArr = ['日期', '报销人', '报销金额', '市场名称', '报销类型'],
            hCtn = '',
            bCtn = '',
            status = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        var statusId = $('#searchKey').val(),
            userid = getStr('employeeId'),
            marketName = $('#searchWords').val();
        $.get(urlInfo() + '/admin/reimburse/list', {
            'status': statusId,
            'reimburseUserId': userid,
            'reimburseUerType': 1,
            'marketName': marketName
        }, function(res) {
            $.each(res.data, function(key, data) {
                if (data.invoiceStatus == 0) {
                    status = '未提供';
                } else if (data.invoiceStatus == 1) {
                    status = '提供';
                } else {
                    status = '部分提供';
                }
                if ((key + 1) % 2 == 0) {
                    bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                        '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                        '</tr>';
                } else {
                    bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                        '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                        '</tr>';
                }
            })
            $(tableBody).html(bCtn);
        })
    })
}
useMarketNameSearch();