package tel_ran.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

import tel_ran.collections.predicates.PredicateEquals;

public class Array implements Iterable<Object> {
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

	public Object get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		return array[index];
	}

	public void add(Object obj) {
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

	public boolean insert(Object obj, int index) {
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

	public Object removeLast() {
		if (size <= 0)
			return null;
		Object ret = array[--size];
		array[size] = null;
		return ret;
	}

	public Object remove(int index) {
		// check index
		if (index < 0 || index >= size) {
			return null;
		}
		Object tmp = array[index];

		for (int i = index; i < size; i++) {
			array[i] = array[i + 1];
		}
		array[--size] = null;

		return tmp;
	}

	public int indexOf(Object obj) {
		return indexOf(new PredicateEquals(obj));
	}

	public int indexOf(Predicate<Object> predicate) {
		for (int i = 0; i < size; i++) {
			if (predicate.test(array[i]))
				return i;
		}
		return -1;
	}

	public int lastIndexOf(Object obj) {
		return lastIndexOf(new PredicateEquals(obj));
	}

	public int lastIndexOf(Predicate<Object> predicate) {
		for (int i = size - 1; i >= 0; i--) {
			if (predicate.test(array[i]))
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
				if (((Comparable<Object>) array[i]).compareTo((Comparable<Object>) array[i + 1]) > 0) {
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

	private void swap(int left, int right) {
		Object tmp;
		tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}

	public Array filter(Predicate<Object> predicate) {
		Array result = new Array();
		for (int i = size - 1; i >= 0; i--) {
			if (predicate.test(array[i]))
				result.add(array[i]);
		}
		return result;
	}

	public Array removeIf(Predicate<Object> predicate) {
		Array result = new Array();
		for (int i = size - 1; i >= 0; i--) {
			if (predicate.test(array[i])) {
				result.add(array[i]);
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
	public Iterator<Object> iterator() {
		return new ArrayIterator(this);
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
