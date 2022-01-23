package com.example.warehouseservice.model.response;

import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * Success Response class of warehouse service
 *
 * @author praveen kumar m
 * @version 1.0.0
 * @since 23-Jan-2022
 */
@Data
@Builder
@ApiModel(description = "Success Response")
public class SuccessResponse
{
	private String status;
	private String message;
}
