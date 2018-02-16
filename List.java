package dataStructures;

public interface List<T> {
	/**
	 * method to get the size of list
	 * @return number of elements in the list
	 */
	int size();

	/**
	 * method to determine if the list is empty
	 * @return whether list is empty or not
	 */
	boolean isEmpty();

	/**
	 * method to contains the index of a specific item
	 * @param item item whose index is to be found
	 * @return index of the item
	 */
	int indexOf(T item);

	/**
	 * method to get an item based on item's equals() method
	 * @param item item to be found
	 * @return exact item object
	 */
	T find(T item);

	/**
	 * method to determine if an item exists or not
	 * @param item item to be found
	 * @return whether the item was found
	 */
	boolean contains(T item);

	/**
	 * method to add an item to the array
	 * @param item item to be added
	 * @return whether the item was added or not
	 */
	boolean add(T item);

	/**
	 * method to add an item to the array at a specific position
	 * shifts the subsequent ones
	 * @param item item to be added
	 * @param index position where the item is to be added
	 * @return whether the item was added or not
	 */
	boolean add(T item, int index);

	/**
	 * method to remove an item from a specific position
	 * @param index position from where the item is to be removed
	 * @return the item that was removed or not
	 */
	T remove(int index);

	/**
	 * method to remove the first occurrence of an item from the array
	 * @param item item to be removed
	 * @return whether the item was removed or not
	 */
	boolean remove(T item);

	/**
	 * method to traverse through all items of the list
	 */
	void traverse();

	/**
	 * method to return an item at a specific index
	 * @param index index whose item is to be returned
	 * @return the item at that index
	 */
	T get(int index);

	/**
	 * method to set an item at a particular index
	 * replaces the existing item
	 * @param item item to be set
	 * @param index index where it is to be kept
	 * @return item that was previously there
	 */
	T set(T item, int index);
}
