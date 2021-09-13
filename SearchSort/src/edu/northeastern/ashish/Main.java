package edu.northeastern.ashish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int[] arr = {6,5,3,1,8,7,2,4};

        printArray(arr);
//        bubbleSort(arr);
//        printArray(arr);
//        selectionSort(arr);
//        printArray(arr);

//        mergeSort(arr);
//        printArray(arr);

//        quickSort(arr);
//        printArray(arr);

    //    findKthLargest(arr, 2);
//
//        int[] arrDutchFlag = {0,-1,1,1,1,-1,0,0,1,1,-1,0};
//        dutchFlagProblem(arrDutchFlag, 0);
//        printArray(arrDutchFlag);

        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(-5,0));
        intervals.add(new Interval(-2,1));
        intervals.add(new Interval(10,11));
        intervals.add(new Interval(5,7));
        intervals.add(new Interval(9,13));
        intervals.add(new Interval(3,6));

        ArrayList<Interval> merged = mergeIntervals(intervals);
    }
    //O(n)
    private static void printArray(int[] arr){
        for (int i : arr) {
            System.out.print( i + ", ");
        }
        System.out.println();
    }

    // O(1)
    private static void swap(int[] arr, int i , int j){
        if(arr == null || i < 0 || i > arr.length -1 || j < 0 || j > arr.length -1){
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //O(n^2)
    private static void bubbleSort(int[] arr){

        for(int i = 0 ; i < arr.length ; i ++){
            for(int j = 0 ; j < arr.length -i-1; j ++){
                if(arr[j] > arr[j +1] ){
                    swap(arr, j , j +1);
                }
            }
        }
    }

    // O(n^2)
    private static void selectionSort(int[] arr){
        for(int i = 0 ; i < arr.length; i ++){
            int minIndex = i;
            for(int j = i +1; j < arr.length ; j ++){
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }

            if(minIndex != i){
                swap(arr, i, minIndex);
            }
        }
    }



    private static void mergeSort(int[] arr){
        mergeSort(arr, 0, arr.length -1);
    }

   // O(n Log n)
    private static  void mergeSort(int[] arr, int low, int high){
        if(low >= high){
            return;
        }

        // O(log n )
        int mid = (low + high)/2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid +1, high);


        merge(arr, low, high);  // O(n)
    }

    // 3 * O(n) ~= O(n)
    private static void merge(int[] arr, int low, int high){

        int mid = (low + high)/2;

        int[] temp = new int[high - low + 1];

        int i = low;
        int j = mid +1;
        int index = 0;

        // we will compare first half and second half till either i or j
        // runs out of bound
        // O(n)
        while( i <= mid && j <= high){
            if(arr[i] < arr[j]){
                temp[index] = arr[i];
                i ++;
            }else{
                temp[index] = arr[j];
                j ++;
            }
            index ++;
        }

        // either i or j has run out of bound
        // we will copy remaining into temp

        // O(n)
        while(i <= mid){
            temp[index] = arr[i];
            i ++;
            index ++;
        }

        //O(n)
        while(j <= high){
            temp[index] = arr[j];
            j ++;
            index ++;
        }

        // O(n)
        i = low;
        for (int value : temp) {
            arr[i] = value;
            i++;
        }
    }


    private static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length -1);
    }

   // O (n Log n)
    private static  void quickSort(int[] arr, int low, int high){

        if(low < high){
            int pos = partition (arr, low, high);
            quickSort(arr, low, pos -1);
            quickSort(arr, pos +1, high);
        }
    }

    // O(n)
    private static  int partition(int[] arr, int low, int high){

        int pivot = arr[high]; // last element is the pivot
        int wall = low -1;
        for(int i = low; i < high; i ++){
            if(arr[i] < pivot){
                wall ++;
                swap(arr, i, wall);
            }
        }

        wall ++;
        swap(arr, high, wall);
        return wall;
    }

    // O(n Log n) Average case
    private static void findKthLargest(int[] arr, int k){
        findKthLargest(arr, k, 0, arr.length -1);
    }

    private  static  void findKthLargest(int[] arr, int k, int low, int high){
        if(low <= high){
            int pos = partition (arr, low, high);
            if( pos == arr.length -k ){
                System.out.println("Value = " + arr[pos]);
                return;
            }
            findKthLargest(arr, k ,  low, pos -1);
            findKthLargest(arr, k,  pos +1, high);
        }
    }

    // O( 2*n) ~= O(n)
    private static void countSort(int[] arr, int RANGE){
        int[] countArr = new int[RANGE];
        for (int j : arr) {
            countArr[j]++;
        }

        int index = 0 ;

        for(int i = 0 ; i < RANGE; i ++){

            while(countArr[i] > 0 ){
                arr[index] = i;
                index ++;
                countArr[i] --;
            }
        }
    }

    // O(n)
    private static void dutchFlagProblem(int[] arr, int pivot){
        int low = 0;
        int mid = 0;
        int high = arr.length -1;

        while(mid <= high){
            if(arr[mid] < pivot){
                swap(arr, mid, low);
                low ++;
                mid ++;
            }else if (arr[mid] > pivot){
                swap(arr, mid, high);
                high --;
            }else{
                mid ++;
            }
        }
    }

    // Sorting of intervals would be O(nLogn) and then finding intervals would be O(n)
    // Total complexity would be O(nLogn)
    private static ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervals){
        if(intervals == null || intervals.size() <= 1  )
            return  intervals;

        intervals.sort(new Comparator<>() {
            @Override
            public int compare(Interval x, Interval y) {
                return Integer.compare(x.start, y.start);
            }
        });




        Stack<Interval> stack =   new Stack<>();
        stack.push(intervals.get(0));
        for(int i = 1; i < intervals.size(); i ++){
            Interval top = stack.peek();
            Interval current = intervals.get(i);
            if(top.end < current.start){
                stack.push(intervals.get(i)); // Intervals dont intersect
            }
            else if(top.end < current.end) {
                top.end = current.end;
                stack.pop();
                stack.push(top);
            }
        }
         ArrayList<Interval> merged = new ArrayList<>();

        while(stack.size() != 0) {
            merged.add(stack.pop());
        }

        return  merged;
    }
}


