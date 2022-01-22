package com.example.warehouseservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.warehouseservice.exception.WarehouseException;
import com.example.warehouseservice.jpa.entity.BoxEntity;
import com.example.warehouseservice.jpa.entity.ProductEntity;
import com.example.warehouseservice.jpa.service.ProductDAOService;
import com.example.warehouseservice.model.request.Product;
import com.example.warehouseservice.service.impl.ProductServiceImpl;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest
{
	@InjectMocks
	ProductServiceImpl _productServiceImpl;

	@Mock
	ProductDAOService _productDAOService;

	@Test
	public void shouldStoreProductInformation() throws WarehouseException
	{
		_productServiceImpl.storeProduct(new Product());

		verify(_productDAOService).saveProductInformation(any(Product.class));
	}

	@Test
	public void shouldGetSearchProductList() throws WarehouseException
	{
		Set<BoxEntity> boxEntitySet = new HashSet<>();

		boxEntitySet.add(BoxEntity.builder().boxName("testBoxName").boxCapacity(3).boxAvailableCapacity(2).build());

		ProductEntity productEntity = new ProductEntity();
		productEntity.setProductName("testProductName");

		productEntity.setBoxes(boxEntitySet);

		when(_productDAOService.fetchProduct(anyString())).thenReturn(Arrays.asList(productEntity));

		List<Product> productList = _productServiceImpl.fetchSearchProduct("testProductName");
		Product product = productList.get(0);

		Assert.assertNotNull(productList);
		Assert.assertEquals("testProductName",product.getProductName());
		Assert.assertEquals("testBoxName",product.getLocationList().get(0));
	}

	@Test(expected = WarehouseException.class)
	public void shouldThrowExceptionWhenSearchProductNameNotFound() throws WarehouseException
	{
		when(_productDAOService.fetchProduct(anyString())).thenReturn(null);
		_productServiceImpl.fetchSearchProduct("testProductName");
	}
}
