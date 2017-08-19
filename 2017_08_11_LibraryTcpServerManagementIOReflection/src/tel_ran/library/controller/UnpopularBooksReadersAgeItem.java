package tel_ran.library.controller;

public class UnpopularBooksReadersAgeItem extends ReadersAge {

	@Override
	public String displayedName() {
		return "Displaying least popular books among readers of the specified ages";
	}
	@Override
	public void perform() {
		super.perform();
		LibraryItem.displayMostLeastPopularBooksRecords
		(getRecordsReaderAge(yearFrom, yearTo), false);
	}

}
