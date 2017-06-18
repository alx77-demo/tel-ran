import java.util.TreeSet;

public class TreeSetTestAppl {
	public static void main(String[] args) {
		int[] numbers = { 10, 3, 20, -8, 100 };
		TreeSet<Integer> tree = new TreeSet<>();
		for (int number : numbers) {
			tree.add(number);
		}
		System.out.println("\nAll numbers:");
		display(tree);
		System.out.println("\nAll numbers less than or equal 20:");
		display(tree.headSet(20, true));
		System.out.println("\nAll numbers greater than 20:");
		display(tree.tailSet(20, false));
		System.out.println("\nAll numbers between -8 and 10:");
		display(tree.subSet(-8, 10));
		tree.subSet(-8, 10).clear();
		System.out.println("\nAll numbers:");
		display(tree);
		System.out.println(tree.ceiling(21));
		System.out.println(tree.floor(21));
	}

	static void display(Iterable<Integer> numbers) {
		for (int number : numbers) {
			System.out.print(number + " ");
		}
		System.out.println("\n---------\n");
	}
}
