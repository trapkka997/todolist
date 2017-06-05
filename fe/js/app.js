(function(window) {
    'use strict';

    loadAllView();


    $(".new-todo").keypress(function(event) {
        var todo = $(this).val();
        if (event.keyCode == 13) {
            if (todo == "") {
                alert("내용을 입력하세요.");
            } else {
                $.ajax({
                    url: "./api/todolists",
                    dataType: "json",
                    contentType: "application/json; charset=UTF-8",
                    type: 'POST',
                    data: JSON.stringify({
                        "todo": todo,
                        "completed": 0,
                        "date": new Date().valueOf()
                    })
                }).done(function(data) {
                    location.reload(true);
                }).fail(function(err) {
                    throw err;
                });
            }

        }
    });
    $(document).on("change", ".toggle", function() {
        var id = $(this).parent().parent().attr('id');
        var check = $(this).prop("checked");

        if (check) {
            $.ajax({
                url: "./api/todolists/" + id,
                dataType: "json",
                contentType: "application/json; charset=UTF-8",
                type: 'PUT',
                data: JSON.stringify({
                    "id": id,
                    "completed": 1
                })
            }).done(function(data) {
                location.reload(true);
            }).fail(function(err) {
                throw err;
            });
        }
    })

    $(document).on("click", ".destroy", function() {
        var id = $(this).parent().parent().attr('id');
        $.ajax({
            url: "./api/todolists/" + id,
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            type: 'DELETE',
            data: JSON.stringify({
                "id": id
            })
        }).done(function(data) {
            location.reload(true);
        }).fail(function(err) {

            throw err;
        });

    })

    $(document).on("click", ".clear-completed", function() {
        $.ajax({
            url: "./api/todolists/",
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            type: 'DELETE'
        }).done(function(data) {
            location.reload(true); 
        }).fail(function(err) {

            throw err;
        });
    })

    $(document).on("click", ".selected", function() {
        $('.todo-list').children().remove();
        loadAllView();
    })
    $(document).on("click", ".active", function() {
        $('.todo-list').children().remove();
        loadActiveView();
    })
    $(document).on("click", ".completed", function() {
        $('.todo-list').children().remove();
        loadCompletedView();
    })

})(window);




function loadAllView() {
    $.ajax({
        url: "./api/todolists",
        dataType: "json",
        type: 'GET'
    }).done(function(data) {
        var completes;
        var check;
        for (var i = 0; i < data.length; i++) {
            if (data[i].completed === 1) {
                completes = 'class="completed"';
                check = 'checked';
            } else {
                completes = '';
                check = '';
            }

            $('.todo-list').prepend('<li ' + completes + ' id=' + data[i].id + '>' + '<div class="view">' + '<input class="toggle" type="checkbox" ' + check + '><label>' + data[i].todo + '</label>' + '<button class="destroy"></button>' + '</div>' + '</li>');
        }
    }).fail(function(err) {
        throw err;
    });

    $.ajax({
        url: "./api/todolists/count",
        dataType: "json"
    }).done(function(data) {
        $('.todo-count strong').text(data);
    }).fail(function(err) {
        throw err;
    });
}

function loadActiveView() {
    $.ajax({
        url: "./api/todolists/active",
        dataType: "json",
        type: 'GET'
    }).done(function(data) {
        var completes;
        for (var i = 0; i < data.length; i++) {

            $('.todo-list').prepend('<li id=' + data[i].id + '>' + '<div class="view">' + '<input class="toggle" type="checkbox"><label>' + data[i].todo + '</label>' + '<button class="destroy"></button>' + '</div>' + '</li>');
        }
    }).fail(function(err) {
        throw err;
    });

    $.ajax({
        url: "./api/todolists/activecount",
        dataType: "json"
    }).done(function(data) {
        $('.todo-count strong').text(data);
    }).fail(function(err) {
        throw err;
    });

}

function loadCompletedView() {
    $.ajax({
        url: "./api/todolists/completed",
        dataType: "json",
        type: 'GET'
    }).done(function(data) {
        var completes;
        for (var i = 0; i < data.length; i++) {

            $('.todo-list').prepend('<li class="completed" id=' + data[i].id + '>' + '<div class="view">' + '<input class="toggle" type="checkbox" checked><label>' + data[i].todo + '</label>' + '<button class="destroy"></button>' + '</div>' + '</li>');
        }
    }).fail(function(err) {
        throw err;
    });

    $.ajax({
        url: "./api/todolists/completedcount",
        dataType: "json"
    }).done(function(data) {
        $('.todo-count strong').text(data);
    }).fail(function(err) {
        throw err;
    });

}