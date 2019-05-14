function Search(urlApis, activeTab) {
  this.urlApis = urlApis;
  this.activeTab = activeTab;
  this.pageSize = 5;
  this.currentPage = 0;
  this.numberOfPages = 0;
  this.lastSearch = "";
}

addFriend = function(userId) {
  var success = function(data){
    alert("Added")
    location.reload()};
  ajaxService.POST("/app/users/sendRequest/"+userId,"",success);
}

errorImage = function(img) {
  img.src = "../assets/images/no-image.png";
}

Search.prototype.switchTab = function(evt, tabName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

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

Search.prototype.switchMode = function(e, isAlreadyActive) {
  e.preventDefault();
  if(isAlreadyActive) {return;}  

  var isSearch = $(e.target).text() == 'Search';
  var formToHide = isSearch ? $("#flightFilterForm") : $("#flightSearchForm");
  var formToShow = isSearch ? $("#flightSearchForm") : $("#flightFilterForm");
  formToHide.hide();
  formToShow.show();
}

Search.prototype.switchPage = function(dir) {
  if ((dir == -1 && this.currentPage > 0) || (dir == 1 && this.currentPage < (this.numberOfPages - 1))) {
    this.currentPage += dir;
    this.render(this.lastSearch +'&size='+this.pageSize+'&page=' + this.currentPage);
  }
}

Search.prototype.switchDestinations = function(e) {
  e.preventDefault();
  var first = $("#startDestinationName").val();
  var second = $("#finalDestinationName").val();
  $("#finalDestinationName").val(first);
  $("#startDestinationName").val(second);
}

Search.prototype.tick = function(e, checkBoxType) {
  $('input[name='+checkBoxType+']').each(function(index, el) {
    if (e.target.labels[0].innerText != el.labels[0].innerText) {
      el.checked = false;
    }
  });
}

Search.prototype.init = function() {
  this.render();
  this.bindEvents();
}

Search.prototype.render = function(url = null) {
  if(url == null){
    this.lastSearch = this.urlApis.Airlines.showAll + "?";
  }
  ajaxService.GET(url || (this.urlApis.Airlines.showAll+ '?size='+this.pageSize+'&page=0'), this.showCallback.bind(this));
}

Search.prototype.bindEvents = function() {
  $(document).on('submit', '#airlineSearchForm', this.searchAirlineCallback.bind(this));
  $(document).on('submit', '#flightSearchForm', this.searchFlightCallback.bind(this));
  // $(document).on('submit', '#flightFilterForm', this.filterFlightCallback.bind(this));
  $(document).on('submit', '#hotelSearchForm', this.searchHotelCallback.bind(this));
  $(document).on('submit', '#rentacarSearchForm', this.searchRentacarCallback.bind(this));
  $(document).on('submit', '#userSearchForm', this.searchUserCallback.bind(this));
}


/* Event Handlers */
Search.prototype.searchAirlineCallback = function(e) {
  e.preventDefault();
  var name = $("#airLineName").first().val();
  this.currentPage = 0;
  this.lastSearch = this.urlApis[this.activeTab].search + '?name=' + name;
  ajaxService.GET(this.lastSearch + '&page=0&size='+this.pageSize, this.showCallback.bind(this));
}

Search.prototype.searchFlightCallback = function(e) {
  e.preventDefault();
  var type = '';
  var flightClass = '';
  $('input[name="type"]:checked').each(function() {
    type = this.value;
  });

  $('input[name="flightClass"]:checked').each(function() {
    flightClass = this.value;
  });
  var from = $("#startDestinationName").first().val();
  var to = $("#finalDestinationName").first().val();
  var takeOff = Date.parse($("#takeOffDate").first().val());
  var landing = Date.parse($("#landingDate").first().val());
  var numberOfPeople = $("#numberOfPeople").first().val();

  var airLineName = $("#flAirlineName").first().val() || '';
  var flightLength = $("#flightLength").first().val() || '';

  var priceFrom = $("#priceFrom").first().val();
  var priceTo = $("#priceTo").first().val();
  this.lastSearch = this.urlApis[this.activeTab].search+ '?type=' + type +
  '&flightClass=' + flightClass + '&from=' + from + '&to=' + to + '&takeOff=' + takeOff + '&landing=' + landing +
  '&numberOfPeople=' + numberOfPeople + '&airLineName=' + airLineName + '&flightLength=' + flightLength + '&priceFrom=' +
  priceFrom + '&priceTo=' + priceTo;
  ajaxService.GET(this.lastSearch + '&page=0&size='+this.pageSize, this.showCallback.bind(this));

}

Search.prototype.searchHotelCallback = function(e) {
  e.preventDefault();
  var destination = $("#destination").first().val();
  var hotelName = $("#hotelName").first().val();
  this.currentPage = 0;
  this.lastSearch = this.urlApis[this.activeTab].search + '?destination=' + destination + '&name=' + hotelName;
  ajaxService.GET(this.lastSearch + '&page=0&size='+this.pageSize, this.showCallback.bind(this));
}

Search.prototype.searchRentacarCallback = function(e) {
  e.preventDefault();
  var destination = $("#destinationName").first().val();
  var serviceName = $("#serviceName").first().val();
  this.lastSearch = this.urlApis[this.activeTab].search + '?destination=' + destination + '&name=' + serviceName;
  ajaxService.GET(this.lastSearch + '&page=0&size='+this.pageSize, this.showCallback.bind(this));
}

Search.prototype.searchUserCallback = function(e) {
  e.preventDefault();
  var searchParam = $("#searchParam").first().val();
  this.lastSearch = this.urlApis[this.activeTab].search + '?serchParam=' + searchParam;
  ajaxService.GET(this.lastSearch + '&page=0&size='+this.pageSize, this.showCallback.bind(this));
}

Search.prototype.showCallback = function(data) {
  var resultsTable = $(".results").first();
  resultsTable.html("");
  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  for(let i = 0; i < data.length; i++) {
    resultsTable.html(resultsTable.html() + this.getEntityTableRowHtml(data[i]));
  }
}

Search.prototype.getEntityTableRowHtml = function(data) {
  switch(this.activeTab) {
    case 'Airlines':
      return "\
        <div class=\"row search-result\">\
          <img class=\"row-image\" src=\""+(data.image || "../assets/images/no-image.png")+"\"onerror=\"errorImage(this)\">\
          <div class=\"search-content\">\
            <h4>"+data.airLineName+"</h4>\
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
            <span>Graded: "+(data.hotelGrade || data.averageGrade)+"</span>\
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
            <h4>"+data.rentACarName+"</h4>\
            <span class=\"text-overflow\">"+ data.rentACarDescription +"</span>\
            <a class=\"see-more-link\" href=\"/guest/rentacar.html?id="+data.rentACarID+"\">See more</a>\
          </div>\
        </div>";
    case 'Users':
      let user = JSON.parse(sessionStorage.user);
      var logdIn = "<a class=\"see-more-link\" href=\"#\" onclick=\"addFriend("+data.id+")\">Add Friend</a>";
      var div = "\
      <div class=\"row search-result\">\
          <img class=\"row-image\" src=\""+(data.image || "../assets/images/no-image.png")+"\"onerror=\"errorImage(this)\">\
          <div class=\"search-content\">\
            <h4>"+data.username+"</h4>\
            <h4>First Name: "+data.firstName+"</h4>\
            <h4>Last Name: "+data.lastName+"</h4>\
            <h4>E-mail: "+data.email+"</h4>\
            <a class=\"see-more-link\" href=\"/guest/user.html?id="+data.id+"\">See more</a>";
      div += user? logdIn : "";
      div +=    "</div>\
              </div>";

        return div;
    default:
      return "";
  }
}

var search = new Search({
  'Flights': {'showAll': '/app/airlines/showAllFlights', 'search': '/app/airlines/searchFlights'},
  'Hotels': {'showAll': '/app/hotels/showHotels', 'search': '/app/hotels/search'},
  'Rentacar': {'showAll': '/app/rentacar/showRentACars', 'search': '/app/rentacar/search'},
  'Airlines': {'showAll': '/app/airlines/showAirLines', 'search': '/app/airlines/search'},
  'Users': {'showAll': '', 'search': '/app/users/findUser'}
}, 'Airlines');