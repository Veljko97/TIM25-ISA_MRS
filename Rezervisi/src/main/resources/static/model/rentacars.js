function RentACars(attributes, addUrlApi, showUrlApi) {
  Model.call(this, attributes, addUrlApi, showUrlApi);
}

RentACars.prototype = Object.create( Model.prototype );

RentACars.prototype.bindEvents = function() {
  $(document).on('submit', '#rentacarForm', this.addCallback.bind(this));
}

RentACars.prototype.showAll = function(data) {
  var table = $("#rentTable").first();
  table.html("<tr><th>Name</th><th>Address</th><th>Description</th></tr>");
  this.list = [];

  for(var i = 0; i < data.length; i++) {
    var rentacar = data[i];
    this.list.push(rentacar);
    table.html(table.html() + "<tr><td>"+ rentacar.rentACarName + "</td><td>" + rentacar.rentACarAddress + "</td><td>" + rentacar.rentACarDescription + "</td></tr>");
  }
}

var rentacars = new RentACars(['rentACarName', 'rentACarAddress', 'rentACarDescription'], '/app/rentacar/addRentACar', '/app/rentacar/showRentACars');