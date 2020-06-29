package lab5.puzzle_8_student;

import java.util.Comparator;

public class Node {
	public int[][] state;
	public int h1; // The number of tiles out of place
	public int h2; // Sum of distances of tiles from goal positions, using Manhattan distance
	public int g;
	// the position of white tile
	public int[] whiteTilePosition = new int[2];

	public Node() {
	}

	public Node(int row, int col) {
		this.state = new int[row][col];
	}

	@Override
	public boolean equals(Object object) {
		Node that = new Node();
		if (object instanceof Node) {
			that = (Node) object;
		} else
			return false;

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				if (this.state[i][j] != that.state[i][j])
					return false;
			}
		}
		return true;
	}

	public Node(Node node) {
		this.state = new int[node.state.length][node.state[0].length];
		for (int i = 0; i < node.state.length; i++) {
			for (int j = 0; j < node.state[i].length; j++) {
				state[i][j] = node.state[i][j];
			}
		}
		this.h1 = node.h1;
		this.h2 = node.h2;
		System.arraycopy(node.whiteTilePosition, 0, this.whiteTilePosition, 0, this.whiteTilePosition.length);
	}

	public int getF1() {
		return this.g + this.h1;
	}

	public int getH1() {
		return h1;
	}

	public void setH1(int h) {
		this.h1 = h;
	}

	public void setG(int g) {
		this.g = g;
	}

	// Get the location of a tile in the board
	public int[] getLocation(int tile) {
		int[] result = new int[2];
		for (int i = 0; i < this.state.length; i++) {
			for (int j = 0; j < this.state[i].length; j++) {
				if (this.state[i][j] == tile) {
					result[0] = i;
					result[1] = j;
				}
			}
		}
		return result;
	}

	// Compare 2 nodes by heuristic values
	public static Comparator<Node> HeuristicComparatorByH1 = new Comparator<Node>() {
		@Override
		public int compare(Node a, Node b) {
			return a.h1 - b.h1;
		}
	};
	// Compare 2 nodes by heuristic values
	public static Comparator<Node> HeuristicComparatorByH2 = new Comparator<Node>() {
		@Override
		public int compare(Node a, Node b) {
			return a.h2 - b.h2;
		}
	};

	// Compare 2 nodes by F values
	public static Comparator<Node> HeuristicComparatorByF1 = new Comparator<Node>() {
		@Override
		public int compare(Node a, Node b) {
			return a.getF1() - b.getF1();
		}
	};
	// Compare 2 nodes by F values
	public static Comparator<Node> HeuristicComparatorByF2 = new Comparator<Node>() {
		@Override
		public int compare(Node a, Node b) {
			return a.getF2() - b.getF2();
		}
	};

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[i].length; j++) {
				output.append(state[i][j] + " ");
			}
			output.append("\n");
		}
		return output.toString();
	}

	protected int getF2() {
		return this.g + this.h2;
	}

	public int getG() {
		return g;
	}

	public void setH2(int h2) {
		this.h2 = h2;
	}

	public int getH2() {
		return this.h2;
	}


}