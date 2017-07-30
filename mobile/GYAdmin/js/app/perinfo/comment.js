$(function() {
    // $('.comment_body').height($('body').height() - 168);
});
//添加评论
function addComment() {
    var items = $('.commentItem'),
        ctnBox = $('.main_body');
    $.each(items, function(key, data) {
        $(data).on('click', '.c_header i', function() {
            $(ctnBox).load('../perInfo/commentAdd.html');
        })
    })
}
addComment();