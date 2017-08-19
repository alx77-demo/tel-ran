package tel_ran.library.controller;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tel_ran.library.entities.*;
import tel_ran.library.model.*;
import tel_ran.view.*;

public abstract class LibraryItem implements Item {
	private static final String ISBN_PROMPT = "Enter isbn";
	private static final String READER_ID_PROMPT = "Enter reader id";
	protected static Library library;
	protected static InputOutput inputOutput;
	protected static String format="dd/MM/yyyy";
	
	public static String getFormat() {
		return format;
	}
	public static void setFormat(String format) {
		LibraryItem.format = format;
	}
	protected static Book getExistingIsbn(){
		long isbn=inputOutput.getNumber(ISBN_PROMPT).longValue();
		Book book= library.getBookItem(isbn);
		if(book==null)
			inputOutput.put("Book doesn't exist");
			
		return book;
	}
	protected static Long getNewIsbn() {
		long isbn=inputOutput.getNumber(ISBN_PROMPT).longValue();
		if(library.getBookItem(isbn)!=null){
			inputOutput.put("The book already exists");
			return null;
		}
		return isbn;
	}
	protected static Reader getExistingReaderId(){
		int readerId=inputOutput.getInteger(READER_ID_PROMPT);
		Reader reader=library.getReader(readerId);
		if(reader==null){
			inputOutput.put("Reader doesn't exist");
		}
		return reader;
	}
	protected static Integer getNewReaderId(){
		int readerId=inputOutput.getInteger(READER_ID_PROMPT);
		if(library.getReader(readerId)!=null){
			inputOutput.put("The reader already exists");
			return null;
		}
		return readerId;
	}
	public static void setLibrary(Library library) {
		LibraryItem.library = library;
	}

	public static void setInputOutput(InputOutput inputOutput) {
		LibraryItem.inputOutput = inputOutput;
	}

	@Override
	public boolean isExit() {
		return false;
	}

	public static void displayMostLeastPopularBooks(Stream<Book> books,boolean isMost){
		books.filter(b->b.getPicksOverall()!=0)
		.collect(Collectors.groupingBy(Book::getPicksOverall))
		.entrySet()
		.stream()
		.max((x,y)->isMost?
		Integer.compare(x.getKey(), y.getKey()):
		Integer.compare(y.getKey(),x.getKey()))
		.ifPresent(e->e.getValue().forEach(b->inputOutput.put(b)));
	}
	public static void displayMostLeastPopularBooksRecords(Stream<BookRecord> records, boolean isMost){
		Map<Long,Long> mapOccurrences=
			records.collect(Collectors.groupingBy(BookRecord::getIsbn,
				Collectors.counting()));
		
		long max=mapOccurrences.entrySet().stream()
		.max((x,y)->isMost? Long.compare(x.getValue(), y.getValue()):
			Long.compare(y.getValue(),x.getValue()))
		.get()
		.getValue();
		
		mapOccurrences.entrySet().stream()
		.filter(e->e.getValue()==max)
		.forEach(e->System.out.println(library.getBookItem(e.getKey())));
		
	}
	protected Long[] getIsbnReaderId(){
		Book book=getExistingIsbn();
		if(book==null){
			return null;
		}
		Reader reader=getExistingReaderId();
		if(reader==null){
			inputOutput.put("Readeer doesn't exist");
			return null;
		}
		return new Long[]{book.getIsbn(),new Long(reader.getReaderId())};
	}

	public static void displayMostLeastActiveReaders(Stream<BookRecord> records,boolean isMost){
		
		Map<Integer,Long> mapOccurrences=
				records.collect(Collectors.groupingBy(BookRecord::getReaderId,
					Collectors.counting()));
			
			long max=mapOccurrences.entrySet().stream()
			.max((x,y)->isMost? Long.compare(x.getValue(), y.getValue()):
				Long.compare(y.getValue(),x.getValue()))
			.get()
			.getValue();
			
			mapOccurrences.entrySet().stream()
			.filter(e->e.getValue()==max)
			.forEach(e->System.out.println(library.getReader(e.getKey())));
	}
}
