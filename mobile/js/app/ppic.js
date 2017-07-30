function addPic() {
    var addBtn = $('#addPic'),
        hideIpt = $('#picup');
    $(addBtn).on('click', function() {
        $(hideIpt).trigger('click');
    })
}
addPic();
//上传即时显示图片
function getImg(event) {
    var file = event.files[0],
        reader = new FileReader(),
        picGroup = $('#picGroup ul');
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        var li = '<li class="f_left plis"><img src="' + this.result + '" alt=""></li>';
        $(picGroup).append(li);
        $('#picup').val('');
    };
}

function addFile() {
    var addBtn = $('#addFile'),
        hideIpt = $('#fileup');
    $(addBtn).on('click', function() {
        $(hideIpt).trigger('click');
    })
}
addFile();

function getFile(event) {
    var file = event.files[0];
    $(event).next().text('已选择' + file.name);
}