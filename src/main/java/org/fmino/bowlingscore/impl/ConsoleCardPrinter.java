package org.fmino.bowlingscore.impl;

import javax.enterprise.context.Dependent;

import org.fmino.bowlingscore.api.CardPrinter;
import org.fmino.bowlingscore.model.PlayerCard;

/**
 * Console (system.out.println) card printer implementation
 * @author Fernando
 *
 */
@Dependent
public class ConsoleCardPrinter implements CardPrinter {

	@Override
	public void print(PlayerCard card) {
		
	}
	
	
	
}
