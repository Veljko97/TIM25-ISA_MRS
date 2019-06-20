function SysAdmins(attributes, urlApi) {
    Model.call(this, attributes, urlApi);
  }
  
  SysAdmins.prototype = Object.create( Model.prototype );
  
  SysAdmins.prototype.bindEvents = function() {
  }
  
  SysAdmins.prototype.showAll = function(data) {}
  
  SysAdmins.prototype.showCallback = function(airline) {}
  
  SysAdmins.prototype.show = function(index) { }
  
  SysAdmins.prototype.showUsers = function() {
    this.urlApi.users = '/auth/showUsers';
    this.urlApi.addUser = '/auth/addUser';
    ajaxService.GET(this.urlApi.users,this.showUsersTable);
    $(document).on('submit', '#addUserForm', this.addUserCallback.bind(this));
  }
  
  var SysAdmins = new SysAdmins([], {});