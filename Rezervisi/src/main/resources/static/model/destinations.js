function Destinations(attributes, addUrlApi, showUrlApi) {
  Model.call(this, attributes, addUrlApi, showUrlApi);
}

Destinations.prototype = Object.create( Model.prototype );

Destinations.prototype.bindEvents = function() {
  $(document).on('submit', '#destinationForm', this.addCallback.bind(this));
}

Destinations.prototype.showAll = function(data) {
  var table = $("#destinationsTable").first();
  table.html("<tr><th>Name</th><th>Description</th><th>Options</th></tr>");
  this.list = [];

  for(var i = 0; i < data.length; i++) {
    var destination = data[i];
    this.list.push(destination);
    table.html(table.html() + "<tr><td>"+ destination.destinationName + "</td><td>" + destination.destinationDescription + "</td><td><a class=\"btn btn-info\">Edit</a></td></tr>");
  }
}

var destinations = new Destinations(['destinationName', 'destinationDescription'], '/app/destinations/addDestination', '/app/destinations/showDestinations');