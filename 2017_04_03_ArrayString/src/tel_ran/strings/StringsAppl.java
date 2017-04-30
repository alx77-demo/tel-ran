package tel_ran.strings;

public class StringsAppl {

	public static void main(String[] args) {
		String str;
		int a = 100;
		str = "hello";
		str += " My "+100;
		System.out.println(str+" world!");
		System.out.println(""+a+500);
		System.out.println(a+500+"");
		str = new String("hello again");
		String x = "abc";
		String y = "ab"+"c"; //%)
		String x1 = "ab";
		String y1 = x1+"c";
		String z = new String("abc");
		System.out.println("x == y is "+ (x==y));
		System.out.println("x == y1 is "+ (x==y1));
		System.out.println("x == z is "+ (x==z));
		System.out.println("x eq y is "+ (x.equals(y)));
		System.out.println("x eq z is "+ (x.equals(z)));
		printByChar(str);
		str = "a.furmanov@mail.ru";
		String login = str.toUpperCase().substring(0,str.indexOf('@'));
		System.out.println(login);
	}

	private static void printByChar(String str) {
		for (int i = 0, len = str.length(); i < len; i++) {
			System.out.println(str.charAt(i));
			
		}
		
	}

}
