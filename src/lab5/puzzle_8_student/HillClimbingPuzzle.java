package lab5.puzzle_8_student;

import java.util.Collections;
import java.util.List;

public class HillClimbingPuzzle implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		long startDT = System.currentTimeMillis();
		int count = 0;
		while (true) {
			Node currentNode = model.getInitialState();
			++count;
			if (currentNode.getH1() == 0) {
				System.out.println("****HILL CLIMBING****");
				System.out.println("Loop: " + count);
				System.out.println("Step: " + currentNode.getG());
				System.out.println("Run time: " + (System.currentTimeMillis() - startDT) + " ms");
				return currentNode;
			}
			List<Node> children = model.getSuccessors(currentNode);
			children.sort(Node.HeuristicComparatorByH1);
			Collections.reverse(children);
			Node childNode = children.get(0);
			if (childNode.getH1() <= currentNode.getH1()) {
				childNode.setG(currentNode.getG() + 1);
				currentNode = childNode;
			} else
				break;
		}
		System.out.println("****HILL CLIMBING****");
		System.out.println("Loop: " + count);
		System.out.println("Local maxima");
		return null;
	}

}
