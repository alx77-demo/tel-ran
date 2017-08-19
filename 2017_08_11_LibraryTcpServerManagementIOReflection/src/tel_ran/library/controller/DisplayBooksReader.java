package tel_ran.library.controller;

import java.util.stream.StreamSupport;

import tel_ran.library.entities.BookRecord;
import tel_ran.library.entities.Reader;

public class DisplayBooksReader extends LibraryItem {

	@Override
	public String displayedName() {
		return "display all books that have been read by a reader ";
	}

	@Override
	public void perform() {
		Reader reader=getExistingReaderId();
		if(reader!=null){
			StreamSupport.stream(library.getAllRecords().spliterator(),
			false)
			.filter(r->r.getReaderId()==reader.getReaderId())
			.map(r->library.getBookItem(r.getIsbn()))
			.forEach(System.out::println);
		}

	}

}
