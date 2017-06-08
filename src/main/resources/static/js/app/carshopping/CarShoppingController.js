'use strict';

angular.module('carShoppingApp').controller('CarShoppingController',
    ['CarShoppingService', '$scope',  function( CarShoppingService, $scope) {
    	
        var self = this;
        self.carShopping = {};
        self.carShoppings=[];
        self.addToCarTemp=[];
        self.newQuantidade ={};
        self.arrayCar = [];

        self.submit = submit;
        self.getAllCarShoppings = getAllCarShoppings;
        self.getAllProducts 	= getAllProducts;
        self.createCarShopping 	= createCarShopping;
        self.updateCarShopping 	= updateCarShopping;
        self.removeCarShopping 	= removeCarShopping;
        self.editCarShopping 	= editCarShopping;
        self.addToCar			= addToCar;
        self.removeToCar		= removeToCar;
        self.reset = reset;
        self.valorTotal = 0;
        self.transaction = Math.floor(Math.random()*new Date().getTime());

        self.successMessage = '';
        self.errorMessage = '';
        self.errorMessageCart = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function createCarShopping(carShopping) {
            console.log('Criando carrinho de compras, total de itens: ', carShopping.length);
            CarShoppingService.createCarShopping(carShopping)
                .then(
                    function (response) {
                        console.log('carrinho de compras criado com sucesso');
                        self.successMessage = 'carrinho de compras criado com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        self.carShopping={};
                        $scope.myFormCarShopping.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Erro na criação do carrinho de compras');
                        self.errorMessage = 'Erro na criação do carrinho de compras: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
        
        function submit() {
            console.log('Enviando total de carrinho de compras: ', self.addToCarTemp.length);
            if (self.addToCarTemp.length > 0) {
                createCarShopping(self.arrayCar);
                reset();
            } 
        }
        
        function addToCar(carShopping){
        	console.log("Adicionando ao carrinho o produto: " + carShopping.productName);
        	
        	//Conferindo quantidade
        	if(carShopping.newQuantidade == null){
        		console.log("Erro, digite uma quantidade");
        		self.errorMessageCart='Digite uma quantidade ';
        		return null;
        	}

        	//Soma dos valores
        	var valorTemp 		= isNaN(parseFloat(carShopping.value)) ? 0: parseFloat(carShopping.value);
        	var valorTotalTemp	= isNaN(parseFloat(self.valorTotal)) ? 0: parseFloat(self.valorTotal);
        	self.valorTotal = self.valorTotal + (valorTemp * carShopping.newQuantidade);
        	console.log("Valor total da compra desse item: " + self.valorTotal);
        	
        	//Convertendo
        	var arrayTemp = {
                        	id 				: null ,
            	    		transactionId 	: self.transaction ,
            	    		quantidade 		: carShopping.newQuantidade,
            	    		dataCompra		: "",
            	    		value		 	: self.valorTotal,
            	    		product: {
            	    				id 		: carShopping.id
            	    			}
            				};
        	console.log("Array: " + JSON.stringify(arrayTemp));
    		self.arrayCar.push(arrayTemp);
    		self.addToCarTemp.push(carShopping);
    		self.errorMessageCart='';
        	
        }
        
        function removeToCar(carShopping){
        	console.log("Removendo do carrinho o produto: " + carShopping.newQuantidade);
        	
        	//Subtracao dos valores para o carrinho
        	var valorTemp 		= isNaN(parseFloat(carShopping.value)) ? 0: parseFloat(carShopping.value);
        	var valorTotalTemp	= isNaN(parseFloat(self.valorTotal)) ? 0: parseFloat(self.valorTotal);
        	self.valorTotal = valorTotalTemp - (valorTemp * carShopping.newQuantidade);
        	console.log(self.valorTotal);
        	
        	//Obtendo indice para remocao
        	var index = self.addToCarTemp.indexOf(carShopping);
        	self.addToCarTemp.splice(index, 1);
        }


        function updateCarShopping(carShopping, id){
            console.log('Atualizando carrinho de compras');
            CarShoppingService.updateCarShopping(carShopping, id)
                .then(
                    function (response){
                        console.log('carrinho de compras atualizado com sucesso');
                        self.successMessage='carrinho de compras com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myFormCarShopping.$setPristine();
                    },
                    function(errResponse){
                        console.error('Erro na atualização do carrinho de compras');
                        self.errorMessage='Erro na atualização do carrinho de compras ';
                        self.successMessage='';
                    }
                );
        }


        function removeCarShopping(id){
            console.log('Removendo carrinho de compras id: '+id);
            CarShoppingService.removeCarShopping(id)
                .then(
                    function(){
                        console.log('carrinho de compras '+id + ' removido com sucesso');
                    },
                    function(errResponse){
                        console.error('Erro de exclusão do carrinho de compras '+id +', Erro :'+errResponse.data);
                    }
                );
        }


        function getAllProducts(){
            return CarShoppingService.getAllProducts();
        }
        
        function getAllCarShoppings(){
            return CarShoppingService.getAllCarShoppings();
        }
        
        function editCarShopping(id) {
            self.successMessage='';
            self.errorMessage='';
            CarShoppingService.getCarShopping(id).then(
                function (carShopping) {
                    self.carShopping = carShopping;
                },
                function (errResponse) {
                    console.error('Erro de exclusão do carrinho de compras ' + id + ', Erro :' + errResponse.data);
                }
            );
        }
        
        function reset(){
            self.successMessage='';
            self.errorMessageCart='';
            self.errorMessage='';
            self.carShopping={};
            self.addToCarTemp=[];
            self.arrayCar = [];
            self.valorTotal = 0;
            self.transaction = Math.floor(Math.random()*new Date().getTime());
            $scope.myFormCarShopping.$setPristine(); 
        }
    }

    ]);