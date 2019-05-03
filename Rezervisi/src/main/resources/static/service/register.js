$(document).ready(function(){
    $("#registerForm").on('submit',function(e){
        e.preventDefault();
        
        user = {};
        user.firstName = document.getElementById("firstName").value;
        user.lastName = document.getElementById("lastName").value;
        user.email = document.getElementById("email").value;
        user.username = document.getElementById("username").value;
        user.password = document.getElementById("password").value;
        
        $.ajax({
            url: "/auth/registration",
            method: "POST",
            dataType: "json",
            contentType:"application/json",
            data: JSON.stringify(user),
            success: function(data){
                alert("Registration successfull!");
                location.replace("LoginPage.html");
            
            },error: function(error){
                alert("Error.");
                console.log(error);
            }
        })
    })
})