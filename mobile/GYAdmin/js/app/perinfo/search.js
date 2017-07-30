$(function() {
    $('.search_body .ctn').height($('html').height() - 208);
})

//获取热门搜索关键字
function getHotWords() {
    var keywordsBox = $('#words'),
        hotkeys = '';
    $.get(urlInfo() + '/light/index/hotwords', function(res) {
        $.each(res.data.wordlist, function(key, data) {
            hotkeys += '<div class="col-xs-3"><p>' + data + '</p></div>';
        });
        $(keywordsBox).html(hotkeys);
    }, 'json');
}
getHotWords();