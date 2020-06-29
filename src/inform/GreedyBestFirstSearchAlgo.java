package inform;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node tree, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			explored.add(currentNode);
			if (goal.equals(currentNode.getLabel())) {
				System.out.print(NodeUtils.printPath(currentNode));
				System.out.println("Path: " + currentNode.getPathCost());
				return currentNode;
			}
			List<Node> children = currentNode.getChildrenNodes();
			for (Node childNode : children) {
				double newCost = currentNode.getPathCost() + currentNode.getCostOfChildren(childNode);
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
					childNode.setPathCost(newCost);
					frontier.add(childNode);
					childNode.setParent(currentNode);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node tree, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
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
			if (goal.equals(currentNode.getLabel())) {
				System.out.print(NodeUtils.printPath(currentNode));
				System.out.println("Path: " + currentNode.getPathCost());
				return currentNode;
			}
			List<Node> children = currentNode.getChildrenNodes();
			for (Node childNode : children) {
				double newCost = currentNode.getPathCost() + currentNode.getCostOfChildren(childNode);
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
					if (isStart) {
						childNode.setPathCost(newCost);
						childNode.setParent(currentNode);
					}
					frontier.add(childNode);
				}
			}
		}
		return null;
	}

}
