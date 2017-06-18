import java.util.HashSet;

public class HashSetTestApp {

	public static void main(String[] args) {
		int[] numbers = { 16, 32, 64, 128, 256, 512, 1024, 32, 128, 48, 80, 232, 144 };
		HashSet<Integer> set = new HashSet<>();
		for(int number:numbers)
		{
			set.add(number);
		}
		System.out.println(set);
	}

}
