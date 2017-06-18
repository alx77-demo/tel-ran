package org.cheskis;

public interface ILinkedList<E> extends Iterable<E> {
	ILinkedListIterator<E> linkedListIterator();

	int size();

	void clear();
}
