function Modal() {
  this.seat = "";
  this.id = "";
}

Modal.prototype.show = function(e, seat, id = 'modal') {
  if (e) { e.preventDefault(); }
  this.seat = seat;
  this.id = id;
  document.getElementById(id).style.display = 'block';
}

Modal.prototype.close = function() {
  document.getElementById(this.id).style.display = 'none';
}

Modal.prototype.switchMode = function(e, isAlreadyActive) {
  e.preventDefault();
  if(isAlreadyActive) {return;}  

  switch($(e.target).text()) {
    case 'For Me':
      $("#unregisteredForm").hide();
      $("#registeredForm").hide();
      $("#currentForm").show();
      break;
    case 'Registered':
      $("#unregisteredForm").hide();
      $("#currentForm").hide();
      $("#registeredForm").show();
      break;
    case 'Unregistered':
      $("#registeredForm").hide();
      $("#currentForm").hide();
      $("#unregisteredForm").show();
      break;
  }
}


var modal = new Modal();