import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterTestApp {

	private static final long N_NUMBERS = 10000;
	private static final int MIN_VALUE = 1;
	private static final int MAX_VALUE = 2000000;
	private static final int RANGE_MIN = 100;
	private static final int RANGE_MAX = 200;
	private static final int NUMBERS_IN_RANGE = 6;
	private static final int RANGES_COUNT = 10;

	private static final int N_RUNS = 10000;

	public static void main(String[] args) {
		List<Integer> numbers = getRandomNumbers();
		// System.out.print(" simple - ");
		// displayEvenNumbersSumTimeSimple(numbers);
		// System.out.print(" stream - ");
		// displayEvenNumbersSumTimeStream(numbers);
		// System.out.print("parallel - ");
		// displayEvenNumbersSumTimeParallelStream(numbers);
		// System.out.print("nostream - ");
		// displayEvenNumbersSumTimeNoStream(numbers);
//		numbers.stream().collect(Collectors.groupingBy(x -> x % 2 == 0 ? "even" : "odd")).entrySet()
//				.forEach(System.out::println);
		// по кол-ву цифр в числе
//		numbers.stream().collect(Collectors.groupingBy(x -> ((Integer) x.toString().length()).toString())).entrySet()
//				.forEach(System.out::println);
		// по кол-ву множителей
//		numbers.stream().collect(Collectors.groupingBy(x -> {
//			int counter = 0;
//			for (int i = x; i >= 1; i--) {
//				if (x % i == 0)
//					counter++;
//			}
//			return counter;
//		})).entrySet().forEach(System.out::println);

		// уникальные случ числа в случ диапазоне определённое количество
		numbers.stream().filter(x -> x > RANGE_MIN && x < RANGE_MAX).distinct().limit(NUMBERS_IN_RANGE)
				.collect(Collectors.groupingBy(x -> "rnd")).entrySet().forEach(System.out::println);
	}

	private static void displayEvenNumbersSumTimeSimple(List<Integer> numbers) {
		Instant start = Instant.now();
		int res = 0;

		for (int i = 0; i < N_RUNS; i++) {
			for (int num : numbers) {
				if (num % 2 == 0)
					res += num;
			}
		}

		Instant end = Instant.now();
		System.out.println("res:" + res + " time:" + ChronoUnit.MILLIS.between(start, end));
	}

	private static void displayEvenNumbersSumTimeNoStream(List<Integer> numbers) {
		Instant start = Instant.now();

		int res = 0;
		for (int i = 0; i < N_RUNS; i++) {
			Collection<Integer> evenNumbers = filter(numbers, x -> x % 2 == 0);
			for (int number : evenNumbers)
				res += number;
		}
		Instant stop = Instant.now();
		System.out.println("res:" + res + " time:" + ChronoUnit.MILLIS.between(start, stop));
	}

	private static void displayEvenNumbersSumTimeStream(List<Integer> numbers) {
		Instant start = Instant.now();
		int res = 0;
		for (int i = 0; i < N_RUNS; i++) {
			res += numbers.stream().filter(x -> x % 2 == 0).mapToInt(x -> x).sum();
		}
		Instant stop = Instant.now();
		System.out.println("res:" + res + " time:" + ChronoUnit.MILLIS.between(start, stop));
	}

	private static void displayEvenNumbersSumTimeParallelStream(List<Integer> numbers) {
		Instant start = Instant.now();
		int res = 0;
		for (int i = 0; i < N_RUNS; i++) {
			res += numbers.parallelStream().filter(x -> x % 2 == 0).mapToInt(x -> x).sum();
		}

		Instant stop = Instant.now();
		System.out.println("res:" + res + " time:" + ChronoUnit.MILLIS.between(start, stop));
	}

	public static <T> Collection<T> filter(Collection<T> collection, Predicate<T> predicate) {
		Collection<T> res = new ArrayList<T>();
		for (T obj : collection) {
			if (predicate.test(obj))
				res.add(obj);
		}
		return res;
	}

	private static List<Integer> getRandomNumbers() {
		return new Random().ints(N_NUMBERS, MIN_VALUE, MAX_VALUE + 1).mapToObj(x -> x).collect(Collectors.toList());
	}
}
