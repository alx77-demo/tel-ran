package tel_ran.library.controller;

import tel_ran.library.entities.Book;

public class AddBookItem extends LibraryItem {

	@Override
	public String displayedName() {
		return "Add book as an item";
	}

	@Override
	public void perform() {
		Long isbn=getNewIsbn();
		if(isbn==null){
			return;
		}
		String title=inputOutput.getString("Enter title");
		String author=inputOutput.getString("Enter author name");
		int amount=inputOutput.getInteger("Enter amount value");
		library.addBookItem(new Book(isbn, title, author, amount));

	}

}
