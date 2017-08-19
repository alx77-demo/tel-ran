package tel_ran.library.util;

import java.util.function.Predicate;

import tel_ran.library.entities.Book;

public class PredicateIsbn implements Predicate<Book> {
long isbn;

	public PredicateIsbn(long isbn) {
	super();
	this.isbn = isbn;
}

	@Override
	public boolean test(Book t) {
		return t.getIsbn()==isbn;
	}

}
