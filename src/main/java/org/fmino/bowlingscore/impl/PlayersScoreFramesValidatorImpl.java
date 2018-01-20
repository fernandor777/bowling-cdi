package org.fmino.bowlingscore.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;

import org.fmino.bowlingscore.api.PlayerFramesNumberException;
import org.fmino.bowlingscore.api.PlayersScoreFramesValidator;
import org.fmino.bowlingscore.model.Pinfall;

/**
 * Check frames number / player, value = 10
 * @author Fernando
 *
 */
@Dependent
public class PlayersScoreFramesValidatorImpl implements PlayersScoreFramesValidator {
	
	private static Logger LOG = Logger.getLogger(PlayersScoreFramesValidatorImpl.class.getName());
	
	@Override
	public void validate(List<Pinfall> scores) {
		//Map<String, Integer> playerFrames = new HashMap<>();
		List<String> names = scores.stream().map(p -> p.getName())
				.distinct().collect(Collectors.toList());
		
		for(String n1 : names){
			List<Pinfall> npf = scores.stream().filter(p -> p.getName().equals(n1))
					.collect(Collectors.toList());
			Integer frameCount = 1;
			Integer points = 0;
			Integer launchs = 0;
			for(Pinfall p1 : npf){
				Boolean resetLaunch = false;
				launchs++;
				points+=p1.getScore();
				if(frameCount<10 && (points==10 || launchs==2)){
					frameCount++;
					points=0;
					resetLaunch=true;
				}
				if( frameCount>10 || (frameCount==10 && launchs>3) ) throw new PlayerFramesNumberException();
				if(resetLaunch) launchs=0;
			}
		}
	}
	
	
}
