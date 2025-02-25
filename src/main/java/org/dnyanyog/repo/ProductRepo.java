package org.dnyanyog.repo;

import org.dnyanyog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	public Product findByProdName(String name);
	
	@Modifying
	@Transactional
	public int deleteByProdName(String name);
}
