import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SortingProcess<T> extends Thread {
	private static final int WORKER_MIN_RATIO = 3;
	T[] array;
	int workerNumber;
	int[] startPoints;
	int segmentLength;

	public T[] getArray() {
		return array;
	}

	public int getWorkerNumber() {
		return workerNumber;
	}

	public SortingProcess(T[] array, int workerNumber) {
		super();
		this.array = array;
		this.workerNumber = workerNumber;
	}

	@Override
	public void run() {
		try {
			sort();
			// System.out.println("Partially sorted:" +Arrays.toString(array));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void sort() throws InterruptedException {
		CopyOnWriteArrayList<SortWorker<T>> workers = new CopyOnWriteArrayList<>();
		ExecutorService pool = Executors.newFixedThreadPool(4);

		if (workerNumber > array.length)
			workerNumber = array.length / WORKER_MIN_RATIO;

		segmentLength = array.length / workerNumber;

		for (int i = 0; i < workerNumber; i++) {
			SortWorker<T> worker = new SortWorker<T>(Arrays.copyOfRange(array, i * segmentLength, (i + 1) * segmentLength),
					workers, pool);
			workers.add(worker);
		}
		workers.add(
				new SortWorker<T>(Arrays.copyOfRange(array, (workerNumber - 1) * segmentLength, array.length), workers, pool));

		for (SortWorker<T> worker : workers) {
			pool.execute(worker);
		}
		pool.shutdown();
		System.out.println("wsize:"+workers.size());
		for (SortWorker<T> worker : workers) {
			if (worker.sorted.get()) {
				array = worker.array;
				break;
			}
		}
		System.out.println(Arrays.toString(array));
		System.out.println("Arr size:" + array.length);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void merge() {
		Object[] resArray = new Object[array.length];
		int resPointer = 0;
		int[] pointers = Arrays.copyOf(startPoints, startPoints.length);
		// merge
		while (true) {
			// choose minimum
			int minIdx = getFirstAvailablePointer(pointers);
			if (minIdx == -1)
				break;// no more sources to merge
			for (int j = minIdx; j < pointers.length - 1; j++) {
				if (pointers[j] < startPoints[j + 1]) {
					if (((Comparable) array[pointers[j]]).compareTo(((Comparable) array[pointers[minIdx]])) < 0) {
						minIdx = j;
					}
				}
			}
			resArray[resPointer++] = array[pointers[minIdx]++];
		}
		this.array = (T[]) resArray;
	}

	private int getFirstAvailablePointer(int[] pointers) {
		for (int i = 0; i < pointers.length - 1; i++) {
			if (pointers[i] < startPoints[i + 1]) {
				return i;
			}
		}
		return -1;
	}
}
