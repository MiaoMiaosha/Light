//获取员工信息列表
function getCooperateList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['合作方', '合作电话', '操作'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    $.get(urlInfo() + '/admin/partner/list', {
        'page': 1,
        'rows': 10
    }, function(res) {
        setCookie('loginStatues', res.status.code);
        setStr('page', 1);
        $.each(res.data, function(key, data) {
            //state 0-未审核 1-已通过 2-不通过
            if (data.status == 0) {
                bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + data.cooperateName + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right"><a href="cooperate-table.html?id=' + data.pid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.pid + '" onclick="del(this)">删除</a></td></tr>';
            } else if (data.status == 1) {
                bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + data.cooperateName + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right"><a href="cooperate-table.html?id=' + data.pid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.pid + '" onclick="del(this)">删除</a></td></tr>';
            } else if (data.status == 2) {
                bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + data.cooperateName + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right"><a href="cooperate-table.html?id=' + data.pid +
                    '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.pid + '" onclick="del(this)">删除</a></td></tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}
getCooperateList();

//删除
function del(event) {
    var delid = $(event).attr('data-id');
    if (confirm('确认删除?')) {
        $.post(urlInfo() + '/admin/partner/delete', {
            'pid': delid
        }, function(res) {
            if (res.status.code == 200) {
                alert('删除成功');
                location.href = "cooperate-list.html";
            }
        })
    }
}
//搜索
function searchResult() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['合作方', '合作电话', '操作'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var statusId = $('#searchKey').val(),
        keyWords = $('#search').val();
    $.get(urlInfo() + '/admin/partner/list', {
        // 'status': statusId,
        'chiefPerson': keyWords,
        'page': 1,
        'rows': 10
    }, function(res) {
        if (res.data.length != 0) {
            setStr('page', 1);
            $.each(res.data, function(key, data) {
                //state 0-未审核 1-已通过 2-不通过
                if (data.status == 0) {
                    bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + data.cooperateName + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right"><a href="cooperate-table.html?id=' + data.pid +
                        '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.pid + '" onclick="del(this)">删除</a></td></tr>';
                } else if (data.status == 1) {
                    bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + data.cooperateName + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right"><a href="cooperate-table.html?id=' + data.pid +
                        '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.pid + '" onclick="del(this)">删除</a></td></tr>';
                } else if (data.status == 2) {
                    bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + data.cooperateName + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right"><a href="cooperate-table.html?id=' + data.pid +
                        '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.pid + '" onclick="del(this)">删除</a></td></tr>';
                }
            })
            $(tableBody).html(bCtn);
        } else {
            $('.nextpage').text('没有更多数据了').prop('disabled', true);
        }
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
 * 记载更多
 */
function getMoreData() {
    $('.nextpage').on('click', function() {
        var page = parseInt(getStr('page')) + 1;
        var tablebox = $('#datatable-default'),
            tableHeader = $(tablebox).find('thead tr'),
            tableBody = $(tablebox).find('tbody'),
            tableArr = ['合作方', '合作电话', '操作'],
            hCtn = '',
            bCtn = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        var statusId = $('#searchKey').val(),
            keyWords = $('#search').val();
        $.get(urlInfo() + '/admin/partner/list', {
            // 'status': statusId,
            'chiefPerson': keyWords,
            'page': page,
            'rows': 10
        }, function(res) {
            if (res.data.length != 0) {
                setStr('page', page);
                $.each(res.data, function(key, data) {
                    //state 0-未审核 1-已通过 2-不通过
                    if (data.status == 0) {
                        bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + data.cooperateName + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right"><a href="cooperate-table.html?id=' + data.pid +
                            '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.pid + '" onclick="del(this)">删除</a></td></tr>';
                    } else if (data.status == 1) {
                        bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + data.cooperateName + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right"><a href="cooperate-table.html?id=' + data.pid +
                            '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.pid + '" onclick="del(this)">删除</a></td></tr>';
                    } else if (data.status == 2) {
                        bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + data.cooperateName + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.mobile + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right"><a href="cooperate-table.html?id=' + data.pid +
                            '" onclick="edit(this)">详情/编辑</a>|<a href="javascript:void(0)" data-id="' + data.pid + '" onclick="del(this)">删除</a></td></tr>';
                    }
                })
                $(tableBody).append(bCtn);
            } else {
                $('.nextpage').text('没有更多数据了').prop('disabled', true);
            }
        })
    })
}
getMoreData();