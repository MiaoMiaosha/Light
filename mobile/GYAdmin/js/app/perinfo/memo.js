$(function() {
    $('.memo_body').css('min-height', $('html').height() - 48);
});

/**
 * 获取工程列表
 */
function getProjectList() {
    var proBox = $('#projectId'),
        proCtn = '';
    var type = getStr('showPage'),
        userid = getCookie('userId');
    $.ajaxSetup({
        async: false
    })
    if (type == 'yg') {
        $.get(urlInfo() + '/admin/project/eplist', {
            'userId': userid
        }, function(res) {
            if (res.data == '' || res.data == null) {
                proCtn += '<option value="0">默认</option>';
            } else {
                proCtn += '<option value="0">默认</option>';
                $.each(res.data, function(i) {
                    if (i == 0) {
                        setStr('projectId', this.pid);
                    }
                    proCtn += '<option value="' + this.pid + '">' + this.marketName + '</option>';
                })
            }
            proBox.html(proCtn);
        }, 'json');
    } else if (type == 'kh') {
        var customerId = getStr('customerId');
        $.get(urlInfo() + '/admin/project/getcuslist', {
            'customerId': customerId
        }, function(res) {
            if (res.data == '' || res.data == null) {
                proCtn += '<option value="0">默认</option>';
            } else {
                proCtn += '<option value="0">默认</option>';
                $.each(res.data, function(i) {
                    if (i == 0) {
                        setStr('projectId', this.pid);
                    }
                    proCtn += '<option value="' + this.element.pid + '">' + this.element.marketName + '</option>';
                })
            }
            proBox.html(proCtn);
        }, 'json');
    }
}
getProjectList();


/**
 * 默认获得的工程备忘
 */
function getDefaultProject() {
    var projectId = getStr('projectId'),
        loginId = getCookie('userId');
    var oneBox = $('#one'),
        oneCtn = '',
        twoBox = $('#two'),
        twoCtn = '',
        threeBox = $('#three'),
        threeCtn = '',
        fourBox = $('#four'),
        fourCtn = '',
        fiveBox = $('#five'),
        fiveCtn = '',
        sixBox = $('#six'),
        sixCtn = '';
    $.get(urlInfo() + '/admin/memo/list', {
        'projectId': 0,
        'loginUserId': loginId
    }, function(res) {
        $.each(res.data, function(i) {
            switch (this.processId) {
                case 1:
                    oneCtn += '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    break;
                case 2:
                    twoCtn += '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    break;
                case 3:
                    threeCtn += '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    break;
                case 4:
                    fourCtn += '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    break;
                case 5:
                    fiveCtn += '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    break;
                case 6:
                    sixCtn += '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    break;
            }
            oneBox.append(oneCtn);
            twoBox.append(twoCtn);
            threeBox.append(threeCtn);
            fourBox.append(fourCtn);
            fiveBox.append(fiveCtn);
            sixBox.append(sixCtn);
            removeNotes();
        })
    }, 'json')
}
getDefaultProject();

//点击add 弹出添加备忘的弹窗
function showDialog() {
    var dialogCtn = '<div class="dialog1 d_none"><div class="mask"></div>' +
        '<div class="dialogCtn trans"><div class="title"><p>添加备忘录</p></div>' +
        '<div class="body"><textarea rows="4"></textarea></div>' +
        '<div class="footer"><ul class="clearfix"><li class="f_left flis">' +
        '<button type="button" class="btnOk btnFooter">确认</button></li>' +
        '<li class="f_left flis"><button type="button" class="btnFooter cancel">取消</button></li></ul></div></div></div>';
    var addlist = $('.memoBox');
    $(addlist).on('click', '.icon-add', function() {
        var _lithis = this;
        var processId = $(_lithis).parents('.mlis').attr('data-type'),
            projectId = $('#projectId').val();
        $('.dialog1').remove();
        $('body').append(dialogCtn);
        //判断是否已经存在备忘
        var pli = $(_lithis).parents('.mlis');
        //取消
        $('.cancel').on('click', function() {
            $(this).parents('.dialog1').addClass('d_none');
        });
        //确定
        $('.btnOk').on('click', function() {
            var _okthis = this;
            var tV = $(_okthis).parents('.dialog1').find('textarea');
            $.post(urlInfo() + '/admin/memo/add', {
                'projectId': projectId,
                'processId': processId,
                'loginUserId': getCookie('userId'),
                'content': $(tV).val()
            }, function(res) {
                if (res.status.code == 200) {
                    alert('添加成功');
                    removeNotes();
                    $(_okthis).parents('.dialog1').addClass('d_none');
                    $('.main_body').load('memo.html');
                } else {
                    alert(res.status.msg);
                }
            }, 'json');
            // var textCtn = '<div class="memoCtn p_rel">' +
            //     '<div class="ctnText">' + $(tV).val() + '</div><div class="ctnOp">' +
            //     '<ul class="clearfix"><li class="f_left colis"><i class="icon-editor"></i></li>' +
            //     '<li class="f_left colis"><i class="icon-close"></i></li></ul></div></div>';
            // $(pli).append(textCtn);
            // removeNotes();
        });
        dialogAnimation();
    });
}
showDialog();

