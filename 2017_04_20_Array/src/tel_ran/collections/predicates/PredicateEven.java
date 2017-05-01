package tel_ran.collections.predicates;

import java.util.function.Predicate;

public class PredicateEven implements Predicate<Object> {

	@Override
	public boolean test(Object num) {
//		String numStr = ((String) num).substring(5);
		Integer number = (Integer)num;//Integer.valueOf(numStr); 
		return number!=0&&number%2==0;
	}

}
