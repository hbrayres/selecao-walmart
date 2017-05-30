'use strict';

angular.module('wallmarselecao',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Login',{templateUrl:'views/login.html',controller:'LoginController'})
      .when('/Produtos',{templateUrl:'views/Produto/search.html',controller:'SearchProdutoController'})
      .when('/Produtos/new',{templateUrl:'views/Produto/detail.html',controller:'NewProdutoController'})
      .when('/Produtos/edit/:ProdutoId',{templateUrl:'views/Produto/detail.html',controller:'EditProdutoController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController($scope) {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  })
  .run(function ($rootScope, $location) {
	  $rootScope.$on('$locationChangeStart', function () {
	  });
  });
