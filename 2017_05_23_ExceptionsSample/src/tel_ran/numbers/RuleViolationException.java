package tel_ran.numbers;

public class RuleViolationException extends Exception {

	private static final long serialVersionUID = 1L;

	private int delta;

	public RuleViolationException(int delta) {
		super();
		this.delta = delta;
	}
	
	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

}
