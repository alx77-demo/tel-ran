
public class TestConcatApp {

	private static final int N_SYMBOLS = 100000;

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String str = "";
		for (int i = 0; i < N_SYMBOLS; i++) {
			str += "a";
		}
		System.out.println(System.currentTimeMillis() - startTime);

		startTime = System.currentTimeMillis();
		StringBuilder strb = new StringBuilder();
		for (int i = 0; i < N_SYMBOLS; i++) {
			strb.append("a");
		}
		str = strb.toString();
		System.out.println(System.currentTimeMillis() - startTime);

		startTime = System.currentTimeMillis();
		StringBuffer strbuf = new StringBuffer();
		for (int i = 0; i < N_SYMBOLS; i++) {
			strbuf.append("a");
		}
		str = strbuf.toString();
		System.out.println(System.currentTimeMillis() - startTime);
	}

}
