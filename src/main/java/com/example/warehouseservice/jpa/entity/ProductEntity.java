package com.example.warehouseservice.jpa.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Product Entity class for handling box transaction
 *
 * @author praveen kumar m
 * @version 1.0.0
 * @since 23-Jan-2022
 */
@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
public class ProductEntity implements Serializable
{
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "PRODUCT_ID", updatable = false, nullable = false)
	private String productId;

	@Column(name = "PRODUCT_NAME", unique = true)
	@NotNull
	private String productName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "PRODUCT_BOX",
		joinColumns = {
			@JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false)
		},
		inverseJoinColumns = {
			@JoinColumn(name = "BOX_ID", nullable = false, updatable = false)
		}
	)
	Set <BoxEntity> boxes = new HashSet<>();

	public ProductEntity()
	{

	}
}
