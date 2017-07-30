$(function() {
    tabCur();
});

/**
 * 从市场详情过来的申请摊位 可直接追加信息
 */
function getMarketIfHad() {
    if (getCookie('marketJson').length > 0 && getCookie('marketJson') != 'undefined') {
        var marketJson = getCookie('marketJson');
        removeCookie('marketJson');
        marketJson = JSON.parse(marketJson);
        var userid = getCookie('userid');
        console.log(marketJson);
        $('#mid').val(marketJson.marketId);
        $('#userid').val(marketJson.marketUserId);
        $('#selectMarket').val(marketJson.marketName);
        $('#mtsheng').val(marketJson.marketProvince);
        $('#mtshi').val(marketJson.marketCity);
        // $('#mtxian').val();
        $('#mtaddr').val(marketJson.marketAddr);
        $('#mtarea').val(marketJson.marketArea);
        $('#imgkey').val(marketJson.marketImgKey);
        var floorBox = $('#mtfloor'),
            floorNum = '';
        $.get(urlInfo() + '/floor/market', {
            'marketId': marketJson.marketId
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
                            if (data.status == 1) {
                                stallCtn += '<li class="f_left tlis ns" data-id="' + data.sid + '" data-status="' + data.status + '"><i class="icon-select"></i>' + data.stallName + '</li>';
                            } else {
                                stallCtn += '<li class="f_left tlis" data-id="' + data.sid + '"  data-status="' + data.status + '"><i class="icon-select"></i>' + data.stallName + '</li>';
                            }
                        });
                        $(stallBox).html(stallCtn);
                        stallSelect();
                    })
                }
                floorNum += '<option value="' + data.fid + '">' + data.floorName + '</option>';
            });
            $(floorBox).html(floorNum);
        });
        //显示小图片
        var imglist = marketJson.marketImgKey.split('#'),
            imgCtn = "";
        for (var i = 0; i < imglist.length; i++) {
            imgCtn += "<li class='f_left plis'><img src='" + imgUrl() + imglist[i] + getShuiYin()+"'/></li>";
        }
        $('#showPic ul').html(imgCtn);
        zoomPic();
        removeCookie('marketJson');
    }
}
getMarketIfHad()

