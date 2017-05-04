package tel_ran.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class StackTest {
	private static final int N_LIMIT = 20;
	Stack<Integer> stackInt = new Stack<>(N_LIMIT);
	Stack<String> stackString = new Stack<>(N_LIMIT);

	@Before
	public void setUp() {
		fail("Not yet implemented");
	}
	@Test
	public void testStack() {
		fail("Not yet implemented");
	}

	@Test
	public void testPush() {
		assertTrue(stackInt.push(123));
		assertTrue(stackString.push("asd"));
	}

	@Test
	public void testPop() {
		assertEquals(123, (int)stackInt.pop());
		assertEquals(3, stackString.pop().length());
	}

	@Test
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

}
