package edu.northeastern.ashish;

import java.util.*;

public class Tree <T>{
    public Node<T> root;

    public Tree(){
        this.root = null;
    }

    public void preOrder(){
        preOrder(root);
        System.out.println();
    }
    private void preOrder(Node<T> node){
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
    private void inOrder(Node<T> node){
        if(node != null){
            inOrder(node.left);
            System.out.print(node.data + ", ");
            inOrder(node.right);
        }
    }

    public void postOrder(){
        postOrder(root);
        System.out.println();
    }
    private void postOrder(Node<T> node){
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
        Queue< Node<T> >  queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while( queue.isEmpty() == false ){
            Node<T> node = queue.remove();
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
    private int size(Node<T> node){

        if(node == null){
            return 0;
        }

        return  1 + size(node.left) + size(node.right);
    }

    public int height(){
        return height(root);
    }

    private int height(Node<T> node){
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

    private int minDepth(Node<T> node){
        if(node == null){
            return 0;
        }
        int left = minDepth(node.left);
        int right = minDepth(node.right);

        return 1 + Math.min(left, right);

    }

    public void printLeftView(){
        if(root == null){
            return;
        }

        Queue< Node<T> > queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        boolean bPrint = true;

        while(true){
            Node<T> node = queue.remove();
            if(node != null){

                if(bPrint == true){
                    System.out.println(node.data);
                    bPrint = false;
                }
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }else{
                // Whenever we change levels we would print the next value
                bPrint = true;
                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }
        }
    }// end of function print left view


    public void depthFirst(){
        if(root == null){
            return;
        }

        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        while(stack.size() != 0 ){

            Node<T> node = stack.pop();
            System.out.print(node.data + ", ");

            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
    }// end of depth first traversal


    public void printRightView(){
        if(root == null){
            return;
        }

        Queue< Node<T> > queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        Node<T> lastNode = root;

        while(true){
            Node<T> node = queue.remove();
            if(node != null){
                lastNode = node;
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }else{

                System.out.println(lastNode.data);

                // Whenever we change levels we would print the next value
                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }
        }

    }// end of function print left view


    public void printBorder(){
        Stack<Node<T>> stack = new Stack<>();
        printBorder(root, 0, 0 , stack);

        while(stack.size() > 0){
            System.out.print(stack.pop().data + ", ");
        }
        System.out.println();
    }

    // This is breaking in one condition
    // I will write the code for that condition later
    private void printBorder(Node<T> node, int left, int right, Stack< Node<T> > stack){
        if(node != null){
            // if it is leaf node
            if(node.left == null && node.right == null){
                System.out.print(node.data + ", ");
            } else if( right == 0){
                System.out.print(node.data + ", ");
            }else if (left == 0 ){
                stack.push(node);
            }

            printBorder(node.left, left +1, right, stack);
            printBorder(node.right, left, right + 1, stack);
        }

    }


    public void printZigZag(){
        if(root == null){
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        boolean bPrint = true;
        Stack<Node<T>> stack = new Stack<>();

        while(true){
            Node<T> node = queue.remove();
            if(node != null){
                if(bPrint == true){
                    System.out.print(node.data + ", ");
                }else{
                    stack.push(node);
                }
                // Add left and right child to queue
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }else{
                bPrint = !bPrint;
                printStack(stack);

                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }
        }
    }


    private void printStack(Stack<Node<T>> stack){
        while(stack.size() != 0){
            System.out.print(stack.pop().data + ", ");
        }
        System.out.println();
    }


    public boolean areSameNodes(Node<T> node1, Node<T> node2){
        if(node1 == null && node2 == null){
            return true;
        }
        if(node1 == null || node2 == null){
            return false;
        }

        return  node1.data == node2.data &&
                areSameNodes(node1.left, node2.left) &&
                areSameNodes(node1.right, node2.right) ;
    }

    public boolean areIsoMorphic(Node<T> node1, Node<T> node2){
        if(node1 == null && node2 == null){
            return true;
        }
        if(node1 == null || node2 == null){
            return false;
        }

        return  areIsoMorphic(node1.left, node2.left) &&
                areIsoMorphic(node1.right, node2.right) ;
    }

    public boolean isSubtree(Node<T> tree, Node<T> subTree){
        return  areSameNodes(tree, subTree) ||
                areSameNodes(tree.left, subTree) ||
                areSameNodes(tree.right, subTree);
    }






}
