package com.tsu.inteleqtualuri;

public class Main {
    public static void main(String[] args) {
        int[] puzzle = {
                8, 6, 7,
                2, 5, 4,
                3, 0, 1,
        };
        int[] goal = {
                1, 2, 3,
                4, 5, 6,
                7, 8, 0,
        };
        Node initNode = new Node(puzzle, goal, 0);
        SearchAlgorithms search = new SearchAlgorithms();
        //search.breadthFirstSearch(initNode);
        System.out.println();
        search.aStar(initNode);
    }
}