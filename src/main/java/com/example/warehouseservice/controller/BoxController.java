package com.example.warehouseservice.controller;

import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.warehouseservice.constant.WarehouseConstant;
import com.example.warehouseservice.exception.WarehouseException;
import com.example.warehouseservice.model.request.Box;
import com.example.warehouseservice.model.response.SuccessResponse;
import com.example.warehouseservice.model.response.WarehouseServiceResponse;
import com.example.warehouseservice.service.BoxService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller class for creating and fetching boxes
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2022
 */

@Slf4j
@AllArgsConstructor
@RequestMapping(path = "/warehouse/v1/box")
@Api(value = "Box Service")
@RestController
public class BoxController
{
	private final BoxService _boxService;

	/**
	 * Create Box Operation to create box with given capacity
	 * @param box Box Information
	 * @return Stored response of Box
	 * @throws WarehouseException throw BoxName or Box Capacity is null or empty
	 */
	@ApiOperation(value = "Create Box with user specified capacity")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully created the box with specified capacity"),
		@ApiResponse(code = 401, message = "Not Authorized to view the resources"),
		@ApiResponse(code = 404, message = "The resource you where trying to reach is not found"),
		@ApiResponse(code = 500, message = "Internal Server error occurred")
	})
	@PostMapping(path = "/createBox", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createBox(@Valid @RequestBody Box box) throws WarehouseException
	{
		try
		{
			_boxService.storeBoxInformation(box);

			SuccessResponse createBoxSuccessResponse = SuccessResponse.builder().status(WarehouseConstant.OK.getString())
				.message(WarehouseConstant.BOX_CREATED.getString()).build();

			WarehouseServiceResponse warehouseServiceResponse = WarehouseServiceResponse.<SuccessResponse>builder()
				.warehouseServiceResponse(createBoxSuccessResponse).build();

			return new ResponseEntity<>(warehouseServiceResponse, HttpStatus.CREATED);
		}
		catch (Exception exp)
		{
			log.error("Exception occurred while creating the BOx {}", exp);
			throw new WarehouseException(WarehouseConstant.BOX_ALREADY_EXIT.getString());
		}
	}

	/**
	 * Get Box with available capacity
	 * @return Boxes with available capacity are fetche
	 * @throws WarehouseException throw box with available capacity not found
	 */
	@ApiOperation(value = "Get Box with available capacity")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Boxes with available capacity are fetched"),
		@ApiResponse(code = 401, message = "Not Authorized to view the resources"),
		@ApiResponse(code = 404, message = "The resource you where trying to reach is not found"),
		@ApiResponse(code = 500, message = "Internal Server error occurred")
	})
	@GetMapping(path = "/getAvailableBox", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAvailableBoxes() throws WarehouseException
	{
		List<String> availableBoxInformationList = _boxService.getAvailableBoxList();

		if (Objects.isNull(availableBoxInformationList))
		{
			throw new WarehouseException(WarehouseConstant.BOXES_WITH_AVAILABLE_CAPACITY_NOT_FOUND.getString());
		}

		WarehouseServiceResponse warehouseServiceResponse = WarehouseServiceResponse.<List<String>>builder()
			.warehouseAvailableBoxList(availableBoxInformationList).build();

		return new ResponseEntity<>(warehouseServiceResponse, HttpStatus.OK);
	}
}
