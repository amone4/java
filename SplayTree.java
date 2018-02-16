package dataStructures;

public class SplayTree<T extends Comparable<T>> extends Tree<T> {
	private class Node extends TreeNode<T> {
		private Node parent;

		private Node(T data) {
			super(data);
			parent = null;
		}
	}

	/**
	 * method to determine if a given node is a left child
	 * @param root node
	 * @return if root is left child
	 */
	private boolean isLeftChild(Node root) {
		return root != null && root.parent != null && root.parent.left == root;
	}

	/**
	 * method to determine if a given node is a right child
	 * @param root node
	 * @return if root is right child
	 */
	private boolean isRightChild(Node root) {
		return root != null && root.parent != null && root.parent.right == root;
	}

	/**
	 * method to perform left rotation on a node
	 * @param root node on which left rotation is to be performed
	 */
	private void leftRotation(Node root) {
		Node temp = (Node) root.right;
		root.right = temp.left;
		temp.left = root;

		if (isLeftChild(root)) root.parent.left = temp;
		else if (isRightChild(root)) root.parent.right = temp;
		else this.root = temp;

		temp.parent = root.parent;
		root.parent = temp;
		if (root.right != null) ((Node) root.right).parent = root;
	}

	/**
	 * method to perform right rotation on the node
	 * @param root node on which right rotation is to be performed
	 */
	private void rightRotation(Node root) {
		Node temp = (Node) root.left;
		root.left = temp.right;
		temp.right = root;

		if (isLeftChild(root)) root.parent.left = temp;
		else if (isRightChild(root)) root.parent.right = temp;
		else this.root = temp;

		temp.parent = root.parent;
		root.parent = temp;
		if (root.left != null) ((Node) root.left).parent = root;
	}

	/**
	 * method to perform splay operation
	 * @param root node to be splayed
	 */
	private void splay(Node root) {
		while (root != this.root)
			if (root.parent.parent != null)
				if (isLeftChild(root.parent) && isLeftChild(root)) {
					rightRotation(root.parent.parent);
					rightRotation(root.parent);
				} else if (isRightChild(root.parent) && isRightChild(root)) {
					leftRotation(root.parent.parent);
					leftRotation(root.parent);
				} else if (isLeftChild(root.parent) && isRightChild(root)) {
					leftRotation(root.parent);
					rightRotation(root.parent);
				} else {
					rightRotation(root.parent);
					leftRotation(root.parent);
				}
			else
				if (isLeftChild(root)) rightRotation(root.parent);
				else leftRotation(root.parent);
	}

	@Override
	protected T find(TreeNode<T> root, T item) {
		if (root == null || root.data == null)
			return null;

		int comparison = root.data.compareTo(item);
		if (comparison == 0) {
			splay((Node) root);
			return root.data;
		}
		if (comparison < 0) return (T) find(root.right, item);
		return (T) find(root.left, item);
	}

	@Override
	protected TreeNode<T> add(TreeNode<T> root, T item) {
		return null;
	}

	@Override
	protected TreeNode<T> remove(TreeNode<T> root, T item) {
		return null;
	}
}
