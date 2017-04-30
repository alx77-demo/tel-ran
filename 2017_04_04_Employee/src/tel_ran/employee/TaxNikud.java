package tel_ran.employee;

public class TaxNikud {
	public static final double NIKUD_RATE = 216.0;
	double value;

	public double getValue() {
		return value;
	}

	public TaxNikud(double value) {
		super();
		this.value = value;
	}

	public double getRebate() {
		return value * TaxNikud.NIKUD_RATE;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
