package com.hp.java.core.companyProblems.Samsung;

import java.util.Scanner;



/**
 * The King's March
 *
 * You’re given a chess board with dimension n x n.
 * There’s a king at the bottom right square of the board marked with s.
 * The king needs to reach the top left square marked with e.
 * The rest of the squares are labeled either with an integer p (marking a point) or with x marking an obstacle.
 * Note that the king can move up, left and up-left (diagonal) only.
 * Find the maximum points the king can collect and the number of such paths the king can take in order to do so.
 *
 * Input Format
 *
 * The first line of input consists of an integer t.
 * This is the number of test cases. Each test case contains a number n which denotes the size of board.
 * This is followed by n lines each containing n space separated tokens.
 *
 * Output Format
 *
 * For each case, print in a separate line the maximum points that can be collected and the number of paths available in
 * order to ensure maximum, both values separated by a space. If e is unreachable from s, print 0 0.
 * Sample Input
 * 3
 * 3
 * e 2 3
 * 2 x 2
 * 1 2 s
 * 3
 * e 1 2
 * 1 x 1
 * 2 1 s
 * 3
 * e 1 1
 * x x x
 * 1 1 s
 *
 * Sample Output
 * 7 1
 * 4 2
 * 0 0
 */

public class KingsMarchTestCase {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();

        while(testCaseCount>0){

            KingsMarchTestCase testCase = new KingsMarchTestCase();
            int n = scanner.nextInt();
            String[][] inputArray = new String[n][n];
            testCase.readInputArray(scanner,inputArray,n);
            testCase.calcMaxDistAndPathCount(inputArray,n);

            testCaseCount--;
        }
    }

    private void calcMaxDistAndPathCount(String[][] inputArray, int n) {
        int maxDist = 0;
        int maxDistPathCount = 0;
        int[][] dp = new int[n][n];

        //initialize
        for(int col=n-1;col>=0;col--){
            if("s".equals(inputArray[n-1][col]) ||
                    "x".equals(inputArray[n-1][col]) ){
                dp[n - 1][col] = 0;
            }else{
                dp[n-1][col] = Integer.parseInt(inputArray[n-1][col])+(dp[n-1][col+1]);
            }
        }
        for(int row=n-1;row>=0;row--){
            if("s".equals(inputArray[row][n-1]) ||
                    "x".equals(inputArray[row][n-1]) ){
                dp[row][n-1] = 0;
            }else{
                dp[row][n-1] = Integer.parseInt(inputArray[row][n-1])+(dp[row+1][n-1]);
            }
        }

        for(int row=n-2;row>=0;row--){
            for(int col=n-2;col>=0;col--){
                if(inputArray[row][col].equals("x")){
                    dp[row][col] = 0;
                }else{
                    int inputInt = Integer.parseInt(inputArray[row][col]);
                    int val1 = inputInt + dp[row][col+1];
                    int val2 = inputInt + dp[row+1][col];
                    int val3 = inputInt + dp[row+1][col+1];
                    dp[row][col] = Math.max(Math.max(val1,val2),val3);
                    maxDistPathCount += numberOfPaths(dp[row][col],inputInt,val1,val2,val3);
                }
            }
        }

        maxDist = dp[0][0];

        System.out.println(maxDist+" "+maxDistPathCount);
    }

    private int numberOfPaths(int target,int input,int val1,int val2,int val3)
    {
        int pathCount = 0;
        if(target == (input+val1)) pathCount++;
        if(target == (input+val2)) pathCount++;
        if(target == (input+val3)) pathCount++;

        return pathCount;
    }

    private void readInputArray(Scanner scanner, String[][] inputArray,int size) {
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                inputArray[row][col] = scanner.next();
            }
        }
        inputArray[0][0] = "0";
    }
}

