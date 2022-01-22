package com.example.warehouseservice.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import com.example.warehouseservice.constant.WarehouseConstant;
import com.example.warehouseservice.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Global Exception handler
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2022
 */
@Slf4j
@RestControllerAdvice
public class GenericExceptionHandler
{

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleMandatoryFields(MethodArgumentNotValidException ex)
	{
		log.error("Exception occurred due to missing mandatory method arguments {}",ex);
		return ErrorResponse.builder()
			.status(WarehouseConstant.KO.getString())
			.timestamp(new Date())
			.message(ex.getMessage().concat(" Request Body is missing"))
			.description("Mandatory fields are missing").build();
	}

	@ExceptionHandler({ MissingServletRequestParameterException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleMissingParams(MissingServletRequestParameterException ex)
	{
		log.error("Exception occurred due to missing params arguments {}",ex);
		return ErrorResponse.builder()
			.status(WarehouseConstant.KO.getString())
			.timestamp(new Date())
			.message(ex.getParameterName().concat(" Request parameter is missing"))
			.description("Request Param Product Name is mandatory").build();
	}

	@ExceptionHandler({ WarehouseException.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse warehouseServiceCustomException(WarehouseException warehouseException)
	{
		log.error("Exception occurred due to internal server error {}",warehouseException);
		return ErrorResponse.builder()
			.status(WarehouseConstant.KO.getString())
			.timestamp(new Date())
			.message(warehouseException.getMessage())
			.description("Internal Server Error occurred in Warehouse Service").build();
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse genericException(Exception exception)
	{
		log.error("Exception occurred while processing the request {}",exception);
		return ErrorResponse.builder()
			.status(WarehouseConstant.KO.getString())
			.timestamp(new Date())
			.message(exception.getMessage())
			.description("Exception occurred in Warehouse Service").build();
	}
}
