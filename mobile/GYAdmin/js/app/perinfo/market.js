$(function() {
    tabCur();
});

//点击市场名称输入框 弹窗显示列表
function showMarketList() {
    var showBtn = $('#selectMarket'),
        dialogBox = '';
    $(showBtn).on('click', function() {
        //<li class="f_left mlis">市场a</li>
        dialogBox = '<div class="dialog d_none"><div class="mask"></div>' +
            '<div class="dialogCtn trans"><div class="title"><p>选择市场名称</p></div>' +
            '<div class="body"><div class="searchbox"><input type="search" class="ipts" name="search" id="search"><button type="button" class="btnsearch" id="goSearch">搜索</button></div> ' +
            '<div class="mlist" id="mlist"><ul class="clearfix"></ul></div></div>' +
            '<div class="footer diafooter d_none"><ul class="clearfix"><li class="f_left flis">' +
            '<button type="button" class="prev btnFooter" disabled="true">上一页</button></li>' +
            '<li class="f_left flis"><button type="button" class="btnFooter next">下一页</button></li></ul></div></div></div>';
        $('.dialog').remove();
        $('body').append(dialogBox);
        //搜索后出现市场条目
        $('#goSearch').on('click', function() {
            var _this = this,
                libox = $('#mlist ul'),
                lictn = '';
            $.post(urlInfo() + '/market/list', {
                'marketName': $(_this).siblings('.ipts').val()
            }, function(res) {
                if (res.data != '') {
                    $.each(res.data, function(key, data) {
                        lictn += '<li class="f_left mlis" data-id="' + data.mid + '" data-userid="' + data.userId + '" data-sheng="' + data.provinceName +
                            '" data-area="' + data.area + '" data-contactName="' + data.contactName + '" data-mobile="' + data.contactMobile + '" data-shi="' +
                            data.cityName + '" data-xian="' + data.districtName + '" data-addr="' + data.townName + data.address + '">' + data.marketName + '</li>';
                    });
                    $(libox).html(lictn);
                    //显示分页
                    $('.diafooter').removeClass('d_none');
                    //点击市场条目 将数据加载至表格
                    showMarketInfo();
                } else {
                    $(libox).html('没有相关的市场信息');
                    $('.diafooter').addClass('d_none');
                }
            })
        });
        dialogAnimation();
    })
}
showMarketList();

//点击市场条目 将数据加载至表格
function showMarketInfo() {
    var lis = $('.dialog .mlis'),
        floorBox = $('#mtfloor');
    $.each(lis, function() {
        var _this = this;
        $(_this).on('click', function() {
            var mid = $(this).attr('data-id'),
                userid = $(this).attr('data-userid'),
                pName = $(this).attr('data-sheng'),
                cName = $(this).attr('data-shi'),
                tName = $(this).attr('data-xian'),
                addrName = $(this).attr('data-addr'),
                area = $(this).attr('data-area'),
                // userName = $(this).attr('data-contactName'),
                // userPhone = $(this).attr('data-mobile'),
                mName = $(this).text(),
                floorNum = '';
            $('#mid').val(mid);
            $('#userid').val(userid);
            $('#selectMarket').val(mName);
            $('#mtsheng').val(pName);
            $('#mtshi').val(cName);
            $('#mtxian').val(tName);
            $('#mtaddr').val(addrName);
            $('#mtarea').val(area);
            // $('#mtcName').val(userName);
            // $('#mtphone').val(userPhone);
            //移除弹框
            $(_this).parents('.dialog').addClass('d_none');
            //这里要根据选择的市场去请求 拥有的楼层数
            $.get(urlInfo() + '/floor/market', {
                'marketId': mid
            }, function(res) {
                $.each(res.data, function(key, data) {
                    if (key == 0) {
                        $(floorBox).siblings('input').val(data.fid);
                        var stallBox = $('#tanwei ul'),
                            stallCtn = '';
                        $.get(urlInfo() + '/stall/list', {
                            'floorId': data.fid
                        }, function(res) {
                            $.each(res.data, function(key, data) {
                                $(stallBox).html('');
                                if (key == 0) {
                                    stallCtn += '<li class="f_left tlis cur" data-id="' + data.sid + '"><i class="icon-select"></i>' + data.stallName + '</li>';
                                } else {
                                    stallCtn += '<li class="f_left tlis" data-id="' + data.sid + '"><i class="icon-select"></i>' + data.stallName + '</li>';
                                }
                            });
                            $(stallBox).html(stallCtn);
                            tabCur();
                        })
                    }
                    floorNum += '<option value="' + data.fid + '">' + data.floorName + '</option>';
                });
                $(floorBox).html(floorNum);
            })
        })
    })
}

