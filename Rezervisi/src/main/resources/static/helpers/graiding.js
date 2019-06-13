function Grade(urlApis, activeTab) {
    this.urlApis = urlApis;
    this.activeTab = activeTab;
    this.pageSize = 5;
    this.currentPage = 0;
    this.numberOfPages = 0;
    this.lastSearch = "";
  }
  
errorImage = function(img) {
  img.src = "../assets/images/no-image.png";
}

Grade.prototype.showSearchOptions = function() { 
  $("#options").html("<button class=\"tablinks active\" onclick=\"grade.switchTab(event, 'Airlines')\">\
          <div class=\"pb_icon\"><i class=\"ion-android-plane pb_icon-gradient\"></i></div>\
        </button>\
        <button class=\"tablinks\" onclick=\"grade.switchTab(event, 'Flights')\">\
          <div class=\"pb_icon\"><i class=\"ion-android-globe pb_icon-gradient\"></i></div>\
        </button>\
        <button class=\"tablinks\" onclick=\"grade.switchTab(event, 'Hotels')\">\
          <div class=\"pb_icon\"><i class=\"ion-android-home pb_icon-gradient\"></i></div>\
        </button>\
        <button class=\"tablinks\" onclick=\"grade.switchTab(event, 'Rentacar')\">\
          <div class=\"pb_icon\"><i class=\"ion-android-car pb_icon-gradient\"></i></div>\
        </button>")
    
}

Grade.prototype.switchTab = function(evt, tabName) {
  var i, tablinks;

  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";

  this.activeTab = tabName;
  this.currentPage = 0;
  $("#section-home").height($('#' + tabName).height() + 200);
  if(this.urlApis[this.activeTab].showAll != ""){
    this.lastSearch = this.urlApis[this.activeTab].showAll;
    this.render(this.urlApis[this.activeTab].showAll + '?size='+this.pageSize+'&page=0');
  }else{
    $(".results").first().html("");
  }
}

Grade.prototype.switchMode = function(e, isAlreadyActive) {
  e.preventDefault();
  if(isAlreadyActive) {return;}  

  var isSearch = $(e.target).text() == 'Search';
  var formToHide = isSearch ? $("#flightFilterForm") : $("#flightSearchForm");
  var formToShow = isSearch ? $("#flightSearchForm") : $("#flightFilterForm");
  formToHide.hide();
  formToShow.show();
}

Grade.prototype.switchPage = function(dir) {
  if ((dir == -1 && this.currentPage > 0) || (dir == 1 && this.currentPage < (this.numberOfPages - 1))) {
    this.currentPage += dir;
    if(this.lastSearch.includes('?')) {
      this.render(this.lastSearch +'&size='+this.pageSize+'&page=' + this.currentPage);
    }else {
      this.render(this.lastSearch +'?size='+this.pageSize+'&page=' + this.currentPage);
    }
  }
}

Grade.prototype.switchDestinations = function(e) {
  e.preventDefault();
  var first = $("#startDestinationName").val();
  var second = $("#finalDestinationName").val();
  $("#finalDestinationName").val(first);
  $("#startDestinationName").val(second);
}

Grade.prototype.tick = function(e, checkBoxType) {
  $('input[name='+checkBoxType+']').each(function(index, el) {
    if (e.target.labels[0].innerText != el.labels[0].innerText) {
      el.checked = false;
    }
  });
}

Grade.prototype.init = function() {
  this.render();
  this.bindEvents();
  this.showSearchOptions();
}

Grade.prototype.render = function(url = null) {
  if(url == null){
    this.lastSearch = this.urlApis.Airlines.showAll + "?";
  }
  ajaxService.GET(url || (this.urlApis.Airlines.showAll+ '?size='+this.pageSize+'&page=0'), this.showCallback.bind(this));
}

Grade.prototype.bindEvents = function() {}



Grade.prototype.showCallback = function(data) {
  var resultsTable = $(".results").first();
  resultsTable.html("");
  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  for(let i = 0; i < data.length; i++) {
    resultsTable.html(resultsTable.html() + this.getEntityTableRowHtml(data[i]));
    this.setStars(data[i]);
  }
  
}

