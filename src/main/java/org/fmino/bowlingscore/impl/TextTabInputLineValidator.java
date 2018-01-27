package org.fmino.bowlingscore.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.fmino.bowlingscore.api.ScoreInputFormatException;
import org.fmino.bowlingscore.api.SingleInputValidator;
import org.fmino.bowlingscore.model.PlayerFrame;

/**
 * Data line validator for Tab separated text format
 * @author Fernando
 *
 */
@ApplicationScoped
public class TextTabInputLineValidator implements SingleInputValidator {
	
	private Pattern validPattern;
	
	public void validate(String inputLine) {
		Matcher m = validPattern.matcher(inputLine);
		if ( !m.matches() || !checkScoreNumber(inputLine)){
			throw new ScoreInputFormatException();
		}
	}
	
	@PostConstruct
	public void init(){
		validPattern = Pattern.compile("^[a-zA-Z]+\\s+(\\d{1,2}|F)$");
	}
	
	/**
	 * validate score values
	 * @param inputLine
	 * @return
	 */
	public boolean checkScoreNumber(String inputLine){
		String[] splitted = inputLine.split("\\s+");
		if(splitted[1].equals(PlayerFrame.FAULT)) return true;
		int value = Integer.valueOf(splitted[1]).intValue();
		if(value>=0 && value<=10) return true;
		return false;
	}
	
}
