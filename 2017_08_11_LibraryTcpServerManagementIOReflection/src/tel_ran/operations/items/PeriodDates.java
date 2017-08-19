package tel_ran.operations.items;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PeriodDates extends OperationItem {
String format="dd/MM/yy";
	@Override
	public String displayedName() {
		
		return "Define number of days between two dates";
	}

	@Override
	public void perform() {
		LocalDate date1=inputOutput.getDate("Enter first date in format "+format, format);
		LocalDate date2=inputOutput.getDate("Enter second date in format "+format, format);
		inputOutput.put("beteewn the specific dates there are "+ChronoUnit.DAYS.between(date1, date2));
	}

}
