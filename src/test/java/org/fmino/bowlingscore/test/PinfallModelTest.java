package org.fmino.bowlingscore.test;

import org.fmino.bowlingscore.model.Pinfall;
import org.junit.Assert;
import org.junit.Test;

public class PinfallModelTest {
	
	@Test
	public void faultTest(){
		Pinfall pf =new Pinfall();
		pf.setScore(10);
		pf.setFault(true);
		Assert.assertTrue(pf.getScore().intValue()==0);
	}
	
	@Test
	public void scoreTest(){
		Pinfall pf =new Pinfall();
		pf.setFault(true);
		pf.setScore(10);
		Assert.assertTrue(pf.getFault().booleanValue()==false);
	}
	
}
