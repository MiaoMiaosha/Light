function addFile() {
    var addBtn = $('#addFile'),
        hideIpt = $('#fileup');
    $(addBtn).on('click', function() {
        $(hideIpt).trigger('click');
    })
}
addFile();
//上传即时显示图片
function getFile(event) {
    var file = event.files[0];
    $(event).next().text('已选择' + file.name);
}