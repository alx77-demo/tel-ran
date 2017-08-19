package tel_ran.library.controller;

public class ActiveReadersAgesItem extends ReadersAge {

	@Override
	public String displayedName() {
		return "Displaying most active readers by the specified ages";
	}
	@Override
	public void perform() {
		super.perform();
		LibraryItem.displayMostLeastActiveReaders
		(getRecordsReaderAge(yearFrom, yearTo), true);
	}

}
