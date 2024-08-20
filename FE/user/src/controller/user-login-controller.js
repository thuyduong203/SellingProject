// window.loginController = function ($scope, $rootScope, $http, $location) {
//   $scope.loginData = $rootScope.accCustomer;
//   let apiLogin = apiURL + "/guest-login";
//   $http
//     .post(apiLogin, $scope.loginData)
//     .then(function (response) {
//       console.log("ahihi");
//       console.log(response.data);
//       console.log("do ngok");
//       $location.path("/check-out");
//     })
//     .catch(function (error) {
//       $scope.message = error.data;
//     });
// };
window.loginController = function ($scope, $rootScope, $http, $location) {
  $scope.loginData = $rootScope.accCustomer;
  let apiLogin = "http://localhost:8080/auth/login";
  console.log($rootScope.accCustomer);
  console.log($scope.loginData);
  $http
    .post(apiLogin, $scope.loginData)
    .then(function (response) {
      console.log("ahihi");
      console.log(response.data);
      $rootScope.accCustomer = response.data;
      console.log("do ngok" + $rootScope.accCustomer);
      console.log("do ngok");
      $location.path("/cart");
    })
    .catch(function (error) {
      $scope.message = error.data;
    });
};
// window.quanLyTaiKhoanController = function ($scope, $rootScope, $location) {
//   // if ($rootScope.accCustomer == null) {
//   //   $location.path("/view-login-customer");
//   // } else {
//   //   console.log("ahihi");
//   //   console.log($rootScope.accCustomer);
//   // }
//   $location.path("/view-login-customer");
// };
window.quanLyTaiKhoanController = function ($scope, $rootScope, $location) {
  // Kiểm tra xem đã đăng nhập chưa
  if ($rootScope.accCustomer.code == null) {
    $location.path("/view-login-customer");
  } else {
    console.log("ahihi");
    console.log("accCustomer: " + $rootScope.accCustomer.code);
    $location.path("/show-inf-customer");
  }
};
