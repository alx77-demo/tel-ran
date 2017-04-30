package tel_ran.company.control;

import tel_ran.company.data.Employee;
import tel_ran.company.data.Manager;
import tel_ran.company.data.SalesManager;
import tel_ran.company.data.WageEmployee;

public class EmployeeTestAppl {
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			Employee emp = getEmployee(args[i]);
			displayEmployee(emp);
		}

	}

	private static void displayEmployee(Employee emp) {
		System.out.println(emp);

	}

	private static Employee getEmployee(String string) {
		Employee emp = null;
		switch (string.toLowerCase()) {
		case "manager":
			emp = new Manager(123, "Masha", 1000, 1);
			break;
		case "salesmanager":
			emp = new SalesManager(234, "Dunia", 2000, 15, 10, 12, 50);
			break;
		case "wageemployee":
			emp = new WageEmployee(567,"Boria", 4000, 20, 20);
			break;
		}
		return emp;
	}
}
