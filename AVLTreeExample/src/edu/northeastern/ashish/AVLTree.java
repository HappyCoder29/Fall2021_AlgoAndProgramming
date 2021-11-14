package edu.northeastern.ashish;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {

    public Node root;

    public  AVLTree(){

    }

    private int height(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node){
        if(node == null){
            return 0;
        }

        return  height(node.left) - height(node.right);
    }

    private Node rotateRight(Node A){
        Node temp = A.left;
        A.left = temp.right;
        temp.right = A;

        A.height = Math.max( height(A.left), height(A.right) ) + 1;
        temp.height = Math.max( height(temp.left), height(temp.right) ) + 1;
        return temp;

    }

    private Node rotateLeft(Node A){
        Node temp = A.right;
        A.right = temp.left;
        temp.left = A;

        A.height = Math.max( height(A.left), height(A.right) ) + 1;
        temp.height = Math.max( height(temp.left), height(temp.right) ) + 1;
        return temp;

    }

    public void insert(int data){
        root = insert(root, data);
    }

    private Node insert(Node node, int data){
        if(node == null){
            return new Node(data);
        }

        if(data < node.data){
            node.left = insert(node.left, data);
        }else if (data > node.data){
            node.right = insert(node.right, data);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);
        if(balance > 1 && data < node.left.data){
            return  rotateRight(node);
        }
        if(balance < -1 && data > node.right.data){
            return rotateLeft(node);
        }

        if(balance > 1 && data > node.left.data){
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if(balance < -1 && data < node.right.data){
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;

    }

    public void delete(int data){
        root = delete(root, data);
    }
    private Node getMinNode(Node node){
        if(node == null){
            return null;
        }
        Node current = node;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    private Node delete(Node node, int data){
        if(node == null){
            return null;
        }

        if(data < node.data){
            node.left = delete(node.left, data);
        }
        else if(data > node.data){
            node.right = delete(node.right, data);
        }else{
            // we have found the node to be deleted
            if(node.left == null){
                return node.right;
            }else if(node.right == null){
                return node.left;
            }
            // both left and right exist
            node.data = getMinNode(node.right).data;
            node.right = delete(node.right, node.data);
        }
        if(node == null){
            return null;
        }

        node.height = Math.max(height(node.left), height(node.right));


        int balance = getBalance(node);

        if(balance > 1 && getBalance(node.left) >= 0){
            return rotateRight(node);
        }

        if(balance > -1 && getBalance(node.right) <=  0){
            return rotateLeft(node);
        }

        if(balance > 1 && getBalance(node.left) < 0){
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if(balance > -1 && getBalance(node.right) > 0){
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;


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



}

