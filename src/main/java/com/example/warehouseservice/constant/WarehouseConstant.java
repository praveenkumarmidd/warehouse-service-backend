package com.example.warehouseservice.constant;


public enum WarehouseConstant
{
    OK("ok"),
    KO("ko"),
    BOX_CREATED("Successfully created the box with specified capacity"),
    BOX_ALREADY_EXIT("Box already exit"),
    PRODUCT_CREATED("Product created"),
    BOXES_WITH_AVAILABLE_CAPACITY_NOT_FOUND("Box with Available capacity not found");

    private final String constants;

    WarehouseConstant(final String constants) {
        this.constants = constants;
    }

    public String getString() {
        return constants;
    }

}
