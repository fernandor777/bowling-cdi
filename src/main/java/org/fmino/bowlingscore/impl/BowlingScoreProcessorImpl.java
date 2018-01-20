package org.fmino.bowlingscore.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.fmino.bowlingscore.api.BowlingScoreProcessor;
import org.fmino.bowlingscore.api.PlayersScoreFramesValidator;
import org.fmino.bowlingscore.api.ScoreInputReader;
import org.fmino.bowlingscore.model.Pinfall;

@ApplicationScoped
public class BowlingScoreProcessorImpl implements BowlingScoreProcessor {
	@Inject
	private ScoreInputReader reader;
	@Inject
	private PlayersScoreFramesValidator scoresValidator;
	
	@Override
	public void processScore(String resource) {
		List<Pinfall> scores = reader.getPlayersUnitScoreList(resource);
		scoresValidator.validate(scores);
	}
	
	
	
}
