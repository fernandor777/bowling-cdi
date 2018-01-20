package org.fmino.bowlingscore.impl;

import java.util.List;
import java.util.function.Consumer;

import javax.enterprise.context.Dependent;

import org.fmino.bowlingscore.api.CardPrinter;
import org.fmino.bowlingscore.model.PlayerCard;
import org.fmino.bowlingscore.model.PlayerFrame;

/**
 * Console (system.out.println) card printer implementation
 * @author Fernando
 *
 */
@Dependent
public class ConsoleCardPrinter implements CardPrinter {

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
			score = score + f.getFrameScore();
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
		if( frame.getNumber().intValue()<10 && frame.getScores().get(0).equals(PlayerFrame.STRIKE)){
			pr("\t\t" + PlayerFrame.STRIKE);
		} else{
			frame.getScores().forEach((s) -> {
				pr("\t" + s);
			});
		}
	}
	
	private void printScore(){
		
	}
	
}
