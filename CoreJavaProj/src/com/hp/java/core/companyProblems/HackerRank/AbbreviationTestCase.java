package com.hp.java.core.companyProblems.HackerRank;

import java.util.Scanner;

public class AbbreviationTestCase {
    public enum Match{
        CHAR_MATCH,
        CHAR_SKIP,
        CHAR_MISMATCH,
        UNKNOWN,
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        AbbreviationTestCase testCase = new AbbreviationTestCase();
        for(int count=0;count<testCaseCount;count++){
            String str1 = scanner.nextLine();
            String str2 = scanner.nextLine();
            String result = testCase.abbreviation(str1, str2);
            System.out.println(result);
        }
    }

    private String abbreviation(String str1, String str2) {
        final int MAX = 1000;
        final boolean[][] dp = new boolean[MAX+1][MAX+1];

        char[] a = str1.toCharArray();
        char[] b = str2.toCharArray();
        int A = a.length;
        int B = b.length;

        for (int i = 0; i <= A; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= A; i++) {
            for (int j = 1; j <= B; j++) {
                dp[i][j] = false;
                if (Character.isLowerCase(a[i-1])) {
                    dp[i][j] |= dp[i-1][j];
                }
                if (Character.toUpperCase(a[i-1]) == b[j-1]) {
                    dp[i][j] |= dp[i-1][j-1];
                }
            }
        }
        return(dp[A][B] ? "YES" : "NO");
    }
}
