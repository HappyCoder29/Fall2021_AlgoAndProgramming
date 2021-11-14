package edu.northeastern.ashish;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert(5);
        tree.insert(4);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.levelOrder();


    }
}
