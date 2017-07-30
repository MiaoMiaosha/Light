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
    })
}
moreSelect();

//菜市场申请--省
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
//省市联动
function getCity(event) {
    var selectValue = $(event).find('option:selected').val(),
        getValue = $(event).siblings('input'),
        cityBox = $('#citybox'),
        cityCtn = '';
    // $('#cityBox').removeClass('d_none');
    $(getValue).val(selectValue);
    $.ajaxSetup({
        async: false
    })
    $.get(urlInfo() + '/region/parent', {
        'parentId': selectValue
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (key == 0) {
                $(cityBox).siblings('input').val(data.id);
                var cityId = data.id;
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
            cityCtn += '<option value="' + data.id + '">' + data.name + '</option>';
        });
        $(cityBox).html(cityCtn);
    });
}
//市县联动
function getTown(event) {
    var selectValue = $(event).find('option:selected').val(),
        getValue = $(event).siblings('input'),
        townBox = $('#districtbox'),
        townCtn = '';
    // $('#townBox').removeClass('d_none');
    $(getValue).val(selectValue);
    $.get(urlInfo() + '/region/parent', {
        'parentId': selectValue
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (key == 0) {
                $(townBox).siblings('input').val(data.id);
            }
            townCtn += '<option value="' + data.id + '">' + data.name + '</option>';
        });
        $(townBox).html(townCtn);
    });
}
//获取县的选中值
function getXian(event) {
    var selectValue = $(event).find('option:selected').val(),
        getValue = $(event).siblings('input');
    $(getValue).val(selectValue);
}

//获取合同人
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

