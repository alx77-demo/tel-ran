package tel_ran.library.util;

import java.util.function.Predicate;

import tel_ran.library.entities.BookRecord;

public class PredicateNonReturned implements Predicate<BookRecord> {

	@Override
	public boolean test(BookRecord t) {
		return t.getReturnDate()==null;
	}

}
