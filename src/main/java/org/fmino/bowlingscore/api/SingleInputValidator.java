package org.fmino.bowlingscore.api;

/**
 * Single score input data validator
 * @author Fernando
 *
 */
public interface SingleInputValidator {
	
	/**
	 * validator method for single score input, may throw a ScoreInputFormatException
	 * @param inputLine
	 */
	public void validate(String inputLine);
	
}
