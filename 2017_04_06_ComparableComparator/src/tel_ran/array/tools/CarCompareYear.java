package tel_ran.array.tools;

import java.util.Comparator;

import tel_ran.compare.data.Car;

public class CarCompareYear implements Comparator<Car> {

	@Override
	public int compare(Car o1, Car o2) {
		return Integer.compare(o1.getYear(), o2.getYear());
	}

}
