'use strict';

angular.module('productApp').controller('ProductController',
    ['ProductService', '$scope',  function( ProductService, $scope) {

        var self = this;
        self.product = {};
        self.products=[];

        self.submit = submit;
        self.getAllProducts = getAllProducts;
        self.createProduct = createProduct;
        self.updateProduct = updateProduct;
        self.removeProduct = removeProduct;
        self.editProduct = editProduct;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Enviando');
            if (self.product.id === undefined || self.product.id === null) {
                console.log('Salvando novo produto', self.product);
                createProduct(self.product);
            } else {
                updateProduct(self.product, self.product.id);
                console.log('Produto atualizado com o ID ', self.product.id);
            }
        }

        function createProduct(product) {
            console.log('Criando Produto');
            ProductService.createProduct(product)
                .then(
                    function (response) {
                        console.log('Produto criado com sucesso');
                        self.successMessage = 'Produto criado com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        self.product={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Erro na criação do produto');
                        self.errorMessage = 'Erro na criação do produto: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateProduct(product, id){
            console.log('Atualizando produto');
            ProductService.updateProduct(product, id)
                .then(
                    function (response){
                        console.log('Produto atualizado com sucesso');
                        self.successMessage='Produto atualizado com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                        reset();
                    },
                    function(errResponse){
                        console.error('Erro na atualização do Produto');
                        self.errorMessage='Erro na atualização do Produto ';
                        self.successMessage='';
                    }
                );
        }


        function removeProduct(id){
            console.log('Removendo produto id: '+id);
            ProductService.removeProduct(id)
                .then(
                    function(){
                        console.log('Produto '+id + ' removido com sucesso');
                    },
                    function(errResponse){
                        console.error('Erro de exclusão do Produto '+ id +', Erro :'+errResponse.data);
                        self.errorMessage='Erro na exclusão desse item, ele está vinculado com uma compra';
                    }
                );
        }


        function getAllProducts(){
            return ProductService.getAllProducts();
        }

        function editProduct(id) {
            self.successMessage='';
            self.errorMessage='';
            ProductService.getProduct(id).then(
                function (product) {
                    self.product = product;
                },
                function (errResponse) {
                    console.error('Erro de exclusão do produto ' + id + ', Erro :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.product={};
            $scope.myForm.$setPristine(); 
        }
    }

    ]);