package tel_ran.view.tests;

import java.util.Random;

import tel_ran.view.InputOutput;

public class TestInputOutput implements InputOutput {
String[]strings;
Random gen;
	public TestInputOutput(String[] strings) {
	super();
	this.strings = strings;
	gen=new Random();
}

	@Override
	public String getString(String prompt) {
		int ind=gen.nextInt(strings.length);
		return strings[ind];
	}

	@Override
	public void put(Object object) {
		// TODO Auto-generated method stub

	}

}
