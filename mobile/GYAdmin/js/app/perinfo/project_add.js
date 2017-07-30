$.ajaxSetup({
    async: false
})

function getEmployee() {
    //获取员工/合同人列表
    var eBox = $('#employee-box ul'),
        eCtn = '';
    $.get(urlInfo() + '/admin/employee/list', function(res) {
        $.each(res.data, function(key, data) {
            eCtn += '<li class="slis" data-id="' + data.eid + '">' + data.name + '</li>';
        })
        $(eBox).html(eCtn);
    }, 'json');
    //获取市场
    var marketbox = $('#customerId'),
        mBox = '';
    $.get(urlInfo() + '/admin/customer/list', function(res) {
        $.each(res.data, function(key, data) {
            mBox += '<option value="' + data.cid + '">' + data.companyName + '</option>'
        })
        $(marketbox).html(mBox);
    }, 'json');
}
getEmployee();

function getProvince(pid, cid) {
    var pBox = $('#provincebox'),
        pItem = '';
    $.ajaxSetup({
        async: false
    })
    $.get(urlInfo() + '/region/parent', {
        'parentId': 0
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (key == 0) {
                $(pBox).siblings('input').val(data.id);
                var provinceId = '';
                if (pid == '' || typeof pid == 'undefined') {
                    provinceId = data.id;
                } else {
                    provinceId = pid;
                }
                // var provinceId = data.id;
                //获取市级
                var cityBox = $('#citybox'),
                    cityCtn = '';
                $.get(urlInfo() + '/region/parent', {
                    'parentId': provinceId
                }, function(res1) {
                    $.each(res1.data, function(key1, data1) {
                        if (key1 == 0) {
                            $(cityBox).siblings('input').val(data1.id);
                            var cityId = '';
                            if (cid == '' || typeof cid == 'undefined') {
                                cityId = data1.id;
                            } else {
                                cityId = cid;
                            }
                            // var cityId = data1.id;
                            //获取县
                            var townBox = $('#districtbox'),
                                townCtn = '';
                            $.get(urlInfo() + '/region/parent', {
                                'parentId': cityId
                            }, function(res2) {
                                $.each(res2.data, function(key2, data2) {
                                    if (key2 == 0) {
                                        $(townBox).siblings('input').val(data2.id);
                                    }
                                    townCtn += '<option value="' + data2.id + '">' + data2.name + '</option>';
                                });
                                $(townBox).html(townCtn);
                            });
                        }
                        cityCtn += '<option value="' + data1.id + '">' + data1.name + '</option>';
                    });
                    $(cityBox).html(cityCtn);
                });
            }
            pItem += '<option value="' + data.id + '">' + data.name + '</option>';
        });
        $(pBox).html(pItem);
    });
}
getProvince();

function edit() {
    var id = window.location.href.split('?id=')[1];
    if (typeof id == 'undefined') {
        //嘿嘿 我就是啥也不做 你咋地
        $('.pstatus').addClass('d_none');
        $('.htgroup').removeClass('d_none');
        // $('.changeBtn').removeClass('d_none').siblings('.btn').addClass('d_none');
    } else {
        $('.pstatus').removeClass('d_none');
        $('.htgroup').addClass('d_none');
        // $('.changeBtn').addClass('d_none').siblings('.btn').removeClass('d_none');
        $.ajaxSetup({
            async: false
        });
        //哎 不得不做了
        $.get(urlInfo() + '/admin/project/list', {
            'pid': id
        }, function(res) {
            //市场名称不可编辑
            $('#createTime1').val(getTime(res.data[0].project.createTime)).prop('disabled', true);
            $('#createTime').val(res.data[0].project.createTime).prop('disabled', true);
            $('#firstFinishTime1').val(getTime(res.data[0].project.firstFinishTime)).prop('disabled', true);
            $('#firstFinishTime').val(res.data[0].project.firstFinishTime).prop('disabled', true);
            $('#secondFinishTime1').val(getTime(res.data[0].project.secondFinishTime)).prop('disabled', true);
            $('#secondFinishTime').val(res.data[0].project.secondFinishTime).prop('disabled', true);
            $('#thirdFinishTime1').val(getTime(res.data[0].project.thirdFinishTime)).prop('disabled', true);
            $('#thirdFinishTime').val(res.data[0].project.thirdFinishTime).prop('disabled', true);
            $('#fourFinishTime1').val(getTime(res.data[0].project.fourFinishTime)).prop('disabled', true);
            $('#fourFinishTime').val(res.data[0].project.fourFinishTime).prop('disabled', true);
            $('#fiveFinishTime1').val(getTime(res.data[0].project.fiveFinishTime)).prop('disabled', true);
            $('#fiveFinishTime').val(res.data[0].project.fiveFinishTime).prop('disabled', true);

            $('#province').val(res.data[0].project.province).prop('disabled', true);
            $('#city').val(res.data[0].project.city).prop('disabled', true);
            $('#district').val(res.data[0].project.district).prop('disabled', true);
            getProvince(res.data[0].project.province, res.data[0].project.city);
            $('#provincebox').val(res.data[0].project.province).prop('disabled', true);
            $('#citybox').val(res.data[0].project.city).prop('disabled', true);
            $('#districtbox').val(res.data[0].project.district).prop('disabled', true);

            $('#marketName').val(res.data[0].project.marketName).prop('disabled', true);
            $('#marketIntro').val(res.data[0].project.marketIntro).prop('disabled', true);
            $('#marketAddress').val(res.data[0].project.marketAddress).prop('disabled', true);
            $('#customerId').val(res.data[0].project.customerId).prop('disabled', true);
            $('#realContactMoney').val(toFloat(res.data[0].project.realContactMoney)).prop('disabled', true);
            $('#contactMoney').val(toFloat(res.data[0].project.contactMoney)).prop('disabled', true);
            $('#firstMoney').val(toFloat(res.data[0].project.firstMoney)).prop('disabled', true);
            $('#secondMoney').val(toFloat(res.data[0].project.secondMoney)).prop('disabled', true);
            $('#thirdMoney').val(toFloat(res.data[0].project.thirdMoney)).prop('disabled', true);
            $('#fourMoney').val(toFloat(res.data[0].project.fourMoney)).prop('disabled', true);
            $('#fiveMoney').val(toFloat(res.data[0].project.fiveMoney)).prop('disabled', true);
            $('#remark').val(res.data[0].project.remark).prop('disabled', true);
            $('#status').val(res.data[0].project.status).prop('disabled', true);
            $('#busTravelType').val(res.data[0].project.busTravelType).prop('disabled', true);
            $('#travelMoney').val(toFloat(res.data[0].project.travelMoney)).prop('disabled', true);
            $('#rebateMoney').val(toFloat(res.data[0].project.rebateMoney)).prop('disabled', true);
            $('#extraMoney').val(toFloat(res.data[0].project.extraMoney)).prop('disabled', true);
            $('#signingCompany').val(res.data[0].project.signingCompany).prop('disabled', true);
            $('#pid').val(res.data[0].project.pid).prop('disabled', true);

            //获取合同人
            if (res.data[0].list.length != 0) {
                var plist = $('#employeebox'),
                    pSpan = '';
                $.each(res.data[0].list, function(key1, data1) {
                    pSpan += '<span data-id="' + data1.employeeId + '">' + data1.employeeName + '<i class="icon-guanbifuzhi"></i></span>';
                })
                $(plist).html(pSpan);
            }
        })
    }
}
edit();