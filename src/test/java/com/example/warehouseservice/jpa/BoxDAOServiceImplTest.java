package com.example.warehouseservice.jpa;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.warehouseservice.jpa.entity.BoxEntity;
import com.example.warehouseservice.jpa.repository.BoxRepository;
import com.example.warehouseservice.jpa.service.impl.BoxDAOServiceImpl;
import com.example.warehouseservice.model.request.Box;

@RunWith(SpringRunner.class)
public class BoxDAOServiceImplTest
{
	@InjectMocks
	BoxDAOServiceImpl _boxDAOService;

	@Mock
	BoxRepository _boxRepository;

	@Test
	public void shouldSaveBoxInformation()
	{
		_boxDAOService.saveBoxInformation(Box.builder().boxId("1").boxName("testBoxName").boxCapacity(3).build());

		verify(_boxRepository).save(any(BoxEntity.class));
	}

	@Test
	public void shouldReturnBoxesWhenAvailableCapacityOfBoxesIsNotZero()
	{
		when(_boxRepository.getAvailableCapacityBoxes(anyInt())).thenReturn(Arrays.asList("BoxName1,BoxName2"));
		List<String> availableBoxes = _boxDAOService.fetchAvailableBoxes(0);

		Assert.assertNotNull(availableBoxes);
		Assert.assertNotNull("BoxName1", availableBoxes.get(0));
	}

	@Test
	public void shouldNotReturnBoxesWhenAvailableCapacityOfBoxesIsZero()
	{
		when(_boxRepository.getAvailableCapacityBoxes(anyInt())).thenReturn(Arrays.asList());
		List<String> availableBoxes = _boxDAOService.fetchAvailableBoxes(0);

		Assert.assertEquals(0, availableBoxes.size());
	}
}
