var myApp = angular.module("myModule", ["ngRoute"]);
myApp.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/home", {
      templateUrl: "./pages/trang-chu.html",
      controller: homeController,
    })
    .when("/contact", {
      templateUrl: "./pages/contact-custom.html",
    })
    .when("/shop", {
      templateUrl: "./pages/shop-custom.html",
      controller: shopController,
    })
    .when("/shopping-cart", {
      templateUrl: "./pages/cart2.html",
    })
    .when("/shop-detail", {
      templateUrl: "./pages/detail-custom.html",
    })
    .when("/check-out", {
      templateUrl: "./pages/checkout-custom.html",
    })
    .when("/detail-product/:id", {
      templateUrl: "./pages/detail-product.html",
      controller: detailProductController,
    })
    .when("/them-vao-gio-hang/:id", {
      templateUrl: "./pages/detail-product.html",
      controller: themVaoGioHangController,
    })
    .when("/cart", {
      templateUrl: "./pages/cart2.html",
    })
    .when("/view-login-customer", {
      templateUrl: "./pages/view-login.html",
    })
    // .when("/quan-ly-tai-khoan-user", {
    //   // templateUrl: "./pages/quan-ly-tai-khoan.html",
    //   templateUrl: "./pages/view-login.html",
    //   // controller: quanLyTaiKhoanController,
    // })
    .when("/guest-login", {
      templateUrl: "./pages/view-login.html",
      controller: loginController,
    })
    // Route quản lý tài khoản user
    .when("/quan-ly-tai-khoan-user", {
      templateUrl: "./pages/quan-ly-tai-khoan.html",
      controller: quanLyTaiKhoanController,
      // templateUrl: "./pages/view-login.html",
    })
    .when("/show-inf-customer", {
      templateUrl: "./pages/acc-customer.html",
    })
    .when("/gio-hang/:id", {
      templateUrl: "./pages/cart2.html",
      controller: cartController,
    })
    .otherwise({ redirectTo: "/home" });
});
