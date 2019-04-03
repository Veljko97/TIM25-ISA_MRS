function Rooms(attributes, urlApi) {
  Model.call(this, attributes, urlApi);
}

Rooms.prototype = Object.create( Model.prototype );

Rooms.prototype.bindEvents = function() {
  $(document).on('submit', '#roomsForm', this.addCallback.bind(this));
  $(document).on('submit', '#editRoomForm', this.addCallback.bind(this));
 
}

Rooms.prototype.showAll = function(data) {
  var table = $("#roomsTable").first();
  table.html("<tr><th>Number</th><th>Description</th><th>Capacity</th></tr>");
  this.list = [];

  for(var i = 0; i < data.length; i++) {
    var room = data[i];
    this.list.push(room);
    table.html(table.html() + "<tr><td>"+ room.roomNumber + "</td><td>" + room.roomDescription + "</td><td>" + room.roomCapacity +"</td><td><a href=\"edit-room.html?id=" + i + "\">Edit</a></td><td><button id=\"removeRoomButton\" onClick=removeRoom('"+room.roomID+"')>Remove</button></td></tr>");
  }
}



Rooms.prototype.showCallback = function(room) { // izvuce sve inpute i ubacuje u value inputa onoga sta menjamo, ocitava se kada se otvara stranica za edit
	  if (room) {
	    var inputs = $("input");
	    for(var i = 0; i < inputs.length - 1; i++) {
	      var input = inputs.eq(i);
	      var inputName = input.attr("id");
	      input.attr("value", room[inputName]);
	    }
	  }
	  this.urlApi.edit = '/app/hotels/editRoom?id=' + room.roomID;
	  $(document).on('submit','#editRoomForm', this.editCallback.bind(this));
	}

	Rooms.prototype.show = function(index) {
	  ajaxService.GET('/app/hotels/getRoom?index=' + index, this.showCallback.bind(this));
	}

var rooms = new Rooms(['roomNumber', 'roomDescription','roomCapacity'], {'add': '/app/hotels/addRoom', 'showAll': '/app/hotels/showRooms', 'remove': '/app/hotels/removeRoom'});