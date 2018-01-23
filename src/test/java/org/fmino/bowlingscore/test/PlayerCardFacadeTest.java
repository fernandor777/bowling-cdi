package org.fmino.bowlingscore.test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.fmino.bowlingscore.api.PlayerCardFacade;
import org.fmino.bowlingscore.impl.TextTabScoreFileReader;
import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;
import org.fmino.bowlingscore.model.PlayerFrame;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class PlayerCardFacadeTest {
	@Inject
	private PlayerCardFacade cardFac;
	@Inject
	private TextTabScoreFileReader reader;
	
	class Sum {
		private int value = 0;
		
		public void add(int b){
			value += b;
		}
		
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
	
	@Test
	public void testPerfect() throws URISyntaxException{
		URL url = this.getClass().getResource("/pinfalls/perfect.txt");
		Path path = Paths.get(url.toURI());
		List<Pinfall> pfl = reader.getPlayersUnitScoreList(path.toString());
		List<PlayerCard> cards = cardFac.generateCards(pfl);
		Assert.assertTrue(cards.size()==1);
		
		Sum sum = new Sum();
		
		PlayerCard c1 = cards.get(0);
		c1.getFrames().forEach( c -> {
			c.getScores().forEach(s -> {
				Assert.assertTrue(s.equals(PlayerFrame.STRIKE));
			});
			sum.add(c.getFrameScore());
		});
		Assert.assertTrue(sum.getValue()==300);
	}
	
	@Test
	public void testZeroes() throws URISyntaxException{
		URL url = this.getClass().getResource("/pinfalls/zeroes.txt");
		Path path = Paths.get(url.toURI());
		List<Pinfall> pfl = reader.getPlayersUnitScoreList(path.toString());
		List<PlayerCard> cards = cardFac.generateCards(pfl);
		Assert.assertTrue(cards.size()==1);
		
		Sum sum = new Sum();
		
		PlayerCard c1 = cards.get(0);
		c1.getFrames().forEach( c -> {
			c.getScores().forEach(s -> {
				Assert.assertTrue(s.equals("0"));
			});
			sum.add(c.getFrameScore());
		});
		Assert.assertTrue(sum.getValue()==0);
	}
	
	@Test
	public void testRegular()throws URISyntaxException{
		URL url = this.getClass().getResource("/pinfalls/scores.txt");
		Path path = Paths.get(url.toURI());
		List<Pinfall> pfl = reader.getPlayersUnitScoreList(path.toString());
		List<PlayerCard> cards = cardFac.generateCards(pfl);
		Assert.assertTrue(cards.size()==2);
		
		int pointsJeff = cards.stream().filter(c -> c.getName().equals("Jeff"))
				.map(c -> c.getFrames().stream()
						.map(f -> f.getFrameScore()).collect(Collectors.summingInt(Integer::intValue)) )
				.collect(Collectors.summingInt(Integer::intValue));
		Assert.assertEquals(pointsJeff, 167);
		
		int pointsJohn = cards.stream().filter(c -> c.getName().equals("John"))
				.map(c -> c.getFrames().stream()
						.map(f -> f.getFrameScore()).collect(Collectors.summingInt(Integer::intValue)) )
				.collect(Collectors.summingInt(Integer::intValue));
		Assert.assertEquals(pointsJohn, 151);
	}
	
}
