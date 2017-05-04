package tel_ran.collections.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tel_ran.collections.Array;
import tel_ran.collections.SumConsumer;
import tel_ran.collections.X;
import tel_ran.collections.XComparator;
import tel_ran.collections.predicates.PredicateEven;

public class ArrayTest {

	private static final int N_ELEMENTS = 100;
	private Array array;
	private int sum;

	@Before
	public void setUp() throws Exception {
		array = new Array();
		sum = 0;
		for (int i = 0; i < N_ELEMENTS; i++) {
			array.add(i);
			sum += i;
		}
	}

	@Test
	public void testSize() {
		assertEquals(100, array.size());
	}

	@Test
	public void testAdd() {
		assertEquals(N_ELEMENTS, array.size());
		for (int i = 0; i < N_ELEMENTS; i++) {
			assertEquals(i, array.get(i));
		}
	}

	@Test
	public void testInsert() {
		assertFalse(array.insert(10, -1));
		assertFalse(array.insert(10, N_ELEMENTS + 1));
		assertTrue(array.insert(10, 0));
		assertFalse(array.insert(10, N_ELEMENTS + 1));
		assertTrue(array.insert(10, N_ELEMENTS / 2));
		assertEquals(10, array.get(0));
		assertEquals(99, array.get(array.size() - 1));
		assertEquals(10, array.get(N_ELEMENTS / 2));
	}

	@Test
	public void testRemoveLast() {
		assertEquals(N_ELEMENTS - 1, array.removeLast());
		assertEquals(N_ELEMENTS - 2, array.get(array.size() - 1));
		assertEquals(null, new Array().removeLast());
	}

	@Test
	public void testRemove() {
		int numbers[] = { N_ELEMENTS / 2, 0, N_ELEMENTS - 1 };
		assertEquals(null, array.remove(N_ELEMENTS));
		assertEquals(null, array.remove(-1));
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(numbers[i], array.remove(numbers[i]));
			assertEquals(-1, array.indexOf(numbers[i]));
			array.insert(numbers[i], numbers[i]);
		}
	}

	@Test
	public void testIndexOfObject() {
		array.add(2);
		assertEquals(2, array.indexOf(2));
		assertEquals(-1, array.lastIndexOf(N_ELEMENTS));
	}

	@Test
	public void testIndexOfPredicateOfObject() {
		assertEquals(2, array.indexOf(new PredicateEven()));
	}

	@Test
	public void testLastIndexOfObject() {
		array.add(2);
		assertEquals(N_ELEMENTS, array.lastIndexOf(2));
		assertEquals(-1, array.lastIndexOf(N_ELEMENTS));

	}

	@Test
	public void testLastIndexOfPredicateOfObject() {
		assertEquals(N_ELEMENTS - 2, array.lastIndexOf(new PredicateEven()));
	}

	@Test
	public void testSort() {
		X xs[] = { new X(10), new X(4), new X(8) };
		Array arrayX = new Array();
		for (int i = 0; i < xs.length; i++) {
			arrayX.add(xs[i]);
		}
		arrayX.sort();
		for (int i = 1; i < xs.length; i++) {
			assertTrue(((X) arrayX.get(i - 1)).compareTo((X) arrayX.get(i)) < 0);
		}
	}

	@Test
	public void testSortComparatorOfX() {
		X xs[] = { new X(10), new X(4), new X(8) };
		Array arrayX = new Array();
		for (int i = 0; i < xs.length; i++) {
			arrayX.add(xs[i]);
		}
		arrayX.sort(new XComparator());

		for (int i = 1; i < xs.length; i++) {
			assertTrue(((X) arrayX.get(i - 1)).compareTo((X) arrayX.get(i)) < 0);
		}

	}

	@Test
	public void testFilter() {
		Array arr = array.filter(new PredicateEven());
		assertEquals(49, arr.size());
	}

	@Test
	public void testRemoveIf() {
		array.removeIf(new PredicateEven());
		assertEquals(51, array.size());
	}

	@Test
	public void testToString() {
		String pattern = "Array [size=100, capacity=160, array=[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null]]";
		assertEquals(pattern, array.toString());
	}

	@Test
	public void testIterator() {
		int lsum = 0;
		for (Object number : array) {
			lsum += (int) number;
		}
		assertEquals(sum, lsum);
	}

	@Test
	public void testIteratorForEachConsumer() {
		SumConsumer sumConsumer = new SumConsumer();
		array.forEach(sumConsumer);
		assertEquals(sum, sumConsumer.getSum());
	}

	@Test
	public void testIterable() {
		int index = 0;
		for (Object number : array) {
			assertEquals(index++, (int) number);
		}
	}
}
