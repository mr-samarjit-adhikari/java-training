package com.hp.java.core.companyProblems.HackerRank;

import java.util.Scanner;

public class CandiesTestCase {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();

        int[] arr = new int[arraySize];
        for(int i=0;i<arraySize;i++){
            arr[i] = scanner.nextInt();
        }

        CandiesTestCase testCase = new CandiesTestCase();
        long result = testCase.solve(arr);
        System.out.println(result);
    }

    private long solve(int[] arr) {
        int candies = 0;
        int[] dp = new int[arr.length]; //candies array

        dp[0] = 1;candies++;

        for(int i=1;i<arr.length;i++){
            if(arr[i] > arr[i-1]){
                dp[i] = dp[i-1] +1;
            }
            else{
                dp[i] = 1;
            }
            candies += dp[i];
        }

        return candies;
    }
}
