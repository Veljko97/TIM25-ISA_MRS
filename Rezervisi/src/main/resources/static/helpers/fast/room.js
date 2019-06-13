function FastRoom(thisName,urlApi) {
    FastModel.call(this, thisName, urlApi);
}

FastRoom.prototype = Object.create( FastModel.prototype )

FastRoom.prototype.setStars = function(data) {
  var selcetor  = "#Rgr"+data.idVehicle;
  $(selcetor).rateYo({
    starSize: 25,
    readOnly:true,
    totalStars: 5,
    rating: data.room.averageGrade || 0
  });
}

FastRoom.prototype.getSubEntityTableRowHtml = function(data){
    return "\
    <div class=\"row search-result\">\
      <img class=\"row-image\" src=\""+(data.room.image || "/assets/images/no-image.png")+"\">\
      <div class=\"search-content\">\
        <h6>"+data.room.roomNumber+"</h6>\
        <h6> Room Capacity: "+data.room.roomCapacity+"</h6>\
        <h6> Reservation starts: "+ (new Date(data.start)).toLocaleString()+"</h6> \
        <h6> Reservation ends: "+ (new Date(data.end)).toLocaleString()+"</h6> \
        <h6> Price: "+ data.price +"</h6> \
        <div class=\"search-group\">\
          <span class='my-rating' id='Rgr"+data.room.roomID+"'></span>\
        </div>\
        <a class=\"see-more-link\" href=\"#\" onClick=\""+this.name+".reserv("+data.resId+")\">Quick Reserve</a>\
      </div>\
    </div>";
}

var fastRoom = new FastRoom("fastRoom", {'showAllFast':"/app/hotels/allFastReservation/"+getParamFromUrl('id'), 
                                                     'reserv': "/app/hotels/takeFastReservation/"})