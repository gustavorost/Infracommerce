package br.com.infracommerce.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.infracommerce.model.Product;
import br.com.infracommerce.services.interfaces.ProductService;
import br.com.infracommerce.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class ProductRestController {

	public static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);

	@Autowired
	ProductService productService; 

	@RequestMapping(value = "/product/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listAllProducts() {
		List<Product> products = productService.findAllProducts();
		if (products.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProduct(@PathVariable("id") long id) {
		logger.info("Obtendo produto {}", id);
		Product product = productService.findById(id);
		if (product == null) {
			logger.error("Produto com o id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Produto com o id " + id + " não encontrado"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/", method = RequestMethod.POST)
	public ResponseEntity<?> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		logger.info("Criando produto : {}", product);

		if (productService.isProductExist(product)) {
			logger.error("Um produto com a descrição {} já existe", product.getDescription());
			return new ResponseEntity(
					new CustomErrorType("Um produto com a descrição " + product.getDescription() + " já existe."),
					HttpStatus.CONFLICT);
		}
		productService.saveProduct(product);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/product/{id}").buildAndExpand(product.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		logger.info("Atualizando produto {}", id);

		Product currentProduct = productService.findById(id);

		if (currentProduct == null) {
			logger.error("Produto com o id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Produto com o id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}

//		currentProduct.setProductName(product.getProductName());

		productService.updateProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
		logger.info("Obtendo e excluindo o produto com o id {}", id);

		Product product = productService.findById(id);
		if (product == null) {
			logger.error("Produto com o id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Produto com o id " + id + "  não encontrado."),
					HttpStatus.NOT_FOUND);
		}
		productService.deleteProductById(id);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/product/", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteAllProduct() {
		logger.info("Deletando todos os produtos");

		productService.deleteAllProducts();
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

}
