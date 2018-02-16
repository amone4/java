package dataStructures;

public class AVLTree<T extends Comparable> extends Tree<T> {
	private class Node extends TreeNode<T> {
		private int height;

		private Node(T data) {
			super(data);
			height = 1;
		}
	}

	public AVLTree() {
		super();
	}

	/**
	 * method to get the height of a node
	 * @param root node whose height is to be returned
	 * @return height of the node, and 0 if root is null
	 */
	private int getHeight(Node root) {
		if (root == null) return 0;
		return root.height;
	}

	/**
	 * method to reset the height of a node
	 * @param root node whose height is to be set
	 */
	private void setHeight(Node root) {
		int left = getHeight((Node) root.left);
		int right = getHeight((Node) root.right);
		root.height = (left > right ? left : right) + 1;
	}

	/**
	 * method to perform left rotation on a node
	 * @param root node on which left rotation is to be performed
	 * @return new root after rotation
	 */
	private Node leftRotation(Node root) {
		Node newRoot = (Node) root.right;
		root.right = newRoot.left;
		newRoot.left = root;

		setHeight(root);
		setHeight(newRoot);

		return newRoot;
	}

	/**
	 * method to perform right rotation on the node
	 * @param root node on which right rotation is to be performed
	 * @return new root after rotation
	 */
	private Node rightRotation(Node root) {
		Node newRoot = (Node) root.left;
		root.left = newRoot.right;
		newRoot.right = root;

		setHeight(root);
		setHeight(newRoot);

		return newRoot;
	}

	/**
	 * method to balance the tree
	 * @param root root of tree to be balanced
	 * @return new root after balancing
	 */
	private Node balanceTree(Node root) {
		int left = getHeight((Node) root.left);
		int right = getHeight((Node) root.right);

		if (left - right > 1) {
			if (getHeight((Node) root.left.left) < getHeight((Node) root.left.right))
				root.left = leftRotation((Node) root.left);
			root = rightRotation(root);
		} else if (left - right < -1) {
			if (getHeight((Node) root.right.right) < getHeight((Node) root.right.left))
				root.right = rightRotation((Node) root.right);
			root = leftRotation(root);
		}

		return root;
	}

	@Override
	protected TreeNode<T> add(TreeNode<T> root, T item) {
		if (root == null) return new Node(item);

		int comparison = root.data.compareTo(item);
		if (comparison < 0) root.right = add(root.right, item);
		else if (comparison > 0) root.left = add(root.left, item);

		setHeight((Node) root);

		return balanceTree((Node) root);
	}

	@Override
	protected TreeNode<T> remove(TreeNode<T> root, T item) {
		int comparison = item.compareTo((Node) root.data);

		if (comparison == 0) {
			if (root.left != null && root.right != null) {
				Node predecessor = (Node) root.left;
				while(predecessor.right != null)
					predecessor = (Node) predecessor.right;

				root.data = predecessor.data;
				predecessor.data = item;

				root.left = remove(root.left, item);
			}

			else if (root.left != null) root = root.left;
			else if (root.right != null) root = root.right;
			else root = null;


		} else if (comparison < 0) root.right = remove(root.right, item);
		else root.left = remove(root.left, item);

		setHeight((Node) root);

		return balanceTree((Node) root);
	}
}