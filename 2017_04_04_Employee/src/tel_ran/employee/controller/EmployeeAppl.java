package tel_ran.employee.controller;

import tel_ran.employee.data.Employee;

public class EmployeeAppl {

	public static void main(String[] args) {
		Employee vasya = new Employee(123123123, "Peter", "male", 2000, 77);
		// vasya.setId(123123123);
		// vasya.setAge(77);
		// vasya.setName("Peter");
		// vasya.setSalary(2000);
		// vasya.setSex(true);

		Employee peter = new Employee(/* 987123828, "Vasia", "male", 3000, 44 */);
		peter.setId(987123828);
		peter.setAge(44);
		peter.setName("Vasia");
		peter.setSalary(3000);
		peter.setSex("male");

		Employee serg = new Employee(987123828, "Vasia", "male", 3000, 44);

		Employee.setTaxLevel(2500);

		System.out.println(vasya);
		System.out.println(peter);
		System.out.println(serg);
		serg = null;
		System.out.println(peter.equals(serg));

	}

}
