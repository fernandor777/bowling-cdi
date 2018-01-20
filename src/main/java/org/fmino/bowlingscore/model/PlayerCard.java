package org.fmino.bowlingscore.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Player Card Data model for result print
 * @author Fernando
 *
 */
public class PlayerCard implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<Pinfall> pinfalls = new ArrayList<>();
	private List<PlayerFrame> frames = new ArrayList<>();
	
	public PlayerCard() {
	}
	
	

	public PlayerCard(String name) {
		super();
		this.name = name;
	}

	public List<PlayerFrame> getFrames() {
		return frames;
	}

	public void setFrames(List<PlayerFrame> frames) {
		this.frames = frames;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pinfall> getPinfalls() {
		return pinfalls;
	}

	public void setPinfalls(List<Pinfall> pinfalls) {
		this.pinfalls = pinfalls;
	}
	
	
	
}
