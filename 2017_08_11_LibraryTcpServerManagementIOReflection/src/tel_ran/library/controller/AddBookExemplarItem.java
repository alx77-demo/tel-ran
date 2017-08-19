package tel_ran.library.controller;

import tel_ran.library.entities.Book;

public class AddBookExemplarItem extends LibraryItem {

	@Override
	public String displayedName() {
		return "Add new exemplars of the existing book";
	}

	@Override
	public void perform() {
		Book book=getExistingIsbn();
		if(book == null){
			return;
		}
		int amount=inputOutput.getInteger("Enter exemplars amount");
		book.setAmount(book.getAmount()+amount);

	}

}
