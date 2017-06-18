package tel_ran.numbers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NumberGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGenerate() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrimesRule() {
		Rule rule = new PrimesNumberRule();
		int number = 16;
		try {
			rule.match(number);
			fail();
		} catch (RuleViolationException e) {
			assertEquals(number + 1, number + e.getDelta());
		}
		number = 20;
		try {
			rule.match(number);
			fail();
		} catch (RuleViolationException e) {
			assertEquals(number - 1, number + e.getDelta());
		}
	}
}
