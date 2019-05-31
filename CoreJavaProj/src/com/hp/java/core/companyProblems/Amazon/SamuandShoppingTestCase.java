package com.hp.java.core.companyProblems.Amazon;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.hp.java.core.companyProblems.Amazon.SamuandShoppingTestCase.ShoppingItems.NONE;
import static com.hp.java.core.companyProblems.Amazon.SamuandShoppingTestCase.ShoppingItems.PANT;
import static com.hp.java.core.companyProblems.Amazon.SamuandShoppingTestCase.ShoppingItems.SHIRT;
import static com.hp.java.core.companyProblems.Amazon.SamuandShoppingTestCase.ShoppingItems.SHOE;

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
        Scanner scanner = new Scanner(System.in);
        int tc = scanner.nextInt();

        while(tc>0){
            SamuandShoppingTestCase testCase = new SamuandShoppingTestCase();
            int shopCount = scanner.nextInt();
            List<item> shops = new ArrayList(shopCount);

            while(shopCount>0){
                testCase.addShirtPantShoe(shops,scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
                shopCount--;
            }
            int minCost = testCase.findMinCostShopping(shops);
            System.out.println(minCost);

            tc--;
        }

    }

    private int findMinCostShopping(List<item> shops) {
        ShoppingItems prevItem = NONE;
        int minCost = 0;
        for(item shop:shops){
            minCost += findMinCost(shop,prevItem);
        }

        return minCost;
    }

    private int findMinCost(item shop, ShoppingItems prevItem) {
        int minCost = 0;
        int shirtItemCost = shop.getCost();
        int pantItemCost = shop.getNext().getCost();
        int shoeItemCost = shop.getNext().getNext().getCost();

        switch(prevItem){
            case NONE:
                minCost = Math.min(shirtItemCost,pantItemCost);
        }
        return 0;
    }

    private void addShirtPantShoe(List<item> shops, int shirtCost, int pantCost, int shoeCost) {
        item shirtItem = new item(SHIRT,shirtCost);
        item pantItem = new item(PANT,pantCost);
        item shoeItem = new item(SHOE,shoeCost);

        pantItem.setNext(shoeItem);
        shirtItem.setNext(pantItem);

        shops.add(shirtItem);
    }

    public enum ShoppingItems{
        SHIRT,
        PANT,
        SHOE,
        NONE
    }

    class item{
        private int cost;
        private ShoppingItems shopItemType;
        private item next;

        private item(ShoppingItems name,int cost){
            shopItemType= name;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }

        public ShoppingItems getShopItemType() {
            return shopItemType;
        }

        public void setNext(item next) {
            this.next = next;
        }

        public item getNext() {
            return next;
        }
    }
}
