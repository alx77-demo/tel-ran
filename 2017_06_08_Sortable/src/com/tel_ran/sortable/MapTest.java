package com.tel_ran.sortable;

import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MapTest {
	MyMap m;
	String[] keys={"lmn","abc","abc","lmn","tt","tt","tt","aa","aa"};

	@Before
	public void setUp() throws Exception {
		m = new MyMap();
		for(String k:keys)
		{
			m.putString(k);
		}
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
