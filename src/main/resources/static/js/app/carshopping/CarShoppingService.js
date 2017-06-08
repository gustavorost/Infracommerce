'use strict';

angular.module('carShoppingApp').factory('CarShoppingService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllCarShoppings: loadAllCarShoppings,
                getAllCarShoppings: getAllCarShoppings,
                getAllProducts: getAllProducts,
                getCarShopping: getCarShopping,
                createCarShopping: createCarShopping,
                updateCarShopping: updateCarShopping,
                removeCarShopping: removeCarShopping
            };

            return factory;

            function loadAllCarShoppings() {
                console.log('Obtendo todos os carrinhos');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Todos os carrinhos obtidos com sucesso');
                            $localStorage.carShoppings = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Erro enquanto carregava os carrinhos');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function loadAllProducts() {
                console.log('Obtendo todos os produtos');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Todos os produtos obtidos com sucesso');
                            $localStorage.products = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Erro enquanto carregava os produtos');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getAllProducts(){
                return $localStorage.products;
            }
            
            function getAllCarShoppings(){
                return $localStorage.carShoppings;
            }

            function getCarShopping(id) {
                console.log('Obtendo carrinho com o id :'+id);
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Obtendo carrinhocom o id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro enquanto carregava carrinhoid :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createCarShopping(carShopping) {
            	var jsonCar = "";
            	for(var i=0; i<carShopping.length; i++){
            		 jsonCar = JSON.stringify(carShopping[i]);
            		 console.log(jsonCar);
	            	var deferred = $q.defer();
	                $http.post(urls.USER_SERVICE_API, jsonCar)
	                    .then(
	                        function (response) {
	                            loadAllCarShoppings();
	                            deferred.resolve(response.data);
	                        },
	                        function (errResponse) {
	                           console.error('Erro enquanto carregava carrinho: '+errResponse.data.errorMessage);
	                           deferred.reject(errResponse);
	                        }
	                    );
            	}
            	return deferred.promise;
            }

            function updateCarShopping(carShopping, id) {
                console.log('Atualizando carrinhocom id '+id);
                var deferred = $q.defer();
                $http.put(urls.USER_SERVICE_API + id, carShopping)
                    .then(
                        function (response) {
                            loadAllCarShoppings();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro enquanto atualizava carrinhocom o id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeCarShopping(id) {
                console.log('Excluindo carrinho com o id '+id);
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllCarShoppings();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro enquanto excluia carrinho:'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
        }
    ]);