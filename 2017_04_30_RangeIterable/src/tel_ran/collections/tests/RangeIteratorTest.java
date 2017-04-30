package tel_ran.collections.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tel_ran.collections.Range;

public class RangeIteratorTest {
	private static final Integer MIN_NUMBER = 10;
	private static final Integer MAX_NUMBER = 20;
	Range range;
	private int sum;

	@Before
	public void setUp() throws Exception {
		range = new Range(MIN_NUMBER, MAX_NUMBER);
		sum = 0;
		for (int num = MIN_NUMBER; num <= MAX_NUMBER; num++) {
			sum += num;
		}
	}

	@Test
	public void testIterable() {
		int current = MIN_NUMBER;
		for (int number : range) {
			assertEquals(current++, number);
		}
		assertEquals(current, MAX_NUMBER + 1);
	}

	@Test
	public void testForEach() {
		SumConsumer sumConsumer = new SumConsumer();
		range.forEach(sumConsumer);
		assertEquals(sum, sumConsumer.getSum());
	}
}
