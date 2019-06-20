function Search(urlApis, activeTab) {
  this.urlApis = urlApis;
  this.activeTab = activeTab;
  this.pageSize = 5;
  this.currentPage = 0;
  this.numberOfPages = 0;
  this.lastSearch = "";
}

var addFriend = function(userId) {
  var success = function(data){
    alert("Added");
    location.reload();
  };
  ajaxService.POST("/app/users/sendRequest/"+userId,"",success);
}

var errorImage = function(img) {
  img.src = "../assets/images/no-image.png";
}

Search.prototype.showSearchOptions = function() {
	let user = JSON.parse(localStorage.user);
	if (user) {
		$("#options").html("<button class=\"tablinks active\" onclick=\"search.switchTab(event, 'Airlines')\">\
                <div class=\"pb_icon\"><i class=\"ion-android-plane pb_icon-gradient\"></i></div>\
              </button>\
              <button class=\"tablinks\" onclick=\"search.switchTab(event, 'Flights')\">\
                <div class=\"pb_icon\"><i class=\"ion-android-globe pb_icon-gradient\"></i></div>\
              </button>\
              <button class=\"tablinks\" onclick=\"search.switchTab(event, 'Hotels')\">\
                <div class=\"pb_icon\"><i class=\"ion-android-home pb_icon-gradient\"></i></div>\
              </button>\
              <button class=\"tablinks\" onclick=\"search.switchTab(event, 'Rentacar')\">\
                <div class=\"pb_icon\"><i class=\"ion-android-car pb_icon-gradient\"></i></div>\
              </button>\
              <button class=\"tablinks\" onclick=\"search.switchTab(event, 'Users')\">\
                <div class=\"pb_icon\"><i class=\"ion-android-contact pb_icon-gradient\"></i></div>\
              </button>");
	}
	else {
		$("#options").html("<button class=\"tablinks active\" onclick=\"search.switchTab(event, 'Airlines')\">\
                <div class=\"pb_icon\"><i class=\"ion-android-plane pb_icon-gradient\"></i></div>\
              </button>\
              <button class=\"tablinks\" onclick=\"search.switchTab(event, 'Flights')\">\
                <div class=\"pb_icon\"><i class=\"ion-android-globe pb_icon-gradient\"></i></div>\
              </button>\
              <button class=\"tablinks\" onclick=\"search.switchTab(event, 'Hotels')\">\
                <div class=\"pb_icon\"><i class=\"ion-android-home pb_icon-gradient\"></i></div>\
              </button>\
              <button class=\"tablinks\" onclick=\"search.switchTab(event, 'Rentacar')\">\
                <div class=\"pb_icon\"><i class=\"ion-android-car pb_icon-gradient\"></i></div>\
              </button>");
	}
}

Search.prototype.switchTab = function(evt, tabName) {
  var tabcontent, tablinks;
  tabcontent = document.querySelectorAll(".tabcontent");
  tabcontent.forEach(function(tab) {
    tab.style.display = "none";
  }.bind(this));

  tablinks = document.querySelectorAll(".tablinks");
  tablinks.forEach(function(link) {
    link.className = link.className.replace(" active", "");
  }.bind(this));

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
  this.showSortOptions();
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
    if(this.lastSearch.includes('?')) {
      this.render(this.lastSearch +'&size='+this.pageSize+'&page=' + this.currentPage);
    }else {
      this.render(this.lastSearch +'?size='+this.pageSize+'&page=' + this.currentPage);
    }
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
  this.showSearchOptions();
  this.showSortOptions();
}

Search.prototype.showSortOptions = function() {
  this.sortOption = "";
  $("#sortselect").parent().show();
  switch(this.activeTab) {
  case 'Airlines':
    $("#sortselect").html("<option value=\"airLineName\">Name</option><option value=\"city\">City</option>")
    break;
  case 'Hotels':
    $("#sortselect").html("<option value=\"hotelName\">Name</option><option value=\"destination\">City</option>")
    break;
  case 'Rentacar':
    $("#sortselect").html("<option value=\"rentACarName\">Name</option><option value=\"destination\">City</option>")
    break;
  case 'Flights':
    $("#sortselect").parent().hide();
    break;
  case 'Users':
    $("#sortselect").html("<option value=\"firstName\">First Name</option><option value=\"lastName\">Last Name</option>");
    break;
  }
}

Search.prototype.sortResults = function(e) {
  this.sortOption = e.target.value;
  this.showCallback(this.data.sort(function(a, b) {
    return a[e.target.value] > b[e.target.value] ? 1 : -1;
  }));
  // this.showCallback(_.sortBy(this.data, [e.target.value]));
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
  var takeOff = Date.parse($("#takeOffDate").first().val()) || 0;
  var landing = Date.parse($("#landingDate").first().val()) || 0;
  var numberOfPeople = $("#numberOfPeople").first().val() || 0;
  var luggage = $("#luggage").first().val() || 0;

  var airLineName = $("#flAirlineName").first().val() || '';
  var flightLength = $("#flightLength").first().val() || '';

  var priceFrom = $("#priceFrom").first().val();
  var priceTo = $("#priceTo").first().val();
  this.lastSearch = this.urlApis[this.activeTab].search+ '?type=' + type +
  '&flightClass=' + flightClass + '&from=' + from + '&to=' + to + '&takeOff=' + takeOff + '&landing=' + landing +
  '&numberOfPeople=' + numberOfPeople + '&luggage=' + luggage + '&airLineName=' + airLineName + '&flightLength=' + flightLength + '&priceFrom=' +
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
  this.data = data;

  data.forEach(function(item) {
    resultsTable.html(resultsTable.html() + this.getEntityTableRowHtml(item));
    this.setStars(item);
  }.bind(this));
}

Search.prototype.setStars = function(data) {
  var selcetor  = ""
  switch(this.activeTab)  {
    case "Hotels":
      selcetor = "#Hgr"+data.hotelID;
      break;
    case "Rentacar" :
      selcetor = "#Rgr"+data.rentACarID;
      break;
    case "Flights" :
      selcetor = "#Fgr"+data.idFlight;
      break;
    case "Airlines":
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

Search.prototype.getEntityTableRowHtml = function(data) {
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
              <div>\
                <span class='my-rating' id='Fgr"+data.idFlight+"'></span>\
                <span>Price: "+data.economyClassPrice+"$</span>\
              </div>\
            </div>\
            <span class=\"text-overflow\">This flight takes off at "+ (new Date(data.takeOffDate)).toLocaleString() +" and lands at " + (new Date(data.landingDate)).toLocaleString()+". It has " + data.numberOfStops + " stops and is " + data.flightLength + " long.</span>\
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
    case 'Users':
      let user = JSON.parse(localStorage.user);
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