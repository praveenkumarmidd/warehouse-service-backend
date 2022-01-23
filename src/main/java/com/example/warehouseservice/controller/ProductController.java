package com.example.warehouseservice.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.warehouseservice.constant.WarehouseConstant;
import com.example.warehouseservice.exception.WarehouseException;
import com.example.warehouseservice.model.request.Product;
import com.example.warehouseservice.model.response.SuccessResponse;
import com.example.warehouseservice.model.response.WarehouseServiceResponse;
import com.example.warehouseservice.service.ProductService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller class for adding and fetching Product Information
 *
 * @author praveen kumar m
 * @version 1.0.0
 * @since 23-Jan-2022
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/warehouse/v1/product")
@Api(value = "Product Service", tags = { "Product" })
public class ProductController
{
	private final ProductService _productService;

	/**
	 * Get Available Product for given product name search
	 *
	 * @param productName Product Name to be search
	 * @return Matching products list for given product name
	 * @throws WarehouseException throw product name exception when product name is null or empty
	 */
	@ApiOperation(value = "Get Available Product for given product name search")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully fetched product information for given productName"),
		@ApiResponse(code = 400, message = "Invalid Request params "),
		@ApiResponse(code = 401, message = "Not Authorized to view the resources"),
		@ApiResponse(code = 404, message = "The resource you where trying to reach is not found"),
		@ApiResponse(code = 500, message = "Internal Server error occurred")
	})
	@GetMapping(path = "/getProducts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchProductInformationByName(
		@ApiParam(value = "productName", example = "TestProduct", required = true)
		@RequestParam final String productName) throws WarehouseException
	{
		WarehouseServiceResponse searchProductListResponse = WarehouseServiceResponse.<List<Product>>builder()
			.warehouseSearchProductList(_productService.fetchSearchProduct(productName)).build();

		log.debug("Product search successful");

		return new ResponseEntity<>(searchProductListResponse, HttpStatus.OK);
	}

	/**
	 * Create Product or Add existing Product to the mentioned box
	 *
	 * @param product Product detail information
	 * @return store product information response
	 * @throws WarehouseException thrown mandatory product information if null or empty
	 */
	@ApiOperation(value = "Create Product or Add existing Product to the mentioned box")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Successfully created/added the product to the mention box"),
		@ApiResponse(code = 401, message = "Not Authorized to view the resources"),
		@ApiResponse(code = 404, message = "The resource you where trying to reach is not found"),
		@ApiResponse(code = 500, message = "Internal Server error occurred")
	})
	@PutMapping(path = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createProduct(@Valid @RequestBody final Product product) throws WarehouseException
	{
		_productService.storeProduct(product);

		SuccessResponse createProductSuccessResponse = SuccessResponse.builder().status(WarehouseConstant.OK.getString())
			.message(WarehouseConstant.PRODUCT_CREATED.getString()).build();

		WarehouseServiceResponse createProductResponse = WarehouseServiceResponse.<SuccessResponse>builder()
			.warehouseSearchProductList(createProductSuccessResponse).build();

		log.debug("Successful Product created or added to the specified box");

		return new ResponseEntity<>(createProductResponse, HttpStatus.CREATED);
	}
}