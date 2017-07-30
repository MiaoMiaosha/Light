/**
 * 根据员工角色取分红信息
 */
function getMoneyInfo() {
    $('#showsytime').on('click', function() {
        var eid = getStr('employeeId'),
            lbox = $('.lmoney'),
            lCtn = '',
            rbox = $('.rmoney'),
            rCtn = '',
            startTime = getUnix($('#startTime').val()),
            endTime = getUnix($('#endTime').val());
        if (isNaN(startTime) || isNaN(endTime)) {
            alert('请选择时间');
        } else {
            $.get(urlInfo() + '/admin/total/staffinfo', {
                'employeeId': eid,
                'startTime': startTime,
                'endTime': endTime
            }, function(res) {
                if (res.data.monthSalary.length == 1) {
                    if (res.data.monthSalary[0].status == 0) {
                        if (res.data.job == 1) {
                            if (res.data.monthSalary[0].sumSalaryMoney != null) {
                                rCtn = '<p>当前时间段总和</p><span>' + toFloat(res.data.monthSalary[0].sumSalaryMoney) + '</span>' +
                                    '<p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>' +
                                    toFloat(res.data.monthSalary[0].sumSalaryMoney) + '</span>';
                            } else {
                                rCtn = '<p>当前时间段总和</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                            }
                        } else {
                            if (res.data.monthSalary[0].bonusMoney != null) {
                                rCtn = '<p>当前时间段总和</p><span>' + toFloat(res.data.monthSalary[0].bonusMoney) + '</span>' +
                                    '<p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>' +
                                    toFloat(res.data.monthSalary[0].bonusMoney) + '</span>';
                            } else {
                                rCtn = '<p>当前时间段总和</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                            }
                        }
                    } else if (res.data.monthSalary[0].status == 1) {
                        if (res.data.job == 1) {
                            if (res.data.monthSalary[0].sumSalaryMoney != null) {
                                rCtn = '<p>当前时间段总和</p><span>' + toFloat(res.data.monthSalary[0].sumSalaryMoney) + '</span>' +
                                    '<p class="ys">已审:</p><span>' + toFloat(res.data.monthSalary[0].sumSalaryMoney) + '</span><p class="ws">未审:</p><span>0</span>';
                            } else {
                                rCtn = '<p>当前时间段总和</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                            }
                        } else {
                            if (res.data.monthSalary[0].bonusMoney != null) {
                                rCtn = '<p>当前时间段总和</p><span>' + toFloat(res.data.monthSalary[0].bonusMoney) + '</span>' +
                                    '<p class="ys">已审:</p><span>' + toFloat(res.data.monthSalary[0].bonusMoney) + '</span><p class="ws">未审:</p><span>0</span>';
                            } else {
                                rCtn = '<p>当前时间段总和</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                            }
                        }
                    }
                    rbox.html(rCtn);
                } else if (res.data.monthSalary.length == 0) {
                    if (res.data.job == 1) {
                        rCtn = '<p>当前时间段总和</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                    } else {
                        rCtn = '<p>当前时间段总和</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                    }
                    rbox.html(rCtn);
                }
            }, 'json');
        }
    })
}
getMoneyInfo();