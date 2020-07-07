package n_queens;

import java.util.List;

public class HillClimbing {

	public Node execute(Node initialState) {

		Node best = new Node(initialState);
		Node current = null;
		int currentH = initialState.getH();

		while (true) {
			current = new Node(best);
			List<Node> candidates = current.generateAllCandidates();

			for (int i = 0; i < candidates.size(); i++) {
				int candidateH = candidates.get(i).getH();

				if (candidateH < currentH) {
					current = new Node(candidates.get(i));
					currentH = candidateH;

				}
			}
			if (currentH == best.getH()) {
				return best;
			}
			best = new Node(current);
		}
	}

	
	
	
	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		int stepClimbed = 0;
		int stepClimbedAfterRandomRestart = 0;
		int randomRestarts = 0;

		Node best = new Node(initialState);
		Node current = null;
		int currentH = initialState.getH();
		while (true) {
			current = new Node(best);
			List<Node> candidates = current.generateAllCandidates();

			for (int i = 0; i < candidates.size(); i++) {
				int candidateH = candidates.get(i).getH();

				if (candidateH < currentH) {
					current = new Node(candidates.get(i));
					currentH = candidateH;
					++stepClimbed;
					++stepClimbedAfterRandomRestart;

				}
			}
			if (currentH == best.getH()) {
				if (currentH != 0) { // restart algorithm
					best = new Node();
					best.generateBoard();
					current = null;
					currentH = best.getH();

					stepClimbedAfterRandomRestart = 0;
					++randomRestarts;
					continue;
				} else {
					System.out.println("step climbed: " + stepClimbed);
					System.out.println("step climbed after restart: " + stepClimbedAfterRandomRestart);
					System.out.println("restart times: " + randomRestarts);
					return best;
				}
			}
			best = new Node(current);
		}
	}

}
