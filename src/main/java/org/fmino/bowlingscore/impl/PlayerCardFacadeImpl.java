package org.fmino.bowlingscore.impl;

import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.fmino.bowlingscore.api.FrameFacade;
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
	@Inject
	private FrameFacade frameFac;
	
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
		PlayerFrame pf = null;
		int pinfallsAmount = 2;
		if(isStrike(card.getPinfalls(), pinfallIndex) && frame.intValue()<9){
			pinfallsAmount = 1;
		}
		else if(frame.intValue()==9){ 
			pinfallsAmount = isStrike(card.getPinfalls(), pinfallIndex) ? 3 : 2;
			if(card.getPinfalls().size()-1 > pinfallIndex + pinfallsAmount -1 ){
				throw new PinfallsAmountException("Bad pinfall amount for player", null);
			}
		}
		pf = frameFac.getFrame( card.getPinfalls().stream().skip(pinfallIndex).limit(pinfallsAmount).collect(Collectors.toList())
				, frame+1, card);
		card.getFrames().add(pf);
		
		if(frame.intValue()<9){
			pinfallIndex+=pinfallsAmount;
			processFrame(card, pinfallIndex, frame+1);
		}
	}
	
	public Boolean isStrike(List<Pinfall> scores, Integer pinfallIndex){
		if(scores.get(pinfallIndex).getScore().intValue()==10) return true;
		return false;
	}
	
	
	
}
