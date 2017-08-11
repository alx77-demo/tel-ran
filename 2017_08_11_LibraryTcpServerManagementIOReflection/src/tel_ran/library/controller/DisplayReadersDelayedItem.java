package tel_ran.library.controller;

import java.time.LocalDate;

public class DisplayReadersDelayedItem extends LibraryItem {

	@Override
	public String displayedName() {
		return "Display all readers having been delaying the books more than by the specified days";
	}

	@Override
	public void perform() {
		LocalDate currentDate=inputOutput.
	getDate("Enter current date in format "+format, format);
		int byDays=inputOutput.getInteger("Enter number of the delay days");
		library.getReadersDelayedBooks(currentDate, byDays)
		.forEach(System.out::println);

	}

}
