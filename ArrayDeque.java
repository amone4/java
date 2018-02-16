package dataStructures;

public class ArrayDeque<T> {
	/**
	 * array of objects
	 */
	private Object[] arr;
	/**
	 * points to the toNext index where the node is to be inserted
	 */
	private int front, rear;
	/**
	 * the number of elements that the array can accomodate
	 */
	private int capacity;

	/**
	 * constructor method to initialize the default capacity as 10
	 */
	public ArrayDeque() {
		this(10);
	}

	/**
	 * constructor method to initialize capacity and the array
	 * @param capacity the capacity as passed in by user
	 */
	public ArrayDeque(int capacity) {
		this.capacity = capacity;
		arr = new Object[capacity];
		front = rear = -1;
	}

	/**
	 * method to double the existing capacity of array
	 */
	private void increaseCapacity() {
		Object[] newArray = new Object[2*capacity];
		for (int i = 0; i < capacity; i++)
			newArray[i] = arr[(front+i)%capacity];
		front = 0;
		rear = capacity - 1;
		capacity *= 2;
		arr = newArray;
	}

	public int size() {
		if (front == -1) return 0;
		else if (front <= rear) return rear + 1 - front;
		else return capacity + rear + 1 - front;
	}

	public boolean isEmpty() {
		return front == -1;
	}

	public T find(T item) {
		if (item == null || front == -1) return null;

		for (int i=front, size = size(); i<size; i = (++i)%capacity)
			if (arr[i].equals((T) item)) return (T) arr[i];

		return null;
	}

	public boolean contains(T item) {
		return find(item) != null;
	}

	public boolean addFront(T item) {
		if (item == null) return false;

		if (size() == capacity) increaseCapacity();

		if (front == -1) arr[(front = rear = 0)] = item;
		else arr[(front = (front == 0 ? capacity : front) - 1)] = item;

		return true;
	}

	public boolean addRear(T item) {
		if (item == null) return false;

		if (size() == capacity) increaseCapacity();

		if (front == -1) arr[(front = rear = 0)] = item;
		else arr[(rear = (rear+1)%capacity)] = item;

		return true;
	}

	public T removeFront() {
		if (front == -1) return null;
		T data = (T) arr[front];
		if (front == rear) front = rear = -1;
		else front = (front + 1) % capacity;
		return data;
	}

	public T removeRear() {
		if (front == -1) return null;
		T data = (T) arr[rear];
		if (front == rear) front = rear = -1;
		else rear = (rear == 0 ? capacity : rear) - 1;
		return data;
	}

	public void traverse() {
		for(int i=0, size = size(); i<size; i++)
			System.out.println((arr[i]).toString());
	}

	public T getFront() {
		if (front == -1) return null;
		return (T) arr[front];
	}

	public T getRear() {
		if (front == -1) return null;
		return (T) arr[rear];
	}
}