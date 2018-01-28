package org.fmino.bowlingscore.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Frame score data model
 * @author Fernando
 *
 */
public class PlayerFrame implements Serializable {
	
	public static final String STRIKE = "X";
	public static final String SPARE = "/";
	public static final String FAULT = "F";
	
	private Integer number;
	private List<Pinfall> pinfalls = new ArrayList<>();
	private PlayerCard card;
	
	public PlayerFrame() {
	}
	
	public PlayerFrame(Integer number) {
		super();
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public List<Pinfall> getPinfalls() {
		return pinfalls;
	}

	public void setPinfalls(List<Pinfall> pinfalls) {
		this.pinfalls = pinfalls;
	}

	public PlayerCard getCard() {
		return card;
	}

	public void setCard(PlayerCard card) {
		this.card = card;
	}
	
	
	
	
}
