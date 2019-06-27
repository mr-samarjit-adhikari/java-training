package com.hp.java.core.companyProblems.Samsung;

import java.util.Scanner;



/**
 * The King's March
 *
 * You’re given a chess board with dimension n x n. There’s a king at the bottom right square of the board marked with s. The king needs to reach the top left square marked with e. The rest of the squares are labeled either with an integer p (marking a point) or with x marking an obstacle. Note that the king can move up, left and up-left (diagonal) only. Find the maximum points the king can collect and the number of such paths the king can take in order to do so.
 * Input Format
 *
 * The first line of input consists of an integer t. This is the number of test cases. Each test case contains a number n which denotes the size of board. This is followed by n lines each containing n space separated tokens.
 * Output Format
 *
 * For each case, print in a separate line the maximum points that can be collected and the number of paths available in order to ensure maximum, both values separated by a space. If e is unreachable from s, print 0 0.
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
            int maxDist  = testCase.calcMaxDist(inputArray,n);
            int maxDistPathCount = testCase.numberOfPaths(n);
            System.out.println(maxDist+" "+maxDistPathCount);

            testCaseCount--;
        }
    }

    private int calcMaxDist(String[][] inputArray, int n) {
        int maxDist = 0;
        int[][] dp = new int[n+1][n+1];

        //initialize
        for(int row=0;row<=n;row++){
            for(int col=0;col<=n;col++){
                dp[row][col] = 0;
            }
        }
        //replace input[0][0] as 0
        inputArray[0][0] = "0";
        inputArray[n-1][n-1] = "0";

        for(int row=n-1;row>0;row--){
            for(int col=n-1;col>0;col--){
                dp[row][col-1] = dp[row][col]+("x".equals(inputArray[row][col-1])?0:Integer.parseInt(inputArray[row][col-1]));
                dp[row-1][col] = dp[row][col]+("x".equals(inputArray[row-1][col])?0:Integer.parseInt(inputArray[row-1][col]));
                dp[row-1][col-1] = dp[row][col]+("x".equals(inputArray[row-1][col-1])?0:Integer.parseInt(inputArray[row-1][col-1]));
            }
        }

        maxDist = Math.max(Math.max(dp[0][0],dp[0][1]),dp[1][0]);

        return maxDist;
    }

    private int numberOfPaths(int n)
    {
        int[][] count = new int [n][n];

        for (int i = 0; i < n; i++)
            count[i][0] = 1;

        for (int j = 0; j < n; j++)
            count[0][j] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++)
                count[i][j] = count[i - 1][j] + count[i][j - 1] + count[i-1][j-1];
        }
        return count[n - 1][n - 1];
    }

    private void readInputArray(Scanner scanner, String[][] inputArray,int size) {
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                inputArray[row][col] = scanner.next();
            }
        }
    }
}

/*
[Samsung]

Stdin
No.	Content
1.	25
2.	2
3.	e 1
4.	1 s
5.	2
6.	e x
7.	x s
8.	3
9.	e x x
10.	x x x
11.	x x s
12.	3
13.	e 1 1
14.	1 1 1
15.	1 1 s
16.	4
17.	e 1 1 1
18.	1 1 1 1
19.	1 1 1 1
20.	1 1 1 s
21.	4
22.	e 1 1 1
23.	1 x 1 1
24.	1 1 x 1
25.	1 1 1 s
26.	5
27.	e 1 1 1 1
28.	1 1 1 1 1
29.	1 1 1 1 1
30.	1 1 1 1 1
31.	1 1 1 1 s
32.	5
33.	e 1 1 1 x
34.	1 x x x 1
35.	1 x 9 x 1
36.	1 x x x 1
37.	x 1 1 1 s
38.	3
39.	e 2 3
40.	2 x 2
41.	1 2 s
42.	3
43.	e 2 1
44.	2 x 2
45.	1 2 s
46.	3
47.	e 2 x
48.	2 x 2
49.	x 2 s
50.	3
51.	e 1 x
52.	4 x x
53.	x 1 s
54.	3
55.	e 1 1
56.	1 x 1
57.	1 1 s
58.	3
59.	e 1 1
60.	x x x
61.	1 1 s
62.	6
63.	e 9 6 1 5 5
64.	2 4 9 3 x 1
65.	6 2 8 x 4 5
66.	7 9 7 1 1 1
67.	2 3 5 4 4 4
68.	4 4 3 9 8 s
69.	9
70.	e 4 x 9 1 7 5 9 1
71.	2 9 8 2 9 6 x 8 8
72.	9 5 9 5 7 1 x 2 1
73.	2 3 8 9 x 3 8 7 8
74.	8 8 9 2 x 2 7 8 2
75.	4 6 2 6 8 7 9 5 9
76.	x 6 3 8 8 3 5 8 7
77.	9 5 7 3 5 8 4 8 1
78.	x 4 4 5 8 7 4 1 s
79.	7
80.	e 2 8 7 6 7 4
81.	2 6 6 2 x 6 7
82.	3 1 1 4 x 7 2
83.	1 4 2 6 1 7 6
84.	x 7 8 9 x 4 x
85.	7 1 1 4 x 2 4
86.	8 6 5 9 1 1 s
87.	7
88.	e 5 4 9 9 3 7
89.	x 3 1 4 5 7 5
90.	3 6 3 x 6 x 5
91.	7 1 5 8 x 9 1
92.	8 4 3 9 6 8 3
93.	2 x x 5 9 3 7
94.	3 2 6 4 7 4 s
95.	3
96.	e 6 1
97.	4 8 9
98.	9 9 s
99.	10
100.	e x 6 2 x 3 5 9 8 4
101.	3 3 5 7 4 2 8 8 4 8
102.	4 2 5 8 8 5 5 x 7 2
103.	1 5 3 2 1 9 3 4 6 9
104.	x x 7 1 4 3 8 3 x 1
105.	x 8 8 8 8 1 4 9 5 9
106.	7 6 9 2 2 6 1 4 7 4
107.	7 9 8 2 1 4 9 8 4 x
108.	8 6 2 3 1 6 3 3 3 5
109.	2 5 7 7 9 2 4 6 3 s
110.	7
111.	e 5 7 6 1 6 2
112.	1 7 8 6 3 9 1
113.	9 5 6 8 9 7 x
114.	8 5 8 5 8 7 8
115.	1 4 4 6 4 1 5
116.	8 9 6 1 5 x 8
117.	9 9 5 2 8 8 s
118.	10
119.	e 6 4 9 x x 6 x 1 x
120.	4 8 6 x 2 8 x 6 5 6
121.	3 4 1 9 7 4 5 6 1 2
122.	x 4 3 x 9 9 1 1 6 4
123.	9 5 3 x 8 4 5 3 x 3
124.	x x x x 8 1 6 8 x x
125.	5 4 9 x x x x 5 2 x
126.	1 6 6 2 x 2 1 x 6 2
127.	5 3 x 8 9 x x 2 2 1
128.	2 3 6 1 7 8 7 3 1 s
129.	6
130.	e x 9 3 8 3
131.	5 x 3 9 9 x
132.	9 7 3 8 6 1
133.	x 3 8 6 2 6
134.	8 3 5 1 1 x
135.	5 x 9 6 x s
136.	7
137.	e x 8 6 4 3 x
138.	x x 3 8 2 6 8
139.	2 6 x 5 5 8 4
140.	4 4 4 2 7 9 x
141.	x 7 8 3 x 6 3
142.	4 3 6 6 x 1 7
143.	1 x 8 2 9 5 s
144.	4
145.	e 2 2 2
146.	1 x x 2
147.	1 x x 1
148.	1 1 2 s


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
