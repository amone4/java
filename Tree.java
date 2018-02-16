package dataStructures;

public abstract class Tree<T extends Comparable> {
	protected TreeNode<T> root;

	protected Tree() {
		root = null;
	}

	/**
	 * method for inOrder traversal
	 */
	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(TreeNode<T> root) {
		if (root != null && root.data != null) {
			inOrder(root.left);
			System.out.println(root.data.toString());
			inOrder(root.right);
		}
	}

	/**
	 * method for preOrder traversal
	 */
	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(TreeNode<T> root) {
		if (root != null && root.data != null) {
			System.out.println(root.data.toString());
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	/**
	 * method for postOrder traversal
	 */
	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(TreeNode<T> root) {
		if (root != null && root.data != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.println(root.data.toString());
		}
	}

	protected T find(TreeNode<T> root, T item) {
		if (root == null || root.data == null)
			return null;

		int comparison = root.data.compareTo(item);
		if (comparison == 0)
			return root.data;
		if (comparison < 0)
			return (T) find(root.right, item);
		return (T) find(root.left, item);
	}

	/**
	 * method to find an item
	 * @param item item to be found
	 * @return reference to the item
	 */
	public T find(T item) {
		return find(root, item);
	}

	/**
	 * method to contains an item
	 * @param item item to be found
	 * @return whether the item was found
	 */
	public boolean contains(T item) {
		return find(root, item) != null;
	}

	/**
	 * method to add an item to tree
	 * @param item item to be added
	 * @return whether it was successfully added
	 */
	public boolean add(T item) {
		if (contains(item))
			return false;

		root = add(root, item);
		return true;
	}

	protected abstract TreeNode<T> add(TreeNode<T> root, T item);

	/**
	 * method to remove an item from tree
	 * @param item item to be removed
	 * @return whether it was successfully removed
	 */
	public boolean remove(T item) {
		if (root == null || !contains(item) || item == null)
			return false;

		root = remove(root, item);
		return true;
	}

	protected abstract TreeNode<T> remove(TreeNode<T> root, T item);
}