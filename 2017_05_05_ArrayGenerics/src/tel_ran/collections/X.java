package tel_ran.collections;

public class X implements Comparable<X> {
	Object member;

	public X(Object obj) {
		member = obj;
	}

	@Override
	public int compareTo(X obj) {
		return (int) member - (int) obj.member;
	}

	@Override
	public String toString() {
		return "X [member=" + member + "]";
	}

}
