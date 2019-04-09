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
    object[this.attributes[i]] = $('#' + this.attributes[i]).val();
  }
  return JSON.stringify(object);
}

Model.prototype.addCallback = function(e) {
  e.preventDefault();
  var obj = this.makeJSONObject();
  ajaxService.POST(this.urlApi.add, obj, this.showAll.bind(this));
}

Model.prototype.deleteCallback = function(i) {
  ajaxService.DELETE(this.urlApi.delete + i, this.showAll.bind(this));
}

Model.prototype.editCallback = function(e) {
  e.preventDefault();
  var obj = this.makeJSONObject();
  ajaxService.PUT(this.urlApi.edit, obj, function() { window.location.replace('http://localhost:8888') });
}

Model.prototype.render = function() {
  ajaxService.GET(this.urlApi.showAll, this.showAll.bind(this));
}

Model.prototype.showAll = function(data) {}
Model.prototype.show = function(data) {}
