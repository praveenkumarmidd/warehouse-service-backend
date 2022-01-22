package com.example.warehouseservice.jpa.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.sun.istack.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOX")
public class BoxEntity implements Serializable
{
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "BOX_ID", updatable = false, nullable = false)
	private String boxId;

	@Column(name = "BOX_NAME", unique = true)
	@NotNull
	private String boxName;

	@Column(name = "BOX_CAPACITY")
	@NotNull
	private int boxCapacity;

	@Column(name = "BOX_AVAILABLE_CAPACITY")
	@NotNull
	private int boxAvailableCapacity;

	@ManyToMany(mappedBy = "boxes", cascade = { CascadeType.ALL })
	private Set<ProductEntity> productEntities = new HashSet<>();
}
