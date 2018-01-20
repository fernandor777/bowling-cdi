package org.fmino.bowlingscore.api;

import java.util.List;

import org.fmino.bowlingscore.model.PlayerCard;

/**
 * Card result printer interface
 * @author Fernando
 *
 */
public interface CardPrinter {
	
	/**
	 * Prints score card data
	 * @param card
	 */
	public void print(PlayerCard card);

	void print(List<PlayerCard> cards);
	
}
