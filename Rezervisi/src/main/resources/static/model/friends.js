function Friends(varName) {
    this.accepted = [];
    this.pending = [];
    this.confirmation = [];
    this.varName = varName;

    this.acceptedPg = 0;
    this.pendingPg = 0;
    this.confirmationPg = 0;
    this.pageSize = 2;

    this.foundUsers = [];
    this.pageNumber = [];
};

Friends.prototype.arivedCallback = function(data){
    data = JSON.parse(data);
    var friends = this;
    var flag = false;
    $.each(this.pending, function(index, item){
        if(item.id === data.id){
            $("#pendingTable")[index].remove();
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
            $("#pendingTable")[index].remove();
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
            $("#acceptedTable")[index].remove();
            friends.accepted.splice(index,1);
            return false;
        }
    });
    this.renderAcceptedTable.call(this,this.accepted);
}

Friends.prototype.renderTable = function(tableId, collection) {
    var res = $("#"+tableId).empty();
    var friends = this;
    var page = this.pendingPg * this.pageSize;
    for(var i = page; i < page+this.pageSize; i++){
        var item = collection[i];
        var div = "\
        <div class=\"row search-result\" style=\"background-color: steelblue;\">\
            <img class=\"row-image\" src=\""+(item.other.image || "../assets/images/no-image.png")+"\"onerror=\"errorImage(this)\">\
            <div class=\"search-content\">\
            <h4>"+item.other.username+"</h4>\
            <h4>First Name: "+item.other.firstName+"</h4>\
            <h4>Last Name: "+item.other.lastName+"</h4>\
            <h4>E-mail: "+item.other.email+"</h4>\
            <a class=\"see-more-link\" href=\"/guest/user.html?id="+item.other.id+"\">See more</a>";
        div +=    "</div>\
                </div>";
        res.append(div);
    }
}

Friends.prototype.renderAcceptedTable = function(collection) {
    var res = $("#acceptedTable").empty();
    var friends = this;
    var page = this.acceptedPg * this.pageSize;
    for(var i = page; i < page+this.pageSize; i++){
        var item = collection[i];
        var div = "\
        <div class=\"row search-result\">\
            <img class=\"row-image\" src=\""+(item.other.image || "../assets/images/no-image.png")+"\"onerror=\"errorImage(this)\">\
            <div class=\"search-content\">\
            <h4>"+item.other.username+"</h4>\
            <h4>First Name: "+item.other.firstName+"</h4>\
            <h4>Last Name: "+item.other.lastName+"</h4>\
            <h4>E-mail: "+item.other.email+"</h4>\
            <a class=\"see-more-link\" href=\"/guest/user.html?id="+item.other.id+"\">See more</a>";
        div += "<a class=\"see-more-link\" href=\"#\" onclick=\"" + friends.varName + ".deleteFriend("+item.id+")\">Remove</a>";
        div +=    "</div>\
                </div>";
        res.append(div);
    }
}


Friends.prototype.renderConfirmationTable = function(collection) {
    var res = $("#confirmationTable").empty();
    var friends = this;
    var page = this.confirmationPg * this.pageSize;
    for(var i = page; i < page+this.pageSize; i++){
        var item = collection[i];
        var div = "\
        <div class=\"row search-result\">\
            <img class=\"row-image\" src=\""+(item.other.image || "../assets/images/no-image.png")+"\"onerror=\"errorImage(this)\">\
            <div class=\"search-content\">\
            <h4>"+item.other.username+"</h4>\
            <h4>First Name: "+item.other.firstName+"</h4>\
            <h4>Last Name: "+item.other.lastName+"</h4>\
            <h4>E-mail: "+item.other.email+"</h4>\
            <a class=\"see-more-link\" href=\"/guest/user.html?id="+item.id+"\">See more</a>";
        div += "<a class=\"see-more-link\" href=\"#\" onclick=\"" + friends.varName + ".acceptRequest("+item.id+")\">Accept</a>";
        div += "<a class=\"see-more-link\" href=\"#\" onclick=\"" + friends.varName + ".refuseRequest("+item.id+")\">Refuse</a>";
        div +=    "</div>\
                </div>";
        res.append(div);
    }
}

Friends.prototype.loadFriends = function(){
    ajaxService.GET("/app/users/myFriends", this.fillTablesCallback.bind(this));
}

Friends.prototype.refuseRequest = function(requestId){
    ajaxService.DELETE("/app/users/removeFriend/"+requestId, this.refuseCallback.bind(this));
}

Friends.prototype.deleteFriend = function(requestId){
    ajaxService.DELETE("/app/users/deleteFriend/"+requestId, this.deleteFriendCallback.bind(this));
}

Friends.prototype.acceptRequest = function(requestId)   {
    ajaxService.PUT("/app/users/confirmeRequest/"+requestId.toString(), "", this.acceptedCallback.bind(this));
}

Friends.prototype.findUsers = function(event){
    event.preventDefault();

    var param = $("#serchParam").val();

    ajaxService.GET("/app/users/findUser?serchParam="+param, this.findUsersCallback.bind(this))
}

Friends.prototype.addUser = function(userId){
    var success = function(data){
        console.log("su")
        location.reload()};
    var error = function(data){
        console.log("er")
        location.reload()};
    ajaxService.POST("/app/users/sendRequest/"+userId,"",success,error);
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
            $("#confirmationTable > .row")[index].remove();
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
            $("#confirmationTable > .row")[index].remove();
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
            $("#acceptedTable > .row")[index].remove();
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

Friends.prototype.switchPagePanding = function(direction) {
    var next = this.pendingPg + direction
    if(next < 0){
        return;
    }
    if((this.pendingPg + 1 ) * this.pageSize > this.pending.length && direction == 1){
        return;
    }
    this.pendingPg = next;
    this.renderTable("pendingTable",this.pending);
}

Friends.prototype.switchPageAcept = function(direction) {
    var next = this.acceptedPg + direction
    if(next < 0){
        return;
    }
    if((this.acceptedPg + 1 ) * this.pageSize > this.accepted.length && direction == 1){
        return;
    }
    this.acceptedPg = next;
    this.renderAcceptedTable.call(this,this.accepted);
}

Friends.prototype.switchPageConf = function(direction) {
    var next = this.confirmationPg + direction
    if(next < 0){
        return;
    }
    if((this.confirmationPg + 1 ) * this.pageSize > this.confirmation.length && direction == 1){
        return;
    }
    this.confirmationPg = next;
    this.renderConfirmationTable.call(this,this.confirmation);
}