package tel_ran.random.tests;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import tel_ran.random.RandomGenerator;

public class RandomGeneratorTest {
	private static final Integer MIN_INT_VALUE = 10;
	private static final Integer MAX_INT_VALUE = 30;
	private static final Long MIN_LONG_VALUE = 10L;
	private static final Long MAX_LONG_VALUE = 30L;
	private static final Float MIN_FLOAT_VALUE = 10.0F;
	private static final Float MAX_FLOAT_VALUE = 30.0F;
	private static final Double MIN_DOUBLE_VALUE = 10.0;
	private static final Double MAX_DOUBLE_VALUE = 30.0;
	private static final LocalDate MIN_DATE_VALUE = LocalDate.of(2000, 1, 1);
	private static final LocalDate MAX_DATE_VALUE = LocalDate.of(2099, 12, 31);

	private RandomGenerator rgen;

	@Before
	public void setUp() throws Exception {
		rgen = new RandomGenerator();
	}

	@Test
	public void testGetRandomInteger() {
		Integer res = rgen.getRandomInteger(MIN_INT_VALUE, MAX_INT_VALUE);
		assertTrue(res >= MIN_INT_VALUE && res < MAX_INT_VALUE);
	}

	@Test
	public void testGetRandomLong() {
		Long res = rgen.getRandomLong(MIN_LONG_VALUE, MAX_LONG_VALUE);
		assertTrue(res >= MIN_LONG_VALUE && res < MAX_LONG_VALUE);
	}

	@Test
	public void testGetRandomFloat() {
		Float res = rgen.getRandomFloat(MIN_FLOAT_VALUE, MAX_FLOAT_VALUE);
		assertTrue(res >= MIN_FLOAT_VALUE && res < MAX_FLOAT_VALUE);
	}

	@Test
	public void testGetRandomDouble() {
		Double res = rgen.getRandomDouble(MIN_DOUBLE_VALUE, MAX_DOUBLE_VALUE);
		assertTrue(res >= MIN_DOUBLE_VALUE && res < MAX_DOUBLE_VALUE);
	}

	@Test
	public void testGenerateRandomString() {
		String res = rgen.generateRandomString(MIN_INT_VALUE, MAX_INT_VALUE);
		assertTrue(res.length() >= MIN_INT_VALUE && res.length() < MAX_INT_VALUE);
	}

	@Test
	public void testGetRandomDate() {
		LocalDate res = rgen.getRandomDate(MIN_DATE_VALUE, MAX_DATE_VALUE);
		assertTrue(res.isAfter(MIN_DATE_VALUE) && res.isBefore(MAX_DATE_VALUE));
	}
}
