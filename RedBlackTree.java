package dataStructures;

public class RedBlackTree<T extends Comparable> extends Tree<T> {
	private enum Colour {
		black, red, doubleBlack
	}

	private class Node extends TreeNode<T> {
		private Colour colour;
		private Node parent;

		@Override
		public String toString() {
			return (data == null ? "null" : data.toString()) + "(" + colour + ")";
		}

		private Node(T data) {
			super(data);
			if (data != null) {
				left = new Node(null);
				right = new Node(null);
				colour = Colour.red;
			} else colour = Colour.black;
			parent = null;
		}
	}

	public RedBlackTree() {
		super();
		root = new Node(null);
	}

	/**
	 * method to get the colour of a node
	 * @param root node whose colour is to be returned
	 * @return colour of the node, and 0 if root is null
	 */
	private Colour getColour(Node root) {
		return (root == null) ? Colour.black : root.colour;
	}

	/**
	 * method to determine if a given node is a left child
	 * @param root node
	 * @return if root is left child
	 */
	private boolean isLeftChild(Node root) {
		return root.parent != null && root.parent.left == root;
	}

	/**
	 * method to determine if a given node is a right child
	 * @param root node
	 * @return if root is right child
	 */
	private boolean isRightChild(Node root) {
		return root.parent != null && root.parent.right == root;
	}

	/**
	 * method to perform left rotation on a node
	 * @param root node on which left rotation is to be performed
	 */
	private void leftRotation(Node root) {
		Node newRoot = (Node) root.right;
		root.right = newRoot.left;
		newRoot.left = root;

		if (root.parent == null) this.root = newRoot;
		else if (isLeftChild(root)) root.parent.left = newRoot;
		else root.parent.right = newRoot;

		newRoot.parent = root.parent;
		root.parent = newRoot;
		if (root.right != null) ((Node) root.right).parent = root;
	}

	/**
	 * method to perform right rotation on the node
	 * @param root node on which right rotation is to be performed
	 */
	private void rightRotation(Node root) {
		Node newRoot = (Node) root.left;
		root.left = newRoot.right;
		newRoot.right = root;

		if (root.parent == null) this.root = newRoot;
		else if (isLeftChild(root)) root.parent.left = newRoot;
		else root.parent.right = newRoot;

		newRoot.parent = root.parent;
		root.parent = newRoot;
		if (root.left != null) ((Node) root.left).parent = root;
	}

	/**
	 * method to find the uncle of a given node
	 * @param root node whose uncle is to be found out
	 * @return reference to the uncle
	 */
	private Node uncle(Node root) {
		if (isLeftChild(root.parent)) return (Node) root.parent.parent.right;
		else return (Node) root.parent.parent.left;
	}

	/**
	 * method to find the sibling of a node
	 * @param root node whose sibling is to be found
	 * @return reference to the sibling
	 */
	private Node sibling(Node root) {
		if (isLeftChild(root))
			return (Node) root.parent.right;
		else
			return (Node) root.parent.left;
	}

	/**
	 * method to fix the tree after insertion
	 * @param root root of tree to be fixed
	 */
	private void fixPropertyAfterInsert(Node root) {
		if (root.parent == null) root.colour = Colour.black;

		else if (root.parent.colour == Colour.red) {

			Node uncle = uncle(root);
			if (getColour(uncle) == Colour.red) {
				root.parent.colour = uncle.colour = Colour.black;
				root.parent.parent.colour = Colour.red;
				fixPropertyAfterInsert(root.parent.parent);

			} else {
				if (isLeftChild(root) && isRightChild(root.parent)) {
					rightRotation(root.parent);
					fixPropertyAfterInsert((Node) root.right);
				} else if (isRightChild(root) && isLeftChild(root.parent)) {
					leftRotation(root.parent);
					fixPropertyAfterInsert((Node) root.left);

				} else {
					if (isLeftChild(root.parent)) {
						rightRotation(root.parent.parent);
						((Node) root.parent.right).colour = Colour.red;
					}
					else {
						leftRotation(root.parent.parent);
						((Node) root.parent.left).colour = Colour.red;
					}
					root.parent.colour = Colour.black;
				}
			}
		}
	}

