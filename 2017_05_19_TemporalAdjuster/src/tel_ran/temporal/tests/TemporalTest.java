package tel_ran.temporal.tests;

import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;

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
		TemporalAdjuster wda = new WorkingDaysAdjuster(new DayOfWeek[] { DayOfWeek.SATURDAY, DayOfWeek.SUNDAY }, 1);

		LocalDate ld = LocalDate
				.parse("2017-05-22")/* of(2017, Month.MAY, 22) */, newDate;
		newDate = ld.with(wda);
		for (int i = 0; i < 97; i++) {
			newDate = newDate.with(wda);
		}
		System.out.println(newDate);
		assertEquals(LocalDate.parse("05/10/2017", DateTimeFormatter.ofPattern("dd/MM/yyyy")), newDate);
	}

}
// написать свой аджастер TemporalAdjuster<-WorkingDays(DayOfWeek[], Weekends[],
// int days)возвращает количество рабочих дней,
