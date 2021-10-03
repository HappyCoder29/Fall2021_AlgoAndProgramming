package edu.northeastern.ashish;

public class LinkListRandom {

    public NodeRandom head;

    public LinkListRandom(){
        init();
    }

    private void init(){
       NodeRandom  one = new NodeRandom(1);
        NodeRandom two = new NodeRandom(2);
        NodeRandom three = new NodeRandom(3);
        NodeRandom four = new NodeRandom(4);

        one.next = two;
        two.next = three;
        three.next = four;


        one.random = three;
        two.random = two;
        three.random = four;
        four.random = three;

        head = one;

    }

//    public LinkListRandom makeCopy(){
//
//       if(head == null){
//           return  null;
//       }
//
//       NodeRandom copyHead = new NodeRandom(head.data);
//
//       NodeRandom temp = head.next;
//       NodeRandom tempCopy = copyHead;
//
//       while(temp != null){
//           NodeRandom node = new NodeRandom(temp.data);
//           tempCopy.next = node;
//           tempCopy = tempCopy.next;
//           temp = temp.next;
//       }
//
//       LinkListRandom listRandom = new LinkListRandom();
//       listRandom.head = copyHead;
//       return  listRandom;
//
//    }



    // 3 O(n) ~= O(n)
    public LinkListRandom makeCopy(){


        if(head == null){
            return  null;
        }

        NodeRandom original = head;

        // Creating copy nodes inside the original Node
        //O(n)
        while(original != null){
            NodeRandom copy = new NodeRandom(original.data);
            copy.next = original.next;
            original.next = copy;
            original = original.next.next;
        }

        // Now assign the random pointers for the new created copy nodes
        //O(n)
        original = head;
        NodeRandom tempCopy = original.next;
        while(original != null ){
            tempCopy.random = original.random.next;
            original = original.next.next;
            if(tempCopy.next != null) {
                tempCopy = tempCopy.next.next;
            }

        }

        // Break the list
        //O(n)
        original = head;
        tempCopy = head.next;
        NodeRandom headCopy = head.next;
        while(original != null){
            original.next = original.next.next;
            if(tempCopy.next != null){
                tempCopy.next = tempCopy.next.next;
            }

            original = original.next;
            tempCopy = tempCopy.next;
        }

        LinkListRandom copyList = new LinkListRandom();
        copyList.head = headCopy;
        return  copyList;
    }


}
