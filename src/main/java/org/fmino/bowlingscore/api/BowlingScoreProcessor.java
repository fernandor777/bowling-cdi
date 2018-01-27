package org.fmino.bowlingscore.api;

import java.util.List;

import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;

/**
 * Main scores process application method (CDI context)
 * @author Fernando
 *
 */
public interface BowlingScoreProcessor {
	
	/**
	 * Load pinfalls list from resource, instance BowlingGame and print Score Cards
	 * @param resource
	 */
	void processScore(String resource);
	
	
}
