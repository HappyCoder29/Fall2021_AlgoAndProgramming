package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {

        Graph graph = initialize();
       // graph.depthFirstSearch("A");
        System.out.println(graph.containsCycleGraphColoring());
        System.out.println();

    }

    private static Graph initialize(){
        Graph graph = new Graph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "E");
        graph.addEdge("E", "D");
        graph.addEdge("E", "F");
        graph.addEdge("D", "B");

        return graph;
    }
}
