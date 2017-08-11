package tel_ran.view.tests;

import java.util.function.Predicate;

public class EvenPredicate implements Predicate<Integer> {

	@Override
	public boolean test(Integer t) {
		// TODO Auto-generated method stub
		 return t%2==0;
	}

}
