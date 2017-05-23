package tel_ran.enums;

import static tel_ran.enums.WeekDayLang.*;

public enum WeekDayNumber {
	SUNDAY(1, ENGLISH), MONDAY(2, ENGLISH), TUESDAY(3, ENGLISH), WEDNESDAY(4, ENGLISH), THURSDAY(5, ENGLISH), FRIDAY(6, ENGLISH), SATURDAY(7, ENGLISH),
	א(1, HEBREW), ב(2, HEBREW), ג(3, HEBREW), ד(4, HEBREW), ה(5, HEBREW), ו(6,HEBREW), שבת(7, HEBREW),
	ВОСКРЕСЕНЬЕ(1, RUSSIAN), ПОНЕДЕЛЬНИК(2, RUSSIAN), ВТОРНИК(3, RUSSIAN), СРЕДА(4, RUSSIAN), ЧЕТВЕРГ(5, RUSSIAN), ПЯТНИЦА(6, RUSSIAN), СУББОТА(7, RUSSIAN);

	private int value;
	private WeekDayLang lang;

	public int getValue() {
		return value;
	}

	static public WeekDayNumber getWeekDay(WeekDayNumber number, WeekDayLang lang) {
		//WeekDayNumber asd= WeekDayNumber.values()[1];
		for (WeekDayNumber wdn : WeekDayNumber.values()) {
			if (wdn.lang == lang && wdn.value == number.value)
				return wdn;
		}
		return null;
	}

	private WeekDayNumber(int value, WeekDayLang lang) {
		this.value = value;
		this.lang = lang;
	}
}

