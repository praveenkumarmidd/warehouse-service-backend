package com.example.warehouseservice.model.request;

import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.*;

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
