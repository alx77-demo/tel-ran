package tel_ran.enums.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tel_ran.enums.WeekDayLang;
import tel_ran.enums.WeekDayNumber;

public class WeekDayTest {

	@Test
	public void testGetValue() {
		assertEquals(WeekDayNumber.FRIDAY.getValue(), WeekDayNumber.ו.getValue());
		assertEquals(WeekDayNumber.FRIDAY.getValue(), WeekDayNumber.ПЯТНИЦА.getValue());
		assertEquals(WeekDayNumber.getWeekDay(WeekDayNumber.ה, WeekDayLang.RUSSIAN), WeekDayNumber.ЧЕТВЕРГ);
	}
}
