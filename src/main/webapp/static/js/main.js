$(document).ready(function () {

    $('#msg').html("This is updated by jQuery");

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
            contentType: "application/json"
        })

        // using the done promise callback
            .done(function (data) {
                //save token in cookie
                $.cookie("token", data.token);
            });

        return false;
    });
});

function getAuthHeader() {
    return "Bearer " + $.cookie("token");
}