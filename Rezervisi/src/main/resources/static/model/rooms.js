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
  console.log('a');
  table.html("<tr><th>Number</th><th>Description</th><th>Capacity</th><th>Price</th><th class=\"options-cell\" colspan=\"2\">Options</th></tr>");
  this.list = [];

  for(var i = 0; i < data.length; i++) {
    var room = data[i];
    this.list.push(room);
    table.html(table.html() + "<tr><td>"+ room.roomNumber + "</td><td>" + room.roomDescription + 
    "</td><td>" + room.roomCapacity +"</td><td>"+room.price+
    "</td><td><a class=\"btn btn-info\" href=\"edit-room.html?id=" + room.roomID + 
    "\">Edit</a></td><td><a class=\"btn btn-danger\" onclick=\"rooms.deleteCallback(" + room.roomID +")\">Delete</a></td></tr>");
  }
}

Rooms.prototype.showCallback = function(room) {
	  if (room) {
	    var inputs = $("input");
	    for(var i = 0; i < inputs.length - 1; i++) {
	      var input = inputs.eq(i);
	      var inputName = input.attr("id");
	      input.attr("value", room[inputName]);
	    }
	  }
	  this.urlApi.edit = '/app/hotels/1/editRoom/' + room.roomID;
	  $(document).on('submit','#editRoomForm', this.editCallback.bind(this));
	}

	Rooms.prototype.show = function(index) {
	  ajaxService.GET('/app/hotels/1/getRoom/' + index, this.showCallback.bind(this));
	}

var rooms = new Rooms(['roomNumber', 'roomDescription','roomCapacity', 'price'], {'add': '/app/hotels/1/addRoom', 'showAll': '/app/hotels/1/showRooms', 'delete': '/app/hotels/1/deleteRoom/'});