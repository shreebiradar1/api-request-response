package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.dto.ProductRequest;
import org.dnyanyog.dto.ProductResponse;
import org.dnyanyog.entity.Product;
import org.dnyanyog.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ProductService {
	@Autowired
	ProductRepo repo;
	@Autowired
	Product product;
	@Autowired
	ProductResponse response;

	// Add Product
	public ProductResponse saveProduct(ProductRequest req) {
		Product prod = new Product();
		prod.setProduct_id(req.getId());
		prod.setProdName(req.getProductName());
		prod.setPrice(req.getPrice());
		prod.setQuantity(req.getQuantity());
		prod.setCategory(req.getCategory());

		repo.save(prod);

		response.setCode("0000");
		response.setMsg("Everthing is working fine");
		response.setRequest(req);

		return response;

	}

	// Search by name
	public Product getByName(String name) {
		return repo.findByProdName(name);
	}

	public ProductResponse removeProduct(String name) {
		if (null == repo.findByProdName(name)) {
			response.setCode("911");
			response.setMsg("Name Field can not be empty");
		}
		int result = repo.deleteByProdName(name);
		if (result > 0) {
			response.setCode("0000");
			response.setMsg("Product Delete Successful");
		}
		return response;
	}

	// Display all
	public List<Product> getProduct() {

		return repo.findAll();
	}
}
