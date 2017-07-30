$.ajaxSetup({
    async: false
});
//多选
function moreSelect() {
    var cBox = $('.devide-box'),
        sBox = $('.select-box');
    $(cBox).on('click', function() {
        if ($(this).attr('data-open') == 0) {
            $(this).attr('data-open', 1);
            $(this).siblings('.select-box').removeClass('d_none');
        } else {
            $(this).attr('data-open', 0);
            $(this).siblings('.select-box').addClass('d_none');
        }
    });
    $(sBox).on('click', '.slis', function() {
        var spanBox = '<span data-id="' + $(this).attr('data-id') + '">' + $(this).text() + '<i class="icon-guanbifuzhi"></i></span>';
        $(this).parents('.select-box').siblings('.devide-box').append(spanBox);
        $(this).parents('.select-box').addClass('d_none').siblings('.devide-box').attr('data-open', 0);
        $('#bonusNum').val($('#employeebox').find('span').length);
        $(this).remove();
        // getBounsLev();
    });
    $(cBox).on('click', '.icon-guanbifuzhi', function(e) {
        e.stopPropagation(); //阻止事件向上冒泡
        var sName = $(this).parent().text(),
            sId = $(this).parent().attr('data-id'),
            sli = '';
        sli = '<li class="slis" data-id="' + sId + '">' + sName + '</li>';
        $(this).parents('.devide-box').siblings('.select-box').find('ul').append(sli);
        $('#bonusNum').val($('#employeebox').find('span').length - 1);
        $(this).parent().remove();
        // getBounsLev();
    })
}
moreSelect();

//显示比例框
function showRateBox() {
    var iptBox = $('.dividendRadio'),
        rBox = $('.rateBox');
    $(iptBox).on({
        keyup: function() {
            $(this).siblings('.rateBox').attr('data-open', 0);
            $(this).siblings('.rateBox').addClass('d_none');
            //分红金额=个人业绩*分红比例
            $('#bonusMoney').val(tozheng(parseInt($('#personBusSalary').val()) * parseFloat(parseFloat($('#dividendRadio').val()) / 100)));
        },
        focus: function() {
            $(this).siblings('.rateBox').attr('data-open', 1);
            $(this).siblings('.rateBox').removeClass('d_none');
        },
        // blur: function(e) {
        //     $(this).siblings('.rateBox').attr('data-open', 0);
        //     $(this).siblings('.rateBox').addClass('d_none');
        //     console.log(e);
        // }
    });
    $(rBox).on('click', 'li', function(e) {
        e.stopPropagation(); //阻止事件向上冒泡
        $(this).parents('.rateBox').siblings('.dividendRadio').val($(this).text());
        $(this).parents('.rateBox').attr('data-open', 0);
        $(this).parents('.rateBox').addClass('d_none');
        //分红金额=个人业绩*分红比例
        $('#bonusMoney').val(tozheng(parseInt($('#personBusSalary').val()) * parseFloat(parseFloat($('#dividendRadio').val()) / 100)));
    })
}
showRateBox();

//获取额外信息
function getOtherInfo() {
    //获取分红类型
    var tBox = $('#typeId'),
        tCtn = '';
    // $.ajaxSetup({
    //     async: false
    // })
    $.get(urlInfo() + '/admin/bonus/extralist', function(res) {
        $.each(res.data, function(key, data) {
            tCtn += '<option value="' + data.id + '">' + data.bonusType + '</option>';
        })
        $(tBox).html(tCtn);
    }, 'json');
    //获取员工/合同人列表
    var eBox = $('#employee-box ul'),
        cBox = $('#contact-box ul'),
        eCtn = '';
    $.get(urlInfo() + '/admin/employee/list', function(res) {
        $.each(res.data, function(key, data) {
            eCtn += '<li class="slis" data-id="' + data.eid + '">' + data.name + '</li>';
        })
        $(eBox).html(eCtn);
        $(cBox).html(eCtn);
    }, 'json');
    //获取工程表
    var pBox = $('#projectId'),
        pCtn = '';
    $.get(urlInfo() + '/admin/project/list', function(res) {
        $.each(res.data, function(key, data) {
            pCtn += '<option value="' + data.project.pid + '">' + data.project.marketName + '</option>';
        })
        $(pBox).html(pCtn);
    }, 'json');
    //获取分红比例
    var rate_box = $('.rateBox ul'),
        rate_ctn = '';
    $.get(urlInfo() + '/admin/bonus/levellist', function(res) {
        $.each(res.data, function(key, data) {
            rate_ctn += '<li data-id="' + data.id + '">' + toFloat(data.bonusRate) + '</li>';
        })
        $(rate_box).html(rate_ctn);
        showRateBox();
    }, 'json');
}
getOtherInfo();

