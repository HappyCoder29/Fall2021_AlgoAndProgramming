package edu.northeastern.ashish;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Tree<Integer> tree = new Tree<>();
        tree.root = createTree2();
        System.out.println(tree.diameter());
//        tree.root = createTree();
//        tree.printZigZag();
//       // System.out.println(tree.height());
//
//        Node<Integer> node1 = generateSameNodes1();
//        Node<Integer> node2 = generateSameNodes2();
//        System.out.println(tree.areIsoMorphic(node1, node2));
//
//        printAllRootToLeaf(tree);
//        printSumRootToLeaf(tree);
//
//        System.out.println(hasPathSum(tree, 21));

//         tree.root = createTreeForMirror();
//
//         tree.mirrorTree();
//
//         tree.levelOrder();

//        tree.root = createTreeForIsFoldable();
//        System.out.println(tree.isFoldable());

//        tree.root = createTreeForGetMaxUniLength();
//
//        System.out.println(tree.getMaxUniLength());

//        Integer[] inOrder = {8,4,9,2,5,10,1,6,3,7,11};
//        Integer[] preOrder = {1,2,4,8,9,5,10,3,6,7,11};
//
//        Node<Integer> node = tree.getTreeFromPreAndInOrder(inOrder, preOrder);
//        System.out.println();



    }

    /// region Class 1

    private static Node<Integer> createTree2(){
        Node<Integer> root = new Node(1);

        // Second level
        root.left = new Node<>(2);
        root.right = new Node<>(3);

        // Third Level
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);

        root.left.left.left = new Node<>(6);
        root.left.left.right = new Node<>(7);
        root.left.left.right.left = new Node<>(8);
        root.left.left.right.left.right = new Node<>(9);

        root.left.right.right = new Node<>(10);
        root.left.right.right.left = new Node<>(11);
        root.left.right.right.right = new Node<>(12);
        root.left.right.right.right.left = new Node<>(13);




        return root;
    }

    private static Node<Integer> createTree1(){
        Node<Integer> root = new Node(1);

        // Second level
        root.left = new Node<>(4);
        root.right = new Node<>(3);

        // Third Level
        root.left.left = new Node<>(2);
        root.left.right = new Node<>(5);
        root.right.right = new Node<>(8);

        // Fourth Level
        root.left.right.left = new Node<>(6);
        root.right.right.left = new Node<>(7);

        root.right.right.left.left = new Node<>(0);
        root.right.right.left.right = new Node<>(9);


        return root;
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
    /// endregion


    /// region class 2

    private static Node<Integer> createTreeForMirror(){
        Node<Integer> root = new Node(1);

        // Second level
        root.left = new Node<>(2);
        root.right = new Node<>(3);

        // Third Level
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right.left = new Node<>(6);


        return root;
    }

    private static Node<Integer> createTreeForIsFoldable(){
        Node<Integer> root = new Node(1);

        // Second level
        root.left = new Node<>(2);
        root.right = new Node<>(3);

        // Third Level
        root.left.left = new Node<>(4);
        root.right.right = new Node<>(5);


        return root;
    }

    private static Node<Integer> createTreeForGetMaxConsequtive(){
        Node<Integer> root = new Node(1);

        // Second level
        root.left = new Node<>(2);
        root.right = new Node<>(2);

        // Third Level
        root.left.left = new Node<>(7);
        root.left.right = new Node<>(6);

        root.right.right = new Node<>(3);
        root.right.right.right = new Node<>(5);

        return root;
    }


    private static Node<Integer> createTreeForGetMaxUniLength(){
        Node<Integer> root = new Node(1);

        // Second level
        root.left = new Node<>(1);
        root.right = new Node<>(2);

        // Third Level
        root.left.left = new Node<>(7);
        root.left.right = new Node<>(1);
        root.left.right.left = new Node<>(1);


        root.right.right = new Node<>(2);
        root.right.right.right = new Node<>(5);

        return root;
    }

    /// endregion
}
