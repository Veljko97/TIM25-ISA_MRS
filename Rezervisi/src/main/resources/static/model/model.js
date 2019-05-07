function Model(attributes, urlApi) {
  this.urlApi = urlApi;

  this.attributes = attributes;
  this.list = [];
  this.currentPage = 0;
  this.numberOfPages = 0;
  this.pageSize = 5;
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
  ajaxService.POST(this.urlApi.add + '?page=' + this.currentPage + "&size=" + this.pageSize, obj, this.addSuccessCallback.bind(this), function() {handleErrorAction();});
}

Model.prototype.addSuccessCallback = function(data) {
  handleSuccessAction();
  this.showAll(data);
}

Model.prototype.deleteCallback = function(i) {
  ajaxService.DELETE(this.urlApi.delete + i + '?page=' + this.currentPage + "&size=" + this.pageSize, this.showAll.bind(this), function() {handleErrorAction();});
}

Model.prototype.editCallback = function(e) {
  e.preventDefault();

  if (!this.validateInput()) {
    return handleWrongInput();
  }

  var obj = this.makeJSONObject();
  ajaxService.PUT(this.urlApi.edit, obj, function() { window.location.replace(window.location.host);handleSuccessAction(); }, function() {handleErrorAction();});
}

Model.prototype.render = function(url = null) {
  ajaxService.GET(url || (this.urlApi.showAll+ '?size='+this.pageSize+'&page=' + this.currentPage), this.showAll.bind(this), function() {handleErrorAction();});
}

Model.prototype.addUserCallback = function(e){
  e.preventDefault();
  var admin = {
    username : $("#username").val(),
    firstName : $("#firstName").val(),
    lastName  : $("#lastName").val(),
    email : $("#email").val()
  }
  ajaxService.POST(this.urlApi.addUser,JSON.stringify(admin),function(){location.reload();}, function() {handleErrorAction();});
}

Model.prototype.showAll = function(data) {}
Model.prototype.show = function(data) {}

Model.prototype.switchPage = function(dir) {
  if ((dir == -1 && this.currentPage > 0) || (dir == 1 && this.currentPage < (this.numberOfPages - 1))) {
    this.currentPage += dir;
    this.render(this.urlApi.showAll +'?size='+this.pageSize+'&page=' + this.currentPage);
  }
}

Model.prototype.showUsersTable = function(data) {
  var table = $("#adminsTable").first();
  table.html("<tr><th>User Name</th><th>First Name</th><th>Last Name</th><th>E-mail</th></tr>");
  
  for(var i = 0; i < data.length; i++){
    admin = data[i];
    table.html(table.html()+"<tr><th>"+admin.username+"</th><th>"+admin.firstName+"</th><th>"+admin.lastName+"</th><th>"+admin.email+"</th></tr>");
  }
}
