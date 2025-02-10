package org.dnyanyog.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dnyanyog.common.DBUtil;
import org.dnyanyog.dto.Product;
import org.dnyanyog.dto.ProductRequest;
import org.dnyanyog.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

	@Autowired
	ProductResponse response;
	
	@Autowired
	Product products;
	
	
	// Add product
	public ProductResponse addProduct(ProductRequest product) {
		String query = "Insert into product (id, name, price, quantity) values (" + product.getId() + ", '"
				+ product.getName() + "', " + product.getPrice() + ", " + product.getQuantity() + ");";

		try {
			DBUtil.executeQuery(query);
			products.setId(product.getId());
			products.setName(product.getName());
			products.setPrice(product.getPrice());
			products.setQuantity(product.getQuantity());
			
			List<Product> productList = new ArrayList<>();
			productList.add(products);
			response.setProducts(productList);

			response.setCode("0000");
			response.setMsg("New Product Added Successfully");
			return response;
		} catch (SQLException e) {
			response.setCode("911");
			response.setMsg("Some error occured");
			e.printStackTrace();
			return response;
		}

	}

	// Display Product
	public ProductResponse showproduct() {
		String query = "Select * from product";

		ResultSet result = DBUtil.resultQuery(query);

		List<Product> productList = new ArrayList<>();
		try {
			while (result.next()) {
				Product products = new Product();
				products.setId(result.getInt(1));
				products.setName(result.getString(2));
				products.setPrice(result.getInt(3));
				products.setQuantity(result.getInt(4));

				productList.add(products);
				response.setProducts(productList);
				
			}
			response.setCode("0000");
			response.setMsg("Product fetched");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.setCode("911");
			response.setMsg("Displaying product failed");
			e.printStackTrace();
		}
		return response;
	}

	// Search Product
	public ProductResponse searchProduct(String name) {
		String query = "Select * from product where name = '" + name + "';";

		ResultSet rs = DBUtil.resultQuery(query);
		try {
			rs.next();
			products.setId(rs.getInt(1));
			products.setName(rs.getString(2));
			products.setPrice(rs.getInt(3));
			products.setQuantity(rs.getInt(4));
			
			List<Product> productList = new ArrayList<>();
			productList.add(products);
			response.setProducts(productList);
			response.setCode("0000");
			response.setMsg("Product found");
			return response;

		} catch (SQLException e) {
			response.setMsg("Product not found");
			
			e.printStackTrace();
			return response;
		}
		
	}
}
