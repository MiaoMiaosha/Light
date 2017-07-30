//点击table进行编辑
function editTable() {
    var editBox = $('.table .edit-box');
    $(editBox).on('click', function() {
        var _this = this,
            ipts = $(_this).find('.ipts'),
            defaultValut = $(_this).find('span');
        $(ipts).removeClass('d_none').siblings('span').addClass('d_none');
        if ($(defaultValut).text() == '点此输入' || $(defaultValut).text() == '') {
            $(_this).find('.ipts').val('').focus();
        } else {
            $(_this).find('.ipts').val($(defaultValut).text()).focus();
        }
        //失去焦点 获取值 赋值span
        $(ipts).blur(function() {
            var _iptthis = this,
                iptVal = $(_iptthis).val();
            if (iptVal == '') {
                $(_iptthis).addClass('d_none').siblings('span').removeClass('d_none').text('');
            } else {
                $(_iptthis).addClass('d_none').siblings('span').removeClass('d_none').text(iptVal);
            }

        })
    })
}
editTable();