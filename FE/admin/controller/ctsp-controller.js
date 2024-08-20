window.getAllCTSP = function ($scope, $http, $rootScope) {
  $rootScope.request = {
    id: 0,
    productId: 0,
    categoryId: 0,
    sizeId: 0,
    materialId: 0,
    colorId: 0,
    style: "",
    quanity: 0,
    price: 0,
    moTa: "",
    trangThai: 1,
  };
  $scope.listCategory = [];
  $scope.listProduct = [];
  $scope.listMaterial = [];
  $scope.listColor = [];
  $scope.listSize = [];
  $scope.listCTSP = [];
  let api = categoryURL + "/get-all";
  let apiProduct = productURL + "/get-all";
  let apiMaterial = materialURL + "/get-all";
  let apiColor = colorURL + "/get-all";
  let apiSize = sizeURL + "/get-all";
  let apiCTSP = ctspURL + "/get-all";
  let apiCTSPPhanTrang = ctspURL + "/get-all-phan-trang";
  //khai bao cac bien phuc vu phan trang:
  $scope.currentPage = 1;
  $scope.pageSize = 5;
  $scope.totalPages = 0;
  $scope.items = [];
  //call api:
  $http
    .get(apiCTSPPhanTrang + "?" + $scope.currentPage)
    .then(function (response) {
      $scope.items = response.data.content;
      console.log("ahihi");
      console.log(response.data.content);
      $scope.totalPages = response.data.totalPages;
    });
  ///
  console.log("in mot cai bua ra di");
  $http.get(apiCTSP).then(function (response) {
    $scope.listCTSP = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
  $http.get(api).then(function (response) {
    $scope.listCategory = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
  $http.get(apiProduct).then(function (response) {
    $scope.listProduct = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
  $http.get(apiMaterial).then(function (response) {
    $scope.listMaterial = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
  $http.get(apiColor).then(function (response) {
    $scope.listColor = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
  $http.get(apiSize).then(function (response) {
    $scope.listSize = response.data;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
};
window.addCTSP = function ($scope, $http, $rootScope, $location) {
  $scope.listCTSP = [];
  $scope.newCTSP = $rootScope.request;
  console.log($scope.newCTSP);
  // let url = "http://localhost:8080/category";
  let api = ctspURL + "/add";
  // let apiGetAll = url + "/get-all";
  $http.post(api, $scope.newCTSP).then(function () {
    alert("Add thanh cong");
    $location.path("/view-quan-ly-chi-tiet-san-pham");
  }),
    function (errors) {
      console.log(errors);
    };
};
