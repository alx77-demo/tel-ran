package org.cheskis;

public class LinkedListIterator<E> implements ILinkedListIterator<E> {
	Entry<E> current;
	LinkedList<E> list;

	public LinkedListIterator(LinkedList<E> list) {
		super();
		this.current = list.head;
		this.list = list;
	}

	@Override
	public boolean hasNext() {
		return current.next != null;
	}

	@Override
	public E next() {
		current = current.next;
		return current.element;
	}

	@Override
	public boolean hasPrev() {
		return current.prev != null;
	}

	@Override
	public E prev() {
		current = current.prev;
		return current.element;
	}

	@Override
	public void add(E e) {
		Entry<E> entry = new Entry<E>();
		if (current != null) {
			entry.next = current.next;
			entry.prev = current;
			if (current.next != null)
				current.next.prev = entry;
			else
				list.tail = entry;
			current.next = entry;
		} else
			list.head = list.tail = entry;
		current = entry;
		list.size++;
	}

	@Override
	public void set(E e) {
		current.element = e;
	}
}
