package com.example.warehouseservice.model.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Box input request model class
 *
 * @author praveen kumar m
 * @version 1.0.0
 * @since 23-Jan-2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Box Details")
public class Box
{
	private String boxId;

	@ApiModelProperty(notes = "Name of the Box", name = "box", required = true)
	@NotNull
	private String boxName;

	@ApiModelProperty(notes = "Available Capacity of Box", name = "box", required = true)
	@NotNull
	private int boxCapacity;

	private List<String> availableBoxList;
}
