package org.dnyanyog.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dnyanyog.common.DBUtil;
import org.dnyanyog.dto.ProductRequest;
import org.dnyanyog.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ProductService {

	@Autowired
	ProductResponse response;

	public ProductResponse addProduct(ProductRequest product) {
		String query = "Insert into product (id, name, price, quantity) values (" + product.getId() + ", '"
				+ product.getName() + "', " + product.getPrice() + ", " + product.getQuantity() + ");";
		
		try {
			DBUtil.executeQuery(query);
			response.setId(product.getId());
			response.setName(product.getName());
			response.setPrice(product.getPrice());
			response.setQuantity(product.getQuantity());
			
			response.setCode("0000");
			response.setMsg("New Product Added Successfully");
		} catch (SQLException e) {
			response.setCode("911");
			response.setMsg("Some error occured");
			e.printStackTrace();
		}
		return response;
	}
	
	public List<ProductResponse> showproduct(){
		List<ProductResponse> product = new ArrayList<>();
		String query = "Select * from product";
		
		ResultSet result = DBUtil.resultQuery(query);
		
		try {
			while(result.next()) {
				response.setId(result.getInt(1));
				response.setName(result.getString(2));
				response.setPrice(result.getInt(3));
				response.setQuantity(result.getInt(4));
				
				product.add(response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.setCode("911");
			response.setMsg("Displaying product failed");
			e.printStackTrace();
		}
		return product;
	}
}
