import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class ParallelSortUnitTest {
	private static final long ARRAY_SIZE = 5000;
	private static final int MIN_BOUND = 0;
	private static final int MAX_BOUND = 10000;

	Integer array[];
	
	@Before
	public void setUp() throws Exception {
		array = new Random().ints(ARRAY_SIZE, MIN_BOUND, MAX_BOUND).boxed().toArray(Integer[]::new);
	}

	@Test
	public void testSort() {
		array = Arrays.stream(array).sorted().toArray(Integer[]::new);
		for (int i = 0; i<array.length-1; i++) {
			assertTrue(array[i]<=array[i+1]);
		}
	}

}
