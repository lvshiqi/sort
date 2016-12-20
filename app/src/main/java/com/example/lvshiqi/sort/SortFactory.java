package com.example.lvshiqi.sort;

import java.util.Stack;

/**
 * Created by lvshiqi on 16-11-28.
 * Sort factory
 */
public class SortFactory {
    private int[] numbers;

    public int[] excuteFunction(int function, int[] result) {
        if (result.length != 0) {
            switch (function) {
                case 0:
                    bubbleSort(result);
                    break;
                case 1:
                    selectSort(result);
                    break;
                case 2:
                    quickSort(result, 0, result.length - 1);
                    break;
                case 3:
                    mergeSort(result, 0, result.length - 1);
                    break;
                case 4:
                    quickSort_not_recursion(result);
                default:
                    break;
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * Bubble sort
     *
     * @param result
     * @return
     */
    public int[] bubbleSort(int[] result) {
        if (result.length != 0) {
            for (int i = 0; i < result.length - 1; i++) {
                for (int j = 0; j < result.length - 1 - i; j++) {
                    // Exchange
                    if (result[j] > result[j + 1]) {
                        int temp = result[j];
                        result[j] = result[j + 1];
                        result[j + 1] = temp;
                    }
                }
            }
        }
        return result;
    }

    /**
     * selectSort
     *
     * @param result
     * @return
     */
    public int[] selectSort(int[] result) {
        if (result.length != 0) {
            for (int i = 0; i < result.length; i++) {
                int min = i;
                for (int j = i; j < result.length; j++) {
                    if (result[min] > result[j]) {
                        min = j;
                    }
                }

                // Exchange
                int temp = result[i];
                result[i] = result[min];
                result[min] = temp;
            }
        }

        return result;
    }

    /**
     * quickSort
     *
     * @param result
     * @return
     */
    public void quickSort(int[] result, int low, int high) {
        if (result.length != 0) {
            if (low < high) {
                int pation = pation(result, low, high);
                quickSort(result, low, pation - 1);
                quickSort(result, pation + 1, high);
            }
        }
    }

    /**
     * Get pation
     *
     * @param result
     * @param start
     * @param end
     * @return
     */
    public int pation(int[] result, int start, int end) {
        int temp = result[start];
        while (start < end) {
            // Find the bigger one
            while (result[end] >= temp && start < end) {
                end--;
            }
            result[start] = result[end];

            // Find the smaller one
            while (result[start] <= temp && start < end) {
                start++;
            }

            result[end] = result[start];
        }

        result[start] = temp;
        return start;
    }

    /**
     * Merge sort
     *
     * @param result
     * @param low
     * @param high
     * @return
     */
    public int[] mergeSort(int[] result, int low, int high) {
        int mid = (low + high) / 2;

        if (low < high) {
            mergeSort(result, low, mid);
            mergeSort(result, mid + 1, high);
            merge(result, low, mid, high);
        }

        return result;
    }

    /**
     * Merge sort
     * @param result
     * @param low
     * @param mid
     * @param high
     */
    public void merge(int[] result, int low, int mid, int high) {
        int[] tempNumbers = new int[high - low + 1];
        int i = low;
        int j = mid+1;
        int temp = 0;

        // Set the minus number to the forahead numbers
        while (i <= mid && j <= high) {
            if (result[i] < result[j]) {
                tempNumbers[temp++] = result[i++];
            } else {
                tempNumbers[temp++] = result[j++];
            }
        }

        while (i <= mid) {
            tempNumbers[temp++] = result[i++];
        }

        while (j <= high) {
            tempNumbers[temp++] = result[j++];
        }

        for (int k = 0; k < tempNumbers.length; k++) {
            result[k + low] = tempNumbers[k];
        }
    }

    public int[] quickSort_not_recursion(int[] result) {
        int i = 0;
        int j = result.length-1;
        int min;    // Every loop's max number
        int max;    // Every loop's minus number
        int key = result[0];

        Stack<Integer> conditions = new Stack<Integer>();
        conditions.push(0);
        conditions.push(result.length-1);
        int temp;

        while(!conditions.empty()){
            j = conditions.pop();
            i = conditions.pop();
            key = result[i];
            min = i;
            max = j;

            while(i<j) {
                while (key < result[j] && i<j) {
                    j--;
                }

                while (key > result[i] && i<j) {
                    i++;
                }

                // Swap
                temp = result[j];
                result[j] = result[i];
                result[i] = temp;
            }

            if(min<i-1){
                conditions.push(min);
                conditions.push(i-1);
            }

            if(max>i+1){
                conditions.push(i+1);
                conditions.push(max);
            }
        }

        return result;
    }
}
