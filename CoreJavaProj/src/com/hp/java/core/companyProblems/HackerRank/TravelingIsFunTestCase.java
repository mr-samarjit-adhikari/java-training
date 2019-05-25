package com.hp.java.core.companyProblems.HackerRank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


/**
 * https://www.youtube.com/watch?v=wU6udHRIkcc
 *
 * Julia is planning a vacation and has a list of cities she wants to visit. She doesn't have a map of the area,
 * but she does have some data that will help her determine whether there is a road connecting all the
 * cities she wants to visit. The data comes in the form of two arrays.
 * Each of the first array's elements is an origin city.
 * Each of the second array's is a destination.
 * There is also an integer value threshold. She
 * can tell that any two cities are connected if the values at origin and destination share a common divisor greater
 * than the threshold. Cities are indexed starting at 0.
 * Each of the pairs, originCities[0] and destinationCities[0] for example, represents a route she wants to take.
 * For each pair, determine whether there is a route between cities. The route does not have to be
 * direct. See the explanation to Sample Case 1 relating to originCity equals 2 or 4 for examples.
 * For instance, consider
 * an array originCities = [1, 2, 3] and destinationCities = [4, 5, 6].
 * The threshold value is 2. There are 6 total cities. To draw the map, first determine the divisors of all cities:
 * Origins
 * City
 * Divisors Destinations
 * City
 * Divisors
 * 1
 * 2
 * 3 4
 * 5
 * 6
 * 1
 * 1,2
 * 1,3
 * 1,2,4
 * 1,5
 * 1,2,3,6
 * The threshold is 2, so we can eliminate cities 1 and 2. Their divisors are not greater than the threshold. This leaves city 3 to check in the origins list. It has a divisor in common with city 6, and it is greater than
 * the threshold so there is a road between them. This is the only pair of connected cities. Now that we have created a map, we can check her routes.
 * She wants to go from originCity[0] = 1 to destinationCity[0] = 4 but there is no road. There is no road for her second route either, from city 2 to 5. There is only a road that matches her third route at index 2,
 * from city 3 to 6. A true/false array of her results would be paths = [0, 0, 1].
 * Function Description
 * Complete the function connectedCities in the editor below. The function must return a true/false array where each paths[i] contains 1 if the route between originCities[i] and destinationCities[i] exists, or 0 if it
 * does not.
 * connectedCities has the following parameter(s):
 * n: integer, the number of cities
 * g: integer, the threshold value
 * originCities[originCities[0],...originCities[q-1]]: an array of integers
 * destinationCities[destinationCities[0],...destinationCities[q-1]]: an array of integers
 * Constraints
 * 2 ≤ n ≤ 2 × 10 5
 * 0 ≤ g ≤ n
 * 1 ≤ q ≤ min( n × (n - 1) / 2 , 10 5 )
 * 1 ≤ originCities[i], destinationCities[i] ≤ n, where 0 ≤ i < q
 * originCities[i] ≠ destinationCities[i], where 0 ≤ i < q
 * Input Format for Custom Testing
 * Input from stdin will be processed as follows and passed to the function.
 * The first line contains an integer n, the total number of cities.
 * The second line contains an integer g, the threshold value.
 * The next line contains an integer q, the size of the array originCities.
 * The next q lines each contain an element originCities[i] where 0 ≤ i < q.
 * The next line contains an integer q, the size of the array destinationCities.
 * The next q lines each contain an element destinationCities[i] where 0 ≤ i < q.
 * Sample Case 0
 * Sample Input 0
 * 6
 * 0
 * 4
 * 1
 * 4
 * 3
 * 6
 * 4
 * 3
 * 6
 * 2
 * 5
 * Sample Output 0
 * 1
 * 1
 * 1
 * 1
 */
public class TravelingIsFunTestCase {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int g = in.nextInt();
        //origin cities
        int originCities_cnt = in.nextInt();
        List<Integer> originCities = new ArrayList<>(originCities_cnt);
        for(int originCities_i = 0; originCities_i < originCities_cnt; originCities_i++){
            originCities.add(in.nextInt());
        }
        //destination cities
        int destinationCities_cnt = in.nextInt();
        List<Integer> destinationCities = new ArrayList<>(destinationCities_cnt);
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

        List<Integer> res = new ArrayList<>(originCities.size());
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
