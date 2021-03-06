function RentACars(attributes, urlApi) {
  Model.call(this, attributes, urlApi);
}

RentACars.prototype = Object.create( Model.prototype );

RentACars.prototype.bindEvents = function() {
  $(document).on('submit', '#rentacarForm', this.addCallback.bind(this));
  $(document).on('submit', '#addUserForm', this.addUserCallback.bind(this));
}

RentACars.prototype.showAll = function(data) {
  var table = $("#rentTable").first();
  table.html("<tr><th>Name</th><th>Address</th><th>Description</th><th class=\"options-cell\" colspan=\"2\">Options</th></tr>");
  this.list = [];

  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  for(var i = 0; i < data.length; i++) {
    var rentacar = data[i];
    this.list.push(rentacar);
    table.html(table.html() + "<tr><td>"+ rentacar.rentACarName + "</td><td>" + 
      rentacar.rentACarAddress + "</td><td>" + rentacar.rentACarDescription + 
      "</td><td><a class=\"btn btn-info\" href=\"add-admin-rentacar.html?id=" + 
      rentacar.rentACarID + "\">Add Admins</a></td><td><a class=\"btn btn-danger\" onclick=\"rentacars.deleteCallback(" 
      + rentacar.rentACarID +")\">Delete</a></td></tr>");
  }
}

RentACars.prototype.showCallback = function(rentacar) {
  if (rentacar) {
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      var inputName = input.attr("id");
      input.attr("value", rentacar[inputName]);
    }
  }
  this.urlApi.edit = '/app/rentacar/editRentacar/' + rentacar.rentACarID;
  $(document).on('submit', '#editRentacarForm', this.editCallback.bind(this));
}


RentACars.prototype.showDestinations = function(data) {
  var destinations = $("#destination").first();

  destinations.html("");

  for(var i = 0; i < data.length; i++) {
    var destination = data[i];
    destinations.html(destinations.html() + "<option value=\""+destination.destinationName+"\">"+destination.destinationName+"</option>");
  }
}

RentACars.prototype.show = function(index) {
  ajaxService.GET(this.urlApi.showDestinations, this.showDestinations.bind(this));
  ajaxService.GET('/app/rentacar/getRentacar/' + index, this.showCallback.bind(this));
}

RentACars.prototype.render = function() {
  ajaxService.GET(this.urlApi.showAll + '?size='+this.pageSize+'&page=0', this.showAll.bind(this));
  ajaxService.GET(this.urlApi.showDestinations, this.showDestinations.bind(this));
}

RentACars.prototype.showUsers = function(index) {
  this.urlApi.users = '/app/rentacar/showUser/' + index
  this.urlApi.addUser = '/app/rentacar/addUser/' + index
  ajaxService.GET(this.urlApi.users,this.showUsersTable);
  $(document).on('submit', '#addUserForm', this.addUserCallback.bind(this));
}

var rentacars = new RentACars(['rentACarName', 'destination', 'rentACarAddress', 'rentACarDescription'], {
  'add': '/app/rentacar/addRentACar',
  'showDestinations': '/app/airlines/showAllDestinations',
  'showAll': '/app/rentacar/showRentACars',
  'delete': '/app/rentacar/deleteRentacar/'
});