package com.example.warehouseservice.jpa;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
import com.example.warehouseservice.jpa.repository.BoxRepository;
import com.example.warehouseservice.jpa.repository.ProductRepository;
import com.example.warehouseservice.jpa.service.impl.ProductDAOServiceImpl;
import com.example.warehouseservice.model.request.Product;

@RunWith(SpringRunner.class)
public class ProductDAOServiceImplTest
{
	@InjectMocks
	ProductDAOServiceImpl _productDAOServiceImpl;

	@Mock
	ProductRepository _productRepository;

	@Mock
	BoxRepository _boxRepository;

	@Test
	public void shouldSaveProductWithBoxInformation() throws WarehouseException
	{
		when(_productRepository.findByProductName(anyString())).thenReturn(null);
		when(_boxRepository.findByBoxNameAndAndBoxAvailableCapacityNot(anyString(), anyInt())).thenReturn(getBoxEntityDetails());

		_productDAOServiceImpl.saveProductInformation(getProductDetails());

		verify(_productRepository).save(any(ProductEntity.class));
		verify(_boxRepository).save(any(BoxEntity.class));
	}

	@Test
	public void shouldMapExistingProductToNewBoxLocation() throws WarehouseException
	{
		ProductEntity productEntity = getProductEntityDetails();

		when(_productRepository.findByProductName(anyString())).thenReturn(productEntity);
		when(_boxRepository.findByBoxNameAndAndBoxAvailableCapacityNot(anyString(), anyInt())).thenReturn(getBoxEntityDetails());

		_productDAOServiceImpl.saveProductInformation(getProductDetails());

		verify(_productRepository).save(productEntity);
	}

	@Test(expected = WarehouseException.class)
	public void shouldNotMapProductToBoxWhenAvailableCapacityOfBoxLimitZero() throws WarehouseException
	{
		ProductEntity productEntity = getProductEntityDetails();

		when(_productRepository.findByProductName(anyString())).thenReturn(productEntity);
		when(_boxRepository.findByBoxNameAndAndBoxAvailableCapacityNot(anyString(), anyInt())).thenReturn(null);

		_productDAOServiceImpl.saveProductInformation(getProductDetails());
	}

	@Test
	public void shouldGetProductInformationByAvailableProductNameSearch()
	{
		ProductEntity productEntity = getProductEntityDetails();

		BoxEntity boxEntity = new BoxEntity();
		boxEntity.setBoxId("1");
		boxEntity.setBoxName("testBoxName");
		boxEntity.setBoxCapacity(3);
		boxEntity.setBoxAvailableCapacity(2);

		Set<BoxEntity> boxEntitySet = new HashSet<>();
		boxEntitySet.add(boxEntity);
		productEntity.setBoxes(boxEntitySet);

		when(_productRepository.findByProductNameContainsIgnoreCaseOrderByProductNameAsc(anyString()))
			.thenReturn(Arrays.asList(productEntity));

		List<ProductEntity> availableProducts = _productDAOServiceImpl.fetchProduct("testProductName");
		ProductEntity searchProductResult = availableProducts.get(0);
		BoxEntity boxInformation = searchProductResult.getBoxes().stream().findFirst().get();

		Assert.assertNotNull(availableProducts); //Product Information Assertion
		Assert.assertEquals("1", searchProductResult.getProductId());
		Assert.assertEquals("testProductName", searchProductResult.getProductName());

		Assert.assertNotNull(searchProductResult.getBoxes()); //Box Information Assertion
		Assert.assertNotNull("1", boxInformation.getBoxId());
		Assert.assertNotNull("testBoxName", boxInformation.getBoxName());
		Assert.assertNotNull("3", boxInformation.getBoxCapacity());
		Assert.assertNotNull("2", boxInformation.getBoxAvailableCapacity());
	}

	@Test
	public void shouldNotGetProductInformationByUnAvailableProductNameSearch()
	{
		when(_productRepository.findByProductNameContainsIgnoreCaseOrderByProductNameAsc(anyString()))
			.thenReturn(null);

		List<ProductEntity> unAvailableProducts =
			_productDAOServiceImpl.fetchProduct("testProductName");

		Assert.assertNull(unAvailableProducts);
	}

	private Product getProductDetails()
	{
		return Product.builder().productName("testProductName").boxName("testBoxName").build();
	}

	private ProductEntity getProductEntityDetails()
	{
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProductId("1");
		productEntity.setProductName("testProductName");

		return productEntity;
	}

	private BoxEntity getBoxEntityDetails()
	{
		BoxEntity boxEntity = new BoxEntity();
		boxEntity.setBoxName("testBoxName");
		boxEntity.setBoxAvailableCapacity(1);
		boxEntity.setBoxCapacity(3);

		return boxEntity;
	}
}
