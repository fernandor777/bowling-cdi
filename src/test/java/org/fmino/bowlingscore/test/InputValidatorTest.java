package org.fmino.bowlingscore.test;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.fmino.bowlingscore.api.ScoreInputFormatException;
import org.fmino.bowlingscore.api.SingleInputValidator;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class InputValidatorTest {
	
	private static final Logger LOG = Logger.getLogger(InputValidatorTest.class.getName());
	
	@Inject
	private SingleInputValidator validator;
	
	@Test
	public void testValid(){
		String c1 = "Fer\t0";
		validator.validate(c1);
	}
	
	@Test
	public void testValidSpaces(){
		String c1 = "Fer   0";
		validator.validate(c1);
	}
	
	@Test(expected=ScoreInputFormatException.class)
	public void testOutofrange(){
		String c1 = "Fer\t11";
		validator.validate(c1);
	}
	
	@Test(expected=ScoreInputFormatException.class)
	public void testBadChar(){
		String c1 = "Fer\tC";
		validator.validate(c1);
	}
	
	@Test(expected=ScoreInputFormatException.class)
	public void testBadLength(){
		String c1 = "Fer\t11\t2";
		validator.validate(c1);
	}
	
}
