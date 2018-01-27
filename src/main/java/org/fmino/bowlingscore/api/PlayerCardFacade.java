package org.fmino.bowlingscore.api;

import java.util.List;

import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;

/**
 * PlayerCard Domain Facade methods
 * @author Fernando
 *
 */
public interface PlayerCardFacade {
	
	/**
	 * generate player cards for each player in game
	 * @param scores global, all players pinfalls list
	 * @return player cards list
	 */
	List<PlayerCard> generateCards(List<Pinfall> scores);
	
	/**
	 * Generate card from an unique player pinfalls list
	 * @param name player name
	 * @param scores player pinfalls list
	 * @return player card
	 */
	PlayerCard getCard(String name, List<Pinfall> scores);

}
