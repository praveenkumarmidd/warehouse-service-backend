package com.example.warehouseservice.model.response;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse
{
	private String status;
	private Date timestamp;
	private String message;
	private String description;
}
