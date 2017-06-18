package tel_ran.numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class NumberBoxArrayList extends CommonNumberBox implements NumberBox {

	public NumberBoxArrayList(int[] in) {
		super();
		box = new ArrayList<>();
		for (int i = 0; i < in.length; i++)
			box.add(in[i]);
	}

	protected int[] runThrough(Predicate<Integer> p) {
		List<Integer> lbox=(List<Integer>) box, resList = new ArrayList<>();
		int size = lbox.size();
		Integer val;
		for (int i = 0; i < size; i++) {
			val = lbox.get(i);
			if (p.test(val))
				resList.add(val);
		}
		int[] res = convert(resList);

		return res;

	}

}
