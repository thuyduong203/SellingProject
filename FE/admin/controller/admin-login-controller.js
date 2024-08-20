window.indexAdminController = function ($rootScope) {
  $rootScope.accAdmin = {
    id: 0,
    code: null,
    name: null,
    address: null,
    phoneNumber: null,
    email: null,
    password: null,
    jwt: null,
  };
  // $rootScope.loginAdminData = {
  //   email: null,
  //   password: null,
  // };
};
window.loginAdminController = function ($scope, $rootScope, $http, $location) {
  $scope.loginReq = $rootScope.accAdmin;
  let apiLogin = "http://localhost:8080/auth/login";
  console.log($rootScope.accAdmin);
  console.log($scope.loginReq);
  $http
    .post(apiLogin, $scope.loginReq)
    .then(function (response) {
      console.log(response.data);
      $rootScope.accAdmin = response.data;
      // console.log($rootScope.accAdmin);
      // console.log($rootScope.accAdmin.jwt);
      $rootScope.token = $rootScope.accAdmin.jwt;
      $location.path("/admin/home2");
    })
    .catch(function (error) {
      $scope.message = error.data;
    });
};
