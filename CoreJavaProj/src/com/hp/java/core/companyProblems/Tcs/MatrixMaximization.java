package com.hp.java.core.companyProblems.Tcs;

import java.util.Scanner;

/**
 * Matrix Maximization
 * You are given a matrix of  rows and  columns. Now, you can remove at most one row or at most one column from the matrix.
 * Your goal is to maximize the sum of remaining elements in the matrix. Print the maximum sum that can be obtained.
 *
 * Note: At-most one means either zero or one.
 *
 * Input
 * The first line contains two space-separated integers  and  respectively.
 * Next  lines contain  elements each denoting the elements of the matrix.
 *
 * Output
 * In the output print the maximum sum that can be obtained.
 *
 * Constraints
 * 1 \le M,N \le 1000
 * -10^9 \le val \le 10^9  where val denotes element of the matrix.
 *
 * Sample Input
 * 2 3
 * 1 2 -3
 * 4 5 -6
 * Sample Output
 * 12
 * Explanation
 * In the given sample if you remove the third column then the resultant matrix will be . [1 2,4 5]
 * The sum of the elements is 12 which is the maximum sum possible.
 */

public class MatrixMaximization {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int matrixRowCount = scanner.nextInt();
        int matricColCount = scanner.nextInt();

        System.out.println("RowCount "+matrixRowCount+", ColCount "+matricColCount);
    }
}
