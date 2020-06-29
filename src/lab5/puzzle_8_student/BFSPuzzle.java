package lab5.puzzle_8_student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSPuzzle implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(model.getInitialState());
		long startDT = System.currentTimeMillis();
		int count = 0;
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			++count;
			explored.add(currentNode);
			if (currentNode.getH1() == 0) {
				System.out.println("****BFS****");
				System.out.println("Loop: " + count);
				System.out.println("Step: " + currentNode.getG());
				System.out.println("Run time: " + (System.currentTimeMillis() - startDT) + " ms");
				return currentNode;
			}
			List<Node> children = model.getSuccessors(currentNode);
			children.sort(Node.HeuristicComparatorByH1);
			Collections.reverse(children);
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
