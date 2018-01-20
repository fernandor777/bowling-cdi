package org.fmino.bowlingscore.api;

import java.util.List;

import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;

public interface BowlingScoreProcessor {
	
	public void processScore(String resource);
	
	
}
