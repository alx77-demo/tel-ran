package tel_ran.company.control;

import java.security.SecureRandom;
import java.util.Random;

import tel_ran.company.dao.*;
import tel_ran.company.data.*;

public class CompanyAppl {
	private static final int CAPACITY = 10;
	private static final int MAX_ID = 999999999;
	private static final int MIN_ID = 100000000;

	public static void main(String[] args) {
		Employee[] guys = new Employee[CAPACITY];
		generateEmployees(guys);
		ICompany comp = new Company(guys);
		System.out.println(comp.getEmployeeById(234));
		// displayEmployees(comp.getEmployees("Masha"));
		displayEmployees(comp.getEmployees(0, 100000));

	}

	private static void generateEmployees(Employee[] guys) {
		for (int i = 0; i < guys.length; i++) {
			guys[i] = generateEmployee();
		}
	}

	private static Employee generateEmployee() {
		Employee emp = null;
		int type = (int) (Math.random() * 2);
		switch (type) {
		case 1:
			emp = new SalesManager(generateId(), generateName(), 2000, 15, 10, 12, 50);
			break;
		case 2:
			emp = new WageEmployee(generateId(), generateName(), 4000, 20, 20);
			break;
		case 0:
		default:
			emp = new Manager(generateId(), generateName(), 1000, 1);
			break;
		}

		return emp;
	}

	private static int generateId() {
		return (int) (Math.random() * (MAX_ID - MIN_ID) + MIN_ID);
	}

	private static String generateName() {
		String name = generateRandomString(3, 10);
		return null;
	}

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	private static String generateRandomString(int minLength, int maxLength) {
		int len = rnd.nextInt(maxLength-minLength)+minLength;
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	private static void displayEmployees(Employee[] employees) {
		for (int i = 0; i < employees.length; i++) {
			System.out.println(employees[i]);

		}
	}

}
