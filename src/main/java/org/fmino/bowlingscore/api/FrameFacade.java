package org.fmino.bowlingscore.api;

import java.util.List;
import java.util.stream.Collectors;

import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerCard;
import org.fmino.bowlingscore.model.PlayerFrame;

public interface FrameFacade {

	Boolean isSpare(List<Pinfall> scores);

	Boolean isStrike(List<Pinfall> scores);

	List<Pinfall> nextPinfalls(PlayerFrame frame, int number);

	int unitScore(PlayerFrame frame);

	int getScore(PlayerFrame frame);

	PlayerFrame getFrame(List<Pinfall> pfs, int frameNumber, PlayerCard card);

}
