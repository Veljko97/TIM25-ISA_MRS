function Vehicles(attributes, urlApi) {
  Model.call(this, attributes, urlApi);
}

Vehicles.prototype = Object.create( Model.prototype );

Vehicles.prototype.bindEvents = function() {
  $(document).on('submit', '#vehicleForm', this.addCallback.bind(this));
  $(document).on('submit', '#editVehicleForm', this.editCallback.bind(this));
  $(document).on('submit', '#serchForm', this.showFast.bind(this));
}

Vehicles.prototype.addCallback = function(e) {
  e.preventDefault();

  if (!this.validateInput()) {
    return handleWrongInput();
  }
  var obj = this.makeJSONObject();
  ajaxService.POST(this.urlApi.add + '?page=' + this.currentPage + "&size=" + this.pageSize, obj, this.showAll.bind(this));
}

Vehicles.prototype.showAll = function(data) {
  var table = $("#vehiclesTable").first();
  table.html("<tr><th>Name</th><th>Branch Name</th><th class=\"options-cell\" colspan=\"2\">Options</th></tr>");
  this.list = [];

  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  for(var i = 0; i < data.length; i++) {
    var vehicle = data[i];
    this.list.push(vehicle);
    table.html(table.html() + "<tr><td>"+ vehicle.vehicleName + "</td><td>" + 
    vehicle.branchName + "</td><td><a class=\"btn btn-info\" href=\"edit-vehicle.html?id=" + 
    vehicle.idVehicle + "\">Edit</a></td><td><a class=\"btn btn-danger\" onclick=\"vehicles.deleteCallback(" + vehicle.idVehicle +")\">Delete</a></td></tr>");
  }
}

Vehicles.prototype.showCallback = function(vehicle) {
  if (vehicle) {
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      var inputName = input.attr("id");
      input.attr("value", vehicle[inputName]);
    }
  }
  this.urlApi.edit = '/app/rentacar/'+ getUserServiceId() +'/editVehicle/' + vehicle.idVehicle;
  $(document).on('submit', '#editVehicleForm', this.editCallback.bind(this));
}

Vehicles.prototype.showBranches = function(data) {
  var branches = $("#branchName").first();

  branches.html("");

  for(var i = 0; i < data.length; i++) {
    var branch = data[i];
    branches.html(branches.html() + "<option value=\""+branch.branchName+"\">"+branch.branchName+"</option>");
  }
}

Vehicles.prototype.render = function() {
  ajaxService.GET(this.urlApi.showAll + '?size='+this.pageSize+'&page=' + this.currentPage, this.showAll.bind(this));
  ajaxService.GET(this.urlApi.showBranches, this.showBranches.bind(this));
}

Vehicles.prototype.show = function(index) {
  ajaxService.GET(this.urlApi.showBranches, this.showBranches.bind(this));
  ajaxService.GET('/app/rentacar/' + getUserServiceId() +'/getVehicle/' + index, this.showCallback.bind(this));
}

Vehicles.prototype.showFastCallback = function(data) {
  var table = $("#vehiclesTable").first();
  table.html("<tr><th>Name</th><th>Branch Name</th><th class=\"options-cell\">Options</th></tr>");
  this.list = [];

  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  for(var i = 0; i < data.length; i++) {
    var vehicle = data[i];
    this.list.push(vehicle);
    table.html(table.html() + "<tr><td>"+ vehicle.vehicleName + "</td><td>" + 
    vehicle.branchName + "</td><td><a class=\"btn btn-danger\" onclick=\"modal.show(event," + vehicle.idVehicle +")\">Make Fast</a></td></tr>");
  }
}

Vehicles.prototype.showFast = function(event) {
  event.preventDefault();
  var obj = {}
  obj.start = Date.parse($("#startDate").val());
  obj.end = Date.parse($("#endDate").val());
  ajaxService.POST('/app/rentacar/freeVehicles/'+getUserServiceId()+'?page=' + this.currentPage + "&size=" + this.pageSize,JSON.stringify(obj),this.showFastCallback.bind(this));
}

Vehicles.prototype.makeFast = function(event, vehicleId){
  event.preventDefault();
  var obj = {};
  obj.start = Date.parse($("#startDate").val());
  obj.end = Date.parse($("#endDate").val());
  obj.price = $("#newPrice").val();
  ajaxService.POST('/app/rentacar/makeFastReservation/'+vehicleId,JSON.stringify(obj));
}

var vehicles = new Vehicles(
  ['vehicleName', 'branchName', 'price'],
  {
    'add': '/app/rentacar/' + getUserServiceId() +'/addVehicle',
    'showAll': '/app/rentacar/'+getUserServiceId()+'/showVehicles',
    'delete': '/app/rentacar/'+getUserServiceId()+'/deleteVehicle/',
    'showBranches': '/app/rentacar/'+getUserServiceId()+'/showAllBranches'
});