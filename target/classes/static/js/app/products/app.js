var app = angular.module('productApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/infracommerce',
    USER_SERVICE_API : 'http://localhost:8080/infracommerce/api/product/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/ManageProducts',
                controller:'ProductController',
                controllerAs:'ctrl',
                resolve: {
                    products: function ($q, ProductService) {
                        console.log('Carregando todos os produtos');
                        var deferred = $q.defer();
                        ProductService.loadAllProducts().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

