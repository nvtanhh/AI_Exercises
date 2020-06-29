package uniformed.cost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {
	Queue<Node> frontier = new LinkedList<Node>();

	@Override
	public void execute(Node tree) {
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			System.out.print(currentNode.getLabel() + " ");
			explored.add(currentNode);
			List<Node> children = currentNode.getChildrenNodes();
			Collections.sort(children);
			for (Node childNode : children) {
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
					frontier.add(childNode);
				}
			}
		}
	}

	@Override
	public Node execute(Node tree, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			explored.add(currentNode);
			if (goal.equals(currentNode.getLabel())) {
				System.out.print(NodeUtils.printPath(currentNode));
				return currentNode;
			}
			List<Node> children = currentNode.getChildrenNodes();
			Collections.sort(children);
			for (Node childNode : children) {
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
					frontier.add(childNode);
					childNode.setParent(currentNode);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node tree, String start, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		boolean isStart = false;
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			explored.add(currentNode);
			if (currentNode.getLabel().equals(start)) {
				isStart = true;
				frontier.clear();
				explored.clear();
			}
			if (isStart && goal.equals(currentNode.getLabel())) {
				System.out.print(NodeUtils.printPath(currentNode));
				return currentNode;
			}
			List<Node> children = currentNode.getChildrenNodes();
			Collections.sort(children);
			for (Node childNode : children) {
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
					frontier.add(childNode);
					if (isStart) {
						childNode.setParent(currentNode);
					}
				}
			}
		}
		return null;
	}

}
