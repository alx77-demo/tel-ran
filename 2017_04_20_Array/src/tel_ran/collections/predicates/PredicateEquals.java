package tel_ran.collections.predicates;

import java.util.function.Predicate;

public class PredicateEquals implements Predicate<Object> {
	Object obj;

	public PredicateEquals(Object obj) {
		this.obj = obj;
	}

	@Override
	public boolean test(Object obj) {
		return obj.equals(this.obj);
	}

}
