import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MainApp {
	private static final long ARRAY_SIZE = 5000;
	private static final int MIN_BOUND = 0;
	private static final int MAX_BOUND = 10000;
	static int THREADS[] = { 1, 2, 4, 10, 50, 100, 200, 500 };

	public static void main(String[] args) throws InterruptedException {
		Integer[] array = new Random().ints(ARRAY_SIZE, MIN_BOUND, MAX_BOUND).boxed().toArray(Integer[]::new);
		for (int l = 0; l < THREADS.length; l++) {
			Integer[] arr = array.clone();
			// System.out.println("Initial array: " + Arrays.toString(array));
			SortingProcess<Integer> process = new SortingProcess<>(arr, THREADS[l]);
			Instant start = Instant.now();
			process.start();
			process.join();
			Instant finish = Instant.now();
			// System.out.println("Fully sorted: " +
			// Arrays.toString(process.getArray()));
			System.out
					.println("Trds:" + THREADS[l] + ";Time" + ChronoUnit.MILLIS.between(start, finish));
		}
	}
}
