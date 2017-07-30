/**
 * 设置水印
 */
function setWaterMarke() {
    $('#save').on('click', function() {
        var formDate = $('#saveForm').serialize();
        $.get(urlInfo() + '/watermark/edit', formDate, function(res) {
            if (res.status.code == 200) {
                alert('添加成功');
                location.href = 'index.html';
            } else {
                alert(res.status.msg)
            }
        }, 'json')
    })
}
setWaterMarke()