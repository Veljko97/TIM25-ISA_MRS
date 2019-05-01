function Friends(varName) {
    this.accepted = [];
    this.pending = [];
    this.confirmation = [];
    this.varName = varName;

    this.foundUsers = [];
    this.pageNumber = [];
};

Friends.prototype.arivedCallback = function(data){
    data = JSON.parse(data);
    var friends = this;
    var flag = false;
    $.each(this.pending, function(index, item){
        if(item.id === data.id){
            $("#pendingTable > tbody > tr")[index].remove();
            friends.pending.splice(index,1);
            flag = true;
            return false;
        }
    });
    if(flag){
        this.accepted.unshift(data);
        this.renderTable("pendingTable",this.pending);
        this.renderAcceptedTable.call(this,this.accepted);
    }else   {
        this.confirmation.unshift(data);
        this.renderConfirmationTable.call(this,this.confirmation);
    }
}

Friends.prototype.responseCallback = function(data) {
    data = JSON.parse(data);
    var friends = this;
    $.each(this.pending, function(index, item){
        if(item.id === data.friendRequest.id){
            $("#pendingTable > tbody > tr")[index].remove();
            friends.pending.splice(index,1);
            return false;
        }
    });
    if(data.isConfirmed) {
        this.accepted.unshift(data.friendRequest);
        this.renderAcceptedTable.call(this,this.accepted);
    }else {
        alert(data.friendRequest.other.name + " te je ODBIO!!!")
    }
    this.renderTable("pendingTable",this.pending);
}

Friends.prototype.deleteCallback = function(data) {
    data = JSON.parse(data);
    var friends = this;
    $.each(this.accepted, function(index, item){
        if(item.id === data.id){
            $("#acceptedTable > tbody > tr")[index].remove();
            friends.accepted.splice(index,1);
            return false;
        }
    });
    this.renderAcceptedTable.call(this,this.accepted);
}

Friends.prototype.renderTable = function(tableId, collection) {
    var table = $("#"+tableId+" > tbody").empty();
    $.each(collection, function(index, item){
        var row = $("<tr></tr>");
        row.append("<td>"+item.other.username+"</td>");
        row.append("<td>"+item.other.firstName+"</td>");
        row.append("<td>"+item.other.lastName+"</td>");
        row.append("<td>"+item.other.email+"</td>");
        table.append(row);
    })
}

Friends.prototype.renderAcceptedTable = function(collection) {
    var table = $("#acceptedTable > tbody").empty();
    var friends = this;
    $.each(collection, function(index, item){
        var row = $("<tr></tr>");
        row.append("<td>"+item.other.username+"</td>");
        row.append("<td>"+item.other.firstName+"</td>");
        row.append("<td>"+item.other.lastName+"</td>");
        row.append("<td>"+item.other.email+"</td>");
        var addBtn = $("<button onclick=\"" + friends.varName + ".deleteFriend("+item.id+")\">Remove</button>");
        row.append(addBtn);
        table.append(row);
    })
}

Friends.prototype.renderUserTable = function(collection) {
    var table = $("#userTable > tbody").empty();
    var friends = this;
    $.each(collection, function(index, item){
        var row = $("<tr></tr>");
        row.append("<td>"+item.username+"</td>");
        row.append("<td>"+item.firstName+"</td>");
        row.append("<td>"+item.lastName+"</td>");
        row.append("<td>"+item.email+"</td>");
        var addBtn = $("<button onclick=\"" + friends.varName + ".addUser("+item.id+")\">Add Friend</button>");
        row.append(addBtn);
        table.append(row);
    })
}

Friends.prototype.renderConfirmationTable = function(collection) {
    var table = $("#confirmationTable > tbody").empty();
    var friends = this;
    $.each(collection, function(index, item){
        var row = $("<tr></tr>");
        row.append("<td>"+item.other.username+"</td>");
        row.append("<td>"+item.other.firstName+"</td>");
        row.append("<td>"+item.other.lastName+"</td>");
        row.append("<td>"+item.other.email+"</td>");
        var acBtn = $("<button onclick=\"" + friends.varName + ".acceptRequest("+item.id+")\">Accept</button>");
        var refuseBtn = $("<button onclick=\"" + friends.varName + ".refuseRequest("+item.id+")\">Refuse</button>");
        row.append(acBtn);
        row.append(refuseBtn);
        table.append(row);
    })
}

Friends.prototype.loadFriends = function(){
    ajaxService.GET("/app/friends/myFriends", this.fillTablesCallback.bind(this));
}

Friends.prototype.refuseRequest = function(requestId){
    ajaxService.DELETE("/app/friends/removeFriend/"+requestId, this.refuseCallback.bind(this));
}

Friends.prototype.deleteFriend = function(requestId){
    ajaxService.DELETE("/app/friends/deleteFriend/"+requestId, this.deleteFriendCallback.bind(this));
}

Friends.prototype.acceptRequest = function(requestId)   {
    ajaxService.PUT("/app/friends/confirmeRequest/"+requestId.toString(), "", this.acceptedCallback.bind(this));
}

Friends.prototype.findUsers = function(event){
    event.preventDefault();

    var param = $("#serchParam").val();

    ajaxService.GET("/app/friends/findUser?serchParam="+param, this.findUsersCallback.bind(this))
}

Friends.prototype.addUser = function(userId){
    var success = function(data){
        console.log("su")
        location.reload()};
    var error = function(data){
        console.log("er")
        location.reload()};
    ajaxService.POST("/app/friends/sendRequest/"+userId,"",success,error);
}

Friends.prototype.fillTablesCallback = function(data){

    this.pending = data.pending;
    this.confirmation = data.confirmation;
    this.accepted = data.accepted;

    this.renderTable("pendingTable",this.pending);
    this.renderConfirmationTable.call(this,this.confirmation);
    this.renderAcceptedTable.call(this,this.accepted);
}

Friends.prototype.refuseCallback = function(data){
    var friends = this;
    $.each(this.confirmation, function(index, item){
        if(item.id === data){
            $("#confirmationTable > tbody > tr")[index].remove();
            friends.confirmation.splice(index,1);
            return false;
        }
    });
    this.renderConfirmationTable.call(this,this.confirmation);
}

Friends.prototype.acceptedCallback = function(data){
    var friends = this;
    $.each(this.confirmation, function(index, item){
        if(item.id === data.id){
            $("#confirmationTable > tbody > tr")[index].remove();
            friends.confirmation.splice(index,1);
            return false;
        }
    });
    this.accepted.unshift(data);
    this.renderAcceptedTable.call(this,this.accepted);
    this.renderConfirmationTable.call(this,this.confirmation);
}

Friends.prototype.deleteFriendCallback = function(data){
    var friends = this;
    $.each(this.accepted, function(index, item){
        if(item.id === data){
            $("#acceptedTable > tbody > tr")[index].remove();
            friends.accepted.splice(index,1);
            return false;
        }
    });
    this.renderAcceptedTable.call(this,this.accepted);
}

Friends.prototype.findUsersCallback = function(data){
    this.foundUsers = data.content;

    this.renderUserTable.call(this,this.foundUsers);
}