package org.fmino.bowlingscore.api;

import java.util.List;

import org.fmino.bowlingscore.model.Pinfall;

/**
 * Validator method: frames number / player
 * @author Fernando
 *
 */
public interface PlayersScoreFramesValidator {
	
	public void validate(List<Pinfall> scores);
	
}
