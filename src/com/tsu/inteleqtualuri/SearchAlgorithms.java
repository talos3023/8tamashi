package com.tsu.inteleqtualuri;

import java.util.*;

public class SearchAlgorithms {
    public void breadthFirstSearch(Node root) {
        System.out.println("Searching with BFS!!!");
        LinkedHashSet<Node> openList = new LinkedHashSet<>();
        LinkedHashSet<Node> closedList = new LinkedHashSet<>();
        openList.add(root);
        int totalNodesCount = 1;
        while (!openList.isEmpty()) {
            Node currentNode = openList.iterator().next();
            openList.remove(currentNode);
            closedList.add(currentNode);
            currentNode.expandNode();
            for (Node child : currentNode.children) {
                totalNodesCount++;
                if (totalNodesCount%20000 == 0) {
                    System.out.println(totalNodesCount);
                }
                if (child.isGoal()) {
                    printResults(child, totalNodesCount, closedList.size() + openList.size());
                    return;
                }
                if (!openList.contains(child) && !closedList.contains(child)) {
                    openList.add(child);
                }
            }
        }
        printResults(null, totalNodesCount, openList.size() + closedList.size());
    }

    public void aStar(Node root) {
        System.out.println("Searching with A*!!!");
        TreeSet<Node> openList = new TreeSet<>((a, b) -> (a.cost + a.depth) - (b.cost + b.depth));
        TreeSet<Node> closedList = new TreeSet<>((a, b) -> (a.cost + a.depth) - (b.cost + b.depth));
        root.cost = calculateCostFn(root);
        openList.add(root);
        int totalNodesCount = 1;
        while (!openList.isEmpty()) {
            Node currentNode = openList.pollFirst();
            closedList.add(currentNode);
            currentNode.expandNode();
            for (Node child : currentNode.children) {
                child.cost = calculateCostFn(child);
                totalNodesCount++;
                if (totalNodesCount%20000 == 0) {
                    System.out.println(totalNodesCount);
                }
                if (child.isGoal()) {
                    printResults(child, totalNodesCount, closedList.size() + openList.size());
                    return;
                }
                if (!openList.contains(child) && !closedList.contains(child)) {
                    openList.add(child);
                }
            }
        }
        printResults(null, totalNodesCount, openList.size() + closedList.size());
    }

    public void printResults(Node node, int totalNodesCount, int uniqueNodesCount) {
        if (node != null) {
            System.out.println("Goal Found!");
            System.out.println("Depth is : " + node.depth);
            System.out.println("Total nodes checked : " + totalNodesCount);
            System.out.println("Total unique nodes checked : " + uniqueNodesCount);
            printPathTrace(node);
        } else {
            System.out.println("Goal wasn't found :(");
            System.out.println("Total nodes checked : " + totalNodesCount);
            System.out.println("Total unique nodes checked : " + uniqueNodesCount);
        }
    }

    public int calculateCostFn(Node root) {
        int count = 0;
        int[] puzzle = root.puzzle;
        int[] goal = root.goal;
        int n = puzzle.length;
        for (int i = 0; i < n; i++) {
            if (puzzle[i] != 0 && puzzle[i] != goal[i]) {
                count++;
            }
        }
        return count;
    }

    public void printPathTrace(Node node) {
        System.out.println("Tracing Path...");
        List<Node> solutionPath = new LinkedList<>();
        Node current = node;
        solutionPath.add(current);
        while (current.parent != null) {
            current = current.parent;
            solutionPath.add(current);
        }
        for (int i = solutionPath.size() - 1; i >= 0; i--) {
            solutionPath.get(i).printPuzzle();
        }
    }
}
