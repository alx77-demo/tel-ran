
public class Army {

	public static void main(String[] args) {
		int age = 12;
		boolean res = checkArmy(age);
		System.out.println(res);
	}

	private static boolean checkArmy(int age) {
		//return age >= 18 && age < 45;
		return !(age < 18 || age >= 45); //faster if most of people younger, because || - lazy operation
	}

}