//根据楼层变化 显示对应楼层的摊位号
function getStallNum(event) {
    var selectValue = $(event).find('option:selected').val(),
        getValue = $(event).siblings('input'),
        stallBox = $('#tanwei ul'),
        stallCtn = '';
    $(getValue).val(selectValue);
    $.get(urlInfo() + '/stall/list', {
        'floorId': selectValue
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (key == 0) {
                stallCtn += '<li class="f_left tlis cur" data-id="' + data.sid + '"><i class="icon-select"></i>' + data.stallName + '</li>';
            } else {
                stallCtn += '<li class="f_left tlis" data-id="' + data.sid + '"><i class="icon-select"></i>' + data.stallName + '</li>';
            }
        });
        $(stallBox).html(stallCtn);
        tabCur();
    }, 'json')
}

//提交摊位申请
function stallApply() {
    var applyBtn = $('#stallApply');
    $(applyBtn).on('click', function() {
        var userid = $('#userid').val(),
            marketId = $('#mid').val(),
            // floorId = $('#floorId').val(),
            sid = $('#tanwei .tlis.cur').attr('data-id'),
            contactName = $('#mtcName').val(),
            contactMobile = $('#mtphone').val();
        $.post(urlInfo() + '/stall/apply', {
            'userId': userid,
            'marketId': marketId,
            'sid': sid,
            // 'floorId': floorId,
            'contactName': contactName,
            'contactMobile': contactMobile
        }, function(res) {
            if (res.status.code != '200') {
                alert('申请失败!');
            } else {
                alert('申请成功');
            }
        })
    })
}
stallApply();


