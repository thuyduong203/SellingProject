window.homeController = function ($scope, $http, $rootScope) {
  $scope.listCategory = [];
  // $rootScope.accCustomer = {
  //   id: 0,
  //   code: null,
  //   name: null,
  //   address: null,
  //   phoneNumber: null,
  //   email: null,
  //   password: null,
  // };
  $rootScope.accCustomer = {
    id: 0,
    code: null,
    email: null,
    address: null,
    roleName: null,
    jwt: null,
  };
  $rootScope.cartDto = {
    id: 0,
    code: null,
    customerId: null,
  };
  let api = categoryURL + "/get-all";
  console.log("in mot cai bua ra di");
  $http.get(api).then(function (response) {
    $scope.listCategory = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
};
window.cartController = function (
  $scope,
  $http,
  $rootScope,
  $routeParams,
  $location
) {
  $scope.listCartDetail = [];
  let idCustomer = $routeParams.id;
  if (idCustomer == undefined || idCustomer == null || idCustomer == 0) {
    $location.path("/view-login-customer");
  } else {
    let api = cartDetailURL + "/get-cart-detail-by-idCustomer/" + idCustomer;
    console.log("in mot cai bua ra di");
    console.log("in mot cai bua ra di: idCustomer=" + idCustomer);
    $http.get(api).then(function (response) {
      $scope.listCartDetail = response.data;
      console.log(response.data);
    }),
      function (errors) {
        console.log(errors);
      };
  }
};
