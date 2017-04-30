package tel_ran.company.dao;

import tel_ran.company.data.Employee;

public interface ICompany {

	boolean addEmployee(Employee emp);

	boolean removeEmployee(int id);

	Employee getEmployeeById(int id);

	Employee[] getEmployees(String name);

	Employee[] getEmployees(int minSal, int maxSal);

}