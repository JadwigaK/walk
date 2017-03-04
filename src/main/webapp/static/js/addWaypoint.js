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
        var template = $('#dynamicInput').html();
        $('form.waypointsave').find("longitude").val(lng);
        $('form.waypointsave').find("latitude").val(lat);
        $('#planner').append(template);
        counter++;
    }
}