Grade.prototype.setStars = function(data) {
  var selcetor  = "";
  var id = -1;
  switch(this.activeTab)  {
    case "Hotels":
      selcetor = "#Hgr"+data.hotelID;
      id = data.hotelID;
      break;
    case "Rentacar" :
      selcetor = "#Rgr"+data.rentACarID;
      id = data.rentACarID;
      break;
    case "Flights" :
      selcetor = "#Fgr"+data.idFlight;
      id = data.idFlight;
      break;
    case "Airlines":
      selcetor = "#Agr"+data.airLineID;
      id = data.airLineID;
      break;
  }
  $(".my-rating").rateYo();
  $(selcetor).rateYo("option", "rating", data.averageGrade || 0);
  $(selcetor).rateYo("option", "precision", 2);
  $(selcetor).rateYo("option", "onSet", function(rating, rateYoInstance){
    ajaxService.POST(this.urlApis[this.activeTab].rate + id, ""+rating,
      setTimeout(function(){
        this.render(this.urlApis[this.activeTab].showAll);
      }.bind(this), 1000)
    );
  }.bind(this));
}

Grade.prototype.getEntityTableRowHtml = function(data) {
  switch(this.activeTab) {
    case 'Airlines':
      return "\
        <div class=\"row search-result\">\
          <img class=\"row-image\" src=\""+(data.image || "../assets/images/no-image.png")+"\"onerror=\"errorImage(this)\">\
          <div class=\"search-content\">\
            <div class=\"search-group\">\
              <h4>"+data.airLineName+"</h4>\
              <span class='my-rating' id='Agr"+data.airLineID+"'></span>\
            </div>\
            <span class=\"text-overflow\">"+ data.airLineDescription +"</span>\
            <a class=\"see-more-link\" href=\"/guest/airline.html?id="+data.airLineID +"\">See more</a>\
          </div>\
        </div>"
    case 'Flights':
      return "\
        <div class=\"row search-result\">\
          <img class=\"row-image\" src=\""+(data.image || "../assets/images/no-image.png")+"\"onerror=\"errorImage(this)\">\
          <div class=\"search-content\">\
            <div class=\"search-group\">\
              <h4>"+data.startDestinationName+"<->"+data.finalDestinationName+"</h4>\
              <span class='my-rating' id='Fgr"+data.idFlight+"'></span>\
              <span>Price: "+data.ticketPrice+"$</span>\
            </div>\
            <span class=\"text-overflow\">This flight takes off at "+ (new Date(data.takeOffDate)).toLocaleString() +" and lands at " + (new Date(data.landingDate)).toLocaleString()+". It has " + data.numberOfStops + " stops and lasts " + data.flightLength + " minutes.</span>\
            <a class=\"see-more-link\" href=\"/guest/flight.html?id="+data.idFlight +"&airlineId=" + data.airLineId +"\">See more</a>\
          </div>\
        </div>";
    case 'Hotels':
      return "\
      <div class=\"row search-result\">\
        <img class=\"row-image\" src=\""+(data.image || "../assets/images/no-image.png")+"\"onerror=\"errorImage(this)\">\
        <div class=\"search-content\">\
          <div class=\"search-group\">\
            <h4>"+data.hotelName+"</h4>\
            <span class='my-rating' id='Hgr"+data.hotelID+"'></span>\
          </div>\
          <span class=\"text-overflow\">Location: " + data.destination + "</span>\
          <span class=\"text-overflow\">Address: " + data.hotelAddress + "</span>\
          <a class=\"see-more-link\" href=\"/guest/hotel.html?id="+data.hotelID+"\">See more</a>\
        </div>\
      </div>";
    case 'Rentacar':
      return "\
      <div class=\"row search-result\">\
          <img class=\"row-image\" src=\""+(data.image || "../assets/images/no-image.png")+"\"onerror=\"errorImage(this)\">\
          <div class=\"search-content\">\
            <div class=\"search-group\">\
              <h4>"+data.rentACarName+"</h4>\
              <span class='my-rating' id='Rgr"+data.rentACarID+"'></span>\
            </div>\
            <span class=\"text-overflow\">"+ data.rentACarDescription +"</span>\
            <a class=\"see-more-link\" href=\"/guest/rentacar.html?id="+data.rentACarID+"\">See more</a>\
          </div>\
        </div>";
    default:
      return "";
  }
}
  
var grade = new Grade({
    'Airlines': {'showAll': '/app/reservations/getPastAirLineReservations', 'rate': '/app/users/gradeAirLine/'},
    'Flights': {'showAll': '/app/reservations/getPastFlightReservations', 'rate': '/app/users/gradeFlight/'},
    'Hotels': {'showAll': '/app/reservations/getPastHotelReservations', 'rate': '/app/users/gradeHotel/'},
    'Rooms': {'showAll': '/app/reservations/getPastRoomReservations', 'rate': '/app/users/gradeRoom/'},
    'Rentacar': {'showAll': '/app/reservations/getPastRentACarReservations', 'rate': '/app/users/gradeRentACar/'},
    'Vehicles': {'showAll': '/app/reservations/getPastVehicleReservations', 'rate': '/app/users/gradeVehicle/'},
}, 'Airlines');