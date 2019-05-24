package com.hp.java.core.companyProblems.HackerRank;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TravelingIsFunTestCase {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int g = in.nextInt();
        //origin cities
        int originCities_cnt = in.nextInt();
        List<Integer> originCities = new ArrayList(originCities_cnt);
        for(int originCities_i = 0; originCities_i < originCities_cnt; originCities_i++){
            originCities.add(in.nextInt());
        }
        //destination cities
        int destinationCities_cnt = in.nextInt();
        List<Integer> destinationCities = new ArrayList(destinationCities_cnt);
        for(int originCities_i = 0; originCities_i < destinationCities_cnt; originCities_i++){
            destinationCities.add(in.nextInt());
        }

        List<Integer> res = new TravelingIsFunTestCase()
                                .connectedCities(n, g, originCities, destinationCities);

        for (Integer result:res) {
            System.out.println(result.intValue());
        }
        in.close();
    }

    private List<Integer> connectedCities(int n, int g, List<Integer> originCities, List<Integer> destinationCities) {
        int[] root = new int[n + 1];
        int[] ids = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            root[i] = i;
            ids[i] = 1;
        }

        for (int i = g + 1; i <= n; i++)
            for (int j = 2 * i; j <= n; j += i)
                unionFind(j, i, root, ids);

        List<Integer> res = new ArrayList(originCities.size());
        Iterator<Integer> itSrc = originCities.iterator();
        Iterator<Integer> itDest = destinationCities.iterator();

        while (itSrc.hasNext() && itDest.hasNext())
            res.add(getRoot(itSrc.next(), root) == getRoot(itDest.next(), root) ? 1 : 0);

        return res;
    }

    private int getRoot(Integer a, int[] root) {
        while (a != root[a])
            a = root[a];
        return a;
    }

    private void unionFind(int a, int b, int[] root, int[] ids) {
        int aRoot = getRoot(a, root);
        int bRoot = getRoot(b, root);

        if (aRoot == bRoot)
            return;

        if (ids[aRoot] < ids[bRoot]) {
            root[aRoot] = root[bRoot];
            ids[bRoot] += ids[aRoot];
        } else {
            root[bRoot] = root[aRoot];
            ids[aRoot] += ids[bRoot];
        }
    }
    
}
