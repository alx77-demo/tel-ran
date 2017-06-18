package tel_ran.numbers;

public interface Rule {
	boolean match(int number) throws RuleViolationException;
}
