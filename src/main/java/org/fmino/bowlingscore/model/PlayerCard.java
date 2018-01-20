package org.fmino.bowlingscore.model;

import java.io.Serializable;
import java.util.List;

/**
 * Player Card Data model for result print
 * @author Fernando
 *
 */
public class PlayerCard implements Serializable {
	
	private String name;
	private List<Pinfall> pinfalls;
	private List<PlayerFrame> frames;
	
	public PlayerCard() {
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
