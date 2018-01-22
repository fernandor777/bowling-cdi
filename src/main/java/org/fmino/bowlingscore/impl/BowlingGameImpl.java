package org.fmino.bowlingscore.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.fmino.bowlingscore.api.BowlingGame;
import org.fmino.bowlingscore.api.PlayerCardFacade;
import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;

/**
 * 10 Bowling game implementation
 * @author Fernando
 *
 */
@Dependent
public class BowlingGameImpl implements BowlingGame {
	@Inject
	private PlayerCardFacade cardFac;
	
	private List<Pinfall> pinfalls;
	private List<PlayerCard> cards;
	
	public BowlingGameImpl() {
	}

	@Override
	public List<Pinfall> getPinfalls() {
		return pinfalls;
	}

	@Override
	public void setPinfalls(List<Pinfall> pinfalls) {
		this.pinfalls = pinfalls;
		cards = cardFac.generateCards(pinfalls);
	}

	@Override
	public List<PlayerCard> getCards() {
		return cards;
	}

	@Override
	public void setCards(List<PlayerCard> cards) {
		this.cards = cards;
	}
	
	
	
}
