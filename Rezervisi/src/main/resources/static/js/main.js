function getIndexFromUrl() {
  var search = window.location.search.substring(1);
  var vars = search.split("&");
  var pair = vars[0].split("=");
  return pair[1];
}
