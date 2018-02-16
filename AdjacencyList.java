package dataStructures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class AdjacencyList<Vertex> {
	private ArrayList<Node> list;

	public AdjacencyList() {
		this.list = new ArrayList<>();
	}

	public class Node {
		private Vertex vertex;
		private LinkedList<Vertex> edges;

		public Node(Vertex vertex) {
			this.vertex = vertex;
			edges = new LinkedList<>();
		}

		@Override
		public int hashCode() {
			return vertex.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj != null)
				if (this.getClass().equals(obj.getClass()))
					return vertex.equals(((Node) obj).vertex);
			return false;
		}

		@Override
		public String toString() {
			String string = vertex.toString() + ": ";
			for (Vertex vertex : edges)
				string += vertex.toString() + " ";
			return string;
		}
	}

	public void printStatus() {
		System.out.println("vertex: list");
		for (Node node : list)
			System.out.println("\t" + node.toString());
	}

	public boolean addVertex(Vertex v) {
		if (v == null || list.contains(new Node(v)))
			return false;

		list.add(new Node(v));
		return true;
	}

	public boolean addEdge(Vertex v, Vertex w) {
		if (v == null || !list.contains(new Node(v)) || w == null || !list.contains(new Node(w)))
			return false;

		Node node1 = list.get(list.indexOf(new Node(v)));
		Node node2 = list.get(list.indexOf(new Node(w)));

		if (!node1.edges.contains(w))
			if (!node2.edges.contains(v))
				if (node1.edges.add(w))
					return node2.edges.add(v);
		return false;
	}

	public boolean findVertex(Vertex v) {
		return v != null && list.contains(new Node(v));
	}

	public boolean findEdge(Vertex v, Vertex w) {
		if (v != null && w != null)
			if (list.contains(new Node(v)) && list.contains(new Node(w)))
				if (list.get(list.indexOf(new Node(v))).edges.contains(w))
					return list.get(list.indexOf(new Node(w))).edges.contains(v);
		return false;
	}

	public boolean removeVertex(Vertex v) {
		if (v != null)
			if (list.contains(new Node(v)))
				if (list.remove(new Node(v))) {
					for (Node node: list)
						node.edges.remove(v);
					return true;
				}
		return false;
	}

	public boolean removeEdge(Vertex v, Vertex w) {
		if (v == null || !list.contains(new Node(v)) || w == null || !list.contains(new Node(w)))
			return false;

		Node node1 = list.get(list.indexOf(new Node(v)));
		Node node2 = list.get(list.indexOf(new Node(w)));

		if (node1.edges.contains(w))
			if (node2.edges.contains(v))
				if (node1.edges.remove(w))
					return node2.edges.remove(v);
		return false;
	}

	/**
	 * method to contains a vertex, starting from a certain vertex, following DFS
	 * @param v the vertex to be found
	 * @param start the vertex to start from
	 * @return sequence of search
	 */
	public ArrayList<Vertex> DFS(Vertex v, Vertex start) {
		if (v == null || start == null || !list.contains(new Node(start)))
			return null;

		ArrayList<Vertex> sequence = new ArrayList<>();
		Stack<Vertex> stack = new Stack<>();
		stack.push(start);
		LinkedList<Vertex> neighbours;

		while (!stack.empty()) {
			start = stack.pop();
			sequence.add(start);

			if (start.equals(v))
				break;

			neighbours = list.get(list.indexOf(new Node(start))).edges;
			for (Vertex w : neighbours)
				if(!sequence.contains(w) && !stack.contains(w))
					stack.push(w);
		}

		return sequence;
	}

	/**
	 * method to contains a vertex, starting from a certain vertex, following BFS
	 * @param v the vertex to be found
	 * @param start the vertex to start from
	 * @return sequence of search
	 */
	public ArrayList<Vertex> BFS(Vertex v, Vertex start) {
		if (v == null || start == null || !list.contains(new Node(start)))
			return null;

		ArrayList<Vertex> sequence = new ArrayList<>();
		ArrayDeque<Vertex> queue = new ArrayDeque<>();
		queue.add(start);
		LinkedList<Vertex> neighbours;

		while (!queue.isEmpty()) {
			start = queue.remove();
			sequence.add(start);

			if (start.equals(v))
				break;

			neighbours = list.get(list.indexOf(new Node(start))).edges;
			for (Vertex w : neighbours)
				if(!sequence.contains(w) && !queue.contains(w))
					queue.add(w);
		}

		return sequence;
	}
}