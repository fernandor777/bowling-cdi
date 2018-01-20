package org.fmino.bowlingscore.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.fmino.bowlingscore.api.PinfallFacade;
import org.fmino.bowlingscore.model.Pinfall;

@ApplicationScoped
public class PinfallFacadeImpl implements PinfallFacade {
	
	@Override
	public List<String> getPlayers(List<Pinfall> scores){
		return scores.stream().map(p -> p.getName())
				.distinct().collect(Collectors.toList());
	}
	
	@Override
	public List<Pinfall> getByPlayerName(String name, List<Pinfall> scores){
		return scores.stream().filter(p -> p.getName().equals(name))
				.collect(Collectors.toList());
	}
	
}
