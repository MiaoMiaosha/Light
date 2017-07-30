$(function() {
    //设置主体内容的高度
    // $('.main_body').css('min-height', $('html').height() - 168);
    // $('.main_body').load('../html/home.html'); //默认加载home.html
    // alert(window.location.href);

    $('.com_body').css('min-height', $('html').height() - 108);
});
var xmlHttpRequest;

$(function() {

    if (window.XMLHttpRequest) {

        xmlHttpRequest = new XMLHttpRequest();

    } else {

        xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

    }

    xmlHttpRequest.open("GET", "AjaxServlet", true);

});

//页面切换
function curPage() {
    var boxCtn = $('.main_body'),
        hrefLi = $('.com_bottom .btlis'),
        nowHref = window.location.href,
        nowHrefLen = nowHref.split('#').length;
    if (nowHrefLen == 1)
        $(boxCtn).load('../html/home.html');
    else {
        var ids = nowHref.split('@');
        if (ids.length > 1) {
            var typeName = ids[1].split('=');
            if (typeName[0] == 'caseId') {
                setStr('caseId', typeName[1]);
            } else if (typeName[0] == 'marketId') {
                setStr('marketId', typeName[1]);
            } else if (typeName[0] == 'goodsId') {
                setStr('goodsId', typeName[1]);
            }
            $(boxCtn).load('../html/' + nowHref.split('#')[1].split('@')[0] + '.html');
        } else {
            $(boxCtn).load('../html/' + nowHref.split('#')[1] + '.html');
        }
    }
    $.each(hrefLi, function() {
        $(this).on('click', function() {
            var targetHref = $(this).attr('data-href');
            $(this).addClass('cur').siblings('.btlis').removeClass('cur');
            if (typeof targetHref == 'undefined') {
                // $(boxCtn).load('../html/home.html');
                if (window.location.href.split('perInfo/index.html').length > 1) {
                    location.href = "http://tobyhan.cn/mobile/html/perInfo/index.html";
                }
            } else {
                if (targetHref.split('/').length == 2) {
                    location.hash = "";
                    $(boxCtn).load('../' + targetHref + '.html');
                } else if (targetHref.split('/').length == 1) {
                    window.location.href = '../index.html#' + targetHref;
                }
            }
        })
    })
}
curPage();

/**
 * 微信jssdk
 */