//计算相关数据
function calcDate() {
    var receiveMoney = $('#receiveMoney'),
        travelMoney = $('#travelMoney'),
        rebateMoney = $('#rebateMoney'),
        otherMoney = $('#otherMoney'),
        realMoney = $('#realMoney'),
        bonusNum = $('#bonusNum'),
        personBusSalary = $('#personBusSalary'),
        dividendRadio = $('#dividendRadio'),
        bonusMoney = $('#bonusMoney');
    //计算真实收入
    $('#receiveMoney,#travelMoney,#rebateMoney,#otherMoney').on('blur', function() {
        //真实收入=到账金额+差旅+回扣+其他
        $(realMoney).val(parseInt($(receiveMoney).val()) + parseInt($(travelMoney).val()) + parseInt($(rebateMoney).val()) + parseInt($(otherMoney).val()));
        //个人业绩收入 = 真实收入/分红人数
        $(personBusSalary).val(parseInt($(realMoney).val()) / parseInt($(bonusNum).val()));
    })
}
calcDate();
//获取分红级别
// function getBounsLev() {
//     var bounsId = '',
//         employee = $('#employeebox span');
//     for (var i = 0; i < employee.length; i++) {
//         bounsId += $(employee[i]).attr('data-id') + ":";
//     }
//     bounsId = bounsId.substring(0, bounsId.length - 1);
//     $.ajax({
//         url: urlInfo() + '/admin/bonus/level',
//         data: { 'bonusIds': bounsId },
//         type: 'POST',
//         dataType: 'json',
//         async: false,
//         success: function(res) {
//             if (res.status.code == 200) {
//                 $('#bonusLevel').val(res.data.levelName);
//             } else {
//                 alert('获取分红级别失败');
//             }
//         }
//     });
// }

