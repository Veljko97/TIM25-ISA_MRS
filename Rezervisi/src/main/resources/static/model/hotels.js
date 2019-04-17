function Hotels(attributes, urlApi) {
  Model.call(this, attributes, urlApi);
}

Hotels.prototype = Object.create( Model.prototype );

Hotels.prototype.bindEvents = function() {
  $(document).on('submit', '#hotelForm', this.addCallback.bind(this));
  $(document).on('submit', '#addUserForm', this.addUserCallback.bind(this));
}

Hotels.prototype.showAll = function(data) {
  var table = $("#hotelsTable").first();
  table.html("<tr><th>Name</th><th>Address</th><th>Average Grade</th><th>Earning</th><th class=\"options-cell\" colspan=\"2\">Options</th></tr>");
  this.list = [];

  for(var i = 0; i < data.length; i++) {
    var hotel = data[i];
    this.list.push(hotel);
    table.html(table.html() + "<tr><td>"+ hotel.hotelName + "</td><td>" + 
      hotel.hotelAddress + "</td><td>"+ hotel.hotelAverageGrade+"</td><td>" + 
      hotel.hotelEarning + "</td><td><a class=\"btn btn-info\" href=\"add-admin-hotel.html?id=" + 
      hotel.hotelID + "\">Edit</a></td><td><a class=\"btn btn-danger\" onclick=\"hotels.deleteCallback(" + hotel.hotelID +")\">Delete</a></td></tr>");
  }
}

Hotels.prototype.showCallback = function(hotel) {
  if (hotel) {
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      var inputName = input.attr("id");
      input.attr("value", hotel[inputName]);
    }
  }
  this.urlApi.edit = '/app/hotels/editHotel/' + hotel.hotelID;
  $(document).on('submit', '#editHotelForm', this.editCallback.bind(this));
}

Hotels.prototype.show = function(index) {
  ajaxService.GET('/app/hotels/getHotel/' + index, this.showCallback.bind(this));
}

Hotels.prototype.showUsers = function(index) {
  this.urlApi.users = '/app/hotels/showUser/' + index
  this.urlApi.addUser = '/app/hotels/addUser/' + index
  ajaxService.GET(this.urlApi.users,this.showUsersTable);
  $(document).on('submit', '#addUserForm', this.addUserCallback.bind(this));
}

var hotels = new Hotels(['hotelName', 'hotelAddress', 'hotelDescription', 'hotelGrade', 'roomConfig', 'hotelEarning'], {'add': '/app/hotels/addHotel', 'showAll': '/app/hotels/showHotels', 'delete': '/app/hotels/deleteHotel/'});