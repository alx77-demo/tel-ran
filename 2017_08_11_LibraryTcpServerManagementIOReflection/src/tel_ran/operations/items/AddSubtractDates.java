package tel_ran.operations.items;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class AddSubtractDates extends OperationItem {

	protected boolean fl_add;

	@Override
	public void perform() {
		String format="dd/MM/yy";
		LocalDate date=inputOutput.getDate("Enter date in the format "+format,format );
		int days=inputOutput.getInteger("Enter number days from 10 to 100", 10, 100);
		LocalDate resDate=date.plusDays(fl_add?days:-days);
		inputOutput.put("result: "+resDate.format(DateTimeFormatter.ofPattern(format)));

	}

}
