$(document).ready(function(){
    $("#registerForm").on('submit',function(e){
        e.preventDefault();
        
        user = {};
        user.firstName = document.getElementById("firstName").value;
        user.lastName = document.getElementById("lastName").value;
        user.city = document.getElementById("city").value;
        user.phoneNumber = document.getElementById("phoneNumber").value;
        user.email = document.getElementById("email").value;
        user.username = document.getElementById("username").value;
        user.password = document.getElementById("password").value;
        
        var formData = new FormData();
        var image = $("#profileImage")[0].files[0];
        formData.append("image", image);
        formData.append("model", JSON.stringify(user));
        ajaxService.POSTFORM("/auth/registration", formData, function(){location.replace("../LoginPage.html")});
    })
})