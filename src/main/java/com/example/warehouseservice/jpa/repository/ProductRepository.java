package com.example.warehouseservice.jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.warehouseservice.jpa.entity.ProductEntity;

/**
 * Product repository interface for handling box transaction
 *
 * @author praveen kumar m
 * @version 1.0.0
 * @since 23-Jan-2022
 */
public interface ProductRepository extends JpaRepository<ProductEntity, String>
{
	ProductEntity findByProductName(String productName);

	List<ProductEntity> findByProductNameContainsIgnoreCaseOrderByProductNameAsc(String productName);
}
