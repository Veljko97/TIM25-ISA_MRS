function Discount(attributes, urlApi) {
  Model.call(this, attributes, urlApi);
}

Discount.prototype = Object.create( Model.prototype );

Discount.prototype.bindEvents = function() {
  $(document).on('submit', '#discountForm', this.addCallback.bind(this));
}
Model.prototype.render = function(url = null) {
  ajaxService.GET(this.urlApi.showAll, this.showAll.bind(this), function() {handleErrorAction();});
}
Discount.prototype.showAll = function(data) {
  var table = $("#discountTable").first();
  table.html("<tr><th>Discount Percent</th><th>Points Needed</th><th class=\"options-cell\" colspan=\"1\">Options</th></tr>");
  this.list = [];

  this.numberOfPages = data.totalPages || 0;
  data = data.content || data;

  for(var i = 0; i < data.length; i++) {
    var discount = data[i];
    this.list.push(discount);
    table.html(table.html() + "<tr><td>"+ discount.discountPercent + "</td><td>"+ discount.pointsNeeded+"</td>"
    +"<td><a class=\"btn btn-danger\" onclick=\"discount.deleteCallback(" + discount.id +")\">Delete</a></td></tr>");
  }
}

Discount.prototype.addCallback = function(e){
  e.preventDefault();

  if (!this.validateInput()) {
    return handleWrongInput();
  }
  var obj = this.makeJSONObject();
  ajaxService.POST(this.urlApi.add, obj, this.render.bind(this));
}

Discount.prototype.deleteCallback = function(i) {
  ajaxService.DELETE(this.urlApi.delete + i, {}, this.render.bind(this), window.location.reload());
}

var discount = new Discount(
  ['discountPercent', 'pointsNeeded'],
  {
    'add': '/app/DiscountPoints/editDiscounts',
    'showAll': '/app/DiscountPoints/findDiscounts',
    'delete':"/app/DiscountPoints/deleteDiscounts/"
  }
);