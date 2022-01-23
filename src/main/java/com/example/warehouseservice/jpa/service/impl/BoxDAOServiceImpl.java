package com.example.warehouseservice.jpa.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.example.warehouseservice.jpa.entity.BoxEntity;
import com.example.warehouseservice.jpa.repository.BoxRepository;
import com.example.warehouseservice.jpa.service.BoxDAOService;
import com.example.warehouseservice.model.request.Box;

/**
 * BoxDAOServiceImpl class  for executing transaction
 *
 * @author praveen kumar m
 * @version 1.0.0
 * @since 23-Jan-2022
 */
@Repository
public class BoxDAOServiceImpl implements BoxDAOService
{
	@Resource
	BoxRepository _boxRepository;

	public void saveBoxInformation(Box box)
	{
		BoxEntity boxEntity = BoxEntity.builder()
			.boxName(box.getBoxName())
			.boxCapacity(box.getBoxCapacity())
			.boxAvailableCapacity(box.getBoxCapacity())
			.build();

		_boxRepository.save(boxEntity);
	}

	@Transactional
	public List<String> fetchAvailableBoxes(int availableBoxCapacity)
	{
		return _boxRepository.getAvailableCapacityBoxes(availableBoxCapacity);
	}
}
