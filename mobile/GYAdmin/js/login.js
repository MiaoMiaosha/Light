alert(111);
$(function() {
    $('#codepic').on('click', function() {
        $(this).attr('src', validCode() + '?id=' + Math.random());
    });
    alert(222);
    // getStr('userid').length > 0 && getStr('userid') != 'undefined'
    if ((getCookie('userId').length != 0 && getCookie('userId') != 'undefined') && getStr('roleId') == 2) {
        window.location.href = "index.html";
    } else if ((getCookie('userId').length != 0 && getCookie('userId') != 'undefined') && (getStr('roleId') == 3 || getStr('roleId') == 4 || getStr('roleId') == 1)) {
        location.href = "perInfo/index.html";
    }
    alert(333);
    login();
})

window.onload = function() {
    alert('xxxx');
}

function login() {
    $('#login').on('click', function() {
        var username = $('#username').val(),
            password = $('#password').val(),
            inpcode = $('#inpcode').val(),
            loginType = $('#type').val();
        $.post(urlInfo() + '/redirect', {
            'username': username,
            'password': password,
            'inpcode': inpcode,
            // 'roleId': loginType
        }, function(res) {
            if (res.status.code == 200) {
                var roleId = res.data.roleId;
                switch (roleId) {
                    case 1:
                        setStr('userid', res.data.userId);
                        setStr('nickname', res.data.nickname);
                        setStr('roleId', res.data.roleId);
                        // setStr('customerId', res.data.customerId);
                        // setStr('showPage', $('#type').find("option:selected").attr('data-show'));
                        location.href = "perInfo/index.html";
                        setStr('showPage', 'admin');
                        break;
                    case 2:
                        setStr('userid', res.data.userId);
                        setStr('nickname', res.data.nickname);
                        setStr('roleId', res.data.roleId);
                        setStr('employeeId', res.data.employeeId);
                        // setStr('showPage', $('#type').find("option:selected").attr('data-show'));
                        location.href = "index.html";
                        setStr('showPage', 'kj');
                        break;
                    case 3:
                        //进入员工中心
                        setStr('userid', res.data.userId);
                        setStr('nickname', res.data.nickname);
                        setStr('roleId', res.data.roleId);
                        setStr('employeeId', res.data.employeeId);
                        setStr('job', res.data.job);
                        // setStr('showPage', $('#type').find("option:selected").attr('data-show'));
                        window.location.href = " https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3d36f25cad23c1bf&redirect_uri=http%3a%2f%2ftobyhan.cn%2fLightProject%2fwxlogin&response_type=code&scope=snsapi_userinfo&state=" +
                            res.data.userId + "#wechat_redirect";
                        setStr('showPage', 'yg');
                        break;
                    case 4:
                        //进入客户个人中心
                        setStr('userid', res.data.userId);
                        setStr('nickname', res.data.nickname);
                        setStr('roleId', res.data.roleId);
                        setStr('customerId', res.data.customerId);
                        // setStr('job', res.data.job);
                        // setStr('showPage', $('#type').find("option:selected").attr('data-show'));
                        window.location.href = " https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3d36f25cad23c1bf&redirect_uri=http%3a%2f%2ftobyhan.cn%2fLightProject%2fwxlogin&response_type=code&scope=snsapi_userinfo&state=" +
                            res.data.userId + "#wechat_redirect";
                        setStr('showPage', 'kh');
                        break;
                }
            } else {
                alert(res.status.msg)
            }
        }, 'json')
    })
}