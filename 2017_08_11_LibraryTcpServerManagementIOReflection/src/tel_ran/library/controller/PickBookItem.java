package tel_ran.library.controller;

import java.time.LocalDate;


public class PickBookItem extends LibraryItem {

	@Override
	public String displayedName() {
		return "Pick book";
	}

	@Override
	public void perform() {
		Long[]isbnReaderId=getIsbnReaderId();
		if(isbnReaderId==null)
			return;
		LocalDate pickDate=inputOutput.getDate
				("Enter pick date in the format "+format, format);
		long isbn=isbnReaderId[0];
		int readerId=isbnReaderId[1].intValue();
		if(!library.pickBook(isbn, pickDate, readerId))
			inputOutput.put("Reader has already read this book");


	}
	
}
