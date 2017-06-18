package tel_ran.numbers;

import java.util.HashSet;

public class NumberBoxTreeSet extends NumberBoxIterable implements NumberBox {

	public NumberBoxTreeSet(int[] in) {
		super();
		box = new HashSet<>();
		for (int i = 0; i < in.length; i++)
			box.add(in[i]);
	}

}
