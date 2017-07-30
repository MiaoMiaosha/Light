$(function() {
    // $('.search_body .ctn').height($('html').height() - 208);
})

//获取热门搜索关键字
function getHotWords() {
    var keywordsBox = $('#words'),
        hotkeys = '';
    $.get(urlInfo() + '/hotwords', function(res) {
        $.each(res.data, function(key, data) {
            hotkeys += '<div class="col-xs-3 hw" data-cid="' + data.contentId +
                '" data-type="' + data.type + '"><p>' + data.content + '</p></div>';
        });
        $(keywordsBox).html(hotkeys);
        searchByHotWords();
    }, 'json');
}
getHotWords();


/**
 * 搜索结果
 */
function showSearchResult() {
    var searchBox = $('.searchBox');
    $(searchBox).on('click', '.icon-search', function(e) {
        e.stopPropagation();
        var _this = this;
        var value = $(_this).siblings('#search').val(),
            type = $(_this).siblings('#searchtype').val();
        if (type == '') {
            alert('请输入关键字搜索');
        } else {
            // $(_this).siblings('#search').val('');
            var showResultBox = $('#showResult'),
                showResultCtn = '';
            $('.loadMore').find('button').prop('disabled', false);
            if (type == 0) {
                $.post(urlInfo() + '/search', {
                    'key': value
                }, function(res) {
                    if (res.data.cooperateList.length == 0 && res.data.goodsList.length == 0 &&
                        res.data.newsList.length == 0 && res.data.marketList.length == 0 &&
                        res.data.caseList.length == 0) {
                        $('.loadMore').addClass('d_none');
                        alert('没有相关数据');
                        // showResultCtn = '<p class="t_center">没有相关数据</p>';
                        // showResultBox.html(showResultCtn);
                    } else {
                        if (res.data.cooperateList.length != 0) {
                            $.each(res.data.cooperateList, function(i) {
                                showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                                    '" data-type="' + this.type + '" data-href="join"><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                            })
                        }
                        if (res.data.goodsList.length != 0) {
                            $.each(res.data.goodsList, function(i) {
                                showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                                    '" data-type="' + this.type + '" data-go="devicesDc?goodsId=' + this.contentId +
                                    '"><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                            })
                        }
                        // if (res.data.newsList.length != 0) {
                        //     $.each(res.data.newsList, function(i) {
                        //         showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                        //             '" data-type="' + this.type + '"><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                        //     })
                        // }
                        if (res.data.marketList.length != 0) {
                            $.each(res.data.marketList, function(i) {
                                showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                                    '" data-type="' + this.type + '" data-go="marketDc?marketId=' + this.contentId +
                                    '"><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                            })
                        }
                        if (res.data.caseList.length != 0) {
                            $.each(res.data.caseList, function(i) {
                                showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                                    '" data-type="' + this.type + '" data-go="caseDc?caseId=' + this.contentId +
                                    '"><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                            })
                        }
                        $('.loadMore').addClass('d_none');
                        // $('.loadMore').removeClass('d_none');
                        showResultBox.html(showResultCtn);
                        goDetails();
                    }
                }, 'json');
            } else {
                $.post(urlInfo() + '/search/other', {
                    'key': value,
                    'type': type
                }, function(res) {
                    if (res.data.length == 0) {
                        $('.loadMore').addClass('d_none');
                        showResultCtn = '<p class="t_center">没有相关数据</p>';
                        showResultBox.html(showResultCtn);
                    } else {
                        $.each(res.data, function(i) {
                            var typeC = '';
                            if (this.type == 1) {
                                typeC = 'data-go="caseDc?caseId=' + this.contentId + '"';
                            } else if (this.type == 2) {
                                typeC = 'data-go="marketDc?marketId=' + this.contentId + '"';
                            } else if (this.type == 3) {
                                typeC = 'data-go="devicesDc?goodsId=' + this.contentId + '"';
                            } else {
                                typeC = '';
                            }
                            showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                                '" data-type="' + this.type + '"  ' + typeC + '><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                        })
                        $('.loadMore').removeClass('d_none');
                        showResultBox.html(showResultCtn);
                        goDetails();
                        setCookie('curPage', res.status.curPage, 1);
                    }
                }, 'json');
            }
        }

    })
}
showSearchResult();


/**
 * 将搜索的关键字变色
 */
function showSpecialWord(word, targetWord) {
    if (targetWord.indexOf(word) != -1) {
        var change = new RegExp(word, 'g');
        targetWord = targetWord.replace(change, '<span style="color:#fe0000">' + word + '</span>');
        return targetWord;
    }
}

/**
 * 加载更多
 */
function loadMoreSearchResult() {
    var loadbtn = $('.loadMore');
    $(loadbtn).on('click', function() {
        var _this = this;
        var curPage = parseInt(getCookie('curPage')) + 1;
        var value = $('#search').val(),
            type = $('#searchtype').val();
        var showResultBox = $('#showResult'),
            showResultCtn = '';
        $.post(urlInfo() + '/search/other', {
            'key': value,
            'type': type,
            'page': curPage
        }, function(res) {
            if (res.data.length == 0) {
                $(_this).find('button').text('没有更多数据了').prop('disabled', true);
            } else {
                $.each(res.data, function(i) {
                    var typeC = '';
                    if (this.type == 1) {
                        typeC = 'data-go="caseDc?caseId=' + this.contentId + '"';
                    } else if (this.type == 2) {
                        typeC = 'data-go="marketDc?marketId=' + this.contentId + '"';
                    } else if (this.type == 3) {
                        typeC = 'data-go="devicesDc?goodsId=' + this.contentId + '"';
                    } else {
                        typeC = '';
                    }
                    showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                        '" data-type="' + this.type + '" ' + typeC + '><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                })
                $('.loadMore').removeClass('d_none');
                showResultBox.append(showResultCtn);
                setCookie('curPage', res.status.curPage, 1);
                goDetails();
            }
        }, 'json');
    })
}
loadMoreSearchResult();

/**
 * 去对应的详情
 */
function goDetails() {
    var item = $('#showResult .resultItem'),
        ctnBox = $('.main_body');
    $.each(item, function(key, data) {
        $(data).on('click', function() {
            if (typeof $(this).attr('data-go') != 'undefined') {
                var href = $(this).attr('data-go').split('?')[0],
                    Id = $(this).attr('data-go').split('=')[1];
                if (href == 'caseDc') {
                    setCookie('caseId', Id, 1);
                } else if (href == 'devicesDc') {
                    setCookie('goodsId', Id, 1);
                } else if (href == 'marketDc') {
                    setCookie('marketId', Id, 1);
                }
                ctnBox.load(href + '.html');
            } else if (typeof $(this).attr('data-href') != 'undefined') {
                setCookie('targetHref', $(this.attr('data-href')), 1);
                ctnBox.load('case.html');
            }

            //需要保存一个值 用于其他页面使用
            // setCookie('caseId', caseId, 1);
        })
    })
}

/**
 * 点击热词搜索
 */
function searchByHotWords() {
    var wordsBox = $('#words');
    $(wordsBox).on('click', '.hw', function() {
        var _this = this;
        var value = $(_this).text(),
            type = $(_this).attr('data-type');
        var showResultBox = $('#showResult'),
            showResultCtn = '';
        $('.loadMore').find('button').prop('disabled', false);
        $('#searchtype').val(type);
        if (type == 0) {
            $.post(urlInfo() + '/search', {
                'key': value
            }, function(res) {
                if (res.data.cooperateList.length == 0 && res.data.goodsList.length == 0 &&
                    res.data.newsList.length == 0 && res.data.marketList.length == 0 &&
                    res.data.caseList.length == 0) {
                    $('.loadMore').addClass('d_none');
                    showResultCtn = '<p class="t_center">没有相关数据</p>';
                    showResultBox.html(showResultCtn);
                } else {
                    if (res.data.cooperateList.length != 0) {
                        $.each(res.data.cooperateList, function(i) {
                            showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                                '" data-type="' + this.type + '"><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                        })
                    }
                    if (res.data.goodsList.length != 0) {
                        $.each(res.data.goodsList, function(i) {
                            showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                                '" data-type="' + this.type + '" data-go="devicesDc?goodsId=' + this.contentId +
                                '"><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                        })
                    }
                    // if (res.data.newsList.length != 0) {
                    //     $.each(res.data.newsList, function(i) {
                    //         showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                    //             '" data-type="' + this.type + '"><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                    //     })
                    // }
                    if (res.data.marketList.length != 0) {
                        $.each(res.data.marketList, function(i) {
                            showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                                '" data-type="' + this.type + '" data-go="marketDc?marketId=' + this.contentId +
                                '"><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                        })
                    }
                    if (res.data.caseList.length != 0) {
                        $.each(res.data.caseList, function(i) {
                            showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                                '" data-type="' + this.type + '" data-go="caseDc?caseId=' + this.contentId +
                                '"><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                        })
                    }
                    $('.loadMore').addClass('d_none');
                    // $('.loadMore').removeClass('d_none');
                    showResultBox.html(showResultCtn);
                    goDetails();
                }
            }, 'json');
        } else {
            $.post(urlInfo() + '/search/other', {
                'key': value,
                'type': type
            }, function(res) {
                if (res.data.length == 0) {
                    $('.loadMore').addClass('d_none');
                    showResultCtn = '<p class="t_center">没有相关数据</p>';
                    showResultBox.html(showResultCtn);
                } else {
                    $.each(res.data, function(i) {
                        var typeC = '';
                        if (this.type == 1) {
                            typeC = 'data-go="caseDc?caseId=' + this.contentId + '"';
                        } else if (this.type == 2) {
                            typeC = 'data-go="marketDc?marketId=' + this.contentId + '"';
                        } else if (this.type == 3) {
                            typeC = 'data-go="devicesDc?goodsId=' + this.contentId + '"';
                        } else {
                            typeC = '';
                        }
                        showResultCtn += '<div class="col-xs-12 resultItem" data-cid="' + this.contentId +
                            '" data-type="' + this.type + '"  ' + typeC + '><h3>' + showSpecialWord(value, this.content) + '</h3></div>';
                    })
                    $('.loadMore').removeClass('d_none');
                    showResultBox.html(showResultCtn);
                    goDetails();
                    setCookie('curPage', res.status.curPage, 1);
                }
            }, 'json');
        }
    })
}