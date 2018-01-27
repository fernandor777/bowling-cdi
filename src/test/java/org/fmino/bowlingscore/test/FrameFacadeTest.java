package org.fmino.bowlingscore.test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.fmino.bowlingscore.api.FrameFacade;
import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;
import org.fmino.bowlingscore.model.PlayerFrame;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class FrameFacadeTest {
	
	@Inject
	private FrameFacade frameFac;
	
	@Test
	public void isStrikeTestOk(){
		List<Pinfall> pfs = new ArrayList<>();
		pfs.add(new Pinfall("fer", 10, false));
		Assert.assertTrue(frameFac.isStrike(pfs));
	}
	
	@Test
	public void isStrikeTestFail(){
		List<Pinfall> pfs = new ArrayList<>();
		pfs.add(new Pinfall("fer", 0, false));
		pfs.add(new Pinfall("fer", 10, false));
		Assert.assertFalse(frameFac.isStrike(pfs));
	}
	
	@Test
	public void isSpareTestOk(){
		List<Pinfall> pfs = new ArrayList<>();
		pfs.add(new Pinfall("fer", 0, false));
		pfs.add(new Pinfall("fer", 10, false));
		Assert.assertTrue(frameFac.isSpare(pfs));
	}
	
	@Test
	public void isSpareTestFail(){
		List<Pinfall> pfs = new ArrayList<>();
		pfs.add(new Pinfall("fer", 10, false));
		Assert.assertFalse(frameFac.isSpare(pfs));
	}
	
	@Test
	public void nextPinfallsTest1(){
		PlayerCard card = cardExample1();
		List<Pinfall> pfs = frameFac.nextPinfalls(card.getFrames().get(0), 2);
		Assert.assertTrue(pfs.get(0).getScore().intValue()==3);
		Assert.assertTrue(pfs.get(1).getScore().intValue()==0);
	}
	
	public PlayerCard cardExample1(){
		PlayerCard card = new PlayerCard();
		card.setName("Fer");
		
		PlayerFrame fr1 = new PlayerFrame(1);
		fr1.setCard(card);
		List<Pinfall> pfs = new ArrayList<>();
		pfs.add(new Pinfall("fer", 10, false));
		fr1.setPinfalls(pfs);
		card.getFrames().add(fr1);
		
		fr1 = new PlayerFrame(2);
		fr1.setCard(card);
		pfs = new ArrayList<>();
		pfs.add(new Pinfall("fer", 3, false));
		pfs.add(new Pinfall("fer", 0, true));
		fr1.setPinfalls(pfs);
		card.getFrames().add(fr1);
		
		fr1 = new PlayerFrame(3);
		fr1.setCard(card);
		pfs = new ArrayList<>();
		pfs.add(new Pinfall("fer", 2, false));
		pfs.add(new Pinfall("fer", 8, false));
		fr1.setPinfalls(pfs);
		card.getFrames().add(fr1);
		
		fr1 = new PlayerFrame(4);
		fr1.setCard(card);
		pfs = new ArrayList<>();
		pfs.add(new Pinfall("fer", 5, false));
		pfs.add(new Pinfall("fer", 2, false));
		fr1.setPinfalls(pfs);
		card.getFrames().add(fr1);
		
		return card;
	}
	
	@Test
	public void unitScoreTest(){
		PlayerCard card = cardExample1();
		Assert.assertTrue(frameFac.unitScore(card.getFrames().get(1)) == 3);
		Assert.assertTrue(frameFac.unitScore(card.getFrames().get(2)) == 10);
		Assert.assertTrue(frameFac.unitScore(card.getFrames().get(0)) == 10);
	}
	
	@Test
	public void getScoreTest(){
		PlayerCard card = cardExample1();
		Assert.assertTrue( frameFac.getScore(card.getFrames().get(0)) == 13 );
		Assert.assertTrue( frameFac.getScore(card.getFrames().get(2)) == 15 );
		Assert.assertTrue( frameFac.getScore(card.getFrames().get(3)) == 7 );
	}
	
	
	
}
