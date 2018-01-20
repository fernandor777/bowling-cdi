package org.fmino.bowlingscore.api;

import java.util.List;

import org.fmino.bowlingscore.model.Pinfall;

public interface ScoreInputReader {
	
	/**
	 * Generates a List of Player scores from the data resource
	 * @param resource data resource
	 * @return
	 */
	public List<Pinfall> getPlayersUnitScoreList(String resource);
	
	
	
}
