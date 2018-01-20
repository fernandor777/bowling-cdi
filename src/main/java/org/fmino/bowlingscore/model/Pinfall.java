package org.fmino.bowlingscore.model;

import java.io.Serializable;

/**
 * Data model for Player pinfall
 * @author Fernando
 *
 */
public class Pinfall implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer score;
	private Boolean fault = false;
	
	public Pinfall() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getFault() {
		return fault;
	}

	public void setFault(Boolean fault) {
		this.fault = fault;
		this.score = 0;
	}
	
	
	
}
