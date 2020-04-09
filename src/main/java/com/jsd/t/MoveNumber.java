package com.jsd.t;

import java.util.Arrays;

public class MoveNumber {
    public static void main(String[] args) {
        int[] arr = {6, 4, -3, 5, -2, -1, 0, 1, -9};
        move(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     *  implement a function to move all positive numbers to the left, and move all negative numbers to the right.
     *  Try your best to make its time complexity to O(n), and space complexity to O(1).
     * @param arr
     */
    public static void move(int[] arr){
        int i = 0;
        int j = arr.length-1;
        while (i<j){
            while (arr[i]>=0 && i<j){i++;}
            while (arr[j]<0 && i<j){j--;}
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
