<!DOCTYPE html>

<html lang="en" ng-app="crudApp">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>
    	<div class="custom-width-logo">
    		<img src="images/logo_infracommerce.png" />
    	</div>
    	<div style="padding-left: 24px;">
    		<span class="lead"><a href="http://infracommerce.herokuapp.com/infracommerce">Usuários</a></span>
    		<span class="lead"><a href="http://infracommerce.herokuapp.com/infracommerce/product">Produtos</a></span>
    		<span class="lead"><a href="http://http://infracommerce.herokuapp.com/infracommerce/shopping">Shopping</a></span>
    	</div>
        <div ui-view></div>
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/app/user/app.js"></script>
        <script src="js/app/user/UserService.js"></script>
        <script src="js/app/user/UserController.js"></script>
    </body>
</html>