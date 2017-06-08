package br.com.infracommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infracommerce.model.CarShopping;

@Repository
public interface CarShoppingRepository extends JpaRepository<CarShopping, Long> {
	CarShopping findByTransactionId(long transaction);
}
