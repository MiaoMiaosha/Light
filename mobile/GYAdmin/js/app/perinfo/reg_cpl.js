$(function() {
    $('.reg_body').css('min-height', $('html').height() - 168);
});

function addPic() {
    var addBtn = $('#userImg'),
        hideIpt = $('#fileUser');
    $(addBtn).on('click', function() {
        $(hideIpt).trigger('click');
    })
}
addPic();
//上传即时显示图片
function upUserImg(event) {
    var file = event.files[0],
        reader = new FileReader(),
        picGroup = $('#userImg');
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        $(picGroup).attr('src', this.result);
        $('#fileUser').val('');
    };
}