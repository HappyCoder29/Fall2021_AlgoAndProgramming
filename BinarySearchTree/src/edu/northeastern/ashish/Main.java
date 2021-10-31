package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {
//        BST tree = new BST();
//        tree.root = createBST();
//
////       // tree.preOrder();
////        tree.inOrder();
////        //tree.postOrder();
////        tree.reverseInOrder();
////
////        tree.insert(5);
//
//      //  tree.delete(10);
//       // tree.levelOrder();
//
////        int[] arr = tree.getSortedArrayFromBSTgetSortedArrayFromBST();
////
////        printArray(arr);
////
////        arr = new int[]{1,2,3,4,5,6,7,8,9};
////
////        Node root = getBalancedBSTFromSortedArray(arr);
////
////        tree.levelOrder(root);
////
//
//
//        tree.root = createBinaryTree();
//        tree.convertBinaryTreeToBST();
//        tree.levelOrder();

        BST tree = new BST();
        tree.root = createBST();

        Node node = tree.findKthSmallest(4);

        System.out.println(node.data);



        System.out.println();


    }

    private static void printArray(int[] arr){

        for (int i: arr) {
            System.out.print(i + ", ");
        }
        System.out.println();

    }


    private static Node createBinaryTree(){
        Node root = new Node(20);

        // Second level
        root.left = new Node(1);
        root.right = new Node(15);

        // Third Level
        root.left.left = new Node(23);
        root.left.right = new Node(64);
        root.right.left = new Node(89);
        root.right.right = new Node(-2);

        // Fourth Level
        root.left.left.left = new Node(3);
        root.left.right.left = new Node(10);
        root.right.left.left = new Node(7);
        root.right.right.right = new Node(12);

        return root;
    }


    private static  Node createBST(){
        Node root = new Node(8);

        root.left = new Node(3);
        root.right = new Node(10);

        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.right = new Node(14);

        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);
        root.right.right.left = new Node(13);

        return  root;
    }

    public static Node getBalancedBSTFromSortedArray(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        return getBalancedBSTFromSortedArray( arr, 0, arr.length -1);
    }

    private static Node getBalancedBSTFromSortedArray(int[] arr, int start, int end){
        if(start > end){
            return null;
        }
        int mid = (start + end)/2;
        Node node = new Node(arr[mid]);
        node.left = getBalancedBSTFromSortedArray(arr, start, mid-1);
        node.right = getBalancedBSTFromSortedArray(arr, mid + 1, end);
        return node;

    }
}
