function Flights(attributes, addUrlApi, showUrlApi) {
  Model.call(this, attributes, addUrlApi, showUrlApi);
}

Flights.prototype = Object.create( Model.prototype );

Flights.prototype.bindEvents = function() {
  $(document).on('submit', "#flightForm", this.addCallback.bind(this));
}

Flights.prototype.showAll = function(data) {
  var table = $("#flightsTable").first();
  table.html("<tr><th>Start Destination</th><th>End Destination</th><th>Take off Date</th><th>Landing Date</th><th>Flight Length</th></tr>");
  this.list = [];

  for(var i = 0; i < data.length; i++) {
    var flight = data[i];
    this.list.push(flight);
    table.html(table.html() + "<tr><td>"+ flight.startDestination.destinationName + "</td><td>" + flight.finalDestination.destinationName + "</td><td>" + flight.takeOffDate + "</td><td>" + flight.landingDate + "</td><td>" + flight.flightLength + "</td></tr>");
  }
} 

var flights = new Flights(['startDestinationName', 'finalDestinationName', 'takeOffDate', 'landingDate', 'flightLength', 'numberOfStops', 'stopLocation', 'ticketPrice'], '/app/flight/addFlight', '/app/flight/showFlights');