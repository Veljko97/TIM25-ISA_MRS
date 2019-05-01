function getMainPageNavBar() {
  let user = JSON.parse(sessionStorage.user);
  let navBarHtml = user ? '\
    <li class="nav-item"><a class="nav-link" href="">Home</a></li>\
    <li class="nav-item"><a class="nav-link" href="/about-us.html">About us</a></li>\
    <li class="nav-item"><a class="nav-link" href="/guest/search.html">Search</a></li>\
    <li style="width: 150px;" class="nav-item"><img class="pb_rounded-4 profil-img px-4" src="./assets/images/user-default.png"/></li>'
  : 
  ' <li class="nav-item"><a class="nav-link" href="">Home</a></li>\
    <li class="nav-item"><a class="nav-link" href="/about-us.html">About us</a></li>\
    <li class="nav-item"><a class="nav-link" href="/guest/search.html">Search</a></li>\
    <li class="nav-item cta-btn ml-xl-2 ml-lg-2 ml-md-0 ml-sm-0 ml-0"><a class="nav-link" href="/LoginPage.html"><span class="pb_rounded-4 px-4">Get Started</span></a></li>';
  $("#navBar").html(navBarHtml);

}