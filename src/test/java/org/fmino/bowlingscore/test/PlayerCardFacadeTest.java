package org.fmino.bowlingscore.test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.fmino.bowlingscore.api.FrameFacade;
import org.fmino.bowlingscore.api.PlayerCardFacade;
import org.fmino.bowlingscore.impl.TextTabScoreFileReader;
import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class PlayerCardFacadeTest {
	@Inject
	private PlayerCardFacade cardFac;
	@Inject
	private TextTabScoreFileReader reader;
	@Inject
	private FrameFacade frameFac;
	
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
			sum.add(frameFac.getScore(c));
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
			sum.add(frameFac.getScore(c));
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
		
		Sum sum = new Sum();
		
		cards.stream().filter(c -> c.getName().equals("Jeff"))
				.flatMap( c1 -> c1.getFrames().stream()).
				collect(Collectors.toList()).forEach( f -> {
					sum.add(frameFac.getScore(f));
				});
		Assert.assertEquals(sum.value, 167);
		
		Sum sum2 = new Sum();
		cards.stream().filter(c -> c.getName().equals("John"))
				.flatMap( c1 -> c1.getFrames().stream()).
				collect(Collectors.toList()).forEach( f -> {
					sum2.add(frameFac.getScore(f));
				});
		Assert.assertEquals(sum2.value, 151);
	}
	
}
