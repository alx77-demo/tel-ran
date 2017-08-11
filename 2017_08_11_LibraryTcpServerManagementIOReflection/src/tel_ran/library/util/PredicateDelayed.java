package tel_ran.library.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

import tel_ran.library.entities.BookRecord;

public class PredicateDelayed implements Predicate<BookRecord> {
int pickPeriod;
LocalDate currentDate;
	public PredicateDelayed(int pickPeriod,LocalDate currentDate) {
	super();
	this.pickPeriod = pickPeriod;
	this.currentDate = currentDate;
}
	@Override
	public boolean test(BookRecord t) {
		return t.getReturnDate()==null&&ChronoUnit.DAYS.
				between(t.getPickDate(), currentDate)>pickPeriod;
	}

}
