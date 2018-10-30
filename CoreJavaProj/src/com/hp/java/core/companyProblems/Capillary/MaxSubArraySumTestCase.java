package com.hp.java.core.companyProblems.Capillary;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Maximum Sum - Kadane algorithm
 * Given an array A of N integers. Now, you have to output the sum of unique values of the maximum subarray sum of all
 * the possible subarrays of the given array A.
 * Note: Subarray means contiguous elements with atleast one element in it.
 *
 * Input Format
 *
 * The first line of the input contains a single integer N, the total number of elements in array A.
 * The next line of the input contains N space-separated integers representing the elements of the array.
 *
 * Output Format
 *
 * The only single line of the output should contain a single integral value representing the answer to the problem.
 *
 * Constraints
 * 1<= N <=2000
 * 0<=|Ai| <= 10^9
 *
 * Sample Input:
 * 4
 * 5 -2 7 -3
 *
 * Sample Output:
 * 17
 *
 */

public class MaxSubArraySumTestCase {

    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O: */
        Map<Integer,Integer> sumCache = new HashMap<Integer, Integer>(); /* <sum,repeat count>*/
        Scanner scanner = new Scanner(System.in);
        int arrayElementCount = scanner.nextInt();                 // Reading input from STDIN
        int[] arraysElements = new int[arrayElementCount];

        for(int index=0;index<arrayElementCount;index++){
            arraysElements[index] = scanner.nextInt();
        }

        MaxSubArraySumTestCase maxSubArraySumTestCase = new MaxSubArraySumTestCase();
        for(int index=0;index<arrayElementCount;index++){
            maxSubArraySumTestCase.calculateAndStoreMaxSubArraySum(arraysElements,index,arrayElementCount-1,sumCache);
        }

        int totalSum = 0;
        for(Integer sumValue:sumCache.keySet()){
            totalSum += sumValue.intValue();
        }
        System.out.println(totalSum);
    }

    private void calculateAndStoreMaxSubArraySum(int[] inputArray, int startIndex,int endIndex,
                                                 Map<Integer, Integer> sumCache) {
        int arrayElementCount = endIndex - startIndex+1;
        int[] dp = new int[arrayElementCount];
        int sumPrev = dp[0] = inputArray[startIndex];

        if(arrayElementCount==1){
            CheckAndSetToCache(sumCache,dp[0]);
            return;
        }

        for (int index=1;index<arrayElementCount;index++){
            int sumCurr = sumPrev+inputArray[startIndex+index];

            dp[index] = Math.max(dp[index-1],sumCurr);

            sumPrev = sumCurr;
            CheckAndSetToCache(sumCache,dp[index]);
        }
    }

    private void CheckAndSetToCache(Map<Integer, Integer> sumCache, int key) {
        Integer sumValue = sumCache.get(key);
        if(sumValue == null){
            sumCache.put(key,1);
        }
        else{
            sumCache.put(key,sumValue+1);
        }
    }

}
