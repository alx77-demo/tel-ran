package tel_ran.enums;

public enum WeekDayLang {
	ENGLISH(1), HEBREW(2), RUSSIAN(3);

	int value;

	public int getValue() {
		return value;
	}

	private WeekDayLang(int value) {
		this.value = value;
	}
}
