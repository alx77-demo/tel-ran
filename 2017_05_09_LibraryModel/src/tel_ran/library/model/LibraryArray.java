package tel_ran.library.model;

import java.time.LocalDate;

import tel_ran.collections.Array;
import tel_ran.library.entities.Book;
import tel_ran.library.entities.BookRecord;
import tel_ran.library.entities.Reader;

public class LibraryArray extends Library {

	private Array<Book> books;
	private Array<Reader> readers;
	private Array<BookRecord> records;

	@Override
	public boolean addBookItem(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBook(int isbn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBook(int isbn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pickBook(long isbn, int readerId, LocalDate pickDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean returnBook(long isbn, int readerId, LocalDate returnDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<BookRecord> getNonReturnedBookRecords(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BookRecord> getDelayedBookRecords(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BookRecord> getAllBookRecords(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Reader> getReaders(long isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getReader(int readerInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookItem(long isdn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Reader> getAllReaders(long isbn) {
		// TODO Auto-generated method stub
		return null;
	}

}
