window.getAllCategory = function ($scope, $rootScope, $http) {
  $scope.listCategory = [];
  $rootScope.request = {
    id: 0,
    code: "",
    name: "",
    status: 0,
  };
  let apiCategory = categoryURL + "/get-all";
  console.log("in mot cai bua ra di day la get all sp ahh");
  $http.get(apiCategory).then(function (response) {
    $scope.listCategory = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
};
window.addCategory = function ($scope, $http, $rootScope, $location) {
  $scope.listCategory = [];
  $scope.newCate = $rootScope.request;
  // let url = "http://localhost:8080/category";
  let api = categoryURL + "/add";
  //let apiGetAll = url + "/get-all";
  $http.post(api, $scope.newCate).then(function () {
    alert("Add thanh cong");
    $location.path("/view-quan-ly-category");
  }),
    function (errors) {
      console.log(errors);
    };
};
window.deleteCategory = function ($scope, $location, $http, $routeParams) {
  let id = $routeParams.id;
  let api = categoryURL + "/delete/" + id;
  $http.delete(api).then(function (response) {
    $location.path("/view-quan-ly-category");
    alert("Da xoa");
  });
};
