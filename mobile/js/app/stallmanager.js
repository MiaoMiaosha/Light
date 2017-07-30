/**
 * 获取摊位列表
 */
function getStallManagerList() {
    var listbox = $('#listbox ul'),
        statebox = '',
        listCtn = '';
    var userId = getCookie('wxUserId'),
        marketId = getCookie('marketId');
    $.ajaxSetup({
        async: false
    })
    $.get(urlInfo() + '/floor/market', {
        'marketId': marketId
            // 'userId': userId
    }, function(res) {
        if (typeof res.data == 'undefined' || res.data.length == 0) {
            listCtn = '<p class="t_center">没有数据</p>';
            $(listbox).html(listCtn);
            $('.loadMore').addClass('d_none');
        } else {
            $.each(res.data, function(i) {
                listCtn += '<li class="clis" data-mid="' + this.marketId + '" data-fid="' + this.fid + '"><div class="libox"><h3>' + this.floorName + '</h3>';
                var fid = this.fid,
                    mid = this.marketId,
                    fCtn = '';
                $.get(urlInfo() + '/stall/list', {
                    'floorId': fid
                }, function(res1) {
                    fCtn += '<ul class="clearfix">';
                    $.each(res1.data, function(k) {
                        fCtn += '<li class="f_left sssli" data-id="' + this.sid + '" data-mid="' + this.marketId + '" data-fid="' + this.floorId + '">' +
                            '<button type="button" class="sbtn delb">' + this.stallName + '</button></li>';
                    })
                    fCtn += '</ul><div class="addbtn"><button type="button" class="sbtn addb" data-fid="' + fid + '" data-mid="' + mid + '">添加</button></div>';
                }, 'json')
                listCtn += fCtn + '</div></li>';
            })
            $(listbox).html(listCtn);
            delStall();
            addStall();
        }
    }, 'json')
}
getStallManagerList();


/**
 * 删除摊位
 */
function delStall() {
    var delBtn = $('#listbox .sssli');
    $(delBtn).on('click', '.delb', function() {
        var _this = this;
        var sid = $(_this).parent().attr('data-id');
        $.post(urlInfo() + '/stall/delete', {
            'stallId': sid
        }, function(res) {
            if (res.status.code == 200) {
                $(_this).parent().remove();
            } else {
                alert(res.status.msg);
            }
        }, 'json')
    })
}

/**
 * 添加摊位
 */
function addStall() {
    var addBtn = $('#listbox .addbtn');
    $(addBtn).on('click', '.addb', function() {
        var _this = this;
        var floorId = $(_this).attr('data-fid'),
            marketId = $(_this).attr('data-mid');
        var dialogCtn = '<div class="dialog d_none"><div class="mask"></div>' +
            '<div class="dialogCtn trans"><div class="title"><p>添加摊位信息</p></div>' +
            '<div class="body"><div class="inputbox"><input type="text" class="jsonIpt" name="floor" id="floor" placeholder="请输入摊位名称"/></div>' +
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
            var floorName = $('#floor').val();
            $.post(urlInfo() + '/stall/add', {
                'floorId': floorId,
                'marketId': marketId,
                'stallName': floorName
            }, function(res) {
                if (res.status.code == 200) {
                    alert('添加成功');
                    $('.main_body').load('stallmanager.html');
                } else {
                    alert(res.status.msg);
                }
            })
            $(this).parents('.dialog').addClass('d_none');
        });
        dialogAnimation();
    })
}