package tel_ran.library.controller;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import tel_ran.library.entities.BookRecord;

public abstract class ReadersAge extends LibraryItem {
int yearFrom;
int yearTo;
	public ReadersAge() {
		super();
	}

	@Override
	public void perform() {
		yearFrom=inputOutput.getInteger(String.format("Enter birth year from [%d-%d)", 1917,2005));
		yearTo=inputOutput.getInteger(String.format("Enter birth year to [%d-%d)",yearFrom+1,2006));
		
		
	
	}

	protected Stream<BookRecord> getRecordsReaderAge(int yearFrom, int yearTo) {
		return StreamSupport.stream(library.getAllRecords().spliterator(), false)
		.filter(r->birthYearInRange(r,yearFrom,yearTo));
	}

	private boolean birthYearInRange(BookRecord r, int yearFrom, int yearTo) {
		int readerId=r.getReaderId();
		int birthYear=library.getReader(readerId).getBirthDate().getYear();
		return birthYear>=yearFrom && birthYear<yearTo;
	}

}