//菜市场申请--省
function getProvince() {
    var pBox = $('#provincebox'),
        pItem = '';
    $.get(urlInfo() + '/region/parent', {
        'parentId': 0
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (key == 0) {
                $(pBox).siblings('input').val(data.id);
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
    $('#cityBox').removeClass('d_none');
    $(getValue).val(selectValue);
    $.get(urlInfo() + '/region/parent', {
        'parentId': selectValue
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (key == 0) {
                $(cityBox).siblings('input').val(data.id);
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
    $('#townBox').removeClass('d_none');
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
//上传图片
function addPic() {
    var addBtn = $('#fileAdd'),
        hideIpt = $('#addfile');
    $(addBtn).on('click', function() {
        $(hideIpt).trigger('click');
    })
}
addPic();
//上传即时显示图片
function getImg(event) {
    var file = event.files[0],
        reader = new FileReader(),
        picGroup = $('#showPic ul'),
        tokenVal = getToken(),
        randomKey = getRandomKey();
    // console.log(file);
    // console.log(file.name.split('.')[1]);
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        //七牛云跨域问题
        var pic64 = this.result.split(',')[1], //暂时可以分割逗号得到
            xhr = new XMLHttpRequest(),
            xhrget = new XMLHttpRequest(),
            obj;
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4) {
                // var upJson = JSON.parse(xhr.responseText);
                var upJson = eval('(' + xhr.responseText + ')');;
                console.log(upJson);
                var li = '<li class="f_left plis"><img data-key="' + upJson.data.key + '" src="' + imgUrl() + upJson.data.key + getShuiYin() '" alt="">' +
                    '<p data-delkey="' + upJson.data.key + '" class="del">删除</p></li>';
                $(picGroup).append(li);
                // delPic();
                $('#addfile').val('');
            }
        };
        xhr.open("POST", getImgServer(file.size), true);
        xhr.setRequestHeader("Content-Type", "application/octet-stream");
        xhr.setRequestHeader("Authorization", "UpToken " + tokenVal);
        xhr.send(pic64);
    };
}

//楼层信息添加
function addFloorInfo() {
    var floorinfo = $('#floorInfobox');
    $(floorinfo).on('click', function() {
        var dialogCtn = '<div class="dialog d_none"><div class="mask"></div>' +
            '<div class="dialogCtn trans"><div class="title"><p>添加楼层信息</p></div>' +
            '<div class="body"><input type="text" class="jsonIpt" name="floor" id="floor" placeholder="请输入楼层名称">' +
            '<input type="text" class="jsonIpt" name="staff" id="staff" placeholder="多个摊位以,隔开"></div>' +
            '<div class="footer"><ul class="clearfix"><li class="f_left flis">' +
            '<button type="button" class="btnOk btnFooter">确认</button></li>' +
            '<li class="f_left flis"><button type="button" class="btnFooter cancel">取消</button></li></ul></div></div></div>';
        $('.dialog').remove();
        $('body').append(dialogCtn);
        //取消
        $('.cancel').on('click', function() {
            $(this).parents('.dialog').addClass('d_none');
        });
        //确定
        $('.btnOk').on('click', function() {
            var floor = $('#floor').val(),
                stall = $('#staff').val().replace(/\，/g, ','),
                output = '';
            output = '<span data-stalls="' + stall + '">' + floor + '<i class="icon-close"></i></span>';
            $(floorinfo).append(output);
            $(this).parents('.dialog').addClass('d_none');
        });
        dialogAnimation();
    });
    //移除楼层信息
    $(floorinfo).on('click', '.icon-close', function(e) {
        e.stopPropagation(); //阻止事件向上冒泡
        $(this).parent().remove();
    })
}
addFloorInfo()

//添加市场
function addMarket() {
    $('#marketApply').on('click', function() {
        //获得所有的img key
        var imglis = $('#showPic li img'),
            imgUrls = '';
        $.each(imglis, function() {
            imgUrls += $(this).attr('data-key') + '#';
        });
        imgUrls = imgUrls.substring(0, imgUrls.length - 1);
        var jsonbox = $('#floorInfobox span'),
            jsonArr = '[';
        //获得楼层json数据
        $.each(jsonbox, function() {
            jsonArr += '{"floor":\"' + $(this).text() + '\","stall":\"' + $(this).attr('data-stalls') + '\"}' + ',';
        })
        jsonArr = jsonArr.substring(0, jsonArr.length - 1);
        jsonArr += ']';
        var marketName = $('#marketName').val(),
            province = $('#province').val(),
            city = $('#city').val(),
            district = $('#district').val(),
            address = $('#address').val(),
            area = $('#area').val(),
            floorInfo = jsonArr,
            marketCompany = $('#marketCompany').val(),
            marketIntro = $('#marketIntro').val(),
            contactName = $('#contactName').val(),
            contactMobile = $('#contactMobile').val(),
            meetAddress = $('#meetAddress').val(),
            imgUrl = imgUrls;
        $.post(urlInfo() + '/market/add', {
            'userId': 1,
            'marketName': marketName,
            'province': province,
            'city': city,
            'district': district,
            'address': address,
            'area': area,
            'floorInfo': floorInfo,
            'marketCompany': marketCompany,
            'marketIntro': marketIntro,
            'contactName': contactName,
            'contactMobile': contactMobile,
            'meetAddress': meetAddress,
            'imgUrl': imgUrl
        }, function(res) {
            if (res.status.code == 200) {
                alert('添加成功');
            } else {
                alert('添加失败');
            }
        })
    })
}
addMarket();

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