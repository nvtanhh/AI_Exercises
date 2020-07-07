package lab5.puzzle_8_student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DFSPuzzle implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		Stack<Node> frontier = new Stack<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(model.getInitialState());
		long startDT = System.currentTimeMillis();
		int count = 0;
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			explored.add(currentNode);
			++count;
			if (currentNode.getH1() == 0) {
				System.out.println("****DFS****");
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
					childNode.setG(currentNode.getG()+1);
					frontier.push(childNode);
				}
			}
		}
		return null;
	}

}