//点击市场名称输入框 弹窗显示列表
function showMarketList() {
    var showBtn = $('#selectMarket'),
        dialogBox = '';
    $(showBtn).on('click', function() {
        //<li class="f_left mlis">市场a</li>
        //上下页切换
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
            $.post(urlInfo() + '/market/list?status=1', {
                'marketName': $(_this).siblings('.ipts').val(),
                'rows': 4
            }, function(res) {
                if (res.data != '') {
                    $.each(res.data, function(key, data) {
                        lictn += '<li class="mlis" data-id="' + data.mid + '" data-userid="' + data.userId + '" data-sheng="' + data.provinceName +
                            '" data-area="' + data.area + '" data-contactName="' + data.contactName + '" data-mobile="' + data.contactMobile + '" data-shi="' +
                            data.cityName + '" data-xian="' + data.districtName + '" data-addr="' + data.address + '" data-imgkey="' + data.imgUrl +
                            '">' + data.provinceName + data.cityName + '--' + data.marketName + '</li>';
                    });
                    $(libox).html(lictn);
                    setCookie('nowPage', res.status.curPage, 1);
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
        $('.next').on('click', function() {
            var _this = this,
                libox = $('#mlist ul'),
                lictn = '',
                nowPage = parseInt(getCookie('nowPage')) + 1;
            $.post(urlInfo() + '/market/list', {
                'marketName': $(_this).siblings('.ipts').val(),
                'rows': 4,
                'page': nowPage
            }, function(res) {
                if (res.data != '') {
                    $.each(res.data, function(key, data) {
                        lictn += '<li class="mlis" data-id="' + data.mid + '" data-userid="' + data.userId + '" data-sheng="' + data.provinceName +
                            '" data-area="' + data.area + '" data-contactName="' + data.contactName + '" data-mobile="' + data.contactMobile + '" data-shi="' +
                            data.cityName + '" data-xian="' + data.districtName + '" data-addr="' + data.address + '" data-imgkey="' + data.imgUrl +
                            '">' + data.provinceName + data.cityName + '--' + data.marketName + '</li>';
                    });
                    $(libox).html(lictn);
                    setCookie('nowPage', res.status.curPage, 1);
                    $('.prev').prop('disabled', false);
                    //显示分页
                    $('.diafooter').removeClass('d_none');
                    //点击市场条目 将数据加载至表格
                    showMarketInfo();
                } else {
                    $(libox).html('没有相关的市场信息');
                    $('.diafooter').addClass('d_none');
                }
            })
        })
        $('.prev').on('click', function() {
            var _this = this,
                libox = $('#mlist ul'),
                lictn = '',
                nowPage = parseInt(getCookie('nowPage')) - 1;
            if (nowPage == 1) {
                $(_this).prop('disabled', true);
            }
            $.post(urlInfo() + '/market/list', {
                'marketName': $(_this).siblings('.ipts').val(),
                'rows': 4,
                'page': nowPage
            }, function(res) {
                if (res.data != '') {
                    $.each(res.data, function(key, data) {
                        lictn += '<li class="mlis" data-id="' + data.mid + '" data-userid="' + data.userId + '" data-sheng="' + data.provinceName +
                            '" data-area="' + data.area + '" data-contactName="' + data.contactName + '" data-mobile="' + data.contactMobile + '" data-shi="' +
                            data.cityName + '" data-xian="' + data.districtName + '" data-addr="' + data.address + '" data-imgkey="' + data.imgUrl +
                            '">' + data.provinceName + data.cityName + '--' + data.marketName + '</li>';
                    });
                    $(libox).html(lictn);
                    setCookie('nowPage', res.status.curPage, 1);
                    $('.prev').prop('disabled', false);
                    //显示分页
                    $('.diafooter').removeClass('d_none');
                    //点击市场条目 将数据加载至表格
                    showMarketInfo();
                } else {
                    $(libox).html('没有相关的市场信息');
                    $('.diafooter').addClass('d_none');
                }
            })
        })
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
                imgkey = $(this).attr('data-imgkey'),
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
            $('#imgkey').val(imgkey);
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
                                if (data.status == 1) {
                                    stallCtn += '<li class="f_left tlis ns" data-id="' + data.sid + '" data-status="' + data.status + '"><i class="icon-select"></i>' + data.stallName + '</li>';
                                } else {
                                    stallCtn += '<li class="f_left tlis" data-id="' + data.sid + '"  data-status="' + data.status + '"><i class="icon-select"></i>' + data.stallName + '</li>';
                                }
                            });
                            $(stallBox).html(stallCtn);
                            stallSelect();
                        })
                    }
                    floorNum += '<option value="' + data.fid + '">' + data.floorName + '</option>';
                });
                $(floorBox).html(floorNum);
            })

            //显示小图片
            var imglist = imgkey.split('#'),
                imgCtn = "";
            for (var i = 0; i < imglist.length; i++) {
                imgCtn += "<li class='f_left plis'><img src='" + imgUrl() + imglist[i] + getShuiYin()+"'/></li>";
            }
            $('#showPic ul').html(imgCtn);
            zoomPic();
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
            if (data.status == 1) {
                stallCtn += '<li class="f_left tlis ns" data-id="' + data.sid + '" data-status="' + data.status + '"><i class="icon-select"></i>' + data.stallName + '</li>';
            } else {
                stallCtn += '<li class="f_left tlis" data-id="' + data.sid + '" data-status="' + data.status + '"><i class="icon-select"></i>' + data.stallName + '</li>';
            }
        });
        $(stallBox).html(stallCtn);
        stallSelect();
    }, 'json')
}

/**
 * 摊位号选择
 *  status-1 已出租 0-未出租
 */
function stallSelect() {
    var tlis = $('#tanwei .tlis');
    $(tlis).on('click', function() {
        if ($(this).attr('data-status') != 1) {
            $(this).addClass('cur').siblings('li').removeClass('cur');
        }
    })
}

