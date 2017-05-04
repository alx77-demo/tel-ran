package tel_ran.collections.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tel_ran.collections.RangePredicated;
import tel_ran.collections.predicates.PredicateEven;

public class RangeIteratorTest {
	private static final Integer MIN_NUMBER = 10;
	private static final Integer MAX_NUMBER = 20;
	RangePredicated RangePredicated;
	private int sum;

	@Before
	public void setUp() throws Exception {
		RangePredicated = new RangePredicated(MIN_NUMBER, MAX_NUMBER, new PredicateEven());
		sum = 0;
		for (int num = MIN_NUMBER; num <= MAX_NUMBER; num++) {
			sum += num;
		}
		sum/=2;
	}

	@Test
	public void testIterable() {
		int current = MIN_NUMBER;
		for (int number : RangePredicated) {
			System.out.println(number);
			//assertEquals(current++, number);
		}
		//assertEquals(current, MAX_NUMBER + 1);
	}

	@Test
	public void testForEach() {
		SumConsumer sumConsumer = new SumConsumer();
		RangePredicated.forEach(sumConsumer);
		assertEquals(sum, sumConsumer.getSum());
	}
}
