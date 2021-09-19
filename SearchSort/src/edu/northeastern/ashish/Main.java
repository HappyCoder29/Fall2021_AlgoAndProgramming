package edu.northeastern.ashish;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        int[] arr = {6,5,3,1,8,7,2,4};
//
//        printArray(arr);
////        bubbleSort(arr);
////        printArray(arr);
////        selectionSort(arr);
////        printArray(arr);
//
////        mergeSort(arr);
////        printArray(arr);
//
////        quickSort(arr);
////        printArray(arr);
//
//    //    findKthLargest(arr, 2);
////
////        int[] arrDutchFlag = {0,-1,1,1,1,-1,0,0,1,1,-1,0};
////        dutchFlagProblem(arrDutchFlag, 0);
////        printArray(arrDutchFlag);
//
//        ArrayList<Interval> intervals = new ArrayList<>();
//        intervals.add(new Interval(-5,0));
//        intervals.add(new Interval(-2,1));
//        intervals.add(new Interval(10,11));
//        intervals.add(new Interval(5,7));
//        intervals.add(new Interval(9,13));
//        intervals.add(new Interval(3,6));
//
//        ArrayList<Interval> merged = mergeIntervals(intervals);


//        int[] arr = {5,7,7,5,3,5,5,1,1,1};
//        majorityElement(arr);

//        int[] arr = {1,2,3,4,5};
//        rotateArray(arr, 2);
//        printArray(arr);

//
//        int[] arr = {5,3,7,1,4,8,2,9,10, -1};
//        //wiggleSortOrderNLogN(arr);
//        wiggleSortOrderN(arr);
//        printArray(arr);

//        int[] arr1 = {1,3,5,7,9};
//        int[] arr2 = {2,4,6,8,10};
//        int[] merged = mergeSortedArrays(arr1, arr2);
//        printArray(merged);

//        int[] arr1 = {1,5,7,9,13};
//        int[] arr2 = {-12, 2,5, 8};
//        int[] arr3 = {45,65,78};
//        int[] arr4 = {99,210,800, 964,1234};
//        int[] arr5 = {-23,-12,3,10};
//        ArrayList<int[]> list = new ArrayList<>();
//        list.add(arr1);
//        list.add(arr2);
//        list.add(arr3);
//        list.add(arr4);
//        list.add(arr5);
//
//        int[] merged = mergeKSortedArrays(list);
//        printArray(merged);

//        int[] arr = {5,7,8,12,13,23,54};
//
//        System.out.println( binSearchRecursive(arr, 23) );

        int[] arr = {1,1,1,2,2,3,5,5,5,5,7,9,9};
        System.out.println(numOfOccurances(arr, 6));

    }



    //region Sept 12 2021
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

    /// endregion



    /// region class on September 19th 2021


    // O(2n) ~= O(n)
    public static void majorityElement(int[] arr){
        if(arr == null || arr.length == 0){
            return;
        }
        if(arr.length == 1){
            System.out.println("Majority element = " + arr[0]);
            return;
        }

        int majorityElement = findMajority(arr);

        int count = 0;
        for (int i : arr) {
            if(i == majorityElement){
                count ++;
            }
        }

        if(count >= arr.length/2){
            System.out.println("Majority Element = " + majorityElement);
        }else{
            System.out.println("No majority element");
        }
    }

    // O(n)
    private static int findMajority(int[] arr){

        int majorityElement = arr[0];
        int count = 1;

        for(int i = 1; i < arr.length; i ++){
            if(arr[i] == majorityElement){
                count ++;
            }else{
                count --;
                if(count == 0){
                    majorityElement = arr[i];
                    count = 1;
                }
            }
        }

        return majorityElement;

    }

    //O(n)
    private static void reverseArray(int[] arr, int start, int end){
        if(start < 0 || start >= arr.length  || end <0 || end >= arr.length || start >= end){
            return;
        }

        while(start < end){
            swap(arr, start , end);
            start ++;
            end --;
        }
    }

    // O(2n) ~= O(n)
    private static void rotateArray(int[] arr, int numTimes){
        numTimes = numTimes % arr.length;

        reverseArray(arr, 0, arr.length -1);
        reverseArray(arr, 0, numTimes-1);
        reverseArray(arr, numTimes, arr.length -1);

    }

    // O(nLogn)
    private static  void wiggleSortOrderNLogN(int[] arr){
        if(arr == null || arr.length <=1){
            return;
        }
        Arrays.sort(arr);

        for(int i = 0 ; i < arr.length ; i = i +2){
            swap(arr, i, i +1);
        }
    }


    // O(n)
    private static void wiggleSortOrderN(int[] arr){
        if(arr == null || arr.length <=1){
            return;
        }
        boolean bValue = true;
        for(int i = 0 ; i < arr.length -1 ; i ++){
            if(bValue && arr[i] < arr[i+1]){
                swap(arr, i, i +1);
            }else if(!bValue && arr[i] > arr[i+1] ){
                swap(arr, i, i +1);
            }
            bValue = !bValue;
        }
    }

    // O(n)
    private static int[] mergeSortedArrays(int[] arr1, int[] arr2){
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

    // O(k * n* log(K*n)
    private static int[] mergeKSortedArrays(ArrayList<int[]> listOfArrays){
        int countMergedArray = 0;

        //O(k)
        for (int[] arr: listOfArrays) {
            countMergedArray += arr.length;
        }

        int[] merged = new int[countMergedArray];

        // O(k * n* log(K*n)
       // PriorityQueue<Integer> queue = new PriorityQueue<>();
        MinHeap queue = new MinHeap();
        for (int[] arr: listOfArrays) {
            for (int i : arr) {
                queue.add(i);
            }
        }
        // My priority queue has all the integers inside the queue

        // O( K * n*  log(k*n) )
        for(int i = 0 ; i < merged.length ; i++){
            merged[i] = queue.remove();
        }

        return  merged;
    }


    public static boolean binSearchIterative(int[] arr, int x){
        int low =0;
        int high = arr.length -1;
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] < x){
                low = mid +1;
            }else if ( arr[mid] > x ){
                high = mid - 1;
            }else {
                return true;
            }
        }
        return false;
    }


    public static boolean binSearchRecursive(int[] arr, int x){
        return  binSearchRecursive(arr, x, 0, arr.length -1);
    }

    private static boolean  binSearchRecursive(int[] arr, int x, int low, int high){

        if(low > high){
            return  false;
        }
        int mid = (low + high)/2;

        if(arr[mid] < x){
            return  binSearchRecursive(arr, x, mid +1, high);
        }else if (arr[mid] > x){
            return  binSearchRecursive(arr, x, low, mid -1);
        }

        return  true;

    }


    public static  int numOfOccurances(int[] arr, int x){
        return  numOfOccurances(arr, x, 0, arr.length -1);
    }

    private static int numOfOccurances(int[] arr, int x, int low, int high){

        if(low > high){
            return  0;
        }

        if(arr[low] > x){
            return 0;
        }
        if(arr[high] < x){
            return 0;
        }
        if(arr[low] == x && arr[high] == x){
            return  (high -low + 1);
        }

        int mid = (high + low )/2;
        if(arr[mid] < x){
            return  numOfOccurances(arr, x, mid+1, high);
        }else if (arr[mid] > x){
            return  numOfOccurances(arr, x, low, mid-1);
        }else {
            return 1 + numOfOccurances(arr, x, low, mid-1) +
                    numOfOccurances(arr, x, mid + 1, high);
        }


    }






















    /// endregion


}


