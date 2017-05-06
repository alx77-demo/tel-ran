package tel_ran.collections.predicates;

import java.util.function.Predicate;

import tel_ran.collections.Pair;

public class PredicateEqualsPair implements Predicate<Pair<Object, Object>> {

	@Override
	public boolean test(Pair<Object, Object> p) {
		return p.getLeft().equals(p.getRight());
	}

}
