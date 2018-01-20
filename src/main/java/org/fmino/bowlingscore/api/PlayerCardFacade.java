package org.fmino.bowlingscore.api;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;

/**
 * PlayerCard Domain Facade methods
 * @author Fernando
 *
 */
public interface PlayerCardFacade {

	List<PlayerCard> generateCards(List<Pinfall> scores);

	PlayerCard getCard(String name, List<Pinfall> scores);

}
