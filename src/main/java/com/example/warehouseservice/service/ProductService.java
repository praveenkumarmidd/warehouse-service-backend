package com.example.warehouseservice.service;

import java.util.List;
import com.example.warehouseservice.exception.WarehouseException;
import com.example.warehouseservice.model.request.Product;

public interface ProductService
{
	void storeProduct(Product product) throws WarehouseException;
	List<Product> fetchSearchProduct(String productName) throws WarehouseException;
}
