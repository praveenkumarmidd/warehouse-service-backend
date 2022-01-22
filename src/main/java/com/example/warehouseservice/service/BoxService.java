package com.example.warehouseservice.service;

import java.util.List;
import com.example.warehouseservice.model.request.Box;

public interface BoxService
{
	void storeBoxInformation(Box box);
	List<String> getAvailableBoxList();
}
