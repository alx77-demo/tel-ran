package tel_ran.library.controller;

import tel_ran.library.entities.Book;

public class DisplayBookData extends LibraryItem {

	@Override
	public String displayedName() {
		return "Display a book data";
	}

	@Override
	public void perform() {
		Book book=getExistingIsbn();
		if(book==null)
			return;
		inputOutput.put(book);

	}

}
