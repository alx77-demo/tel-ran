package tel_ran.numbers.rules;

import tel_ran.numbers.RuleViolationException;

public interface Rule {

	public void match(int number) throws RuleViolationException;
}
