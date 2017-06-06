package br.com.infracommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
	
	@RequestMapping("/product")
	String products(ModelMap modal){
		modal.addAttribute("title","Produtos");
		return "product";
	}
	
}
