window.getAllColor = function ($scope, $rootScope, $http) {
  $scope.colors = [];
  $rootScope.request = {
    id: 0,
    code: "",
    name: "",
    status: 0,
  };
  let apiColor = colorURL + "/get-all";
  console.log("in mot cai bua ra di day la get all sp ahh");
  $http.get(apiColor).then(function (response) {
    $scope.colors = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
};
window.addColor = function ($scope, $http, $rootScope, $location) {
  $scope.colors = [];
  $scope.newColor = $rootScope.request;
  let apiAddColor = colorURL + "/add";
  $http.post(apiAddColor, $scope.newColor).then(function () {
    $location.path("/view-quan-ly-mau-sac");
    alert("Add thanh cong");
  }),
    function (errors) {
      console.log(errors);
    };
};
window.deleteColor = function ($scope, $location, $http, $routeParams) {
  let id = $routeParams.id;
  let api = colorURL + "/delete/" + id;
  $http.delete(api).then(function (response) {
    $location.path("/view-quan-ly-mau-sac");
    alert("Da xoa");
  });
};
