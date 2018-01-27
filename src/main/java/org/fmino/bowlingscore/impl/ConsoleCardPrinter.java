package org.fmino.bowlingscore.impl;

import java.util.List;
import java.util.function.Consumer;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.fmino.bowlingscore.api.CardPrinter;
import org.fmino.bowlingscore.api.FrameFacade;
import org.fmino.bowlingscore.model.PlayerCard;
import org.fmino.bowlingscore.model.PlayerFrame;

/**
 * Console (system.out.println) card printer implementation
 * @author Fernando
 *
 */
@Dependent
public class ConsoleCardPrinter implements CardPrinter {
	@Inject
	private FrameFacade frameFac;
	
	public static final String STRIKE = "X";
	public static final String SPARE = "/";
	public static final String FAULT = "F";
	
	@Override
	public void print(PlayerCard card) {
		pr(card.getName() + "\n");
		pr("Pinfalls");
		card.getFrames().forEach((f) -> {
			framePinfalls(f);
		});
		pr("\n");
		pr("Score");
		Integer score = 0;
		for(PlayerFrame f : card.getFrames()){
			score = score + frameFac.getScore(f);
			pr("\t\t" + score.toString());
		}
		pr("\n");
	}
	
	@Override
	public void print(List<PlayerCard> cards){
		printFramesRow();
		cards.stream().forEach((x) ->{
			print(x);
		});
	}
	
	protected void printFramesRow(){
		pr("Frame");
		for(int i=1; i<=10; i++){
			pr("\t\t" + i);
		}
		pr("\n");
	}
	
	private void pr(String str){
		System.out.print(str);
	}
	
	private void framePinfalls(PlayerFrame frame){
		if( frameFac.isStrike(frame.getPinfalls()) && frame.getNumber().intValue()<10){
			pr("\t\t" + ConsoleCardPrinter.STRIKE);
		} else{
			frame.getPinfalls().forEach((s) -> {
				if(s.getScore().intValue()==10){
					pr("\t" + ConsoleCardPrinter.STRIKE);
				}
				else{
					String cad = !s.getFault() ? s.getScore().toString() : ConsoleCardPrinter.FAULT;
					pr("\t" + cad);
				}
			});
		}
	}
	
	
	
	
}
