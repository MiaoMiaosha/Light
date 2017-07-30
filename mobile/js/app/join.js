$(function() {
    getCbk();
})

function getProvince() {
    var pBox = $('#province'),
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
        cityBox = $('#city'),
        cityCtn = '';
    $('#cityJoinBox').removeClass('d_none');
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
        townBox = $('#district'),
        townCtn = '';
    $('#townJoinBox').removeClass('d_none');
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

/**
 * 获取选中的公司主营内容 多个id以:隔开
 */
function getMainCompany() {
    var mBox = $('#mainZhuying'),
        mCtn = "";
    $.get(urlInfo() + '/cooperate/buslist', function(res) {
        $.each(res.data, function() {
            mCtn += '<li class="f_left cbklis"><label><input type="checkbox" class="cbk" name="" id="' + this.mbid + '">' + this.mainBusinessName +
                '</label></li>';
        })
        $(mBox).html(mCtn);
    })
}
getMainCompany();

/**
 * 提交加盟
 */
function addJoin() {
    $('#joinBtn').on('click', function() {
        var chkedId = $('.com_cbkAll').find('.cbk:checked'),
            cIds = "";
        for (var i = 0; i < chkedId.length; i++) {
            cIds += $(chkedId[i]).attr('id') + ':';
        }
        cIds = cIds.substring(0, cIds.length - 1);
        $('#mainBusiness').val(cIds);
        $('#joinForm').find('input[name=userId]').val(getCookie('wxUserId'))
        var formData = $('#joinForm').serialize();
        $.post(urlInfo() + '/cooperate/publish', formData, function(res) {
            if (res.status.code == 200) {
                alert('申请成功');
                location.href = 'perInfo/index.html';
            } else {
                alert(res.status.msg);
            }
        }, 'json')
    })
}
addJoin();