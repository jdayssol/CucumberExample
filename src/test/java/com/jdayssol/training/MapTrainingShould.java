package com.jdayssol.training;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class MapTrainingShould {

	MapTraining mt = new MapTraining();
	
	@Test
	public void substring_my_list()
	{
		List<String> subList = mt.substringClaimNum();
		List<String> expected = Arrays.asList("00009", "00008", "00007", "00006", "00005", "00004", "00003", "00002", "00010", "00001");
		Assert.assertArrayEquals(expected.toArray(), subList.toArray());
	}
	
	@Test
	public void transform_to_integer()
	{
		List<Integer> subList = mt.transformToIntegerClaimNum();
		List<Integer> expected = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 10, 1);
		Assert.assertArrayEquals(expected.toArray(), subList.toArray());
	}
	
	@Test
	public void sort_first_integer()
	{
		Assert.assertEquals(1,mt.sortFirstIntegerClaimNum().intValue());
	}
	
	@Test
	public void sort_last_integer()
	{
		
		Assert.assertEquals(10,mt.sortLastIntegerClaimNum().intValue());
	}
	
	@Test
	public void get_max_extclaimnum()
	{
		Assert.assertEquals("TL-199800010", mt.getMaxExtClaimNum());
	}
	
	@Test
	public void get_Next_extclaimnum()
	{
		Assert.assertEquals("TL-199800011", mt.getNextExtClaimNum());
	}
	
}
