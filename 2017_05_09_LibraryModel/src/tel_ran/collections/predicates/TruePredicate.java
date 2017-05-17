package tel_ran.collections.predicates;

import java.util.function.Predicate;

public class TruePredicate<T> implements Predicate<T> {

	@Override
	public boolean test(T t) {
		return true;
	}

}