/*
[Samsung]

Stdin
No.	Content
	25
1.	2
	e 1
	1 s
2.	2
	e x
	x s
3.	3
	e x x
	x x x
	x x s
4.	3
	e 1 1
	1 1 1
	1 1 s
5.	4
	e 1 1 1
	1 1 1 1
	1 1 1 1
	1 1 1 s
6.	4
	e 1 1 1
	1 x 1 1
	1 1 x 1
	1 1 1 s
7.	5
	e 1 1 1 1
	1 1 1 1 1
	1 1 1 1 1
	1 1 1 1 1
	1 1 1 1 s
8.	5
	e 1 1 1 x
	1 x x x 1
	1 x 9 x 1
	1 x x x 1
	x 1 1 1 s
9.	3
	e 2 3
	2 x 2
	1 2 s
10.	3
	e 2 1
	2 x 2
	1 2 s
11.	3
	e 2 x
	2 x 2
	x 2 s
12.	3
	e 1 x
	4 x x
	x 1 s
13.	3
	e 1 1
	1 x 1
	1 1 s
14.	3
	e 1 1
	x x x
	1 1 s
15.	6
	e 9 6 1 5 5
	2 4 9 3 x 1
	6 2 8 x 4 5
	7 9 7 1 1 1
	2 3 5 4 4 4
	4 4 3 9 8 s
16.	9
	e 4 x 9 1 7 5 9 1
	2 9 8 2 9 6 x 8 8
	9 5 9 5 7 1 x 2 1
	2 3 8 9 x 3 8 7 8
	8 8 9 2 x 2 7 8 2
	4 6 2 6 8 7 9 5 9
	x 6 3 8 8 3 5 8 7
	9 5 7 3 5 8 4 8 1
	x 4 4 5 8 7 4 1 s
17.	7
	e 2 8 7 6 7 4
	2 6 6 2 x 6 7
	3 1 1 4 x 7 2
	1 4 2 6 1 7 6
	x 7 8 9 x 4 x
	7 1 1 4 x 2 4
	8 6 5 9 1 1 s
18.	7
	e 5 4 9 9 3 7
	x 3 1 4 5 7 5
	3 6 3 x 6 x 5
	7 1 5 8 x 9 1
	8 4 3 9 6 8 3
	2 x x 5 9 3 7
	3 2 6 4 7 4 s
19.	3
	e 6 1
	4 8 9
	9 9 s
20.	10
	e x 6 2 x 3 5 9 8 4
	3 3 5 7 4 2 8 8 4 8
	4 2 5 8 8 5 5 x 7 2
	1 5 3 2 1 9 3 4 6 9
	x x 7 1 4 3 8 3 x 1
	x 8 8 8 8 1 4 9 5 9
	7 6 9 2 2 6 1 4 7 4
	7 9 8 2 1 4 9 8 4 x
	8 6 2 3 1 6 3 3 3 5
	2 5 7 7 9 2 4 6 3 s
21.	7
	e 5 7 6 1 6 2
	1 7 8 6 3 9 1
	9 5 6 8 9 7 x
	8 5 8 5 8 7 8
	1 4 4 6 4 1 5
	8 9 6 1 5 x 8
	9 9 5 2 8 8 s
22.	10
	e 6 4 9 x x 6 x 1 x
	4 8 6 x 2 8 x 6 5 6
	3 4 1 9 7 4 5 6 1 2
	x 4 3 x 9 9 1 1 6 4
	9 5 3 x 8 4 5 3 x 3
	x x x x 8 1 6 8 x x
	5 4 9 x x x x 5 2 x
	1 6 6 2 x 2 1 x 6 2
	5 3 x 8 9 x x 2 2 1
	2 3 6 1 7 8 7 3 1 s
23.	6
	e x 9 3 8 3
	5 x 3 9 9 x
	9 7 3 8 6 1
	x 3 8 6 2 6
	8 3 5 1 1 x
	5 x 9 6 x s
24.	7
	e x 8 6 4 3 x
	x x 3 8 2 6 8
	2 6 x 5 5 8 4
	4 4 4 2 7 9 x
	x 7 8 3 x 6 3
	4 3 6 6 x 1 7
	1 x 8 2 9 5 s
25.	4
	e 2 2 2
	1 x x 2
	1 x x 1
	1 1 2 s


Expected Stdout
No.	Content
1.	1 2
2.	0 1
3.	0 0
4.	3 6
5.	5 20
6.	5 4
7.	7 70
8.	6 2
9.	7 1
10.	5 2
11.	4 2
12.	5 1
13.	3 2
14.	0 0
15.	65 1
16.	101 15
17.	60 1
18.	65 3
19.	23 2
20.	96 6
21.	79 4
22.	94 3
23.	41 4
24.	0 0
25.	9 1
 */
