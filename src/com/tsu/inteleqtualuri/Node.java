package com.tsu.inteleqtualuri;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Node implements Comparable<Node> {

    int col = 3;
    List<Node> children = new LinkedList<>();
    Node parent;
    int[] puzzle;
    int[] goal;
    int zeroIndex;
    int depth = 0;
    int cost;

    public Node(int[] puzzle, int[] goal, int depth) {
        int[] pz = puzzle.clone();
        int[] gl = goal.clone();
        Arrays.sort(pz);
        Arrays.sort(gl);
        if (!Arrays.equals(pz, gl)) {
            throw new IllegalArgumentException();
        }
        this.puzzle = puzzle;
        this.goal = goal;
        this.depth = depth;
    }

    public Node(int[] puzzle, Node parent, int[] goal, int depth) {
        this.puzzle = puzzle;
        this.parent = parent;
        this.goal = goal;
        this.depth = depth;
    }

    public void expandNode() {
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == 0) {
                zeroIndex = i;
                break;
            }
        }
        moveDown(puzzle, zeroIndex);
        moveLeft(puzzle, zeroIndex);
        moveRight(puzzle, zeroIndex);
        moveUp(puzzle, zeroIndex);
    }

    public void moveRight(int[] puzzle, int i) {
        if (i % col < col - 1) {
            int[] pz = puzzle.clone();
            pz[i] += pz[i + 1];
            pz[i + 1] = pz[i] - pz[i + 1];
            pz[i] = pz[i] - pz[i + 1];
            Node child = new Node(pz, this, this.goal, ++this.depth);
            children.add(child);
        }
    }

    public void moveLeft(int[] puzzle, int i) {
        if (i % col > 0) {
            int[] pz = puzzle.clone();
            pz[i] += pz[i - 1];
            pz[i - 1] = pz[i] - pz[i - 1];
            pz[i] = pz[i] - pz[i - 1];
            Node child = new Node(pz, this, this.goal, (this.depth + 1));
            children.add(child);
        }
    }

    public void moveUp(int[] puzzle, int i) {
        if (i - col >= 0) {
            int[] pz = puzzle.clone();
            pz[i] += pz[i - col];
            pz[i - col] = pz[i] - pz[i - col];
            pz[i] = pz[i] - pz[i - col];
            Node child = new Node(pz, this, this.goal, ++this.depth);
            children.add(child);
        }
    }

    public void moveDown(int[] puzzle, int i) {
        if (i + col < puzzle.length) {
            int[] pz = puzzle.clone();
            pz[i] += pz[i + col];
            pz[i + col] = pz[i] - pz[i + col];
            pz[i] = pz[i] - pz[i + col];
            Node child = new Node(pz, this, this.goal, ++this.depth);
            children.add(child);
        }
    }

    public boolean isGoal() {
        return Arrays.equals(puzzle, goal);
    }

    @Override
    public boolean equals(Object obj) {
        return Arrays.equals(((Node) obj).puzzle, this.puzzle);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.puzzle);
    }

    @Override
    public int compareTo(Node o) {
        return
    }

    public void printPuzzle() {
        System.out.println();
        for (int i = 0; i < puzzle.length; i++) {
            System.out.print(puzzle[i] + " ");
            if (i % col == 2)
                System.out.println();
        }
    }
}
