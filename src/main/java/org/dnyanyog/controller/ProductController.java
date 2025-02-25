package org.dnyanyog.controller;

import org.dnyanyog.dto.ProductRequest;
import org.dnyanyog.dto.ProductResponse;
import org.dnyanyog.entity.Product;
import org.dnyanyog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
	@Autowired
	ProductService service;

	@PostMapping(path = "/product")
	public ProductResponse saveProduct(@RequestBody ProductRequest req) {

		return service.saveProduct(req);
	}

	@GetMapping(path = "/product/{name}")
	public Product getByName(@PathVariable String name) {
		return service.getByName(name);
	}

	@DeleteMapping(path = "/product/{name}")
	public ProductResponse removeProduct(@PathVariable String name) {
		return service.removeProduct(name);
	}

}
