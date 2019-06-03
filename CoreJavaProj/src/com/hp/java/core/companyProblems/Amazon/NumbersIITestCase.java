package com.hp.java.core.companyProblems.Amazon;

import java.util.Scanner;

import static java.lang.Math.max;

public class NumbersIITestCase {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int tc = scanner.nextInt();

        while(tc>0){
            NumbersIITestCase testCase = new NumbersIITestCase();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int n = scanner.nextInt();

            long result = testCase.solve(a,b,n);
            System.out.println(result);
            tc--;
        }
        scanner.close();
    }

    private long solve(int a, int b, int n) {
        long dp[][] = new long[2][n+1];

        dp[0][0] = a;
        dp[1][0] = b;

        for(int i=1;i<=n;i++){
            if(dp[0][i-1] <= dp[1][i-1]){
                dp[0][i] = dp[0][i-1] + dp[0][0];
                dp[1][i] = dp[1][i-1];
            }else{
                dp[0][i] = dp[0][i-1];
                dp[1][i] = dp[1][i-1] + dp[1][0];
            }
        }

        return max(dp[0][n],dp[1][n]);
    }
}
