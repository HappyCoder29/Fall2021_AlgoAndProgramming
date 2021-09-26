package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {

//       SingleLinkList<Integer> list = createList();
////        list.printList();
////        list.reverseList();
////        list.printList();
////
////        Node<Integer> node = list.getNthFromTheEnd(5);
////        if(node != null){
////            System.out.println(node.data);
////        }
//
//        System.out.println(list.containsCycle());
//
//

//        SingleLinkList<Integer> listCyclic = createCyclicList();
////
////        System.out.println(listCyclic.containsCycle());
////
////        Node<Integer> startOfCycle = listCyclic.findStartOfCycle();
////
////        if(startOfCycle != null){
////            System.out.println(startOfCycle.data);
////        }
//
//     //   System.out.println(listCyclic.findNumberOfElementsOutsideCycle());
//
//        listCyclic.breakCycleInList();
//        System.out.println();
//
//        SingleLinkList<Integer> palindrome = getPlaindromeList();
//        Node<Integer> mid = palindrome.getMiddleNode();
//        System.out.println(palindrome.isPalindrome());

        SingleLinkList<Integer> list = new SingleLinkList<>();

        SingleLinkList<Integer> list1 = getList1();
        SingleLinkList<Integer> list2 = getList2();

        Node<Integer> sorted = list.sortedMerge(list1.head, list2.head);

        System.out.println();





    }

    private static SingleLinkList<Integer> createList(){

        SingleLinkList<Integer> list = new SingleLinkList<>();
        list.addHead(1);
        list.addHead(2);
        list.addHead(3);
        list.addHead(4);
        list.addHead(5);

        return list;
    }

    private static  SingleLinkList<Integer> createCyclicList(){


        SingleLinkList<Integer> list = new SingleLinkList<>();
        list.addTail(1);
        list.addTail(2);
        list.addTail(3);
        list.addTail(4);
        list.addTail(5);
        list.addTail(6);
        list.addTail(7);
        list.addTail(8);
        list.addTail(9);
        list.addTail(10);

        Node<Integer> four = list.head;
        list.tail.next = four;
        return  list;

    }

    private static SingleLinkList<Integer> getPlaindromeList(){
        SingleLinkList<Integer> list = new SingleLinkList<>();
        list.addTail(1);
        list.addTail(2);
        list.addTail(3);
        list.addTail(4);
        list.addTail(4);
        list.addTail(3);
        list.addTail(2);
        list.addTail(1);

        return  list;

    }

    private static SingleLinkList<Integer> getList1(){
        SingleLinkList<Integer> list = new SingleLinkList<>();
        list.addTail(1);
        list.addTail(3);
        list.addTail(5);
        list.addTail(7);


        return  list;

    }

    private static SingleLinkList<Integer> getList2(){
        SingleLinkList<Integer> list = new SingleLinkList<>();
        list.addTail(2);
        list.addTail(4);
        list.addTail(6);
        list.addTail(8);


        return  list;

    }

}
