window.shopController = function ($scope, $http) {
  $scope.listProduct = [];
  let api = productURL + "/get-all";
  console.log("in mot cai bua ra di");
  $http.get(api).then(function (response) {
    $scope.listProduct = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
};
