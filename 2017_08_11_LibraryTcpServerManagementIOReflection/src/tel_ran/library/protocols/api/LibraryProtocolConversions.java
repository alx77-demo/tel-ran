package tel_ran.library.protocols.api;

import java.time.LocalDate;

import tel_ran.library.entities.*;
import static tel_ran.library.protocols.api.LibraryProtocolConstants.*;
public class LibraryProtocolConversions {
	//Record conversion
	public static String recordToString(BookRecord record){
		long isbn=record.getIsbn();
		LocalDate pickDate=record.getPickDate();
		int readerId=record.getReaderId();
		LocalDate returnDate=record.getReturnDate();
		String result=isbn+DELIMETER
				+pickDate+DELIMETER
				+readerId+DELIMETER;
		if(returnDate != null)
			result+=returnDate;
		return result;
	}
	public static BookRecord stringToRecord(String[] tokens) {
		long isbn=Long.parseLong(tokens[0]);
		LocalDate pickDate=LocalDate.parse(tokens[1]);
		int readerId=Integer.parseInt(tokens[2]);
		BookRecord record=new BookRecord(isbn, pickDate, readerId);
		if(tokens.length==4){
			LocalDate returnDate=LocalDate.parse(tokens[3]);
			record.setReturnDate(returnDate);
		}
		return record;
	}
	/************************************************/
	//Reader conversion
	public static String readerToString(Reader reader){
		int readerId=reader.getReaderId();
		String name=reader.getName();
		String phone=reader.getPhone();
		LocalDate birthDate=reader.getBirthDate();
		
		return ""+readerId+DELIMETER+
				name+DELIMETER+
				phone+DELIMETER+
				birthDate;
		
	}
	public static Reader stringToReader(String[] tokens) {
		int readerId;
		String name;
		String phone;
		LocalDate birthDate;
		try {
			readerId = Integer.parseInt(tokens[0]);
			name = tokens[1];
			phone = tokens[2];
			birthDate = LocalDate.parse(tokens[3]);
		} catch (Throwable e) {
			return null;
		}
		return new Reader(readerId, name, phone, birthDate);
	}
	/*******************************************************/
	//Book conversion
	public static String bookToString(Book book){
		long isbn=book.getIsbn();
		String title=book.getTitle();
		String author=book.getAuthor();
		int amount=book.getAmount();
		int amountInUse=book.getAmountInUse();
		int  picksOverall=book.getPicksOverall();
		return isbn+DELIMETER+title+DELIMETER+
				author+DELIMETER+amount+DELIMETER+
				amountInUse+DELIMETER+
				picksOverall;
	}
	public static Book stringToBook(String[] tokens){
		Book book;
		try {
			long isbn=Long.parseLong(tokens[0]);
			String title=tokens[1];
			String author=tokens[2];
			int amount=Integer.parseInt(tokens[3]);
			book = new Book(isbn, title, author, amount);
			int amountInUse=Integer.parseInt(tokens[4]);
			book.setAmountInUse(amountInUse);
			int picksOverall=Integer.parseInt(tokens[5]);
			book.setPicksOverall(picksOverall);
		} catch (Throwable e) {
			return null;
		}
		return book;
	}
	
}
