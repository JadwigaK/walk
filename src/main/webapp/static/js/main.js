$(document).ready(function () {

    //check if browser remembers last session
    if ($.cookie("token")) {
        $('#navLogin').css('display', 'none');
        $('#navLogout').css('display', 'block');
    }

    $('#loginform').submit(function (event) {
        /* stop form from submitting normally */
        event.preventDefault();
        var data = {};
        data['username'] = $('#username').val();
        data['password'] = $('#password').val();

        // process the form
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: 'login', // the url where we want to POST
            data: JSON.stringify(data),
            dataType: 'json', // what type of data do we expect back from the server
            contentType: "application/json",
            success: function (response) {
                //save token in cookie
                $.cookie("token", response.token);
                //remove errors just in case
                $('#loginform').removeClass("has-error");
                $('#loginError').remove();
                //change login to logout in navbar
                $('#navLogin').css('display', 'none');
                $('#navLogout').css('display', 'block');
                //quit modal
                $('#loginmodal').modal('toggle');
            },
            error: function (request, status, error) {
                $('#loginform').addClass("has-error");
                $('#loginform').after('<p class="text-danger" id="loginError">' + request.responseText + '</p>');
            }
        })
        return false;
    });

    $("#navLogout").click(function () {
        $.cookie("token", null);
        //switch logout to login in navbar
        $('#navLogout').css('display', 'none');
        $('#navLogin').css('display', 'block');
    });

    $('#msg').html("This is updated by jQuery");

    //just for simple interaction
    $("#btn1").click(function () {

        $.ajax({
            type: 'GET', // define the type of HTTP verb we want to use (POST for our form)
            url: '/api/walks', // the url where we want to POST
            dataType: 'json', // what type of data do we expect back from the server
            contentType: "application/json",
            beforeSend: function (request) {
                request.setRequestHeader("X-Authorization", getAuthHeader());
            }
        })
        // using the done promise callback
            .done(function (data) {
                alert("Data: " + data[0].name + "\nStatus: " + status);
            });
    });
});

function getAuthHeader() {
    return "Bearer " + $.cookie("token");
}