window.getAllChatLieu = function ($scope, $rootScope, $http) {
  $scope.marterials = [];
  $rootScope.request = {
    id: 0,
    code: "",
    name: "",
    status: 0,
  };
  let apiMar = materialURL + "/get-all";
  console.log("in mot cai bua ra di day la get all sp ahh");
  $http.get(apiMar).then(function (response) {
    $scope.marterials = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
};
window.addMarterial = function ($scope, $http, $rootScope, $location) {
  $scope.marterials = [];
  $scope.newMarterial = $rootScope.request;
  let apiMarAdd = materialURL + "/add";
  //let apiGetAll = productURL + "/get-all";
  $http.post(apiMarAdd, $scope.newMarterial).then(function () {
    $location.path("/view-quan-ly-chat-lieu");
    alert("Add thanh cong");
  }),
    function (errors) {
      console.log(errors);
    };
};
window.deleteMarterial = function ($scope, $location, $http, $routeParams) {
  let id = $routeParams.id;
  let api = materialURL + "/delete/" + id;
  $http.delete(api).then(function (response) {
    $location.path("/view-quan-ly-chat-lieu");
    alert("Da xoa");
  });
};
