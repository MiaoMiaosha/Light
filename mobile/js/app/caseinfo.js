$(function() {
    $('.caseInfo_body').css('min-height', $('html').height() - 108);
})

//状态筛选
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
                type = getCookie('targetType');
            var status = $(this).attr('data-status');
            var inputUrl = ' ',
                statuslink = '';
            if (type == 'caselist') {
                statuslink = 'state=' + status;
            } else {
                statuslink = 'status=' + status;
            }
            if (typeof userId != 'undefined' && userId != '')
                inputUrl = '?userId=' + userId + '&' + statuslink;
            $.get(urlInfo() + '/apply/' + type + inputUrl, function(res) {
                if (res.data.length == 0) {
                    listCtn = '<p class="t_center">没有数据</p>';
                    $(listbox).html(listCtn);
                    $('.loadMore').addClass('d_none');
                } else {
                    $.each(res.data, function(key, data) {
                        $('.loadMore').removeClass('d_none');
                        if (type == 'caselist') {
                            if (data.state == 0) {
                                statebox = '<span class="push">已提交</span>';
                            } else if (data.state == 1) {
                                statebox = '<span class="apply-success">申请成功</span>'
                            } else if (data.state == 2) {
                                statebox = '<span class="apply-error">申请失败</span>'
                            }
                            listCtn += '<li class="clis" data-go="caseDc?caseId=' + data.caseId + '" data-id="' + data.caid + '" data-cid="' + data.caseId + '" data-userid="' + data.userId +
                                '"><div class="libox"><h3>' + data.caseName + statebox + '</h3><p>时间：' + getTime(data.createTime) +
                                '</p><p>面积：' + data.area + '</p></div></li>';
                        } else if (type == 'newslist') {
                            var typeName = '';
                            if (data.status == 0) {
                                statebox = '<span class="push">已提交</span>';
                            } else if (data.status == 1) {
                                statebox = '<span class="apply-success">申请成功</span>'
                            } else if (data.status == 2) {
                                statebox = '<span class="apply-error">申请失败</span>'
                            }

                            if (data.type == 1) {
                                typeName = '公司新闻';
                            } else {
                                typeName = '行业资讯';
                            }
                            listCtn += '<li class="clis" data-id="' + data.naid + '" data-nid="' + data.newsId + '" data-userid="' + data.userId +
                                '"><div class="libox"><h3>' + data.newsTitle + statebox + '</h3><p>类型：' + typeName + '</p><p>时间：' + getTime(data.createTime) +
                                '</p></div></li>';
                        } else if (type == 'stalllist') {
                            if (data.status == 0) {
                                statebox = '<span class="push">已提交</span>';
                            } else if (data.status == 1) {
                                statebox = '<span class="apply-success">申请成功</span>'
                            } else if (data.status == 2) {
                                statebox = '<span class="apply-error">申请失败</span>'
                            }
                            listCtn += '<li class="clis" data-id="' + data.said + '" data-mid="' + data.stallId + '" data-userid="' + data.userId +
                                '"><div class="libox"><h3>' + data.stallName + statebox + '</h3><p>市场名称：' + data.marketName + '</p><p>楼层：' + data.floorName + '</p>' +
                                '<p>联系人：' + data.contactName + '</p><p>联系电话：' + data.contactMobile +
                                '</p><p>时间：' + getTime(data.createTime) + '</p></div></li>';
                        } else if (type == 'marketlist') {
                            if (data.status == 0) {
                                statebox = '<span class="push">已提交</span>';
                            } else if (data.status == 1) {
                                statebox = '<span class="apply-success">申请成功</span>'
                            } else if (data.status == 2) {
                                statebox = '<span class="apply-error">申请失败</span>'
                            }
                            listCtn += '<li class="clis" data-go="marketDc?marketId=' + data.marketId + '" data-id="' + data.maid + '" data-mid="' + data.marketId + '" data-userid="' + data.userId +
                                '"><div class="libox"><h3>' + data.marketName + statebox + '</h3><p>联系人：' + data.contactName + '</p><p>联系电话：' + data.contactMobile +
                                '</p><p>时间：' + getTime(data.createTime) + '</p><div class="showstall"><button data-mid="' + data.marketId +
                                '" class="gs manager" type="button">管理摊位</button><button data-mid="' + data.marketId +
                                '" class="gs apply" type="button">管理申请</button></div></div></li>';
                        } else if (type == 'goodslist') {
                            if (data.status == 0) {
                                statebox = '<span class="push">已提交</span>';
                            } else if (data.status == 1) {
                                statebox = '<span class="apply-success">申请成功</span>'
                            } else if (data.status == 2) {
                                statebox = '<span class="apply-error">申请失败</span>'
                            }
                            listCtn += '<li class="clis" data-go="devicesDc?goodsId=' + data.goodsId + '" data-id="' + data.gaid + '" data-gid="' + data.goodsId + '" data-userid="' + data.userId +
                                '"><div class="libox"><h3>' + data.goodsName + statebox + '</h3><p>价格：' + data.goodsPrice + '</p><p>时间：' + getTime(data.createTime) +
                                '</p></div></li>';
                        }
                    });
                    $(listbox).html(listCtn);
                    showStalllist();
                    showStall();
                    goDetails();
                }
            })
        })
    })
}
stateChoose();

