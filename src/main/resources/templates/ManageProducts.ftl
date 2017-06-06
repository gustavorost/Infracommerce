<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Gerenciamento de produtos </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.product.id" />
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="productName">Nome do produto</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.product.productName" id="productName" class="form-control input-sm" placeholder="Digite o nome do produto" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="description">Descrição</label>
	                        <div class="col-md-7">
	                            <textarea ng-model="ctrl.product.description" id="description" class="username form-control input-sm" placeholder="Digite a descrição" required></textarea>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="value">Valor R$</label>
	                        <div class="col-md-7">
	                            <input type="number" ng-model="ctrl.product.value" id="value" class="form-control input-sm" placeholder="Digite o valor do produto" required  ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" step="0.01"/>
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.product.id ? 'Inserir' : 'Atualizar'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Limpar</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de produtos</span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>Nome do produto</th>
		                <th>Descrição</th>
		                <th>Valor</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllProducts()">
		                <td>{{u.id}}</td>
		                <td>{{u.productName}}</td>
		                <td>{{u.description}}</td>
		                <td>{{u.value}}</td>
		                <td><button type="button" ng-click="ctrl.editProduct(u.id)" class="btn btn-success custom-width">Editar</button></td>
		                <td><button type="button" ng-click="ctrl.removeProduct(u.id)" class="btn btn-danger custom-width">Excluir</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>