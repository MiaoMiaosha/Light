$(function() {
    $('.com_body').css('min-height', $('html').height() - 108);
});

/**
 * 点击确认完成
 */
function checkStatus() {
    var tp = $('.pmBox .mlis'),
        projectId = getStr('projectId');
    $.each(tp, function(i) {
        if ($(this).find('.tp').attr('data-status') == 1) {
            $(this).find('.tp').addClass('ctp').find('.icon-check').removeClass('d_none');
        }
    })
    $(tp).on('click', '.tp', function() {
        var _this = this;
        if ($(_this).attr('data-status') == 0) {
            if (confirm('是否修改进度')) {
                var progressId = $(_this).attr('data-id'),
                    type = $(_this).attr('data-lv');
                $.post(urlInfo() + '/admin/project/editprodetail', {
                    'type': type,
                    'processId': progressId,
                    'projectId': projectId
                }, function(res) {
                    if (res.status.code == 200) {
                        $(_this).addClass('ctp').find('.icon-check').removeClass('d_none');
                    } else {
                        alert('修改失败');
                    }
                }, 'json');
            }
        }
        //  else {
        //     $(_this).attr('data-check', 0).removeClass('ctp').find('.icon-check').addClass('d_none');
        // }
    })
}
// checkStatus();

/**
 * 获取进度列表
 */
function getProgressList() {
    var projectId = getStr('projectId'),
        progressBox = $('#progressBox'),
        progressCtn = '',
        // progressChildCtn = '',
        progressState = '',
        pChild = '';
    $.get(urlInfo() + '/admin/project/prodetail', {
        'projectId': projectId
    }, function(res) {
        $.each(res.data, function(i) {
            if (this.list.length > 0) {
                var progressChildCtn = '';
                $.each(this.list, function(k) {
                    if (this.status == 0) {
                        progressState = '<button type="button" class="btn btnAdd btnSave" onclick="checkChildProgress(' + this.id + ',2)">确定完成</button>';
                    } else {
                        progressState = '完成';
                    }
                    progressChildCtn += '<li><div class="ctnText clearfix"><p class="f_left">' + this.name + '</p>' +
                        '<div class="isdone f_right">' + progressState + '</div></div></li>';
                })
                pChild = progressChildCtn;
            }
            progressCtn += '<li class="mlis"><p class="tp" data-lv="1" data-id="' + this.id + '" data-status="' + this.status +
                '">' + this.name + '<i class="icon-check d_none"></i><i class="icon-check1"></i></p><div class="pmCtn p_rel">' +
                '<ul>' + pChild + '</ul></div></li>';
        })
        progressBox.html(progressCtn);
        checkStatus();
    }, 'json');
}
getProgressList();

/**
 * 修改子进度
 */
function checkChildProgress(processId, type) {
    if (confirm('是否修改进度')) {
        var projectId = getStr('projectId');
        $.post(urlInfo() + '/admin/project/editprodetail', {
            'type': type,
            'processId': processId,
            'projectId': projectId
        }, function(res) {
            if (res.status.code == 200) {
                alert('修改成功');
                $('.main_body').load('../perInfo/promanager.html');
            } else {
                alert('修改失败');
            }
        })
    }
}