package tel_ran.numbers;

public class NumbersGenerator {
	private int min;
	private int max;
	private int size;

	public NumbersGenerator(int min, int max, int size) {
		super();
		this.min = min;
		this.max = max;
		this.size = size;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int[] generate() {
		return null;
	}
}
