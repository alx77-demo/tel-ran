package tel_ran.library.util;

import java.util.function.Predicate;

import tel_ran.library.entities.BookRecord;

public class PredicateRecord implements Predicate<BookRecord> {
long isbn;
int readerId;
	public PredicateRecord(long isbn, int readerId) {
		this.isbn=isbn;
		this.readerId=readerId;
	}
	@Override
	public boolean test(BookRecord t) {
		return t.getReaderId()==readerId&&t.getIsbn()==isbn;
	}

}
