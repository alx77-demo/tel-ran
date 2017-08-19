package tel_ran.library.controller;

import tel_ran.library.entities.Book;

public class RemoveBookItem extends LibraryItem {

	@Override
	public String displayedName() {
		return "Remove a book";
	}

	@Override
	public void perform() {
		Book book=getExistingIsbn();
		if(book==null){
			return;
		}
		library.removeBook(book.getIsbn());
	}

}
