package tel_ran.operations.items;

public class AddDays extends AddSubtractDates {
public AddDays(){
	fl_add=true;
}
	@Override
	public String displayedName() {
		return "Add a given number of days to a given date";
	}

}
