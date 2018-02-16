package dataStructures;

class TreeNode<T extends Comparable> {
	protected T data;
	protected TreeNode left, right;

	TreeNode(T data) {
		this.data = data;
		left = right = null;
	}
}