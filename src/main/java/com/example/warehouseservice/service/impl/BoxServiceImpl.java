package com.example.warehouseservice.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.warehouseservice.jpa.service.BoxDAOService;
import com.example.warehouseservice.model.request.Box;
import com.example.warehouseservice.service.BoxService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoxServiceImpl implements BoxService
{
	private final BoxDAOService _boxDAOService;

	@Override
	public void storeBoxInformation(Box box)
	{
		_boxDAOService.saveBoxInformation(box);
	}

	@Override
	public List<String> getAvailableBoxList()
	{
		return _boxDAOService.fetchAvailableBoxes(0);
	}

}
