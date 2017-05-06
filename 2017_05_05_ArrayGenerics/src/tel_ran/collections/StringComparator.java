package tel_ran.collections;

import java.util.Comparator;

public class StringComparator<T> implements Comparator<T> {

	@Override
	public int compare(T left, T right) {
		return ((String) left).compareTo((String) right);
	}

}
