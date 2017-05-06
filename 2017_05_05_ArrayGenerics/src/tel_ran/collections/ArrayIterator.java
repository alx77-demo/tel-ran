package tel_ran.collections;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

	Array<T> array;
	int pointer;

	public ArrayIterator(Array<T> array) {
		this.array = array;
		pointer = 0;
	}

	@Override
	public boolean hasNext() {
		return pointer < array.size();
	}

	@Override
	public T next() {
		return array.get(pointer++);
	}

}
