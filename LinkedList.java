package dataStructures;

public class LinkedList<T> implements List<T> {
	/**
	 * class to implement Data
	 */
	private class Node {
		private T data;
		private Node next, prev;

		private Node(T data) {
			this.data = data;
		}

		private Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		private Node(T data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	/**
	 * head of the linked list
	 */
	private Node head;

	/**
	 * top of the list
	 */
	private int top;

	/**
	 * constructor method to initialize the list as empty
	 */
	public LinkedList() {
		head = null;
		top = 0;
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

		Node temp = head;
		for (int i = 0; temp != null; temp = temp.next, i++)
			if (temp.data.equals(item)) return i;

		return -1;
	}

	@Override
	public T find(T item) {
		if (item == null) return null;

		Node temp = head;
		for (int i = 0; temp != null; temp = temp.next, i++)
			if (temp.data.equals(item)) return temp.data;

		return null;
	}

	@Override
	public boolean contains(T item) {
		return indexOf(item) != -1;
	}

	@Override
	public boolean add(T item) {
		if (item == null)
			return false;

		if (head == null) {
			head = new Node(item);
			top++;
			return true;
		}

		Node temp = head;
		while (temp.next != null)
			temp = temp.next;

		temp.next = new Node(item, null, temp);
		top++;

		return true;
	}

	@Override
	public boolean add(T item, int index) {
		if (item == null || index < 0)
			return false;

		if (index == 0) {
			head = new Node(item, head);
			if (head.next != null)
				head.next.prev = head;
			top++;
			return true;
		}

		Node temp = head;
		while(temp != null && --index > 0)
			temp = temp.next;

		if (temp == null)
			return false;

		temp.next = new Node(item, temp.next, temp);
		if (temp.next.next != null)
			temp.next.next.prev = temp.next;
		top++;
		return true;
	}

	@Override
	public T remove(int index) {
		if (index < 0 || head == null) return null;

		if (index == 0) {
			T data = head.data;
			head = head.next;
			if (head != null) head.prev = null;
			top--;
			return data;
		}

		Node temp = head;
		while(temp != null && --index > 0)
			temp = temp.next;

		if (temp == null) return null;

		T data = temp.data;
		temp.next = temp.next.next;
		if (temp.next != null) temp.next.prev = temp;
		top--;
		return data;
	}

	@Override
	public boolean remove(T item) {
		if (item == null || head == null) return false;

		if (head.data.equals(item)) {
			head = head.next;
			if (head != null) head.prev = null;
			top--;
			return true;
		}

		Node temp = head;
		while (temp != null && !temp.next.data.equals(item))
			temp = temp.next;

		if (temp == null) return false;

		temp.next = temp.next.next;
		if (temp.next != null) temp.next.prev = temp;
		top--;

		return true;
	}

	@Override
	public void traverse() {
		for (Node temp = head; temp != null; temp = temp.next)
			System.out.println(temp.data.toString());
	}

	@Override
	public T get(int index) {
		if (index < 0 || head == null) return null;

		Node temp = head;
		while(temp != null && index-- > 0)
			temp = temp.next;

		if (temp == null) return null;

		return temp.data;
	}

	@Override
	public T set(T item, int index) {
		if (item == null || index < 0) return null;

		if (index == 0) {
			T data = head.data;
			head.data = item;
			return data;
		}

		Node temp = head;
		while (temp != null && --index > 0)
			temp = temp.next;

		if (temp == null) return null;

		if (temp.next == null) {
			temp.next = new Node(item, null, temp);
			top++;
			return null;
		} else {
			T data = temp.next.data;
			temp.next.data = item;
			return data;
		}
	}
}