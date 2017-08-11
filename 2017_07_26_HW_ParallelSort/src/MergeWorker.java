import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class MergeWorker<T> extends Worker<T> {

	public MergeWorker(T[] array, CopyOnWriteArrayList<Worker<T>> workers, ExecutorService pool) {
		super(array, workers, pool);
	}

	protected MergeWorker<T> neighbor = null;
	protected AtomicBoolean inMerge = new AtomicBoolean(false);

	@Override
	public void run() {
		inMerge.set(true);
		for (Worker<T> worker : workers) {
			if (worker.inMerge.compareAndSet(false, true)) {
				neighbor = worker;
				break;
			}
		}
		if (neighbor != null) {
			mergeArrays(neighbor);
			workers.add(this);
			pool.execute(this);
		} else// no neighbors more
		{

		}
		inMerge.set(false);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void mergeArrays(Worker<T> neighbour) {
		Object[] resArray = new Object[array.length + neighbour.array.length];
		// merge
		int resPtr = 0, inPtr = 0, outPtr = 0;

		while (inPtr < array.length || outPtr < neighbour.array.length) {
			if (inPtr < array.length && outPtr < neighbour.array.length) {
				resArray[resPtr++] = (((Comparable) array[inPtr]).compareTo((Comparable) neighbour.array[outPtr]) == 1)
						? array[inPtr++] : neighbour.array[outPtr];
			}
			if (inPtr < array.length && outPtr == neighbour.array.length) {
				resArray[resPtr++] = array[inPtr++];
			}
			if (inPtr == array.length && outPtr < neighbour.array.length) {
				resArray[resPtr++] = neighbour.array[outPtr];
			}
		}
		this.array = (T[]) resArray;
	}

}