//提交摊位申请
function stallApply() {
    var applyBtn = $('#stallApply');
    $(applyBtn).on('click', function() {
        var userid = getCookie('wxUserId'),
            marketId = $('#mid').val(),
            floorId = $('#floorId').val(),
            sid = $('#tanwei .tlis.cur').attr('data-id'),
            stallName = $('#tanwei .tlis.cur').text(),
            contactName = $('#mtcName').val(),
            contactMobile = $('#mtphone').val();
        $.post(urlInfo() + '/stall/apply', {
            'userId': userid,
            'marketId': marketId,
            'stallId': sid,
            'stallName': stallName,
            'floorId': floorId,
            'contactName': contactName,
            'contactMobile': contactMobile
        }, function(res) {
            if (res.status.code != '200') {
                alert('申请失败!');
            } else {
                alert('申请成功');
                $('.main_body').load('../html/home.html');
            }
        })
    })
}
stallApply();


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
                if (pid == '') {
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
                            if (cid == '') {
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
// getProvince();

//菜市场申请--省
function getProvince1() {
    var pBox = $('#provincebox'),
        pItem = '';
    $.get(urlInfo() + '/region/parent', {
        'parentId': 0
    }, function(res) {
        $.each(res.data, function(key, data) {
            if (key == 0) {
                $(pBox).siblings('input').val(data.id);
                // var provinceId = '';
                // if (pid == '') {
                //     provinceId = data.id;
                // } else {
                //     provinceId = pid;
                // }
                var provinceId = data.id;
                //获取市级
                var cityBox = $('#citybox'),
                    cityCtn = '';
                $.get(urlInfo() + '/region/parent', {
                    'parentId': provinceId
                }, function(res1) {
                    $.each(res1.data, function(key1, data1) {
                        if (key1 == 0) {
                            $(cityBox).siblings('input').val(data1.id);
                            // var cityId = '';
                            // if (cid == '') {
                            //     cityId = data1.id;
                            // } else {
                            //     cityId = cid;
                            // }
                            var cityId = data1.id;
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
getProvince1();
//省市联动
function getCity1(event) {
    var selectValue = $(event).find('option:selected').val(),
        getValue = $(event).siblings('input'),
        cityBox = $('#citybox'),
        cityCtn = '';
    // $('#cityBox').removeClass('d_none');
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
function getTown1(event) {
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
function getXian1(event) {
    var selectValue = $(event).find('option:selected').val(),
        getValue = $(event).siblings('input');
    $(getValue).val(selectValue);
}
//上传图片
function addPic() {
    var addBtn = $('#marketAdd');
    $(addBtn).on('click', function() {
        // var hideIpt = $(this).siblings('.com_fileup');
        var picGroupElm = $(this).parents('.filebox').next('.picbox');
        if (limitUploadNum(picGroupElm)) {
            $(this).siblings('.com_fileup').trigger('click');
        } else {
            alert('每次上传的数量不得超过十张');
        }
    })
}
addPic();
//上传即时显示图片
function getMarketImg(event) {
    var file = event.files[0],
        reader = new FileReader(),
        picGroup = $(event).parents('.filebox').next('.picbox').find('ul'),
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
                var li = '<li class="f_left plis"><img data-key="' + upJson.data.key + '" src="' + imgUrl() + upJson.data.key + getShuiYin() +'" alt="">' +
                    '<p data-delkey="' + upJson.data.key + '" class="del">删除</p></li>';
                $(picGroup).append(li);
                // delMarketPic();
                $('#addfile').val('');
            }
        };
        xhr.upload.onprogress = function(evt) {
            if (evt.lengthComputable) {
                var percentComplete = Math.round(evt.loaded * 100 / evt.total);
                if (percentComplete != 100) {
                    $(event).siblings('.com_add').prop('disabled', true).text('正在上传中..');
                    $('#marketApply').prop('disabled', true);
                } else {
                    $(event).siblings('.com_add').prop('disabled', false).text('上传文件');
                    $('#marketApply').prop('disabled', false);
                }
            }
        }
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
            '<input type="text" class="jsonIpt lipt" name="staff" id="staff" placeholder="多个摊位以,隔开"><p class="notes">输入可发布的摊位号,多个以逗号隔开</p>' +
            '<p class="notes lp">例如:a002,a07...</p></div>' +
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
                stall = $('#staff').val().toUpperCase().replace(/\，/g, ','),
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
        var jsonbox = $('#floorInfobox span');
        var jsonArr = '[';
        if (jsonbox.length != 0) {
            //获得楼层json数据
            $.each(jsonbox, function() {
                jsonArr += '{"floor":\"' + $(this).text() + '\","stall":\"' + $(this).attr('data-stalls') + '\"}' + ',';
            })
            jsonArr = jsonArr.substring(0, jsonArr.length - 1);
            jsonArr += ']';
        } else {
            jsonArr = '';
        }
        // var jsonArr = '[';
        //获得楼层json数据
        // $.each(jsonbox, function() {
        //     jsonArr += '{"floor":\"' + $(this).text() + '\","stall":\"' + $(this).attr('data-stalls') + '\"}' + ',';
        // })
        // jsonArr = jsonArr.substring(0, jsonArr.length - 1);
        // jsonArr += ']';
        var marketName = $('#marketName').val(),
            province = $('#province1').val(),
            city = $('#city1').val(),
            district = $('#district1').val(),
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
            'userId': getCookie('wxUserId'),
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
                location.href = 'perInfo/index.html';
            } else {
                alert('添加失败');
            }
        })
    })
}
addMarket();

/**
 * 获取招租图片
 */
function getAdPic() {
    $.get(urlInfo() + '/config/bus', function(res) {
        $.each(res.data, function(i) {
            var imgu = '<img src="' + imgUrl() + this.value + getShuiYin()+'" alt="' + this.name + '" />'
            $('#ad' + (i + 1)).html(imgu);
        })
    }, 'json')
}
getAdPic();

/**
 * 删除图片
 */
function delMarketPic() {
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
delMarketPic();