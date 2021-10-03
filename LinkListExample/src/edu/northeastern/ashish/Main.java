package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {

//        SingleLinkList<Integer> list1 = getList1();
//        SingleLinkList<Integer> list2 = getList2();
//
//        Node<Integer> sorted = sortedMerge(list1.head, list2.head);
//        printList(sorted);
//        System.out.println();
//
//        SingleLinkList<Integer> list = getLinkList();
//
//        list.zipMerge();
//        printList(list.head);

//
//        LinkListRandom list = new LinkListRandom();
//        LinkListRandom copy = list.makeCopy();
//
//        System.out.println();

//        SingleLinkList<Integer> list = getLinkList();
//        list.swapNodeInPairs();
//        list.printList();

//        SingleLinkList<Integer> list = getLinkList();
//        list.reverseInKGroups(3);
//        list.printList();

//        SingleLinkList<Integer> list = getUnsortedList();
//        mergeSort(list);
//        list.printList();
       // mergeSort(list.head);


//        SingleLinkList<Integer> list = exampleListSorted();
//        removeDuplicatedFromUnsortedList(list);
//        list.printList();

        SingleLinkList<Integer> list = partitionAroundValues();
        partitionAroundValueX(list.head, 3);
        list.printList();




    }
    /// region Link List Class 1
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

    private static Node<Integer> sortedMerge(Node<Integer> node1, Node<Integer> node2){
        Node<Integer> result = null;
        if(node1 == null){
            return  node2;
        }
        if(node2 == null){
            return  node1;
        }

        if( node1.data < node2.data ){
            result = node1;
            result.next =  sortedMerge(node1.next, node2);
        }else{
            result = node2;
            result.next = sortedMerge(node1, node2.next);
        }
        return  result;
    }

    public static void printList(Node<Integer> node){
        Node<Integer> temp = node;
        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    /// endregion


    /// region Link List class 2


    private static SingleLinkList<Integer> getLinkList(){
        SingleLinkList<Integer> list = new SingleLinkList<>();
        list.addTail(1);
        list.addTail(2);
        list.addTail(3);
        list.addTail(4);
        list.addTail(5);
        list.addTail(6);
        list.addTail(7);
        list.addTail(8);

        return list;

    }

    private static SingleLinkList<Integer> getUnsortedList(){
        SingleLinkList<Integer> list = new SingleLinkList<>();
        list.addTail(6);
        list.addTail(5);
        list.addTail(3);
        list.addTail(1);
        list.addTail(2);
        list.addTail(4);

        return list;

    }

    private static Node<Integer> breakListInHalf(Node<Integer> node){
        if(node == null || node.next == null){
            return null;
        }
        Node<Integer> front = node;
        Node<Integer> back = node;

        while(front.next != null){
            front = front.next;
            if(front.next != null){
                front = front.next;
                back = back.next;
            }
        }

        Node<Integer> secondHalf = back.next;
        back.next = null;
        return  secondHalf;
    }


    private static  void mergeSort(SingleLinkList<Integer> list){
        list.head = mergeSort(list.head);
    }


    private static Node<Integer> mergeSort(Node<Integer> node){
        if(node == null || node.next == null){
            return node ;
        }

        Node<Integer> second = breakListInHalf(node);

        node =  mergeSort(node);
        second = mergeSort(second);

        return sortedMerge(node, second);
    }


    private static  SingleLinkList<Integer> exampleListSorted(){
        SingleLinkList<Integer> list = new SingleLinkList<>();
        list.addTail(1);
        list.addTail(2);
        list.addTail(3);
        list.addTail(3);
        list.addTail(4);
        list.addTail(4);
        list.addTail(5);

        return list;
    }

    private static  void removeDuplicatedFromUnsortedList(SingleLinkList<Integer> list){
        if(list.head == null || list.head.next == null){
            return;
        }

        // Sort the list
        list.head = mergeSort(list.head);

        Node<Integer> temp = list.head;

        while(temp.next != null){
            if(temp.next.data == temp.data){
                temp.next = temp.next.next;
            }else{
                temp = temp.next;
            }
        }
    }


    private static  SingleLinkList<Integer> partitionAroundValues(){
        SingleLinkList<Integer> list = new SingleLinkList<>();
        list.addTail(1);
        list.addTail(4);
        list.addTail(3);
        list.addTail(2);
        list.addTail(5);
        list.addTail(2);

        return list;
    }

    public static  void partitionAroundValueX(SingleLinkList<Integer> list, int x){
        list.head = partitionAroundValueX(list.head,x );
    }


    public static Node<Integer> partitionAroundValueX(Node<Integer> node , int x){
        if(node == null || node.next == null){
            return node;
        }

        Node<Integer> before = null;
        Node<Integer> beforeEnd = null;
        Node<Integer> after = null;
        Node<Integer> afterEnd = null;

        Node <Integer> temp = node;

        while(temp != null){
            if(temp.data < x){
                if(before == null){
                    before = temp;
                    beforeEnd = temp;
                }else{
                    beforeEnd.next = temp;
                    beforeEnd = temp;
                }
            }else {
                if(after == null){
                    after = temp;
                    afterEnd = temp;
                }else{
                    afterEnd.next = temp;
                    afterEnd = temp;
                }
            }
            temp = temp.next;
        }

        beforeEnd.next = after;
        afterEnd.next = null;
        return  before;

    }

    /// endregion


}
