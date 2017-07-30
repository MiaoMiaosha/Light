$(function() {
    $('.caseInfo_body').css('min-height', $('html').height() - 108);
})

function approveRS(type, typeApplyId) {
    // else {
    //     appyId = 'cooperateApplyId';
    //     interfaceName = 'appcooperate';
    //     statuslink = 'status=' + status;
    // }
    $.post(urlInfo() + '/admin/config/settop?type=' + type + '&id=' + typeApplyId, function(res) {
        if (res.status.code == 200) {
            alert('置顶成功');
            //stateChoose();
            $('.main_body').load('../perInfo/settop.html')
        } else
            alert(res.status.msg);
    });
}

//状态筛选
function stateChoose() {
    var lis = $('.caseInfo_body .oplis');
    $.each(lis, function() {
        //申请要链接到前台的详情页
        $(this).on('click', function() {
            $(this).addClass('cur').siblings('li').removeClass('cur');
            var choose = $(this).attr('data-type'),
                listbox = $('#listbox ul'),
                statebox = '',
                listCtn = '';

            var userId = getCookie('userId');
            var inputUrl = '';
            if (choose == 'case') {
                inputUrl = '/list?state=1';
            } else {
                inputUrl = '/list?status=1'
            }
            $.get(urlInfo() + '/' + choose + inputUrl, function(res) {
                //console.log(userId);
                if (res.data == '') {
                    listCtn = '<p class="t_center">没有数据</p>'
                    $(listbox).html(listCtn);
                } else {
                    $.each(res.data, function(key, data) {
                        if (choose == 'case') {
                            listCtn += '<li class="clis" data-id="' + data.cid + '" data-userid="' + data.userId +
                                '"><div class="libox"><h3>' + data.caseName + '</h3><p>时间：' + getTime(data.createTime) +
                                '</p><p>面积：' + data.area + '</p></div>' +
                                '<div>' +
                                '<input type="button" class="settop" value="置顶" onclick="approveRS(1,' + data.cid + ')">' +
                                // '<input type="button" value="审核拒绝" onclick="approveRS(1,' + data.caid + ',2)">' +
                                '</div>' +
                                '</li>';
                        } else if (choose == 'market') {
                            listCtn += '<li class="clis" data-id="' + data.mid + '" data-userid="' + data.userId +
                                '"><div class="libox"><h3>' + data.marketName + '</h3><p>市场公司：' + data.marketCompany + '</p>' +
                                '<p>市场介绍：' + data.marketIntro + '</p><p>市场负责人：' + data.contactName + '</p>' +
                                '<p>负责人电话：' + data.contactMobile + '</p><p>时间：' + getTime(data.createTime) +
                                '</p></div>' +
                                '<div>' +
                                '<input type="button" class="settop" value="置顶" onclick="approveRS(2,' + data.mid + ')">' +
                                // '<input type="button" value="审核拒绝" onclick="approveRS(2,' + data.naid + ',2)">' +
                                '</div>' +
                                '</li>';
                        } else if (choose == 'goods') {
                            listCtn += '<li class="clis" data-id="' + data.goodsId + '" data-userid="' + data.userId +
                                '"><div class="libox"><h3>' + data.goodsName + '</h3><p>商品描述：' + data.description + '</p><p>商品价格：' + toFloat(data.goodsPrice) + '</p>' +
                                '<p>地址：' + data.address + '</p><p>联系电话：' + data.mobile +
                                '</p></div>' +
                                '<div>' +
                                '<input type="button" class="settop" value="置顶" onclick="approveRS(3,' + data.goodsId + ')">' +
                                // '<input type="button" value="审核拒绝" onclick="approveRS(3,' + data.said + ',2)">' +
                                '</div>' +
                                '</li>';
                        } else if (choose == 'news') {
                            var typeName = '';
                            if (data.type == 1) {
                                typeName = '公司新闻';
                            } else {
                                typeName = '行业资讯';
                            }
                            listCtn += '<li class="clis" data-id="' + data.nid + '" data-userid="' + data.userId +
                                '"><div class="libox"><h3>' + data.newsTitle + '</h3><p>发布人：' + data.userName + '</p><p>类型：' + typeName + '</p>' +
                                '</div>' +
                                '<div>' +
                                '<input type="button" class="settop" value="置顶" onclick="approveRS(4,' + data.nid + ')">' +
                                // '<input type="button" value="审核拒绝" onclick="approveRS(3,' + data.said + ',2)">' +
                                '</div>' +
                                '</li>';
                        }
                    });
                    $(listbox).html(listCtn);
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
    var userId = getStr('userid');
    $.get(urlInfo() + '/case/list?state=1', function(res) {
        if (res.data == '') {
            listCtn = '<p class="t_center">没有数据</p>'
            $(listbox).html(listCtn);
        } else {
            $.each(res.data, function(key, data) {
                listCtn += '<li class="clis" data-id="' + data.cid + '" data-userid="' + data.userId +
                    '"><div class="libox"><h3>' + data.caseName + '</h3><p>时间：' + getTime(data.createTime) +
                    '</p><p>面积：' + data.area + '</p></div>' +
                    '<div>' +
                    '<input type="button" class="settop" value="置顶" onclick="approveRS(1,' + data.cid + ')">' +
                    // '<input type="button" value="审核拒绝" onclick="approveRS(1,' + data.caid + ',2)">' +
                    '</div>' +
                    '</li>';
            });
            $(listbox).html(listCtn);
        }
    });
}
getCaseList();