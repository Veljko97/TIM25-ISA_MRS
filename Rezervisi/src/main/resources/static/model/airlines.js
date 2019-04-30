function Airlines(attributes, urlApi) {
  Model.call(this, attributes, urlApi);
}

Airlines.prototype = Object.create( Model.prototype );

Airlines.prototype.bindEvents = function() {
  $(document).on('submit', '#airlineForm', this.addCallback.bind(this));
  $(document).on('submit', '#addUserForm', this.addUserCallback.bind(this));
}

Airlines.prototype.showAll = function(data) {
  var table = $("#airlinesTable").first();
  table.html("<tr><th>Name</th><th>Address</th><th>Description</th><th class=\"options-cell\" colspan=\"2\">Options</th></tr>");
  this.list = [];

  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  for(var i = 0; i < data.length; i++) {
    var airline = data[i];
    this.list.push(airline);
    table.html(table.html() + "<tr><td>"+ airline.airLineName + 
      "</td><td>" + airline.airLineAddress + "</td><td>"+ airline.airLineDescription+
      "</td><td><a class=\"btn btn-info\" href=\"add-admin-airline.html?id=" + airline.airLineID + 
      "\">Add Admins</a></td><td><a class=\"btn btn-danger\" onclick=\"airlines.deleteCallback(" + airline.airLineID +")\">Delete</a></td></tr>");
  }
}


Airlines.prototype.showCallback = function(airline) {
  if (airline) {
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      var inputName = input.attr("id");
      input.attr("value", airline[inputName]);
    }
  }
  this.urlApi.edit = '/app/airlines/editAirline/' + airline.airLineID;
  $(document).on('submit', '#editAirlineForm', this.editCallback.bind(this));
}

Airlines.prototype.show = function(index) {
  ajaxService.GET('/app/airlines/getAirline/' + index, this.showCallback.bind(this));
}

Airlines.prototype.showUsers = function(index) {
  this.urlApi.users = '/app/airlines/showUser/' + index
  this.urlApi.addUser = '/app/airlines/addUser/' + index
  ajaxService.GET(this.urlApi.users,this.showUsersTable);
  $(document).on('submit', '#addUserForm', this.addUserCallback.bind(this));
}


var airlines = new Airlines(['airLineName', 'airLineAddress', 'airLineDescription'], {'add': '/app/airlines/addAirline', 'showAll': '/app/airlines/showAirLines', 'delete': '/app/airlines/deleteAirline/'});