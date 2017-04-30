package tel_ran.collections;

import java.util.Comparator;

public class StringComparator implements Comparator<Object> {

	@Override
	public int compare(Object left, Object right) {
		return ((String) left).compareTo((String) right);
	}

}
