function Modal() {
  this.seat = "";
}

Modal.prototype.show = function(e, seat) {
  e.preventDefault();
  this.seat = seat;
  document.getElementById('modal').style.display = 'block';
}

Modal.prototype.close = function() {
  document.getElementById('modal').style.display = 'none';
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