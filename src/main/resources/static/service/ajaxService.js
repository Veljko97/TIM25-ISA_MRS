var ajaxService = {
  SETTOKEN: function(){
    var user = JSON.parse( localStorage.getItem("user"));
    var token = "";
    if(user != null){
      token = user.token.accessToken
    }
    $.ajaxSetup({
      headers:
      { "Authorization": "Bearer "+token}
    });
  },
  GET: function(apiUrl, successCallback = function() {}, errorCallback = function() {}) {
    this.SETTOKEN();
    $.ajax({
      method:"GET",
      url: apiUrl,	
      data: {},
      contentType:"application/json; charset=utf-8",
      dataType: "json",
      success: successCallback,
      error: errorCallback
    })
  },
  POST: function(apiUrl, dataObj, successCallback = function() {}, errorCallback = function() {}) {
    this.SETTOKEN();
    console.log(dataObj);
    console.log(apiUrl);
    $.ajax({
      method:"POST",
      url: apiUrl,	
      data: dataObj,
      contentType:"application/json; charset=utf-8",
      dataType: "json",
      success: successCallback,
      error: errorCallback
    })
  },
  DELETE: function(apiUrl, dataObj, successCallback = function() {}, errorCallback = function() {}) {
    this.SETTOKEN();
    $.ajax({
      method: "DELETE",
      url: apiUrl,
      data: dataObj,
      contentType:"application/json; charset=utf-8",
      dataType: "json",
      success: successCallback,
      error: errorCallback
    })
  },
  PUT: function(apiUrl, dataObj, successCallback = function() {}, errorCallback = function() {}) {
    this.SETTOKEN();
    $.ajax({
      method: "PUT",
      url: apiUrl,
      data: dataObj,
      contentType:"application/json; charset=utf-8",
      dataType: "json",
      success: successCallback,
      error: errorCallback
    })
  },
  GETPAGE: function(apiUrl, page, successCallback = function() {}, errorCallback = function() {}) {
    this.SETTOKEN();
    $.ajax({
      method:"GET",
      url: apiUrl,	
      data: {},
      contentType:"application/json; charset=utf-8",
      dataType: "json",
      success: successCallback,
      error: errorCallback
    })
  },
  POSTFORM: function(apiUrl, dataObj, successCallback = function() {}, errorCallback = function() {}){
    this.SETTOKEN();
    $.ajax({
      method: "POST",
      url: apiUrl,
      data: dataObj,
      processData: false,
      contentType: false,
      success: successCallback,
      error: errorCallback
    })
  }
}