//上传图片
function addPic() {
    var addBtn = $('#addPic'),
        hideIpt = $('#fileup');
    $(addBtn).on('click', function() {
        $(hideIpt).trigger('click');
    })
}
addPic();
//上传即时显示图片
function getImg(event) {
    var file = event.files[0],
        reader = new FileReader(),
        picGroup = $('#picGroup ul'),
        tokenVal = getToken(),
        randomKey = getRandomKey();
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        //七牛云跨域问题
        var pic64 = this.result.split(',')[1], //暂时可以分割逗号得到
            xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4) {
                // var upJson = JSON.parse(xhr.responseText);
                var upJson = eval('(' + xhr.responseText + ')');;
                var li = '<li class="f_left plis"><img data-key="' + upJson.data.key + '" src="' + imgUrl() + upJson.data.key + '" alt="">' +
                    '<p data-delkey="' + upJson.data.key + '" class="del">删除</p></li>';
                $(picGroup).append(li);
                // delPic();
                $('#fileup').val('');
            }
        };
        xhr.upload.onprogress = function(evt) {
            if (evt.lengthComputable) {
                var percentComplete = Math.round(evt.loaded * 100 / evt.total);
                if (percentComplete != 100) {
                    $('#addPic').prop('disabled', true).text('正在上传中..');
                    $('#addTable').prop('disabled', true);
                } else {
                    $('#addPic').prop('disabled', false).text('上传图片');
                    $('#addTable').prop('disabled', false);
                }
            }
        }
        xhr.open("POST", getImgServer(file.size), true);
        xhr.setRequestHeader("Content-Type", "application/octet-stream");
        xhr.setRequestHeader("Authorization", "UpToken " + tokenVal);
        xhr.send(pic64);
    };
}
//添加工程
function projectAdd() {
    $('#addTable').on('click', function() {
        if ($('#marketAddress').val() == '') {
            alert('市场地址不能为空');
        } else if ($('#city').val() == '') {
            alert('请选择市区');
        } else if ($('#district').val() == '') {
            alert('请选择县区');
        } else if ($('#createTime1').val() == '') {
            alert('请选择日期时间');
        } else if ($('#employeebox').find('span').length == 0) {
            alert('请选择合同人');
        } else if ($('#marketIntro').val() == '') {
            alert('请输入市场介绍');
        } else if ($('#customerId').val() == '') {
            alert('单位名称不能为空');
        } else if ($('#contactMoney').val() == '') {
            alert('合同价不能为空');
        } else if ($('#picGroup li img').length == 0) {
            alert('请上传合同图片');
        } else {
            var customerId = $('#contractIds'),
                employee = $('#employeebox span'),
                customerIds = '';
            for (var i = 0; i < employee.length; i++) {
                customerIds += $(employee[i]).attr('data-id') + ":";
            }
            customerIds = customerIds.substring(0, customerIds.length - 1);
            $(customerId).val(customerIds); //存储选择的合同人id
            //获得所有的img key
            var imglis = $('#picGroup li img'),
                imgUrls = '';
            $.each(imglis, function() {
                imgUrls += $(this).attr('data-key') + '#';
            });
            imgUrls = imgUrls.substring(0, imgUrls.length - 1);
            $('#contractUrls').val(imgUrls);
            //时间转时间戳处理
            var cTime = isNaN(getUnix($('#createTime1').val())) ? '' : getUnix($('#createTime1').val()),
                oneTime = isNaN(getUnix($('#firstFinishTime1').val())) ? '' : getUnix($('#firstFinishTime1').val()),
                twoTime = isNaN(getUnix($('#secondFinishTime1').val())) ? '' : getUnix($('#secondFinishTime1').val()),
                threeTime = isNaN(getUnix($('#thirdFinishTime1').val())) ? '' : getUnix($('#thirdFinishTime1').val()),
                fourTime = isNaN(getUnix($('#fourFinishTime1').val())) ? '' : getUnix($('#fourFinishTime1').val()),
                fiveTime = isNaN(getUnix($('#fiveFinishTime1').val())) ? '' : getUnix($('#fiveFinishTime1').val());
            $('#createTime').val(cTime);
            $('#firstFinishTime').val(oneTime);
            $('#secondFinishTime').val(twoTime);
            $('#thirdFinishTime').val(threeTime);
            $('#fourFinishTime').val(fourTime);
            $('#fiveFinishTime').val(fiveTime);

            $('#contactMoney').val(toInt($('#contactMoney').val()));
            $('#firstMoney').val(toInt($('#firstMoney').val()));
            $('#secondMoney').val(toInt($('#secondMoney').val()));
            $('#thirdMoney').val(toInt($('#thirdMoney').val()));
            $('#fourMoney').val(toInt($('#fourMoney').val()));
            $('#fiveMoney').val(toInt($('#fiveMoney').val()));

            $('#travelMoney').val(toInt($('#travelMoney').val()));
            $('#rebateMoney').val(toInt($('#rebateMoney').val()));
            $('#extraMoney').val(toInt($('#extraMoney').val()));

            // var realContactMoney = parseInt($('#contactMoney').val()) + parseInt($('#firstMoney').val()) + parseInt($('#secondMoney').val()) +
            //     parseInt($('#thirdMoney').val()) + parseInt($('#fourMoney').val()) + parseInt($('#fiveMoney').val()) + parseInt($('#travelMoney').val()) +
            //     parseInt($('#rebateMoney').val()) + parseInt($('#extraMoney').val());
            // $('#realContactMoney').val(realContactMoney);
            var formDate = $('#projectForm').serialize();
            $.post(urlInfo() + '/admin/project/commit', formDate, function(res) {
                var tRole = getStr('showPage');
                if (res.status.code == 200) {
                    alert('添加成功');
                    if (tRole == 'yg') {
                        location.href = 'perInfo/index.html';
                    } else {
                        window.location.href = "project-list.html";
                    }
                } else {
                    alert(res.status.msg);
                }
            }, 'json');
        }
    })
}
projectAdd();


/**
 * 计算
 */
