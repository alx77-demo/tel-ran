package tel_ran.memory.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tel_ran.memory.MemoryService;

public class MemoryServiceTest {

	byte ar[];

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMaxArrayMemory() {
		int size = MemoryService.getMaxArrayMemory();
		System.out.println(size);
		try {
			ar = new byte[size];
		} catch (Exception e) {
			fail(e.getMessage());
		}
		ar = null;
		try {
			ar = new byte[size + 1];
			fail("wrong size");
		} catch (Throwable e) {
			//e.printStackTrace();
		}
		assertTrue(true);
	}

}
