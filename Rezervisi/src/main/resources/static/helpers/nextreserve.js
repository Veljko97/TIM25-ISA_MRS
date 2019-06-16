function NextReserve() {
  this.currentRoomPage = 0;
  this.currentVehiclePage = 0;
  this.numberOfRoomPages = 0;
  this.numberOfVehiclePages = 0;
  this.pageSize = 5;
  this.roomIds = [];
  this.vehicleIds = [];
}

NextReserve.prototype.init = function() {
  $(document).on('submit', '#searchForm', this.render.bind(this));
  ajaxService.GET('/app/DiscountPoints/findMyDiscount', this.setDiscount.bind(this));
  this.render();
}

NextReserve.prototype.showAllRooms = function(data) {
  var resultsTable = $("#rooms").first();
  resultsTable.html("");
  this.numberOfRoomPages = data.totalPages || 0;
  allData = data.content || data;

  for(let i = 0; i < allData.length; i++) {
    let data = allData[i];
    resultsTable.html(resultsTable.html() + 
    "<div class=\"row search-result\">\
    <img class=\"row-image\" src=\""+(data.image || "../assets/images/no-image.png")+"\">\
    <div class=\"search-content\">\
      <div class=\"search-group\">\
        <h4>Room Number: " + data.roomNumber + "</h4>\
        <span>Price: "+data.price+"$</span>\
        <span>Capacity: " + data.roomCapacity + "</span>\
      </div>\
      <span class=\"text-overflow\">" + data.roomDescription + "</span>\
      <a class=\"see-more-link\" onclick=\"nextreserve.reserveRoom(event, " + data.roomID + ")\" href=\"#\">Reserve</a>\
    </div>\
    ");
  }
}

NextReserve.prototype.switchRoomPage = function(dir) {
  if ((dir == -1 && this.currentRoomPage > 0) || (dir == 1 && this.currentRoomPage < (this.numberOfRoomPages - 1))) {
    this.currentRoomPage += dir;
    this.render();
  }
}

NextReserve.prototype.switchVehiclePage = function(dir) {
  if ((dir == -1 && this.currentVehiclePage > 0) || (dir == 1 && this.currentVehiclePage < (this.numberOfVehiclePages - 1))) {
    this.currentVehiclePage += dir;
    this.render();
  }
}

NextReserve.prototype.showAllVehicles = function(data) {
  var resultsTable = $("#vehicles").first();
  resultsTable.html("");
  this.numberOfVehiclePages = data.totalPages || 0;
  allData = data.content || data;

  for(let i = 0; i < allData.length; i++) {
    let data = allData[i];
    resultsTable.html(resultsTable.html() + 
    "<div class=\"row search-result\">\
        <img class=\"row-image\" src=\""+(data.image || "../assets/images/no-image.png")+"\">\
        <div class=\"search-content\">\
          <div class=\"search-group\">\
            <h4>"+data.vehicleName+"</h4>\
            <span>Grade: "+data.averageGrade+"</span>\
          </div>\
          <a class=\"see-more-link\" onclick=\"nextreserve.reserveVehicle(event, " + data.idVehicle + ")\" href=\"#\">Reserve</a>\
        </div>\
      </div>");
  }
}

NextReserve.prototype.reserveRoom = function(e, roomId)
{
  e.preventDefault();
  let obj = {};
  obj.start = Date.parse($("#startDate").val()) || 0;
  obj.end = Date.parse($("#endDate").val()) || 0;

  ajaxService.POST('/app/hotels/'+this.ticketId+'/reserve/'+ roomId, JSON.stringify(obj), function(data){
    this.roomIds.push(data);
  });
  this.render();
}

NextReserve.prototype.reserveVehicle = function(e, vehicleId)
{
  e.preventDefault();
  let obj = {};
  obj.start = Date.parse($("#startDate").val()) || 0;
  obj.end = Date.parse($("#endDate").val()) || 0;

  ajaxService.POST('/app/rentacar/'+this.ticketId+'/reserve/'+ vehicleId, JSON.stringify(obj), function(data){
    this.vehicleIds.push(data);
  });
  this.render();
}


NextReserve.prototype.render = function(e) {
  if (e) {e.preventDefault();};
  let obj = {
    'start': Date.parse($("#startDate").val()) || 0,
    'end': Date.parse($("#endDate").val()) || 0
  };

  ajaxService.POST('/app/hotels/'+this.ticketId+'/getAvailableRooms?size='+this.pageSize+'&page=' + this.currentRoomPage, JSON.stringify(obj), this.showAllRooms.bind(this));
  ajaxService.POST('/app/rentacar/'+this.ticketId+'/getAvailableVehicles?size='+this.pageSize+'&page=' + this.currentVehiclePage, JSON.stringify(obj), this.showAllVehicles.bind(this));
}

NextReserve.prototype.finish = function(e) {
  e.preventDefault();
  let obj = {
    'ticketId': this.ticketId,
    'vehicleIds': this.vehicleIds,
    'roomIds': this.roomIds,
    'usePoints': $("#discountBox").prop("checked")
  };
  ajaxService.POST('/app/airlines/finishReservation', JSON.stringify(obj), function() {window.location.replace('/');});
}

var nextreserve = new NextReserve();



NextReserve.prototype.setDiscount = function (data) {
  if(data.id == -1){
    $("#discountBox").prop('disabled', true);
    $("#discountBoxLab").text("You don't have enough points for a discount");
  }else {
    $("#discountBoxLab").text("Use " + data.pointsNeeded + " points for " + data.discountPercent + "% discount");
  }
}
