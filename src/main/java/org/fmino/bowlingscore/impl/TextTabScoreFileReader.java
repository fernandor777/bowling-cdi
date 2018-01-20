package org.fmino.bowlingscore.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.fmino.bowlingscore.api.ScoreInputFormatException;
import org.fmino.bowlingscore.api.ScoreInputReader;
import org.fmino.bowlingscore.api.SingleInputValidator;
import org.fmino.bowlingscore.model.Pinfall;

/**
 * Score File Reader in Text format, tab separated
 * @author Fernando
 *
 */
@Dependent
public class TextTabScoreFileReader implements ScoreInputReader {
	
	private static Logger LOG = Logger.getLogger(TextTabScoreFileReader.class.getName());
	
	@Inject
	private SingleInputValidator inputValidator;
	
	public List<Pinfall> getPlayersUnitScoreList(String resource) {
		List<Pinfall> scores = null;
		try (Stream<String> fileStream = Files.lines(Paths.get(resource))){
			
			scores = fileStream.map(new Function<String, Pinfall>() {
                @Override
                public Pinfall apply(String line) {
                	inputValidator.validate(line);
                	return lineToScore(line);
                }}).collect(Collectors.toList());
			
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "IO error", e);
			throw new ScoreInputFormatException();
		}
		
		return scores;
	}
	
	private Pinfall lineToScore(String line){
		Pinfall score = new Pinfall();
		String[] splitted = line.split("\t");
		score.setName(splitted[0]);
		if(splitted[1].equals("F")){
			score.setScore(0);
			score.setFault(true);
		}
		else{
			score.setScore(Integer.valueOf(splitted[1]));
		}
		return score;
	}
	
}
