$(function() {
    // calcHeight();
    perPageCur();
    if (getStr('showPage') != null) {
        var sp = getStr('showPage');
        $('.' + sp).removeClass('d_none');
        if (sp == 'yg') {
            $('#mytw').attr('data-title', '我的解答').find('p').text('我的解答');
            $('.com_money').parents('.s_oplist').removeClass('d_none');
            getMoneyInfo();
        }
        if (sp == 'kh') {
            $('#mytw').attr('data-title', '我的提问').find('p').text('我的提问');
        }
    }
    var href = window.location.href.split('?');
    if (getStr('openId') == null && href.length > 1) {
        var openbox = href[1].split('&'),
            openid = openbox[0].split('openId=')[1],
            err = openbox[1].split('err=')[1];
        // if (err != '')
        //     alert(err);
        setStr('openId', openid);
    }
});

function calcHeight() {
    $('.perInfo_body').css('min-height', $('html').height() - 48);
}

/**
 * 获取用户信息
 */
function getUserInfo() {
    var uid = getStr('userid'),
        uInfoBox = $('#sInfo'),
        uBox = '',
        roleName = '',
        type = getStr('showPage'),
        typeId = '',
        openId = getStr('openId'),
        roleId = getStr('roleId'),
        job = getStr('job');
    if (type == 'yg') typeId = 1;
    else if (type == 'kh') typeId = 2;
    if (type == 'admin') {
        uBox += '<img src="../../imgs/user1.jpg" alt="' + getStr('nickname') + '" title="' + getStr('nickname') + '" />';
        uBox += '<p class="name">' + getStr('nickname') + '</p><p class="lv">角色信息：<span>管理员</span></p>';
        uInfoBox.html(uBox);
    } else {
        $.get(urlInfo() + '/admin/login/userdetail', {
            'type': typeId,
            'openId': openId
        }, function(res) {
            if (res.status.code != 200) {
                alert(res.status.msg);
                location.reload();
                removeAllCookie();
                removeAllStr();
            } else {
                switch (roleId) {
                    case "1":
                        roleName = '管理员';
                        break;
                    case "2":
                        roleName = '会计';
                        break;
                    case "3":
                        if (job == 1) {
                            roleName = '设计师';
                        } else {
                            roleName = '合同人';
                        }
                        break;
                    case "4":
                        roleName = '客户';
                        break;
                }
                uBox += '<img src="' + res.data.headimgurl + '" alt="' + res.data.nickname + '" title="' + res.data.nickname + '" />';
                uBox += '<p class="name">' + res.data.nickname + '</p><p class="lv">角色信息：<span>' + roleName + '</span></p>';
                uInfoBox.html(uBox);
            }
        }, 'json');
    }
}
getUserInfo();

/**
 * 根据员工的分角色--2合同人/1设计师 修改对应的 我要发布内容
 */
function showPublishContent() {
    var roleType = getStr('job'),
        rCtn = $('#rType');
    if (roleType == 1) {
        rCtn.attr('data-list', '员工自身档案,工资,报销');
        rCtn.attr('data-arr', 'staff-table,shouru-table,baoxiao-table');
        $('.sjs').addClass('d_none');
    } else {
        rCtn.attr('data-list', '员工自身档案,提交客户档案,提交市场项目,报销,分红');
        rCtn.attr('data-arr', 'staff-table,customer-table,project-table,baoxiao-table,fenhong-table');
        $('.htr').addClass('d_none');
    }
}
showPublishContent();

/**
 * 根据员工角色取分红信息
 */
