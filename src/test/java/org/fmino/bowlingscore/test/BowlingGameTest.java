package org.fmino.bowlingscore.test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.fmino.bowlingscore.api.BowlingGame;
import org.fmino.bowlingscore.api.ScoreInputReader;
import org.fmino.bowlingscore.impl.BowlingGameImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class BowlingGameTest {
	@Inject
	private BowlingGame game;
	@Inject
	private ScoreInputReader reader;
	
	@Test
	public void testRegularGame() throws URISyntaxException{
		URL url = this.getClass().getResource("/pinfalls/scores.txt");
		Path path = Paths.get(url.toURI());
		
		game.setPinfalls(reader.getPlayersUnitScoreList(path.toString()));
		// two players
		Assert.assertTrue(game.getCards().size()==2);
		
		int pointsJeff = game.getCards().stream().filter(c -> c.getName().equals("Jeff"))
				.map(c -> c.getFrames().stream()
						.map(f -> f.getFrameScore()).collect(Collectors.summingInt(Integer::intValue)) )
				.collect(Collectors.summingInt(Integer::intValue));
		Assert.assertEquals(pointsJeff, 167);
		
		int pointsJohn = game.getCards().stream().filter(c -> c.getName().equals("John"))
				.map(c -> c.getFrames().stream()
						.map(f -> f.getFrameScore()).collect(Collectors.summingInt(Integer::intValue)) )
				.collect(Collectors.summingInt(Integer::intValue));
		Assert.assertEquals(pointsJohn, 151);
		
		
	}
	
}
