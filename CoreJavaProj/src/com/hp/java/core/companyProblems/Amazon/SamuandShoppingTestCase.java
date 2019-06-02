package com.hp.java.core.companyProblems.Amazon;

import java.util.Scanner;
import static java.lang.Math.min;

/**
 * Samu is in super market and in a mood to do a lot of shopping. She needs to buy shirts, pants and shoes for herself
 * and her family. There are N different shops. Each shop contains all these three items but at different prices.
 * Now Samu has a strategy that she won't buy the same item from the current shop if she had already bought that item
 * from the shop adjacent to the current shop.
 *
 * Now Samu is confused, because although she want to follow her strategy strictly but at the same time she want to
 * minimize the total money she spends on shopping. Being a good programmer, she asks for your help.
 *
 * You are provided description about all N shops i.e costs of all three items in each shop. You need to help Samu
 * find minimum money that she needs to spend such that she buys exactly one item from every shop.
 *
 * Input Format:
 * First line contain number of test cases T. Each test case in its first line contain N denoting the number of
 * shops in Super Market. Then each of next N lines contains three space separated integers denoting cost of shirts,
 * pants and shoes in that particular shop.
 *
 * Output Format:
 * For each test case, output the minimum cost of shopping taking the mentioned conditions into account in a separate line.
 *
 * Constraints :
 * 1 ≤ T ≤ 10
 * 1 ≤ N ≤ 105
 * Cost of each item (shirt/pant/shoe) does not exceed pow(10,4)
 */
public class SamuandShoppingTestCase {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();

        while(t!=0)
        {
            int n = s.nextInt();

            int a[][] = new int[3][n];
            int b[][] = new int[3][n];
            for(int i=0;i<n;i++)
            {
                a[0][i] = s.nextInt();
                a[1][i] = s.nextInt();
                a[2][i] = s.nextInt();
            }

            b[0][0]=a[0][0];
            b[1][0]=a[1][0];
            b[2][0]=a[2][0];

            for(int i=1;i<n;i++)
            {
                b[0][i]=a[0][i]+min(b[1][i-1],b[2][i-1]);
                b[1][i]=a[1][i]+min(b[0][i-1],b[2][i-1]);
                b[2][i]=a[2][i]+min(b[0][i-1],b[1][i-1]);
            }

            t--;
            System.out.println(min(b[0][n-1],min(b[1][n-1],b[2][n-1])));
        }
    }
}
