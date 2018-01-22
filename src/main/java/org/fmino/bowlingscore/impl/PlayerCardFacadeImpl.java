package org.fmino.bowlingscore.impl;

import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.fmino.bowlingscore.api.PinfallFacade;
import org.fmino.bowlingscore.api.PinfallsAmountException;
import org.fmino.bowlingscore.api.PlayerCardFacade;
import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;
import org.fmino.bowlingscore.model.PlayerFrame;

/**
 * Player Card domain logic implementation
 * @author Fernando
 *
 */
@ApplicationScoped
public class PlayerCardFacadeImpl implements PlayerCardFacade {
	
	private static final Logger LOG = Logger.getLogger(PlayerCardFacadeImpl.class.getName());
	
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
	
	/**
	 * Process every frame in the player game, recursive
	 * @param card
	 * @param pinfallIndex
	 * @param frame
	 */
	protected void processFrame(PlayerCard card, Integer pinfallIndex, Integer frame){
		Integer extras = 0;
		
		PlayerFrame pf = new PlayerFrame(frame+1);
				
		if(isStrike(card.getPinfalls(), pinfallIndex)){
			pf.getScores().add("X");
			pf.setFrameScore(10+StrikeBonus(card.getPinfalls(), pinfallIndex, frame));
			pinfallIndex++;
			extras = 2;
		}
		else if(isSpare(card.getPinfalls(), pinfallIndex)){
			pf.getScores().add(cardSignal(card.getPinfalls().get(pinfallIndex)));
			pf.getScores().add("/");
			pf.setFrameScore(10+SpareBonus(card.getPinfalls(), pinfallIndex, frame));
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
		
		if(frame.intValue()<9){
			processFrame(card, pinfallIndex, frame+1);
		}
		else {
			processExtra(card, pinfallIndex, pf, extras);
		}
	}
	
	/**
	 * process extra turns in frame 10, recursive
	 * @param card
	 * @param pinfallIndex
	 * @param pf
	 * @param extras
	 */
	protected void processExtra(PlayerCard card, Integer pinfallIndex, PlayerFrame pf, Integer extras){
		if(extras.intValue()<=0) {
			// if exists more pinfall rows for this player, exception:
			if(card.getPinfalls().size() >= pinfallIndex.intValue()+1){
				LOG.severe("Player Pinfalls amount error!");
				throw new PinfallsAmountException();
			}
			return;
		}
		if(isStrike(card.getPinfalls(), pinfallIndex)){
			pf.getScores().add("X");
		}
		else{
			pf.getScores().add(cardSignal(card.getPinfalls().get(pinfallIndex)));
		}
		pf.addFrameScore(card.getPinfalls().get(pinfallIndex).getScore());
		extras--;
		pinfallIndex++;
		processExtra(card, pinfallIndex++, pf, extras);
	}
	
	protected String cardSignal(Pinfall pinfall){
		return pinfall.getFault() ? "F" : pinfall.getScore().toString();
	}
	
	public Boolean isStrike(List<Pinfall> scores, Integer pinfallIndex){
		if(scores.get(pinfallIndex).getScore().intValue()==10) return true;
		return false;
	}
	
	private Boolean isSpare(List<Pinfall> scores, Integer pinfallIndex){
		Integer sum = scores.get(pinfallIndex).getScore() + scores.get(pinfallIndex+1).getScore();
		if(sum.intValue()==10) return true;
		return false;
	}
	
	private Integer StrikeBonus(List<Pinfall> scores, Integer pinfallIndex, Integer frame){
		if(frame.intValue()==9) return 0;
		return  ((pinfallIndex.intValue()+1 <= scores.size()-1) ? scores.get(pinfallIndex+1).getScore() : 0 )
				+ ((pinfallIndex.intValue()+2 <= scores.size()-1) ? scores.get(pinfallIndex+2).getScore() : 0);
	}
	
	private Integer SpareBonus(List<Pinfall> scores, Integer pinfallIndex, Integer frame){
		if(frame.intValue()==9) return 0;
		return ((pinfallIndex.intValue()+2 <= scores.size()-1) ? 
				scores.get(pinfallIndex+2).getScore() : 0 );
	}
	
}
