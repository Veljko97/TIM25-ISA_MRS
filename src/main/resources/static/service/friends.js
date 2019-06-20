function friendLisener(arived = function(){}, response =  function(){}, deleted =  function(){}) {
    var socket = new SockJS('/friendRequests');
    var stompClient = Stomp.over(socket);
    var user = JSON.parse(localStorage.user);
    stompClient.connect({}, function(frame) {
        stompClient.subscribe("/request/"+user.id, function(data){
            arived(data.body);
        });
        stompClient.subscribe("/response/"+user.id, function(data){
            response(data.body);
        });
        stompClient.subscribe("/deleted/"+user.id, function(data){
            deleted(data.body);
        });
    });
};