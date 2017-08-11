package tel_ran.library.controller;

import java.time.LocalDate;

public class ReturnBookItem extends LibraryItem {

	@Override
	public String displayedName() {
		return "Return book";
	}

	@Override
	public void perform() {
		Long[]isbnReaderId=getIsbnReaderId();
		if(isbnReaderId==null)
			return;
		LocalDate returnDate=inputOutput.getDate("Enter return date in format "+format, format);
		long isbn=isbnReaderId[0];
		int readerId=isbnReaderId[1].intValue();
		if(library.returnBook(isbn, readerId, returnDate))
			inputOutput.put("The book hasn't be picked by the reader");

	}

}
