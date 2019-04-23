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
