function Flights(attributes, urlApi) {
  Model.call(this, attributes, urlApi);
}

Flights.prototype = Object.create( Model.prototype );

Flights.prototype.bindEvents = function() {
  $(document).on('submit', "#flightForm", this.addCallback.bind(this));
}

Flights.prototype.makeJSONObject = function() {
  var object = {}
  for(var i = 0; i < this.attributes.length; i++) {
    if(this.attributes[i] == "takeOffDate" || this.attributes[i] == "landingDate") {
      object[this.attributes[i]] = Date.parse($('#' + this.attributes[i]).val());
    }else{
      object[this.attributes[i]] = $('#' + this.attributes[i]).val() || $('#' + this.attributes[i]).first().val();
    }
  }
  return JSON.stringify(object);
}

Flights.prototype.addCallback = function(e) {
  e.preventDefault();

  if (!this.validateInput()) {
    return handleWrongInput();
  }
  var obj = this.makeJSONObject();
  ajaxService.POST(this.urlApi.add + '?page=' + this.currentPage + "&size=" + this.pageSize, obj, this.showAll.bind(this));
}

Flights.prototype.showAll = function(data) {
  var table = $("#flightsTable").first();
  table.html("<tr><th>Start Destination</th><th>End Destination</th><th>Take off Date</th><th>Landing Date</th><th>Flight Length</th></tr>");
  this.list = [];

  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  for(var i = 0; i < data.length; i++) {
    var flight = data[i];
    this.list.push(flight);
    table.html(table.html() + "<tr><td>"+ (flight.startDestinationName ? flight.startDestinationName : 'No destination')  + "</td><td>" + (flight.finalDestinationName ? flight.finalDestinationName : 'No destination')
     + "</td><td>" + (new Date(flight.takeOffDate)).toDateString() + "</td><td>" + (new Date(flight.landingDate)).toDateString() + "</td><td>" + flight.flightLength + "</td><td><a class=\"btn btn-info\" href=\"edit-flight.html?id=" + 
    flight.idFlight + "\">Edit</a></td></td><td><a class=\"btn btn-info\" href=\"addStopDestination.html?id=" + 
    flight.idFlight + "\">Add stop destination</a></td></td><td><a class=\"btn btn-danger\" onclick=\"flights.deleteCallback(" + flight.idFlight +")\">Delete</a></td></tr>");
  }
}

Flights.prototype.validateInput = function() {
  return true;
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
      if(inputName == "takeOffDate" || inputName == "landingDate"){
        input.attr("value", toDatetimeLocal(new Date(flight[inputName])));
      }else {
        input.attr("value", flight[inputName]);
      }
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
  this.urlApi.edit = '/app/airlines/'+getUserServiceId()+'/editFlight/' + flight.idFlight;
  $(document).on('submit', '#editFlightForm', this.editCallback.bind(this));
}

Flights.prototype.show = function(index) {
  ajaxService.GET(this.urlApi.showDestinations, this.showDestinations.bind(this));
  ajaxService.GET(this.urlApi.showAirplanes, this.showAirplanes.bind(this));
  ajaxService.GET('/app/airlines/'+getUserServiceId()+'/getFlight/' + index, this.showCallback.bind(this));
}

Flights.prototype.render = function() {
  ajaxService.GET(this.urlApi.showAll + '?size='+this.pageSize+'&page=' + this.currentPage, this.showAll.bind(this));
  ajaxService.GET(this.urlApi.showDestinations, this.showDestinations.bind(this));
  ajaxService.GET(this.urlApi.showAirplanes, this.showAirplanes.bind(this));
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

Flights.prototype.showAirplanes = function(data) {
  var airplanes = $("#airplane").first();

  for(var i = 0; i < data.length; i++) {
    var airplane = data[i];
    airplanes.html(airplanes.html() + "<option value=\""+airplane.name+"\">"+airplane.name+"</option>");
  }
}

Flights.prototype.showSeats = function(airlineId, flightId) {
  ajaxService.GET('/app/airlines/'+airlineId+'/getFlight/' + flightId, function(flight) {
    $("h1").first().html(flight.airplane);
    let numberOfSeats = flight.numberOfSeats;
    var cockpit = $("#cockpit").first();
    var numberOfRows = numberOfSeats / 6;

    for(let i = 1; i <= numberOfRows; i++) {
      cockpit.html(cockpit.html() + "\
        <li class=\"row row--"+i+"\">\
          <ol class=\"seats\" type=\"A\">\
            <li class=\"seat\">\
              <input onclick=\"modal.show(event, '"+i+"A');\" " + (flight.seats.includes(i + 'A') ? 'disabled' : '') +" type=\"checkbox\" id=\""+i+"A\"/>\
              <label for=\""+i+"A\">"+i+"A</label>\
            </li>\
            <li class=\"seat\">\
              <input onclick=\"modal.show(event, '"+i+"B');\" " + (flight.seats.includes(i + 'B') ? 'disabled' : '') +" type=\"checkbox\" id=\""+i+"B\"/>\
              <label for=\""+i+"B\">"+i+"B</label>\
            </li>\
            <li class=\"seat\">\
              <input onclick=\"modal.show(event, '"+i+"C');\" " + (flight.seats.includes(i + 'C') ? 'disabled' : '') +" type=\"checkbox\" id=\""+i+"C\"/>\
              <label for=\""+i+"C\">"+i+"C</label>\
            </li>\
            <li class=\"seat\">\
              <input onclick=\"modal.show(event, '"+i+"D');\" " + (flight.seats.includes(i + 'D') ? 'disabled' : '') +" type=\"checkbox\" id=\""+i+"D\"/>\
              <label for=\""+i+"D\">"+i+"D</label>\
            </li>\
            <li class=\"seat\">\
              <input onclick=\"modal.show(event, '"+i+"E');\" " + (flight.seats.includes(i + 'E') ? 'disabled' : '') +" type=\"checkbox\" id=\""+i+"E\"/>\
              <label for=\""+i+"E\">"+i+"E</label>\
            </li>\
            <li class=\"seat\">\
              <input onclick=\"modal.show(event, '"+i+"F');\" " + (flight.seats.includes(i + 'F') ? 'disabled' : '') +" type=\"checkbox\" id=\""+i+"F\"/>\
              <label for=\""+i+"F\">"+i+"F</label>\
            </li>\
          </ol>\
        </li>")
    }
  });
}

var flights = new Flights(
  ['startDestinationName', 'finalDestinationName', 'takeOffDate', 'landingDate', 'flightClass', 'type', 'flightLength', 'numberOfStops', 'airplane', 'stopLocation', 'ticketPrice'],
  {
    'add': '/app/airlines/'+getUserServiceId()+'/addFlight',
    'showAll': '/app/airlines/'+getUserServiceId()+'/showFlights',
    'delete': '/app/airlines/'+getUserServiceId()+'/deleteFlight/',
    'showDestinations': '/app/airlines/showAllDestinations',
    'showAirplanes': '/app/airplanes/show'
  }
);