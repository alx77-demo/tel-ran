package tel_ran.computer.data;

public class Laptop extends Computer {
	protected int batteryHours;
	protected double weight;

	
	public Laptop(String brand, int hd, int ram, int batteryHours, double weight) {
		super(brand, hd, ram);
		this.batteryHours = batteryHours;
		this.weight = weight;
	}

	public int getBatteryHours() {
		return batteryHours;
	}

	public void setBatteryHours(int batteryHours) {
		this.batteryHours = batteryHours;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Laptop [batteryHours=" + batteryHours + ", weight=" + weight + ", brand=" + brand + ", hd=" + hd
				+ ", ram=" + ram + "]";
	}
}
