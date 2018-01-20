package org.fmino.bowlingscore.test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.fmino.bowlingscore.api.PlayerCardFacade;
import org.fmino.bowlingscore.impl.PlayerCardFacadeImpl;
import org.fmino.bowlingscore.model.Pinfall;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class CardFacadeTest {
	@Inject
	private PlayerCardFacadeImpl cardFac;
	
	@Test
	public void striketest(){
		
		List<Pinfall> scores = new ArrayList<>();
		Pinfall pf = new Pinfall();
		pf.setScore(10);
		scores.add(pf);
		
		Assert.assertTrue(cardFac.isStrike(scores, 0));
		
		List<Pinfall> scores2 = new ArrayList<>();
		Pinfall pf1 = new Pinfall();
		pf1.setScore(3);
		scores2.add(pf1);
		
		Assert.assertFalse(cardFac.isStrike(scores2, 0));
		
	}
	
}
