/**
 * 获取摊位列表
 */
function getStallList() {
    var listbox = $('#listbox ul'),
        statebox = '',
        listCtn = '';
    var userId = getCookie('wxUserId'),
        marketId = getCookie('marketId');
    $.get(urlInfo() + '/apply/stalllist', {
        'marketId': marketId,
        'userId': userId
    }, function(res) {
        if (res.data.length == 0) {
            listCtn = '<p class="t_center">没有数据</p>';
            $(listbox).html(listCtn);
            $('.loadMore').addClass('d_none');
        } else {
            $.each(res.data, function(i) {
                if (this.status == 0) {
                    statebox = '<span class="push">已提交</span>';
                } else if (this.status == 1) {
                    statebox = '<span class="apply-success">申请成功</span>';
                } else if (this.status == 2) {
                    statebox = '<span class="apply-error">申请失败</span>';
                }
                listCtn += '<li class="clis" data-id="' + this.said + '" data-sid="' + this.stallId + '" data-userid="' + this.userId +
                    '"><div class="libox"><h3>' + this.stallName + statebox + '</h3><p>市场名称：' + this.marketName + '</p><p>时间：' + getTime(this.createTime) +
                    '</p></div></li>';
            })
            $(listbox).html(listCtn);
        }

    }, 'json')
}
getStallList();

/**
 * 状态筛选
 */
function stateChoose() {
    var lis = $('.caseInfo_body .oplis');
    $.each(lis, function() {
        $(this).on('click', function() {
            $(this).addClass('cur').siblings('li').removeClass('cur');
            // var choose = $(this).attr('data-type'),
            var listbox = $('#listbox ul'),
                statebox = '',
                listCtn = '';

            var userId = getCookie('wxUserId'),
                marketId = getCookie('marketId');
            var status = $(this).attr('data-status');
            $.get(urlInfo() + '/apply/stalllist', {
                'marketId': marketId,
                'userId': userId,
                'status': status
            }, function(res) {
                if (res.data.length == 0) {
                    listCtn = '<p class="t_center">没有数据</p>';
                    $(listbox).html(listCtn);
                    $('.loadMore').addClass('d_none');
                } else {
                    $('.loadMore').removeClass('d_none');
                    $.each(res.data, function(i) {
                        if (this.status == 0) {
                            statebox = '<span class="push">已提交</span>';
                        } else if (this.status == 1) {
                            statebox = '<span class="apply-success">申请成功</span>';
                        } else if (this.status == 2) {
                            statebox = '<span class="apply-error">申请失败</span>';
                        }
                        listCtn += '<li class="clis" data-id="' + this.said + '" data-sid="' + this.stallId + '" data-userid="' + this.userId +
                            '"><div class="libox"><h3>' + this.stallName + statebox + '</h3><p>市场名称：' + this.marketName + '</p><p>时间：' + getTime(this.createTime) +
                            '</p></div></li>';
                    })
                    $(listbox).html(listCtn);
                }
            }, 'json')
        })
    })
}
stateChoose();