function Model(attributes, urlApi) {
  this.urlApi = urlApi;

  this.attributes = attributes;
  this.list = [];
}

Model.prototype.init = function() {
  this.cacheDom();
  this.render();
  this.bindEvents();
}

Model.prototype.cacheDom = function() {}
Model.prototype.bindEvents = function() {}

Model.prototype.validateInput = function() {
  for(var i = 0; i < this.attributes.length; i++) {
    if ($('#' + this.attributes[i]).val() == ("")) {
      return false;
    }
  }
  return true;
}

Model.prototype.makeJSONObject = function() {
  var object = {}
  for(var i = 0; i < this.attributes.length; i++) {
    object[this.attributes[i]] = $('#' + this.attributes[i]).val() || $('#' + this.attributes[i]).first().val();
  }
  return JSON.stringify(object);
}

Model.prototype.addCallback = function(e) {
  e.preventDefault();

  if (!this.validateInput()) {
    return handleWrongInput();
  }

  var obj = this.makeJSONObject();
  ajaxService.POST(this.urlApi.add, obj, this.showAll.bind(this));
}

Model.prototype.deleteCallback = function(i) {
  ajaxService.DELETE(this.urlApi.delete + i, this.showAll.bind(this));
}

Model.prototype.editCallback = function(e) {
  e.preventDefault();

  if (!this.validateInput()) {
    return handleWrongInput();
  }

  var obj = this.makeJSONObject();
  ajaxService.PUT(this.urlApi.edit, obj, function() { window.location.replace(window.location.host) });
}

Model.prototype.render = function() {
  ajaxService.GET(this.urlApi.showAll, this.showAll.bind(this));
}

Model.prototype.addUserCallback = function(e){
  e.preventDefault();
  var admin = {
    username : $("#username").val(),
    firstName : $("#firstName").val(),
    lastName  : $("#lastName").val(),
    email : $("#email").val()
  }
  ajaxService.POST(this.urlApi.addUser,JSON.stringify(admin),function(){location.reload()});
}
Model.prototype.showAll = function(data) {}
Model.prototype.show = function(data) {}
Model.prototype.showUsersTable = function(data) {
  var table = $("#adminsTable").first();
  table.html("<tr><th>User Name</th><th>First Name</th><th>Last Name</th><th>E-mail</th></tr>");
  
  for(var i = 0; i < data.length; i++){
    admin = data[i];
    table.html(table.html()+"<tr><th>"+admin.username+"</th><th>"+admin.firstName+"</th><th>"+admin.lastName+"</th><th>"+admin.email+"</th></tr>");
  }
}
