package tel_ran.library.controller;

import tel_ran.library.entities.Book;

public class DisplayReadersBookItem extends LibraryItem {

	@Override
	public String displayedName() {
		return "Display the readers having been reading a book ";
	}

	@Override
	public void perform() {
		Book book=getExistingIsbn();
		if(book != null)
			library.getReaders(book.getIsbn()).forEach(System.out::println);

	}

}
