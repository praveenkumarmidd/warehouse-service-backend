package com.example.warehouseservice.model.response;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Data
@Builder
@ApiModel(description = "Success Response")
public class SuccessResponse
{
	private String status;
	private String message;
}
