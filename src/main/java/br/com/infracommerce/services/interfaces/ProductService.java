package br.com.infracommerce.services.interfaces;

import java.util.List;

import br.com.infracommerce.model.Product;

public interface ProductService {
	Product findById(Long id);

	void saveProduct(Product product);

	void updateProduct(Product product);

	void deleteProductById(Long id);

	void deleteAllProducts();

	List<Product> findAllProducts();

	boolean isProductExist(Product product);
	
	Product findByProductName(String productName);
}
