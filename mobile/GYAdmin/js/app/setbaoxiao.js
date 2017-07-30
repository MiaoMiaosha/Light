/**
 * 获取账户列表
 */
function getShouruList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['报销类型', '操作'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    $.get(urlInfo() + '/admin/reimburse/extralist', {
        'page': 1,
        'rows': 10
    }, function(res) {
        setStr('page', 1);
        setCookie('loginStatues', res.status.code);
        $.each(res.data, function(key, data) {
            if ((key + 1) % 2 == 0) {
                bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[1] + '" class="text-right"><a href="setbaoxiao-table.html?id=' + data.id +
                    '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
            } else {
                bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.typeName + '</td>' +
                    '<td data-title="' + tableArr[1] + '" class="text-right"><a href="setbaoxiao-table.html?id=' + data.id +
                    '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}
getShouruList();


/**
 * 删除收入
 */
function del(event) {
    var delid = $(event).attr('data-id');
    if (confirm('确认删除?')) {
        $.post(urlInfo() + '/admin/reimburse/extradelete', {
            'id': delid
        }, function(res) {
            if (res.status.code == 200) {
                alert('删除成功');
                location.href = "setbaoxiao-list.html";
            }
        })
    }
}

/**
 * 搜索结果
 */
// function searchResult() {
//     var tablebox = $('#datatable-default'),
//         tableHeader = $(tablebox).find('thead tr'),
//         tableBody = $(tablebox).find('tbody'),
//         tableArr = ['收入类型', '收入金额', '操作'],
//         hCtn = '',
//         bCtn = '';
//     for (var i = 0; i < tableArr.length; i++) {
//         hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
//     }
//     $(tableHeader).html(hCtn);
//     var type = $('#searchKey').val();
//     $.get(urlInfo() + '/admin/salary/extra', {
//         'type': type
//     }, function(res) {
//         setCookie('loginStatues', res.status.code);
//         $.each(res.data, function(key, data) {
//             if ((key + 1) % 2 == 0) {
//                 bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.typeName + '</td>' +
//                     '<td data-title="' + tableArr[1] + '">' + data.typeMoney + '</td>' +
//                     '<td data-title="' + tableArr[2] + '" class="text-right"><a href="setshouru-table.html?id=' + data.tid +
//                     '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.tid + '" onclick="del(this)">删除</a></td></tr>';
//             } else {
//                 bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.typeName + '</td>' +
//                     '<td data-title="' + tableArr[1] + '">' + data.typeMoney + '</td>' +
//                     '<td data-title="' + tableArr[2] + '" class="text-right"><a href="setshouru-table.html?id=' + data.tid +
//                     '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.tid + '" onclick="del(this)">删除</a></td></tr>';
//             }
//         })
//         $(tableBody).html(bCtn);
//     })
// }

/**
 * 加载更多
 */
function getMoreData() {
    $('.nextpage').on('click', function() {
        var page = parseInt(getStr('page')) + 1;
        var tablebox = $('#datatable-default'),
            tableHeader = $(tablebox).find('thead tr'),
            tableBody = $(tablebox).find('tbody'),
            tableArr = ['报销类型', '操作'],
            hCtn = '',
            bCtn = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        $.get(urlInfo() + '/admin/reimburse/extralist', {
            'page': page,
            'rows': 10
        }, function(res) {
            if (res.data.length != 0) {
                setStr('page', page);
                setCookie('loginStatues', res.status.code);
                $.each(res.data, function(key, data) {
                    if ((key + 1) % 2 == 0) {
                        bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.typeName + '</td>' +
                            '<td data-title="' + tableArr[1] + '" class="text-right"><a href="setbaoxiao-table.html?id=' + data.id +
                            '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
                    } else {
                        bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.typeName + '</td>' +
                            '<td data-title="' + tableArr[1] + '" class="text-right"><a href="setbaoxiao-table.html?id=' + data.id +
                            '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
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