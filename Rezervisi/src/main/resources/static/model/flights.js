function Flights(attributes, urlApi) {
  Model.call(this, attributes, urlApi);
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
    table.html(table.html() + "<tr><td>"+ (flight.startDestination ? flight.startDestination.destinationName : 'No destination')  + "</td><td>" + (flight.finalDestination ? flight.finalDestination.destinationName : 'No destination') + "</td><td>" + flight.takeOffDate + "</td><td>" + flight.landingDate + "</td><td>" + flight.flightLength + "</td><td><a class=\"btn btn-info\" href=\"edit-flight.html?id=" + 
    flight.idFlight + "\">Edit</a></td><td><a class=\"btn btn-danger\" onclick=\"flights.deleteCallback(" + flight.idFlight +")\">Delete</a></td></tr>");
  }
}

Flights.prototype.showCallback = function(flight) {
  if (flight) {
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      var inputName = input.attr("id");
      input.attr("value", flight[inputName]);
    }
  }
  this.urlApi.edit = '/app/airlines/1/editFlight/' + flight.idFlight;
  $(document).on('submit', '#editFlightForm', this.editCallback.bind(this));
}

Flights.prototype.show = function(index) {
  ajaxService.GET('/app/airlines/1/getFlight/' + index, this.showCallback.bind(this));
}

var flights = new Flights(['startDestinationName', 'finalDestinationName', 'takeOffDate', 'landingDate', 'flightLength', 'numberOfStops', 'numberOfSeats', 'stopLocation', 'ticketPrice'], {'add': '/app/airlines/1/addFlight', 'showAll': '/app/airlines/1/showFlights/', 'delete': '/app/airlines/1/deleteFlight/'});