package com.hp.java.core.companyProblems.InterviewBit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Container With Most Water
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * 'n' vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 *
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Your program should return an integer which corresponds to the maximum area of water that can be contained
 * ( Yes, we know maximum area instead of maximum volume sounds weird. But this is 2D plane we are working with
 * for simplicity ).
 *
 *       |
 *       |-|
 *       | |-|
 *       | | |
 *     |-| | |
 *     1 5 4 3
 */

public class ContainerWithMostWaterSolution {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in).useDelimiter(" *");
        List intergerList = new ArrayList();

        ContainerWithMostWaterSolution solution = new ContainerWithMostWaterSolution();
        solution.getInput(scanner,intergerList);
        int result  = solution.solve(intergerList);
        System.out.println(result);
    }

    private int solve(List intergerList) {
        int leftindex = 0;
        int maxVal = 0;
        int rightIndex = intergerList.size()-1;

        while(leftindex<rightIndex){
            int minValue = Math.min((int)intergerList.get(leftindex),
                                     (int)intergerList.get(rightIndex));
            int area = minValue * (rightIndex-leftindex);
            if(maxVal< area){
                maxVal = area;
            }
            if(minValue==((int)intergerList.get(leftindex))){
                leftindex++;
            }
            else{
                rightIndex--;
            }
        }
        return maxVal;
    }

    private void getInput(Scanner scanner,List intergerList) {
        while(scanner.hasNextInt())
            intergerList.add(scanner.nextInt());
    }
}
