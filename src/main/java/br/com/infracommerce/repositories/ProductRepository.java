package br.com.infracommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infracommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findByProductName(String productName);
}