	/**
	 * method to fix the tree after deletion
	 * @param replacement root of tree to be fixed
	 */
	private void fixPropertyAfterDelete(Node replacement) {
		if (this.root != replacement) {
			Node sibling = sibling((Node) root);

			if (sibling.colour == Colour.black && (((Node) sibling.left).colour == Colour.red || ((Node) sibling.right).colour == Colour.red)) {
				Node redChildOfSibling = (Node) (((Node) sibling.right).colour == Colour.red ? sibling.right : sibling.left);

				if (isLeftChild(sibling) && isLeftChild(redChildOfSibling)) {
					((Node) sibling.right).colour = Colour.black;
					rightRotation(replacement.parent);

				} else if (isRightChild(sibling) && isRightChild(redChildOfSibling)) {
					((Node) sibling.left).colour = Colour.black;
					leftRotation(replacement.parent);

				} else {
					sibling.colour = Colour.red;
					redChildOfSibling.colour = Colour.black;

					if (isLeftChild(sibling) && isRightChild(redChildOfSibling)) {
						rightRotation(sibling);
						leftRotation(replacement.parent);
					} else {
						leftRotation(sibling);
						rightRotation(replacement.parent);
					}
				}

			} else if (sibling.colour == Colour.black) {
				sibling.colour = Colour.red;
				replacement.parent.colour = (replacement.parent.colour == Colour.black ? Colour.doubleBlack : Colour.red);
				fixPropertyAfterDelete(replacement.parent);

			} else {
				sibling.colour = Colour.black;
				replacement.parent.colour = Colour.red;
				if (isLeftChild(sibling)) rightRotation(replacement.parent);
				else leftRotation(replacement.parent);
				fixPropertyAfterDelete(replacement);
			}
		}

		replacement.colour = Colour.black;
	}

	@Override
	public boolean add(T item) {
		if (contains(item))
			return false;

		Node node = new Node(item);
		root = add(root, node, null);
		fixPropertyAfterInsert(node);
		return true;
	}

	private TreeNode<T> add(TreeNode<T> root, Node item, Node parent) {
		if (root.data == null) {
			root = item;
			item.parent = parent;

		} else {
			int comparison = root.data.compareTo(item.data);
			if (comparison < 0) root.right = add(root.right, item, (Node) root);
			else if (comparison > 0) root.left = add(root.left, item, (Node) root);
		}

		return root;
	}

	@Override
	protected TreeNode<T> add(TreeNode<T> root, T item) {
		return null;
	}

	@Override
	protected TreeNode<T> remove(TreeNode<T> root, T item) {
		int comparison = item.compareTo(root.data);

		if (comparison == 0) {
			if (root.right.data != null && root.left.data != null) {
				Node predecessor = (Node) root.left;
				while (predecessor.right.data != null)
					predecessor = (Node) predecessor.right;

				root.data = predecessor.data;
				predecessor.data = item;
				root.left = remove(root.left, item);

			} else {
				Node replacement = (Node) (root.right.data != null ? root.right : root.left);
				if (((Node) root).colour == Colour.red || replacement.colour == Colour.red) {
					replacement.parent = ((Node) root).parent;
					replacement.colour = Colour.black;
					root = replacement;

				} else {
					if (isLeftChild((Node) root)) ((Node) root).parent.left = replacement;
					else if (isRightChild((Node) root)) ((Node) root).parent.right = replacement;
					else this.root = replacement;

					replacement.parent = ((Node) root).parent;
					replacement.colour = Colour.doubleBlack;
					root = replacement;

					fixPropertyAfterDelete(replacement);
				}
			}

		} else if (comparison < 0) root.left = remove(root.left, item);
		else root.right = remove(root.right, item);

		return root;
	}
// testing
	/*
7(black)
2(red)
1(black)
null(black)
null(black)
5(black)
4(red)
null(black)
null(black)
null(black)
11(red)
8(black)
null(black)
null(black)
14(black)
null(black)
15(red)
null(black)
null(black)
	*/
	/**
	 * method for preOrder traversal
	 */
	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(TreeNode<T> root) {
		if (root != null) {
			System.out.println(root);
			preOrder(root.left);
			preOrder(root.right);
		}
	}
}