package tel_ran.memory;

public class MemoryService {

	static public int getMaxArrayMemory() {
		int l = 0, r = Integer.MAX_VALUE;
		int center;
		while (true) {
			try {
				center = (r - l) / 2;
				byte[] arr = new byte[l + center];
				l = l + center;
				if ((r - l) <= 1)
					break;
			} catch (Throwable e) {
				center = (r - l) / 2;
				r = l + center;
			}
		}
		return l;
	}
}
