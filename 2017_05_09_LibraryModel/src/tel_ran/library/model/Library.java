package tel_ran.library.model;

import java.time.LocalDate;

import tel_ran.library.entities.Book;
import tel_ran.library.entities.BookRecord;
import tel_ran.library.entities.Reader;

public abstract class Library implements Iterable<Book> {
	private int pickPeriod;

	public Library(int pickPeriod) {
		super();
		this.pickPeriod = pickPeriod;
	}

	public int getPickPeriod() {
		return pickPeriod;
	}

	public void setPickPeriod(int pickPeriod) {
		this.pickPeriod = pickPeriod;
	}

	abstract public boolean addBookItem(Book book);

	abstract public boolean addBook(long isbn);

	abstract public boolean addReader(Reader reader);

	abstract public boolean removeBook(long isbn);

	abstract public boolean pickBook(long isbn, int readerId, LocalDate pickDate);

	abstract public boolean returnBook(long isbn, int readerId, LocalDate returnDate);

	abstract public Iterable<BookRecord> getNonReturnedBookRecords();

	abstract public Iterable<BookRecord> getDelayedBookRecords();

	abstract public Iterable<BookRecord> getAllBookRecords();

	abstract public Iterable<Book> getAllBooks();

	abstract public Iterable<Reader> getReaders(long isbn);

	abstract public Reader getReader(int readerId);

	abstract public Book getBookItem(long isbn);

	abstract public Iterable<Reader> getAllReaders();
}
