package edu.northeastern.ashish;

import java.util.LinkedList;
import java.util.List;

public class MinHeap {
    private LinkedList<Integer> list;
    private int size;

    public MinHeap(){
        list = new LinkedList<>();
        size = 0;
    }

    private void swapList(LinkedList<Integer> list, int i, int j){
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }


    private int getParent(int n){
        return  (n-1)/2;
    }
    private int getLeftChild(int n){
        return  2 * n + 1;
    }
    private int getRightChild(int n){
        return  2 * n + 2;
    }

    private boolean isEmpty(){
        return  size == 0;
    }

    public int peek(){
        if(isEmpty()){
            return  Integer.MIN_VALUE;
        }
        return list.get(0);
    }

    public void add(int value){
        list.add(value);
        size ++;
        siftUp(size-1);

    }

    private void siftUp(int index){
        if( index == 0 ){
            return;
        }

        int parent = getParent(index);

        if(parent == index){
            return;
        }

        if( list.get(parent) >  list.get(index)){
            int temp = list.get(parent);
            list.set(parent, list.get(index));
            list.set(index, temp);
            siftUp(parent);
        }
    }

    public int remove(){
        if( isEmpty() ){
            return Integer.MIN_VALUE;
        }
        // Set the last value to the root of the heap
        int value = list.get(0);
        list.set(0, list.get(size -1));
        list.remove(size -1) ;
        size --;

        siftDown(0);
        return  value;

    }

    private void siftDown(int index){
        int leftChild = getLeftChild(index);
        int rightChild = getRightChild(index);

        int minIndex;
        if(rightChild >= size){
            // if my left child exists
            if(leftChild >= size ){
                return;
            }else{
                minIndex = leftChild;
            }
        }else{
            // Where Left as well as right child exist
            if(list.get(leftChild) < list.get(rightChild)){
                minIndex = leftChild;
            }else{
                minIndex = rightChild;
            }
        }

        // At this point either we have returned or we have one minChild
        if(list.get(index) > list.get(minIndex)){
            int temp = list.get(index);
            list.set(index, list.get(minIndex));
            list.set(minIndex, temp);
            siftDown(minIndex);
        }
    }
}
