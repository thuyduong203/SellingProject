var myApp = angular.module("testCuaHang", ["ngRoute"]);
myApp.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/admin/home", {
      templateUrl: "./pages/main.html",
      //   controller: getAllCategory,
    })
    .when("/view-quan-ly-chi-tiet-san-pham", {
      templateUrl: "./pages/view-ctsp.html",
      controller: getAllCTSP,
    })
    .when("/quan-ly-ctsp/add-ctsp", {
      templateUrl: "./pages/view-ctsp.html",
      controller: addCTSP,
    })
    .when("/view-quan-ly-san-pham", {
      templateUrl: "./pages/view-sp.html",
      controller: getAllSanPham,
    })
    .when("/product-add", {
      templateUrl: "./pages/view-sp.html",
      controller: addProduct,
    })
    .when("/product/delete/:id", {
      templateUrl: "./pages/view-sp.html",
      controller: deleteProduct,
    })
    .when("/view-quan-ly-category", {
      templateUrl: "./pages/view-category.html",
      controller: getAllCategory,
    })
    .when("/category-add", {
      templateUrl: "./pages/view-category.html",
      controller: addCategory,
    })
    .when("/category/delete/:id", {
      templateUrl: "./pages/view-category.html",
      controller: deleteCategory,
    })
    .when("/view-quan-ly-size", {
      templateUrl: "./pages/view-size.html",
      controller: getAllSize,
    })
    .when("/size-add", {
      templateUrl: "./pages/view-size.html",
      controller: addSize,
    })
    .when("/size/delete/:id", {
      templateUrl: "./pages/view-size.html",
      controller: deleteSize,
    })
    .when("/view-quan-ly-chat-lieu", {
      templateUrl: "./pages/view-chat-lieu.html",
      controller: getAllChatLieu,
    })
    .when("/marterial-add", {
      templateUrl: "./pages/view-chat-lieu.html",
      controller: addMarterial,
    })
    .when("/marterial/delete/:id", {
      templateUrl: "./pages/view-chat-lieu.html",
      controller: deleteMarterial,
    })
    .when("/view-quan-ly-mau-sac", {
      templateUrl: "./pages/view-mau-sac.html",
      controller: getAllColor,
    })
    .when("/color-add", {
      templateUrl: "./pages/view-mau-sac.html",
      controller: addColor,
    })
    .when("/color/delete/:id", {
      templateUrl: "./pages/view-mau-sac.html",
      controller: deleteColor,
    })
    .otherwise({ redirectTo: "/admin/home" });
});
