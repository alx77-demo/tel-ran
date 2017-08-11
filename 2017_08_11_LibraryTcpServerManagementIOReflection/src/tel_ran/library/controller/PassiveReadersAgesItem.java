package tel_ran.library.controller;

public class PassiveReadersAgesItem extends ReadersAge {

	@Override
	public String displayedName() {
		return "Displaying least active readers by the specified ages";
	}
	@Override
	public void perform() {
		super.perform();
		LibraryItem.displayMostLeastActiveReaders
		(getRecordsReaderAge(yearFrom, yearTo), false);
	}

}
