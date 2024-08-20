window.getAllSize = function ($scope, $rootScope, $http) {
  $scope.sizes = [];
  $rootScope.request = {
    id: 0,
    code: "",
    name: "",
    status: 0,
  };
  let apiSize = sizeURL + "/get-all";
  console.log("in mot cai bua ra di day la get all sz ahh");
  $http.get(apiSize).then(function (response) {
    $scope.sizes = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
};
window.addSize = function ($scope, $http, $rootScope, $location) {
  $scope.sizes = [];
  $scope.newSz = $rootScope.request;
  let apiAddSz = sizeURL + "/add";
  //   let apiGetAll = productURL + "/get-all";
  $http.post(apiAddSz, $scope.newSz).then(function () {
    $location.path("/view-quan-ly-size");
    alert("Add thanh cong");
  }),
    function (errors) {
      console.log(errors);
    };
};
window.deleteSize = function ($scope, $location, $http, $routeParams) {
  let id = $routeParams.id;
  let api = sizeURL + "/delete/" + id;
  $http.delete(api).then(function (response) {
    $location.path("/view-quan-ly-size");
    alert("Da xoa");
  });
};
