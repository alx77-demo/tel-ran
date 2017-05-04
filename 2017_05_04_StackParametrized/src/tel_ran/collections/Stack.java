package tel_ran.collections;

public class Stack<T> {
	Object[] array;// T<=>Object; Object[] array; <=> T[] array;
	int size = 0;

	public Stack(int limit) {
		array = new Object[limit];
		// T[] a = new T[10]; ???????
	}

	public boolean push(T obj) {
		if (size == array.length) {
			System.out.println("stack overflow");
			return false;
		}
		array[size++] = obj;
		return true;
	}

	public T pop() {
		if (size == 0) {
			System.out.println("stack is empty");
			return null;
		}
		T result = (T) array[--size];
		array[size] = 0;
		return result;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}
