/**
 * 设置公司内容
 */
function setCompanyInfo() {
    $('#save').on('click', function() {
        var meditObj = medit(document.getElementById("meditContent"), document.getElementById("meditToolBar"));
        var content = meditObj.getContent();
        $('#content').val(content);
        var formDate = $('#saveForm').serialize();
        $.post(urlInfo() + '/admin/config/setintro', formDate, function(res) {
            if (res.status.code == 200) {
                alert('添加成功');
                location.href = 'index.html';
            } else alert('添加失败');
        }, 'json');
    })
}
setCompanyInfo();

/**
 * 获取简介类型
 */
function getCompanyType() {
    var typebox = $('#id'),
        typeCtn = '';
    $.get(urlInfo() + '/config/introlist', function(res) {
        $.each(res.data, function(i) {
            typeCtn += '<option value="' + this.id + '">' + this.typename + '</option>';
        })
        $(typebox).html(typeCtn);
    })
}
getCompanyType()