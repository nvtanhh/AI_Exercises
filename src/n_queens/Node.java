package n_queens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		// generateBoard();
		state = new Queen[N];
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < state.length; i++) {
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public void generateBoard() {
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			state[i] = new Queen(random.nextInt(N), i);
		}
	}

	public int getH() {
		int heuristic = 0;
		for (int i = 0; i < state.length; i++) {
			for (int j = i + 1; j < state.length; j++) {
				if (state[i].isConflict(state[j])) {
					heuristic++;
				}
			}
		}
		return heuristic;
	}

	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<Node>();

		for (int i = 0; i < state.length; i++) {
			Node newCandidate = new Node(this.state);
			newCandidate.state[i].move();
			result.add(newCandidate);
		}
		return result;
	}

	public Node selectNextRandomCandidate() {
		Node newCandidate = new Node(this.state);
		newCandidate.state[(int) Math.random() * 8].move();
		return newCandidate;
	}

	public void displayBoard() {
		int[][] board = new int[N][N];
		// set queen position on the board
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = 1;
		}
		// print board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}

	@Override
	public boolean equals(Object obj) {

		if (obj != null && this.getClass() == obj.getClass()) {
			Node that = (Node) obj;
			for (int i = 0; i < state.length; i++) {
				if (state[i] != that.state[i])
					return false;
			}
		} else
			return false;

		return true;
	}
}
