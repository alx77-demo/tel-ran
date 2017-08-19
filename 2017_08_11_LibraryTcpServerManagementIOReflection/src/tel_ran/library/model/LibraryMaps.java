package tel_ran.library.model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tel_ran.library.entities.*;
import tel_ran.library.util.IsbnPredicate;
import tel_ran.library.util.IsbnReaderId;

@SuppressWarnings("serial")
public class LibraryMaps extends Library {
HashMap<Long,Book> books=new HashMap<>();
HashMap<IsbnReaderId,BookRecord> records=new HashMap<>();
TreeMap<LocalDate,List<BookRecord>> nonreturnedRecords=new TreeMap<>();
HashMap<Integer,Reader> readers=new HashMap<>();
	public LibraryMaps(int pickPeriod) {
		super(pickPeriod);
	}
	@Override
	public Iterator<Book> iterator() {
		return books.values().iterator();
	}
	@Override
	public boolean addBookItem(Book book) {
		return books.putIfAbsent(book.getIsbn(), book)==null;
	}
	@Override
	public boolean addBookExemplar(long isbn) {
		Book book=books.get(isbn);
		if(book==null)
			return false;
		book.setAmount(book.getAmount()+1);
		return true;
	}
	@Override
	public boolean removeBook(long isbn) {
		Book book=books.get(isbn);
		if(book==null || book.getAmountInUse()!=0)
			return false;
		books.remove(isbn);//removing from books
		records.values().removeIf(new IsbnPredicate(isbn));
		return true;
	}
	@Override
	public boolean pickBook(long isbn, LocalDate pickDate, int readerId) {
		Book book=books.get(isbn);
		if(book==null || !readers.containsKey(readerId) || book.getAmount()-book.getAmountInUse() == 0)
			return false;
		BookRecord record=new BookRecord(isbn, pickDate, readerId);
		if(records.putIfAbsent(new IsbnReaderId(isbn, readerId),
				record)!=null)
			return false;
		addNonReturned(pickDate,record);
		updatesAmounts(book);
		return true;
	}
	private void addNonReturned(LocalDate pickDate, BookRecord record) {
		List<BookRecord> newList=new LinkedList<BookRecord>();
		List<BookRecord> listRecords=nonreturnedRecords.putIfAbsent(pickDate,newList );
		if(listRecords==null)
			listRecords=newList;
		listRecords.add(record);
		
		
	}
	private void updatesAmounts(Book book) {
		book.setAmountInUse(book.getAmountInUse()+1);
		book.setPicksOverall(book.getPicksOverall()+1);
	}
	@Override
	public boolean returnBook(long isbn, int readerId, LocalDate returnDate) {
		BookRecord record=records.get(new IsbnReaderId(isbn, readerId));
		if (record==null)
			return false;
		Book book=books.get(isbn);
		if(book==null)
			throw new Error("record exists but book doesn't");
		book.setAmountInUse(book.getAmountInUse()-1);
		record.setReturnDate(returnDate);
		removeNonReturnedRecord(record);
		return true;

	}
	private void removeNonReturnedRecord(BookRecord record) {
		LocalDate pickDate=record.getPickDate();
		List<BookRecord> records=nonreturnedRecords.get(pickDate);
		if(records==null || !records.remove(record))
			throw new Error("discrepency in nonreturned records");
		if(records.isEmpty())
			nonreturnedRecords.remove(pickDate);
		
	}

	@Override
	public Iterable<Book> getNonReturnedBooks() {
		return getRecordsStream()
				.map(r->books.get(r.getIsbn())).distinct()
				.collect(Collectors.toList());
	}
	private Stream<BookRecord> getRecordsStream() {
		return nonreturnedRecords.values().stream().flatMap(List::stream);
	}
	@Override
	public Iterable<BookRecord> getNonReturnedBookRecords() {
		
		return getRecordsStream()
				.collect(Collectors.toList());

	}
	

	@Override
	public Iterable<BookRecord> getDelayedBookRecords(LocalDate currentDate) {
		LocalDate pickDate=currentDate.minusDays(pickPeriod);
		return getDelayedRecordsStream(pickDate)
				.collect(Collectors.toList());
	}
	@Override
	public Iterable<Reader> getReadersDelayedBooks(LocalDate currentDate, int byDays) {
		LocalDate pickDate=currentDate.minusDays(pickPeriod+byDays);
		return getDelayedRecordsStream(pickDate)
		.map(r->readers.get(r.getReaderId()))
		.collect(Collectors.toList());
	}
	private Stream<BookRecord> getDelayedRecordsStream(LocalDate pickDate) {
		return nonreturnedRecords.headMap(pickDate).values().stream()
		.flatMap(rl->rl.stream());
	}
	private Iterable<Reader> getReadersFromRecords(Stream<BookRecord> records) {
		
		return records
				.map(r->readers.get(r.getReaderId()))
				.collect(Collectors.toList());
	}
	@Override
	public boolean addReader(Reader reader) {
		return readers.putIfAbsent(reader.getReaderId(), reader)==null;
	}
	@Override
	public Iterable<Reader> getReaders(long isbn) {
		
		return getReadersFromRecords(records.values().stream()
				.filter(new IsbnPredicate(isbn)));
				
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
	public Iterable<BookRecord> getAllRecords() {
		return records.values();
	}
	@Override
	public Iterable<Reader> getAllReaders() {
		return readers.values();
	}

	
}
