import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

public abstract class Worker<T> implements Runnable {

	protected T[] array;
	protected CopyOnWriteArrayList<Worker<T>> workers;
	protected ExecutorService pool;

	public Worker(T[] array, CopyOnWriteArrayList<Worker<T>> workers, ExecutorService pool) {
		super();
		this.array = array;
		this.workers = workers;
		this.pool = pool;
	}
}