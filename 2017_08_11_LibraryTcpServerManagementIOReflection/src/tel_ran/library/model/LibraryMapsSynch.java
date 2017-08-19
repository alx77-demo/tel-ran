package tel_ran.library.model;

import java.time.LocalDate;
import tel_ran.library.entities.*;
public class LibraryMapsSynch extends LibraryMaps{

	public LibraryMapsSynch(int pickPeriod) {
		super(pickPeriod);
	}
	/// Library methods
	
	@Override
	public boolean addBookItem(Book book) {
		synchronized (books) {
			return super.addBookItem(book);
		}
	}

	@Override
	public boolean addBookExemplar(long isbn) {
		synchronized (books) {
			return super.addBookExemplar(isbn);
		}
	}
	@Override
	public boolean removeBook(long isbn) {
		synchronized (books) {
			synchronized (records) {
				return super.removeBook(isbn);
			}
		}
	}
	
	@Override
	public boolean pickBook(long isbn, LocalDate pickDate, int readerId) {
		synchronized (books) {
			synchronized (records) {
				synchronized (nonreturnedRecords) {
					return super.pickBook(isbn, pickDate, readerId);
				}
			}
		}
	}
	
	@Override
	public boolean returnBook(long isbn, int readerId, LocalDate returnDate) {
		synchronized (books) {
			synchronized (records) {
				synchronized (nonreturnedRecords) {
					return returnBook(isbn, readerId, returnDate);
				}
			}
		}
	}
	
	@Override
	public Iterable<Book> getNonReturnedBooks() {
		
		synchronized (books) {
			synchronized (nonreturnedRecords) {
				return super.getNonReturnedBooks();
			}
		}
				
	}
	@Override
	public Iterable<BookRecord> getNonReturnedBookRecords() {
			synchronized (nonreturnedRecords) {
				return super.getNonReturnedBookRecords();
			}
	}
	
	@Override
	public Iterable<BookRecord> getDelayedBookRecords(LocalDate currentDate) {
			synchronized (nonreturnedRecords) {
				return super.getDelayedBookRecords(currentDate);
			}
	}
	
	@Override
	public Iterable<Reader> getReadersDelayedBooks(LocalDate currentDate, int byDays) {
		
		synchronized (readers) {
				synchronized (nonreturnedRecords) {
					return super.getReadersDelayedBooks(currentDate, byDays);
				}
			}
	}
	
	@Override
	public Iterable<Reader> getReaders(long isbn) {
		
		synchronized (readers) {
			return super.getReaders(isbn);
		}
	}
	@Override
	public boolean addReader(Reader reader) {
		synchronized (readers) {
			return super.addReader(reader);
		}
	}

	

	@Override
	public Reader getReader(int readerId) {
		synchronized (readers) {
			return readers.get(readerId);
		}
	}

	@Override
	public Book getBookItem(long isbn) {
		synchronized (books) {
			return books.get(isbn);
		}
	}

	@Override
	public Iterable<BookRecord> getAllRecords() {
		synchronized (records) {
			return records.values();
		}
	}
	@Override
	public Iterable<Reader> getAllReaders() {
		synchronized (readers) {
			return readers.values();
		}
	}
	

}
