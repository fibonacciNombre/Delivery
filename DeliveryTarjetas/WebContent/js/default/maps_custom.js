
    function initialize_map() {
        var var_location = new google.maps.LatLng(-12.091302,-77.02837);

        var var_mapoptions = {
          center: var_location,
          zoom: 14
        };

        var var_marker = new google.maps.Marker({
            position: var_location,
            map: var_map,
            title:"Lima"});

        var var_map = new google.maps.Map(document.getElementById("map-container"),
            var_mapoptions);

        var_marker.setMap(var_map);

    }

$(document).ready(function(){

    initialize_map();

});