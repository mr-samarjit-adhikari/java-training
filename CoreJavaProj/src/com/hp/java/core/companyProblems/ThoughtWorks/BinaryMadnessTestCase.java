package com.hp.java.core.companyProblems.ThoughtWorks;


import java.util.Scanner;

/**
 * Binary Madness : unsolved
 *
 * Given a non-negative integer, find the count of substrings with an odd number of 0s in its binary representation and
 * the count of substrings with an odd number of 1s in its binary representation.
 * Input Format
 *
 * First line of input t denotes the number of test cases. Next t lines follow each containing a non-negative integer n.
 * Output Format
 *
 * For each test case print the count of substrings with an odd number of 0s in its binary representation and the count
 * of substrings with an odd number of 1s in its binary representation.
 *
 * Sample Input:
 * 13
 * 0
 * 1
 * 2
 * 10
 * 17
 * 32
 * 33
 * 127
 * 341
 * 455
 * 496
 * 992
 * 1000
 *
 * Sample Output
 *
 * 1 0
 * 0 1
 * 2 2
 * 6 6
 * 9 8
 * 12 6
 * 10 10
 * 0 16
 * 24 25
 * 25 24
 * 16 21
 * 24 24
 * 24 28
 *
 */

public class BinaryMadnessTestCase {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();

        while (testCaseCount > 0) {

            BinaryMadnessTestCase testCase = new BinaryMadnessTestCase();
            String inputString = Long.toBinaryString(scanner.nextLong());
            int oddOneCount = testCase.countOdd1s(inputString);
            //int oddZeroCount = testCase.countOdd0s(inputString);
            //System.out.println(oddOneCount + " " + oddZeroCount);
            System.out.println(oddOneCount);

            testCaseCount--;
        }

    }

    private int countOdd1s(String inputInteger) {
        int N = inputInteger.length();
        int oddSum = 0;
        int[] subStrFreq = new int[N + 1];

        int firstCharVal = Character.getNumericValue(inputInteger.charAt(0));
        if(firstCharVal==0){
            subStrFreq[0] = 0;
            oddSum = 0;
        }
        else{
            subStrFreq[0] = 1;
            oddSum = 1;
        }

        for (int i = 1; i < N; i++) {
            oddSum = oddSum + Character.getNumericValue(inputInteger.charAt(i));

            if(oddSum %2 !=0){
                subStrFreq[i] = subStrFreq[i-1] +1;
            }else{
                subStrFreq[i] = subStrFreq[i-1];
            }
        }

        return subStrFreq[N-1];
    }
}


/*
Stdin
No.	Content
1.	25
2.	0
3.	1
4.	2
5.	10
6.	17
7.	32
8.	33
9.	127
10.	341
11.	455
12.	496
13.	992
14.	1000
15.	430510914326
16.	8470634074737128636070224
17.	9893020956
18.	3846435266552999150
19.	977354604222
20.	27055031064
21.	6702565932198233123811
22.	427738644490
23.	65146
24.	89623387
25.	3773561366613203692478122629384
26.	2566517506689656989470

Expected Stdout
No.	Content
1.	1 0
2.	0 1
3.	2 2
4.	6 6
5.	9 8
6.	12 6
7.	10 10
8.	0 16
9.	24 25
10.	25 24
11.	16 21
12.	24 24
13.	24 28
14.	399 399
15.	1620 1760
16.	286 306
17.	986 992
18.	420 420
19.	315 275
20.	1368 1353
21.	391 375
22.	42 72
23.	192 192
24.	2610 2542
25.	1558 1558
 */