package tel_ran.numbers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class NumberBoxLinkedList extends CommonNumberBox implements NumberBox {

	public NumberBoxLinkedList(int[] in) {
		super();
		box = new LinkedList<>();
		for (int i = 0; i < in.length; i++)
			box.add(in[i]);
	}

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
