package com.example.warehouseservice.jpa.service;

import java.util.List;
import com.example.warehouseservice.exception.WarehouseException;
import com.example.warehouseservice.jpa.entity.ProductEntity;
import com.example.warehouseservice.model.request.Product;

public interface ProductDAOService
{
	void saveProductInformation(Product product) throws WarehouseException;

	List<ProductEntity> fetchProduct(String productName);
}