//提交分红
function fenhong_add() {
    var roleName = getStr('showPage');
    if (roleName == 'yg') {
        $('.kjshow').addClass('d_none');
    } else if (roleName == 'kj') {
        $('.kjshow').removeClass('d_none');
    }
    $('#addTable').click(function() {
        if (roleName == 'yg') {
            var bounsId = '',
                employee = $('#employeebox span'),
                contactId = '',
                contact = $('#contactbox span');
            for (var i = 0; i < employee.length; i++) {
                bounsId += $(employee[i]).attr('data-id') + ":";
            }
            for (var i = 0; i < contact.length; i++) {
                contactId += $(contact[i]).attr('data-id') + ":";
            }
            bounsId = bounsId.substring(0, bounsId.length - 1);
            contactId = contactId.substring(0, contactId.length - 1);
            var bonusTime = isNaN(getUnix($('#bonusTime1').val())) ? '' : getUnix($('#bonusTime1').val()),
                bonusIds = bounsId,
                contactIds = contactId,
                marketId = $('#projectId').val(),
                marketName = $('#projectId').find('option:selected').text(),
                typeId = $('#typeId').val(),
                receiveMoney = toInt($('#receiveMoney').val()),
                travelMoney = toInt($('#travelMoney').val()),
                rebateMoney = toInt($('#rebateMoney').val()),
                otherMoney = toInt($('#otherMoney').val()),
                realMoney = toInt($('#realMoney').val()),
                bonusNum = $('#bonusNum').val(),
                personBusSalary = toInt($('#personBusSalary').val()),
                dividendRadio = $('#dividendRadio').val() * 100,
                // bonusLevel = $('#bonusLevel').val(),
                bonusMoney = toInt($('#bonusMoney').val()),
                remark = $('#remark').val();
            $.post(urlInfo() + '/admin/bonus/commit', {
                'bonusTime': bonusTime,
                'bonusUsers': bonusIds,
                'contractids': contactIds,
                'projectId': marketId,
                'marketName': marketName,
                'typeId': typeId,
                'receiveMoney': receiveMoney,
                'travelMoney': travelMoney,
                'rebateMoney': rebateMoney,
                'otherMoney': otherMoney,
                'realMoney': realMoney,
                'bonusNum': bonusNum,
                'personBusSalary': personBusSalary,
                'dividendRadio': dividendRadio,
                // 'bonusLevel': bonusLevel,
                'bonusMoney': bonusMoney,
                'remark': remark
            }, function(res) {
                var tRole = getStr('showPage');
                if (res.status.code == 200) {
                    alert('添加成功');
                    if (tRole == 'yg') {
                        location.href = 'perInfo/index.html';
                    } else {
                        window.location.href = "fenhong-list.html";
                    }
                } else {
                    alert(res.status.msg);
                }
            })
        } else if (roleName == 'kj') {
            var bounsId = '',
                employee = $('#employeebox span'),
                contactId = '',
                contact = $('#contactbox span');
            for (var i = 0; i < employee.length; i++) {
                bounsId += $(employee[i]).attr('data-id') + ":";
            }
            for (var i = 0; i < contact.length; i++) {
                contactId += $(contact[i]).attr('data-id') + ":";
            }
            bounsId = bounsId.substring(0, bounsId.length - 1);
            contactId = contactId.substring(0, contactId.length - 1);

            //分红计入通过时间
            var updateTime = '';
            if ($('#updateTime1').val() == '') {
                alert('请选择分红计入通过时间');
                return;
            } else {
                updateTime = getLastDay($('#updateTime1').val().split('-')[0], $('#updateTime1').val().split('-')[1]);
            }

            var bonusTime = isNaN(getUnix($('#bonusTime1').val())) ? '' : getUnix($('#bonusTime1').val()),
                bonusIds = bounsId,
                contactIds = contactId,
                marketId = $('#projectId').val(),
                marketName = $('#projectId').find('option:selected').text(),
                typeId = $('#typeId').val(),
                receiveMoney = toInt($('#receiveMoney').val()),
                travelMoney = toInt($('#travelMoney').val()),
                rebateMoney = toInt($('#rebateMoney').val()),
                otherMoney = toInt($('#otherMoney').val()),
                realMoney = toInt($('#realMoney').val()),
                bonusNum = $('#bonusNum').val(),
                personBusSalary = toInt($('#personBusSalary').val()),
                dividendRadio = $('#dividendRadio').val() * 100,
                // bonusLevel = $('#bonusLevel').val(),
                bonusMoney = toInt($('#bonusMoney').val()),
                remark = $('#remark').val();
            $.post(urlInfo() + '/admin/bonus/commit', {
                'updateTime': updateTime,
                'bonusTime': bonusTime,
                'bonusUsers': bonusIds,
                'contractids': contactIds,
                'projectId': marketId,
                'marketName': marketName,
                'typeId': typeId,
                'receiveMoney': receiveMoney,
                'travelMoney': travelMoney,
                'rebateMoney': rebateMoney,
                'otherMoney': otherMoney,
                'realMoney': realMoney,
                'bonusNum': bonusNum,
                'personBusSalary': personBusSalary,
                'dividendRadio': dividendRadio,
                // 'bonusLevel': bonusLevel,
                'bonusMoney': bonusMoney,
                'remark': remark
            }, function(res) {
                var tRole = getStr('showPage');
                if (res.status.code == 200) {
                    alert('添加成功');
                    if (tRole == 'yg') {
                        location.href = 'perInfo/index.html';
                    } else {
                        window.location.href = "fenhong-list.html";
                    }
                } else {
                    alert(res.status.msg);
                }
            })
        }

    });
}
fenhong_add()

