//获取员工信息列表
function getProjectList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['合同人', '市场名称', '单位名称', '合同价', '差旅类型', '操作'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var eid = getStr('employeeId');
    $.get(urlInfo() + '/admin/project/list', {
        'page': 1,
        'rows': 10,
        'employeeId': eid
    }, function(res) {
        setStr('page', 1);
        setCookie('loginStatues', res.status.code);
        $.each(res.data, function(key, data) {
            var eNames = '|';
            $.each(data.list, function(index, elist) {
                eNames += elist.employeeName + '|';
            });
            var busType = data.project.busTravelType;
            var busName = '';
            if (busType == 0) {
                busName = '甲方全报销';
            } else if (busType == 1) {
                busName = '甲方报销交通';
            } else if (busType == 2) {
                busName = '甲方报销住宿';
            } else {
                busName = '乙方全报销';
            }
            if (data.project.status == 0) {
                bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + eNames + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.project.marketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.project.customerName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.project.contactMoney) + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + busName + '</td>' +
                    '<td data-title="' + tableArr[5] + '" class="text-right"><a href="project-table.html?id=' + data.project.pid +
                    '" onclick="edit(this)">编辑</a></td></tr>';
            } else if (data.project.status == 1) {
                bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + eNames + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.project.marketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.project.customerName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.project.contactMoney) + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + busName + '</td>' +
                    '<td data-title="' + tableArr[5] + '" class="text-right"><a href="project-table.html?id=' + data.project.pid +
                    '" onclick="edit(this)">编辑</a></td></tr>';
            } else if (data.project.status == 2) {
                bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + eNames + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.project.marketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.project.customerName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.project.contactMoney) + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right">' + busName + '</td>' +
                    '<td data-title="' + tableArr[5] + '" class="text-right"><a href="project-table.html?id=' + data.project.pid +
                    '" onclick="edit(this)">编辑</a></td></tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}
getProjectList();

function searchResult() {
    $('.nextpage').text('加载更多').prop('disabled', false);
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['合同人', '市场名称', '单位名称', '合同价', '差旅类型', '操作'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var statusId = $('#searchKey').val(),
        marketName = $('#search').val();
    var eid = getStr('employeeId');
    $.get(urlInfo() + '/admin/project/list', {
        // 'status': statusId,
        'marketName': marketName,
        'page': 1,
        'rows': 10,
        'employeeId': eid
    }, function(res) {
        if (res.data.length != 0) {
            setStr('page', 1);
            $.each(res.data, function(key, data) {
                var eNames = '|';
                $.each(data.list, function(index, elist) {
                    eNames += elist.employeeName + '|';
                });
                var busType = data.project.busTravelType;
                var busName = '';
                if (busType == 0) {
                    busName = '甲方全报销';
                } else if (busType == 1) {
                    busName = '甲方报销交通';
                } else if (busType == 2) {
                    busName = '甲方报销住宿';
                } else {
                    busName = '乙方全报销';
                }
                if (data.project.status == 0) {
                    bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + eNames + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.project.marketName + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + data.project.customerName + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.project.contactMoney) + '</td>' +
                        '<td data-title="' + tableArr[4] + '" class="text-right">' + busName + '</td>' +
                        '<td data-title="' + tableArr[5] + '" class="text-right"><a href="project-table.html?id=' + data.project.pid +
                        '" onclick="edit(this)">编辑</a></td></tr>';
                } else if (data.project.status == 1) {
                    bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + eNames + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.project.marketName + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + data.project.customerName + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.project.contactMoney) + '</td>' +
                        '<td data-title="' + tableArr[4] + '" class="text-right">' + busName + '</td>' +
                        '<td data-title="' + tableArr[5] + '" class="text-right"><a href="project-table.html?id=' + data.project.pid +
                        '" onclick="edit(this)">编辑</a></td></tr>';
                } else if (data.project.status == 2) {
                    bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + eNames + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.project.marketName + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + data.project.customerName + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.project.contactMoney) + '</td>' +
                        '<td data-title="' + tableArr[4] + '" class="text-right">' + busName + '</td>' +
                        '<td data-title="' + tableArr[5] + '" class="text-right"><a href="project-table.html?id=' + data.project.pid +
                        '" onclick="edit(this)">编辑</a></td></tr>';
                }
            })
            $(tableBody).html(bCtn);
        } else {
            $('.nextpage').text('没有更多数据了').prop('disabled', true);
        }
    })
}

function getMoreData() {
    $('.nextpage').on('click', function() {
        var page = parseInt(getStr('page')) + 1;
        var tablebox = $('#datatable-default'),
            tableHeader = $(tablebox).find('thead tr'),
            tableBody = $(tablebox).find('tbody'),
            tableArr = ['合同人', '市场名称', '单位名称', '合同价', '差旅类型', '操作'],
            hCtn = '',
            bCtn = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        var statusId = $('#searchKey').val(),
            marketName = $('#search').val();
        var eid = getStr('employeeId');
        $.get(urlInfo() + '/admin/project/list', {
            // 'status': statusId,
            'marketName': marketName,
            'page': page,
            'rows': 10,
            'employeeId': eid
        }, function(res) {
            if (res.data.length != 0) {
                setStr('page', page);
                $.each(res.data, function(key, data) {
                    var eNames = '|';
                    $.each(data.list, function(index, elist) {
                        eNames += elist.employeeName + '|';
                    });
                    var busType = data.project.busTravelType;
                    var busName = '';
                    if (busType == 0) {
                        busName = '甲方全报销';
                    } else if (busType == 1) {
                        busName = '甲方报销交通';
                    } else if (busType == 2) {
                        busName = '甲方报销住宿';
                    } else {
                        busName = '乙方全报销';
                    }
                    if (data.project.status == 0) {
                        bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + eNames + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.project.marketName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + data.project.customerName + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.project.contactMoney) + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right">' + busName + '</td>' +
                            '<td data-title="' + tableArr[5] + '" class="text-right"><a href="project-table.html?id=' + data.project.pid +
                            '" onclick="edit(this)">编辑</a></td></tr>';
                    } else if (data.project.status == 1) {
                        bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + eNames + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.project.marketName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + data.project.customerName + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.project.contactMoney) + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right">' + busName + '</td>' +
                            '<td data-title="' + tableArr[5] + '" class="text-right"><a href="project-table.html?id=' + data.project.pid +
                            '" onclick="edit(this)">编辑</a></td></tr>';
                    } else if (data.project.status == 2) {
                        bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + eNames + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.project.marketName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + data.project.customerName + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.project.contactMoney) + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right">' + busName + '</td>' +
                            '<td data-title="' + tableArr[5] + '" class="text-right"><a href="project-table.html?id=' + data.project.pid +
                            '" onclick="edit(this)">编辑</a></td></tr>';
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