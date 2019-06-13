function Profile(urlApis, activeTab) {
  this.urlApis = urlApis;
  this.activeTab = activeTab;
  this.pageSize = 5;
  this.currentPage = 0;
  this.numberOfPages = 0;
  this.index = 0;
}

Profile.prototype.show = function(indexParams, activeTab) {
  this.index = indexParams[0] || indexParams;
  this.activeTab = activeTab;
  ajaxService.GET(this.getGETURL(indexParams, activeTab), this.showCallback.bind(this));
  let showAllUrl = this.getShowAllURL(indexParams, activeTab);
  this.showList(showAllUrl);
}

Profile.prototype.showList = function(showAllUrl) {
  if (showAllUrl) {
    ajaxService.GET(showAllUrl+'?size='+this.pageSize+'&page=' + this.currentPage, this.showListCallback.bind(this));
  }
}

Profile.prototype.showCallback = function(data) {
  var resultsTable = $(".result").first();
  resultsTable.html("");
  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  resultsTable.html(resultsTable.html() + this.getEntityHtml(data));
  this.setStars(data);
}

Profile.prototype.showListCallback = function(data) {
  var resultsTable = $(".sub-list").first();
  resultsTable.html("");
  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  for(let i = 0; i < data.length; i++) {
    resultsTable.html(resultsTable.html() + this.getSubEntityTableRowHtml(data[i]));
    this.setStarsSub(data[i]);
  }
}

Profile.prototype.switchPage = function(dir) {
  if ((dir == -1 && this.currentPage > 0) || (dir == 1 && this.currentPage < (this.numberOfPages - 1))) {
    this.currentPage += dir;
    this.showList(this.getShowAllURL(this.index));
  }
}

Profile.prototype.getShowAllURL = function(index) {
  switch(this.activeTab) {
    case 'airline':
      return `/app/airlines/${index}/showFlights`
    case 'flight':
      return "";
    case 'hotel':
      return `/app/hotels/${index}/showRooms`;
    case 'rentacar':
      return `/app/rentacar/${index}/showVehicles`;
    default:
      return "";
  }
}

Profile.prototype.getGETURL = function(indexParams, activeTab) {
  switch(this.activeTab) {
    case 'flight':
      return `/app/airlines/${indexParams[1]}/getFlight/${indexParams[0]}`;
    default:
      return this.urlApis[activeTab].show + indexParams;
  }
}

Profile.prototype.setStars = function(data) {
  var selcetor  = ""
  switch(this.activeTab)  {
    case "hotel":
      selcetor = "#Hgr"+data.hotelID;
      break;
    case "rentacar" :
      selcetor = "#Rgr"+data.rentACarID;
      break;
    case "flight" :
      selcetor = "#Fgr"+data.idFlight;
      break;
    case "airline":
      selcetor = "#Agr"+data.airLineID;
      break;
  }
  $(selcetor).rateYo({
    starSize: 25,
    readOnly:true,
    totalStars: 5,
    rating: data.averageGrade || 0,
  });
}

Profile.prototype.getEntityHtml = function(data) {
  switch(this.activeTab) {
    case 'airline':
      return "<iframe  width=1110px height=300px src=\"https://wego.here.com/directions/mix//"+ data.airLineAddress + "\"/>\
      <img class=\"profile-img\" src=\""+(data.image || "../assets/images/airline.jpg")+"\"/>\
      <h2 class=\"profile-headline\">" + data.airLineName + "</h2>\
      <div class=\"about\">\
        <span class='my-rating' id='Agr"+data.airLineID+"'></span>\
        <span>Address: " + data.airLineAddress + "</span>\
        <span class=\"description\">" + data.airLineDescription + "</span>\
      </div>\
      <p class=\"mb-5 mt-3\"><a class=\"btn btn-success btn-lg pb_btn-pill\" href=\"/reserve/fast/flight.html?id="+data.airLineID+"\"><span class=\"pb_font-14 text-uppercase pb_letter-spacing-1\">Fast Reserve</span></a></p>";;
    case 'flight':
      return "\
      <img class=\"profile-img\" src=\""+(data.image || "../assets/images/airline.jpg")+"\"/>\
      <h2 class=\"profile-headline\">"+data.startDestinationName+"<->"+data.finalDestinationName+"</h2>\
      <div class=\"about\">\
        <span>Price: "+data.ticketPrice+"$</span>\
        <span class=\"description\">This flight takes off at "+ (new Date(data.takeOffDate)).toLocaleString() +" and lands at " +(new Date(data.landingDate)).toLocaleString()+". It has " + data.numberOfStops + " stops and lasts " + data.flightLength + " minutes.</span>\
      </div>\
      <span class='my-rating' id='Fgr"+data.idFlight+"'></span>\
      <p class=\"mb-5 mt-3\"><a class=\"btn btn-success btn-lg pb_btn-pill\" href=\"/reserve/flight.html?id="+data.idFlight+"&airlineId="+data.airLineId+"\"><span class=\"pb_font-14 text-uppercase pb_letter-spacing-1\">Reserve</span></a></p>\
      ";
    case 'hotel':
      return "\
      <img class=\"profile-img\" src=\""+(data.image || "../assets/images/hotel.jpg")+"\"/>\
      <h2 class=\"profile-headline\">" + data.hotelName + "</h2>\
      <div class=\"about\">\
        <span class='my-rating' id='Hgr"+data.hotelID+"'></span>\
        <span>Address: " + data.hotelAddress + "</span>\
        <span class=\"description\">" + data.hotelDescription + "</span>\
      </div>\
      <p class=\"mb-5 mt-3\"><a class=\"btn btn-success btn-lg pb_btn-pill\" href=\"/reserve/fast/room.html?id="+data.hotelID+"\"><span class=\"pb_font-14 text-uppercase pb_letter-spacing-1\">Fast Reserve</span></a></p>";
    case 'rentacar':
      return "<iframe  width=1110px height=300px src=\"https://wego.here.com/directions/mix//"+ data.rentACarAddress + "\"/>\
      <img class=\"profile-img\" src=\""+(data.image || "../assets/images/rentacar.jpg")+"\"/>\
      <h2 class=\"profile-headline\">" + data.rentACarName + "</h2>\
      <div class=\"about\">\
        <span class='my-rating' id='Rgr"+data.rentACarID+"'></span>\
        <span>Address: " + data.rentACarAddress + "</span>\
        <span class=\"description\">" + data.rentACarDescription + "</span>\
      </div>\
      <p class=\"mb-5 mt-3\"><a class=\"btn btn-success btn-lg pb_btn-pill\" href=\"/reserve/fast/vehicle.html?id="+data.rentACarID+"\"><span class=\"pb_font-14 text-uppercase pb_letter-spacing-1\">Fast Reserve</span></a></p>";
    default:
      return "";
  }
}

