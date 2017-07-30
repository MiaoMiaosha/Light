$(function() {
    $('.com_body').css('min-height', $('html').height() - 48);
});
//切换类型
function getTypeCtn() {
    var lis = $('.ucType .uclis');
    $.each(lis, function() {
        $(this).on('click', function() {
            $(this).addClass('cur').siblings('li').removeClass('cur');
        })
    })
}
getTypeCtn();

/**
 * 获取项目列表
 */
function getProjectList() {
    var ctnBox = $('#projectList'),
        ctn = "";
    var employeeId = getStr('userid'),
        type = getStr('showPage');
    if (type == 'yg') {
        $.get(urlInfo() + '/admin/project/eplist', {
            'userId': employeeId
        }, function(res) {
            $.each(res.data, function(key, data) {
                ctn += '<li class="uitem"><div class="uiBox"><h3>' + data.marketName + '</h3>' +
                    '<p>实际合同价：' + toFloat(data.realContactMoney) + '</p><p>提交时间：' + getTime(data.createTime) + '</p><div class="opbox">' +
                    '<a href="javascript:void(0)" class="d" data-href="pushbbs#' + data.pid + '#' + data.processId + '">查看详情</a>' +
                    '<a href="javascript:void(0)" data-href="promanager#' + data.pid + '#' + data.processId + '" class="p">修改进度</a></div></div>';
            });
            $(ctnBox).html(ctn);
            seeProDetail();
        }, 'json');
    } else if (type == 'kh') {
        var customerId = getStr('customerId');
        $.get(urlInfo() + '/admin/project/list', {
            'customerId': customerId
        }, function(res) {
            $.each(res.data, function(key, data) {
                ctn += '<li class="uitem"><div class="uiBox"><h3>' + data.project.marketName + '</h3>' +
                    '<p>实际合同价：' + toFloat(data.project.realContactMoney) + '</p><p>提交时间：' + getTime(data.project.createTime) + '</p><div class="opbox">' +
                    '<a href="javascript:void(0)" class="d" data-href="pushbbs#' + data.project.pid + '#' + data.project.processId + '">查看详情</a>' +
                    '</div></div>';
                // <a href="javascript:void(0)" data-href="promanager#' + data.pid + '" class="p">修改进度</a>
            });
            $(ctnBox).html(ctn);
            seeProDetail();
        }, 'json');
    }
}
getProjectList();

/**
 * 查看项目详情(pushbbs)/修改进度(promanager)
 */
function seeProDetail() {
    var plis = $('#projectList .uitem');
    $(plis).on('click', 'a', function() {
        var target = $(this).attr('data-href').split('#');
        var loadTarget = target[0],
            loadId = target[1],
            processId = target[2];
        var historyArr = '';
        if (getStr('historyArr') != null) {
            historyArr = getStr('historyArr');
            historyArr = JSON.parse(historyArr);
            var abc = { 'hpage': 'usercontract' };
            historyArr.push(abc);
            historyArr = JSON.stringify(historyArr);
            setStr('historyArr', historyArr);
        } else {
            historyArr = ['perinfo'];
            setStr('historyArr', historyArr);
        }
        setStr('projectId', loadId);
        setStr('processId', processId);
        $('.main_body').load('../perInfo/' + loadTarget + '.html');
    })
}