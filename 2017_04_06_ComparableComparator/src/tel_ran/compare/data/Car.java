package tel_ran.compare.data;

import tel_ran.array.tools.IComparable;

public class Car implements IComparable{
	private String vendor;
	private String model;
	private double displacement;
	private int year;
	
	public Car(String vendor, String model, double displacement, int year) {
		super();
		this.vendor = vendor;
		this.model = model;
		this.displacement = displacement;
		this.year = year;
	}

	@Override
	public int compare(Object obj) {
		Car car = (Car) obj;
		return this.year-car.year;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public double getDisplacement() {
		return displacement;
	}

	public void setDisplacement(double displacement) {
		this.displacement = displacement;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Car [vendor=" + vendor + ", model=" + model + ", displacement=" + displacement + ", year=" + year + "]";
	}
}
