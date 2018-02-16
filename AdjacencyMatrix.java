package dataStructures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class AdjacencyMatrix<Vertex> {
	private ArrayList<Vertex> vertices;
	private boolean[][] edges;

	public AdjacencyMatrix() {
		vertices = new ArrayList<>();
		edges = new boolean[vertices.size()][vertices.size()];
	}

	public void printStatus() {
		System.out.println("vertex: list");
		for (int i = 0; i < vertices.size(); i++) {
			System.out.print("\t" + vertices.get(i) + ": ");
			for (int j = 0; j < vertices.size(); j++) {
				if (edges[i][j])
					System.out.print(vertices.get(j) + " ");
			}
			System.out.println();
		}
	}

	public boolean addVertex(Vertex v) {
		if (v == null || vertices.contains(v))
			return false;

		vertices.add(v);

		boolean[][] newEdges = new boolean[vertices.size()][vertices.size()];

		for (int i = 0; i < vertices.size() - 1; i++)
			System.arraycopy(edges[i], 0, newEdges[i], 0, vertices.size() - 1);

		for (int i = 0; i < vertices.size(); i++)
			newEdges[i][vertices.size() - 1] = newEdges[vertices.size() - 1][i] = false;

		edges = newEdges;
		return true;
	}

	public boolean addEdge(Vertex v, Vertex w) {
		if (v == null || w == null)
			return false;

		int indexOfV = vertices.indexOf(v);
		int indexOfW = vertices.indexOf(w);

		if (indexOfV != -1 && indexOfW != -1)
			if (!edges[indexOfV][indexOfW] && !edges[indexOfW][indexOfV])
				return edges[indexOfV][indexOfW] = edges[indexOfW][indexOfV] = true;

		return false;
	}

	public boolean findVertex(Vertex v) {
		return v != null && vertices.contains(v);
	}

	public boolean findEdge(Vertex v, Vertex w) {
		if (v == null || w == null)
			return false;

		int indexOfV = vertices.indexOf(v);
		int indexOfW = vertices.indexOf(w);

		return indexOfV != -1 && indexOfW != -1 && edges[indexOfV][indexOfW] && edges[indexOfV][indexOfW];
	}

	public boolean removeVertex(Vertex v) {
		if (v == null)
			return false;

		int index = vertices.indexOf(v);

		if (index == -1)
			return false;

		vertices.remove(index);

		for (int i = index; i < vertices.size(); i++)
			for (int j = 0; j <= vertices.size() ; j++) {
				edges[i][j] = edges[i+1][j];
				edges[j][i] = edges[j][i+1];
			}

		return true;
	}

	public boolean removeEdge(Vertex v, Vertex w) {
		if (v == null || w == null)
			return false;

		int indexOfV = vertices.indexOf(v);
		int indexOfW = vertices.indexOf(w);

		if (indexOfV != -1 && indexOfW != -1)
			if (edges[indexOfV][indexOfW] && edges[indexOfW][indexOfV])
				return !(edges[indexOfV][indexOfW] = edges[indexOfW][indexOfV] = false);

		return false;

	}

	/**
	 * method to contains a vertex, starting from a certain vertex, following DFS
	 * @param v the vertex to be found
	 * @param start the vertex to start from
	 * @return sequence of search
	 */
	public ArrayList<Vertex> DFS(Vertex v, Vertex start) {
		if (v == null || start == null || !vertices.contains(start))
			return null;

		ArrayList<Vertex> sequence = new ArrayList<>();
		Stack<Vertex> stack = new Stack<>();
		stack.push(start);

		while (!stack.empty()) {
			start = stack.pop();
			sequence.add(start);

			if (start.equals(v))
				break;

			int index = vertices.indexOf(start);
			for (int i = 0; i < vertices.size(); i++) {
				if (edges[i][index] && edges[index][i])
					if (!stack.contains(vertices.get(i)) && !sequence.contains(vertices.get(i)))
						stack.push(vertices.get(i));
			}
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
		if (v == null || start == null || !vertices.contains(start))
			return null;

		ArrayList<Vertex> sequence = new ArrayList<>();
		ArrayDeque<Vertex> queue = new ArrayDeque<>();
		queue.add(start);

		while (!queue.isEmpty()) {
			start = queue.remove();
			sequence.add(start);

			if (start.equals(v))
				break;

			int index = vertices.indexOf(start);
			for (int i = 0; i < vertices.size(); i++) {
				if (edges[i][index] && edges[index][i])
					if (!queue.contains(vertices.get(i)) && !sequence.contains(vertices.get(i)))
						queue.add(vertices.get(i));
			}
		}

		return sequence;
	}
}