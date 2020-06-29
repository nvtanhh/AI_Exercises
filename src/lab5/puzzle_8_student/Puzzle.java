package lab5.puzzle_8_student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Puzzle {
	public static final int MAX_ROW = 3;// 3x3: Dimension of the puzzle map
	public static final int MAX_COL = 3;

	private Node initialState;
	private Node goalState;

	public Puzzle() {
		this.initialState = new Node(MAX_ROW, MAX_COL);
		this.goalState = new Node(MAX_ROW, MAX_COL);
	}

	public void readInput(final String INITIAL_STATE_MAP_PATH, final String GOAL_STATE_MAP_PATH) {
		try {
			// 1 - Import map
			BufferedReader bufferedReader = new BufferedReader(new FileReader(INITIAL_STATE_MAP_PATH));

			String line = null;
			int row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					initialState.state[row][col] = Integer.parseInt(tile[col]);
				}
				row++;
			}

			bufferedReader.close();

			// 2 - Import goal state
			bufferedReader = new BufferedReader(new FileReader(GOAL_STATE_MAP_PATH));

			line = null;
			row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					goalState.state[row][col] = Integer.parseInt(tile[col]);
				}
				row++;
			}

			bufferedReader.close();

			// 3 - Compute heuristic value and get white tile position
			int[] whiteTilePosition = initialState.getLocation(0);
			System.arraycopy(whiteTilePosition, 0, initialState.whiteTilePosition, 0, whiteTilePosition.length);
			initialState.setH1(computeH1(initialState, goalState));
			initialState.setH2(computeH2(initialState, goalState));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int[] getTilePosition(int[][] state, int tile) {
		// Return position (in 2D) of a tile in current state
		int[] output = new int[2];

		/* Enter your code here */

		return output;
	}

	// distance between P1(x1, y1) and P2(x2, y2)
	public int manhattanDistance(int[] current, final int[] target) {
		return Math.abs(target[0] - current[0]) + Math.abs(target[1] - current[1]);
	}

	// Using manhattanDistance above to compute H
	public int computeH2(final Node currentState, final Node goalState) {
		int output = 0;
		for (int i = 0; i < MAX_ROW; i++) {
			for (int j = 0; j < MAX_COL; j++) {
				if (currentState.state[i][j] != 0) {
					int[] target = goalState.getLocation(currentState.state[i][j]);
					int[] current = { i, j };
					output += manhattanDistance(current, target);
				}
			}
		}

		return output;
	}

	//
	public int computeH1(final Node currentState, final Node goalState) {
		int output = 0;
		for (int i = 0; i < MAX_ROW; i++) {
			for (int j = 0; j < MAX_COL; j++) {
				if (currentState.state[i][j] != 0 && currentState.state[i][j] != goalState.state[i][j])
					output++;
			}
		}
		return output;
	}

	public Node moveTile(final Node state, int[] tile, char operator) {
		Node output = new Node(state);
		int row = -1, col = -1;
		switch (operator) {
		case 'u':
			row = tile[0] - 1;
			col = tile[1];
			break;
		case 'd':
			row = tile[0] + 1;
			col = tile[1];
			break;
		case 'l':
			row = tile[0];
			col = tile[1] - 1;
			break;
		case 'r':
			row = tile[0];
			col = tile[1] + 1;
			break;
		default:
			break;
		}

		if (row >= 0 && row < MAX_ROW && col >= 0 && col < MAX_COL) {
			int tmp = state.state[row][col];
			output.state[row][col] = 0;
			output.state[tile[0]][tile[1]] = tmp;
			int[] newWhiteTilePosition = { row, col };
			System.arraycopy(newWhiteTilePosition, 0, output.whiteTilePosition, 0, newWhiteTilePosition.length);
			output.setH1(computeH1(output, goalState));
			output.setH2(computeH2(output, goalState));
			return output;
		}
		return null;
	}

	public ArrayList<Node> getSuccessors(final Node currentState) {
		ArrayList<Node> output = new ArrayList<Node>();
		char[] operators = { 'l', 'r', 'u', 'd' };

		for (char operator : operators) {
			Node tmp = moveTile(currentState, currentState.whiteTilePosition, operator);
			if (tmp != null) {
				output.add(tmp);
			}
		}

		return output;
	}

	public Node getInitialState() {
		return initialState;
	}

	public Node getGoalState() {
		return goalState;
	}
}
