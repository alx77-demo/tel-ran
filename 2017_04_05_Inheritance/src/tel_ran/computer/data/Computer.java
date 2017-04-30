package tel_ran.computer.data;

public class Computer {
	protected String brand;
	protected int hd;
	protected int ram;

	public Computer() {
		super();
	}

	public Computer(String brand, int hd, int ram) {
		super();
		this.brand = brand;
		this.hd = hd;
		this.ram = ram;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getHd() {
		return hd;
	}

	public void setHd(int hd) {
		this.hd = hd;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	@Override
	public String toString() {
		return "Computer [brand=" + brand + ", hd=" + hd + ", ram=" + ram + "]";
	}

}
