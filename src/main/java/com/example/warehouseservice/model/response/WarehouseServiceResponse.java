package com.example.warehouseservice.model.response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonView
public class WarehouseServiceResponse<T>
{
	private final T warehouseSearchProductList;
	private final T warehouseAvailableBoxList;
	private final T warehouseServiceResponse;
}
