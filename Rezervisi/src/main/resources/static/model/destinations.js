function Destinations(attributes, urlApi) {
  Model.call(this, attributes, urlApi);
}

Destinations.prototype = Object.create( Model.prototype );

Destinations.prototype.bindEvents = function() {
  $(document).on('submit', '#destinationForm', this.addCallback.bind(this));
  $(document).on('submit', '#editDestinationForm', this.editCallback.bind(this));
}

Destinations.prototype.showAll = function(data) {
  var table = $("#destinationsTable").first();
  table.html("<tr><th>Name</th><th>Description</th><th class=\"options-cell\" colspan=\"2\">Options</th></tr>");
  this.list = [];

  for(var i = 0; i < data.length; i++) {
    var destination = data[i];
    this.list.push(destination);
    table.html(table.html() + "<tr><td>"+ destination.destinationName + "</td><td>" + 
    destination.destinationDescription + "</td><td><a class=\"btn btn-info\" href=\"edit-destination.html?id=" + 
    destination.idDestination + "\">Edit</a></td><td><a class=\"btn btn-danger\" onclick=\"destinations.deleteCallback(" + destination.idDestination +")\">Delete</a></td></tr>");
  }
}

Destinations.prototype.showCallback = function(destination) {
  if (destination) {
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      var inputName = input.attr("id");
      input.attr("value", destination[inputName]);
    }
  }
  this.urlApi.edit = '/app/airlines/1/editDestination/' + destination.idDestination;
  $(document).on('submit', '#editDestinationForm', this.editCallback.bind(this));
}

Destinations.prototype.show = function(index) {
  ajaxService.GET('/app/airlines/1/getDestination/' + index, this.showCallback.bind(this));
}

var destinations = new Destinations(['destinationName', 'destinationDescription'], {'add': '/app/airlines/1/addDestination', 'showAll': '/app/airlines/1/showDestinations/', 'delete': '/app/airlines/1/deleteDestination/'});