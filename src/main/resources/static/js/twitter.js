var curUser = {};

$(document).ready(function () {
    if ($("meta[name='_csrf']").length) { // if csrf is enabled
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }

    $.post('/api/getMyself').done(function (data) {
        curUser = data;
        $("#herename").text(curUser.username);
        $("#heregravatar").attr('src', curUser.gravatarUrl);
        loadMore();
    });

    $("#text").on('keyup', function (e) {
        if (e.keyCode == 13) {
            addMessage();
        }
    });
});

function addMessage() {
    var text = $("#text").val();
    $.post('/api/addMessage', {'text': text})
        .done(function (result) {
            if (result === 'ok') {
                $("#text").val('');
                $('.feed').empty();
                $('#loadMore').show();
                loadMore();
            } else {
                if (result !== 'error') {
                    // session problem
                    location.reload();
                }
            }
        });
}

function loadMore() {
    var params = {count: 10};
    var lastChild = $(".message:last");
    if (lastChild.length) {
        params['maxID'] = lastChild.attr('data-id') - 1;
    }

    $.post('/api/getMessages', params)
        .done(function (data) {
            if (!data.length) {
                $('#loadMore').hide();
            }
            data.forEach(function (message) {
                $(".feed").append(markupMessage(message));
            });
        });
}

function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function getTime(d) {
    var h = addZero(d.getHours());
    var m = addZero(d.getMinutes());
    var s = addZero(d.getSeconds());
    return h + ":" + m + ":" + s;
}

function markupMessage(message) {
    var date = new Date(message.date);
    var dateStr = date.getDate() + '.' + (date.getMonth() + 1) + '.' + date.getFullYear();
    var timeStr = getTime(date);

    var removeButton = message.author.id === curUser.id ?
    '<button type="button" class="btn btn-xs btn-danger left10" onclick="removeMessage(this, ' + message.id + ');">✖</button>' : '';

    return '<div class="row top10 message" data-id="' + message.id + '">' +
        '<div class="col-xs-7">' + message.message + removeButton + '</div>' +
        '<div class="col-xs-1"><img src="' + message.author.gravatarUrl + '" width=30 height=30></div>' +
        '<div class="col-xs-2"><span id="herename" class="label label-info">' + message.author.username + '</span></div>' +
        '<div class="col-xs-2">' + dateStr + ' в ' + timeStr + '</div>' +
        '</div>';
}

function removeMessage(btn, id) {
    $.post('/api/removeMessage', {'id': id}).done(function (data) {
        if (data == 'ok') {
            $(btn).parent().parent().slideUp(300);
        }
    });
}