function calcMoney() {
    $('#fiveMoney,#firstMoney,#secondMoney,#thirdMoney,#fourMoney').on('blur', function() {
        var contactMoney = parseInt($('#firstMoney').val()) + parseInt($('#secondMoney').val()) +
            parseInt($('#thirdMoney').val()) + parseInt($('#fourMoney').val()) + parseInt($('#fiveMoney').val());
        var realContactMoney = contactMoney + parseInt($('#travelMoney').val()) +
            parseInt($('#rebateMoney').val()) + parseInt($('#extraMoney').val());
        $('#contactMoney').val(contactMoney);
        $('#realContactMoney').val(realContactMoney);
    })
    $('#extraMoney,#rebateMoney,#extraMoney').on('blur', function() {
        var realContactMoney = parseInt($('#firstMoney').val()) + parseInt($('#secondMoney').val()) +
            parseInt($('#thirdMoney').val()) + parseInt($('#fourMoney').val()) + parseInt($('#fiveMoney').val()) + parseInt($('#travelMoney').val()) +
            parseInt($('#rebateMoney').val()) + parseInt($('#extraMoney').val());
        $('#realContactMoney').val(realContactMoney);
    })
}
calcMoney();


//是否编辑
function edit() {
    var id = window.location.href.split('?id=')[1];
    if (typeof id == 'undefined') {
        //嘿嘿 我就是啥也不做 你咋地
        $('.pstatus').addClass('d_none');
        $('.htgroup').removeClass('d_none');
        $('.changeBtn').removeClass('d_none').siblings('.btn').addClass('d_none');
    } else {
        $('.pstatus').removeClass('d_none');
        $('.htgroup').addClass('d_none');
        $('.changeBtn').addClass('d_none').siblings('.btn').removeClass('d_none');
        $.ajaxSetup({
            async: false
        });
        //哎 不得不做了
        $.get(urlInfo() + '/admin/project/list', {
            'pid': id
        }, function(res) {
            //市场名称不可编辑
            $('#createTime1').val(getTime(res.data[0].project.createTime));
            $('#createTime').val(res.data[0].project.createTime);
            $('#firstFinishTime1').val(getTime(res.data[0].project.firstFinishTime));
            $('#firstFinishTime').val(res.data[0].project.firstFinishTime);
            $('#secondFinishTime1').val(getTime(res.data[0].project.secondFinishTime));
            $('#secondFinishTime').val(res.data[0].project.secondFinishTime);
            $('#thirdFinishTime1').val(getTime(res.data[0].project.thirdFinishTime));
            $('#thirdFinishTime').val(res.data[0].project.thirdFinishTime);
            $('#fourFinishTime1').val(getTime(res.data[0].project.fourFinishTime));
            $('#fourFinishTime').val(res.data[0].project.fourFinishTime);
            $('#fiveFinishTime1').val(getTime(res.data[0].project.fiveFinishTime));
            $('#fiveFinishTime').val(res.data[0].project.fiveFinishTime);

            $('#province').val(res.data[0].project.province);
            $('#city').val(res.data[0].project.city);
            $('#district').val(res.data[0].project.district);
            getProvince(res.data[0].project.province, res.data[0].project.city);
            $('#provincebox').val(res.data[0].project.province);
            $('#citybox').val(res.data[0].project.city);
            $('#districtbox').val(res.data[0].project.district);

            $('#marketName').val(res.data[0].project.marketName);
            $('#marketIntro').val(res.data[0].project.marketIntro);
            $('#marketAddress').val(res.data[0].project.marketAddress);
            $('#customerId').val(res.data[0].project.customerId);
            $('#realContactMoney').val(toFloat(res.data[0].project.realContactMoney));
            $('#contactMoney').val(toFloat(res.data[0].project.contactMoney));
            $('#firstMoney').val(toFloat(res.data[0].project.firstMoney));
            $('#secondMoney').val(toFloat(res.data[0].project.secondMoney));
            $('#thirdMoney').val(toFloat(res.data[0].project.thirdMoney));
            $('#fourMoney').val(toFloat(res.data[0].project.fourMoney));
            $('#fiveMoney').val(toFloat(res.data[0].project.fiveMoney));
            $('#remark').val(res.data[0].project.remark);
            $('#status').val(res.data[0].project.status);
            $('#busTravelType').val(res.data[0].project.busTravelType);
            $('#travelMoney').val(toFloat(res.data[0].project.travelMoney));
            $('#rebateMoney').val(toFloat(res.data[0].project.rebateMoney));
            $('#extraMoney').val(toFloat(res.data[0].project.extraMoney));
            $('#signingCompany').val(res.data[0].project.signingCompany);
            $('#pid').val(res.data[0].project.pid);

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
//提交修改数据
function edit_Project() {
    $('#editTable').on('click', function() {
        //时间转时间戳处理
        var cTime = isNaN(getUnix($('#createTime1').val())) ? '' : getUnix($('#createTime1').val()),
            oneTime = isNaN(getUnix($('#firstFinishTime1').val())) ? '' : getUnix($('#firstFinishTime1').val()),
            twoTime = isNaN(getUnix($('#secondFinishTime1').val())) ? '' : getUnix($('#secondFinishTime1').val()),
            threeTime = isNaN(getUnix($('#thirdFinishTime1').val())) ? '' : getUnix($('#thirdFinishTime1').val()),
            fourTime = isNaN(getUnix($('#fourFinishTime1').val())) ? '' : getUnix($('#fourFinishTime1').val()),
            fiveTime = isNaN(getUnix($('#fiveFinishTime1').val())) ? '' : getUnix($('#fiveFinishTime1').val());
        $('#createTime').val(cTime);
        $('#firstFinishTime').val(oneTime);
        $('#secondFinishTime').val(twoTime);
        $('#thirdFinishTime').val(threeTime);
        $('#fourFinishTime').val(fourTime);
        $('#fiveFinishTime').val(fiveTime);

        $('#contactMoney').val(toInt($('#contactMoney').val()));
        $('#firstMoney').val(toInt($('#firstMoney').val()));
        $('#secondMoney').val(toInt($('#secondMoney').val()));
        $('#thirdMoney').val(toInt($('#thirdMoney').val()));
        $('#fourMoney').val(toInt($('#fourMoney').val()));
        $('#fiveMoney').val(toInt($('#fiveMoney').val()));

        $('#travelMoney').val(toInt($('#travelMoney').val()));
        $('#rebateMoney').val(toInt($('#rebateMoney').val()));
        $('#extraMoney').val(toInt($('#extraMoney').val()));
        $('#realContactMoney').val(toInt($('#realContactMoney').val()));

        // var realContactMoney = parseInt($('#contactMoney').val()) + parseInt($('#firstMoney').val()) + parseInt($('#secondMoney').val()) +
        //     parseInt($('#thirdMoney').val()) + parseInt($('#fourMoney').val()) + parseInt($('#fiveMoney').val()) - parseInt($('#travelMoney').val()) -
        //     parseInt($('#rebateMoney').val()) - parseInt($('#extraMoney').val());
        // $('#realContactMoney').val(realContactMoney);
        var formDate = $('#projectForm').serialize();
        $.post(urlInfo() + '/admin/project/edit', formDate, function(res) {
            if (res.status.code == 200) {
                alert('修改成功');
                window.location.href = "project-list.html";
            } else {
                alert('修改失败');
                location.reload();
            }
        }, 'json');
    })
}
edit_Project();

/**
 * 删除图片
 */
function delPic() {
    var delBtn = $('.picGroup');
    $(delBtn).on('click', '.del', function() {
        var _this = this;
        var key = $(_this).attr('data-delkey');
        $.post(urlInfo() + '/upload/delete', {
            'key': key
        }, function(res) {
            if (res.status.code == 200) {
                $(_this).parents('.plis').remove();
            } else {
                alert(res.status.msg);
            }
        })
    })
}
delPic();