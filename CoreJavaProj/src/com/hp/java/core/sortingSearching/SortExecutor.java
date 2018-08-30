package com.hp.java.core.sortingSearching;

public class SortExecutor {
    private ALGO sortingAlgo;
    
    public SortExecutor(ALGO algorithm) {
        this.sortingAlgo = algorithm;
    }

    public void sort(int[] inputArray) {
        switch(this.sortingAlgo){
            case QUICKSORT:
                doQuickSort(inputArray,0,inputArray.length-1);
                break;
            default:
                System.out.println("NOT IMPLEMENTED");
        }
    }

    private void doQuickSort(int[] inputArray,int startIndex,int endIndex) {
        if(startIndex < endIndex){ //terminal condition for recursive call
            int partitionIndex = doQuickSortPartition(inputArray,startIndex,endIndex);
            doQuickSort(inputArray,startIndex,partitionIndex-1);
            doQuickSort(inputArray,partitionIndex+1,endIndex);
        }
    }

    private int doQuickSortPartition(int[] inputArray, int startIndex, int endIndex) {
        int privotElementIndex = startIndex;
        int privotElement = inputArray[privotElementIndex];

        int indexLeft = startIndex +1;
        int indexRight = indexLeft;

        while(indexRight<=endIndex){

            if(inputArray[indexRight] < privotElement){
                swapArrayContent(inputArray,indexLeft,indexRight);
                indexLeft++;
            }

            indexRight ++ ;
        }
        swapArrayContent(inputArray,startIndex,indexLeft-1);

        return indexLeft-1;  //return the position of pivot
    }

    private void swapArrayContent(int[] inputArray,int leftIndex, int rightIndex) {
        int tmp = inputArray[leftIndex];
        inputArray[leftIndex] = inputArray[rightIndex];
        inputArray[rightIndex] = tmp;
    }
}
