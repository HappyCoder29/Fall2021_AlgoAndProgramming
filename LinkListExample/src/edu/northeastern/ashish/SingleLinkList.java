package edu.northeastern.ashish;

public class SingleLinkList <T> {

    public Node<T> head;
    public int count = 0;
    public Node<T> tail;

    public SingleLinkList(){
        head = null;
        count = 0;
    }

    public void addHead( T data ){
        Node<T> add = new Node<>(data);
        count++;
        if(head == null){
            head = add;
            tail = add;
            return;
        }
        add.next = head;
        head = add;
    }

    public void addTail(T data){

        Node<T> add = new Node<>(data);
        count++;
        if(head == null){
            head = add;
            tail = add;
            return;
        }

        tail.next = add;
        tail = add;
    }

    public void printList(){
        Node<T> temp = head;
        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    public void reverseList(){
        if(head == null || head.next == null){
            return;
        }

        Node<T> back = null;
        Node<T> mid = head;
        Node<T> front = head.next;

        while(front != null){
            mid.next = back;
            back = mid;
            mid = front;
            front = front.next;
        }
        mid.next = back;
        tail = head;
        head = mid;
    }


    public Node<T> reverseList(Node<T> node){
        if(head == null || head.next == null){
            return head;
        }

        Node<T> back = null;
        Node<T> mid = node;
        Node<T> front = node.next;

        while(front != null){
            mid.next = back;
            back = mid;
            mid = front;
            front = front.next;
        }
        mid.next = back;
        return  mid;
    }

    public int  length(){
        if(head == null){
            return 0;
        }
        int count = 0;
        Node<T> temp = head;
        while(temp != null){
            count ++;
            temp = temp.next;
        }
        return count;
    }

    // Assuming no cycle
    public Node<T> getNthFromTheEnd(int n){
        if(n <= 0 ){
            return null;
        }

        Node<T> front = head;
        Node<T> back = head;

        for(int i = 0 ; i < n; i ++){
            if(front != null){
                front = front.next;
            }
            else{
                return null;
            }
        }
        while(front != null){
            front = front.next;
            back = back.next;
        }
        return  back;
    }

    public boolean containsCycle(){
        if(head == null || head.next == null){
            return false;
        }

        Node<T> front = head;
        Node<T> back = head;

        while(front != null){
            front = front.next;
            if(front != null){
                front = front.next;
                back = back.next;
            }

            if(front == back){
                return true;
            }
        }
        return false;
    }

    public Node<T> findStartOfCycle(){

        if(head == null || head.next == null){
            return null;
        }
        Node<T> front = head;
        Node<T> back = head;

        while(front != null){
            front = front.next;
            if(front != null){
                front = front.next;
                back = back.next;
            }

            if(front == back){
                break;
            }
        }

        if(front == null){
            return  null;// there is no cycle
        }

        front = head;

        while(front != back){
            front = front.next;
            back = back.next;
        }

        return front;
    }

    public int findNumberOfElementsOutsideCycle(){
        if(head == null){
            return 0 ;
        }
        if(head.next == null){
            return  1;
        }
        Node<T> front = head;
        Node<T> back = head;

        while(front != null){
            front = front.next;
            if(front != null){
                front = front.next;
                back = back.next;
            }

            if(front == back){
                break;
            }
        }

        if(front == null){
            return  length();
        }

        int count = 0;
        front = head;
        while(front != back){
            front = front.next;
            back = back.next;
            count++;
        }
        return count;
    }

    public void breakCycleInList(){
        if(head == null || head.next == null){
            return;
        }
        Node<T> front = head;
        Node<T> back = head;

        while(front != null){
            front = front.next;
            if(front != null){
                front = front.next;
                back = back.next;
            }

            if(front == back){
                break;
            }
        }

        if(front == null){
            return;// there is no cycle
        }

        // Condition when there are no elements before the cycle
        if(front == head){
            front = front.next;
            while(front.next != back){
                front = front.next;
            }
            front.next = null;
            return;
        }

        front = head;
        Node<T> last = back;

        front = front.next;
        back = back.next;
        while(front != back){
            front = front.next;
            back = back.next;
            last = last.next;
        }

        last.next = null;

    }

    // Assuming there is no cycle;
    //O(n/2)
    public Node<T> breakListInHalf(){
        if(head == null || head.next == null){
            return null;
        }
        Node<T> front = head;
        Node<T> back = head;

        while(front.next != null){
            front = front.next;
            if(front.next != null){
                front = front.next;
                back = back.next;
            }
        }

        Node<T> secondHalf = back.next;
        back.next = null;
        return  secondHalf;
    }

    public Node<T> breakListInHalf(Node<T> node){
        if(node == null || node.next == null){
            return null;
        }
        Node<T> front = node;
        Node<T> back = node;

        while(front.next != null){
            front = front.next;
            if(front.next != null){
                front = front.next;
                back = back.next;
            }
        }

        Node<T> secondHalf = back.next;
        back.next = null;
        return  secondHalf;
    }


    // 5* O(n/2) = 2.5 * O(n) ~= O(n)
    public boolean isPalindrome(){
        if(head == null || head.next == null){
            return true;
        }

        //O(n/2)
        Node<T> secondHalf = breakListInHalf();
        //O(n/2)
        secondHalf = reverseList(secondHalf);

        Node<T> temp1 = head;
        Node<T> temp2 = secondHalf;
        boolean palindrome = true;
       // O(n/2)
        while(temp1 != null && temp2 != null ){
            if(temp1.data != temp2.data){
                palindrome = false;
                break;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }


        // Put the list back together
        //O(n/2)
        secondHalf = reverseList(secondHalf);

        //O(n/2)
        temp1 = head;
        while(temp1.next != null){
            temp1 = temp1.next;
        }
        temp1.next = secondHalf;

        return palindrome;
    }

    public  Node<T> getMiddleNode(){
        if(head == null || head.next == null){
            return null;
        }
        Node<T> front = head;
        Node<T> back = head;

        while( front != null && front.next != null ){
            front = front.next.next;
            back = back.next;
        }

        Node<T> secondHalf = back.next;
        back.next = null;
        return  secondHalf;
    }


    public Node<T> sortedMerge(Node<T> node1, Node<T> node2){
        Node<T> result = null;
        if(node1 == null){
            return  node2;
        }
        if(node2 == null){
            return  node1;
        }

        if(node1.compareTo((T) node2) < 0){
            result = node1;
            result.next = sortedMerge(node1.next, node2);
        }else{
            result = node2;
            result.next = sortedMerge(node1, node2.next);
        }
        return  result;
    }


}
