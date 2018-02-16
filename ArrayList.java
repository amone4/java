package dataStructures;

public class ArrayList<T> implements List<T> {
	/**
	 * array of objects
	 */
	private Object[] arr;
	/**
	 * points to the toNext index where the node is to be inserted
	 */
	private int top;
	/**
	 * the number of elements that the array can accomodate
	 */
	private int capacity;

	/**
	 * constructor method to initialize the default capacity as 10
	 */
	public ArrayList() {
		this(10);
	}

	/**
	 * constructor method to initialize capacity and the array
	 * @param capacity the capacity as passed in by user
	 */
	public ArrayList(int capacity) {
		this.capacity = capacity;
		arr = new Object[capacity];
		top = 0;
	}

	/**
	 * method to double the existing capacity of array
	 */
	private void increaseCapacity() {
		capacity *= 2;

		Object[] newArray = new Object[capacity];

		System.arraycopy(arr, 0, newArray, 0, top);

		arr = newArray;
	}

	@Override
	public int size() {
		return top;
	}

	@Override
	public boolean isEmpty() {
		return top == 0;
	}

	@Override
	public int indexOf(T item) {
		if (item == null) return -1;

		for (int i=0; i<top; i++)
			if (arr[i].equals((T) item)) return i;

		return -1;
	}

	@Override
	public T find(T item) {
		if (item == null) return null;

		for (int i=0; i<top; i++)
			if (arr[i].equals((T) item)) return (T) arr[i];

		return null;
	}

	@Override
	public boolean contains(T item) {
		return indexOf(item) != -1;
	}

	@Override
	public boolean add(T item) {
		if (item == null) return false;

		if (top == capacity) increaseCapacity();

		arr[top++] = item;

		return true;
	}

	@Override
	public boolean add(T item, int index) {
		if (item == null || index < 0 || index > top) return false;

		System.arraycopy(arr, index, arr, index + 1, top - index);

		arr[index] = item;

		top++;

		return true;
	}

	@Override
	public T remove(int index) {
		if (top == 0 || index < 0 || index >= top) return null;

		T temp = (T) arr[index];
		while(index < top-1) {
			arr[index] = arr[index + 1];
			index++;
		}

		top--;
		return temp;
	}

	@Override
	public boolean remove(T item) {
		return remove(indexOf(item)) != null;
	}

	@Override
	public void traverse() {
		for(int i=0; i<top; i++)
			System.out.println((arr[i]).toString());
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= top) return null;
		else return (T) arr[index];
	}

	@Override
	public T set(T item, int index) {
		if (top == 0 || index < 0 || index >= top) return null;

		T temp = (T) arr[index];
		arr[index] = item;

		return temp;
	}
}