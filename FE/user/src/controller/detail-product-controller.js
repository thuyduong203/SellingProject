window.detailProductController = function (
  $scope,
  $http,
  $routeParams,
  $rootScope
) {
  let id = $routeParams.id;
  console.log(id);
  $scope.sizes = [];
  $scope.colors = [];
  $scope.categories = [];
  $scope.marterials = [];
  $scope.productDetailReq = {
    categoryId: 1,
    productId: $routeParams.id,
    colorId: 1,
    sizeId: 1,
    materialId: 1,
  };
  console.log("id:" + id);
  let apiSizeByProduct =
    "http://localhost:8080/guest/product-detail/get-size-by-product-id/" + id;
  let apiColor =
    "http://localhost:8080/guest/product-detail/get-color-by-product-id/" + id;
  let apiCategory =
    "http://localhost:8080/guest/product-detail/get-category-by-product-id/" +
    id;
  let apiMarterial =
    "http://localhost:8080/guest/product-detail/get-material-by-product-id/" +
    id;
  let apiGetOneProduct = productURL + "/get-one/" + id;
  let apiGetOnePrDByAttribute = ctspURL + "/get-one-by-attribute";
  $rootScope.soLuong = 1;
  //get one product:
  $http.get(apiGetOneProduct).then(function (response) {
    $scope.product = response.data;
    console.log(response.data);
    console.log("alo?");
  }),
    function (errors) {
      console.log(errors);
    };

  $http.get(apiSizeByProduct).then(function (response) {
    $scope.sizes = response.data;
    $scope.sizeId = 1;
    console.log("alo?");
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
  $http.get(apiColor).then(function (response) {
    $scope.colors = response.data;
    $scope.colorId = 1;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
  $http.get(apiCategory).then(function (response) {
    $scope.categories = response.data;
    $scope.categoryId = 1;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
  $http.get(apiMarterial).then(function (response) {
    $scope.marterials = response.data;
    $scope.materialId = 1;
    console.log(response.data);
  }),
    function (errors) {
      console.log(errors);
    };
  $http
    .get(
      apiGetOnePrDByAttribute +
        "?categoryId=" +
        $scope.productDetailReq.categoryId +
        "&productId=" +
        $scope.productDetailReq.productId +
        "&colorId=" +
        $scope.productDetailReq.colorId +
        "&sizeId=" +
        $scope.productDetailReq.sizeId +
        "&materialId=" +
        $scope.productDetailReq.materialId
    )
    .then(function (response) {
      $scope.ctsp = response.data;
      console.log("productDetailReq:", $scope.productDetailReq);
      console.log(response.data);
    })
    .catch(function (errors) {
      console.log(errors);
    });
  //hiển thi số lượng theo ctsp
  $scope.updateQuantity = function () {
    $scope.newReq = $scope.productDetailReq;
    $http
      .get(
        apiGetOnePrDByAttribute +
          "?categoryId=" +
          $scope.productDetailReq.categoryId +
          "&productId=" +
          $scope.productDetailReq.productId +
          "&colorId=" +
          $scope.productDetailReq.colorId +
          "&sizeId=" +
          $scope.productDetailReq.sizeId +
          "&materialId=" +
          $scope.productDetailReq.materialId
      )
      .then(function (response) {
        $scope.ctsp = response.data;
        if ($scope.ctsp) {
        } else {
          $scope.ctsp = {}; // Khởi tạo $scope.ctsp nếu không tồn tại
          $scope.ctsp.quanity = 0; // Gán giá trị quantity mặc định
        }
      })
      .catch(function (errors) {
        console.log(errors);
      });
  };
};
window.themVaoGioHangController = function (
  $rootScope,
  $http,
  $routeParams,
  $location,
  $scope
) {
  if ($rootScope.accCustomer.code == null) {
    $location.path("/view-login-customer");
  } else {
    let idCTSP = $routeParams.id;
    let idCustomer = $rootScope.accCustomer.id;
    let apiGetCartByCustomer = cartURL + "/get-by-customerId/" + idCustomer;
    $http
      .get(apiGetCartByCustomer)
      .then(function (response) {
        $rootScope.cartDto = response.data;
        console.log("ahihi");
        console.log($rootScope.cartDto);

        $scope.cartDetail = {
          id: 0,
          cartId: $rootScope.cartDto.id,
          productDetailId: idCTSP,
          quanity: $scope.soLuong,
          price: 100,
        };

        console.log($scope.cartDetail);

        let apiAddCartDetail = cartDetailURL + "/add";
        $http
          .post(apiAddCartDetail, $scope.cartDetail)
          .then(function () {
            $location.path("/home");
            alert("Add thành công");
          })
          .catch(function (errors) {
            console.log(errors);
          });
      })
      .catch(function (errors) {
        console.log(errors);
      });
  }
};
