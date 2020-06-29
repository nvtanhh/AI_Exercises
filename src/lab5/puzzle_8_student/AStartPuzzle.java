package lab5.puzzle_8_student;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStartPuzzle implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(Node.HeuristicComparatorByF2);
		List<Node> explored = new ArrayList<Node>();
		frontier.add(model.getInitialState());
		long startDT = System.currentTimeMillis();
		int count = 0;
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			explored.add(currentNode);
			++count;
			if (currentNode.getH1() == 0) {
				System.out.println("**** A* Algorithm ****");
				System.out.println("Loop: " + count);
				System.out.println("Step: " + currentNode.getG());
				System.out.println("Run time: " + (System.currentTimeMillis() - startDT) + " ms");
				return currentNode;
			}
			List<Node> children = model.getSuccessors(currentNode);
			for (Node childNode : children) {
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
					childNode.setG(currentNode.getG() + 1);
					frontier.add(childNode);
				}
			}
		}
		return null;
	}

}
