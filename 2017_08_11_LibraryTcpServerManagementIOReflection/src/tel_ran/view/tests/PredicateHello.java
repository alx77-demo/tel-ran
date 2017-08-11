package tel_ran.view.tests;

import java.util.function.Predicate;

public class PredicateHello implements Predicate<String> {
String string;
	public PredicateHello(String string) {
	super();
	this.string = string;
}
	@Override
	public boolean test(String t) {
		// TODO Auto-generated method stub
		return string.equals(t);
	}

}
