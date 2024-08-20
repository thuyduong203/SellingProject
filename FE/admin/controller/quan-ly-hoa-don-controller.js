// $http({
//   method: 'GET',
//   url: 'http://localhost:8080/admin/bill-get-all',
//   headers: {
//     'Authorization': 'Bearer your_jwt_token_here'
//   }
// }).then(function(response) {
//   // Xử lý phản hồi thành công
//   console.log(response.data);
// }).catch(function(error) {
//   // Xử lý lỗi
//   console.error(error);
// });
window.getHD = function ($scope, $rootScope, $http) {
  $scope.listHDChuaThanhToan = [];
  $scope.listHoaDon = [];
  $rootScope.request = {
    id: 0,
    code: "",
    name: "",
    status: 0,
  };
  //trang Thai == 1 => chua TT
  let apiHDChuaTT = billURL + "/bill-get-bill-by-status/1";
  let apiAllHD = billURL + "/bill-get-all";
  console.log("token ahh");
  console.log($rootScope.token);

  ///
  $http({
    method: "GET",
    url: "http://localhost:8080/admin/bill-get-all",
    headers: {
      Authorization: "Bearer " + $rootScope.token,
      // Authorization: $rootScope.token,
    },
  })
    .then(function (response) {
      // Xử lý phản hồi thành công
      $scope.listHoaDon = response.data;
      console.log(response.data);
    })
    .catch(function (error) {
      // Xử lý lỗi
      console.error(error);
    });
  $http({
    method: "GET",
    url: "http://localhost:8080/admin/bill-get-bill-by-status/1",
    headers: {
      Authorization: "Bearer " + $rootScope.token,
      // Authorization: $rootScope.token,
    },
  })
    .then(function (response) {
      // Xử lý phản hồi thành công
      $scope.listHDChuaThanhToan = response.data;
      console.log(response.data);
    })
    .catch(function (error) {
      // Xử lý lỗi
      console.error(error);
    });
  //
  // $http.get(apiHDChuaTT).then(function (response) {
  //   $scope.listHDChuaThanhToan = response.data;
  //   console.log(response.data);
  // }),
  //   function (errors) {
  //     console.log(errors);
  //   };
  // $http.get(apiAllHD).then(function (response) {
  //   $scope.listHoaDon = response.data;
  //   console.log(response.data);
  // }),
  //   function (errors) {
  //     console.log(errors);
  //   };
};
// window.addProduct = function ($scope, $http, $rootScope, $location) {
//   $scope.listProduct = [];
//   $scope.newProduct = $rootScope.request;
//   let apiProduct = productURL + "/add";
//   let apiGetAll = productURL + "/get-all";
//   $http.post(apiProduct, $scope.newProduct).then(function () {
//     $location.path("/view-quan-ly-san-pham");
//     alert("Add thanh cong");
//   }),
//     function (errors) {
//       console.log(errors);
//     };
// };
// window.deleteProduct = function ($scope, $location, $http, $routeParams) {
//   let id = $routeParams.id;
//   let api = productURL + "/delete/" + id;
//   $http.delete(api).then(function (response) {
//     $location.path("/view-quan-ly-san-pham");
//     alert("Da xoa");
//   });
// };
