package tel_ran.computer.data;

public class SmartPhone extends Laptop {
	long imei;

	public SmartPhone(String brand, int hd, int ram, int batteryHours, double weight, long imei) {
		super(brand, hd, ram, batteryHours, weight);
		this.imei = imei;
	}

	public long getImei() {
		return imei;
	}

	public void setImei(long imei) {
		this.imei = imei;
	}

	@Override
	public String toString() {
		return "SmartPhone [imei=" + imei + ", batteryHours=" + batteryHours + ", weight=" + weight + ", brand=" + brand
				+ ", hd=" + hd + ", ram=" + ram + "]";
	}

}
