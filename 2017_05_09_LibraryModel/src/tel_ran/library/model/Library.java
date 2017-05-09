package tel_ran.library.model;

import java.time.LocalDate;

import tel_ran.library.entities.Book;
import tel_ran.library.entities.BookRecord;
import tel_ran.library.entities.Reader;

public abstract class Library {
	private LocalDate pickPeriod;

	public LocalDate getPickPeriod() {
		return pickPeriod;
	}

	public void setPickPeriod(LocalDate pickPeriod) {
		this.pickPeriod = pickPeriod;
	}

	abstract public boolean addBookItem(Book book);

	abstract public boolean addBook(int isbn);

	abstract public boolean removeBook(int isbn);

	abstract public boolean pickBook(long isbn, int readerId, LocalDate pickDate);

	abstract public boolean returnBook(long isbn, int readerId, LocalDate returnDate);

	abstract public Iterable<BookRecord> getNonReturnedBookRecords(Book book);

	abstract public Iterable<BookRecord> getDelayedBookRecords(Book book);

	abstract public Iterable<BookRecord> getAllBookRecords(Book book);

	abstract public Iterable<Reader> getReaders(long isbn);

	abstract public Reader getReader(int readerInt);

	abstract public Book getBookItem(long isdn);

	abstract public Iterable<Reader> getAllReaders(long isbn);
}
