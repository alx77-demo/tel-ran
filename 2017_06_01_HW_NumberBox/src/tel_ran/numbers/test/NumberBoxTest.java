package tel_ran.numbers.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tel_ran.numbers.NumberBoxArrayList;
import tel_ran.numbers.NumberBoxLinkedList;
import tel_ran.numbers.NumberBox;

public class NumberBoxTest {

	int[] etalon = new int[] { 2, 6, -10, 7, 2, -10 };
	NumberBox nba = new NumberBoxArrayList(etalon);
	NumberBox nbl = new NumberBoxLinkedList(etalon);
	int[] etalonWODuplicates = new int[] { 2, 6, -10, 7 };
	int[] etalonWOOdd = new int[] { 2, 6, -10, 2, -10 };
	int[] etalonDiv = new int[] { -10, -10 };
	int[] etalonRange = new int[] { 2, 6, 7, 2 };
	int[] etalonAdd = new int[] { 2, 6, -10, 7, 2, -10, 123 };
	int[] etalonRemove = new int[] { 2, 6, 7, 2, -10 };

	@Before
	public void setUp() throws Exception {
		// nba.addNumber(2);
		// nba.addNumber(6);
		// nba.addNumber(-10);
		// nba.addNumber(7);
		// nba.addNumber(2);
		// nba.addNumber(-10);
		//
		// nbl.addNumber(2);
		// nbl.addNumber(6);
		// nbl.addNumber(-10);
		// nbl.addNumber(7);
		// nbl.addNumber(2);
		// nbl.addNumber(-10);
	}

	@Test
	public void testAddNumber() {
		nba.addNumber(123);
		nbl.addNumber(123);
		assertArrayEquals(etalonAdd, nba.getArray());
		assertArrayEquals(etalonAdd, nbl.getArray());
	}

	@Test
	public void testRemoveNumber() {
		nba.removeNumber(-10);
		nbl.removeNumber(-10);
		assertArrayEquals(etalonRemove, nba.getArray());
		assertArrayEquals(etalonRemove, nbl.getArray());
	}

	@Test
	public void testGetEventNumbers() {
		assertArrayEquals(etalonWOOdd, nba.getEvenNumbers());
		assertArrayEquals(etalonWOOdd, nbl.getEvenNumbers());
	}

	@Test
	public void testGetDividerNumbers() {
		assertArrayEquals(etalonDiv, nba.getDividerNumbers(5));
		assertArrayEquals(etalonDiv, nbl.getDividerNumbers(5));
	}

	@Test
	public void testGetNumbersInRange() {
		assertArrayEquals(etalonRange, nba.getNumbersInRange(0, 100));
		assertArrayEquals(etalonRange, nbl.getNumbersInRange(0, 100));
	}

	@Test
	public void testRemoveOddNumbers() {
		nba.removeOddNumbers();
		nbl.removeOddNumbers();
		assertArrayEquals(etalonWOOdd, nba.getArray());
		assertArrayEquals(etalonWOOdd, nbl.getArray());
	}

	@Test
	public void testRemoveRepeated() {
		nba.removeRepeated();
		nbl.removeRepeated();
		assertArrayEquals(etalonWODuplicates, nba.getArray());
		assertArrayEquals(etalonWODuplicates, nbl.getArray());
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

}
