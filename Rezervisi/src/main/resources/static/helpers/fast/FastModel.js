function FastModel(thisName,urlApi) {
  this.urlApi = urlApi;

  this.name = thisName;
  this.list = [];
  this.currentPage = 0;
  this.numberOfPages = 0;
  this.pageSize = 5;
}

FastModel.prototype.init = function() {
  this.cacheDom();
  this.render();
  this.bindEvents();
}

FastModel.prototype.cacheDom = function() {}
FastModel.prototype.bindEvents = function() {}

FastModel.prototype.render = function(url = null) {
  ajaxService.GET(url || (this.urlApi.showAllFast+ '?size='+this.pageSize+'&page=' + this.currentPage), this.showAll.bind(this), function() {handleErrorAction();});
}

FastModel.prototype.showAll = function(data) {
  var resultsTable = $(".sub-list").first();
  resultsTable.html("");
  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  for(let i = 0; i < data.length; i++) {
    resultsTable.html(resultsTable.html() + this.getSubEntityTableRowHtml(data[i]));
  }
}

FastModel.prototype.switchPage = function(dir) {
  if ((dir == -1 && this.currentPage > 0) || (dir == 1 && this.currentPage < (this.numberOfPages - 1))) {
    this.currentPage += dir;
    this.render(this.urlApi.showAllFast +'?size='+this.pageSize+'&page=' + this.currentPage);
  }
}

FastModel.prototype.reserv = function(ticketId, obj = "") {
  ajaxService.PUT(this.urlApi.reserv + ticketId, obj, function(){window.location.replace("/index.html")})
}

FastModel.prototype.getSubEntityTableRowHtml = function(data){return ""}

