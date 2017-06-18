package tel_ran.numbers;

import java.util.HashSet;

public class NumberBoxHashSet extends NumberBoxIterable implements NumberBox {

	public NumberBoxHashSet(int[] in) {
		super();
		box = new HashSet<>();
		for (int i = 0; i < in.length; i++)
			box.add(in[i]);
	}

}
