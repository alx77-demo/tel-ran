package tel_ran.collections;

import java.util.Iterator;

public class ArrayIterator implements Iterator<Object> {

	Array array;
	int pointer;

	public ArrayIterator(Array array) {
		this.array = array;
		pointer = 0;
	}

	@Override
	public boolean hasNext() {
		return pointer < array.size();
	}

	@Override
	public Object next() {
		return array.get(pointer++);
	}

}
