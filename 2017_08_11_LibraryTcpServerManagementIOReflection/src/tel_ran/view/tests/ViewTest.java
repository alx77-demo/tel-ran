package tel_ran.view.tests;

import static org.junit.Assert.*;

import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import tel_ran.view.InputOutput;

public class ViewTest {
private static final String INTEGER = "123";
private static final String NUMBER = "500.5";
private static final String STRING_16 = "abcdefgrtyhjugit";
private static final String STRING_HELLO_WORLD = "hello world";
private static final String DATE = "14/05/17";
private static final String FORMAT="dd/MM/yy";
private static final String PROMPT = "prompt";
InputOutput inputOutput;
String strings[]={INTEGER,NUMBER,STRING_16,STRING_HELLO_WORLD,DATE};
	@Before
	public void setUp() throws Exception {
		inputOutput=new TestInputOutput(strings);
	}

	@Test
	public void testInteger() {
		assertEquals(INTEGER,inputOutput.getInteger(PROMPT).toString());
	}
	@Test
	public void testNumber() {
		
		
		assertEquals(NUMBER,Double.toString(inputOutput.getNumber(PROMPT, 200, 600).doubleValue()));
	}
	@Test
	public void testStringLength() {
		assertEquals(STRING_16,inputOutput.getString(PROMPT,15,17));
	}
	@Test
	public void testDate() {
		assertEquals(DATE,inputOutput.getDate(PROMPT,FORMAT).format(DateTimeFormatter.ofPattern(FORMAT)));
	}
	@Test
	public void testStringPredicate() {
		assertEquals(STRING_HELLO_WORLD,inputOutput.getString(PROMPT,new PredicateHello(STRING_HELLO_WORLD)));
	}
	@Test
	public void testStringsSelection() {
		assertEquals(STRING_HELLO_WORLD,inputOutput.getString(PROMPT,new String[]{STRING_HELLO_WORLD}));
	}

}
