/**
 * 获取账户列表
 */
function getShouruList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['分红类型', '操作'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    $.get(urlInfo() + '/admin/bonus/extralist', {
        'page': 1,
        'rows': 10
    }, function(res) {
        setStr('page', 1);
        setCookie('loginStatues', res.status.code);
        $.each(res.data, function(key, data) {
            if ((key + 1) % 2 == 0) {
                bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.bonusType + '</td>' +
                    '<td data-title="' + tableArr[1] + '" class="text-right"><a href="setfenhong-table.html?type=0&id=' + data.id +
                    '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
            } else {
                bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.bonusType + '</td>' +
                    '<td data-title="' + tableArr[1] + '" class="text-right"><a href="setfenhong-table.html?type=0&id=' + data.id +
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
    var type = $('#type').val();
    if (confirm('确认删除?')) {
        if (type == 0) {
            $.post(urlInfo() + '/admin/bonus/extradelete', {
                'id': delid
            }, function(res) {
                if (res.status.code == 200) {
                    alert('删除成功');
                    location.href = "setfenhong-list.html";
                }
            })
        } else {
            $.post(urlInfo() + '/admin/bonus/leveldelete', {
                'id': delid
            }, function(res) {
                if (res.status.code == 200) {
                    alert('删除成功');
                    location.href = "setfenhong-list.html";
                }
            })
        }

    }
}

/**
 * 搜索结果
 */
function searchResult() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['分红类型', '操作'],
        tableArr2 = ['分红名称', '分红比例', '对应金额', '操作'],
        hCtn = '',
        bCtn = '';
    var type = $('#searchKey').val();
    if (type == 0) {
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        $.get(urlInfo() + '/admin/bonus/extralist', {
            'page': 1,
            'rows': 10
        }, function(res) {
            setStr('page', 1);
            setCookie('loginStatues', res.status.code);
            $.each(res.data, function(key, data) {
                if ((key + 1) % 2 == 0) {
                    bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.bonusType + '</td>' +
                        '<td data-title="' + tableArr[1] + '" class="text-right"><a href="setfenhong-table.html?type=0&id=' + data.id +
                        '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
                } else {
                    bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.bonusType + '</td>' +
                        '<td data-title="' + tableArr[1] + '" class="text-right"><a href="setfenhong-table.html?type=0&id=' + data.id +
                        '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
                }
            })
            $(tableBody).html(bCtn);
        })
    } else if (type == 1) {
        for (var i = 0; i < tableArr2.length; i++) {
            hCtn += '<th class="text-right">' + tableArr2[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        $.get(urlInfo() + '/admin/bonus/levellist', {
            'page': 1,
            'rows': 10
        }, function(res) {
            setStr('page', 1);
            setCookie('loginStatues', res.status.code);
            $.each(res.data, function(key, data) {
                if ((key + 1) % 2 == 0) {
                    bCtn += '<tr class="gradeX"><td data-title="' + tableArr2[0] + '">' + data.bonusLevelName + '</td>' +
                        '<td data-title="' + tableArr2[1] + '">' + toFloat(data.bonusRate) + '</td>' +
                        '<td data-title="' + tableArr2[2] + '">' + toFloat(data.bonusLevelMoney) + '</td>' +
                        '<td data-title="' + tableArr2[3] + '" class="text-right"><a href="setfenhong-table.html?type=1&id=' + data.id +
                        '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
                } else {
                    bCtn += '<tr class="gradeC"><td data-title="' + tableArr2[0] + '">' + data.bonusLevelName + '</td>' +
                        '<td data-title="' + tableArr2[1] + '">' + toFloat(data.bonusRate) + '</td>' +
                        '<td data-title="' + tableArr2[2] + '">' + toFloat(data.bonusLevelMoney) + '</td>' +
                        '<td data-title="' + tableArr2[3] + '" class="text-right"><a href="setfenhong-table.html?type=1&id=' + data.id +
                        '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
                }
            })
            $(tableBody).html(bCtn);
        })
    }
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
            tableArr = ['分红类型', '操作'],
            tableArr2 = ['分红名称', '分红比例', '对应金额', '操作'],
            hCtn = '',
            bCtn = '';
        var type = $('#searchKey').val();
        if (type == 0) {
            for (var i = 0; i < tableArr.length; i++) {
                hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
            }
            $(tableHeader).html(hCtn);
            $.get(urlInfo() + '/admin/bonus/extralist', {
                'page': page,
                'rows': 10
            }, function(res) {
                if (res.data.length != 0) {
                    setStr('page', page);
                    setCookie('loginStatues', res.status.code);
                    $.each(res.data, function(key, data) {
                        if ((key + 1) % 2 == 0) {
                            bCtn += '<tr class="gradeX"><td data-title="' + tableArr[0] + '">' + data.bonusType + '</td>' +
                                '<td data-title="' + tableArr[1] + '" class="text-right"><a href="setfenhong-table.html?type=0&id=' + data.id +
                                '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
                        } else {
                            bCtn += '<tr class="gradeC"><td data-title="' + tableArr[0] + '">' + data.bonusType + '</td>' +
                                '<td data-title="' + tableArr[1] + '" class="text-right"><a href="setfenhong-table.html?type=0&id=' + data.id +
                                '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
                        }
                    })
                    $(tableBody).append(bCtn);
                } else {
                    $('.nextpage').text('没有更多数据了').prop('disabled', true);
                }
            })
        } else if (type == 1) {
            for (var i = 0; i < tableArr2.length; i++) {
                hCtn += '<th class="text-right">' + tableArr2[i] + '</th>';
            }
            $(tableHeader).html(hCtn);
            $.get(urlInfo() + '/admin/bonus/levellist', {
                'page': page,
                'rows': 10
            }, function(res) {
                if (res.data.length != 0) {
                    setStr('page', page);
                    setCookie('loginStatues', res.status.code);
                    $.each(res.data, function(key, data) {
                        if ((key + 1) % 2 == 0) {
                            bCtn += '<tr class="gradeX"><td data-title="' + tableArr2[0] + '">' + data.bonusLevelName + '</td>' +
                                '<td data-title="' + tableArr2[1] + '">' + toFloat(data.bonusRate) + '</td>' +
                                '<td data-title="' + tableArr2[2] + '">' + toFloat(data.bonusLevelMoney) + '</td>' +
                                '<td data-title="' + tableArr2[3] + '" class="text-right"><a href="setfenhong-table.html?type=1&id=' + data.id +
                                '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
                        } else {
                            bCtn += '<tr class="gradeC"><td data-title="' + tableArr2[0] + '">' + data.bonusLevelName + '</td>' +
                                '<td data-title="' + tableArr2[1] + '">' + toFloat(data.bonusRate) + '</td>' +
                                '<td data-title="' + tableArr2[2] + '">' + toFloat(data.bonusLevelMoney) + '</td>' +
                                '<td data-title="' + tableArr2[3] + '" class="text-right"><a href="setfenhong-table.html?type=1&id=' + data.id +
                                '">编辑</a>|<a href="javascript:void(0)" data-id="' + data.id + '" onclick="del(this)">删除</a></td></tr>';
                        }
                    })
                    $(tableBody).append(bCtn);
                } else {
                    $('.nextpage').text('没有更多数据了').prop('disabled', true);
                }
            })
        }
    })
}
getMoreData()