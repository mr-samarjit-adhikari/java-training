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
        int[] candies = new int[arr.length];//candies array
        long[] dp = new long[arr.length];
        long optCandiesCount = 0;

        dp[0] = 1;
        candies[0] = 1;

        for(int i=1;i<arr.length;i++){
            dp[i] = dp[i-1] + compAndDist(arr,i,candies);
        }
        optCandiesCount += dp[arr.length-1];

        dp[arr.length-1] = 0;
        for(int i=arr.length-1;i>=1;i--){
            dp[i-1] = dp[i] + reCompAndDist(arr,i,candies);
        }

        for(int i=0;i<candies.length;i++){
            System.out.println(candies[i]+" ");
        }
        System.out.println();

        optCandiesCount += dp[0];

        return optCandiesCount;
    }

    private int reCompAndDist(int[] arr, int i, int[] candies) {
        int allocatedCandies = 0;
        if(arr[i-1] > arr[i]){
            if(candies[i-1] <= candies[i]){
                int tmp = candies[i-1];
                candies[i-1] = candies[i] +1;
                allocatedCandies += (candies[i-1] - tmp);
            }
        }

        return allocatedCandies;
    }

    private int compAndDist(int[] arr, int i, int[] candies) {
        int allocatedCandies = 0;
        if(0 == candies[i]) {
            candies[i] = 1; //minimum allocation
            allocatedCandies++;
        }

        if(arr[i] > arr[i-1]){
            if(candies[i] <= candies[i-1]) {
                candies[i] = candies[i - 1] + 1;
                allocatedCandies += (candies[i]-1);
            }
        }
        else if(arr[i-1] > arr[i]){
            if(candies[i-1] <= candies[i]){
                candies[i-1] = candies[i-1] +1;
                allocatedCandies++;
            }

        }

        return allocatedCandies;
    }
}