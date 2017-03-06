/**
 * Created by OEM on 04.03.2017.
 */
var counter = 1;
var limit = 5;
var waypoints = [];
function addInput(lat, lng) {
    if (counter == limit) {
        alert("You have reached the limit of adding " + counter + " inputs");
    }
    else {
        var template = $('#dynamicInput').clone().prop('id', 'dynamicInput'+counter ).css("display","inline-block");
        $('#waypointList').append(template);
        $('#dynamicInput'+counter).find("#longitude").val(lng);
        $('#dynamicInput'+counter).find("#latitude").val(lat);
        counter++;
    }
}