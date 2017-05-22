package tel_ran.random;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class RandomGenerator {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static final SecureRandom gen = new SecureRandom();

	// min - inclusive, max - exclusive
	public Integer getRandomInteger(Integer min, Integer max) {
		return (gen.nextInt(max - min) + min);
	}

	public Long getRandomLong(Long min, Long max) {
		Double d = gen.nextDouble() * (max - min);
		return (d.longValue() + min);
	}

	public Float getRandomFloat(Float min, Float max) {
		return ((max - min) * gen.nextFloat() + min);
	}

	public Double getRandomDouble(Double min, Double max) {
		return ((max - min) * gen.nextDouble() + min);
	}

	public String generateRandomString(int minLength, int maxLength) {
		int len = gen.nextInt(maxLength - minLength) + minLength;
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(gen.nextInt(AB.length())));
		return sb.toString();
	}

	public LocalDate getRandomDate(LocalDate minDate, LocalDate maxDate) {
		long days = ChronoUnit.DAYS.between(minDate, maxDate);
		Period p = Period.ofDays(gen.nextInt((int) days));

		return minDate.plus(p);
	}
}
