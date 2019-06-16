function getParamFromUrl(param) {
  var search = window.location.search.substring(1);
  var vars = search.split("&");
  for(let i = 0; i < vars.length; i++) {
    if (vars[i].split("=")[0] == param) {
      return vars[i].split("=")[1]; // return value of that param
    }
  }
}

// Date Fromating function
function toDatetimeLocal(date) {
  var
    ten = function (i) {
      return (i < 10 ? '0' : '') + i;
    },
    YYYY = date.getFullYear(),
    MM = ten(date.getMonth() + 1),
    DD = ten(date.getDate()),
    HH = ten(date.getHours()),
    II = ten(date.getMinutes()),
    SS = ten(date.getSeconds())
  ;
  return YYYY + '-' + MM + '-' + DD + 'T' +
            HH + ':' + II + ':' + SS;
};

function handleWrongInput() {
  $('#warning-message').html('Wrong input. Fill all of the required fields and check the input!');
  $('#warning-message').attr('style', 'display: block;');
  $('#warning-message').addClass('alert-danger');

  window.scrollTo(0, 0);

  var inputs = $("input");
  for(var i = 0; i < inputs.length - 1; i++) {
    var input = inputs.eq(i);
    if (input.val() == ("")) {
      input.attr('style', 'border-color: red;');
    }
  }

  setTimeout(function() {
    $('#warning-message').attr('style', 'display: none;');
    $('#warning-message').removeClass('alert-danger');
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      input.attr('style', 'border-color: none;');
    }
  }, 5000);

  return false;
}

function handleSuccessAction() {
  $('#warning-message').html('Action was successful!');
  $('#warning-message').attr('style', 'display: block;');
  $('#warning-message').addClass('alert-success');
  window.scrollTo(0, 0);

  setTimeout(function() {
    $('#warning-message').attr('style', 'display: none;');
    $('#warning-message').removeClass('alert-danger');
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      input.attr('style', 'border-color: none;');
    }
  }, 5000);

  return false;
}

function handleErrorAction() {
  $('#warning-message').html('Something went wrong. Please try again later.');
  $('#warning-message').attr('style', 'display: block;');
  $('#warning-message').addClass('alert-danger');
  window.scrollTo(0, 0);

  setTimeout(function() {
    $('#warning-message').attr('style', 'display: none;');
    $('#warning-message').removeClass('alert-danger');
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      input.attr('style', 'border-color: none;');
    }
  }, 5000);
}

function checkUserPermission(){
  ajaxService.SETTOKEN();
  $.ajax({
    method:"GET",
    url: document.location.pathname.split(".")[0],
    success: function(){},
    error: function(data){
      if(data.status == "404"){
        return;
      }
      location.replace("/LoginPage.html");
    }
  });
}

function getAdminUrlSlug(user) {
  switch(user.role) {
    case 'ROLE_AIRLINE_ADMIN':
      return '/admin-airlines/';
    case 'ROLE_RENTACAR_ADMIN':
      return '/admin-rentacars/';
    case 'ROLE_HOTEL_ADMIN':
      return '/admin-hotels/';
    case 'ROLE_SYS_ADMIN':
      return '/admin/';
    default:
      return '';
  }
}

function showAdminNavbar() {
  let user = JSON.parse(sessionStorage.user);
  let navbar = $("#navigationBar");
  switch(user.role) {
    case 'ROLE_AIRLINE_ADMIN':
      navbar.html("<div>\
          <li><a href=\"/admin-airlines/destinations.html\">Destinations</a></li>\
          <li><a href=\"/admin-airlines/flights.html\">Flights</a></li>\
          <li><a href=\"/admin-airlines/fastReservation.html\">Fast Reservation</a></li>\
          <li><a href=\"/admin-airlines/report.html\">Report</a></li>\
        </div>\
        <div>\
    	  <li><a href=\"/editProfile.html\">Edit profile</a></li>\
    	  <li><a href=\"#\" onclick=\"return logout(event)\">Logout</a></li>\
    	</div>\
      ")
      return;
    case 'ROLE_RENTACAR_ADMIN':
      navbar.html("\
        <div>\
          <li><a href=\"/admin-rentacars/branches.html\">Branches</a></li>\
          <li><a href=\"/admin-rentacars/vehicles.html\">Vehicles</a></li>\
          <li><a href=\"/admin-rentacars/fastReservation.html\">Fast Reservation</a></li>\
          <li><a href=\"/admin-rentacars/report.html\">Report</a></li>\
        </div>\
        <div>\
    	  <li><a href=\"/editProfile.html\">Edit profile</a></li>\
    	  <li><a href=\"#\" onclick=\"return logout(event)\">Logout</a></li>\
    	</div>\
      ")
      return;
    case 'ROLE_HOTEL_ADMIN':
      navbar.html("\
        <div>\
          <li><a href=\"/admin-hotels/rooms.html\">Rooms</a></li>\
          <li><a href=\"/admin-hotels/report.html\">Report</a></li>\
          <li><a href=\"/admin-hotels/fastReservation.html\">Fast Reservation</a></li>\
        </div>\
        <div>\
          <li><a href=\"/editProfile.html\">Edit profile</a></li>\
          <li><a href=\"#\" onclick=\"return logout(event)\">Logout</a></li>\
    	</div>\
      ")
      return;
    case 'ROLE_SYS_ADMIN':
      navbar.html("\
      <div>\
        <li><a href=\"/admin/airlines.html\">Airlines</a></li>\
        <li><a href=\"/admin/hotels.html\">Hotels</a></li>\
        <li><a href=\"/admin/rentacars.html\">Rent a Car</a></li>\
        <li><a href=\"/admin/discount.html\">Discount</a></li>\
        <li><a href=\"/admin/add-admin-sys.html\">System Admins</a></li>\
      </div>\
      <div>\
		<li><a href=\"/editProfile.html\">Edit profile</a></li>\
		<li><a href=\"#\" onclick=\"return logout(event)\">Logout</a></li>\
      </div>\
      ")
      return;
    default:
      return;
  }
}

function logout(e) {
  e.preventDefault();
  sessionStorage.user = null;
  location.replace("/index.html");
  return false;
}

function getUserServiceId() {
  let user = JSON.parse(sessionStorage.user);
  return user ? JSON.parse(sessionStorage.user).serviceId : null;
}

function getUserId() {
  let user = JSON.parse(sessionStorage.user);
  return user ? JSON.parse(sessionStorage.user).id : null;
}
