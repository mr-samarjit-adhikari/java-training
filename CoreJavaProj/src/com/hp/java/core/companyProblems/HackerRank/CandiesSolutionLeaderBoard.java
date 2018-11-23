package com.hp.java.core.companyProblems.HackerRank;

import java.util.Scanner;

public class CandiesSolutionLeaderBoard {
    public static void main( String args[] )
    {
        Scanner in = new Scanner(System.in);

        int N;
        N = in.nextInt();

        int rating[] = new int[N];
        int countMap[] = new int[N];

        for(int i=0; i<N; i++)
        {
            rating[i] = in.nextInt();
        }

        for(int j=0; j<N; j++)
        {
            countMap[j] = 1;
        }

        for (int k=0;k<N-1;k++)
        {
            if (rating[k] > rating[k+1])
            {
                if (countMap[k]<=countMap[k+1])
                    countMap[k]++;
            }
            else if(rating[k] < rating[k+1])
            {
                countMap[k+1]++;
                if (countMap[k+1]<=countMap[k])
                {
                    countMap[k+1] = countMap[k]+1;
                }
            }
            else
            {
                continue;
            }
        }

        for (int m=N-1;m>0;m--)
        {
            if (rating[m-1]>rating[m])
            {
                if(countMap[m-1] <=countMap[m])
                {
                    countMap[m-1] = countMap[m]+1;
                }
            }
        }
        for(int i=0;i<countMap.length;i++){
            System.out.println(countMap[i]+" ");
        }
        System.out.println();

        int numOfCandies = 0;
        for (int i=0; i<N;i++)
        {
            numOfCandies += countMap[i];
        }
        System.out.println(numOfCandies);
    }
}
