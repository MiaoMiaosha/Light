//获取入账信息列表
function getEnterList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['到账日期', '市场名称', '款项类型', '到账金额', '操作'],
        hCtn = '',
        bCtn = '',
        state = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    $.get(urlInfo() + '/admin/account/list', {
        'page': 1,
        'rows': 10
    }, function(res) {
        setStr('page', 1);
        setCookie('loginStatues', res.status.code);
        $.each(res.data, function(key, data) {
            if (data.status == 0) {
                state = '未开';
            } else {
                state = '已开';
            }
            if ((key + 1) % 2 == 0) {
                bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + getTime(data.receiveDate) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.receiveMoney) + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right"><a href="enter-table.html?id=' + data.aid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.aid + '" onclick="del(this)">删除</a></td></tr>';
            } else {
                bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + getTime(data.receiveDate) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.receiveMoney) + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right"><a href="enter-table.html?id=' + data.aid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.aid + '" onclick="del(this)">删除</a></td></tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}
getEnterList();

//删除
function del(event) {
    var delid = $(event).attr('data-id');
    if (confirm('确认删除?')) {
        $.post(urlInfo() + '/admin/account/delete', {
            'id': delid
        }, function(res) {
            if (res.status.code == 200) {
                alert('删除成功');
                location.href = "enter-list.html";
            }
        })
    }
}
//搜索
function searchResult() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['到账日期', '市场名称', '款项类型', '到账金额', '操作'],
        hCtn = '',
        bCtn = '',
        state = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var statusId = $('#searchKey').val();
    $.get(urlInfo() + '/admin/account/list', {
        'status': statusId
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (data.status == 0) {
                state = '未开';
            } else {
                state = '已开';
            }
            if ((key + 1) % 2 == 0) {
                bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + getTime(data.receiveDate) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.receiveMoney) + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right"><a href="enter-table.html?id=' + data.aid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.aid + '" onclick="del(this)">删除</a></td></tr>';
            } else {
                bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + getTime(data.receiveDate) + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.marketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.receiveMoney) + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right"><a href="enter-table.html?id=' + data.aid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.aid + '" onclick="del(this)">删除</a></td></tr>';
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

/**
 * 加载更多
 */
function getMoreData() {
    $('.nextpage').on('click', function() {
        var page = parseInt(getStr('page')) + 1;
        var tablebox = $('#datatable-default'),
            tableHeader = $(tablebox).find('thead tr'),
            tableBody = $(tablebox).find('tbody'),
            tableArr = ['到账日期', '市场名称', '款项类型', '到账金额', '操作'],
            hCtn = '',
            bCtn = '',
            state = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        $.get(urlInfo() + '/admin/account/list', {
            'page': page,
            'rows': 10
        }, function(res) {
            if (res.data.length != 0) {
                setStr('page', page);
                setCookie('loginStatues', res.status.code);
                $.each(res.data, function(key, data) {
                    if (data.status == 0) {
                        state = '未开';
                    } else {
                        state = '已开';
                    }
                    if ((key + 1) % 2 == 0) {
                        bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + getTime(data.receiveDate) + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.marketName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + data.typeName + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.receiveMoney) + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right"><a href="enter-table.html?id=' + data.aid +
                            '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.aid + '" onclick="del(this)">删除</a></td></tr>';
                    } else {
                        bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + getTime(data.receiveDate) + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.marketName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + data.typeName + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.receiveMoney) + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right"><a href="enter-table.html?id=' + data.aid +
                            '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.aid + '" onclick="del(this)">删除</a></td></tr>';
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