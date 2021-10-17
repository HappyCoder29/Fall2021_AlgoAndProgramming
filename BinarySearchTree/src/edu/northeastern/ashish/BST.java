package edu.northeastern.ashish;

import java.util.LinkedList;
import java.util.Queue;

public class BST  {
    public Node root;

    public BST(){

    }

    public void preOrder(){
        preOrder(root);
        System.out.println();
    }
    private void preOrder(Node node){
        if(node != null){
            System.out.print(node.data + ", ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void inOrder(){
        inOrder(root);
        System.out.println();
    }
    private void inOrder(Node node){
        if(node != null){
            inOrder(node.left);
            System.out.print(node.data + ", ");
            inOrder(node.right);
        }
    }

    public void reverseInOrder(){
        reverseInOrder(root);
        System.out.println();
    }
    private void reverseInOrder(Node node){
        if(node != null){
            reverseInOrder(node.right);
            System.out.print(node.data + ", ");
            reverseInOrder(node.left);
        }
    }

    public void postOrder(){
        postOrder(root);
        System.out.println();
    }
    private void postOrder(Node node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + ", ");
        }
    }


    /// Level Order
    public void levelOrder(){
        if(root == null){
            return;
        }
        Queue< Node > queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while( queue.isEmpty() == false ){
            Node node = queue.remove();
            if(node != null){
                System.out.print(node.data + ", ");
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }else{
                System.out.println();

                // Node was null
                // Check if the queue is empty if it is then break out of the loop
                // otherwise we will go in infinite loop
                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }
        }
        System.out.println();
    }

    public int size(){
        return  size(root);
    }
    private int size(Node node){

        if(node == null){
            return 0;
        }

        return  1 + size(node.left) + size(node.right);
    }

    public int height(){
        return height(root);
    }

    private int height(Node node){
        if(node == null){
            return 0;
        }
        int left = height(node.left);
        int right = height(node.right);

        return 1 + Math.max(left, right);

    }

    public int minDepth(){
        return minDepth(root);
    }

    private int minDepth(Node node){
        if(node == null){
            return 0;
        }
        int left = minDepth(node.left);
        int right = minDepth(node.right);

        return 1 + Math.min(left, right);

    }

    public boolean search(int value){
        return  search(root, value);
    }
    private boolean search(Node node, int value){
        if(node == null){
            return false;
        }

        if((int) node.data == value){
            return  true;
        }else if((int) node.data < value){
            return search(node.right, value);
        }else{
            return search(node.left, value);
        }
    }

    public void insert(int value){

        Node node = new Node(value);
        if(root == null){
            root = node;
            return;
        }

        Node current = root;
        Node parent = root;
        while(current != null){
            parent = current;
            if(current.data < value){
                current = current.right;
            }else{
                current = current.left;
            }
        }
        if(parent.data < value){
            parent.right = node;
        }else{
            parent.left = node;
        }

    }

    public Node getMinNode(){
        return getMinNode(root);
    }

    private Node getMinNode(Node node){
        if(node == null){
            return  null;
        }
        Node current = node;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }


    public Node getMaxNode(){
        return getMaxNode(root);
    }

    private Node getMaxNode(Node node){
        if(node == null){
            return  null;
        }
        Node current = node;
        while(current.right != null){
            current = current.right;
        }
        return current;
    }



}
