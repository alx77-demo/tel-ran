package tel_ran.library.controller;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import tel_ran.library.entities.*;
import tel_ran.library.model.*;

import tel_ran.view.*;

public class RandomLibraryAppl {

	private static final int PICK_PERIOD = 20;
	private static final int N_BOOKS = 10000;
	private static final Integer MAX_AMOUNT = 30;
	private static int globalIsbn=1;
	private static int globalReaderId=1;
	private static final Integer MIN_AMOUNT=15;
	private static final Integer N_AUTHORS = 100;
	private static final Integer MIN_PICKED_BOOKS = 50;
	private static final Integer MAX_PICKED_BOOKS = 80;
	private static final Integer N_READERS = 1000;
	private static final long N_YEARS = 10;
	private static final Integer MIN_RETURN_PERCENT = 10;
	private static final Integer MAX_RETURN_PERCENT = 15;

	public static void main(String[] args) {
		Library library=new LibraryMaps(PICK_PERIOD);
		InputOutput inputOutput=new RandomInputConsoleOutput();
		((RandomInputConsoleOutput)inputOutput).setFl_print(false);
		createLibrary(library,inputOutput);
		immitateLibraryWork(library,inputOutput);
		LibraryItem.setLibrary(library);
		LibraryItem.setInputOutput(inputOutput);
		((RandomInputConsoleOutput)inputOutput).setFl_print(true);
		displayMostPupularBooks(library,inputOutput);
		displayLeastPopularBooks(library,inputOutput);
		displayMostActiveReaders(library,inputOutput);
		displayLeastActiveReaders(library,inputOutput);
		inputOutput=new ConsoleInputOutput();
		LibraryItem.setInputOutput(inputOutput);
		List<Item> items=new ArrayList<>();
		items.add(new PopularBooksReaderAgesItem());
		items.add(new UnpopularBooksReadersAgeItem());
		items.add(new ActiveReadersAgesItem());
		items.add(new PassiveReadersAgesItem());
		items.add(new ExitSaveLibraryItem());
		Menu menu=new Menu(inputOutput, items);
		menu.runMenu();

	}
	
	private static void displayLeastActiveReaders(Library library, InputOutput output) {
		output.put("\nDisplaying least active readers");
		Stream<BookRecord> records=
				StreamSupport.stream(library.getAllRecords().spliterator(),false);
				LibraryItem.displayMostLeastActiveReaders(records, false);
		
	}

	private static void displayMostActiveReaders(Library library,InputOutput output) {
		output.put("\nDisplaying most active readers");
		Stream<BookRecord> records=
		StreamSupport.stream(library.getAllRecords().spliterator(),false);
		LibraryItem.displayMostLeastActiveReaders(records, true);
		
	}

	private static void displayLeastPopularBooks(Library library,InputOutput output) {
		output.put("\nDisplaying least popular books");
		Stream<Book> books=StreamSupport.stream(library.spliterator(), true);
		LibraryItem.displayMostLeastPopularBooks(books, false);
		
	}

	private static void displayMostPupularBooks(Library library,InputOutput output) {
		output.put("\nDisplaying most popular books");
		Stream<Book> books=StreamSupport.stream(library.spliterator(), true);
		LibraryItem.displayMostLeastPopularBooks(books, true);
		
	}

	private static void immitateLibraryWork(Library library, InputOutput inputOutput) {
		LocalDate current=LocalDate.now();
		LocalDate finishDate=current.plusYears(N_YEARS);
		while(current.isBefore(finishDate)){
			pickBooks(library,inputOutput,current);
			returnBooks(library,inputOutput,current);
			current=current.plusDays(1);
		}
		
	}

	private static void returnBooks(Library library, InputOutput inputOutput, LocalDate current) {
		int returnPercent=inputOutput.getInteger("return percent:", MIN_RETURN_PERCENT, MAX_RETURN_PERCENT);
		Iterable<BookRecord> records=library.getNonReturnedBookRecords();
		for(BookRecord record:records){
			int chance=inputOutput.getInteger("chance:", 1, 101);
			if(chance <= returnPercent)
				library.returnBook(record.getIsbn(), record.getReaderId(), current);
				
		}
		
	}

	private static void pickBooks(Library library, InputOutput inputOutput, LocalDate current) {
		int nPickedBooks=inputOutput.getInteger
				("enter number of picked books", MIN_PICKED_BOOKS, MAX_PICKED_BOOKS);
		for(int i=0;i<nPickedBooks;i++){
			long isbn=inputOutput.getNumber("enter isbn",1,N_BOOKS).longValue();
			int readerId=inputOutput.getInteger("enter reader id", 1, N_READERS);
			library.pickBook(isbn, current, readerId);
		}
		
	}

	private static void createLibrary(Library library, InputOutput inputOutput) {
		createBooks(library,inputOutput);
		createReaders(library,inputOutput);
		
	}

	private static void createReaders(Library library, InputOutput inputOutput) {
		for(int i=0; i<N_READERS; i++)
		{
			library.addReader(getRandomReader(inputOutput));
		}
		
	}

	private static Reader getRandomReader(InputOutput inputOutput) {
		
		
		int readerId=globalReaderId++;
		String name="reader"+readerId;
		String[] codes={"050-","052-","053-","054-","057-","058-"};
		String phone=
      inputOutput.getString("enter a phone code from: "+Arrays.toString(codes), codes)+
      inputOutput.getInteger("enter phone number with no code",1000000,9999999);
		LocalDate birthDate=inputOutput.getDate("enter birth date of the reader","dd/MM/yyyy", LocalDate.ofYearDay(1940, 1),
				LocalDate.ofYearDay(2000, 1));
		return new Reader(readerId, name, phone, birthDate);
	}

	private static void createBooks(Library library, InputOutput inputOutput) {
		for(int i=0; i<N_BOOKS;i++){
			Book book=getRandomBook(inputOutput);
			library.addBookItem(book);
		}
		
	}

	private static Book getRandomBook(InputOutput inputOutput) {
		long isbn=globalIsbn++;
		String title="title "+isbn;
		String author=getRandomAuthor(inputOutput);
		int amount=inputOutput.getInteger("enter amount", MIN_AMOUNT, MAX_AMOUNT);
		Book res=new Book(isbn, title, author, amount);
		
		return res;
	}

	private static String getRandomAuthor(InputOutput inputOutput) {
		
		return "author"+inputOutput.getInteger("enter author number",1,N_AUTHORS);
	}

}
