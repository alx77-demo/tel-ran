package tel_ran.collections;

import java.util.function.Consumer;

public class SumConsumer implements Consumer<Object> {
	int sum = 0;

	@Override
	public void accept(Object t) {
		sum += (int) t;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

}
