package tel_ran.library.util;

import java.util.function.Predicate;

import tel_ran.library.entities.BookRecord;

public class PredicateRecordsReaderUse implements Predicate<BookRecord> {
long isbn;
int readerId;

	public PredicateRecordsReaderUse(long isbn, int readerId) {
	super();
	this.isbn = isbn;
	this.readerId = readerId;
}

	@Override
	public boolean test(BookRecord t) {
		return t.getIsbn()==isbn&&t.getReaderId()==readerId&&t.getReturnDate()==null;
	}

}
