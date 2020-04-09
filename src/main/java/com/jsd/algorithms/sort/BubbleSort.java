package com.jsd.algorithms.sort;

import java.util.Arrays;

/**
 * 冒泡排序-必会
 */
public class BubbleSort {
    public static void main(String[] args) {
        Integer[] arr = {1, 4, 2, 6, 8, 9, 7, 3};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public  static  void sort(Integer arr[]){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
