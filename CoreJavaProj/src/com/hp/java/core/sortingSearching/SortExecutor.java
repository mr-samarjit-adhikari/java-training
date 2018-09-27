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
            case MERGESORT:
                doMergeSort(inputArray,0,inputArray.length-1);
                break;
            default:
                System.out.println("NOT IMPLEMENTED");
        }
    }

    private void doMergeSort(int[] inputArray, int startIndex, int endIndex) {
        if(startIndex < endIndex){
            int midIndex = (startIndex + endIndex )/2;
            doMergeSort(inputArray,startIndex,midIndex);
            doMergeSort(inputArray,midIndex+1,endIndex);

            doMerge(inputArray,startIndex,midIndex,endIndex);
        }
    }

    private void doMerge(int[] inputArray, int startIndex, int midIndex, int endIndex) {
        int p = startIndex;
        int q = midIndex +1;
        int k = 0;
        int[] AuxArray = new int[endIndex-startIndex+1];

        for(int index = startIndex;index < endIndex;index++){
            //when input array is in increasing order.
            if(p>midIndex){
                AuxArray[k++] = inputArray[q++];
            }
            else if(q>endIndex){ //when array is in decresing order
                AuxArray[k++] = inputArray[p++];
            }
            else if (inputArray[p] < inputArray[q]){
                AuxArray[k++] = inputArray[p++];
            }
            else{
                AuxArray[k++] = inputArray[q++];
            }
        }

        //Now override the input array.
        for(int idx=0;idx<k;idx++){
            inputArray[idx] = AuxArray[idx];
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
