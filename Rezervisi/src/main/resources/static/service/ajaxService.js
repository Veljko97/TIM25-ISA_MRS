var ajaxService = {
  SETTOKEN: function(token){
    $.ajaxSetup({
      headers:
      { "Authorization": "Bearer "+token}
    });
  },
  GET: function(apiUrl, successCallback = function() {}, errorCallback = function() {}) {
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
  DELETE: function(apiUrl, successCallback = function() {}, errorCallback = function() {}) {
    $.ajax({
      method: "DELETE",
      url: apiUrl,
      contentType:"application/json; charset=utf-8",
      dataType: "json",
      success: successCallback,
      error: errorCallback
    })
  },
  PUT: function(apiUrl, dataObj, successCallback = function() {}, errorCallback = function() {}) {
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
    $.ajax({
      method:"GET",
      url: apiUrl,	
      data: {},
      contentType:"application/json; charset=utf-8",
      dataType: "json",
      success: successCallback,
      error: errorCallback
    })
  }
}