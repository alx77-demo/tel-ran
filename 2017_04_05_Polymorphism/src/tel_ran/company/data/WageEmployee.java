package tel_ran.company.data;

public class WageEmployee extends Employee {
	private int wage;
	private int hours;

	public WageEmployee() {
		super();
	}

	public WageEmployee(int id, String name, int basicSalary, int wage, int hours) {
		super(id, name, basicSalary);
		this.wage = wage;
		this.hours = hours;
	}

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int computeSalary() {
		return getBasicSalary() + wage * hours;
	}

	@Override
	public String toString() {
		return "WageEmployee [wage=" + wage + ", hours=" + hours + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getBasicSalary()=" + getBasicSalary() + "]";
	}

}