//默认显示案例发布列表
function getCaseList() {
    var listbox = $('#listbox ul'),
        statebox = '',
        listCtn = '';
    var userId = getCookie('wxUserId'),
        type = getCookie('targetType'),
        statuslink = '';
    if (type == 'caselist') {
        statuslink = 'state=0';
    } else {
        statuslink = 'status=0';
    }
    $.get(urlInfo() + '/apply/' + type + '?userId=' + userId + '&' + statuslink, function(res) {
        if (res.data.length == 0) {
            listCtn = '<p class="t_center">没有数据</p>';
            $(listbox).html(listCtn);
            $('.loadMore').addClass('d_none');
        } else {
            $.each(res.data, function(key, data) {
                $('.loadMore').removeClass('d_none');

                // if (data.state == 0) {
                //     statebox = '<span class="push">已提交</span>';
                // } else if (data.state == 1) {
                //     statebox = '<span class="apply-success">申请成功</span>'
                // } else if (data.state == 2) {
                //     statebox = '<span class="apply-error">申请失败</span>'
                // }
                // listCtn += '<li class="clis" data-id="' + data.caid + '" data-cid="' + data.caseId + '" data-userid="' + data.userId +
                //     '"><div class="libox"><h3>' + data.caseName + statebox + '</h3><p>时间：' + getTime(data.createTime) +
                //     '</p><p>面积：' + data.area + '</p></div></li>';
                if (type == 'caselist') {
                    if (data.state == 0) {
                        statebox = '<span class="push">已提交</span>';
                    } else if (data.state == 1) {
                        statebox = '<span class="apply-success">申请成功</span>'
                    } else if (data.state == 2) {
                        statebox = '<span class="apply-error">申请失败</span>'
                    }
                    listCtn += '<li class="clis" data-go="caseDc?caseId=' + data.caseId + '" data-id="' + data.caid + '" data-cid="' + data.caseId + '" data-userid="' + data.userId +
                        '"><div class="libox"><h3>' + data.caseName + statebox + '</h3><p>时间：' + getTime(data.createTime) +
                        '</p><p>面积：' + data.area + '</p></div></li>';
                } else if (type == 'newslist') {
                    if (data.status == 0) {
                        statebox = '<span class="push">已提交</span>';
                    } else if (data.status == 1) {
                        statebox = '<span class="apply-success">申请成功</span>'
                    } else if (data.status == 2) {
                        statebox = '<span class="apply-error">申请失败</span>'
                    }
                    var typeName = '';
                    if (data.type == 1) {
                        typeName = '公司新闻';
                    } else {
                        typeName = '行业资讯';
                    }
                    listCtn += '<li class="clis" data-id="' + data.naid + '" data-nid="' + data.newsId + '" data-userid="' + data.userId +
                        '"><div class="libox"><h3>' + data.newsTitle + statebox + '</h3><p>类型：' + typeName + '</p><p>时间：' + getTime(data.createTime) +
                        '</p></div></li>';
                } else if (type == 'stalllist') {
                    if (data.status == 0) {
                        statebox = '<span class="push">已提交</span>';
                    } else if (data.status == 1) {
                        statebox = '<span class="apply-success">申请成功</span>'
                    } else if (data.status == 2) {
                        statebox = '<span class="apply-error">申请失败</span>'
                    }
                    listCtn += '<li class="clis" data-id="' + data.said + '" data-mid="' + data.stallId + '" data-userid="' + data.userId +
                        '"><div class="libox"><h3>' + data.stallName + statebox + '</h3><p>市场名称：' + data.marketName + '</p><p>楼层：' + data.floorName + '</p>' +
                        '<p>联系人：' + data.contactName + '</p><p>联系电话：' + data.contactMobile +
                        '</p><p>时间：' + getTime(data.createTime) + '</p></div></li>';
                } else if (type == 'marketlist') {
                    if (data.status == 0) {
                        statebox = '<span class="push">已提交</span>';
                    } else if (data.status == 1) {
                        statebox = '<span class="apply-success">申请成功</span>'
                    } else if (data.status == 2) {
                        statebox = '<span class="apply-error">申请失败</span>'
                    }
                    listCtn += '<li class="clis" data-go="marketDc?marketId=' + data.marketId + '" data-id="' + data.maid + '" data-mid="' + data.marketId + '" data-userid="' + data.userId +
                        '"><div class="libox"><h3>' + data.marketName + statebox + '</h3><p>联系人：' + data.contactName + '</p><p>联系电话：' + data.contactMobile +
                        '</p><p>时间：' + getTime(data.createTime) + '</p><div class="showstall"><button data-mid="' + data.marketId +
                        '" class="gs manager" type="button">管理摊位</button><button data-mid="' + data.marketId +
                        '" class="gs apply" type="button">管理申请</button></div></div></li>';
                } else if (type == 'goodslist') {
                    if (data.status == 0) {
                        statebox = '<span class="push">已提交</span>';
                    } else if (data.status == 1) {
                        statebox = '<span class="apply-success">申请成功</span>'
                    } else if (data.status == 2) {
                        statebox = '<span class="apply-error">申请失败</span>'
                    }
                    listCtn += '<li class="clis" data-go="devicesDc?goodsId=' + data.goodsId + '" data-id="' + data.gaid + '" data-gid="' + data.goodsId + '" data-userid="' + data.userId +
                        '"><div class="libox"><h3>' + data.goodsName + statebox + '</h3><p>价格：' + data.goodsPrice + '</p><p>时间：' + getTime(data.createTime) +
                        '</p></div></li>';
                }
            });
            $(listbox).html(listCtn);
            showStalllist();
            showStall();
            goDetails();
        }
    });
}
getCaseList();


