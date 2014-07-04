package com.lightblue;

import org.junit.Assert;
import org.junit.Test;

public class PositionTest {
	
	Position pos1 = new Position(0, 0);
	Position pos2 = new Position(0, 1);
	Position pos3 = new Position(3, 4);
	Position pos4 = new Position(7, 5);
	
	@Test
	public void getColorTest(){
		Assert.assertTrue(pos1.getColor() == Position.color.WHITE);
		Assert.assertTrue(pos2.getColor() == Position.color.BLACK);
		Assert.assertTrue(pos3.getColor() == Position.color.BLACK);
		Assert.assertTrue(pos4.getColor() == Position.color.WHITE);		
	}
	
	@Test
	public void getStringTest(){
		Assert.assertTrue(pos1.getString().equals("a1"));
		Assert.assertTrue(pos2.getString().equals("a2"));
		Assert.assertTrue(pos3.getString().equals("d5"));
		Assert.assertTrue(pos4.getString().equals("h6"));
	}
	
	@Test
	public void equalsTest(){
		Assert.assertTrue(pos1.equals(pos1));
		Assert.assertFalse(pos1.equals(pos2));
		Assert.assertTrue(pos1.equals(new Position(pos1.getX(), pos1.getY())));
		Assert.assertFalse(pos1.equals(new Position(pos2.getX(), pos2.getY())));
	}

}
