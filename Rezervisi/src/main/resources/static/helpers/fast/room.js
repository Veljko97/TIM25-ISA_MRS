function FastRoom(thisName,urlApi) {
    FastModel.call(this, thisName, urlApi);
}

FastRoom.prototype = Object.create( FastModel.prototype )
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
          <span>Grade: "+data.room.averageGrade+"$</span>\
        </div>\
        <a class=\"see-more-link\" href=\"#\" onClick=\""+this.name+".reserv("+data.resId+")\">Quick Reserve</a>\
      </div>\
    </div>";
}

var fastRoom = new FastRoom("fastRoom", {'showAllFast':"/app/hotels/allFastReservation/"+getParamFromUrl('id'), 
                                                     'reserv': "/app/hotels/takeFastReservation/"})