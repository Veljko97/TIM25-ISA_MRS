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
    table.html(table.html() + "<tr><td>"+ (flight.startDestinationName ? flight.startDestinationName : 'No destination')  + "</td><td>" + (flight.finalDestinationName ? flight.finalDestinationName : 'No destination') + "</td><td>" + flight.takeOffDate + "</td><td>" + flight.landingDate + "</td><td>" + flight.flightLength + "</td><td><a class=\"btn btn-info\" href=\"edit-flight.html?id=" + 
    flight.idFlight + "\">Edit</a></td><td><a class=\"btn btn-danger\" onclick=\"flights.deleteCallback(" + flight.idFlight +")\">Delete</a></td></tr>");
  }
}

Flights.prototype.validateInput = function() {
  for(var i = 0; i < this.attributes.length; i++) {
    if ($('#' + this.attributes[i]).val() == ("")) {
      return false;
    }
  }
  var regExp = new RegExp(/^\d{1,2}\/\d{1,2}\/\d{4} \d{1,2}:\d{2}$/);
  if (!regExp.test($('#landingDate').val())) { 
    $('#landingDate').attr('style', 'border-color: red;');
    setTimeout(() => {$('#landingDate').attr('style', 'border-color: none;');}, 5000);
  }
  if (!regExp.test($('#takeOffDate').val())) {
    $('#takeOffDate').attr('style', 'border-color: red;');
    setTimeout(() => {$('#takeOffDate').attr('style', 'border-color: none;');}, 5000);
  }
  return regExp.test($('#landingDate').val()) && regExp.test($('#takeOffDate').val());
}

Flights.prototype.showCallback = function(flight) {
  if (flight) {
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      var inputName = input.attr("id");
      input.attr("value", flight[inputName]);
    }

    var sourceChildren = $('#startDestinationName').children();
    var finalChildren = $("#finalDestinationName").children();
    
    for(var i = 0; i < sourceChildren.length; i++) {
      if(sourceChildren.eq(i).val() === flight.startDestinationName) {
        sourceChildren.eq(i).attr('selected', 'selected');
      }
    }

    for (var i = 0; i < finalChildren.length; i++) {
      if (finalChildren.eq(i).val() === flight.finalDestinationName) {
        finalChildren.eq(i).attr('selected', 'selected');
      }
    }
  }
  this.urlApi.edit = '/app/airlines/1/editFlight/' + flight.idFlight;
  $(document).on('submit', '#editFlightForm', this.editCallback.bind(this));
}

Flights.prototype.show = function(index) {
  ajaxService.GET(this.urlApi.showDestinations, this.showDestinations.bind(this));
  ajaxService.GET('/app/airlines/1/getFlight/' + index, this.showCallback.bind(this));
}

Flights.prototype.render = function() {
  ajaxService.GET(this.urlApi.showAll, this.showAll.bind(this));
  ajaxService.GET(this.urlApi.showDestinations, this.showDestinations.bind(this));
}

Flights.prototype.showDestinations = function(data) {
  var startDestinations = $("#startDestinationName").first();
  var finalDestinations = $("#finalDestinationName").first();

  for(var i = 0; i < data.length; i++) {
    var destination = data[i];
    startDestinations.html(startDestinations.html() + "<option value=\""+destination.destinationName+"\">"+destination.destinationName+"</option>");
    finalDestinations.html(finalDestinations.html() + "<option value=\""+destination.destinationName+"\">"+destination.destinationName+"</option>");
  }
}

var flights = new Flights(['startDestinationName', 'finalDestinationName', 'takeOffDate', 'landingDate', 'flightLength', 'numberOfStops', 'numberOfSeats', 'stopLocation', 'ticketPrice'], {'add': '/app/airlines/1/addFlight', 'showAll': '/app/airlines/1/showFlights/', 'delete': '/app/airlines/1/deleteFlight/', 'showDestinations': '/app/airlines/1/showDestinations/'});