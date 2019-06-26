package com.hp.java.core.companyProblems.ITCInfotech;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Given a matrix, m rows, n columns and an integer k, filled with x and o,
 * find the count of streaks of x and o. A streak is a group of adjacent k characters
 * where adjacency is horizontal, vertical and diagonal.
 *
 * Input Format
 *
 * The first line contains and integer t denoting the number of test cases.
 * The second line consists of three space separated integers m, n and k.
 * The next m rows each contain n space separated characters.
 * Output Format
 *
 * For each test case print the count of streaks of x and o space separated.
 * Input:
 * 2
 * 3 3 3
 * x x x
 * x o x
 * x x x
 * 1 1 2
 * o
 */
public class KnotsAndCrossesTestCase {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();

        while(testCaseCount>0){

            KnotsAndCrossesTestCase testCase = new KnotsAndCrossesTestCase();
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            char[][] matrix = testCase.buildMatrix(m,n,scanner);
            int resultX = testCase.countStreaks('x',k);
            int resultO = testCase.countStreaks('o',k);
            System.out.println(resultX+" "+resultO);

            testCaseCount--;
        }
    }

    private int countStreaks(char player,int k) {
        List<PointLocation> locList;
        int returnVal = 0;
        if(player=='x') locList = xLoc;
        else locList = oLoc;

        for(PointLocation loc:locList){
            int horizontalLen = 1;
            int verticalLen = 1;
            int diagonalLen = 1;

            //horizontal
            PointLocation lLoc = loc;
            for(int count=1;count<=k;count++){
                PointLocation newLoc = new PointLocation(lLoc.getX(),lLoc.getY()+1);
                if(locList.contains(newLoc)){
                    horizontalLen++;
                    lLoc = new PointLocation(lLoc.getX(),lLoc.getY()+1);
                }else break;
            }
            if(k==horizontalLen) returnVal++;

            //Vertical
            lLoc = loc;
            for(int count=1;count<=k;count++){
                PointLocation newLoc = new PointLocation(lLoc.getX()+1,lLoc.getY());
                if(locList.contains(newLoc)){
                    verticalLen++;
                    lLoc = new PointLocation(lLoc.getX()+1,lLoc.getY());
                }else break;
            }
            if(k==verticalLen) returnVal++;

            //diagonal
            lLoc = loc;
            for(int count=1;count<=k;count++){
                PointLocation newLoc = new PointLocation(lLoc.getX()+1,lLoc.getY()+1);
                if(locList.contains(newLoc)){
                    diagonalLen++;
                    lLoc = new PointLocation(lLoc.getX()+1,lLoc.getY()+1);
                }else break;
            }
            if(k==diagonalLen) returnVal++;
        }
        return returnVal;
    }

    private char[][] buildMatrix(int m, int n, Scanner scanner) {
        char[][] matrix = new char[m][n];
        for(int row=0;row<m;row++){
            for(int col=0;col<n;col++){
                matrix[row][col] = scanner.next().charAt(0);
                if(matrix[row][col]=='x'){
                    xLoc.add(new PointLocation(row,col));
                }else{
                    oLoc.add(new PointLocation(row,col));
                }
            }
        }
        return matrix;
    }

    private KnotsAndCrossesTestCase(){
        this.xLoc = new ArrayList<>();
        this.oLoc = new ArrayList<>();
    }

    private List<PointLocation>  xLoc;
    private List<PointLocation>  oLoc;
    class PointLocation{
        private int x;
        private int y;
        PointLocation(int x,int y){
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
            PointLocation loc = (PointLocation)obj;
            if((this.x==loc.getX())&&
                    (this.y==loc.getY())){
                return true;
            }
            return false;
        }
    }
}
