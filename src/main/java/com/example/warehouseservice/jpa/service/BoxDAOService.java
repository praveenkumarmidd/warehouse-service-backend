package com.example.warehouseservice.jpa.service;

import java.util.List;
import com.example.warehouseservice.model.request.Box;

public interface BoxDAOService
{
	void saveBoxInformation(Box box);
	List<String> fetchAvailableBoxes(int availableBoxCapacity);
}