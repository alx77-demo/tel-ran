package tel_ran.employee;

import java.util.Arrays;

public class TaxCalculator {
	protected TaxGrade[] taxGrades = new TaxGrade[7];

	public TaxCalculator() {
		this.taxGrades[0] = new TaxGrade(0, 10);
		this.taxGrades[1] = new TaxGrade(6220, 14);
		this.taxGrades[2] = new TaxGrade(8920, 20);
		this.taxGrades[3] = new TaxGrade(14320, 31);
		this.taxGrades[4] = new TaxGrade(19800, 35);
		this.taxGrades[5] = new TaxGrade(41410, 47);
		this.taxGrades[6] = new TaxGrade(66960, 50);
		Arrays.sort(this.taxGrades);
	}

	public double calculateTax(double salary, double nikudPoints) {
		double tax = 0;
		TaxNikud taxNikud = new TaxNikud(nikudPoints);
		for (int i = taxGrades.length - 1; i >= 0; i--) {
			if (taxGrades[i].getSalaryAmount() < salary) {
				tax += (salary - taxGrades[i].getSalaryAmount()) * taxGrades[i].getTaxDegree() / 100;
				salary = taxGrades[i].getSalaryAmount();
			}
		}
		double rebate = taxNikud.getRebate();
		return tax < rebate ? 0 : (tax - rebate);
	}

	public static void main(String[] args) {
		TaxCalculator calc = new TaxCalculator();
		double salary = 13000.0, nikudPoints = 2.25 + 0.5 + 2 + 1 + 3;
		System.out.println(salary + "-" + calc.calculateTax(salary, nikudPoints));
		System.out.println(salary - calc.calculateTax(salary, nikudPoints));
	}

}
