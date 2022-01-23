package com.example.warehouseservice.exception;

/**
 * Custom Exception handler of the warehouse service
 *
 * @author praveen kumar m
 * @version 1.0.0
 * @since 23-Jan-2022
 */
public class WarehouseException extends Exception
{
	public WarehouseException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
