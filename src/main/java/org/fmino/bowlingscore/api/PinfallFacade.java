package org.fmino.bowlingscore.api;

import java.util.List;
import java.util.stream.Collectors;

import org.fmino.bowlingscore.model.Pinfall;

/**
 * Pinfall domain logic layer
 * @author Fernando
 *
 */
public interface PinfallFacade {
	
	/**
	 * get pinfalls list for the player name
	 * @param name
	 * @param scores
	 * @return pinfalls list
	 */
	List<Pinfall> getByPlayerName(String name, List<Pinfall> scores);
	
	/**
	 * get the players list involved in the game
	 * @param scores
	 * @return names list
	 */
	List<String> getPlayers(List<Pinfall> scores);

}
