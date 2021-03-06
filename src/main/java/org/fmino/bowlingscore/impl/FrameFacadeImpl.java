package org.fmino.bowlingscore.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;

import org.fmino.bowlingscore.api.FrameFacade;
import org.fmino.bowlingscore.api.ScoreInputFormatException;
import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;
import org.fmino.bowlingscore.model.PlayerFrame;

@Dependent
public class FrameFacadeImpl implements FrameFacade {
	
	@Override
	public PlayerFrame getFrame(List<Pinfall> pfs, int frameNumber, PlayerCard card){
		PlayerFrame frame = new PlayerFrame(frameNumber);
		frame.setPinfalls(pfs);
		frame.setCard(card);
		if(frameNumber<10 && pfs.stream().mapToInt(Pinfall::getScore).sum()>10){
			throw new ScoreInputFormatException("Bad frame pinfalls score sum", null);
		}
		return frame;
	}
	
	@Override
	public int getScore(PlayerFrame frame){
		int score = unitScore(frame);
		if(isStrike(frame.getPinfalls())){
			score += nextPinfalls(frame, 2).stream().mapToInt(Pinfall::getScore).sum();
		} else if (isSpare(frame.getPinfalls())){
			score += nextPinfalls(frame, 1).stream().mapToInt(Pinfall::getScore).sum();
		}
		return score;
	}
	
	@Override
	public int unitScore(PlayerFrame frame){
		return frame.getPinfalls().stream().mapToInt(Pinfall::getScore).sum();
	}
	
	@Override
	public List<Pinfall> nextPinfalls(PlayerFrame frame, int number){
		return frame.getCard().getFrames().stream().filter(f -> f.getNumber().intValue() > frame.getNumber())
			.flatMap(f -> f.getPinfalls().stream()).limit(number).collect(Collectors.toList());
	}
	
	@Override
	public Boolean isStrike(List<Pinfall> scores){
		if(scores.get(0).getScore().intValue()==10) return true;
		return false;
	}
	
	@Override
	public Boolean isSpare(List<Pinfall> scores){
		if(isStrike(scores)) return false;
		int sum = scores.get(0).getScore() + scores.get(1).getScore();
		if(sum==10) {
			return true;
		}
		return false;
	}
	
}
