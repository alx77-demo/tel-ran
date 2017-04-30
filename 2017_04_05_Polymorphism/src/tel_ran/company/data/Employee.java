package tel_ran.company.data;

public abstract class Employee {
	private int id;
	private String name;
	private int basicSalary;
	private static int minBasicSalary = 5000;

	public Employee() {
		super();
	}

	public Employee(int id, String name, int basicSalary) {
		super();
		this.id = id;
		this.name = name;
		setBasicSalary(basicSalary);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(int basicSalary) {
		this.basicSalary = (this.basicSalary < minBasicSalary) ? minBasicSalary : basicSalary;
	}

	public static int getMinBasicSalary() {
		return minBasicSalary;
	}

	public static void setMinBasicSalary(int minBasicSalary) {
		Employee.minBasicSalary = minBasicSalary;
	}
	public abstract int computeSalary();
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", basicSalary=" + basicSalary + "]";
	}
}
