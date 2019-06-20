function FastFlight(thisName,urlApi) {
    FastModel.call(this, thisName, urlApi);
}

FastFlight.prototype = Object.create( FastModel.prototype );

FastFlight.prototype.reserveFlight = function(event, id){
    event.preventDefault();
    var obj = {};
    obj.passport = $("#passport3").val();
    this.reserv(id,JSON.stringify(obj));
}

FastFlight.prototype.setStars = function(data) {
  var selcetor  = "#Fgr"+data.idFlight;
  $(selcetor).rateYo({
    starSize: 25,
    readOnly:true,
    totalStars: 5,
    rating: data.flight.averageGrade || 0
  });
}

FastFlight.prototype.getSubEntityTableRowHtml = function(data){
    return "\
    <div class=\"row search-result\">\
      <img class=\"row-image\" src=\""+(data.flight.image || "/assets/images/no-image.png")+"\">\
      <div class=\"search-content\">\
        <h6>"+data.flight.startDestinationName + "->" + data.flight.finalDestinationName +"</h6>\
        <h6> Flight Class: "+data.seat+"</h6>\
        <h6> Flight starts: "+ (new Date(data.flight.takeOffDate)).toLocaleString()+"</h6> \
        <h6> Flight ends: "+ (new Date(data.flight.landingDate)).toLocaleString()+"</h6> \
        <h6> Price: "+ data.economyClassPrice +"</h6> \
        <div class=\"search-group\">\
          <span class='my-rating' id='Fgr"+data.flight.idFlight+"'></span>\
        </div>\
        <a class=\"see-more-link\" href=\"#\" onClick=\"modal.show(event,'"+data.ticketId+"')\">Quick Reserve</a>\
      </div>\
    </div>";
}

var fastFlight = new FastFlight("fastFlight", {'showAllFast':"/app/airlines/allFastTickets/"+getParamFromUrl('id'), 
                                                     'reserv': "/app/airlines/reserveFastTicket/"});