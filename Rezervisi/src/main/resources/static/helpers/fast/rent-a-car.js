function FastRentACar(thisName,urlApi) {
    FastModel.call(this, thisName, urlApi);
}

FastRentACar.prototype = Object.create( FastModel.prototype );

FastRentACar.prototype.setStars = function(data) {
  var selcetor  = "#Vgr"+data.idVehicle;
  $(selcetor).rateYo({
    starSize: 25,
    readOnly:true,
    totalStars: 5,
    rating: data.vehicle.averageGrade || 0
  });
}

FastRentACar.prototype.getSubEntityTableRowHtml = function(data){
    return "\
    <div class=\"row search-result\">\
      <img class=\"row-image\" src=\""+(data.vehicle.image || "/assets/images/no-image.png")+"\">\
      <div class=\"search-content\">\
        <h4>"+data.vehicle.vehicleName+"</h4>\
        <h4> Reservation starts: "+ (new Date(data.start)).toLocaleString()+"</h4>\
        <h4> Reservation ends: "+ (new Date(data.end)).toLocaleString()+"</h4> \
        <h4> Price: "+ data.price +"</h4> \
        <div class=\"search-group\">\
          <span class='my-rating' id='Vgr"+data.vehicle.idVehicle+"'></span>\
        </div>\
        <a class=\"see-more-link\" href=\"#\" onClick=\""+this.name+".reserv("+data.resId+")\">Quick Reserve</a>\
      </div>\
    </div>";
}

var fastRentACar = new FastRentACar("fastRentACar", {'showAllFast':"/app/rentacar/allFastReservation/"+getParamFromUrl('id'), 
                                                     'reserv': "/app/rentacar/takeFastReservation/"});