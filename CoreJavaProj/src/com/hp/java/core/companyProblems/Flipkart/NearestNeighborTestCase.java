package com.hp.java.core.companyProblems.Flipkart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class NearestNeighborTestCase {
    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int cCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> c = IntStream.range(0, cCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        int xCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> x = IntStream.range(0, xCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int yCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> y = IntStream.range(0, yCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int qCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> q = IntStream.range(0, qCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        List<String> result = NearestNeighborTestCase.closestStraightCity(c, x, y, q);

        System.out.printf(result.stream().collect(joining("\n"))+ "\n");

        bufferedReader.close();
    }

    public static List<String> closestStraightCity(List<String> c, List<Integer> x, List<Integer> y, List<String> q) {
        List<String> ret = new ArrayList();
        Map<String,Integer> cityNameMap = new HashMap();
        int cityCount = c.size();
        int matx[][] = new int[cityCount][cityCount];
        int coMatx[][] = new int [2][cityCount];

        int i = 0;
        for(String cityName:c){
            cityNameMap.put(cityName,i++);
        }

        for(i=0;i<cityCount;i++){
            coMatx[0][i] = x.get(i);
            coMatx[1][i] = y.get(i);
        }

        //polulate matx by cost
        for(i=0;i<cityCount;i++){
            for(int j=0;j<cityCount;j++){
                matx[i][j] = calculateMinDist(i,j,coMatx);
            }
        }

        for(String qcityName:q){
            String neighbor = findMinNeighbor(qcityName, cityNameMap, matx);
            ret.add(neighbor);
        }
        return ret;
    }

    public static String findMinNeighbor (String queryCity,Map<String,Integer> cityMap,int matx[][]){
        String retString = null;
        int queryCityindex = cityMap.get(queryCity);
        int cityCount = cityMap.size();

        int minCost = matx[queryCityindex][0];
        int minCostIndex = 0;

        for(int i=0;i<cityCount;i++){
            minCost = matx[queryCityindex][i];
            if(minCost!=0){
                minCostIndex = i;
                minCost = matx[queryCityindex][i];
                break;
            }
        }

        for(int i=minCostIndex;i<cityCount;i++){
            int currCost = matx[queryCityindex][i];
            if((currCost != 0) && (currCost<minCost)){
                minCost = currCost;
                minCostIndex = i;
            }
        }

        if(minCost == Integer.MAX_VALUE){
            retString = "NONE";
        }
        else{
            for(Map.Entry<String,Integer> entry:cityMap.entrySet()){
                if(minCostIndex == entry.getValue().intValue()){
                    retString = entry.getKey();
                    break;
                }
            }
        }
        return retString;
    }

    public static int calculateMinDist(int i,int j,int coMatx[][]){
        int ret = 0;

        if(i==j){
            ret = 0;
        }else{
            if(coMatx[0][i]==coMatx[0][j]){
                ret = abs(coMatx[1][i] - coMatx[1][j]);
            } //X coordinate is same
            else if(coMatx[1][i]==coMatx[1][j]){ //y coordintes are same
                ret = abs(coMatx[0][i] - coMatx[0][j]);
            }
            else{
                ret = Integer.MAX_VALUE; // Represents None
            }
        }

        return ret;
    }
}
