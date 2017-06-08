package br.com.infracommerce.vo;

import java.io.Serializable;

import br.com.infracommerce.model.CarShopping;

public class CarShoppingVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1464075274846451132L;
	private CarShopping cars;
	

	public CarShopping getCars() {
		return cars;
	}

	public void setCars(CarShopping cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "CarShoppingVO [cars=" + cars + "]";
	}
	
}
