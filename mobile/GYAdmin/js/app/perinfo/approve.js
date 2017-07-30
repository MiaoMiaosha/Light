$(function() {
    $('.caseInfo_body').css('min-height', $('html').height() - 108);
})

function approveRS(type, typeApplyId, status) {
    var appyId = '';
    var interfaceName = '',
        statuslink = '';
    if (type == 1) {
        appyId = 'caseApplyId';
        interfaceName = 'appcase';
        statuslink = 'status=' + status;
    } else if (type == 2) {
        appyId = 'newsApplyId';
        interfaceName = 'appnews';
        statuslink = 'status=' + status;
    } else if (type == 3) {
        appyId = 'stallApplyId';
        interfaceName = 'appstall';
        statuslink = 'status=' + status;

    } else if (type == 4) {
        appyId = 'marketApplyId';
        interfaceName = 'appmarket';
        statuslink = 'status=' + status;

    } else if (type == 5) {
        appyId = 'cooperateApplyId';
        interfaceName = 'appcooperate';
        statuslink = 'status=' + status;
    } else {
        appyId = 'goodsApplyId';
        interfaceName = 'appgoods';
        statuslink = 'status=' + status;
    }
    $.post(urlInfo() + '/admin/apply/' + interfaceName + '?' + appyId + '=' + typeApplyId + '&' + statuslink, function(res) {
        if (res.status.code == 200) {
            alert('审核成功');
            //stateChoose();
            $('.main_body').load('../perInfo/approve.html')
        } else
            alert(res.status.msg);
    });
}

/**
 * 设置案例星级
 */
function setLev(cid) {
    console.log(111);
    var dialogCtn = '<div class="dialog1 d_none"><div class="mask"></div>' +
        '<div class="dialogCtn trans"><div class="title"><p>设置星级</p></div>' +
        '<div class="body"><select class="setCaseLv" name="setCaseLv" id="setCaseLv">' +
        '<option value="5">五星级</option><option value="4">四星级</option>' +
        '<option value="3">三星级</option><option value="2">建筑综合</option>' +
        '<option value="1">装修中</option></select></div>' +
        '<div class="footer"><ul class="clearfix"><li class="f_left flis">' +
        '<button type="button" class="btnOk btnFooter">确认</button></li>' +
        '<li class="f_left flis"><button type="button" class="btnFooter cancel">取消</button></li></ul></div></div></div>';
    $('.dialog1').remove();
    $('body').append(dialogCtn);
    //取消
    $('.cancel').on('click', function() {
        $(this).parents('.dialog1').addClass('d_none');
    });
    $('.btnOk').on('click', function() {
        var _this = this;
        $.post(urlInfo() + '/admin/case/edit', {
            'cid': cid,
            'level': $('#setCaseLv').val()
        }, function(res) {
            if (res.status.code == 200) {
                $(_this).parents('.dialog1').addClass('d_none');
            } else {
                alert(res.status.msg);
            }
        })
    })
    dialogAnimation();
}


