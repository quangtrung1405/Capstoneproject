package com.hcl.shopforhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.shopforhome.bean.Products;
import com.hcl.shopforhome.service.ProductService;

@RestController
@RequestMapping("/ProductCRUD")
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService prodService;

	@PostMapping(value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Products CreateUserInfo(@RequestBody Products prod) {
		return prodService.createProduct(prod);
	}

	@GetMapping(value = "retrieveProducts", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Products> retrieveUserDetails() {
		return prodService.retrieveProducts();
	}

	@PutMapping(value = "updateProduct")
	public Products updateUserdetails(@RequestBody Products prod) {
		return prodService.updateProduct(prod);
	}

	@DeleteMapping(value = "deleteProduct/{id}")
	public String deleteUserDetails(@PathVariable("id") int id) {
		return prodService.deleteProduct(id);
	}
}
