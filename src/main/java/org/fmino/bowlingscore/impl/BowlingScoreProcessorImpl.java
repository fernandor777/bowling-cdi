package org.fmino.bowlingscore.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.fmino.bowlingscore.api.BowlingScoreProcessor;
import org.fmino.bowlingscore.api.CardPrinter;
import org.fmino.bowlingscore.api.PinfallFacade;
import org.fmino.bowlingscore.api.PlayerCardFacade;
import org.fmino.bowlingscore.api.PlayersScoreFramesValidator;
import org.fmino.bowlingscore.api.ScoreInputReader;
import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;

/**
 * Data Processor Reference implementation 
 * @author Fernando
 *
 */
@ApplicationScoped
public class BowlingScoreProcessorImpl implements BowlingScoreProcessor {
	@Inject
	private ScoreInputReader reader;
	@Inject
	private PlayersScoreFramesValidator scoresValidator;
	@Inject
	private CardPrinter printer;
	@Inject
	private PinfallFacade pinfallFac;
	@Inject
	private PlayerCardFacade cardFac;
	
	@Override
	public void processScore(String resource) {
		List<Pinfall> scores = reader.getPlayersUnitScoreList(resource);
		
		List<PlayerCard> cards = cardFac.generateCards(scores);
		
		//scoresValidator.validate(scores);
		
	}
	
	
	
	
	
}
