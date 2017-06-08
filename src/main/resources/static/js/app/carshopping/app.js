var app = angular.module('carShoppingApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/infracommerce',
    USER_SERVICE_API : 'http://localhost:8080/infracommerce/api/shopping/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/ManageCarShopping',
                controller:'CarShoppingController',
                controllerAs:'ctrl',
                resolve: {
                    carShopping: function ($q, CarShoppingService) {
                        console.log('Carregando todos os carrinhos');
                        var deferred = $q.defer();
                        CarShoppingService.loadAllCarShoppings().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);