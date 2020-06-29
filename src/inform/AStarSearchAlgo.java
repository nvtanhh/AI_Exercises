package inform;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.text.Utilities;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node tree, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByGn());
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			System.out.print(currentNode.getLabel() + ", ");
			explored.add(currentNode);
			if (goal.equals(currentNode.getLabel())) {
				System.out.println();
				System.out.println(NodeUtils.printPath(currentNode));
				System.out.println("Path: " + currentNode.getPathCost());
				return currentNode;
			}
			List<Node> children = currentNode.getChildrenNodes();
			for (Node childNode : children) {
				double newCost = currentNode.getG() + currentNode.getCostOfChildren(childNode);
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
					childNode.setPathCost(newCost);
					frontier.add(childNode);
					childNode.setParent(currentNode);
				} else if (frontier.contains(childNode)) {
					double newF = newCost + currentNode.getH();
					if ((childNode.getF() > newF)) {
						childNode.setPathCost(newCost);
						childNode.setParent(currentNode);
					}
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node tree, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByGn());
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		boolean isStart = false;
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			System.out.print(currentNode.getLabel() + ", ");
			explored.add(currentNode);

			if (currentNode.getLabel().equals(start)) {
				isStart = true;
				frontier.clear();
				explored.clear();
			}
			if (currentNode.getLabel().equals(goal)) {
				System.out.println("Your path: " + NodeUtils.printPath(currentNode));
				System.out.println("Cost: " + currentNode.getPathCost());
				return currentNode;
			}
			List<Node> children = currentNode.getChildrenNodes();
			for (Node childNode : children) {
				double newCost = currentNode.getG() + currentNode.getCostOfChildren(childNode);
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
					if (isStart) {
						childNode.setPathCost(newCost);
						childNode.setParent(currentNode);
					}
					frontier.add(childNode);
				} else if (frontier.contains(childNode)) {
					double newF = newCost + currentNode.getH();
					if ((childNode.getF() > newF && isStart)) {
						childNode.setPathCost(newCost);
						childNode.setParent(currentNode);
					}
				}
			}
		}
		return null;
	}

}
