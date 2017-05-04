package tel_ran.collections.predicates;

import java.util.function.Predicate;

public class PredicateEven implements Predicate<Integer> {

	@Override
	public boolean test(Integer num) {
//		String numStr = ((String) num).substring(5);
		Integer number = num;//Integer.valueOf(numStr); 
		return number!=0&&number%2==0;
	}

}
