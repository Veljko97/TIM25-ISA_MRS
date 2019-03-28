function Airlines(attributes, addUrlApi, showUrlApi) {
  Model.call(this, attributes, addUrlApi, showUrlApi);
}

Airlines.prototype = Object.create( Model.prototype );

Airlines.prototype.bindEvents = function() {
  $(document).on('submit', '#airlineForm', this.addCallback.bind(this));
  $(document).on('submit', '#editAirlineForm', this.editCallback.bind(this));
}

Airlines.prototype.showAll = function(data) {
  var table = $("#airlinesTable").first();
  table.html("<tr><th>Name</th><th>Address</th><th>Description</th><th>Options</th></tr>");
  this.list = [];

  for(var i = 0; i < data.length; i++) {
    var airline = data[i];
    this.list.push(airline);
    table.html(table.html() + "<tr><td>"+ airline.airLineName + "</td><td>" + airline.airLineAddress + "</td><td>"+ airline.airLineDescription+"</td><td><a class=\"btn btn-info\" href=\"edit-airline.html?id=" + i + "\">Edit</a></td></tr>");
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
  this.urlApi.edit = '/app/airlines/editAirline?id=' + airline.airLineID;
  $(document).on('submit', '#editAirlineForm', this.editCallback.bind(this));
}

Airlines.prototype.show = function(index) {
  ajaxService.GET('/app/airlines/getAirline?index=' + index, this.showCallback.bind(this));
}


var airlines = new Airlines(['airLineName', 'airLineAddress', 'airLineDescription'], '/app/airlines/addAirline', '/app/airlines/showAirLines');