//状态筛选
function stateChoose() {
    var lis = $('.caseInfo_body .oplis');
    $.each(lis, function() {
        //申请要链接到前台的详情页
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
                    listCtn = '',
                    btnbox = '';

                var userId = getCookie('userId');
                var inputUrl = '?order=create_time desc';
                $.get(urlInfo() + '/apply/' + choose + inputUrl, function(res) {
                    //console.log(userId);
                    if (res.data == '') {
                        listCtn = '<p class="t_center">没有数据</p>'
                        $(listbox).html(listCtn);
                    } else {
                        $.each(res.data, function(key, data) {
                            if (choose == 'caselist') {
                                var editButton = '<div>' +
                                    +'<input type="button" class="tg" value="审核通过" onclick="approveRS(1,1)">' +
                                    '<input type="button" class="tg jj" value="审核失败" onclick="approveRS(1,2)">' +
                                    '</div>';
                                if (data.state == 0) {
                                    statebox = '<span class="push">已提交</span>';
                                    btnbox = '<input type="button"  class="tg" value="审核通过" onclick="approveRS(1,' + data.caid + ',1)">' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(1,' + data.caid + ',2)">' +
                                        '<input type="button" class="tg sl" value="设置星级" onclick="setLev(' + data.caseId + ')"/>';
                                } else if (data.state == 1) {
                                    statebox = '<span class="apply-success">申请成功</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(1,' + data.caid + ',1)" disabled>' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(1,' + data.caid + ',2)" disabled>' +
                                        '<input type="button" class="tg sl" value="设置星级" onclick="setLev(' + data.caseId + ')"/>';
                                } else if (data.state == 2) {
                                    statebox = '<span class="apply-error">申请失败</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(1,' + data.caid + ',1)" disabled>' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(1,' + data.caid + ',2)" disabled>' +
                                        '<input type="button" class="tg sl" value="设置星级" onclick="setLev(' + data.caseId + ')" disabled/>';
                                }
                                listCtn += '<li class="clis" data-id="' + data.caid + '" data-cid="' + data.caseId + '" data-userid="' + data.userId +
                                    '"><div class="libox"><h3>' + data.caseName + statebox + '</h3><p>时间：' + getTime(data.createTime) +
                                    '</p><p>面积：' + data.area + '</p></div>' +
                                    '<div>' + btnbox +
                                    // '<input type="button" value="审核通过" onclick="approveRS(1,' + data.caid + ',1)">' +
                                    // '<input type="button" value="审核拒绝" onclick="approveRS(1,' + data.caid + ',2)">' +
                                    '</div>' +
                                    '</li>';
                            } else if (choose == 'newslist') {
                                if (data.status == 0) {
                                    statebox = '<span class="push">已提交</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(2,' + data.naid + ',1)">' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(2,' + data.naid + ',2)">';
                                } else if (data.status == 1) {
                                    statebox = '<span class="apply-success">申请成功</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(2,' + data.naid + ',1)" disabled>' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(2,' + data.naid + ',2)" disabled>';
                                } else if (data.status == 2) {
                                    statebox = '<span class="apply-error">申请失败</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(2,' + data.naid + ',1)" disabled>' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(2,' + data.naid + ',2)" disabled>';
                                }
                                var typeName = '';
                                if (data.type == 1) {
                                    typeName = '公司新闻';
                                } else {
                                    typeName = '行业资讯';
                                }
                                listCtn += '<li class="clis" data-id="' + data.naid + '" data-nid="' + data.newsId + '" data-userid="' + data.userId +
                                    '"><div class="libox"><h3>' + data.newsTitle + statebox + '</h3><p>类型：' + typeName + '</p><p>时间：' + getTime(data.createTime) +
                                    '</p></div>' +
                                    '<div>' + btnbox +
                                    // '<input type="button" value="审核通过" onclick="approveRS(2,' + data.naid + ',1)">' +
                                    // '<input type="button" value="审核拒绝" onclick="approveRS(2,' + data.naid + ',2)">' +
                                    '</div>' +
                                    '</li>';
                            } else if (choose == 'stalllist') {
                                if (data.status == 0) {
                                    statebox = '<span class="push">已提交</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(3,' + data.said + ',1)" >' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(3,' + data.said + ',2)" >';
                                } else if (data.status == 1) {
                                    statebox = '<span class="apply-success">申请成功</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(3,' + data.said + ',1)" disabled>' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(3,' + data.said + ',2)" disabled>';
                                } else if (data.status == 2) {
                                    statebox = '<span class="apply-error">申请失败</span>';
                                    btnbox = '<input type="button"  class="tg" value="审核通过" onclick="approveRS(3,' + data.said + ',1)" disabled>' +
                                        '<input type="button"  class="tg jj" value="审核拒绝" onclick="approveRS(3,' + data.said + ',2)" disabled>';
                                }
                                listCtn += '<li class="clis" data-id="' + data.said + '" data-mid="' + data.stallId + '" data-userid="' + data.userId +
                                    '"><div class="libox"><h3>' + data.stallName + statebox + '</h3><p>市场名称：' + data.marketName + '</p><p>楼层：' + data.floorName + '</p>' +
                                    '<p>联系人：' + data.contactName + '</p><p>联系电话：' + data.contactMobile +
                                    '</p><p>时间：' + getTime(data.createTime) + '</p></div>' +
                                    '<div>' + btnbox +
                                    // '<input type="button" value="审核通过" onclick="approveRS(3,' + data.said + ',1)">' +
                                    // '<input type="button" value="审核拒绝" onclick="approveRS(3,' + data.said + ',2)">' +
                                    '</div>' +
                                    '</li>';
                            } else if (choose == 'marketlist') {
                                if (data.status == 0) {
                                    statebox = '<span class="push">已提交</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(4,' + data.maid + ',1)">' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(4,' + data.maid + ',2)" >';
                                } else if (data.status == 1) {
                                    statebox = '<span class="apply-success">申请成功</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(4,' + data.maid + ',1)" disabled>' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(4,' + data.maid + ',2)" disabled>';
                                } else if (data.status == 2) {
                                    statebox = '<span class="apply-error">申请失败</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(4,' + data.maid + ',1)" disabled>' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(4,' + data.maid + ',2)" disabled>';
                                }
                                listCtn += '<li class="clis" data-id="' + data.maid + '" data-mid="' + data.marketId + '" data-userid="' + data.userId +
                                    '"><div class="libox"><h3>' + data.marketName + statebox + '</h3><p>联系人：' + data.contactName + '</p><p>联系电话：' + data.contactMobile +
                                    '</p><p>时间：' + getTime(data.createTime) + '</p></div>' +
                                    '<div>' + btnbox +
                                    // '<input type="button" value="审核通过" onclick="approveRS(4,' + data.maid + ',1)">' +
                                    // '<input type="button" value="审核拒绝" onclick="approveRS(4,' + data.maid + ',2)">' +
                                    '</div>' +
                                    '</li>';
                            } else if (choose == 'cooperatelist') {
                                if (data.state == 0) {
                                    statebox = '<span class="push">已提交</span>';
                                    btnbox = '<input type="button"  class="tg" value="审核通过" onclick="approveRS(5,' + data.cooperateCompanyId + ',1)" >' +
                                        '<input type="button"  class="tg jj" value="审核拒绝" onclick="approveRS(5,' + data.cooperateCompanyId + ',2)" >';
                                } else if (data.state == 1) {
                                    statebox = '<span class="apply-success">申请成功</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(5,' + data.cooperateCompanyId + ',1)" disabled>' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(5,' + data.cooperateCompanyId + ',2)" disabled>';
                                } else if (data.state == 2) {
                                    statebox = '<span class="apply-error">申请失败</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(5,' + data.cooperateCompanyId + ',1)" disabled>' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(5,' + data.cooperateCompanyId + ',2)" disabled>';
                                }
                                listCtn += '<li class="clis" data-id="' + data.cid + '" data-cooperateCompanyId="' + data.cooperateCompanyId + '" data-userid="' + data.userId +
                                    '"><div class="libox"><h3>' + data.fullName + statebox + '</h3><p>时间：' + getTime(data.createTime) + '</p></div>' +
                                    '<div>' + btnbox +
                                    // '<input type="button" value="审核通过" onclick="approveRS(4,' + data.cooperateCompanyId + ',1)">' +
                                    // '<input type="button" value="审核拒绝" onclick="approveRS(4,' + data.cooperateCompanyId + ',2)">' +
                                    '</div>' +
                                    '</li>';
                            } else {
                                if (data.status == 0) {
                                    statebox = '<span class="push">已提交</span>';
                                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(6,' + data.goodsId + ',1)">' +
                                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(6,' + data.goodsId + ',2)">';
                                } else if (data.status == 1) {
                                    statebox = '<span class="apply-success">申请成功</span>';
                                    btnbox = '<input type="button"  class="tg" value="审核通过" onclick="approveRS(6,' + data.goodsId + ',1)" disabled>' +
                                        '<input type="button"  class="tg jj" value="审核拒绝" onclick="approveRS(6,' + data.goodsId + ',2)" disabled>';
                                } else if (data.status == 2) {
                                    statebox = '<span class="apply-error">申请失败</span>';
                                    btnbox = '<input type="button"  class="tg" value="审核通过" onclick="approveRS(6,' + data.goodsId + ',1)" disabled>' +
                                        '<input type="button"  class="tg jj" value="审核拒绝" onclick="approveRS(6,' + data.goodsId + ',2)" disabled>';
                                }
                                listCtn += '<li class="clis" data-id="' + data.gaid + '" data-goodsId="' + data.goodsId + '">' +
                                    '<div class="libox"><h3>' + data.goodsName + statebox + '</h3><p>时间：' + getTime(data.createTime) + '</p></div>' +
                                    '<div>' + btnbox +
                                    // '<input type="button" value="审核通过" onclick="approveRS(4,' + data.goodsId + ',1)">' +
                                    // '<input type="button" value="审核拒绝" onclick="approveRS(4,' + data.goodsId + ',2)">' +
                                    '</div>' +
                                    '</li>';
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
        listCtn = '',
        btnbox = '';
    var userId = getCookie('userId');
    $.get(urlInfo() + '/apply/caselist', function(res) {
        if (res.data == '') {
            listCtn = '<p class="t_center">没有数据</p>'
            $(listbox).html(listCtn);
        } else {
            $.each(res.data, function(key, data) {
                if (data.state == 0) {
                    statebox = '<span class="push">已提交</span>';
                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(1,' + data.caid + ',1)" >' +
                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(1,' + data.caid + ',2)" >' +
                        '<input type="button" class="tg sl" value="设置星级" onclick="setLev(' + data.caseId + ')"/>';
                } else if (data.state == 1) {
                    statebox = '<span class="apply-success">申请成功</span>';
                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(1,' + data.caid + ',1)" disabled>' +
                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(1,' + data.caid + ',2)" disabled>' +
                        '<input type="button" class="tg sl" value="设置星级" onclick="setLev(' + data.caseId + ')"/>';
                } else if (data.state == 2) {
                    statebox = '<span class="apply-error">申请失败</span>';
                    btnbox = '<input type="button" class="tg" value="审核通过" onclick="approveRS(1,' + data.caid + ',1)" disabled>' +
                        '<input type="button" class="tg jj" value="审核拒绝" onclick="approveRS(1,' + data.caid + ',2)" disabled>' +
                        '<input type="button" class="tg sl" value="设置星级" onclick="setLev(' + data.caseId + ')" disabled/>';
                }
                listCtn += '<li class="clis" data-id="' + data.caid + '" data-cid="' + data.caseId + '" data-userid="' + data.userId +
                    '"><div class="libox"><h3>' + data.caseName + statebox + '</h3><p>时间：' + getTime(data.createTime) +
                    '</p><p>面积：' + data.area + '</p></div>' +
                    '<div>' + btnbox +
                    // '<input type="button" value="审核通过" onclick="approveRS(1,' + data.caid + ',1)">' +
                    // '<input type="button" value="审核拒绝" onclick="approveRS(1,' + data.caid + ',2)">' +
                    '</div>' +
                    '</li>';
            });
            $(listbox).html(listCtn);
        }
    });
}
getCaseList();