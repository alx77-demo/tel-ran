
public class Demo {

	public static void main(String[] args) {


		for (int i = 0; i <= 15; i = i + 5) {
			Integer age = 15;
			age = age * 2;
			if (age < 10) {
				System.out.println("child");
			} else if (age < 20) {
				System.out.println("teenager");
			} else if (age < 40) {
				System.out.println("young man");
			} else if (age < 70) {
				System.out.println("old man");
			} else if (age < 200) {
				System.out.println("starik");
			}
		}
	}

}
