package tel_ran.view.tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import tel_ran.view.*;

public class RandomInputConsoleOutputTest {
private static final int N_STARS = 40;
InputOutput inputOutput;
	@Before
	public void setUp() throws Exception {
		inputOutput=new RandomInputConsoleOutput();
	}

	@Test
	public void testGetInteger() {
		printStars(N_STARS);
		int minValue=10;
		int maxValue=20;
		String prompt=String.format("Enter integer number from %d inclusive to %d exclusive",
				minValue,maxValue);
		inputOutput.getInteger(prompt, minValue, maxValue);
		
	}
	private void printStars(int nStars) {
		for (int i=0;i<nStars; i++)
			System.out.print('*');
		System.out.println();
		
	}

	@Test
	public void testGetNumber() {
		printStars(N_STARS);
		float minValue=10.5f;
		float maxValue=20.2f;
		String prompt=String.format("Enter any number from %g inclusive to %g exclusive",
				minValue,maxValue);
		inputOutput.getNumber(prompt, minValue, maxValue);
	}
	@Test
	public void testGetEvenNumber() {
		printStars(N_STARS);
		inputOutput.getInteger("Enter even integer number",new EvenPredicate());
	}
	@Test
	public void testGetString() {
		printStars(N_STARS);
		int minLength=4;
		int maxLength=8;
		String prompt=String.format("Enter string with length in rande [%d-%d)", minLength, maxLength);
		inputOutput.getString(prompt,minLength,maxLength);
	}
	@Test
	public void testGetDate() {
		printStars(N_STARS);
		inputOutput.getDate("Enter any date", "yyyy-MM-dd");
		printStars(N_STARS);
		inputOutput.getDate("Enter any date of the second millenium ", "yyyy-MM-dd",LocalDate.ofYearDay(2000, 1),LocalDate.now());
	}
	

}
