package com.example.warehouseservice.model.response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;

/**
 * Generic Response class of warehouse service
 *
 * @author praveen kumar m
 * @version 1.0.0
 * @since 23-Jan-2022
 */
@Getter
@Builder
@JsonView
public class WarehouseServiceResponse<T>
{
	private final T warehouseSearchProductList;
	private final T warehouseAvailableBoxList;
	private final T warehouseServiceResponse;
}
