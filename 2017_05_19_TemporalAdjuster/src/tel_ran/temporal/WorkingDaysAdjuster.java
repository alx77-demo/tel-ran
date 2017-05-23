package tel_ran.temporal;

import static java.time.temporal.ChronoField.DAY_OF_WEEK;

import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;

public class WorkingDaysAdjuster implements TemporalAdjuster {
	protected DayOfWeek daysOff[];

	protected ArrayList<Integer> daysOffInt = new ArrayList<Integer>();
	protected int step;

	public WorkingDaysAdjuster(DayOfWeek daysOff[], int step) {
		this.daysOff = daysOff;
		for (DayOfWeek dayOfWeek : daysOff) {
			daysOffInt.add(dayOfWeek.getValue());
		}
		this.step = step;
	}

	@Override
	public Temporal adjustInto(Temporal temporal) {

		int i = 0;
		Temporal res = temporal;
		while (i < step) {
			if (!daysOffInt.contains(res.get(DAY_OF_WEEK))) {
				i++;
			}
			res = res.plus(1, ChronoUnit.DAYS);
		}

		return res;
	}
}
