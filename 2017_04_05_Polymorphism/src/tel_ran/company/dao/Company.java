package tel_ran.company.dao;

import tel_ran.company.data.Employee;
import tel_ran.company.data.Manager;
import tel_ran.company.data.SalesManager;
import tel_ran.company.data.WageEmployee;

public class Company implements ICompany {
	Employee[] company;
	int capacity;
	int size;

	public Company(Employee[] company) {
		super();
		this.company = company;
		this.capacity = company.length;
		this.size = 0;
		company = new Employee[capacity];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tel_ran.company.dao.ICompany#addEmployee(tel_ran.company.data.Employee)
	 */
	@Override
	public boolean addEmployee(Employee emp) {
		if (size == capacity) {
			return false;
		}
		if (getEmployeeById(emp.getId()) != null) {
			return false;
		}
		company[size++] = emp;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tel_ran.company.dao.ICompany#removeEmployee(int)
	 */
	@Override
	public boolean removeEmployee(int id) {
		int i = 0;
		for (; i < size && company[i].getId() != id; i++)
			;
		if (i < size) {
			company[i] = company[--size];
			size--;
			return true;
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tel_ran.company.dao.ICompany#getEmployeeById(int)
	 */
	@Override
	public Employee getEmployeeById(int id) {
		int i = 0;
		while (i < size && company[i].getId() != id) {
			i++;
		}
		return i < size ? company[i] : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tel_ran.company.dao.ICompany#getEmployees(java.lang.String)
	 */
	@Override
	public Employee[] getEmployees(String name) {
		int i = 0, cntr = 0;
		while (i < size) {
			if (company[i].getName() == name)
				cntr++;
			i++;
		}
		Employee emp[] = new Employee[cntr];
		for (i = 0; cntr > 0; i++) {
			if (company[i].getName() == name) {
				cntr--;
				emp[cntr] = company[i];
			}
		}
		return emp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tel_ran.company.dao.ICompany#getEmployees(int, int)
	 */
	@Override
	public Employee[] getEmployees(int minSal, int maxSal) {
		int i = 0, cntr = 0;
		while (i < size) {
			if (company[i].getBasicSalary() > minSal && company[i].getBasicSalary() < maxSal)
				cntr++;
			i++;
		}
		Employee emp[] = new Employee[cntr];
		for (i = 0; cntr > 0; i++) {
			if (company[i].getBasicSalary() > minSal && company[i].getBasicSalary() < maxSal) {
				cntr--;
				emp[cntr] = company[i];
			}
		}
		return emp;
	}
}