function getMoneyInfo() {
    var roleType = getStr('showPage');
    if (roleType == 'yg') {
        var eid = getStr('employeeId'),
            lbox = $('.lmoney'),
            lCtn = '',
            rbox = $('.rmoney'),
            rCtn = '';
        $.get(urlInfo() + '/admin/total/staffinfo', {
            'employeeId': eid
        }, function(res) {
            if (res.data.totalSalary.length == 1) {
                if (res.data.totalSalary[0].status == 0) {
                    if (res.data.job == 1) {
                        if (res.data.totalSalary[0].sumSalaryMoney != null) {
                            lCtn += '<p>总收入</p><span>' + toFloat(res.data.totalSalary[0].sumSalaryMoney) + '</span>' +
                                '<p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>' +
                                toFloat(res.data.totalSalary[0].sumSalaryMoney) + '</span>';
                        } else {
                            lCtn += '<p>总收入</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                        }
                    } else {
                        if (res.data.totalSalary[0].bonusMoney != null) {
                            lCtn += '<p>总分红</p><span>' + toFloat(res.data.totalSalary[0].bonusMoney) + '</span>' +
                                '<p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>' +
                                toFloat(res.data.totalSalary[0].bonusMoney) + '</span>';
                        } else {
                            lCtn += '<p>总分红</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                        }
                    }
                } else if (res.data.totalSalary[0].status == 1) {
                    if (res.data.job == 1) {
                        if (res.data.totalSalary[0].sumSalaryMoney != null) {
                            lCtn = '<p>总收入</p><span>' + toFloat(res.data.totalSalary[0].sumSalaryMoney) + '</span>' +
                                '<p class="ys">已审:</p><span>' + toFloat(res.data.totalSalary[0].sumSalaryMoney) + '</span><p class="ws">未审:</p><span>0</span>';
                        } else {
                            lCtn = '<p>总收入</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                        }
                    } else {
                        if (res.data.totalSalary[0].bonusMoney != null) {
                            lCtn = '<p>总分红</p><span>' + toFloat(res.data.totalSalary[0].bonusMoney) + '</span>' +
                                '<p class="ys">已审:</p><span>' + toFloat(res.data.totalSalary[0].bonusMoney) + '</span><p class="ws">未审:</p><span>0</span>';
                        } else {
                            lCtn = '<p>总分红</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                        }
                    }
                } else {
                    lCtn = '<p>总收入</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                }
                lbox.html(lCtn);
            } else if (res.data.totalSalary.length > 1) {
                var totalSalary = 0,
                    isPassSalary = 0,
                    unPassSalary = 0;
                if (res.data.job == 1) {
                    //总收入
                    $.each(res.data.totalSalary, function(i) {
                        if (this.status == 0) {
                            totalSalary += parseInt(this.sumSalaryMoney);
                            unPassSalary = parseInt(this.sumSalaryMoney);
                        } else if (this.status == 1) {
                            totalSalary += parseInt(this.sumSalaryMoney);
                            isPassSalary = parseInt(this.sumSalaryMoney);
                        }
                    });

                    lCtn = '<p>总收入</p><span>' + toFloat(totalSalary) + '</span>' +
                        '<p class="ys">已审:</p><span>' + toFloat(isPassSalary) + '</span><p class="ws">未审:</p><span>' +
                        toFloat(unPassSalary) + '</span>';
                } else {
                    $.each(res.data.totalSalary, function(i) {
                        if (this.status == 0) {
                            totalSalary += parseInt(this.bonusMoney);
                            unPassSalary = parseInt(this.bonusMoney);
                        } else if (this.status == 1) {
                            totalSalary += parseInt(this.bonusMoney);
                            isPassSalary = parseInt(this.bonusMoney);
                        }
                    })
                    lCtn = '<p>总分红</p><span>' + toFloat(totalSalary) + '</span>' +
                        '<p class="ys">已审:</p><span>' + toFloat(isPassSalary) + '</span><p class="ws">未审:</p><span>' +
                        toFloat(unPassSalary) + '</span>';
                }
                lbox.html(lCtn);
            } else {
                if (res.data.job == 1) {
                    lCtn = '<p>总收入</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                } else {
                    lCtn = '<p>总分红</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                }
            }
            if (res.data.monthSalary.length == 1) {
                if (res.data.monthSalary[0].status == 0) {
                    if (res.data.job == 1) {
                        if (res.data.monthSalary[0].sumSalaryMoney != null) {
                            rCtn = '<p>当月收入</p><span>' + toFloat(res.data.monthSalary[0].sumSalaryMoney) + '</span>' +
                                '<p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>' +
                                toFloat(res.data.monthSalary[0].sumSalaryMoney) + '</span>';
                        } else {
                            rCtn = '<p>当月收入</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                        }
                    } else {
                        if (res.data.monthSalary[0].bonusMoney != null) {
                            rCtn = '<p>当月分红</p><span>' + toFloat(res.data.monthSalary[0].bonusMoney) + '</span>' +
                                '<p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>' +
                                toFloat(res.data.monthSalary[0].bonusMoney) + '</span>';
                        } else {
                            rCtn = '<p>当月分红</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                        }
                    }
                } else if (res.data.monthSalary[0].status == 1) {
                    if (res.data.job == 1) {
                        if (res.data.monthSalary[0].sumSalaryMoney != null) {
                            rCtn = '<p>当月收入</p><span>' + toFloat(res.data.monthSalary[0].sumSalaryMoney) + '</span>' +
                                '<p class="ys">已审:</p><span>' + toFloat(res.data.monthSalary[0].sumSalaryMoney) + '</span><p class="ws">未审:</p><span>0</span>';
                        } else {
                            rCtn = '<p>当月收入</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                        }
                    } else {
                        if (res.data.monthSalary[0].bonusMoney != null) {
                            rCtn = '<p>当月分红</p><span>' + toFloat(res.data.monthSalary[0].bonusMoney) + '</span>' +
                                '<p class="ys">已审:</p><span>' + toFloat(res.data.monthSalary[0].bonusMoney) + '</span><p class="ws">未审:</p><span>0</span>';
                        } else {
                            rCtn = '<p>当月分红</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                        }
                    }
                }
                rbox.html(rCtn);
            } else if (res.data.monthSalary.length == 0) {
                if (res.data.job == 1) {
                    rCtn = '<p>当月收入</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                } else {
                    rCtn = '<p>当月分红</p><span>0</span><p class="ys">已审:</p><span>0</span><p class="ws">未审:</p><span>0</span>';
                }
                rbox.html(rCtn);
            }

            //合同人需要显示个人业绩
            if (res.data.job == 1) {
                $('.htryj').addClass('d_none');
            } else if (res.data.job == 2) {
                var htryj = '<p>个人业绩：<span style="color:#000">' + toFloat(res.data.totalPersonSalary) + '</span>元</p>';
                $('.htryj').removeClass('d_none').html(htryj);
            }
        }, 'json');
    }

}
getMoneyInfo();