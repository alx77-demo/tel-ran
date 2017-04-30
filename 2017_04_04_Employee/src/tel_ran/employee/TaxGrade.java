package tel_ran.employee;

public class TaxGrade implements Comparable<TaxGrade> {
	protected double salaryAmountLowerBound;
	protected double taxDegreeInPercent;

	public TaxGrade(double salaryAmountLowerBound, double taxDegreeInPercent) {
		super();
		this.salaryAmountLowerBound = salaryAmountLowerBound;
		this.taxDegreeInPercent = taxDegreeInPercent;
	}

	public double getSalaryAmount() {
		return salaryAmountLowerBound;
	}

	public void setSalaryAmount(double salaryAmount) {
		this.salaryAmountLowerBound = salaryAmount;
	}

	public double getTaxDegree() {
		return taxDegreeInPercent;
	}

	public void setTaxDegree(double taxDegree) {
		this.taxDegreeInPercent = taxDegree;
	}

	@Override
	public int compareTo(TaxGrade o) {
		return Double.compare(this.salaryAmountLowerBound, o.salaryAmountLowerBound);
	}
}
