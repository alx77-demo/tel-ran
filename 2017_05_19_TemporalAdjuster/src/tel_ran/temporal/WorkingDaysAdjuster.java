package tel_ran.temporal;

import static java.time.temporal.ChronoField.ALIGNED_WEEK_OF_YEAR;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;

import java.time.DayOfWeek;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;

public class WorkingDaysAdjuster implements TemporalAdjuster {
	protected ArrayList<DayOfWeek> daysOff;

	protected ArrayList<Integer> daysOffInt = new ArrayList<Integer>();

	public WorkingDaysAdjuster(ArrayList<DayOfWeek> daysOff) {
		this.daysOff = daysOff;
		for (DayOfWeek dayOfWeek : daysOff) {
			daysOffInt.add(dayOfWeek.getValue());
		}
	}

	@Override
	public Temporal adjustInto(Temporal temporal) {
		
		int dow = temporal.get(DAY_OF_WEEK);
		dow++;
		while (daysOffInt.contains(dow))
			dow++;
		long weekLength = temporal.range(DAY_OF_WEEK).getMaximum();
		if (dow > weekLength) {
			dow -= weekLength;
			temporal = temporal.with(ALIGNED_WEEK_OF_YEAR, temporal.get(ALIGNED_WEEK_OF_YEAR) + 1);
		}

		return temporal.with(DAY_OF_WEEK, dow);
	}
}
