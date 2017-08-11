import java.util.Random;
import java.util.stream.Collectors;

public class StreamApp {
	private static final long ARRAY_SIZE = 5000;
	private static final int MIN_BOUND = 0;
	private static final int MAX_BOUND = 1000;

	public static void main(String[] args) {
		
		System.out.println(""+new Random().ints(ARRAY_SIZE, MIN_BOUND, MAX_BOUND).parallel().peek(s->{System.out.println(Thread.currentThread().getName());}).boxed().collect(Collectors.counting()));

	}

}
