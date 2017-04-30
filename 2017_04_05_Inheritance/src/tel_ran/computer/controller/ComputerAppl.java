package tel_ran.computer.controller;

import tel_ran.computer.data.*;

public class ComputerAppl {
	public static void main(String[] args) {
		Computer comp1 = new Computer("Dell", 1024, 6);
		System.out.println(comp1);
		Object lap1 = new Laptop("Acer", 512, 8, 4, 3);
//		SmartPhone lap2 = (SmartPhone)lap1;
//		lap2.setBrand("asd");
		((Laptop)lap1).setWeight(1000);
		System.out.println(lap1);
		Computer smart1 = new SmartPhone("Acer", 512, 8, 4, 3, 1234234);
		System.out.println(smart1);
	}
}
