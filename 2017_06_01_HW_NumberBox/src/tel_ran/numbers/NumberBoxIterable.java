package tel_ran.numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class NumberBoxIterable extends CommonNumberBox {

	@Override
	protected int[] runThrough(Predicate<Integer> p) {
		List<Integer> resList = new ArrayList<>();
		for (Integer i : box) {
			if (p.test(i))
				resList.add(i);
		}
		int[] res = convert(resList);

		return res;
	}

}
