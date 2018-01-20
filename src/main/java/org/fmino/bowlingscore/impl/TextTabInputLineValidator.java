package org.fmino.bowlingscore.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

import org.fmino.bowlingscore.api.ScoreInputFormatException;
import org.fmino.bowlingscore.api.SingleInputValidator;

/**
 * Data line validator for Tab separated text format
 * @author Fernando
 *
 */
@Dependent
public class TextTabInputLineValidator implements SingleInputValidator {
	
	private Pattern validPattern;
	
	public void validate(String inputLine) {
		System.out.println("validating line: " + inputLine);
		Matcher m = validPattern.matcher(inputLine);
		if ( !m.matches() || !checkScoreNumber(inputLine)){
			throw new ScoreInputFormatException();
		}
	}
	
	@PostConstruct
	public void init(){
		validPattern = Pattern.compile("^[a-zA-Z]*\\t(\\d{1,2}|F)");
	}
	
	public boolean checkScoreNumber(String inputLine){
		String[] splitted = inputLine.split("\\t");
		if(splitted[1].equals("F")) return true;
		Integer value = Integer.valueOf(splitted[1]);
		if(value<=10) return true;
		return false;
	}
	
}