//是否编辑
function edit() {
    var id = window.location.href.split('?id=')[1];
    if (typeof id == 'undefined') {
        //嘿嘿 我就是啥也不做 你咋地
        $('.changeBtn').removeClass('d_none').siblings('.btn').addClass('d_none');
    } else {
        $('.check-box').removeClass('d_none');
        // $('.kjshow').removeClass('d_none');
        var a = new Date();
        var y = a.getFullYear();
        var m = a.getMonth() < 10 ? '0' + (a.getMonth() + 1) : a.getMonth() + 1;
        var c = y + '-' + m;
        $('#updateTime').val(c);
        $('.changeBtn').addClass('d_none').siblings('.btn').removeClass('d_none');
        //哎 不得不做了
        $.get(urlInfo() + '/admin/bonus/list', {
            'bid': id
        }, function(res) {

            if (res.data[0].updateTime == null || res.data[0].updateTime == '' || typeof res.data[0].updateTime == 'undefined') {
                $('#updateTime1').val('');
                $('#updateTime').val('');
            } else {
                $('#updateTime1').val(formatDate(res.data[0].updateTime));
                $('#updateTime').val(res.data[0].updateTime);
            }

            $('#bonusTime1').val(getTime(res.data[0].bonus.bonusTime));
            $('#bonusTime').val(res.data[0].bonus.bonusTime);
            $('#bonusUsers').val(res.data[0].bonus.bonusIds);
            $('#contractids').val(res.data[0].bonus.contactIds);
            $('#projectId').val(res.data[0].bonus.projectId);
            $('#receiveMoney').val(toFloat(res.data[0].bonus.receiveMoney));
            $('#travelMoney').val(toFloat(res.data[0].bonus.travelMoney));
            $('#rebateMoney').val(toFloat(res.data[0].bonus.rebateMoney));
            $('#otherMoney').val(toFloat(res.data[0].bonus.otherMoney));
            $('#realMoney').val(toFloat(res.data[0].bonus.realMoney));
            $('#personBusSalary').val(toFloat(res.data[0].bonus.personBusSalary));
            $('#bonusMoney').val(toFloat(res.data[0].bonus.bonusMoney));
            $('#bonusNum').val(res.data[0].bonus.bonusNum);
            $('#dividendRadio').val(toFloat(res.data[0].bonus.dividendRadio));
            // $('#bonusLevel').val(res.data[0].bonus.bonusLevel);
            $('#remark').val(res.data[0].bonus.remark);
            $('#status').val(res.data[0].bonus.status);
            $('#bid').val(res.data[0].bonus.bid);
            //获取分红人
            if (res.data[0].bonusIds.length != 0) {
                var elist = $('#employeebox'),
                    eSpan = '';
                $.each(res.data[0].bonusIds, function(key1, data1) {
                    eSpan += '<span data-id="' + data1.employeeId + '">' + data1.employeeName + '<i class="icon-guanbifuzhi"></i></span>';
                })
                $(elist).html(eSpan);
            }
            //获取合同人
            if (res.data[0].contractIds.length != 0) {
                var clist = $('#contactbox'),
                    cSpan = '';
                $.each(res.data[0].contractIds, function(key1, data1) {
                    cSpan += '<span data-id="' + data1.employeeId + '">' + data1.employeeName + '<i class="icon-guanbifuzhi"></i></span>';
                })
                $(clist).html(cSpan);
            }
        })
    }
}
edit();
//提交修改数据
function edit_Fenhong() {
    $('#editTable').on('click', function() {
        var bTime = isNaN(getUnix($('#bonusTime1').val())) ? '' : getUnix($('#bonusTime1').val());
        $('#bonusTime').val(bTime);

        $('#receiveMoney').val(toInt($('#receiveMoney').val()));
        $('#travelMoney').val(toInt($('#travelMoney').val()));
        $('#rebateMoney').val(toInt($('#rebateMoney').val()));
        $('#otherMoney').val(toInt($('#otherMoney').val()));
        $('#realMoney').val(toInt($('#realMoney').val()));
        $('#personBusSalary').val(toInt($('#personBusSalary').val()));
        $('#bonusMoney').val(toInt($('#bonusMoney').val()));
        $('#dividendRadio').val($('#bonusMoney').val() * 100);


        var finishTime = '';
        if ($('#updateTime1').val() == '') {
            finishTime = '';
            alert('请选择计入时间');
        } else {
            finishTime = getLastDay($('#updateTime1').val().split('-')[0], $('#updateTime1').val().split('-')[1]);
        }
        $('#updateTime').val(finishTime);

        $('#marketName').val($('#projectId').find('option:selected').text());
        var bounsId = '',
            employee = $('#employeebox span'),
            contactId = '',
            contact = $('#contactbox span');
        for (var i = 0; i < employee.length; i++) {
            bounsId += $(employee[i]).attr('data-id') + ":";
        }
        for (var i = 0; i < contact.length; i++) {
            contactId += $(contact[i]).attr('data-id') + ":";
        }
        bounsId = bounsId.substring(0, bounsId.length - 1);
        contactId = contactId.substring(0, contactId.length - 1);
        $('#bonusUsers').val(bounsId);
        $('#contractids').val(contactId);
        var formDate = $('#fenhongForm').serialize();
        $.post(urlInfo() + '/admin/bonus/edit', formDate, function(res) {
            if (res.status.code == 200) {
                alert('修改成功');
                window.location.href = "fenhong-list.html";
            } else {
                alert('修改失败');
                location.reload();
            }
        }, 'json');
    })
}
edit_Fenhong();