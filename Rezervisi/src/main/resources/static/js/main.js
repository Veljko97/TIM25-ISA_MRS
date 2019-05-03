function getIndexFromUrl() {
  var search = window.location.search.substring(1);
  var vars = search.split("&");
  var pair = vars[0].split("=");
  return pair[1];
}

function handleWrongInput() {
  $('#warning-message').html('Wrong input. Fill all of the required fields and check the input!');
  $('#warning-message').attr('style', 'display: block;');

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
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      input.attr('style', 'border-color: none;');
    }
  }, 5000);

  return false;
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
      return '/admin-airlines/'
    case 'ROLE_RENTACAR_ADMIN':
      return '/admin-rentacars/'
    case 'ROLE_HOTEL_ADMIN':
      return '/admin-hotels/'
    case 'ROLE_SYS_ADMIN':
      return '/admin/'
    default:
      return ''
  }
}

function showAdminNavbar() {
  let user = JSON.parse(sessionStorage.user);
  let navbar = $("#navigationBar");
  switch(user.role) {
    case 'ROLE_AIRLINE_ADMIN':
      navbar.html("\
        <div>\
          <li><a href=\"/admin-airlines/destinations.html\">Destinations</a></li>\
          <li><a href=\"/admin-airlines/flights.html\">Flights</a></li>\
        </div>\
        <li><a href=\"#\" onclick=\"return logout(event)\">Logout</a></li>\
      ")
      return;
    case 'ROLE_RENTACAR_ADMIN':
      navbar.html("\
        <li><a href=\"/admin-rentacars/branches.html\">Branches</a></li>\
        <li><a href=\"#\" onclick=\"return logout(event)\">Logout</a></li>\
      ")
      return;
    case 'ROLE_HOTEL_ADMIN':
      navbar.html("\
        <li><a href=\"/admin-hotels/rooms.html\">Rooms</a></li>\
        <li><a href=\"#\" onclick=\"return logout(event)\">Logout</a></li>\
      ")
      return;
    case 'ROLE_SYS_ADMIN':
      navbar.html("\
      <div>\
        <li><a href=\"/admin/airlines.html\">Airlines</a></li>\
        <li><a href=\"/admin/hotels.html\">Hotels</a></li>\
        <li><a href=\"/admin/rentacars.html\">Rent a Car</a></li>\
        <li><a href=\"/admin/add-admin-sys.html\">System Admins</a></li>\
      </div>\
      <li><a href=\"#\" onclick=\"return logout(event)\">Logout</a></li>\
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
