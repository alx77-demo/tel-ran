package tel_ran.temporal.tests;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import tel_ran.temporal.WorkingDaysAdjuster;

public class TemporalTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		// TemporalUnit unit;
		// Temporal tmp;
		// ZonedDateTime zdt;

		TemporalAdjuster wda = new WorkingDaysAdjuster(
				new ArrayList<>(Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)));

		LocalDate ld = LocalDate.of(2017, Month.MAY, 22), newDate;
		newDate = ld.with(wda);
		for (int i = 0; i < 97; i++) {
			newDate = newDate.with(wda);
		}
		System.out.println(newDate);
		assertEquals(newDate, LocalDate.of(2017, Month.OCTOBER, 5));
	}

}
// написать свой аджастер TemporalAdjuster<-WorkingDays(DayOfWeek[], Weekends[],
// int days)возвращает количество рабочих дней,
