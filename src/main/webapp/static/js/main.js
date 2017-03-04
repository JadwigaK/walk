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

    $('#signform').submit(function (event) {
        /* stop form from submitting normally */
        event.preventDefault();
        var data = {};
        data['email'] = $('#usernameS').val();
        data['password'] = $('#passwordS').val();

        // process the form
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: '/home/register', // the url where we want to POST
            data: JSON.stringify(data),
            dataType: 'json', // what type of data do we expect back from the server
            contentType: "application/json",
            success: function (response) {
                //quit modal
                $('#signmodal').modal('toggle');
            }
        })
            .done(function (data) {
                alert("User saved. Now you can login.");
            });
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

    $('#savewalk').submit(function (event) {
        event.preventDefault();
        var walk = {};
        walk['name'] = $('#walkname').val();
        for (i = 1; i < locations.length+1; i++) {
            waypoints.push({pointname: $('#pointname'+i).val(), description: $('#description'+i).val(), longitude: $('#longitude'+i).val(), latitude: $('#latitude'+i).val() });
        }

        walk['waypoint'] = waypoints;
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: 'api/users/77/walks', // the url where we want to POST
            data: JSON.stringify(walk),
            dataType: 'json', // what type of data do we expect back from the server
            contentType: "application/json",
            beforeSend: function (request) {
                request.setRequestHeader("X-Authorization", getAuthHeader());
            }
        })
        // using the done promise callback
            .done(function (data) {
                alert("Walk saved");
            });
    });

    $("#addwalk").click(function () {
        $(window).trigger('hashchange');
    });

    $("#mywalks").click(function () {
        $(window).trigger('hashchange');
    });

    $(window).on('hashchange', function(){
        // On every hash change the render function is called with the new hash.
        // This is how the navigation of our app happens.
        render(decodeURI(window.location.hash));
    });
});

function getAuthHeader() {
    return "Bearer " + $.cookie("token");
}

var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
var labelIndex = 0;
var locations = [];

function myMap() {
    var mapCanvas = document.getElementById("map");
    var mapOptions = {
        center: new google.maps.LatLng(51.5, -0.2), zoom: 10
    };
    var map = new google.maps.Map(mapCanvas, mapOptions);
}

function initMap() {
    var canvas = document.getElementById("map");
    var mapOptions = {
        center: new google.maps.LatLng(50.060, 19.959), zoom: 12
    };
    var map = new google.maps.Map(canvas, mapOptions);

    // This event listener calls addMarker() when the map is clicked.
    google.maps.event.addListener(map, 'click', function(event) {
        addMarker(event.latLng, map);
    });
}

// Adds a marker to the map.
function addMarker(location, map) {
    // Add the marker at the clicked location, and add the next-available label
    // from the array of alphabetical characters.

    var marker = new google.maps.Marker({
        position: location,
        label: labels[labelIndex++ % labels.length],
        map: map
    });
    locations.push({lat: marker.getPosition().lat(), lng: marker.getPosition().lng()});
    addInput(marker.getPosition().lat(), marker.getPosition().lng());
}

function render(url) {
    // Get the keyword from the url.
    var temp = url.split('/')[0];

    var map1 = {

        // The Homepage.
        '': function() {
            $('.container1').hide();
        },

        // Single Products page.
        '#addwalk': function() {
            renderAddWalkPage();
        }
    };

    // Execute the needed function depending on the url keyword (stored in temp).
    if(map1[temp]){
        map1[temp]();
    }
}

function renderAddWalkPage(){
    // Hides and shows products in the All Products Page depending on the data it recieves.
    $('.container1').show();
}


