package com.Sathya.mvc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sathya.mvc.entity.ProductEntity;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	
}
