package org.fmino.bowlingscore.model;

import java.io.Serializable;
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
	private List<String> scores;
	private Integer total;
	
	public PlayerFrame() {
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public List<String> getScores() {
		return scores;
	}

	public void setScores(List<String> scores) {
		this.scores = scores;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	
	
}