//移除/编辑备忘
function removeNotes() {
    var mlis = $('.memoBox .mlis');
    if ($(mlis).find('.memoCtn').length > 0) {
        var close = $(mlis).find('.icon-close'),
            edit = $(mlis).find('.icon-editor');
        //删除
        $.each(close, function() {
            $(this).on('click', function() {
                var _cthis = this;
                var id = $(_cthis).parent().attr('data-id');
                $.post(urlInfo() + '/admin/memo/delete', {
                    'id': id
                }, function(res) {
                    if (res.status.code == 200) {
                        alert('删除成功');
                        $(_cthis).parents('.memoCtn').remove();
                    } else {
                        alert(res.status.msg);
                    }
                }, 'json');

            })
        });
        //修改
        var dialogCtn = '<div class="dialog1 d_none"><div class="mask"></div>' +
            '<div class="dialogCtn trans"><div class="title"><p>添加备忘录</p></div>' +
            '<div class="body"><textarea rows="4"></textarea></div>' +
            '<div class="footer"><ul class="clearfix"><li class="f_left flis">' +
            '<button type="button" class="btnOk btnFooter">确认</button></li>' +
            '<li class="f_left flis"><button type="button" class="btnFooter cancel">取消</button></li></ul></div></div></div>';
        $.each(edit, function() {
            $(this).on('click', function() {
                var _edit = this;
                var eid = $(_edit).parent().attr('data-id');
                $('.dialog1').remove();
                $('body').append(dialogCtn);
                var oldValue = $(_edit).parents('.memoCtn').find('.ctnText');
                $('.dialog1').find('textarea').val($(oldValue).text());
                //取消
                $('.cancel').on('click', function() {
                    $(this).parents('.dialog1').addClass('d_none');
                });
                //确定
                $('.btnOk').on('click', function() {
                    var _eokthis = this;
                    var tV = $(_eokthis).parents('.dialog1').find('textarea');
                    $.post(urlInfo() + '/admin/memo/edit', {
                        'id': eid,
                        'content': $(tV).val()
                    }, function(res) {
                        if (res.status.code == 200) {
                            alert('修改成功');
                            $(_eokthis).parents('.dialog1').addClass('d_none');
                            $('.main_body').load('memo.html');
                        } else {
                            alert(res.status.msg);
                        }
                    }, 'json');
                    // $(oldValue).text($(tV).val());
                });
                dialogAnimation();
            })
        });
    }
}
// removeNotes();

/**
 * 根据工程的不同 显示不同的备忘
 */
function getProjectMark(event) {
    var projectId = $(event).val();
    var oneBox = $('#one'),
        oneCtn = '',
        twoBox = $('#two'),
        twoCtn = '',
        threeBox = $('#three'),
        threeCtn = '',
        fourBox = $('#four'),
        fourCtn = '',
        fiveBox = $('#five'),
        fiveCtn = '',
        sixBox = $('#six'),
        sixCtn = '';
    $('#typeGroup').find('.memoCtn').remove();
    $.get(urlInfo() + '/admin/memo/list', {
        'projectId': projectId,
        'loginUserId': getCookie('userId')
    }, function(res) {
        $.each(res.data, function(i) {
            switch (this.processId) {
                case 1:
                    oneCtn = '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    oneBox.append(oneCtn);
                    break;
                case 2:
                    twoCtn = '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    twoBox.append(twoCtn);
                    break;
                case 3:
                    threeCtn = '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    threeBox.append(threeCtn);
                    break;
                case 4:
                    fourCtn = '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    fourBox.append(fourCtn);
                    break;
                case 5:
                    fiveCtn = '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    fiveBox.append(fiveCtn);
                    break;
                case 6:
                    sixCtn = '<div class="memoCtn p_rel" data-projectid="' + this.projectId + '" data-loginId="' + this.loginUserId + '">' +
                        '<div class="ctnText">' + this.content + '</div><div class="ctnOp"><ul class="clearfix">' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-editor"></i></li>' +
                        '<li class="f_left colis" data-id="' + this.id + '"><i class="icon-close"></i></li></ul></div></div>';
                    sixBox.append(sixCtn);
                    break;
            }
        })
        removeNotes();
    }, 'json')
}