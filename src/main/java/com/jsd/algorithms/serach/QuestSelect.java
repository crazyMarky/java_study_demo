package com.jsd.algorithms.serach;

public class QuestSelect {


    public static int questSelect(int k, int[] array, int low, int hight) {
        int firstLow = low;
        //获取任意一个数
        int index = 0;
        int pivot = array[index];
        int length = array.length - 1;
        //将数组分成比当前任意数大和小两个集合
        while (low != hight && hight >= 0 && length >= low) {
            while (array[hight] > pivot) {
                hight--;
            }
            arraySwap(array, hight, index);
            index = hight;
            while (array[low] < pivot) {
                low++;
            }
            arraySwap(array, low, index);
            index = low;
        }
        //看是否第k个最小值
        if ((k - 1) == index) {
            return array[index];
        } else {
            //如果不是的话则继续遍历前一段区间的集合
            return questSelect(k, array, firstLow, index);
        }

    }

    public static void main(String[] args) {
        int[] a = new int[]{321, 555, 554, 11, 10, 456, 789, 1234, 562, 4502, 123, 4556, 4451, 3325, 2345, 4531};
        int i1 = questSelect(2, a, 1, 14);
        for (int i : a) {
            System.out.printf(i + " ");
        }
        System.out.println(" ");
        System.out.println(i1);
    }

    private static void arraySwap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}