function useWxJSSDK(title, url, intro, imgs) {
    $.ajaxSetup({
        async: false
    });
    $.get(urlInfo() + '/sign', {
        'url': location.href
    }, function(res) {
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: res.data.appid, // 必填，公众号的唯一标识
            timestamp: res.data.timestamp, // 必填，生成签名的时间戳
            nonceStr: res.data.nonceStr, // 必填，生成签名的随机串
            signature: res.data.signature, // 必填，签名，见附录1
            jsApiList: ["onMenuShareAppMessage", "getLocation"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
    }, 'json');
    wx.ready(function() {
        wx.onMenuShareAppMessage({
            title: title, // 分享标题
            desc: intro, // 分享描述
            link: url, // 分享链接，该链接域名需在JS安全域名中进行登记
            imgUrl: imgs, // 分享图标
            type: '', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function() {
                // 用户确认分享后执行的回调函数
                console.log(1);
            },
            cancel: function() {
                // 用户取消分享后执行的回调函数
                console.log(2);
            }
        });
        wx.getLocation({
            type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
            success: function(res) {
                var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                var speed = res.speed; // 速度，以米/每秒计
                var accuracy = res.accuracy; // 位置精度
                var gpsPoint = new BMap.Point(longitude, latitude);
                var pointArr = [];
                pointArr.push(gpsPoint);
                new BMap.Convertor().translate(pointArr, 0, 5, initMap); //转换坐标 
            }
        });
    })
}
useWxJSSDK()

/**
 * 回调取坐标
 */
function initMap(point) {
    var geoc = new BMap.Geocoder();
    var pt = point.points[0];
    geoc.getLocation(pt, function(rs) {
        var addComp = rs.addressComponents;
        $('#location').html('<i class="icon-location"></i>' + addComp.city);
        // alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
    });
}


function tabCur() {
    //通用的切换
    var lis = $('.com_lip li'),
        items = $('.com_boxp .com_itemp');
    $.each(lis, function(key, data) {
        $(data).on('click', function() {
            $(this).addClass('cur').siblings('li').removeClass('cur');
            $(items[key]).removeClass('d_none').siblings('.com_itemp').addClass('d_none');
        })
    });
    //三级
    var lis3 = $('.com_li li'),
        items3 = $('.com_box .com_item');
    $.each(lis3, function(key, data) {
        $(data).on('click', function() {
            $(this).addClass('cur').siblings('li').removeClass('cur');
            $(items3[key]).removeClass('d_none').siblings('.com_item').addClass('d_none');
        })
    });
}

//通用的勾选
function getCbk() {
    var cbkBox = $('.com_cbkAll'),
        cbkli = $(cbkBox).find('li');
    $.each(cbkli, function(key, data) {
        $(data).on('click', '.cbk', function() {
            if ($(this).is(':checked')) {
                //这里能够得到勾选的数据
                console.log($(this).parent().text());
            }
        })
    })
}

function searchPage() {
    var searchBox = $('#search'),
        ctnBox = $('.main_body');
    $(searchBox).on({
        focus: function() {
            if ($(this).val() == '') {
                $(ctnBox).load('../html/' + $(this).attr('data-href') + '.html');
            }
        },
        // blur: function() {
        //     if ($(this).val() == '') {
        //         $(ctnBox).load('../html/home.html');
        //     }
        // }
    })
}
searchPage();

//接口地址统一
function urlInfo() {
    var baseUrl = 'http://tobyhan.cn/LightProject';
    return baseUrl;
}
//图片地址统一
function imgUrl() {
    var baseImgUrl = 'http://onk41i0aj.bkt.clouddn.com/';
    return baseImgUrl;
}

function getShuiYin() {
    var shuiyin = '';
    $.ajaxSetup({
        async: false
    })
    $.get(urlInfo() + '/watermark', function(res) {
        if (res.status.code == 200) {
            shuiyin = '?' + res.data;
        }
    });
    // var shuiyin = '?imageView2/0/q/75%7Cwatermark/2/text/5Yac6LS46K6-6K6h55S15ZWG566h55CG77yad3d3Lmd5Z3lneS5jb20=/font/5b6u6L2v6ZuF6buR/fontsize/500/fill/I0ZBRjRGNA==/dissolve/100/gravity/SouthEast/dx/20/dy/35%7Cimageslim';
    return shuiyin;
}
//获取上传图片接口的token
function getToken() {
    var tokenValue = '';
    //改用$.ajax({}) 同步
    $.ajax({
        type: 'GET',
        url: urlInfo() + '/upload/token',
        async: false,
        dataType: 'json',
        success: function(res) {
            tokenValue = res.data.token;
        }
    });
    // $.get(urlInfo() + '/upload/token', function(res) {
    //     tokenValue = res.data.token;
    // });
    return tokenValue;
}
//七牛服务器
function getImgServer(filesize) {
    var imgServer = 'http://up-z2.qiniu.com/putb64/' + filesize + '/key/' + getRandomKey();
    return imgServer;
}

function getImgServerNot64() {
    var imgServer = 'http://up-z2.qiniu.com/';
    return imgServer;
}
//获取随机数--用于重命名图片
function getRandomKey() {
    //再加上一个两位数的随机数
    var Base64Encode = new Base64(),
        date = new Date(),
        key = '';
    key = date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getTime().toString() + '/' + parseInt(Math.random() * 10);
    return Base64Encode.encode(key);
}

function getRights() {
    var rightsText = '',
        rights = $('.com_bottom .cyrhts');
    $.get(urlInfo() + '/config/index', function(res) {
        var phone = res.data.settingMap.mobile_number,
            icp = res.data.settingMap.ICP,
            copyName = res.data.settingMap.copyright;
        rightsText = '<p>客服热线：' + phone + '&nbsp;' + icp + '</p><p>' + copyName + '</p>';
        $(rights).html(rightsText);
    }, 'json');
}
getRights();

//关闭当前窗口
function removeAllCookie() {
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if (keys) {
        for (var i = keys.length; i--;)
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
    }
}

//弹窗动画
function dialogAnimation() {
    var dialog = $('.dialog'),
        mask = $('.dialog .mask'),
        dialogBody = $('.dialog .dialogCtn');
    $(dialog).removeClass('d_none').find('.mask').css('opacity', '0').animate({
        'opacity': '0.6'
    }).siblings('.dialogCtn').css({
        'transform': 'translateY(0px)',
        'opacity': '1'
    });
    $(mask).on('click', function() {
        $(this).animate({
            'opacity': '0'
        }, function() {
            $(dialog).addClass('d_none');
        }).siblings('.dialogCtn').css({
            'opacity': '0',
            'transform': 'translateY(-100px)'
        })
    });
}
//设置cookie
function setCookie(c_name, value, expiredays) {
    var exdate = new Date()
    exdate.setDate(exdate.getDate() + expiredays)
    document.cookie = c_name + "=" + escape(value) +
        ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString() + ";path=/")
}

//取回cookie
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(";", c_start)
            if (c_end == -1) c_end = document.cookie.length
            return unescape(document.cookie.substring(c_start, c_end))
        }
    }
    return ""
}

