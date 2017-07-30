//获取员工信息列表
function getBaoxiaoList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['日期', '报销人', '报销金额', '市场名称', '报销类型', '操作'],
        hCtn = '',
        bCtn = '',
        status = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    $.get(urlInfo() + '/admin/reimburse/list', {
        'page': 1,
        'rows': 10,
    }, function(res) {
        setStr('page', 1);
        setCookie('loginStatues', res.status.code);
        $.each(res.data, function(key, data) {
            if (data.invoiceStatus == 0) {
                status = '未提供';
            } else if (data.invoiceStatus == 1) {
                status = '提供';
            } else {
                status = '部分提供';
            }
            //state 0-未审核 1-已通过 2-不通过
            if (data.status == 0) {
                bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[5] + '" class="text-right"><a href="baoxiao-table.html?id=' + data.rid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.rid + '" onclick="del(this)">删除</a>' +
                    '</td></tr>';
            } else if (data.status == 1) {
                bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[5] + '" class="text-right"><a href="baoxiao-table.html?id=' + data.rid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.rid + '" onclick="del(this)">删除</a>' +
                    '</td></tr>';
            } else if (data.status == 2) {
                bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[5] + '" class="text-right"><a href="baoxiao-table.html?id=' + data.rid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.rid + '" onclick="del(this)">删除</a>' +
                    '</td></tr>';
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
        tableArr = ['日期', '报销人', '报销金额', '市场名称', '报销类型', '操作'],
        hCtn = '',
        bCtn = '',
        status = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var statusId = $('#searchKey').val();
    $.get(urlInfo() + '/admin/reimburse/list', {
        'status': statusId
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (data.invoiceStatus == 0) {
                status = '未提供';
            } else if (data.invoiceStatus == 1) {
                status = '提供';
            } else {
                status = '部分提供';
            }
            //state 0-未审核 1-已通过 2-不通过
            if (data.status == 0) {
                bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[5] + '" class="text-right"><a href="baoxiao-table.html?id=' + data.rid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.rid + '" onclick="del(this)">删除</a>' +
                    '</td></tr>';
            } else if (data.status == 1) {
                bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[5] + '" class="text-right"><a href="baoxiao-table.html?id=' + data.rid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.rid + '" onclick="del(this)">删除</a>' +
                    '</td></tr>';
            } else if (data.status == 2) {
                bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[5] + '" class="text-right"><a href="baoxiao-table.html?id=' + data.rid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.rid + '" onclick="del(this)">删除</a>' +
                    '</td></tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}

/**
 * 返回对应角色的首页
 */
function backTargetPage() {
    var backBtn = $('#tBack');
    $(backBtn).on('click', function() {
        var tRole = getStr('showPage');
        if (tRole == 'yg') {
            location.href = 'perInfo/index.html';
        } else {
            location.href = 'index.html';
        }
    })
}
// backTargetPage();


/**加载更多 */
function showPageData() {
    $('.nextpage').on('click', function() {
        var page = parseInt(getStr('page')) + 1;
        // var employeeId = $('#searchKey').val();
        // $('.prevpage').removeClass('d_none');
        var tablebox = $('#datatable-default'),
            tableHeader = $(tablebox).find('thead tr'),
            tableBody = $(tablebox).find('tbody'),
            tableArr = ['日期', '报销人', '报销金额', '市场名称', '报销类型', '操作'],
            hCtn = '',
            bCtn = '',
            status = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        var statusId = $('#searchKey').val();
        $.get(urlInfo() + '/admin/reimburse/list', {
            'page': page,
            'rows': 10,
            // 'status': statusId
        }, function(res) {
            if (res.data.length != 0) {
                setStr('page', page);
                setCookie('loginStatues', res.status.code);
                $.each(res.data, function(key, data) {
                    if (data.invoiceStatus == 0) {
                        status = '未提供';
                    } else if (data.invoiceStatus == 1) {
                        status = '提供';
                    } else {
                        status = '部分提供';
                    }
                    //state 0-未审核 1-已通过 2-不通过
                    if (data.status == 0) {
                        bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                            '<td data-title="' + tableArr[5] + '" class="text-right"><a href="baoxiao-table.html?id=' + data.rid +
                            '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.rid + '" onclick="del(this)">删除</a>' +
                            '</td></tr>';
                    } else if (data.status == 1) {
                        bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                            '<td data-title="' + tableArr[5] + '" class="text-right"><a href="baoxiao-table.html?id=' + data.rid +
                            '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.rid + '" onclick="del(this)">删除</a>' +
                            '</td></tr>';
                    } else if (data.status == 2) {
                        bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + getTime(data.createTime) + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.reimburseUserName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.money) + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + data.marketName + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right">' + data.typeName + '</td>' +
                            '<td data-title="' + tableArr[5] + '" class="text-right"><a href="baoxiao-table.html?id=' + data.rid +
                            '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.rid + '" onclick="del(this)">删除</a>' +
                            '</td></tr>';
                    }
                })
                $(tableBody).append(bCtn);
            } else {
                $('.nextpage').text('没有更多数据了').prop('disabled', true);
            }
        })
    })
}
showPageData()