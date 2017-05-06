package tel_ran.collections.predicates;

import java.util.function.Predicate;

public class PredicateEquals<T> implements Predicate<T> {
	T obj;

	public PredicateEquals(T obj) {
		this.obj = obj;
	}

	@Override
	public boolean test(T obj) {
		return obj.equals(this.obj);
	}

}
