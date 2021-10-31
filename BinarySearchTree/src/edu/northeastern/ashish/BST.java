package edu.northeastern.ashish;

import java.util.Arrays;
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


    public void levelOrder(Node rootNode){
        if(rootNode == null){
            return;
        }
        Queue< Node > queue = new LinkedList<>();
        queue.add(rootNode);
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

        if( node.data == value){
            return  true;
        }else if(  node.data < value){
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

    public void delete (int data){
       root =  delete(root, data);
    }

    private Node delete(Node node, int data){
        if(node == null){
            return null;
        }

        if(data < node.data){
            node.left = delete(node.left, data);
        }else if( data > node.data){
            node.right = delete(node.right, data);
        }else{
            // either one or both child are null
            if(node.left == null){
                return node.right;
            }
            else if(node.right == null){
                return node.left;
            }

            // Node has both left asd well as right node
            // Get Min node on the right and assign its value to current node
            node.data = getMinNode(node.right).data;
            node.right = delete(node.right, node.data);

        }

        return node;
    }

    public int[] getSortedArrayFromBST(){
        int size = size();
        if(size == 0 ){
            return null ;
        }
        int[] arr = new int[size];
        BoxValue<Integer> box = new BoxValue<>(0);
        getSortedArrayFromBST(root, arr, box);
        return arr;

    }

    public int[] getArrayFromTree(Node node){
        int size = size(node);
        if(size == 0 ){
            return null ;
        }
        int[] arr = new int[size];
        BoxValue<Integer> box = new BoxValue<>(0);
        getSortedArrayFromBST(node, arr, box);
        return arr;

    }
    private void getSortedArrayFromBST(Node node, int[] arr, BoxValue<Integer> box){
        if(node != null){
            getSortedArrayFromBST(node.left, arr, box);

            //get value from node and store in int array
            // increment the box pointer
            arr[box.value] = node.data;
            box.value ++;

            getSortedArrayFromBST(node.right, arr, box);

        }
    }

    public void convertBinaryTreeToBST(Node node){
        if(node == null){
            return;
        }
        int[] arr = getArrayFromTree(node);
        Arrays.sort(arr);
        BoxValue<Integer> box = new BoxValue<>(0);
        convertBinaryTreeToBST(node, arr, box);


    }


    public void convertBinaryTreeToBST(){
        if(root == null){
            return;
        }
        int[] arr = getSortedArrayFromBST();
        Arrays.sort(arr);
        BoxValue<Integer> box = new BoxValue<>(0);
        convertBinaryTreeToBST(root, arr, box);

    }

    private void convertBinaryTreeToBST(Node node, int[] arr, BoxValue<Integer> index ){
        if(node != null){
            convertBinaryTreeToBST(node.left, arr, index);

            node.data = arr[index.value];
            index.value ++;

            convertBinaryTreeToBST(node.right, arr, index);

        }
    }

    public void inOrderWithoutBox(){
        int index = 0;
        inOrderWithoutBox(root, index);
        System.out.println();
    }

    private void inOrderWithoutBox(Node node, int index){
        if(node != null){
            inOrderWithoutBox(node.left, index);
            System.out.println("Node.data = " + node.data );
            System.out.println("Index value = " + index);

            index ++;
            inOrderWithoutBox(node.right, index);
        }

    }


    public void inOrderWithBox(){
        BoxValue<Integer> index = new BoxValue<>(0);
        inOrderWithBox(root, index);
        System.out.println();
    }

    private void inOrderWithBox(Node node, BoxValue<Integer>  index){
        if(node != null){
            inOrderWithBox(node.left, index);
           // System.out.println("Node.data = " + node.data );
            System.out.println("Index value = " + index.value);

            index.value ++;
            inOrderWithBox(node.right, index);
        }

    }

    public Node mergeTwoBinaryTrees(Node root1, Node root2){
        if(root1 == null){
            convertBinaryTreeToBST(root2);
            return root2;
        }
        if(root2 == null){
            convertBinaryTreeToBST(root1);
            return root1;
        }
        int[] arr1 = getArrayFromTree(root1);
        int[] arr2 = getArrayFromTree(root2);
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int[] merged = mergeSortedArrays(arr1, arr2);

        Node mergedRoot = getBalancedBSTFromSortedArray(merged);

        return mergedRoot;

    }

    private  int[] mergeSortedArrays(int[] arr1, int[] arr2){
        if(arr1 == null){
            return  arr2;
        }
        if(arr2 == null){
            return  arr1;
        }

        int[] merged = new int[ arr1.length + arr2.length];
        int ptr1 = 0;
        int ptr2 = 0;
        int ptr3 = 0;

        while(ptr1 < arr1.length && ptr2 < arr2.length){
            if(arr1[ptr1] < arr2[ptr2]){
                merged[ptr3 ++] = arr1[ptr1 ++];
            }else{
                merged[ptr3 ++] = arr2[ptr2 ++];
            }
        }
        while(ptr1 < arr1.length){
            merged[ptr3 ++] = arr1[ptr1 ++];
        }
        while(ptr2 < arr2.length){
            merged[ptr3 ++] = arr2[ptr2 ++];
        }
        return  merged;

    }


    public  Node getBalancedBSTFromSortedArray(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        return getBalancedBSTFromSortedArray( arr, 0, arr.length -1);
    }

    private  Node getBalancedBSTFromSortedArray(int[] arr, int start, int end){
        if(start > end){
            return null;
        }
        int mid = (start + end)/2;
        Node node = new Node(arr[mid]);
        node.left = getBalancedBSTFromSortedArray(arr, start, mid-1);
        node.right = getBalancedBSTFromSortedArray(arr, mid + 1, end);
        return node;

    }

    public void printInRange(int low, int high){
        printInRange(root, low, high);
        System.out.println();
    }

    private void printInRange(Node node, int low, int high){
        if(node != null){
            printInRange(node.left, low, high);

            if(node.data > high){
                return;
            }

            if(node.data >= low && node.data <= high){
                System.out.print(node.data + ", ");
            }

            printInRange(node.right, low, high);

        }
    }

    public Node findKthLargest(int k){
        if(k < 0 || root == null){
            return null;
        }
        BoxValue<Node> boxNode = new BoxValue<>(null);
        BoxValue<Integer> boxIndex = new BoxValue<>(k);
        findKthLargest(root, boxNode, boxIndex);
        return boxNode.value;
    }

    private void findKthLargest(Node node, BoxValue<Node> boxNode, BoxValue<Integer> boxIndex){

        if(node != null){

            findKthLargest(node.right, boxNode, boxIndex);
            boxIndex.value --;
            if(boxIndex.value == 0){
                boxNode.value = node;
                return;
            }
            findKthLargest(node.left, boxNode, boxIndex);
        }

    }

    public Node findKthSmallest(int k){
        if(k < 0 || root == null){
            return null;
        }
        BoxValue<Node> boxNode = new BoxValue<>(null);
        BoxValue<Integer> boxIndex = new BoxValue<>(0);
        findKthSmallest(root, boxNode, boxIndex, k);
        return boxNode.value;
    }

    private void findKthSmallest(Node node, BoxValue<Node> boxNode, BoxValue<Integer> boxIndex, int k ){

        if(node != null){


            findKthSmallest(node.left, boxNode, boxIndex, k);
            boxIndex.value ++;
            if(boxIndex.value == k){
                boxNode.value = node;
                return;
            }
            findKthSmallest(node.right, boxNode, boxIndex, k);
        }

    }










}
