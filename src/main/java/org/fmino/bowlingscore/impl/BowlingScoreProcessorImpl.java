package org.fmino.bowlingscore.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.fmino.bowlingscore.api.BowlingGame;
import org.fmino.bowlingscore.api.BowlingScoreProcessor;
import org.fmino.bowlingscore.api.CardPrinter;
import org.fmino.bowlingscore.api.PinfallFacade;
import org.fmino.bowlingscore.api.PlayerCardFacade;
import org.fmino.bowlingscore.api.ScoreInputReader;
import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;

/**
 * Data Processor Reference implementation, using file system resource
 * @author Fernando
 *
 */
@Dependent
public class BowlingScoreProcessorImpl implements BowlingScoreProcessor {
	@Inject
	private ScoreInputReader reader;
	@Inject
	private CardPrinter printer;
	@Inject
	private BowlingGame game;
	
	@Override
	public void processScore(String resource) {
		game.setPinfalls(reader.getPlayersUnitScoreList(resource));
		printer.print(game.getCards());
	}
	
	
	
	
	
}
