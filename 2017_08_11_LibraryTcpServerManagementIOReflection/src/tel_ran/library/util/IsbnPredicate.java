package tel_ran.library.util;

import java.util.function.Predicate;

import tel_ran.library.entities.BookRecord;

public class IsbnPredicate implements Predicate<BookRecord> {
long isbn;

@Override
public boolean test(BookRecord t) {
	return t.getIsbn()==isbn;
}

public IsbnPredicate(long isbn) {
	super();
	this.isbn = isbn;
}

}
