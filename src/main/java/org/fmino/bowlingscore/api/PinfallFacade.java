package org.fmino.bowlingscore.api;

import java.util.List;
import java.util.stream.Collectors;

import org.fmino.bowlingscore.model.Pinfall;

public interface PinfallFacade {

	List<Pinfall> getByPlayerName(String name, List<Pinfall> scores);

	List<String> getPlayers(List<Pinfall> scores);

}
