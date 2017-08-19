package tel_ran.operations.items;

public class SubtractDays extends AddSubtractDates {
	public SubtractDays(){
		fl_add=false;
	}
	@Override
	public String displayedName() {
		return "Subtract a given number of days from a given date";
	}

}
