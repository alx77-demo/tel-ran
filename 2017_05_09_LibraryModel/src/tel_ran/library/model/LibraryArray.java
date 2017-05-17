package tel_ran.library.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import tel_ran.collections.Array;
import tel_ran.collections.predicates.TruePredicate;
import tel_ran.library.entities.Book;
import tel_ran.library.entities.BookRecord;
import tel_ran.library.entities.Reader;

public class LibraryArray extends Library {

	private Array<Book> books;
	private Array<Reader> readers;
	private Array<BookRecord> records;

	public LibraryArray(int allowedDelayDays) {
		super(allowedDelayDays);
		books = new Array<Book>();
		readers = new Array<Reader>();
		records = new Array<BookRecord>();
	}

	@Override
	public boolean addBookItem(Book book) {
		books.add(book);
		return true;
	}

	@Override
	public boolean addBook(long isbn) {
		Array<Book> foundBooks = books.filter(new Predicate<Book>() {
			@Override
			public boolean test(Book t) {
				return t.getIsbn() == isbn;
			}
		});
		if (foundBooks.size() > 0) {
			foundBooks.get(0).setAmount(foundBooks.get(0).getAmount() + 1);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeBook(long isbn) {
		Array<Book> foundBooks = books.filter(new Predicate<Book>() {
			@Override
			public boolean test(Book t) {
				return t.getIsbn() == isbn;
			}
		});
		if (foundBooks.size() > 0 && foundBooks.get(0).getAmount() > 0) {
			foundBooks.get(0).setAmount(foundBooks.get(0).getAmount() - 1);
			return true;
		}
		return false;
	}

	@Override
	public boolean pickBook(long isbn, int readerId, LocalDate pickDate) {
		Array<Book> foundBooks = books.filter(new Predicate<Book>() {
			@Override
			public boolean test(Book t) {
				return t.getIsbn() == isbn;
			}
		});
		if (foundBooks.size() > 0 && foundBooks.get(0).getAmount() > 0) {
			foundBooks.get(0).setAmount(foundBooks.get(0).getAmount() - 1);
			BookRecord br = new BookRecord(isbn, pickDate, null, readerId);
			records.add(br);
			return true;
		}
		return false;
	}

	@Override
	public boolean returnBook(long isbn, int readerId, LocalDate returnDate) {
		Array<Book> foundBooks = books.filter(new Predicate<Book>() {
			@Override
			public boolean test(Book t) {
				return t.getIsbn() == isbn;
			}
		});
		if (foundBooks.size() > 0) {
			foundBooks.get(0).setAmount(foundBooks.get(0).getAmount() + 1);
			Array<BookRecord> foundRecords = records.filter(new Predicate<BookRecord>() {

				@Override
				public boolean test(BookRecord t) {
					return t.getIsbn() == isbn && t.getReaderId() == readerId;
				}
			});
			foundRecords.get(0).setReturnDate(LocalDate.now());
			return true;
		}
		return false;
	}

	@Override
	public Iterable<BookRecord> getNonReturnedBookRecords() {
		return records.filter(new Predicate<BookRecord>() {

			@Override
			public boolean test(BookRecord t) {
				return t.getReturnDate() == null;
			}
		});
	}

	@Override
	public Iterable<BookRecord> getDelayedBookRecords() {
		return records.filter(new Predicate<BookRecord>() {

			@Override
			public boolean test(BookRecord t) {
				return t.getPickDate().plusDays(getPickPeriod()).compareTo(LocalDate.now()) < 0
						&& t.getReturnDate() == null;
			}
		});
	}

	@Override
	public Iterable<BookRecord> getAllBookRecords() {
		return records.filter(new TruePredicate<BookRecord>());
	}

	@Override
	public Iterable<Reader> getReaders(long isbn) {
		Array<BookRecord> bra = records.filter(new Predicate<BookRecord>() {

			@Override
			public boolean test(BookRecord t) {
				return t.getIsbn() == isbn;
			}
		});
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (BookRecord bookRecord : bra) {
			al.add(bookRecord.getReaderId());
		}
		return readers.filter(new Predicate<Reader>() {

			@Override
			public boolean test(Reader t) {
				return al.contains(t.getReaderId());
			}

		});
	}

	@Override
	public Reader getReader(int readerId) {
		Array<Reader> foundReaders = readers.filter(new Predicate<Reader>() {
			@Override
			public boolean test(Reader t) {
				return t.getReaderId() == readerId;
			}
		});
		return foundReaders.get(0);
	}

	@Override
	public Book getBookItem(long isbn) {
		Book b = new Book(isbn, "noname", "noauthor", 0);
		Array<Book> foundBooks = books.filter(new Predicate<Book>() {
			@Override
			public boolean test(Book t) {
				return t.equals(b);
			}
		});
		return foundBooks.get(0);
	}

	@Override
	public Iterable<Reader> getAllReaders() {
		return readers.filter(new TruePredicate<Reader>());
	}

	@Override
	public Iterator<Book> iterator() {
		return books.iterator();
	}

	@Override
	public boolean addReader(Reader reader) {
		readers.add(reader);
		return true;
	}

	@Override
	public Iterable<Book> getAllBooks() {
		return books.filter(new TruePredicate<Book>());
	}

	@Override
	public String toString() {
		return "LibraryArray [books=" + books + ", readers=" + readers + ", records=" + records + "]";
	}
}
