package tel_ran.library.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import tel_ran.library.entities.Book;
import tel_ran.library.entities.BookRecord;
import tel_ran.library.entities.Reader;
import tel_ran.library.model.Library;
import tel_ran.library.model.LibraryArray;

public class LibraryArrayTest {

	private static final int PICK_PERIOD = 14;
	private static final int FIRST_READER_ID = 10;
	private static final LocalDate FIRST_READER_BIRTH_DATE = LocalDate.of(1989, 03, 15);
	private static final int FIRST_ISBN = 100;

	private Library library;
	private int nextIsbn = FIRST_ISBN;
	private int nextReaderId = FIRST_READER_ID;

	@Before
	public void setUp() throws Exception {
		library = new LibraryArray(PICK_PERIOD);
		library.addBookItem(new Book(nextIsbn++, "Skazki", "Pushkin", 10));
		library.addBookItem(new Book(nextIsbn++, "Vojna i mir", "Tolstoy", 12));
		library.addBookItem(new Book(nextIsbn++, "Kavkazskij plennik", "Lermontov", 14));
		library.addBookItem(new Book(nextIsbn++, "Malenkij prints", "Ekzuperi", 8));
		library.addBookItem(new Book(nextIsbn++, "Kobzar", "Shevchenko", 6));
		library.addBookItem(new Book(nextIsbn++, "Pasport", "Majakovskiy", 4));
		library.addBookItem(new Book(nextIsbn++, "The Bells", "Poe", 2));
		library.addBookItem(new Book(nextIsbn++, "The Lost World", "Doyle", 16));

		library.addReader(new Reader(nextReaderId++, "Ivan Reitman", "+123456789", FIRST_READER_BIRTH_DATE));
		library.addReader(
				new Reader(nextReaderId++, "Alexey Furmanov", "+123456789", FIRST_READER_BIRTH_DATE.plusDays(100)));
		library.addReader(
				new Reader(nextReaderId++, "Buzz Ligthing", "+123456789", FIRST_READER_BIRTH_DATE.plusDays(200)));
		library.addReader(
				new Reader(nextReaderId++, "Iosif Stalin", "+123456789", FIRST_READER_BIRTH_DATE.plusDays(300)));
		library.addReader(
				new Reader(nextReaderId++, "Vladimir Putin", "+123456789", FIRST_READER_BIRTH_DATE.plusDays(400)));
		library.addReader(
				new Reader(nextReaderId++, "Beniamin Netaniahu", "+123456789", FIRST_READER_BIRTH_DATE.plusDays(500)));
		System.out.println("Setup:" + library);
	}

	@Test
	public void testAddBookItem() {
		assertTrue(library.addBookItem(new Book(nextIsbn++, "Memoires", "Furmanov", 100)));
		Book myBook = null;
		Iterable<Book> books = library.getAllBooks();
		for (Book book : books) {
			myBook = book;
		}
		assertNotEquals(null, myBook);
		assertEquals(100, myBook.getAmount());
	}

	@Test
	public void testAddBook() {
		Book myBook = null;
		for (Book book : library.getAllBooks()) {
			myBook = book;
		}
		System.out.println("Before adding book:" + library);
		assertNotEquals(null, myBook);
		assertEquals(16, myBook.getAmount());
		assertTrue(library.addBook(myBook.getIsbn()));
		assertEquals(17, myBook.getAmount());
		System.out.println("After adding book:" + library);
	}

	@Test
	public void testRemoveBook() {
		int myIsbn = nextIsbn++;
		assertTrue(library.addBookItem(new Book(myIsbn, "TaNaH", "God", 1)));
		Book myBook = null;
		for (Book book : library.getAllBooks()) {
			myBook = book;
		}
		assertTrue(library.addBook(myIsbn));
		assertEquals(2, myBook.getAmount());
		assertTrue(library.removeBook(myIsbn));
		assertEquals(1, myBook.getAmount());
	}

	@Test
	public void testPickBook() {
		int myIsbn = nextIsbn++;
		assertTrue(library.addBookItem(new Book(myIsbn, "TaNaH", "God", 1)));
		Book myBook = null;
		for (Book book : library.getAllBooks()) {
			myBook = book;
		}
		assertTrue(library.pickBook(myIsbn, FIRST_READER_ID, LocalDate.now()));
		assertEquals(0, myBook.getAmount());
		assertFalse(library.pickBook(myIsbn, FIRST_READER_ID, LocalDate.now()));
	}

	@Test
	public void testReturnBook() {
		int myIsbn = nextIsbn++;
		assertTrue(library.addBookItem(new Book(myIsbn, "TaNaH", "God", 2)));
		Book myBook = null;
		for (Book book : library.getAllBooks()) {
			myBook = book;
		}
		assertTrue(library.pickBook(myIsbn, FIRST_READER_ID, LocalDate.now()));
		assertEquals(1, myBook.getAmount());
		assertTrue(library.returnBook(myIsbn, FIRST_READER_ID, LocalDate.now()));
		assertEquals(2, myBook.getAmount());
	}

	@Test
	public void testGetNonReturnedBookRecords() {
		Book myBook = library.getBookItem(FIRST_ISBN);
		assertTrue(library.pickBook(FIRST_ISBN, FIRST_READER_ID, LocalDate.now()));
		assertEquals(9, myBook.getAmount());

		Iterable<BookRecord> records = library.getNonReturnedBookRecords();
		BookRecord record = records.iterator().next();
		assertEquals(record.getReaderId(), FIRST_READER_ID);

		assertTrue(library.returnBook(FIRST_ISBN, FIRST_READER_ID, LocalDate.now()));
		assertEquals(10, myBook.getAmount());
	}

	@Test
	public void testGetDelayedBookRecords() {
		assertTrue(library.pickBook(FIRST_ISBN, FIRST_READER_ID + 1, LocalDate.now().minusDays(PICK_PERIOD + 1)));

		Iterable<BookRecord> records = library.getDelayedBookRecords();
		BookRecord record = records.iterator().next();
		assertEquals(record.getReaderId(), FIRST_READER_ID + 1);
	}

	@Test
	public void testGetAllBookRecords() {
		int counter = 0;
		assertTrue(library.pickBook(FIRST_ISBN + 1, FIRST_READER_ID + 1, LocalDate.now().minusDays(PICK_PERIOD + 1)));
		assertTrue(library.pickBook(FIRST_ISBN + 2, FIRST_READER_ID + 2, LocalDate.now().minusDays(PICK_PERIOD + 1)));
		for (BookRecord br : library.getAllBookRecords()) {
			counter++;
		}
		assertEquals(2, counter);
	}

	@Test
	public void testGetReaders() {

		Iterable<Reader> readers = library.getAllReaders();
		Reader r = null;

		for (Reader reader : readers) {
			r = reader;
		}

		assertEquals(nextReaderId - 1, r.getReaderId());
	}

	@Test
	public void testGetReader() {
		Reader r = library.getReader(nextReaderId - 1);
		assertNotNull(r);
		assertEquals(nextReaderId - 1, r.getReaderId());
	}

	@Test
	public void testGetBookItem() {
		Book b = library.getBookItem(nextIsbn - 1);
		assertNotNull(b);
		assertEquals(nextIsbn - 1, b.getIsbn());
	}

	@Test
	public void testGetAllReaders() {
		Iterable<Reader> readers = library.getAllReaders();
		int counter = 0;

		for (Reader reader : readers) {
			counter++;
		}

		assertEquals(6, counter);
	}

}