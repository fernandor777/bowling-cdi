package org.fmino.bowlingscore.test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.fmino.bowlingscore.api.ScoreInputReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class FileReaderTest {
	
	@Inject
	private ScoreInputReader reader;
	
	@Test
	public void testReader() throws URISyntaxException{
		URL url = this.getClass().getResource("/pinfalls/perfect.txt");
		Path path = Paths.get(url.toURI());
		reader.getPlayersUnitScoreList(path.toString()).forEach(p -> {
			Assert.assertTrue(p.getScore().intValue()==10 && p.getName().equals("Carl") 
					&& p.getFault().equals(Boolean.FALSE));
		});
	}
	
	@Test
	public void testReaderSpaces() throws URISyntaxException{
		URL url = this.getClass().getResource("/pinfalls/perfect-with-spaces.txt");
		Path path = Paths.get(url.toURI());
		reader.getPlayersUnitScoreList(path.toString()).forEach(p -> {
			Assert.assertTrue(p.getScore().intValue()==10 && p.getName().equals("Carl") 
					&& p.getFault().equals(Boolean.FALSE));
		});
	}
	
}
