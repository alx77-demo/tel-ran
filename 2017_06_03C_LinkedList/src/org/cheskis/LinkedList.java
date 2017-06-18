package org.cheskis;

import java.util.Iterator;

public class LinkedList<E> implements ILinkedList<E> {
	Entry<E> head = null, tail = null;
	int size = 0;

	public LinkedList() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> it = new LinkedListIterator<E>(this);
		return it;
	}

	@Override
	public ILinkedListIterator<E> linkedListIterator() {
		ILinkedListIterator<E> it = new LinkedListIterator<E>(this);
		return it;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		head = tail = null;
		size = 0;
	}

}
