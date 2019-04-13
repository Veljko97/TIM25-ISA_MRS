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
