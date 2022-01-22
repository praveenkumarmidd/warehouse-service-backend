package com.example.warehouseservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.example.warehouseservice.exception.WarehouseException;
import com.example.warehouseservice.jpa.entity.ProductEntity;
import com.example.warehouseservice.jpa.service.ProductDAOService;
import com.example.warehouseservice.model.request.Product;
import com.example.warehouseservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService
{
	private final ProductDAOService _productDAOService;

	@Override
	public void storeProduct(Product product) throws WarehouseException
	{
		_productDAOService.saveProductInformation(product);
	}

	@Override
	@Transactional
	public List<Product> fetchSearchProduct(String productName) throws WarehouseException
	{
		List<ProductEntity> productEntityList = _productDAOService.fetchProduct(productName);

		if (CollectionUtils.isEmpty(productEntityList))
		{
			log.warn("Product not found for given product name");
			throw new WarehouseException("Product not found for given search");
		}

		return productEntityList.stream().map(product -> buildProduct(product)).collect(Collectors.toList());
	}

	private Product buildProduct(ProductEntity productEntity)
	{
		return Product.builder().productName(productEntity.getProductName())
			.locationList(productEntity.getBoxes().stream().map(box -> box.getBoxName()).collect(
				Collectors.toList())).build();
	}
}
