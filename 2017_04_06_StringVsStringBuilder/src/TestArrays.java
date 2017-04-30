
public class TestArrays {

	private static final int CAPACITY = 20000;

	public static void main(String[] args) {
		int arr[][] = new int[CAPACITY][CAPACITY];
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = i * j;
			}
		}
		System.out.println(System.currentTimeMillis()-startTime);
		
		startTime = System.currentTimeMillis();

		for (int i = 0; i < arr.length; i++) {
			arr[i][i] = i * i;
			for (int j = i+1; j < arr[i].length; j++) {
				arr[i][j] = i * j;
				arr[j][i] = i * j;
			}
		}
		System.out.println(System.currentTimeMillis()-startTime);

	}

}
