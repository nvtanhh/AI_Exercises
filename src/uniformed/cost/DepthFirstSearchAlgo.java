package uniformed.cost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo {
	Stack<Node> fontier = new Stack<Node>();

	@Override
	public void execute(Node tree) {
		Stack<Node> frontier = new Stack<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			System.out.print(currentNode.getLabel() + " ");
			explored.add(currentNode);
			List<Node> childrens = currentNode.getChildrenNodes();
			Collections.sort(childrens);
			for (Node childNode : childrens) {
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
					frontier.add(childNode);
				}
			}
		}
	}

	@Override
	public Node execute(Node tree, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			explored.add(currentNode);
			if (currentNode.getLabel().equals(goal)) {
				System.out.print(NodeUtils.printPath(currentNode));
				return currentNode;
			}
			List<Node> childrens = currentNode.getChildrenNodes();
			Collections.sort(childrens);
			for (Node childNode : childrens) {
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
		Stack<Node> frontier = new Stack<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		boolean isStart = false;
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			explored.add(currentNode);
			if (currentNode.getLabel().equals(start)) {
				isStart = true;
				frontier.clear();
				explored.clear();
			}
			if (isStart && currentNode.getLabel().equals(goal)) {
				System.out.print(NodeUtils.printPath(currentNode));
				return currentNode;
			}
			List<Node> childrens = currentNode.getChildrenNodes();
			Collections.sort(childrens);
			for (Node childNode : childrens) {
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

	public void execute(Node tree, int limitedDepth) {
		Stack<Node> frontier = new Stack<Node>();

		@SuppressWarnings("unchecked")
		ArrayList<Node>[] tmp = new ArrayList[limitedDepth + 1];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = new ArrayList<Node>();
		}
		int depth = 0;
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		tmp[0].add(tree);

		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			int depthTmp = findDepth(tmp, currentNode);
			if (depthTmp != -1) {
				depth = depthTmp;
			}
			System.out.print(currentNode.getLabel() + " ");
			explored.add(currentNode);
			List<Node> childrens = currentNode.getChildrenNodes();
			Collections.sort(childrens);
			boolean countable = false;
			for (Node childNode : childrens) {
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
					if (depth < limitedDepth) {
						countable = true;
						tmp[depth + 1].add(childNode);
						frontier.add(childNode);
					}
				}
			}
//			tmp[depth].add(currentNode);
			if (countable)
				depth++;
		}
	}

	private int findDepth(ArrayList<Node>[] tmp, Node currentNode) {
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].contains(currentNode)) {
				return i;
			}
		}
		return -1;
	}

}
