package tel_ran.collections.tests;

import tel_ran.collections.Array;

public class ArrayTestApp {
	public static void main(String[] args) {
		Array arr = new Array(10);
		for (int i = 0; i < 20; i++) {
			Object obj = new String("Hello" + i);
			arr.add(obj);
		}
		System.out.println(arr);
		arr.removeLast();
		System.out.println(arr);
		arr.remove(15);
		System.out.println(arr);
		Object tmpStr = new String("BOOM");
		arr.insert(tmpStr, 2);
		System.out.println(arr);
		arr.insert(tmpStr, 15);
		System.out.println(arr);
		System.out.println(arr.lastIndexOf(tmpStr));
		System.out.println(arr.indexOf(tmpStr));
	}
//дз (25.04.2017):
	//1) закончить юниттест
	//2) предикат - условие на множество (Predicate) и на него юниттест 
	//3) написать предикаты на такие функции: equals (внутри класса массива для indexOf/indexOfLast), и ряд других предикатов при тестировании 
}
