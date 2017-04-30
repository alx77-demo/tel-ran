package tel_ran.array.tools;

import java.util.Comparator;

import tel_ran.compare.data.Car;

public class CarCompareVendor implements Comparator<Car> {

	@Override
	public int compare(Car o1, Car o2) {
		return o1.getVendor().compareTo(o2.getVendor());
	}

}
