function Reserve() {
  this.reservations = [];
  this.tickets = [];
}

Reserve.prototype.reserveFlight = function(e, userType) {
  e.preventDefault();
  var obj = this.makeJSONFlightObject(userType);
  ajaxService.POST('/app/airlines/' + reserve.airlineId + '/buyticket/' + reserve.id, obj, this.reserveFlightCallback.bind(this));
}

Reserve.prototype.reserveFlightCallback = function(data) {
  this.reservations.push(data);
  $('#' + modal.seat).prop('checked', true);
  $('#' + modal.seat).prop("onclick", null).off("click");
  $('#' + modal.seat).click(function(e) { e.preventDefault();return false; });
  modal.close();
}

Reserve.prototype.getReservations = function() {
  let user = JSON.parse(sessionStorage.user);
  $("#ticket-results").html("");
  ajaxService.GET('/app/reservations/' + user.id + '/getTickets', this.showTicketReservationsCallback.bind(this));
  ajaxService.GET('/app/reservations/' + user.id + '/getRoomReservations', this.showRoomReservationsCallback.bind(this));
  ajaxService.GET('/app/reservations/' + user.id + '/getVehicleReservations', this.showVehicleReservationsCallback.bind(this));
}

Reserve.prototype.showRoomReservationsCallback = function(response) {
  if (!response.length) {return;}
  for(let i = 0; i < response.length; i++) {
    let data = response[i];
    $("#ticket-results").html($("#ticket-results").html() + "<div class=\"row search-result\">\
    <div class=\"search-content\">\
      <div class=\"search-group\">\
        <h4>Hotel: "+data.hotelName+"</h4>\
        <span>Price: "+data.price+"$</span>\
      </div>\
      <span class=\"text-overflow\">Room number: " + data.roomNumber + "</span>\
      <span class=\"text-overflow\">Reservation Type: Room</span>\
      <span class=\"text-overflow\">Reservations starts on " + new Date(data.reservationStart).toLocaleString() + " and ends on " + new Date(data.reservationEnd).toLocaleString() + ".</span>\
      <p class=\"mb-5 mt-3 ml-3\">\
        <a class=\"btn btn-success btn-lg pb_btn-pill\" href=\"\" onclick=\"reserve.deleteRoomReservation(event,"+ data.hotelId + "," + data.roomId + "," + data.id+")\">\
          <span class=\"pb_font-14 text-uppercase pb_letter-spacing-1\">\
            Cancel\
          </span>\
        </a>\
      </p>\
    </div>\
    </div>");
  }
}

Reserve.prototype.showVehicleReservationsCallback = function(response) {
  if (!response.length) {return;}
  for(let i = 0; i < response.length; i++) {
    let data = response[i];
    $("#ticket-results").html($("#ticket-results").html() + "<div class=\"row search-result\">\
    <div class=\"search-content\">\
      <div class=\"search-group\">\
        <h4>Vehicle: "+data.vehicleName+"</h4>\
        <span>Price: "+data.price+"$</span>\
      </div>\
      <span class=\"text-overflow\">Reservation Type: Vehicle</span>\
      <span class=\"text-overflow\">Reservations starts on " + new Date(data.reservationStart).toLocaleString() + " and ends on " + new Date(data.reservationEnd).toLocaleString() + ".</span>\
      <p class=\"mb-5 mt-3 ml-3\">\
        <a class=\"btn btn-success btn-lg pb_btn-pill\" href=\"\" onclick=\"reserve.deleteVehicleReservation(event,"+ data.rentACarId + "," + data.vehicleId + "," + data.id+")\">\
          <span class=\"pb_font-14 text-uppercase pb_letter-spacing-1\">\
            Cancel\
          </span>\
        </a>\
      </p>\
    </div>\
    </div>");
  }
}

