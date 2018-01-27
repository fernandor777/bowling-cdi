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
	void print(PlayerCard card);

	/**
	 * Prints score cards data from list
	 * @param cards
	 */
	void print(List<PlayerCard> cards);
	
}
