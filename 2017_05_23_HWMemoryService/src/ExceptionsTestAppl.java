
public class ExceptionsTestAppl {

	public static void main(String[] args) {
		// java.lang.NumberFormatException
		System.out.println(isInteger("abc"));
		System.out.println(isInteger("123"));
		
	}

	public static boolean isIntegerBad(String str) {
		int length = str.length();
		for (int i = 0; i < length; i++)
			if (!Character.isDigit(str.charAt(i)))
				return true;
		return false;
	}

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
//			e.printStackTrace();
			
			System.out.println(e.getMessage());
			return false;
		}
		finally{
			System.out.println("bye");
		}
	}
}
