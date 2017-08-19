package tel_ran.library.util;

import java.util.function.Predicate;

import tel_ran.library.entities.BookRecord;

public class PredicateRecordIsbnUse implements Predicate<BookRecord> {
long isbn;
	public PredicateRecordIsbnUse(long isbn) {
	super();
	this.isbn = isbn;
}
	@Override
	public boolean test(BookRecord t) {
		
		return t.getIsbn()==isbn && t.getReturnDate()==null;
	}

}
