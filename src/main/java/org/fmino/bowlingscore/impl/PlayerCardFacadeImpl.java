package org.fmino.bowlingscore.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.fmino.bowlingscore.api.PinfallFacade;
import org.fmino.bowlingscore.api.PlayerCardFacade;
import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;
import org.fmino.bowlingscore.model.PlayerFrame;

@ApplicationScoped
public class PlayerCardFacadeImpl implements PlayerCardFacade {
	
	@Inject
	private PinfallFacade pinfallFac;
	
	@Override
	public List<PlayerCard> generateCards(List<Pinfall> scores){
		return pinfallFac.getPlayers(scores).stream().map(new Function<String, PlayerCard>() {
			@Override
			public PlayerCard apply(String name){
				return getCard(name, scores);
			}
		}).collect(Collectors.toList());
	}
	
	@Override
	public PlayerCard getCard(String name, List<Pinfall> scores){
		PlayerCard card = new PlayerCard(name);
		card.setPinfalls(pinfallFac.getByPlayerName(card.getName(), scores));
		processFrame(card, 0, 0);
		return card;
	}
	
	protected void processFrame(PlayerCard card, Integer pinfallIndex, Integer frame){
		Integer extras = 0;
		if(frame==9){
			PlayerFrame pf10 = new PlayerFrame(frame+1);
			card.getFrames().add(pf10);
			processFrame10(card, pinfallIndex, pf10);
			return;
		}
		
		PlayerFrame pf = new PlayerFrame(frame+1);
		
		if(isStrike(card.getPinfalls(), pinfallIndex)){
			pf.getScores().add("X");
			pf.setFrameScore(10+StrikeBonus(card.getPinfalls(), pinfallIndex));
			pinfallIndex++;
			extras = 2;
		}
		else if(isSpare(card.getPinfalls(), pinfallIndex)){
			pf.getScores().add(cardSignal(card.getPinfalls().get(pinfallIndex)));
			pf.getScores().add("/");
			pf.setFrameScore(10+SpareBonus(card.getPinfalls(), pinfallIndex));
			pinfallIndex+=2;
			extras = 1;
		}
		else{ 
			pf.getScores().add(cardSignal(card.getPinfalls().get(pinfallIndex)));
			pf.getScores().add(cardSignal(card.getPinfalls().get(pinfallIndex+1)));
			pf.setFrameScore(card.getPinfalls().get(pinfallIndex).getScore()
					+card.getPinfalls().get(pinfallIndex+1).getScore());
			pinfallIndex+=2;
		}
		card.getFrames().add(pf);
		
		if(frame<9){
			processFrame(card, pinfallIndex, frame++);
		}
		else if (extras>0){
			processExtra(card, pinfallIndex, pf, extras);
		}
	}
	
	protected void processFrame10(PlayerCard card, Integer pinfallIndex, PlayerFrame pf ){
		Integer extras = 0;
		if(isStrike(card.getPinfalls(), pinfallIndex)){
			pf.getScores().add("X");
			pf.addFrameScore(10+StrikeBonus(card.getPinfalls(), pinfallIndex));
			pinfallIndex++;
			extras = 2;
			processExtra(card, pinfallIndex++, pf, extras);
		}
		else if(isSpare(card.getPinfalls(), pinfallIndex)){
			pf.getScores().add(cardSignal(card.getPinfalls().get(pinfallIndex)));
			pf.getScores().add("/");
			pf.addFrameScore(10+SpareBonus(card.getPinfalls(), pinfallIndex));
			pinfallIndex+=2;
			extras =1;
			processExtra(card, pinfallIndex++, pf, extras);
		} else{
			pf.getScores().add(cardSignal(card.getPinfalls().get(pinfallIndex)));
			pf.getScores().add(cardSignal(card.getPinfalls().get(pinfallIndex+1)));
		}
		
	}
	
	protected void processExtra(PlayerCard card, Integer pinfallIndex, PlayerFrame pf, Integer extras){
		if(isStrike(card.getPinfalls(), pinfallIndex)){
			pf.getScores().add("X");
			pf.addFrameScore(10+StrikeBonus(card.getPinfalls(), pinfallIndex));
		}
		else{
			pf.getScores().add(cardSignal(card.getPinfalls().get(pinfallIndex)));
			pf.addFrameScore(card.getPinfalls().get(pinfallIndex).getScore());
		}
		extras--;
		pinfallIndex++;
		if(extras>0) processExtra(card, pinfallIndex++, pf, extras);
	}
	
	protected String cardSignal(Pinfall pinfall){
		return pinfall.getFault() ? "F" : pinfall.getScore().toString();
	}
	
	protected Integer calculateScore(PlayerCard card, Integer pinfallIndex, Integer frame, Integer frameSub){
		Integer score = 0;
		//card.getPinfalls().get(pinfallIndex).get
		return score;
	}
	
	private Boolean isStrike(List<Pinfall> scores, Integer pinfallIndex){
		if(scores.get(pinfallIndex).equals(10)) return true;
		return false;
	}
	
	private Boolean isSpare(List<Pinfall> scores, Integer pinfallIndex){
		Integer sum = scores.get(pinfallIndex).getScore() + scores.get(pinfallIndex+1).getScore();
		if(sum.equals(10)) return true;
		return false;
	}
	
	private Integer StrikeBonus(List<Pinfall> scores, Integer pinfallIndex){
		return  ((pinfallIndex+1 <= scores.size()-1) ? scores.get(pinfallIndex+1).getScore() : 0 )
				+ ((pinfallIndex+2 <= scores.size()-1) ? scores.get(pinfallIndex+2).getScore() : 0);
	}
	
	private Integer SpareBonus(List<Pinfall> scores, Integer pinfallIndex){
		return ((pinfallIndex+1 <= scores.size()-1) ? scores.get(pinfallIndex+1).getScore() : 0 );
	}
	
}