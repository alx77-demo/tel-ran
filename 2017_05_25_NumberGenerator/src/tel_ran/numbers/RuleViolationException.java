package tel_ran.numbers;

public class RuleViolationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int delta;

	public RuleViolationException(int delta) {
		this.delta = delta;
	}

	public int getDelta() {
		return delta;
	}
}
