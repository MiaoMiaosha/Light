//获取员工信息列表
function getBonusList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['分红人', '市场名称', '到账金额', '分红金额'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var fid;
    if (getStr('employeeId') != null) {
        fid = getStr('employeeId');
    }
    $.get(urlInfo() + '/admin/bonus/list', {
        'employeeId': fid,
        'page': 1,
        'rows': 10
    }, function(res) {
        setStr('page', 1);
        setCookie('loginStatues', res.status.code);
        $.each(res.data, function(key, data) {
            var bNames = '|';
            $.each(data.bonusIds, function(index, element) {
                bNames += element.employeeName + '|';
            });
            if (data.bonus.status == 0) {
                bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                    // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                    '</tr>';
                // <td data-title="' + tableArr[4] + '" class="text-right"><a href="fenhong-table.html?id=' + data.bonus.bid +
                //'" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.bonus.bid +
                //'" onclick="del(this)">删除</a>|<a href="javascript:void(0)" data-id="' + data.bonus.bid + '" onclick="check(this)">审核</a></td>
            } else if (data.bonus.status == 1) {
                bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                    // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                    '</tr>';
            } else if (data.bonus.status == 2) {
                bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                    // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                    '</tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}
getBonusList();

//删除
function del(event) {
    var delid = $(event).attr('data-id');
    if (confirm('确认删除?')) {
        $.post(urlInfo() + '/admin/bonus/delete', {
            'id': delid
        }, function(res) {
            if (res.status.code == 200) {
                alert('删除成功');
                location.href = "fenhong-list.html";
            } else {
                alert('删除失败');
            }
        })
    }
}

//审核
function check(event) {
    var cid = $(event).attr('data-id'),
        dialogCtn = '<div class="dialog1 d_none"><div class="mask"></div>' +
        '<div class="dialogCtn trans"><div class="title"><p>审核分红</p></div>' +
        '<div class="body"><select class="checkSelect" name="checkSelect" id="checkSelect">' +
        '<option value="1">审核通过</option><option value="2">审核不通过</option></select></div>' +
        '<div class="footer"><ul class="clearfix"><li class="f_left flis">' +
        '<button type="button" class="btnOk btnFooter">确认</button></li>' +
        '<li class="f_left flis"><button type="button" class="btnFooter cancel">取消</button></li></ul></div></div></div>';
    $('.dialog1').remove();
    $('body').append(dialogCtn);
    //取消
    $('.cancel').on('click', function() {
        $(this).parents('.dialog1').addClass('d_none');
    });
    //确定
    $('.btnOk').on('click', function() {
        $.post(urlInfo() + '/admin/bonus/confirm', {
            'id': cid,
            'status': $('#checkSelect').val()
        }, function(res) {
            if (res.status.code == 200) {
                alert('操作成功');
            } else {
                alert('操作失败');
            }
        }, 'json');
        $(this).parents('.dialog1').addClass('d_none');
    });
    dialogAnimation();
}



//搜索
function searchResult() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['分红人', '市场名称', '到账金额', '分红金额'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var statusId = $('#searchKey').val();
    $.get(urlInfo() + '/admin/bonus/list', {
        'status': statusId
    }, function(res) {
        $.each(res.data, function(key, data) {
            var bNames = '|';
            $.each(data.bonusIds, function(index, element) {
                bNames += element.employeeName + '|';
            });
            if (data.bonus.status == 0) {
                bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                    // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right"><a href="fenhong-table.html?id=' + data.bonus.bid +
                    '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.bonus.bid +
                    '" onclick="del(this)">删除</a>|<a href="javascript:void(0)" data-id="' + data.bonus.bid + '" onclick="check(this)">审核</a></td></tr>';
            } else if (data.bonus.status == 1) {
                bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                    // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right"><a href="fenhong-table.html?id=' + data.bonus.bid +
                    '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.bonus.bid +
                    '" onclick="del(this)">删除</a>|<a href="javascript:void(0)" data-id="' + data.bonus.bid + '" onclick="check(this)">审核</a></td></tr>';
            } else if (data.bonus.status == 2) {
                bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                    // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                    '<td data-title="' + tableArr[4] + '" class="text-right"><a href="fenhong-table.html?id=' + data.bonus.bid +
                    '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.bonus.bid +
                    '" onclick="del(this)">删除</a>|<a href="javascript:void(0)" data-id="' + data.bonus.bid + '" onclick="check(this)">审核</a></td></tr>';
            }
        })
        $(tableBody).html(bCtn);
    })
}

/**
 * 使用市场名称搜索
 */
function useMarketNameSearch() {
    $('#searchbtn').on('click', function() {
        var tablebox = $('#datatable-default'),
            tableHeader = $(tablebox).find('thead tr'),
            tableBody = $(tablebox).find('tbody'),
            tableArr = ['分红人', '市场名称', '到账金额', '分红金额'],
            hCtn = '',
            bCtn = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        var statusId = $('#searchKey').val(),
            marketName = $('#searchWords').val();
        var selectData = $('#startTime').val();
        var firstData = '',
            lastData = '';
        if (selectData != '') {
            firstData = getUnix(selectData + '-01');
            lastData = getLastDay1(selectData.split('-')[0], selectData.split('-')[1]);
        }
        if (typeof firstData == NaN) {
            firstData = '';
        }
        if (typeof lastData == NaN) {
            lastData = '';
        }
        var fid;
        if (getStr('employeeId') != null) {
            fid = getStr('employeeId');
        }
        $.get(urlInfo() + '/admin/bonus/list', {
            'page': 1,
            'rows': 10,
            'status': statusId,
            'marketName': marketName,
            'startTime': firstData,
            'endTime': lastData,
            'employeeId': fid
        }, function(res) {
            if (res.data.length != 0) {

                setStr('page', 1);
                $.each(res.data, function(key, data) {
                    var bNames = '|';
                    $.each(data.bonusIds, function(index, element) {
                        bNames += element.employeeName + '|';
                    });
                    if (data.bonus.status == 0) {
                        bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                            // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                            '</tr>';
                    } else if (data.bonus.status == 1) {
                        bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                            // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                            '</tr>';
                    } else if (data.bonus.status == 2) {
                        bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                            // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                            '</tr>';
                    }
                })
                $(tableBody).html(bCtn);
            } else {
                $(tableBody).html(bCtn);
                $('.nextpage').text('没有更多数据了').prop('disabled', true);
            }
        })
    })
}
useMarketNameSearch();


/**
 * 加载更多
 */
function getMoreData() {
    $('.nextpage').on('click', function() {
        var page = parseInt(getStr('page')) + 1;
        var tablebox = $('#datatable-default'),
            tableHeader = $(tablebox).find('thead tr'),
            tableBody = $(tablebox).find('tbody'),
            tableArr = ['分红人', '市场名称', '到账金额', '分红金额'],
            hCtn = '',
            bCtn = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        var statusId = $('#searchKey').val(),
            marketName = $('#searchWords').val();
        var selectData = $('#startTime').val();
        var firstData = '',
            lastData = '';
        if (selectData != '') {
            firstData = getUnix(selectData + '-01');
            lastData = getLastDay1(selectData.split('-')[0], selectData.split('-')[1]);
        }
        if (typeof firstData == NaN) {
            firstData = '';
        }
        if (typeof lastData == NaN) {
            lastData = '';
        }
        var fid;
        if (getStr('employeeId') != null) {
            fid = getStr('employeeId');
        }
        $.get(urlInfo() + '/admin/bonus/list', {
            'page': page,
            'rows': 10,
            'status': statusId,
            'marketName': marketName,
            'startTime': firstData,
            'endTime': lastData,
            'employeeId': fid
        }, function(res) {
            if (res.data.length != 0) {
                setStr('page', page);
                $.each(res.data, function(key, data) {
                    var bNames = '|';
                    $.each(data.bonusIds, function(index, element) {
                        bNames += element.employeeName + '|';
                    });
                    if (data.bonus.status == 0) {
                        bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                            // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                            '</tr>';
                    } else if (data.bonus.status == 1) {
                        bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                            // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                            '</tr>';
                    } else if (data.bonus.status == 2) {
                        bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + bNames + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.bonus.marketName + '</td>' +
                            // '<td data-title="' + tableArr[2] + '" class="text-right">' + data.bonus.typeId + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + toFloat(data.bonus.receiveMoney) + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.bonus.bonusMoney) + '</td>' +
                            '</tr>';
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


/**
 * 获取当月收入
 */
function getTotal(event) {
    var selectData = $(event).val();
    var firstData = '',
        lastData = '';
    if (selectData != '') {
        firstData = getUnix(selectData + '-01');
        lastData = getLastDay1(selectData.split('-')[0], selectData.split('-')[1]);
    }
    if (typeof firstData == NaN) {
        firstData = '';
    }
    if (typeof lastData == NaN) {
        lastData = '';
    }
    var fid;
    if (getStr('employeeId') != null) {
        fid = getStr('employeeId');
    }
    $.get(urlInfo() + '/admin/total/staffinfo', {
        'employeeId': fid,
        'startTime': firstData,
        'endTime': lastData
    }, function(res) {
        if (res.status.code === 200) {
            $.each(res.data.monthSalary, function(i) {
                if (this.status === 1) {
                    $('#tSalary i').text(toFloat(this.bonusMoney));
                    return false;
                } else {
                    $('#tSalary i').text('0.00');
                }
            })
        }
    }, 'json')
}