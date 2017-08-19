import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class SortWorker<T> extends Worker<T> {

	public SortWorker(T[] array, CopyOnWriteArrayList<Worker<T>> workers, ExecutorService pool) {
		super(array, workers, pool);
	}

//	AtomicBoolean sorted = new AtomicBoolean(false);

	@Override
	public void run() {
//		if (!sorted.get()) {
			Arrays.sort(array);
//			sorted.set(true);
			Worker<T> worker = new MergeWorker<T>(array, workers, pool);
			workers.add(worker);
			pool.execute(worker);
//		}
	}
}
