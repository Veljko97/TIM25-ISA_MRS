function Branches(attributes, urlApi) {
  Model.call(this, attributes, urlApi);
}

Branches.prototype = Object.create( Model.prototype );

Branches.prototype.bindEvents = function() {
  $(document).on('submit', '#branchForm', this.addCallback.bind(this));
  $(document).on('submit', '#editBranchForm', this.editCallback.bind(this));
}

Branches.prototype.showAll = function(data) {
  var table = $("#branchesTable").first();
  table.html("<tr><th>Name</th><th>Address</th><th class=\"options-cell\" colspan=\"2\">Options</th></tr>");
  this.list = [];

  for(var i = 0; i < data.length; i++) {
    var branch = data[i];
    this.list.push(branch);
    table.html(table.html() + "<tr><td>"+ branch.branchName + "</td><td>" + 
    branch.branchAddress + "</td><td><a class=\"btn btn-info\" href=\"edit-branch.html?id=" + 
    branch.idBranch + "\">Edit</a></td><td><a class=\"btn btn-danger\" onclick=\"branches.deleteCallback(" + branch.idBranch +")\">Delete</a></td></tr>");
  }
}

Branches.prototype.showCallback = function(branch) {
  if (branch) {
    var inputs = $("input");
    for(var i = 0; i < inputs.length - 1; i++) {
      var input = inputs.eq(i);
      var inputName = input.attr("id");
      input.attr("value", branch[inputName]);
    }
  }
  this.urlApi.edit = '/app/rentacar/1/editBranch/' + branch.idBranch;
  $(document).on('submit', '#editBranchForm', this.editCallback.bind(this));
}

Branches.prototype.show = function(index) {
  ajaxService.GET('/app/rentacar/1/getBranch/' + index, this.showCallback.bind(this));
}

var branches = new Branches(['branchName', 'branchAddress'], {'add': '/app/rentacar/1/addBranch', 'showAll': '/app/rentacar/1/showBranches/', 'delete': '/app/rentacar/1/deleteBranch/'});