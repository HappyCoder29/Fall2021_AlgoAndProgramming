package edu.northeastern.ashish;

import java.util.Comparator;

public class Node <T> {

    public T data;
    public Node<T> next;

    public Node(T data){
        this.data = data;
        this.next = null;
    }


}
