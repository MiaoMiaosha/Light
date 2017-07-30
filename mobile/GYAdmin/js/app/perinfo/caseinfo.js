$(function() {
    $('.caseInfo_body').css('min-height', $('html').height() - 108);
})

//状态筛选
function stateChoose() {
    var lis = $('.caseInfo_body .oplis');
    $.each(lis, function() {
        $(this).on('click', function() {
            if (typeof $(this).attr('data-list') != 'undefined') {
                var _slis = this;
                var list = $(_slis).attr('data-list').split(','),
                    litem = '';
                for (var i in list) {
                    if (i == 0) {
                        litem += '<li class="dlis">' + list[i] + '</li>';
                    } else {
                        litem += '<li class="dlis" data-tid="' + (i - 1) + '">' + list[i] + '</li>';
                    }
                }
                $('.dialog').remove();
                var dialogCtn = '<div class="dialog d_none"><div class="mask"></div>' +
                    '<div class="dialogCtn trans">' +
                    '<ul>' + litem +
                    '</ul></div></div>';
                $('body').append(dialogCtn);
                //点击选中
                var state = $('.dialog .dlis');
                $.each(state, function() {
                    $(this).on('click', function() {
                        $('.dialog').addClass('d_none');
                        var v = $(this).text();
                        var choose = $('.caseInfo_body').find('li.cur').attr('data-type'),
                            listbox = $('#listbox ul');
                        if (typeof $(this).attr('data-tid') != 'undefined') {
                            $(_slis).find('p').text(v).attr('data-type', $(this).attr('data-tid'));
                            // var listbox = $('#listbox ul'),
                            //     type = $(this).attr('data-tid'),
                            //     statebox = '',
                            //     listCtn = '';
                            // if (choose == 'caselist') {
                            //     $.get(urlInfo() + '/apply/' + choose, {
                            //         'state': type
                            //     }, function(res) {
                            //         if (res.data == '') {
                            //             listCtn = '<p class="t_center">没有数据</p>'
                            //         } else {
                            //             $.each(res.data, function(key, data) {
                            //                 if (data.state == 0) {
                            //                     statebox = '<span class="push">已提交</span>';
                            //                 } else if (data.state == 1) {
                            //                     statebox = '<span class="apply-success">申请成功</span>'
                            //                 } else if (data.state == 2) {
                            //                     statebox = '<span class="apply-error">申请失败</span>'
                            //                 }
                            //                 listCtn += '<li class="clis" data-id="' + data.caid + '" data-cid="' + data.caseId + '" data-userid="' + data.userId +
                            //                     '"><div class="libox"><h3>' + data.caseName + statebox + '</h3><p>时间：' + getTime(data.createTime) +
                            //                     '</p><p>面积：' + data.area + '</p></div></li>';
                            //             });
                            //             $(listbox).html(listCtn);
                            //         }
                            //     })
                            // } else {
                            //     var listbox = $('#listbox ul'),
                            //         type = $(this).attr('data-tid'),
                            //         statebox = '',
                            //         listCtn = '';
                            //     $.get(urlInfo() + '/apply/' + choose, {
                            //         'state': type
                            //     }, function(res) {
                            //         if (res.data == '') {
                            //             listCtn = '<p class="t_center">没有数据</p>'
                            //         } else {
                            //             $.each(res.data, function(key, data) {
                            //                 if (choose == 'newslist') {
                            //                     if (data.status == 0) {
                            //                         statebox = '<span class="push">已提交</span>';
                            //                     } else if (data.status == 1) {
                            //                         statebox = '<span class="apply-success">申请成功</span>'
                            //                     } else if (data.status == 2) {
                            //                         statebox = '<span class="apply-error">申请失败</span>'
                            //                     }
                            //                     listCtn += '<li class="clis" data-id="' + data.naid + '" data-nid="' + data.newsId + '" data-userid="' + data.userId +
                            //                         '"><div class="libox"><h3>' + data.newsTitle + statebox + '</h3><p>类型：' + data.type + '</p><p>时间：' + getTime(data.createTime) +
                            //                         '</p></div></li>';
                            //                 } else if (choose == 'stalllist') {
                            //                     if (data.status == 0) {
                            //                         statebox = '<span class="push">已提交</span>';
                            //                     } else if (data.status == 1) {
                            //                         statebox = '<span class="apply-success">申请成功</span>'
                            //                     } else if (data.status == 2) {
                            //                         statebox = '<span class="apply-error">申请失败</span>'
                            //                     }
                            //                     listCtn += '<li class="clis" data-id="' + data.said + '" data-mid="' + data.stallId + '" data-userid="' + data.userId +
                            //                         '"><div class="libox"><h3>' + data.stallName + statebox + '</h3><p>市场名称：' + data.marketName + '</p><p>楼层：' + data.floorName + '</p>' +
                            //                         '<p>联系人：' + data.contactName + '</p><p>联系电话：' + data.contactMobile +
                            //                         '</p><p>时间：' + getTime(data.createTime) + '</p></div></li>';
                            //                 } else if (choose == 'marketlist') {
                            //                     if (data.status == 0) {
                            //                         statebox = '<span class="push">已提交</span>';
                            //                     } else if (data.status == 1) {
                            //                         statebox = '<span class="apply-success">申请成功</span>'
                            //                     } else if (data.status == 2) {
                            //                         statebox = '<span class="apply-error">申请失败</span>'
                            //                     }
                            //                     listCtn += '<li class="clis" data-id="' + data.maid + '" data-mid="' + data.marketId + '" data-userid="' + data.userId +
                            //                         '"><div class="libox"><h3>' + data.marketName + statebox + '</h3><p>联系人：' + data.contactName + '</p><p>联系电话：' + data.contactMobile +
                            //                         '</p><p>时间：' + getTime(data.createTime) + '</p></div></li>';
                            //                 }
                            //             });
                            //             $(listbox).html(listCtn);
                            //         }
                            //     })
                            // }
                        } else {
                            $(_slis).find('p').text(v).removeAttr('data-type');
                        }
                    })
                });
                dialogAnimation();
            } else {
                $(this).addClass('cur').siblings('li').removeClass('cur');
                var choose = $(this).attr('data-type'),
                    listbox = $('#listbox ul'),
                    statebox = '',
                    listCtn = '';
                $.get(urlInfo() + '/apply/' + choose, function(res) {
                    if (res.data == '') {
                        listCtn = '<p class="t_center">没有数据</p>'
                    } else {
                        $.each(res.data, function(key, data) {
                            if (choose == 'caselist') {
                                if (data.state == 0) {
                                    statebox = '<span class="push">已提交</span>';
                                } else if (data.state == 1) {
                                    statebox = '<span class="apply-success">申请成功</span>'
                                } else if (data.state == 2) {
                                    statebox = '<span class="apply-error">申请失败</span>'
                                }
                                listCtn += '<li class="clis" data-id="' + data.caid + '" data-cid="' + data.caseId + '" data-userid="' + data.userId +
                                    '"><div class="libox"><h3>' + data.caseName + statebox + '</h3><p>时间：' + getTime(data.createTime) +
                                    '</p><p>面积：' + data.area + '</p></div></li>';
                            } else if (choose == 'newslist') {
                                if (data.status == 0) {
                                    statebox = '<span class="push">已提交</span>';
                                } else if (data.status == 1) {
                                    statebox = '<span class="apply-success">申请成功</span>'
                                } else if (data.status == 2) {
                                    statebox = '<span class="apply-error">申请失败</span>'
                                }
                                listCtn += '<li class="clis" data-id="' + data.naid + '" data-nid="' + data.newsId + '" data-userid="' + data.userId +
                                    '"><div class="libox"><h3>' + data.newsTitle + statebox + '</h3><p>类型：' + data.type + '</p><p>时间：' + getTime(data.createTime) +
                                    '</p></div></li>';
                            } else if (choose == 'stalllist') {
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
                            } else if (choose == 'marketlist') {
                                if (data.status == 0) {
                                    statebox = '<span class="push">已提交</span>';
                                } else if (data.status == 1) {
                                    statebox = '<span class="apply-success">申请成功</span>'
                                } else if (data.status == 2) {
                                    statebox = '<span class="apply-error">申请失败</span>'
                                }
                                listCtn += '<li class="clis" data-id="' + data.maid + '" data-mid="' + data.marketId + '" data-userid="' + data.userId +
                                    '"><div class="libox"><h3>' + data.marketName + statebox + '</h3><p>联系人：' + data.contactName + '</p><p>联系电话：' + data.contactMobile +
                                    '</p><p>时间：' + getTime(data.createTime) + '</p></div></li>';
                            }
                        });
                        $(listbox).html(listCtn);
                    }
                })
            }
        })
    })
}
stateChoose();

//默认显示案例发布列表
function getCaseList() {
    var listbox = $('#listbox ul'),
        statebox = '',
        listCtn = '';
    $.get(urlInfo() + '/apply/caselist', function(res) {
        if (res.data == '') {
            listCtn = '<p class="t_center">没有数据</p>'
        } else {
            $.each(res.data, function(key, data) {
                if (data.state == 0) {
                    statebox = '<span class="push">已提交</span>';
                } else if (data.state == 1) {
                    statebox = '<span class="apply-success">申请成功</span>'
                } else if (data.state == 2) {
                    statebox = '<span class="apply-error">申请失败</span>'
                }
                listCtn += '<li class="clis" data-id="' + data.caid + '" data-cid="' + data.caseId + '" data-userid="' + data.userId +
                    '"><div class="libox"><h3>' + data.caseName + statebox + '</h3><p>时间：' + getTime(data.createTime) +
                    '</p><p>面积：' + data.area + '</p></div></li>';
            });
            $(listbox).html(listCtn);
        }
    });
}
getCaseList();