Reserve.prototype.showTicketReservationsCallback = function(response) {
  if (!response.length) {return;}
  for(let i = 0; i < response.length; i++) {
    let data = response[i];
    $("#ticket-results").html($("#ticket-results").html() + "<div class=\"row search-result\">\
    <div class=\"search-content\">\
      <div class=\"search-group\">\
        <h4>"+data.srcDestName+"<->"+data.targetDestName+"</h4>\
        <span>Price: "+data.ticketPrice+"$</span>\
      </div>\
      <span class=\"text-overflow\">Seat: " + data.seat + "</span>\
      <span class=\"text-overflow\">Reservation Type: Flight</span>\
      <p class=\"mb-5 mt-3 ml-3\">\
        <a class=\"btn btn-success btn-lg pb_btn-pill\" href=\"\" onclick=\"reserve.deleteTicketReservation(event,"+ data.airlineId + "," + data.idFlight + "," + data.ticketId+")\">\
          <span class=\"pb_font-14 text-uppercase pb_letter-spacing-1\">\
            Cancel\
          </span>\
        </a>\
      </p>\
    </div>\
    </div>");
  }
}

Reserve.prototype.deleteTicketReservation = function(e, airlineid, flightid, ticketid) {
  e.preventDefault();
  ajaxService.DELETE('/app/airlines/' + airlineid + '/cancelReservation/' + flightid, JSON.stringify([ticketid]), this.getReservations.bind(this));
}

Reserve.prototype.deleteRoomReservation = function(e, hotelId, roomId, roomReservationId) {
  e.preventDefault();
  ajaxService.DELETE('/app/hotels/' + hotelId + '/cancelReservation/' + roomId, JSON.stringify([roomReservationId]), this.getReservations.bind(this));
}

Reserve.prototype.deleteVehicleReservation = function(e, serviceId, vehicleId, vehicleReservationId) {
  e.preventDefault();
  ajaxService.DELETE('/app/rentacar/' + serviceId + '/cancelReservation/' + vehicleId, JSON.stringify([vehicleReservationId]), this.getReservations.bind(this));
}

Reserve.prototype.makeJSONFlightObject = function(userType) {
  switch(userType) {
  case 'REGISTERED':
    return JSON.stringify({
      "email": $("#email1").val(),
      "passport": $("#passport1").val(),
      "status": 'PENDING',
      "seat": modal.seat,
      "userType": userType
    });
  case 'UNREGISTERED':
    return JSON.stringify({
      "firstName": $("#firstName2").val(),
      "lastName": $("#lastName2").val(),
      "email": $("#email2").val(),
      "passport": $("#passport2").val(),
      "status": 'PENDING',
      "seat": modal.seat,
      "userType": userType
    });
  case 'CURRENT':
    return JSON.stringify({
      "passport": $("#passport3").val(),
      "status": 'PENDING',
      "seat": modal.seat,
      "userType": userType
    });
  }
}

Reserve.prototype.cancelReservation = function(e) {
  e.preventDefault();
  if (this.reservations.length) {
    ajaxService.DELETE('/app/airlines/' + reserve.airlineId + '/cancelReservation/' + reserve.id, JSON.stringify(this.reservations), function() {window.location.replace('/');});
  }
}

Reserve.prototype.continueReservation = function(e) {
  e.preventDefault();
  ajaxService.POST('/app/airlines/' + reserve.airlineId + '/continueReservation/' + reserve.id, JSON.stringify(this.reservations), function() {/*to-do implement other reservations after this one*/});
}

Reserve.prototype.checkTicketPermission = function(ticketId) {
  ajaxService.GET('/app/airlines/getTicket/' + ticketId, function(data) {
    var user = JSON.parse(sessionStorage.getItem('user'));
    if (data.status == 'ACCEPTED' || user == null || user.email != data.email) {
      window.location.replace('/');
    } else {
      $("#details").val("The ticket price is " + data.ticketPrice + ". The relation of the trip is " + data.srcDestName + "<->" + data.targetDestName);
    }
  });
}

Reserve.prototype.sendResponse = function(event, response) {
  event.preventDefault();
  ajaxService.POST('/app/airlines/reservationResponse/' + getParamFromUrl('ticketId'), JSON.stringify(response), function() {window.location.replace('/')});
}

var reserve = new Reserve();

