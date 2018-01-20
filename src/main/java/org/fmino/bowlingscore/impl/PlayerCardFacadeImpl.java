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

@ApplicationScoped
public class PlayerCardFacadeImpl implements PlayerCardFacade {
	
	@Inject
	private PinfallFacade pinfallFac;
	
	public List<PlayerCard> generateCards(List<Pinfall> scores){
		return pinfallFac.getPlayers(scores).stream().map(new Function<String, PlayerCard>() {
			@Override
			public PlayerCard apply(String name){
				PlayerCard card = new PlayerCard(name);
				fillCard(card);
				return card;
			}
		}).collect(Collectors.toList());
	}
	
	
	
	private void fillCard(PlayerCard card){
		
	}
	
}
