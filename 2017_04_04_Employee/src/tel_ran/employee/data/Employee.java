package tel_ran.employee.data;

public class Employee {

	private int id;
	private String name;
	private boolean sex;
	private double salary;
	private int age;
	private static double taxLevel;

	public Employee(int id, String name, String sex, double salary, int age) {
		super();
		this.setId(id);
		this.name = name;
		this.setSex(sex);
		this.salary = salary;
		this.setAge(age);
	}

	public boolean equals(Employee e) {
		if (e == null)
			return false;
		return this.id == e.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (sex ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (age != other.age)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		if (sex != other.sex)
			return false;
		return true;
	}

	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 100000000 && id <= 999999999) {
			this.id = id;
		} else {
			this.id = -1;
			System.out.println("Ohh my GOD!!!");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public void setSex(String sex) {
		this.sex = sex.equalsIgnoreCase("male");
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age >= 18 && age <= 80) {
			this.age = age;
		} else {
			this.age = -1;
			System.out.println("Ohh my GOD!!!");
		}
	}

	public double getTax() {
		if (salary < taxLevel)
			return 0;
		else
			return (salary - 1000) * 0.17;
	}

	public static void setTaxLevel(double taxLevel) {
		Employee.taxLevel = taxLevel;
	}

	@Override
	public String toString() {
		return "ID:" + this.id + "; name:" + this.name + "; age:" + this.age + "; salary:" + this.salary + "; tax:"
				+ this.getTax() + "; gender:" + (this.sex ? "male" : "female");
	}
}
