package com.example.warehouseservice.model.request;

import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.*;

/**
 * Product input request model class
 *
 * @author praveen kumar m
 * @version 1.0.0
 * @since 23-Jan-2022
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Product Details")
public class Product
{
	@ApiModelProperty(notes = "product to be created", name = "product", required = true)
	@NotNull
	private String productName;

	@ApiModelProperty(notes = "box tagged to product", name = "box", required = true)
	@NotNull
	private String boxName;

	private List<String> locationList;
}
