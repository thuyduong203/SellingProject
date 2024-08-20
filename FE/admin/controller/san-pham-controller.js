window.getAllSanPham = function ($scope, $rootScope, $http) {
  $scope.listProduct = [];
  $rootScope.request = {
    id: 0,
    code: "",
    name: "",
    status: 0,
  };
  let apiProduct = productURL + "/get-all";
  console.log("in mot cai bua ra di day la get all sp ahh");
  $http.get(apiProduct).then(function (response) {
    $scope.listProduct = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
};
window.addProduct = function ($scope, $http, $rootScope, $location) {
  $scope.listProduct = [];
  $scope.newProduct = $rootScope.request;
  let apiProduct = productURL + "/add";
  let apiGetAll = productURL + "/get-all";
  $http.post(apiProduct, $scope.newProduct).then(function () {
    $location.path("/view-quan-ly-san-pham");
    alert("Add thanh cong");
  }),
    function (errors) {
      console.log(errors);
    };
};
window.deleteProduct = function ($scope, $location, $http, $routeParams) {
  let id = $routeParams.id;
  let api = productURL + "/delete/" + id;
  $http.delete(api).then(function (response) {
    $location.path("/view-quan-ly-san-pham");
    alert("Da xoa");
  });
};
