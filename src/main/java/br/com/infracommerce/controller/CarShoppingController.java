package br.com.infracommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CarShoppingController {

	@RequestMapping("/shopping")
	String products(ModelMap modal) {
		modal.addAttribute("title", "Carrinho de compras");
		return "carshopping";
	}
}