/**
 * 点击招商里 的 管理申请 跳转至 摊位列表
 */
function showStalllist() {

    var gbtn = $('#listbox .clis');
    $(gbtn).on('click', '.apply', function(e) {
        e.stopPropagation();
        var mid = $(this).attr('data-mid');
        setCookie('marketId', mid, 1);
        $('.main_body').load('stallinfo.html');
    })
}

/**
 * 点击招商 管理摊位
 */
function showStall() {
    var gbtn = $('#listbox .clis');
    $(gbtn).on('click', '.manager', function(e) {
        e.stopPropagation();
        var mid = $(this).attr('data-mid');
        setCookie('marketId', mid, 1);
        $('.main_body').load('stallmanager.html');
    })
}

/**
 * 去对应的详情
 */
function goDetails() {
    var item = $('#listbox .clis'),
        ctnBox = $('.main_body');
    $.each(item, function(key, data) {
        $(data).on('click', function() {
            if (typeof $(this).attr('data-go') != 'undefined') {
                var href = $(this).attr('data-go').split('?')[0],
                    Id = $(this).attr('data-go').split('=')[1];
                if (href == 'caseDc') {
                    setStr('caseId', Id, 1);
                } else if (href == 'devicesDc') {
                    setStr('goodsId', Id, 1);
                } else if (href == 'marketDc') {
                    setStr('marketId', Id, 1);
                }
                location.href = "../index.html#" + href;
                //需要保存一个值 用于其他页面使用
                // setStr('caseId', caseId, 1);
            }
        })
    })
}