package uniformed.cost;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {

	@Override
	public void execute(Node tree) {
		// TODO Auto-generated method stub

	}

	@Override
	public Node execute(Node tree, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
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
			for (Node childNode : children) {
				double newCost = currentNode.getPathCost() + currentNode.getCostOfChildren(childNode);
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
					childNode.setPathCost(newCost);
					frontier.add(childNode);
					childNode.setParent(currentNode);
				} else if (frontier.contains(childNode) && (childNode.getPathCost() > newCost)) {
					childNode.setPathCost(newCost);
					childNode.setParent(currentNode);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node tree, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
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
				System.out.println(NodeUtils.printPath(currentNode));
				System.out.println("Path cost: " + currentNode.getPathCost());
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
				} else if (frontier.contains(childNode)) {
					if (childNode.getPathCost() > newCost && isStart) {
						childNode.setPathCost(newCost);
						childNode.setParent(currentNode);
					}
				}
			}
		}
		return null;
	}

}
