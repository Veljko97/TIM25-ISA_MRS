var ajaxService = {
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
  }
}