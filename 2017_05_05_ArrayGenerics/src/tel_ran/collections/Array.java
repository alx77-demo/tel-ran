package tel_ran.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

import tel_ran.collections.predicates.PredicateEquals;
import tel_ran.collections.predicates.X;

public class Array<T> implements Iterable<T> {
	public final static int CAPACITY = 10;
	private int size;
	private int capacity = CAPACITY;
	private Object[] array;

	public Array() {
		this(CAPACITY);
	}

	public Array(int capacity) {
		this.capacity = capacity;
		array = new Object[capacity];
	}

	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		return (T) array[index];
	}

	public void add(T obj) {
		if (size == capacity) {
			allocate();
		}
		array[size++] = obj;
	}

	private void allocate() {
		capacity *= 2;
		Object[] tmp = new Object[capacity];
		for (int i = 0; i < size; i++) {
			tmp[i] = array[i];
		}
		array = tmp;
	}

	public boolean insert(T obj, int index) {
		// check index
		if (index < 0 || index >= size) {
			return false;
		}
		// check capacity
		if (size == capacity) {
			allocate();
		}
		for (int i = size; i > index; i--) {
			array[i] = array[i - 1];
		}
		array[index] = obj;
		size++;
		return true;
	}

	public T removeLast() {
		if (size <= 0)
			return null;
		@SuppressWarnings("unchecked")
		T ret = (T) array[--size];
		array[size] = null;
		return ret;
	}

	public T remove(int index) {
		// check index
		if (index < 0 || index >= size) {
			return null;
		}
		@SuppressWarnings("unchecked")
		T tmp = (T) array[index];

		for (int i = index; i < size; i++) {
			array[i] = array[i + 1];
		}
		array[--size] = null;

		return tmp;
	}

	public int indexOf(T obj) {
		return indexOf(new PredicateEquals<T>(obj));
	}

	@SuppressWarnings("unchecked")
	public int indexOf(Predicate<T> predicate) {
		for (int i = 0; i < size; i++) {
			if (predicate.test((T) array[i]))
				return i;
		}
		return -1;
	}

	public int lastIndexOf(T obj) {
		return lastIndexOf(new PredicateEquals<T>(obj));
	}

	@SuppressWarnings("unchecked")
	public int lastIndexOf(Predicate<T> predicate) {
		for (int i = size - 1; i >= 0; i--) {
			if (predicate.test((T) array[i]))
				return i;
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public void sort() {
		boolean unsorted = true;
		int unsortedSize = size;
		while (unsorted) {
			unsorted = false;
			unsortedSize--;
			for (int i = 0; i < unsortedSize; ++i) {
				if (((Comparable<T>) array[i]).compareTo((T) array[i + 1]) > 0) {
					unsorted = true;
					swap(i, i + 1);
				}
			}
		}
	}

	public void sort(Comparator<X> comp) {
		boolean unsorted = true;
		int unsortedSize = size;
		while (unsorted) {
			unsorted = false;
			unsortedSize--;
			for (int i = 0; i < unsortedSize; ++i) {
				if (comp.compare((X) array[i], (X) array[i + 1]) > 0) {
					unsorted = true;
					swap(i, i + 1);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void swap(int left, int right) {
		T tmp;
		tmp = (T) array[left];
		array[left] = array[right];
		array[right] = tmp;
	}

	@SuppressWarnings("unchecked")
	public Array<T> filter(Predicate<T> predicate) {
		Array<T> result = new Array<T>();
		for (int i = 0; i < size; i++) {
			if (predicate.test((T) array[i]))
				result.add((T) array[i]);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Array<T> removeIf(Predicate<T> predicate) {
		Array<T> result = new Array<T>();
		for (int i = size - 1; i >= 0; i--) {
			if (predicate.test((T) array[i])) {
				result.add((T) array[i]);
				array[i] = null;
				size--;
			}
		}
		supress();
		return result;
	}

	private void supress() {
		int j = -1;
		for (int i = 0; i < array.length; i++) {
			if (j == -1 && array[i] == null) {
				j = i;
			} else if (j != -1 && array[i] != null) {
				array[j] = array[i];
				array[i] = null;
				j++;
			}
		}
	}

	@Override
	public String toString() {
		return "Array [size=" + size + ", capacity=" + capacity + ", array=" + Arrays.toString(array) + "]";
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator<T>(this);
	}
}
// дз (27.04.2017) Array
// 1) функция sort() без параметров, на основе стандартного
// compareTo/*естественный порядок*/
// 2) функция public Array filter(Predicat<Object> p)
// 3) функция public Array removeIf(Predicat<Object> p)

// дз (30.04.2017) Array
// 1) Array + Iterable:Iterator
// 2) test Iterable:Iterator
// 3) test SUM with Iterable:Iterator

// дз (04.05.2017) Array
// 1) Array + generics
// 2) parametrized stack
