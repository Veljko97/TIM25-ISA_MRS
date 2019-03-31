function Hotels(attributes, urlApi) {
  Model.call(this, attributes, urlApi);
}

Hotels.prototype = Object.create( Model.prototype );

Hotels.prototype.bindEvents = function() {
  $(document).on('submit', '#hotelForm', this.addCallback.bind(this));
}

Hotels.prototype.showAll = function(data) {
  var table = $("#hotelsTable").first();
  table.html("<tr><th>Name</th><th>Address</th><th>Average Grade</th><th>Earning</th></tr>");
  this.list = [];

  for(var i = 0; i < data.length; i++) {
    var hotel = data[i];
    this.list.push(hotel);
    table.html(table.html() + "<tr><td>"+ hotel.hotelName + "</td><td>" + hotel.hotelAddress + "</td><td>"+ hotel.hotelAverageGrade+"</td><td>" + hotel.hotelEarning + "</td></tr>");
  }
}

var hotels = new Hotels(['hotelName', 'hotelAddress', 'hotelDesc', 'hotelGrade', 'roomConfig', 'hotelEarning'], {'add': '/app/hotels/addHotel', 'showAll': '/app/hotels/showHotels'});