package org.fmino.bowlingscore.test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.fmino.bowlingscore.api.BowlingGame;
import org.fmino.bowlingscore.api.PinfallsAmountException;
import org.fmino.bowlingscore.api.ScoreInputReader;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class BowlingGameTest {
	@Inject
	private BowlingGame game;
	@Inject
	private ScoreInputReader reader;
	
	
	@Test(expected = PinfallsAmountException.class)
	public void testbadAmountPinfalls() throws URISyntaxException{
		URL url = this.getClass().getResource("/pinfalls/bad-scores-amount.txt");
		Path path = Paths.get(url.toURI());
		
		game.setPinfalls(reader.getPlayersUnitScoreList(path.toString()));
	}
	
	
	
}
