package org.fmino.bowlingscore.api;


/**
 * Main scores process application method (CDI context)
 * @author Fernando
 *
 */
public interface BowlingScoreProcessor {
	
	/**
	 * Load pinfalls list from resource, instance BowlingGame and print Score Cards
	 * @param resource is an resource string identifier, can represent file system, http resources, JDNI resources
	 */
	void processScore(String resource);
	
	
}
