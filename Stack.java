package dataStructures;

public class Stack<T> {
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
	public Stack() {
		this(10);
	}

	/**
	 * constructor method to initialize capacity and the array
	 * @param capacity the capacity as passed in by user
	 */
	public Stack(int capacity) {
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

	/**
	 * method to get the size of list
	 * @return number of elements in the list
	 */
	public int size() {
		return top;
	}

	/**
	 * method to determine if the list is empty
	 * @return whether list is empty or not
	 */
	public boolean isEmpty() {
		return top == 0;
	}

	/**
	 * method to get an item based on item's equals() method
	 * @param item item to be found
	 * @return exact item object
	 */
	public T find(T item) {
		if (item == null) return null;

		for (int i=0; i<top; i++)
			if (arr[i].equals((T) item)) return (T) arr[i];

		return null;
	}

	/**
	 * method to determine if an item exists or not
	 * @param item item to be found
	 * @return whether the item was found
	 */
	public boolean contains(T item) {
		return find(item) != null;
	}

	/**
	 * method to push an item on top of the stack
	 * @param item item to be pushed
	 * @return if the item was successfully pushed
	 */
	public boolean push(T item) {
		if (item == null) return false;

		if (top == capacity) increaseCapacity();

		arr[top++] = item;

		return true;
	}

	/**
	 * method to pop the item from top of the stack
	 * @return item removed
	 */
	public T pop() {
		if (top == 0) return null;

		return (T) arr[--top];
	}

	/**
	 * method to traverse through all items of the list
	 */
	public void traverse() {
		for(int i=0; i<top; i++)
			System.out.println((arr[i]).toString());
	}

	/**
	 * method to seek the item on top of the stack
	 * @return reference of topmost item
	 */
	public T seek() {
		if (top == 0) return null;

		return (T) arr[top-1];
	}
}