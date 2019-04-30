function Profile(urlApis, activeTab) {
  this.urlApis = urlApis;
  this.activeTab = activeTab;
}

Profile.prototype.show = function(index) {
  ajaxService.GET(this.urlApis[this.activeTab] + index, this.showCallback.bind(this));
}

Profile.prototype.showCallback = function(data) {
  var resultsTable = $(".results").first();
  resultsTable.html("");
  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  for(let i = 0; i < data.length; i++) {
    resultsTable.html(resultsTable.html() + this.getEntityTableRowHtml(data[i]));
  }
}

Profile.prototype.getEntityHtml = function(data) {
  switch(this.activeTab) {
    case 'airline':
      return ""
    case 'flight':
      return "";
    case 'hotel':
      return "";
    case 'rentacar':
      return "";
    default:
      return "";
  }
}

var profile = new Profile({
  'flight': {'show': '/app/airlines/'+getUserServiceId()+'/getFlight/'},
  'hotel': {'show': '/app/hotels/getHotel/'},
  'rentacar': {'show': '/app/rentacar/showRentACars'},
  'airline': {'show': '/app/airlines/getAirline/', 'showFlights': '/app/airlines/search'}
});