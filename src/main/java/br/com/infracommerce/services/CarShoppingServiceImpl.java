	package br.com.infracommerce.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infracommerce.model.CarShopping;
import br.com.infracommerce.repositories.CarShoppingRepository;
import br.com.infracommerce.services.interfaces.CarShoppingService;

@Service("carShoppingService")
@Transactional
public class CarShoppingServiceImpl implements CarShoppingService {

	@Autowired
	private CarShoppingRepository carShoppingRepository;

	@Override
	public CarShopping findById(Long id) {
		return carShoppingRepository.findOne(id);
	}

	@Override
	public void saveCarShopping(CarShopping carShopping) {
		carShoppingRepository.save(carShopping);
	}

	@Override
	public void updateCarShopping(CarShopping carShopping) {
		saveCarShopping(carShopping);
	}

	@Override
	public void deleteCarShoppingById(Long id) {
		carShoppingRepository.delete(id);
	}

	@Override
	public void deleteAllCarShopping() {
		carShoppingRepository.deleteAll();
	}

	@Override
	public List<CarShopping> findAllCarShoppings() {
		return carShoppingRepository.findAll();
	}

	@Override
	public boolean isCarShoppingExist(CarShopping carShopping) {
		return carShoppingRepository.findByTransactionId(carShopping.getTransactionId()) != null;
	}

	@Override
	public CarShopping findByTransactionId(long transaction) {
		return carShoppingRepository.findByTransactionId(transaction);
	}

}
