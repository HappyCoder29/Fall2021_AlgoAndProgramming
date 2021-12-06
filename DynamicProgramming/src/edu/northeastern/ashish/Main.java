package edu.northeastern.ashish;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
       // System.out.println(findFibMemoization(56));

//        int[][] matrix = {
//                {5,8,12,2,15},
//                {3,1, 8,6, 7},
//                {4,1, 2,5, 9},
//                {3,15,2,7, 6},
//                {1,5, 4,2,1}
//
//                 };
//
//        System.out.println(getMinPath(matrix));
//        int[] arr = {-2,1,-3,4,-1,2,1,-5, 7};
//        System.out.println(kadane(arr) );
//
//        int[] arr = {0,1,0,3,2,3};
//        System.out.println(longestIncreasingSubSequence(arr));

//        int[] jumps = {1,3,5,8,9,2,6,7};
//        System.out.println(minJumpsToReachEnd(jumps));
       // System.out.println(tribonacciTabulization(5));

//        int[] arr = {1,3,2,5,6,10,4};
//        System.out.println(minCostClimbingStairs(arr));

      //  System.out.println(getMaxMatchingSubstring("xybcdz", "abcdxye"));
        int[] coins = {2,4,5};
        System.out.println(numberOfWaysToReachTotal(coins, 11));


    }


    // region Class 1

    // O(2^n)
    private static int findFib(int n){
        if(n <= 1){
            return n;
        }
        return findFib(n-1) + findFib(n-2);
    }

    private static Long findFibMemoization(int n){
        if(n <= 1){
            return (long)n;
        }
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, 0L);
        map.put(1, 1L);
        return findFibMemoization(n, map);

    }


    //O(n)
    private static Long findFibMemoization(int n, HashMap<Integer, Long> map){

        if(!map.containsKey(n)){
            map.put(n, findFibMemoization(n-1, map) + findFibMemoization(n-2, map));
        }
        return map.get(n);
    }

    // O(n)
    private static long findFibTabulization(int n){
        if(n <= 1){
            return n;
        }
        long[] arr = new long[n+1];
        arr[0] = 0;
        arr[1] = 1;
        for(int i = 2; i < arr.length; i ++){
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[arr.length-1];
    }

    private static int getMinPath(int[][] matrix){

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] table = new int[rows][cols];
        table[0][0] = matrix[0][0];
        for(int i = 1 ; i < rows; i ++){
            table[i][0] = table[i-1][0] + matrix[i][0];
        }

        for(int i = 1 ; i < cols; i ++){
            table[0][i] = table[0][i-1] + matrix[0][i];
        }

        for(int i = 1 ; i < rows; i ++){
            for(int j = 1; j < cols; j ++){
                table[i][j] = matrix[i][j] + Math.min(table[i-1][j-1],
                        Math.min(table[i-1][j], table[i][j-1]));
            }
        }

        return table[rows-1][cols-1];

    }

    private static int kadaneBruteForce(int[] arr){
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0 ; i < arr.length; i ++){
            int sum = 0;
            for(int j = i; j < arr.length; j ++){
                sum += arr[j];
            }
            if(maxSum< sum){
                maxSum = sum;
            }
        }
        return maxSum;
    }

    private static int kadane(int[] arr){
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        int startIndex = 0;
        int endIndex = 0;
        for(int i = 0 ; i < arr.length; i ++){
            sum = sum + arr[i];
            if(maxSum < sum){
                maxSum = sum;
                endIndex = i;
            }
            if(sum < 0){
                sum = 0 ;
                startIndex = i+1;
            }

        }

        return maxSum;
    }

    // O(n^2)
    private static int longestIncreasingSubSequence(int[] arr){
        if(arr == null || arr.length <=0){
            return 1;
        }
        if(arr.length == 1){
            return 1;
        }

        int[] lis = new int[arr.length];
        Arrays.fill(lis, 1);

        int[] indexes = new int[arr.length];
        for(int i = 0 ; i < arr.length; i ++){
            indexes[i] = i;
        }

        int maxLIS = 1;
        int indexMax = -1;

        for(int i = 0 ; i < arr.length-1 ; i ++){
            for(int j = i+1; j < arr.length; j ++){
                if(arr[j] > arr[i]){
                    // If lis of i + 1 is bigger than lis of j then update the lis j Value
                    // Update the index too if needed
                    if(lis[i] + 1 > lis[j]){
                        lis[j] =  lis[i]+1;
                        indexes[j] = i;
                    }
                }

                if(maxLIS < lis[j]){
                    maxLIS = lis[j];
                    indexMax = j;
                }
            }
        }
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i < maxLIS; i ++){
            stack.push(arr[indexMax]);
            indexMax = indexes[indexMax];
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + ", ");
        }
        System.out.println();

        return maxLIS;
    }

    // O(n^2)
    private static int minJumpsToReachEnd(int[] arr){

        if(arr == null || arr.length <=0){
            return 1;
        }
        if(arr.length == 1){
            return 1;
        }

        int[] jumps = new int[arr.length];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] =0;
        int[] indexes = new int[arr.length];
        Arrays.fill(indexes, -1);
        indexes[0] = 0;

        for(int i = 0 ; i < arr.length-1; i ++){
            for(int j = i + 1 ; j < arr.length; j ++){
                // can we make the jump
                if(arr[i] + i >= j){
                    // If we can reach current j value from i
                    // better than before we update the values
                    if(jumps[j] > jumps[i] + 1){
                        jumps[j] = jumps[i] +1;
                        indexes[j] = i;
                    }
                }
            }
        }

        // If the last value is infinite this means we cannot reach end
        if(jumps[jumps.length -1] == Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }

        int minJumps = jumps[jumps.length -1];
        Stack<Integer> stack = new Stack<>();
        int indexMax = arr.length-1;
        for(int i = 0 ; i < minJumps; i ++){
            stack.push(arr[indexMax]);
            indexMax = indexes[indexMax];
        }
        stack.push(arr[0]);
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + ", ");
        }
        System.out.println();



        return jumps[arr.length-1];



    }
    // endregion



    // region Class 2

    private static int tribonacciRecursive(int n){

        if( n == 0){
            return  0;
        }
        if(n == 1 || n == 2){
            return 1;
        }

        return  tribonacciRecursive(n-3) + tribonacciRecursive(n-2) + tribonacciRecursive(n-1);

    }

    private static int tribonacciTabulization(int n){
        if( n == 0){
            return  0;
        }
        if(n == 1 || n == 2){
            return 1;
        }

        int[] arr = new int[n+1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;

        for(int i = 3; i < arr.length; i ++){
            arr[i] = arr[i-1] + arr[i-2] + arr[i-3];
        }
        return arr[arr.length -1];
    }

    private static int houseRobber(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        if(arr.length == 1){
            return arr[0];// there is only one house to rob
        }
        if(arr.length == 2){
            return Math.max(arr[0], arr[1]);// If there are 2 houses we will get the max of two houses
        }

        // we have more than 2 houses
        int[] maxArray = new int[arr.length];
        maxArray[0] = arr[0];
        maxArray[1] = Math.max(arr[0], arr[1]);
        for(int i = 2; i < arr.length; i ++){
            maxArray[i] = Math.max(arr[i] + maxArray[i-2], maxArray[i-1]);
        }

        int len = arr.length;
        int index = len -1;
        if(maxArray[index] == maxArray[index -1]){
            index = index -1;
        }

        while(index > 0){
            if(maxArray[index] == maxArray[index -1]){
                System.out.print(arr[index -1]  + ",");
                index = index -1;
            }else{
                System.out.print(arr[index]  + ",");
                index = index -2;
            }
        }
        if(index == 0 ){
            System.out.print(arr[index]  );
        }

        System.out.println();


        return maxArray[arr.length -1];


    }

    private static int minCostClimbingStairs(int[] arr){
        // declare an array
        int maxArray[] = new int[arr.length];

        // base case
        if (arr.length == 1)
            return arr[0];

        maxArray[0] = arr[0];
        maxArray[1] = arr[1];

        // iterate for finding the cost
        for (int i = 2; i < arr.length; i++)
        {
            maxArray[i] = Math.min(maxArray[i - 1],
                    maxArray[i - 2]) + arr[i];
        }

        return Math.min(maxArray[arr.length - 2],
                maxArray[arr.length - 1]);
    }


    private static int getMaxMatchingSubstring(String str1, String str2){
        if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0){
            return 0;
        }

        int rows = str1.length() + 1;
        int cols=  str2.length() + 1;

        int[][] matrix = new int[rows ][cols];

        int maxMatching = 0;
        int indexX = -1;
        for(int i = 1 ; i < rows; i ++){
            for(int j = 1; j < cols ; j ++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    matrix[i][j] = matrix[i-1][j-1] + 1;// Take the diagonal matching and add one to it
                }else{
                    matrix[i][j] = 0;
                }
                if(maxMatching < matrix[i][j]){
                    maxMatching  = matrix[i][j];
                    indexX = i;
                }
            }
        }

        String maxSubstring = str1.substring(indexX - maxMatching, indexX);

        return maxMatching;
    }

    private static int minCoinsToReachTotal(int[] coins, int total){
        if(coins == null || coins.length == 0){
            return 0;
        }
        if(total < 0){
            return 0;
        }

        int rows = coins.length +1;
        int cols = total +1;
        int[][] matrix = new int[rows][cols];

        // fil;l the 0'th col by 0 and remaining as Infinite
        for(int i = 0 ; i < rows; i++ ){
            for(int j = 0 ; j < cols; j ++){
                if(j == 0){
                    matrix[i][j] = 0;
                }else{
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for(int i = 1 ; i < rows; i ++){
            int coinValue = coins[i-1];
            for(int j = 1; j < cols; j ++){

                if(j < coinValue){
                    matrix[i][j] = matrix[i-1][j];// Till the current coin denomination is not reached
                    // copy the top value
                }else{
                    if(matrix[i][j- coinValue] != Integer.MAX_VALUE){
                        matrix[i][j] = Math.min( matrix[i-1][j], matrix[i][j- coinValue] + 1);
                        // Get the min of Top or same row - coin denomination value + 1
                    }else{
                        matrix[i][j] = matrix[i-1][j];// Get the top one
                    }
                }
            }
        }



        return  matrix[rows-1][cols-1];
    }


    private static int numberOfWaysToReachTotal(int[] coins, int total) {
        if(coins == null || coins.length == 0){
            return 0;
        }
        if(total < 0){
            return 0;
        }

        int rows = coins.length +1;
        int cols = total +1;
        int[][] matrix = new int[rows][cols];
        // fill the 0'th col by 1 and remaining as Infinite
        for(int i = 0 ; i < rows; i++ ){
            for(int j = 0 ; j < cols; j ++){
                if(j == 0){
                    matrix[i][j] = 1;// Since there is one way to fulfill giving back 1 $
                }else{
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for(int i = 1 ; i < rows; i ++){
            int coinValue = coins[i-1];
            for(int j = 1; j < cols; j ++){

                if(j < coinValue){
                    matrix[i][j] = matrix[i-1][j];// Till the current coin denomination is not reached
                    // copy the top value
                }else{
                    // get the sum of top value and the left value if eityher of them is Infinite
                    // dont add that value
                    int sum = matrix[i-1][j] != Integer.MAX_VALUE ? matrix[i-1][j] : 0;
                    sum += matrix[i][j- coinValue] != Integer.MAX_VALUE ? matrix[i][j- coinValue] : 0;
                    if(sum != 0) {
                        matrix[i][j] = sum;
                    }
                }
            }
        }

        return matrix[rows-1][cols-1];


    }

        // endregion






}
