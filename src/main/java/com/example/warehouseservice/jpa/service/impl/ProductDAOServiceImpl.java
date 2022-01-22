package com.example.warehouseservice.jpa.service.impl;

import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.example.warehouseservice.exception.WarehouseException;
import com.example.warehouseservice.jpa.entity.BoxEntity;
import com.example.warehouseservice.jpa.entity.ProductEntity;
import com.example.warehouseservice.jpa.repository.BoxRepository;
import com.example.warehouseservice.jpa.repository.ProductRepository;
import com.example.warehouseservice.jpa.service.ProductDAOService;
import com.example.warehouseservice.model.request.Product;

@Repository
public class ProductDAOServiceImpl implements ProductDAOService
{
	@Resource
	ProductRepository _productRepository;

	@Resource
	BoxRepository _boxRepository;

	@Transactional
	public void saveProductInformation(Product product) throws WarehouseException
	{
		ProductEntity productEntity;
		productEntity = _productRepository.findByProductName(product.getProductName());

		if (Objects.isNull(productEntity))
		{
			productEntity = new ProductEntity();
			productEntity.setProductName(product.getProductName());
		}

		BoxEntity boxEntity = _boxRepository.findByBoxNameAndAndBoxAvailableCapacityNot(product.getBoxName(),0);

		if (Objects.nonNull(boxEntity))
		{
			productEntity.getBoxes().add(boxEntity);
			_productRepository.save(productEntity);
			reduceBoxCapacity(boxEntity);
		}
		else
		{
			throw new WarehouseException("Product is not added due to incapacity of Box");
		}

	}

	private void reduceBoxCapacity(BoxEntity boxEntity)
	{
		boxEntity.setBoxAvailableCapacity(boxEntity.getBoxAvailableCapacity() -1);
		_boxRepository.save(boxEntity);
	}

	public List<ProductEntity> fetchProduct(String productName)
	{
		return _productRepository.findByProductNameContainsIgnoreCaseOrderByProductNameAsc(productName);
	}
}
