'use strict';

angular.module('crudApp').controller('UserController',
    ['UserService', '$scope',  function( UserService, $scope) {

        var self = this;
        self.user = {};
        self.users=[];

        self.submit = submit;
        self.getAllUsers = getAllUsers;
        self.createUser = createUser;
        self.updateUser = updateUser;
        self.removeUser = removeUser;
        self.editUser = editUser;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Enviando');
            if (self.user.id === undefined || self.user.id === null) {
                console.log('Salvando novo usuario', self.user);
                createUser(self.user);
            } else {
                updateUser(self.user, self.user.id);
                console.log('Usuario atualizado com o ID ', self.user.id);
            }
        }

        function createUser(user) {
            console.log('Criando usuário');
            UserService.createUser(user)
                .then(
                    function (response) {
                        console.log('Usuário criado com sucesso');
                        self.successMessage = 'Usuário criado com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        self.user={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Erro na criação do usuário');
                        self.errorMessage = 'Erro na criação do usuário: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateUser(user, id){
            console.log('Atualizando usuário');
            UserService.updateUser(user, id)
                .then(
                    function (response){
                        console.log('Usuário atualizado com sucesso');
                        self.successMessage='Usuário atualizado com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        reset();
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Erro na atualização do usuário');
                        self.errorMessage='Erro na atualização do usuário ';
                        self.successMessage='';
                    }
                );
        }


        function removeUser(id){
            console.log('Removendo usuário id: '+id);
            UserService.removeUser(id)
                .then(
                    function(){
                        console.log('Usuário '+id + ' removido com sucesso');
                    },
                    function(errResponse){
                        console.error('Erro de exclusão do usuário '+id +', Erro :'+errResponse.data);
                    }
                );
        }


        function getAllUsers(){
            return UserService.getAllUsers();
        }

        function editUser(id) {
            self.successMessage='';
            self.errorMessage='';
            UserService.getUser(id).then(
                function (user) {
                    self.user = user;
                },
                function (errResponse) {
                    console.error('Erro de exclusão do usuário ' + id + ', Erro :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.user={};
            $scope.myForm.$setPristine(); 
        }
    }

    ]);