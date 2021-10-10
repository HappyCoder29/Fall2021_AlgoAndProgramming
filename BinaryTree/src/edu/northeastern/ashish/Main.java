package edu.northeastern.ashish;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Tree<Integer> tree = new Tree<>();
        tree.root = createTree();
        tree.printZigZag();
       // System.out.println(tree.height());

        Node<Integer> node1 = generateSameNodes1();
        Node<Integer> node2 = generateSameNodes2();
        System.out.println(tree.areIsoMorphic(node1, node2));

        printAllRootToLeaf(tree);
        printSumRootToLeaf(tree);

        System.out.println(hasPathSum(tree, 21));


    }

    private static Node<Integer> createTree(){
        Node<Integer> root = new Node(1);

        // Second level
        root.left = new Node<>(2);
        root.right = new Node<>(3);

        // Third Level
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right.left = new Node<>(6);
        root.right.right = new Node<>(7);

        // Fourth Level
        root.left.left.left = new Node<>(8);
        root.left.right.left = new Node<>(9);
        root.right.left.left = new Node<>(10);
        root.right.right.right = new Node<>(11);

        return root;
    }

    private static Node<Integer> generateSameNodes1(){
        Node<Integer> node = new Node<>(1);
        node.left = new Node<>(2);
        node.right = new Node<>(3);
        node.left.left = new Node<>(4);
        node.left.right = new Node<>(5);

        return node;
    }

    private static Node<Integer> generateSameNodes2(){
        Node<Integer> node = new Node<>(1);
        node.left = new Node<>(5);
        node.right = new Node<>(7);
        node.left.left = new Node<>(3);
        node.left.right = new Node<>(6);

        return node;
    }

    public static void printAllRootToLeaf(Tree<Integer> tree){
        ArrayList<Integer> list = new ArrayList<>();

        printAllRootToLeaf(tree.root, list, 0);
    }

    private static void printAllRootToLeaf(Node<Integer> node, ArrayList<Integer> list, int ptr){
        if(node == null){
            return;
        }
        list.add(ptr, node.data);

        // If node == leaf we print out the entire list
        if(node.left == null && node.right == null){
            for(int i = 0 ; i <= ptr; i ++){
                System.out.print(list.get(i) + ", ");
            }
            System.out.println();
        }
        else{
            printAllRootToLeaf(node.left, list, ptr + 1);
            printAllRootToLeaf(node.right, list, ptr + 1);
        }
    }

    public static void printSumRootToLeaf(Tree<Integer> tree){
        ArrayList<Integer> list = new ArrayList<>();

        printSumRootToLeaf(tree.root, list, 0);
    }

    private static void printSumRootToLeaf(Node<Integer> node, ArrayList<Integer> list, int ptr){
        if(node == null){
            return;
        }
        list.add(ptr, node.data);

        // If node == leaf we print out the entire list
        if(node.left == null && node.right == null){
            int sum = 0;
            for(int i = 0 ; i <= ptr; i ++){
                sum += list.get(i);
            }
            System.out.println(sum);
        }
        else{
            printSumRootToLeaf(node.left, list, ptr + 1);
            printSumRootToLeaf(node.right, list, ptr + 1);
        }
    }

    public static boolean hasPathSum(Tree<Integer> tree, int sum){
        return hasPathSum(tree.root, sum);
    }
    private static boolean hasPathSum(Node<Integer> node, int sum){
        if(node == null){
            return  sum == 0 ;
        }

        return  hasPathSum(node.left, sum - node.data) || hasPathSum(node.right, sum - node.data);

    }
}
