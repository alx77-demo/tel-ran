package tel_ran.library.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;

import tel_ran.library.entities.Book;
import tel_ran.library.entities.BookRecord;
import tel_ran.library.entities.Reader;

public class LibraryMaps extends Library {
	public LibraryMaps(int pickPeriod) {
		super(pickPeriod);
	}

	HashMap<Long, Book> books = new HashMap<>();
	HashMap<Integer, Reader> readers = new HashMap<>();
	HashMap<IsbnReaderId, BookRecord> records = new HashMap<>();
	TreeMap<LocalDate, List<BookRecord>> nonreturnedRecords = new TreeMap<>();

	@Override
	public Iterator<Book> iterator() {
		return books.values().iterator();
	}

	@Override
	public boolean addBookItem(Book book) {
		return books.putIfAbsent(book.getIsbn(), book) == null;
	}

	@Override
	public boolean addBookExemplar(long isbn) {
		Book book = books.get(isbn);
		if (book == null) {
			return false;
		}
		book.setAmount(book.getAmount() + 1);
		return true;
	}

	@Override
	public boolean addReader(Reader reader) {
		return readers.putIfAbsent(reader.getReaderId(), reader) == null;
	}

	@Override
	public boolean removeBookExemplar(long isbn) {
		Book book = books.get(isbn);
		if (book == null)
			return false;
		if (book.getAmountInUse() == 0) {
			books.remove(book);
			return true;
		}

		records.values().removeIf(new Predicate<BookRecord>() {
			@Override
			public boolean test(BookRecord t) {
				return t.getIsbn() == book.getIsbn();
			}
		});

		return true;
	}

	@Override
	public boolean pickBook(long isbn, LocalDate pickDate, int readerId) {
		Book book = books.get(isbn);
		if (book == null || book.getAmount() == book.getAmountInUse() || !readers.containsKey(readerId)) {
			return false;
		}
		IsbnReaderId iri = new IsbnReaderId(isbn, readerId);
		BookRecord br = new BookRecord(isbn, pickDate, readerId);
		if (records.putIfAbsent(iri, br) != null) {
			return false;
		}
		addNonReturned(pickDate, br);
		updateAmounts(book);
		return true;
	}

	private void addNonReturned(LocalDate pickDate, BookRecord br) {
		List<BookRecord> lbr = nonreturnedRecords.putIfAbsent(pickDate, new LinkedList<>());
		if (lbr == null) {
			lbr = nonreturnedRecords.get(pickDate);
		}
		lbr.add(br);
	}

	// merge && compute???
	private void updateAmounts(Book book) {
		book.setAmountInUse(book.getAmountInUse() + 1);
		book.setPicksOverall(book.getPicksOverall() + 1);
	}

	@Override
	public boolean returnBook(long isbn, int readerId, LocalDate returnDate) {
		// 1.check book
		Book b = books.get(isbn);
		if (b == null)
			return false;

		// 2.check reader
		if (readers.get(readerId) == null)
			return false;
		// readers.values()
		// 3.check this book presence to reader
		BookRecord br = records.get(new IsbnReaderId(isbn, readerId));
		if (br == null)
			return false;
		// 4.setup corresponding return date to the record
		br.setReturnDate(returnDate);
		b.setAmountInUse(b.getAmountInUse()-1);
		// 5.remove this book from list of non-returned ones
		return nonreturnedRecords.get(br.getPickDate()).remove(br);
	}

	@Override
	public Iterable<BookRecord> getNonReturnedBookRecords() {
		List<BookRecord> nonReturnedBooks = new LinkedList<>();
		for (List<BookRecord> brl : nonreturnedRecords.values()) {
			nonReturnedBooks.addAll(brl);
		}
		return nonReturnedBooks;
	}

	@Override
	public Reader getReader(int readerId) {
		return readers.get(readerId);
	}

	@Override
	public Book getBookItem(long isbn) {
		return books.get(isbn);
	}

	@Override
	public Iterable<Reader> getAllReaders() {
		return readers.values();
	}

	@Override
	public Iterable<Reader> getReadersDelayedBooks(LocalDate currentDate, int byDays) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeBook(long isbn) {
		TreeSet<Long> nonReturnedBooksSet = new TreeSet<>();
		for (List<BookRecord> brl : nonreturnedRecords.values()) {
			for (BookRecord br : brl) {
				nonReturnedBooksSet.add(br.getIsbn());
			}
		}
		if(nonReturnedBooksSet.contains(isbn)) return false;
		return books.keySet().remove(isbn);
	}

	@Override
	public Iterable<Book> getNonReturnedBooks() {
		HashSet<Book> nonReturnedBooksSet = new HashSet<>();
		for (List<BookRecord> brl : nonreturnedRecords.values()) {
			for (BookRecord br : brl) {
				Book b = books.get(br.getIsbn());
				nonReturnedBooksSet.add(b);
			}
		}
		return nonReturnedBooksSet;
	}

	@Override
	public Iterable<BookRecord> getDelayedBookRecords(LocalDate currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Reader> getReaders(int isbn) {
		ArrayList<Reader> ar = new ArrayList<>();
		for (BookRecord br : records.values()) {
			if (br.getIsbn() == isbn) {
				ar.add(readers.get(br.getReaderId()));
			}
		}
		return ar;
	}

	@Override
	public Iterable<BookRecord> getAllRecords() {
		return records.values();
	}

	static long power(long base, long p) {
		return p == 1 ? base : (multiply(base, power(base, p - 1)));
	}

	static long multiply(long base, long m) {
		return m == 1 ? base : (base + multiply(base, m - 1));
	}

	public static void main(String[] args) {
		System.out.println(power(3, 5));
	}
}
