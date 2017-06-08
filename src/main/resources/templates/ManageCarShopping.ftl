<div class="generic-container">
    <div class="panel panel-default">
         <div class="panel panel-default">
        
        <!-- Loading all products to insert a car -->
		<div class="panel-heading"><span class="lead">Produtos disponíveis</span></div>
		<div class="panel-body">
			<div class="table-responsive">
			<div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessageCart">{{ctrl.errorMessageCart}}</div>
			<form name="productForm"> 
			        <table class="table table-hover">
			            <thead>
			            <tr>
			                <th>Produtos</th>
			                <th>Valor</th> 
			                <th>Quantidade desejada</th> 
			                <th width="100"></th>
			                <th width="100"></th>
			            </tr>
			            </thead>
			            <tbody>
			            <tr ng-repeat="u in ctrl.getAllProducts()">
			                <td>{{u.productName}}</td>
			                <td>{{u.value}}</td>
			                <td>
			                	<input type="number" id="newQuantidade" name="newQuantidade" 
			                	 ng-model="u.newQuantidade" class="form-control input-sm" 
			                	 placeholder="Digite a quantidade" style="width:145px"  
			                	 ng-disabled="ctrl.addToCarTemp.indexOf(u) !== -1" 
			                	 required ng-minlength="1" min="0" max="{{u.quantidade}}"/>
			                	 <p ng-show="productForm.newQuantidade.$error.maxlength">Quantidade inserida maior que a disponível.</p>
			                	 
			                </td>
			                <td>
			                	<button type="button" ng-click="ctrl.addToCar(u)" class="btn btn-success custom-width" 
			                	ng-disabled="ctrl.addToCarTemp.indexOf(u) !== -1">Adicionar</button>
			                </td>
			            </tr>
			            </tbody>
			        </table>	
		        </form>	
			</div>
		</div>
        
        <div class="panel-heading"><span class="lead">Carrinho de compras </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myFormCarShopping" class="form-horizontal">
	
					<div class="row">
	                    <div class="form-group col-md-12" >
	                        <label class="col-md-2 control-lable" for="description"></label>
	                        <div class="col-md-7">
	                            <table class="table table-hover">
						            <thead>
						            <tr>
						                <th>Descrição</th>
						                <th>Preço</th>
						                <th>Quantidade</th>
						                <th width="100"></th>
						            </tr>
						            </thead>
						            <tbody>
						            <tr ng-repeat="productAtCar in ctrl.addToCarTemp">
						                <td>{{productAtCar.productName}}</td>
						                <td>{{productAtCar.description}}</td>
						                <td> {{productAtCar.newQuantidade}}</td>
						                <td><button type="button" style="width:130px !important;" ng-click="ctrl.removeToCar(productAtCar)" class="btn btn-danger custom-width">Excluir item</button></td>
						            </tr>
						            <tr>
						            	<td colspan="4"><h4>Total: {{ctrl.valorTotal | currency : symbol : fractionSize}}</h4></td>
						            </tr>
						            </tbody>
						        </table>
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="Concluir compra" class="btn btn-primary btn-sm" ng-disabled="ctrl.addToCarTemp == 0">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="ctrl.addToCarTemp == 0">Limpar itens</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de compras efetuadas</span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>Transação</th>
		                <th>Produtos</th>
		                <th>Quantidade</th>
		                <th>Data</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllCarShoppings()">
		                <td>{{u.transactionId}}</td>
		                <td>{{u.product.productName}}</td>
		                <td>{{u.quantidade}}</td>
		                <td>{{u.dataCompra | date:'dd/MM/yyyy'}}</td>
		                <td><button type="button" style="width:130px !important;" ng-click="ctrl.removeCarShopping(u.id)" class="btn btn-danger custom-width">Excluir Compra</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>