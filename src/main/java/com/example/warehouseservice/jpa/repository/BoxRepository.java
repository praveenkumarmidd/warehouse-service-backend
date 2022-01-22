package com.example.warehouseservice.jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.warehouseservice.jpa.entity.BoxEntity;

public interface BoxRepository extends JpaRepository<BoxEntity, String>
{
	BoxEntity findByBoxNameAndAndBoxAvailableCapacityNot(String boxName,int capacity);

	@Query("SELECT u.boxName FROM BoxEntity u WHERE u.boxAvailableCapacity not in :boxCapacity")
	List<String> getAvailableCapacityBoxes(@Param("boxCapacity")int boxMinimumAvailableCapacity);
}
