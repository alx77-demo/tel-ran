package tel_ran.wrapper.control;

public class WrapperPrinterAppl {
	static String[] typeNames = { "byte", "short", "int", "long", "float", "double", "char" };
	static Object[] maxValues = { Byte.MAX_VALUE, Short.MAX_VALUE, Integer.MAX_VALUE, Long.MAX_VALUE, Float.MAX_VALUE,
			Double.MAX_VALUE, (int)Character.MAX_VALUE };
	static Object[] minValues = { Byte.MIN_VALUE, Short.MIN_VALUE, Integer.MIN_VALUE, Long.MIN_VALUE, Float.MIN_VALUE,
			Double.MIN_VALUE, (int)Character.MIN_VALUE };

	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String type = args[i];
			printResult(type);
		}
	}

	private static void printResult(String type) {
		for (int i = 0; i < typeNames.length; i++) {
			if (type.equals(typeNames[i])) {
				System.out.println(type + " max:" + maxValues[i] + "; min:" + minValues[i]);
				return;
			}
		}
	}

}