//清除cookie    
function removeCookie(name) {
    setCookie(name, "", -1);
}

/**
 * 使用localstorage
 */
function setStr(key, word) {
    var str = window.localStorage;
    str.setItem(key, word);
}

function getStr(key) {
    var str = window.localStorage;
    return str.getItem(key);
}

function removeStr(key) {
    var str = window.localStorage;
    str.removeItem(key);
}

function removeAllStr() {
    var str = window.localStorage;
    str.clear();
}

//时间戳的转换
function getTime(unixtime) {
    return new Date(parseInt(unixtime) * 1000).toLocaleString().replace(/[\u4e00-\u9fa5].*/, '');
}

function getTimeToDetail(obj) {
    obj *= 1000;
    var date = new Date(obj);
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    return Y + M + D + h + m + s;
    // console.log(Y + M + D + h + m + s); //呀麻碟
}


//价钱0.00化
function toFloat(num) {
    return parseFloat(num / 100).toFixed(2);
}
//价格分化
function toInt(num) {
    return parseInt((num * 100).toFixed(2));
}

function tozheng(num) {
    return parseInt((num).toFixed(0));
}


//获取用户信息
function getUserInfo() {
    if (getCookie('wxUserId').length > 0 && getCookie('wxUserId') != 'undefined') {

    } else {
        if (location.href.indexOf('?userId=') != -1) {
            var userId = window.location.href.split('?userId=')[1];
            if (userId.split('#').length > 1) {
                var realuserid = userId.split('#')[0];
                setCookie('wxUserId', realuserid);
            } else {
                setCookie('wxUserId', userId);
            }
        }
    }
}
getUserInfo();

/**
 * 重新url
 */
function reloadUrl() {
    var href = location.href;
    if (href.indexOf('?userId') != -1) {
        var newHref = href.split('?userId')[0];
        location.href = newHref;
    }
}
reloadUrl();

/**
 * 返回多张图片的第一张
 */
function getFirstImg(imgUrl) {
    if (imgUrl == '' || imgUrl == null) return '';
    else return imgUrl.split('#')[0];
}

/**
 * 上传的限制 不能超过十张
 */
function limitUploadNum(picGroupElm) {
    var picGroup = $(picGroupElm).find('li');
    if (picGroup.length > 10) {
        return false;
    } else {
        return true;
    }
}

/**
 * 放大图片
 */
function zoomPic() {
    var prevImg = $('img');
    $(prevImg).on('click', function() {
        if ($(this).parent()[0].tagName.toLowerCase() != 'a') {
            var prevImgSrc = $(this).attr('src');
            $('.dialog').remove();
            var dialogCtn = '<div class="dialog d_none"><div class="mask"></div>' +
                '<div class="dialog1Img trans"><img class="trans" src="' + prevImgSrc + '">' +
                '</div></div>';
            $('body').append(dialogCtn);
            // var imgWh = getImgInitWH(prevImgSrc).split('-'),
            //     iWidth = imgWh[0],
            //     iHeight = imgWh[1];
            dialogAnimation();
            var bodyImgH = $('.dialog img').height();
            $('.dialog .dialog1Img').css({
                'top': '50%',
                'margin-top': -bodyImgH / 2 + 'px'
            })
        }
    })
}
/**
 * com_table下的输入框获取焦点的时候隐藏底部菜单栏
 * 失去焦点的时候显示
 */
function hideBottomMenu() {
    var formipt = $('.com_table input'),
        formarea = $('.com_table textarea');
    $(formipt).on({
        focus: function() {
            $('.com_bottom').hide();
        },
        blur: function() {
            $('.com_bottom').show();
        }
    })
    $(formarea).on({
        focus: function() {
            $('.com_bottom').hide();
        },
        blur: function() {
            $('.com_bottom').show();
        }
    })
}
hideBottomMenu()

function Base64() {

    // private property
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    // public method for encoding
    this.encode = function(input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = _utf8_encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
                _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
                _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
        }
        return output;
    }

    // public method for decoding
    this.decode = function(input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (i < input.length) {
            enc1 = _keyStr.indexOf(input.charAt(i++));
            enc2 = _keyStr.indexOf(input.charAt(i++));
            enc3 = _keyStr.indexOf(input.charAt(i++));
            enc4 = _keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
        }
        output = _utf8_decode(output);
        return output;
    }

    // private method for UTF-8 encoding
    _utf8_encode = function(string) {
        string = string.replace(/\r\n/g, "\n");
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }
        return utftext;
    }

    // private method for UTF-8 decoding
    _utf8_decode = function(utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while (i < utftext.length) {
            c = utftext.charCodeAt(i);
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if ((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i + 1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i + 1);
                c3 = utftext.charCodeAt(i + 2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
        return string;
    }
}