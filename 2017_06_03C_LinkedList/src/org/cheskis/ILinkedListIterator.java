package org.cheskis;

import java.util.Iterator;

public interface ILinkedListIterator<E> extends Iterator<E> {
	boolean hasPrev();

	E prev();

	void add(E e);

	void set(E e);
}
