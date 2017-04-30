package tel_ran.collections;

import java.util.Comparator;

public class XComparator implements Comparator<X> {

	@Override
	public int compare(X left, X right) {
		return left.compareTo(right);
	}

}
