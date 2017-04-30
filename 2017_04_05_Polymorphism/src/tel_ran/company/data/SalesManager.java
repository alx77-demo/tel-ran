package tel_ran.company.data;

public class SalesManager extends WageEmployee {
	private int percent;
	private int sales;

	public SalesManager() {
		super();
	}

	public SalesManager(int id, String name, int basicSalary, int wage, int hours, int percent, int sales) {
		super(id, name, basicSalary, wage, hours);
		this.percent = percent;
		this.sales = sales;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public int computeSalary() {
		return super.computeSalary() + sales * percent / 100;
	}

	@Override
	public String toString() {
		return "SalesManager [percent=" + percent + ", sales=" + sales + ", getPercent()=" + getPercent()
				+ ", computeSalary()=" + computeSalary() + "]";
	}

}
