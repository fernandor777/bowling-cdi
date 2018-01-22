package org.fmino.bowlingscore.api;

import java.util.List;

import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;

/**
 * Bowling game instance
 * @author Fernando
 *
 */
public interface BowlingGame {

	void setCards(List<PlayerCard> cards);

	List<PlayerCard> getCards();
	
	/**
	 * Set pinfalls for all players in order, generates Score Cards
	 * @param pinfalls
	 */
	void setPinfalls(List<Pinfall> pinfalls);

	List<Pinfall> getPinfalls();

}
