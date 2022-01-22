package com.example.warehouseservice.jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.warehouseservice.jpa.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, String>
{
	ProductEntity findByProductName(String productName);

	List<ProductEntity> findByProductNameContainsIgnoreCaseOrderByProductNameAsc(String productName);
}
