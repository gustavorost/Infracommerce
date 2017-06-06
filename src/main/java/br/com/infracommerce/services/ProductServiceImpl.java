package br.com.infracommerce.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infracommerce.model.Product;
import br.com.infracommerce.repositories.ProductRepository;
import br.com.infracommerce.services.interfaces.ProductService;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository; 
	
	@Override
	public Product findById(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		saveProduct(product);
	}

	@Override
	public void deleteProductById(Long id) {
		productRepository.delete(id);
	}

	@Override
	public void deleteAllProducts() {
		productRepository.deleteAll();
	}

	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public boolean isProductExist(Product product) {
		return productRepository.findByProductName(product.getProductName()) != null;
	}

	@Override
	public Product findByProductName(String productName) {
		return productRepository.findByProductName(productName);
	}
	
	

}
