package edu.northeastern.ashish;

import java.util.*;

 enum COLOR {White, Black, Grey};

public class Graph {

    public HashMap<String, Node> nodes;
    public Graph() {
        nodes = new HashMap<>();
    }

    public void addNode(String name){
        name = name.toUpperCase();
        if(nodes.containsKey(name)){
            return;
        }
        Node node = new Node(name);
        nodes.put(name, node);
    }

    public void addEdge(String node1Name, String node2Name){
        Node node1 = nodes.get(node1Name);
        Node node2 = nodes.get(node2Name);
        if( !nodes.containsKey(node1.getName()) || !nodes.containsKey(node2.getName())){
            return;
        }

        Edge edge = getEdge(node1, node2);
        if(edge == null){
            edge = new Edge(node1.getName(), node2.getName());
            node1.getListEdges().add(edge);
        }

    }

    private Edge getEdge(Node node1, Node node2){

        for (Edge  edge: node1.getListEdges()) {
            if(edge.endNode.equals(node2.getName())){
                return edge;
            }
        }
        return null;
    }

    private void resetVisited(){
        nodes.forEach( (k,v) ->{
            v.setVisited(false);
        });
    }

    private void resetColors(){
        nodes.forEach( (k,v) ->{
            v.color = COLOR.White;
        });
    }

    public void breadthFirstSearch(String startNode){
        startNode = startNode.toUpperCase();
        if(!nodes.containsKey(startNode)){
            return;
        }
        resetVisited();
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes.get(startNode));
        queue.add(null);
        while(!queue.isEmpty()){
            Node node = queue.remove();
            if(node != null){
                if(node.isVisited() == false){
                    System.out.print(node.getName() + " ");
                }
                node.setVisited(true);
                for (Edge edge : node.getListEdges()) {
                    Node neighbour = nodes.get(edge.endNode);
                    if(neighbour.isVisited() == false){
                        queue.add(neighbour);
                    }
                }
                // end of if node != null
            }else{
                // Node is null
                System.out.println();
                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }// end of else node == null
        }// end of queue is empty

    }// end of function


    public void depthFirstSearch(String startNode){
        startNode = startNode.toUpperCase();
        if(!nodes.containsKey(startNode)){
            return;
        }
        resetVisited();
        Stack<Node> stack = new Stack<>();
        stack.add(nodes.get(startNode));
        while(!stack.isEmpty()){
            Node node = stack.pop();

            if(node.isVisited() == false){
                System.out.print(node.getName() + " ");
            }
            node.setVisited(true);
            for (Edge edge : node.getListEdges()) {
                Node neighbour = nodes.get(edge.endNode);
                if(neighbour.isVisited() == false){
                    stack.push(neighbour);
                }
            }
        }// end of queue is empty

    }// end of function

    public boolean containsCycle(){
        for (Map.Entry<String, Node> entry : nodes.entrySet()) {
            System.out.println("Start Node = " + entry.getKey());
            Stack<Node> stack = new Stack<>();
            Node startNode = entry.getValue();
            BoxValue<Boolean> box = new BoxValue<>();
            box.value = false;
            if(containsCycle(startNode, stack, box)){
                return true;
            }
        }
        return false;

    }

    private boolean containsCycle(Node node, Stack<Node> stack, BoxValue<Boolean> box){
        if(stack.contains(node)){
            return true;
        }
        stack.push(node);
        for (Edge edge : node.getListEdges()) {
            Node neighbour = nodes.get(edge.endNode);
            if(containsCycle(neighbour, stack, box ) && box.value == false){
                System.out.print(neighbour.getName() + " <- ");
                while(!stack.isEmpty()){
                    Node pop = stack.pop();
                    System.out.print(pop.getName() + " <- ");
                    if(neighbour == pop){
                        break;
                    }
                }
                box.value = true;
                System.out.println();
                return true;
            }

            if(box.value = true){
                return true;
            }

        }
        stack.pop();
        return false;

    }

    public boolean containsCycleUnionFind(){
        LinkedList<Edge> edges = new LinkedList<>();

        for (Map.Entry<String, Node> entry : nodes.entrySet()) {
            Node node = entry.getValue();
            node.setParent( node);
            node.setRank(1);
            for (Edge edge : node.getListEdges()) {
                edges.add(edge);
            }
        }

        for (Edge edge: edges) {
            Node node1 = nodes.get(edge.startNode);
            Node node2 = nodes.get(edge.endNode);
            if(node1.getParent() == node2.getParent()){
                return true;
            }
            union(node1, node2);

        }

        return false;


    }



    private void union(Node node1, Node node2){
        Node parent1 = findParent(node1);
        Node parent2 = findParent(node2);

        if(parent1.getName() == parent2.getName()){
            return;
        }
        if(parent1.getRank() >= parent2.getRank()){
            if(parent1.getRank() == parent2.getRank()){
                parent1.setRank(parent1.getRank()  + 1);
            }
            parent2.setParent(parent1);
        }else{
            parent1.setParent(parent2);
        }
    }


    private Node findParent(Node node){
        Node parent = node.getParent();
        if(parent == node){
            return node;
        }
        node.setParent(findParent(node.getParent()));
        return node.getParent();
    }


    public boolean containsCycleGraphColoring(){
        resetColors();

        for (Map.Entry<String, Node> entry : nodes.entrySet()) {

            Node node = entry.getValue();
            if(node.color == COLOR.White){
                if(containsCycleGraphColoring(node) == true){
                    return true;
                }
            }

        }
        return false;
    }
    private boolean containsCycleGraphColoring(Node node){
        node.color = COLOR.Grey;
        for(Edge edge : node.getListEdges()){
            Node endNode = nodes.get(edge.endNode);
            if(endNode.color == COLOR.Grey){
                return true;
            }
        }
        node.color = COLOR.Black;
        return false;
    }

    public void printAllPaths(String source, String dest){
        source = source.toUpperCase();
        dest = dest.toUpperCase();

        if( !nodes.containsKey(source) || !nodes.containsKey(dest) ){
            return;
        }

        LinkedList<String> visited = new LinkedList<>();
        printAllPaths(visited, source, dest);

    }

    private void printAllPaths(LinkedList<String> visited, String current, String dest){
        if( visited.contains(current) ){
            return;
        }
        if(dest == current){
            for (String str : visited) {
                System.out.print(str + " -> ");
            }
            System.out.println(dest);
        }

        visited.add(current);

        Node node = nodes.get(current);
        for (Edge edge : node.getListEdges()) {
            if( ! visited.contains(edge.endNode) ){
                printAllPaths(visited, edge.endNode, dest);
            }
        }

        visited.remove(current);
    }






}


