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

	public Pinfall(String name, Integer score, Boolean fault) {
		super();
		this.name = name;
		this.fault = fault;
		if(fault){
			this.score = 0;
		}
		else{
			this.score = score;
		}
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
		if(score.intValue()>0) fault = false;
	}

	public Boolean getFault() {
		return fault;
	}

	public void setFault(Boolean fault) {
		this.fault = fault;
		if(fault) this.score = 0;
	}
	
	
	
}
