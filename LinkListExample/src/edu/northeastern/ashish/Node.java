package edu.northeastern.ashish;

import java.util.Comparator;

public class Node <T> implements Comparable<T> {

    public T data;
    public Node<T> next;

    public Node(T data){
        this.data = data;
        this.next = null;
    }


    @Override
    public int compareTo(T other) {
        if(this.compareTo(other) > 0){
            return  1;
        }else if(this.compareTo(other) < 0){
            return  -1;
        }else{
            return  0;
        }
    }
}
