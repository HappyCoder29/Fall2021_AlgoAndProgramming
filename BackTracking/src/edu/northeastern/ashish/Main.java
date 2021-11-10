package edu.northeastern.ashish;

import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        //generateBinarySequence(2);
       // combinations("ABC", 4);

        //generateAllSubsets("ABC");
       // int[] coins = {1,5,3,2,1,1,6,7,10};
       // generateSumEqualToK(coins, 5);

       // permutations("ABC");
        //generateAllValidParenthesis(3);

        int[] states = {9,3,11,6,55,9,7,3,3,29,16,4,4,20,11,6,6,8,8,4,10,11,16,10,6,10,3,5,
                        6,4,14,5,29,15,3,18,7,7,20,4,9,3,11,38,6,3,13,12,5,10,3};
        getNumOfWaysDeadlockInElectoralVotes(states);
    }

    private static void generateBinarySequence(int n){
        if(n <= 0){
            return;
        }
        int current = 0;
        int[] result = new int[n];
        result[0] = Integer.MIN_VALUE;
        result[1] = Integer.MIN_VALUE;

        generateBinarySequence(result, current);
    }

    private static void generateBinarySequence(int[] result, int current){
        if(current == result.length){
            System.out.println(Arrays.toString(result));
            return;
        }

        for(int i = 0 ; i < 2 ; i++){
            result[current] = i;
            generateBinarySequence(result, current +1 );
        }
    }

    private static void generateTernarySequence(int size){
        if(size <= 0 ){
            return;
        }
        int current = 0;
        int[] result = new int[size];
        generateTernarySequence(result, current);
    }

    private static void generateTernarySequence(int[] result, int current){
        if(current == result.length){
            System.out.println(Arrays.toString(result));
            return;
        }
        for(int i = 0 ; i < 3 ; i++){
            result[current] = i;
            generateTernarySequence(result, current +1 );
        }
    }

    private static void generateMarySequence(int size, int m){
        if(size <= 0 ){
            return;
        }
        int current = 0;
        int[] result = new int[size];
        generateMarySequence(result, current, m);
    }

    private static void generateMarySequence(int[] result, int current, int m){
        if(current == result.length){
            System.out.println(Arrays.toString(result));
            return;
        }
        for(int i = 0 ; i < m ; i++){
            result[current] = i;
            generateMarySequence(result, current +1 , m);
        }
    }

    public static void combinations(String str, int size){
        if(str == null || str.length() == 0 || size<= 0){
            return;
        }

        int[] result = new int[size];
        int current = 0;
        combinations(result, 0, str);

    }

    public static void combinations(int[] result, int current, String str){
        if(current == result.length){
            printCombinations(str, result);
            return;
        }
        for(int i = 0 ; i < str.length(); i ++){
            result[current] = i;
            combinations(result, current +1, str);
        }
    }

    private static void printCombinations(String str, int[] result){
        char[] arr = str.toCharArray();
        for(int i = 0 ; i < result.length; i ++){
            System.out.print(arr[result[i]] + " ");
        }
        System.out.println();
    }

    private static void generateAllSubsets(String str){
        if(str == null || str.length() == 0){
            return;
        }
        int[] result = new int[str.length()];
        int current = 0;
        generateAllSubsets(result, current, str);
    }

    private static void generateAllSubsets(int[] result, int current, String str){

        if(current == result.length){
            printSubset(result, str);
            return;
        }

        for(int  i = 0 ; i < 2; i ++){
            result[current] = i;
            generateAllSubsets(result, current+1, str);
        }
    }

    private static void printSubset(int[] result, String str){
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for(int i = 0 ; i < result.length; i ++){
            if(result[i] == 1){
                sb.append(str.charAt(i) + ", ");
            }
        }
        sb.append(" }");
        System.out.println(sb.toString());
    }

    private static void generateSumEqualToK(int[] coins, int k){
        if( coins == null || coins.length == 0 ){
            return;
        }

        int[] result = new int[coins.length];
        int current = 0;
        generateSumEqualToK(result, 0, coins, k);

    }
    private static void generateSumEqualToK(int[] result, int current,  int[] coins, int k){

        if(current == result.length){
            printSumEqualToK(coins, result, k);
            return;
        }
        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            generateSumEqualToK(result, current+1, coins, k);
        }
    }
    private static void printSumEqualToK(int[] coins , int[] result, int k){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("{ ");
        for(int i = 0 ; i < result.length; i ++){
            if(result[i] == 1){
                sb.append(coins[i] + ", ");
                count += coins[i];
            }
        }
        sb.append(" }");
        if(count == k ){
            System.out.println(sb.toString());
        }
    }

    private static void permutations(String str){
        if(str == null || str.length() == 0){
            return;
        }
        int[] result = new int[str.length()];
        int current = 0;
        permutations(result, current, str);
    }
    private static void permutations(int[] result, int current, String str){
        if(current == result.length){
            printCombinations(str, result);
            return;
        }
        for(int i = 0 ; i < str.length(); i ++) {
            // check if adding current value will make the string invalid
            if(isValidPermutation(result, current, i)){
                result[current] = i;
                permutations(result, current +1, str);
            }

        }
    }


    private static boolean isValidPermutation(int[] result, int current, int numToAdd){

        for(int i = 0 ; i < current; i ++){
            if(result[i] == numToAdd){
                return false;
            }
        }
        return true;

    }

    private static void generateAllValidParenthesis(int n){
        char[] arr = new char[ 2* n ];
        generateAllValidParenthesis(arr, 0);

    }
    private static void generateAllValidParenthesis(char[] arr, int current){
        if(current == arr.length){
            if(isValidParenthesis(arr)){
                printParenthesis(arr);
            }
            return;
        }
        for(int i = 0 ; i < 2; i ++){
            char ch = i == 0 ? '(' : ')';
            arr[current] = ch;
            generateAllValidParenthesis(arr, current +1);
        }
    }

    private static void printParenthesis(char[] arr){
        for (char ch : arr) {
            System.out.print(ch + " ");
        }
        System.out.println();
    }
    /// char array has only ( and )
    static boolean isValidParenthesis(char[] arr){
        int balance = 0;
        for (char ch : arr) {
            if(ch == '('){
                balance++;
            }else{
                balance--;
            }
            if(balance < 0){
                return false;
            }
        }
        return  balance == 0;
    }

    static boolean isValidParenthesisWithStack(char[] arr){
        Stack<Character> stack = new Stack<>();

        for (Character ch : arr) {
            if(ch == '('){
                stack.push('(');
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private static void getNumOfWaysDeadlockInElectoralVotes(int[] states){
        if(states == null || states.length == 0){
            return;
        }

        int[] result = new int[states.length];
        int current = 0;
        getNumOfWaysDeadlockInElectoralVotes(result, current, states);

    }

    private  static  void  getNumOfWaysDeadlockInElectoralVotes(int[] result, int current, int[] states){
        if(current == states.length){
            // Calculate the number of votes for one party and see if it is equal to 269
            System.out.println(Arrays.toString(result));
            return;
        }
        for(int i = 0 ; i < 2; i ++){
            if(! didBidenExceed269(result, states, current)){
                result[current] = i;
                getNumOfWaysDeadlockInElectoralVotes(result, current + 1, states);
            }

        }
    }


    private static boolean didBidenExceed269(int[] result, int[] states, int current){
        int count = 0;
        for(int i = 0 ; i < current; i ++){
            if(result[i] == 0 ){ // biden = 0
                count = count + states[i];
            }
            if(count > 269){
                return true;
            }
        }
        return false;
    }




}