Profile.prototype.setStarsSub = function(data) {
  var selcetor  = ""
  switch(this.activeTab)  {
    case "hotel":
      selcetor = "#Rogr"+data.roomID;
      break;
    case "rentacar" :
      selcetor = "#Vgr"+data.idVehicle;
      break;
    case "flight" :
      selcetor = "";
      break;
    case "airline":
      selcetor = "#Fgr"+data.idFlight;
      break;
  }
  $(selcetor).rateYo({
    starSize: 25,
    readOnly:true,
    totalStars: 5,
    rating: data.averageGrade || 0,
  });
}

Profile.prototype.getSubEntityTableRowHtml = function(data) {
  switch(this.activeTab) {
    case 'airline':
      return "\
      <div class=\"row search-result\">\
        <img class=\"row-image\" src=\""+(data.image || "../assets/images/no-image.png")+"\">\
        <div class=\"search-content\">\
          <div class=\"search-group\">\
            <h4>"+data.startDestinationName+"<->"+data.finalDestinationName+"</h4>\
            <span class='my-rating' id='Fgr"+data.idFlight+"'></span>\
            <span>Price: "+data.ticketPrice+"$</span>\
          </div>\
          <span class=\"text-overflow\">This flight takes off at "+ (new Date(data.takeOffDate)).toLocaleString() +" and lands at " +(new Date(data.landingDate)).toLocaleString()+". It has " + data.numberOfStops + " stops and lasts " + data.flightLength + " minutes.</span>\
          <a class=\"see-more-link\" href=\"/guest/flight.html?id="+data.idFlight +"&airlineId=" + data.airLineId +"\">See more</a>\
        </div>\
      </div>";
    case 'flight':
      return "";
    case 'hotel':
      return "\
      <div class=\"row search-result\">\
      <img class=\"row-image\" src=\""+(data.image || "../assets/images/no-image.png")+"\">\
      <div class=\"search-content\">\
        <div class=\"search-group\">\
          <h4>Room Number: " + data.roomNumber + "</h4>\
          <span>Price: "+data.price+"$</span>\
          <span>Capacity: " + data.roomCapacity + "</span>\
        </div>\
        <span class=\"text-overflow\">" + data.roomDescription + "</span>\
        <span class='my-rating' id='Rogr"+data.roomID+"'></span>\
        <a class=\"see-more-link\" href=\"#\">Quick Reserve</a>\
      </div>\
    </div>";
    case 'rentacar':
      return "\
      <div class=\"row search-result\">\
        <img class=\"row-image\" src=\""+(data.image || "../assets/images/no-image.png")+"\">\
        <div class=\"search-content\">\
          <div class=\"search-group\">\
            <h4>"+data.vehicleName+"</h4>\
            <span class='my-rating' id='Vgr"+data.idVehicle+"'></span>\
          </div>\
          <a class=\"see-more-link\" href=\"#\">Quick Reserve</a>\
        </div>\
      </div>";
    default:
      return "";
  }
}

var profile = new Profile({
  'flight': {'show': '/app/airlines/'+getUserServiceId()+'/getFlight/'},
  'hotel': {'show': '/app/hotels/getHotel/'},
  'rentacar': {'show': '/app/rentacar/getRentacar/'},
  'airline': {'show': '/app/airlines/getAirline/'}
});