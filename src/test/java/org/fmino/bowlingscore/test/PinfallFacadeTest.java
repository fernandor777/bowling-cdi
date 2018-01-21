package org.fmino.bowlingscore.test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.fmino.bowlingscore.impl.PinfallFacadeImpl;
import org.fmino.bowlingscore.model.Pinfall;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class PinfallFacadeTest {
	@Inject
	private PinfallFacadeImpl pinFac;
	
	@Test
	public void playersTest(){
		List<String> names = pinFac.getPlayers(twoPlayersScore());
		Boolean b = (names.containsAll(twoPlayersNames()) && names.size()==twoPlayersNames().size());
		Assert.assertTrue(b);
	}
	
	@Test
	public void pinfallsPlayerTest(){
		List<Pinfall> pp = pinFac.getByPlayerName("fer", twoPlayersScore());
		Assert.assertTrue(pp.size()==3);
	}
	
	public List<String> twoPlayersNames(){
		List<String> names = new ArrayList<>();
		names.add("fer");
		names.add("cris");
		return names;
	}
	
	public List<Pinfall> twoPlayersScore(){
		List<Pinfall> scores = new ArrayList<>();
		scores.add(new Pinfall("fer", 10, false));
		scores.add(new Pinfall("cris", 0, true));
		scores.add(new Pinfall("cris", 4, true));
		scores.add(new Pinfall("fer", 3, false));
		scores.add(new Pinfall("fer", 7, false));
		scores.add(new Pinfall("cris", 4, true));
		return scores;
	}
	
	
}
