package tel_ran.number.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import tel_ran.number.NaturalNumbers;

public class NaturalNumberTest {
	private static final int MAX_NUMBER = 10;
	int[] primesExpected = { 2, 3, 5, 7 };
	NaturalNumbers numbers;

	@Before
	public void setUp() throws Exception {
		numbers = new NaturalNumbers(MAX_NUMBER);
		System.out.println(numbers);
		// numbers.setForNumber(8);
	}

	@Test
	public void testIterator() {
		int numberExpected = 1;
		for (int actualNumber : numbers) {
			assertEquals(numberExpected++, actualNumber);
		}
		assertEquals(numberExpected - 1, MAX_NUMBER);
	}

	@Test
	public void testPrimes() {
		assertArrayEquals(primesExpected, numbers.getPrimes());
	}

	@Test
	// (expected = NoSuchElementException.class)
	public void testCommonDivider() {
		assertEquals(4, numbers.greatestCommonDivider(4, 8));
		assertEquals(1, numbers.greatestCommonDivider(1, 9));
		boolean fl_exception = false;
		try {
			int t = numbers.greatestCommonDivider(1, MAX_NUMBER + 1);
		} catch (NoSuchElementException e) {
			fl_exception = true;
		}
		assertTrue(fl_exception);
	}
}
