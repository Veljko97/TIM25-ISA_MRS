function Stops() {

}

Stops.prototype = Object.create( Model.prototype );

Stops.prototype.render = function() {
  ajaxService.GET('/app/airlines/' + getUserServiceId() + '/showDestinations', this.showDestinations.bind(this));
}

Stops.prototype.addStopLocation = function(e) {
  e.preventDefault();
  ajaxService.POST('/app/airlines/' + getUserServiceId() + '/addStopLocation/' + this.flightId, JSON.stringify($("#destination").val()), function() {
    window.location.replace('/admin-airlines/index.html');
  }, function() {
    alert('Something went wrong! Please try again.');
  });
}

Stops.prototype.showDestinations = function(data) {
  var destinations = $("#destination").first();

  destinations.html("");

  for(var i = 0; i < data.content.length; i++) {
    var destination = data.content[i];
    destinations.html(destinations.html() + "<option value=\""+destination.idDestination+"\">"+destination.destinationName+"</option>");
  }
}
var stops = new Stops();