package org.fmino.bowlingscore.impl;

import java.util.List;

import javax.enterprise.context.Dependent;

import org.fmino.bowlingscore.api.FrameFacade;
import org.fmino.bowlingscore.model.Pinfall;
import org.fmino.bowlingscore.model.PlayerFrame;

@Dependent
public class FrameFacadeImpl implements FrameFacade {
	
	public PlayerFrame getFrame(List<Pinfall> pfs, int number){
		PlayerFrame frame = new PlayerFrame(number);
		frame.setPinfalls(pfs);
		
		return frame;
	}
	
}
