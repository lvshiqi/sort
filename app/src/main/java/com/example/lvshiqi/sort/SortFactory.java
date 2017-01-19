package com.example.lvshiqi.sort;

import java.util.Stack;

/**
 * Created by lvshiqi on 16-11-28.
 * Sort factory
 */
public class SortFactory {

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
                case 5:
                    mergeSort_not_recursion(result);
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
     *
     * @param result
     * @param low
     * @param mid
     * @param high
     */
    public void merge(int[] result, int low, int mid, int high) {
        int[] tempNumbers = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
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

    /**
     * The quicksort without recursion
     * @param result
     * @return
     */
    public int[] quickSort_not_recursion(int[] result) {
        int i;
        int j;
        int min;    // Every loop's max index
        int max;    // Every loop's minus index
        int key;

        Stack<Integer> conditions = new Stack<Integer>();   // Record the minus index and the max index
        conditions.push(0);
        conditions.push(result.length - 1);
        int temp;

        // In every loop will get a left index and right index.
        while (!conditions.empty()) {
            max = conditions.pop();
            min = conditions.pop();
            key = result[min];
            i = min + 1;
            j = max;

            // With this step, the numbers can be divided to 2 sections,
            // the left side is smaller than the key value,
            // the right side is bigger than the key value.
            while (i < j) {
                // Get the number's index which is smaller than key
                while (key < result[j] && i < j) {
                    j--;
                }

                // Get the number's index which is bigger than key
                while (key > result[i] && i < j) {
                    i++;
                }

                // Swap
                temp = result[j];
                result[j] = result[i];
                result[i] = temp;
            }

            // Swap the key and i(or j)
            if (key > result[i]) {
                temp = result[min];
                result[min] = result[j];
                result[j] = temp;
            }

            // Store the left side minus index and the max index
            if (min < i - 1) {
                conditions.push(min);
                conditions.push(i - 1);
            }

            // Store the right side minus index and the max index
            if (max > i + 1) {
                conditions.push(i + 1);
                conditions.push(max);
            }
        }

        return result;
    }

    /**
     * Merge sort without recursion
     * @param result
     * @return
     */
    public int[] mergeSort_not_recursion(int[] result) {
        int length = result.length;
        int[] temp = new int[length]; // To store the minus numbers

        if (result.length == 0) {
            LogUtils.e("mergeSort_not_recursion", "The count of array is null", null);
        }

        // Split array
        int gap, i = 0;
        for (gap = 1; gap < length; gap *= 2) {
            // To use gap splits numbers
            for (i = 0; i + 2 * gap - 1 < length; i = i + 2 * gap) {
                mergesort(temp, i, i + gap, i + 2 * gap - 1, result);
            }

            // The rest of numbers
            if (i + gap < length) {
                mergesort(temp, i, i + gap, length - 1, result);
            }
        }

        return result;
    }

    public int[] mergesort(int[] temp, int min, int middle, int high, int[] result){
        int index = 0;
        int i = min;
        int j = middle;

        // Get The smaller numbers,
        // Store them in temp
        while(i <= middle-1 && j<=high) {
            if (result[i] >= result[j]) {
                temp[index] = result[j];
                j++;
                index++;
            }else{
                temp[index] = result[i];
                i++;
                index++;
            }
        }

        // To store the rest of numbers to temp
        while (i < middle) {
            temp[index] = result[i];
            i++;
            index++;
        }

        // To store the rest of numbers to temp
        while (j < high) {
            temp[index] = result[j];
            j++;
            index++;
        }

        // To copy numbers back to result
        int k = 0;
        while (k < index) {
            result[min] = temp[k];
            k++;
            min++;
        }

        return result;
    }
}
