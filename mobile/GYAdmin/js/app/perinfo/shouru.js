//获取收入信息列表
function getSalaryList() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['设计师', '市场名称', '设计内容', '收入金额'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var userid = getStr('employeeId');
    $.get(urlInfo() + '/admin/salary/list?employeeId=' + userid, {
        'page': 1,
        'rows': 10
    }, function(res) {
        setCookie('loginStatues', res.status.code);
        setStr('page', 1);
        $.each(res.data, function(key, data) {
            if (data.status == 0) {
                bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                    '</tr>';
            } else if (data.status == 1) {
                bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                    '</tr>';
            } else if (data.status == 2) {
                bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                    '</tr>';
                // |<a href="javascript:void(0)" data-id="' + data.sid + '" onclick="check(this)">审核</a>
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
        $.post(urlInfo() + '/admin/salary/delete', {
            'sid': delid
        }, function(res) {
            if (res.status.code == 200) {
                alert('删除成功');
                location.href = "shouru-list.html";
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
        '<div class="dialogCtn trans"><div class="title"><p>审核收入</p></div>' +
        '<div class="body"><select class="checkSelect" name="checkSelect" id="checkSelect">' +
        '<option value="1">审核通过</option><option value="2">审核关闭</option></select></div>' +
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
        var status = $('#checkSelect').val();
        if (status == 1) {
            $.post(urlInfo() + '/admin/salary/confirm', {
                'sid': cid
                    // 'status': $('#checkSelect').val()
            }, function(res) {
                if (res.status.code == 200) {
                    alert('操作成功');
                } else {
                    alert('操作失败');
                }
            }, 'json');
        } else {
            $.post(urlInfo() + '/admin/salary/edit', {
                'sid': cid,
                'status': 2
            }, function(res) {
                if (res.status.code == 200) {
                    alert('操作成功');
                } else {
                    alert('操作失败');
                }
            }, 'json');
        }
        $(this).parents('.dialog1').addClass('d_none');
    });
    dialogAnimation();
}

//搜索
function searchResult() {
    var tablebox = $('#datatable-default'),
        tableHeader = $(tablebox).find('thead tr'),
        tableBody = $(tablebox).find('tbody'),
        tableArr = ['设计师', '市场名称', '设计内容', '收入金额'],
        hCtn = '',
        bCtn = '';
    for (var i = 0; i < tableArr.length; i++) {
        hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
    }
    $(tableHeader).html(hCtn);
    var statusId = $('#searchKey').val();
    var userid = getStr('employeeId');
    $.get(urlInfo() + '/admin/salary/list', {
        'status': statusId,
        'employeeId': userid
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (data.status == 0) {
                bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                    '</tr>';
            } else if (data.status == 1) {
                bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                    '</tr>';
            } else if (data.status == 2) {
                bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                    '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                    '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                    '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                    '</tr>';
                // |<a href="javascript:void(0)" data-id="' + data.sid + '" onclick="check(this)">审核</a>
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
            tableArr = ['设计师', '市场名称', '设计内容', '收入金额'],
            hCtn = '',
            bCtn = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        var statusId = $('#searchKey').val(),
            marketName = $('#searchWords').val();
        var userid = getStr('employeeId');
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
        $.get(urlInfo() + '/admin/salary/list', {
            'rows': 10,
            'page': 1,
            'status': statusId,
            'employeeId': userid,
            'marketName': marketName,
            'startTime': firstData,
            'endTime': lastData
        }, function(res) {
            setStr('page', 1);
            $.each(res.data, function(key, data) {
                if (data.status == 0) {
                    bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                        '</tr>';
                } else if (data.status == 1) {
                    bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                        '</tr>';
                } else if (data.status == 2) {
                    bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                        '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                        '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                        '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                        '</tr>';
                    // |<a href="javascript:void(0)" data-id="' + data.sid + '" onclick="check(this)">审核</a>
                }
            })
            $(tableBody).html(bCtn);
        })
    })
}
useMarketNameSearch()

/**加载更多 */
function showPageData() {
    $('.nextpage').on('click', function() {
        var page = parseInt(getStr('page')) + 1;
        var employeeId = $('#searchKey').val();
        // $('.prevpage').removeClass('d_none');
        var tablebox = $('#datatable-default'),
            tableHeader = $(tablebox).find('thead tr'),
            tableBody = $(tablebox).find('tbody'),
            tableArr = ['设计师', '市场名称', '设计内容', '收入金额', '操作'],
            hCtn = '',
            bCtn = '';
        for (var i = 0; i < tableArr.length; i++) {
            hCtn += '<th class="text-right">' + tableArr[i] + '</th>';
        }
        $(tableHeader).html(hCtn);
        var statusId = $('#searchKey').val(),
            marketName = $('#searchWords').val();
        var userid = getStr('employeeId');
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
        $.get(urlInfo() + '/admin/salary/list', {
            'rows': 10,
            'page': page,
            'status': statusId,
            'employeeId': userid,
            'marketName': marketName,
            'startTime': firstData,
            'endTime': lastData
        }, function(res) {
            if (res.data.length > 0) {
                setStr('page', page);
                setCookie('loginStatues', res.status.code);
                $.each(res.data, function(key, data) {
                    if (data.status == 0) {
                        bCtn += '<tr class="uncheck"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right"><a href="shouru-table.html?id=' + data.sid +
                            '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.sid +
                            '" onclick="del(this)">删除</a></td></tr>';
                    } else if (data.status == 1) {
                        bCtn += '<tr class="checked"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right"><a href="shouru-table.html?id=' + data.sid +
                            '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.sid +
                            '" onclick="del(this)">删除</a></td></tr>';
                    } else if (data.status == 2) {
                        bCtn += '<tr class="unpass"><td data-title="' + tableArr[0] + '">' + data.employeeName + '</td>' +
                            '<td data-title="' + tableArr[1] + '">' + data.projectMarketName + '</td>' +
                            '<td data-title="' + tableArr[2] + '" class="text-right">' + data.designContentTypeName + '</td>' +
                            '<td data-title="' + tableArr[3] + '" class="text-right">' + toFloat(data.salaryMoney) + '</td>' +
                            '<td data-title="' + tableArr[4] + '" class="text-right"><a href="shouru-table.html?id=' + data.sid +
                            '" onclick="edit(this)">编辑</a>|<a href="javascript:void(0)" data-id="' + data.sid +
                            '" onclick="del(this)">删除</a></td></tr>';
                        // |<a href="javascript:void(0)" data-id="' + data.sid + '" onclick="check(this)">审核</a>
                    }
                })
                $(tableBody).append(bCtn);
            } else {
                // alert('没有更多数据了');
                $('.nextpage').text('没有更多数据了').prop('disabled', true);
            }
        })
    })
}
showPageData()


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
                    $('#tSalary i').text(toFloat(this.sumSalaryMoney));
                    return false;
                } else {
                    $('#tSalary i').text('0.00');
                }
            })
        }
    }, 'json')
}