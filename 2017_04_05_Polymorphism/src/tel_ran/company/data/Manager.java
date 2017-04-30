package tel_ran.company.data;

public class Manager extends Employee {
	private int grade;

	public Manager() {
		super();
	}

	public Manager(int id, String name, int basicSalary, int grade) {
		super(id, name, basicSalary);
		this.grade = grade;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int computeSalary() {
		return grade*getBasicSalary();
	}

	@Override
	public String toString() {
		return "Manager [grade=" + grade + ", id=" + getId() + ", name=" + getName() + ", basicSalary=" + getBasicSalary() + "]";
	}

}
