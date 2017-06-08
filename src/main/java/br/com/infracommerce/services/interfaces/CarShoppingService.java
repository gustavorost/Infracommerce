package br.com.infracommerce.services.interfaces;

import java.util.List;

import br.com.infracommerce.model.CarShopping;

public interface CarShoppingService {
	CarShopping findById(Long id);

	void saveCarShopping(CarShopping carShopping);

	void updateCarShopping(CarShopping carShopping);

	void deleteCarShoppingById(Long id);

	void deleteAllCarShopping();

	List<CarShopping> findAllCarShoppings();

	boolean isCarShoppingExist(CarShopping carShopping);
	
	CarShopping findByTransactionId(long transaction);
}
