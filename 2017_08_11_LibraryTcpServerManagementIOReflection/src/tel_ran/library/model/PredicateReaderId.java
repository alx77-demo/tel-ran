package tel_ran.library.model;

import java.util.function.Predicate;

import tel_ran.library.entities.Reader;

public class PredicateReaderId implements Predicate<Reader> {
int readerId;
	public PredicateReaderId(int readerId) {
	super();
	this.readerId = readerId;
}
	@Override
	public boolean test(Reader t) {
		return t.getReaderId()==readerId;
	}

}
