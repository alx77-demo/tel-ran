package tel_ran.collections;

import java.util.function.Consumer;

public class SumConsumer implements Consumer<Integer> {
	int sum = 0;

	@Override
	public void accept(Integer t) {
		sum += t;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

}
