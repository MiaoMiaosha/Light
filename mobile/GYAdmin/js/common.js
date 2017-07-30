//接口地址统一
function urlInfo() {
    var baseUrl = 'http://tobyhan.cn/LightProject';
    return baseUrl;
}

function uuuu() {
    var baseUrl = 'http://tobyhan.cn/mobile';
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
 * 移除所有的cookie
 */
function removeAllCookie() {
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if (keys) {
        for (var i = keys.length; i--;)
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString() + ';path=/';
    }
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

//验证登录状态
function checkLoginStatus() {
    var href = location.href,
        targetHref = "";
    if (href.indexOf('perInfo') != -1) {
        targetHref = "../login.html";
    } else {
        targetHref = "login.html";
    }
    if (getCookie('userId').length == 0) {
        location.href = targetHref;
        removeAllCookie();
        removeAllStr();
    } else if (getStr('loginStatues') == 500) {
        alert('登陆状态异常,请重新登录');
        location.href = targetHref;
        removeAllCookie();
        removeAllStr();
    }
}

/**
 * 退出
 */
function logoutAll() {
    var type = getStr('showPage'),
        typeId = '',
        openId = getStr('openId');
    switch (type) {
        case 'admin':
            typeId = 0;
            break;
        case 'yg':
            typeId = 1;
            break;
        case 'kh':
            typeId = 2;
            break;
        case 'kj':
            typeId = 0;
            break;
        default:
            break;
    }
    $.post(urlInfo() + '/logout', {
        'type': typeId,
        'openId': openId
    }, function(res) {
        if (res.status.code == 200) {
            // removeAllCookie();
            removeAllStr();
            removeAllCookie();
            location.reload();
        } else {
            alert('服务器发生错误!')
        }
    })
}

//注销
function logout() {
    $('#logout').on('click', function() {
        var type = getStr('showPage'),
            typeId = '',
            openId = getStr('openId');
        switch (type) {
            case 'admin':
                typeId = 0;
                break;
            case 'yg':
                typeId = 1;
                break;
            case 'kh':
                typeId = 2;
                break;
            case 'kj':
                typeId = 0;
                break;
            default:
                break;
        }
        $.post(urlInfo() + '/logout', {
            'type': typeId,
            'openId': openId
        }, function(res) {
            if (res.status.code == 200) {
                // removeAllCookie();
                removeAllStr();
                removeAllCookie();
                location.reload();
            } else {
                alert('服务器发生错误!')
            }
        })
    })
}
logout()

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

//时间戳的转换
function getTime(unixtime) {
    var t = new Date(parseInt(unixtime) * 1000).toLocaleString().replace(/[\u4e00-\u9fa5].*/, '').replace(/\//g, '-'),
        n = '';
    // t = t.split('-');
    if (parseInt(t.split('-')[1]) < 10) {
        t = t.split('-');
        t[1] = '0' + t[1];
        if (parseInt(t[2].trim()) < 10) {
            t[2] = '0' + t[2];
        }
        for (var i = 0; i < t.length; i++) {
            n = t.join('-');
        }
        return n.trim();
    } else if (parseInt(t.split('-')[2]) < 10) {
        t = t.split('-');
        t[2] = '0' + t[2];
        for (var i = 0; i < t.length; i++) {
            n = t.join('-');
        }
        return n.trim();
    } else {
        return t;
    }
}
/**
 * 获取年月
 * @param {*} year 
 * @param {*} month 
 */

function getLastDay(year, month) {
    var new_year = year; //取当前的年份          
    var new_month = month++; //取下一个月的第一天，方便计算（最后一天不固定）          
    if (month > 12) {
        new_month -= 12; //月份减          
        new_year++; //年份增          
    }
    //取当年当月中的第一天
    var new_date = new Date(new_year, new_month, 1);
    var final_date = new Date(new_date.getTime() - 1000 * 60 * 60 * 24);
    //获取当月最后一天日期             
    return final_date.getTime() / 1000;
}

function getLastDay1(year, month) {
    var new_year = year; //取当前的年份          
    var new_month = month++; //取下一个月的第一天，方便计算（最后一天不固定）          
    if (month > 12) {
        new_month -= 12; //月份减          
        new_year++; //年份增          
    }
    //取当年当月中的第一天
    var new_date = new Date(new_year, new_month, 1);
    var final_date = new Date(new_date.getTime() - 1000);
    //获取当月最后一天日期             
    return final_date.getTime() / 1000;
}
//时间转时间戳
function getUnix(dateTime) {
    return Date.parse(dateTime) / 1000;
}
//时间格式化
function formatDate(now) {
    var now1 = new Date(now * 1000);
    var year = now1.getFullYear();
    var month = now1.getMonth() + 1;
    if (month < 10) {
        month = '0' + month;
    }
    return year + "-" + month;
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

//验证输入的金钱
function checkMoney(obj) {
    var id = obj.id;
    var val = obj.value;
    var regStrs = [
        ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0  
        ['[^\\d\\.]+$', ''], //禁止录入任何非数字和点  
        ['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点  
        ['^(\\d+\\.\\d{2}).+', '$1'] //禁止录入小数点后两位以上  
    ];
    for (i = 0; i < regStrs.length; i++) {
        var reg = new RegExp(regStrs[i][0]);
        obj.value = obj.value.replace(reg, regStrs[i][1]);
    }
}

//验证码
function validCode() {
    var codeUrl = 'http://tobyhan.cn/LightProject/validcode';
    return codeUrl;
}

//弹窗动画
function dialogAnimation() {
    var dialog = $('.dialog1'),
        mask = $('.dialog1 .mask'),
        dialogBody = $('.dialog1 .dialogCtn');
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

//base64 加密
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

/**
 * 判断当前浏览器是否是微信浏览器
 */
function isWeixin() {
    var ua = navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == 'micromessenger')
        return true;
    else return false;
}