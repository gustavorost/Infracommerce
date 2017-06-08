package br.com.infracommerce.controller.rest;

import java.util.Calendar;
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

import br.com.infracommerce.model.CarShopping;
import br.com.infracommerce.services.interfaces.CarShoppingService;
import br.com.infracommerce.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class CarShoppingRestController {

	public static final Logger logger = LoggerFactory.getLogger(CarShoppingRestController.class);

	@Autowired
	CarShoppingService carShoppingService; 

	@RequestMapping(value = "/shopping/", method = RequestMethod.GET)
	public ResponseEntity<List<CarShopping>> listAllCarShoppings() {
		List<CarShopping> carShopping = carShoppingService.findAllCarShoppings();
		if (carShopping.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CarShopping>>(carShopping, HttpStatus.OK);
	}

	@RequestMapping(value = "/shopping/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCarShooping(@PathVariable("id") long id) {
		logger.info("Obtendo carrinho {}", id);
		CarShopping carShopping = carShoppingService.findById(id);
		if (carShopping == null) {
			logger.error("carrinho com o id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Carrinho de compras com o id " + id + " não encontrado"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CarShopping>(carShopping, HttpStatus.OK);
	}

	@RequestMapping(value = "/shopping/", method = RequestMethod.POST)
	public ResponseEntity<?> createCarShopping(@RequestBody CarShopping carShopping, UriComponentsBuilder ucBuilder) {
		logger.info("Criando carrinho : {}", carShopping);

		if (carShoppingService.isCarShoppingExist(carShopping)){
			logger.error("Um carrinho com a descrição {} já existe", carShopping);
			return new ResponseEntity(
					new CustomErrorType("Um carrinho com a descrição " + carShopping.getId() + " já existe."),
					HttpStatus.CONFLICT);
		}
		carShopping.setDataCompra(Calendar.getInstance());
		carShoppingService.saveCarShopping(carShopping);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/shopping/{id}").buildAndExpand(carShopping).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/shopping/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCarShopping(@PathVariable("id") long id, @RequestBody CarShopping carShopping) {
		logger.info("Atualizando carrinho {}", id);

		CarShopping currentCarShopping = carShoppingService.findById(id);

		if (currentCarShopping == null) {
			logger.error("carrinho com o id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("carrinho com o id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}

		carShoppingService.updateCarShopping(carShopping);
		return new ResponseEntity<CarShopping>(carShopping, HttpStatus.OK);
	}

	@RequestMapping(value = "/shopping/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCarShopping(@PathVariable("id") Long id) {
		logger.info("Obtendo e excluindo o carrinho com o id {}", id);

		CarShopping carShopping = carShoppingService.findById(id);
		if (carShopping == null) {
			logger.error("Carrinho com o id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Carrinho com o id " + id + "  não encontrado."),
					HttpStatus.NOT_FOUND);
		}
		carShoppingService.deleteCarShoppingById(id);
		return new ResponseEntity<CarShopping>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/shopping/", method = RequestMethod.DELETE)
	public ResponseEntity<CarShopping> deleteAllCarShopping() {
		logger.info("Deletando todos os carinhos");

		carShoppingService.deleteAllCarShopping();
		return new ResponseEntity<CarShopping>(HttpStatus.NO_CONTENT);
	}

}
