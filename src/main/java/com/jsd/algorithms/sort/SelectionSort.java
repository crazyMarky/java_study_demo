package com.jsd.algorithms.sort;

public class SelectionSort {
    //选择排序是一种减而治之的思路，主要是把数组(前/后)当成是有序的，在无序的部分中选出最大/最小的，放到数组的最(后/前)
    //随后排序问题就会变成在无序的地方进行寻找最大值，然后则是一直重复，最后则会得到一个有序的数组

    /**
     * 选择排序
     *
     * @param array 需要排序的数组
     * @param index 当前的数组坐标(就是模拟数组的分成两份)
     */
    public static void selectionSort(int[] array, int index) {
        //当数组的坐标小于0的时候，说明已经遍历完了
        if (index < 0) {
            return;
        } else {
            //获取当前坐标的值
            int max = array[index];
            //定义一个最终最大值的坐标
            int maxIndex = -1;
            //遍历数组
            for (int i = 0; i < index; i++) {
                if (array[i] > max) {
                    //获取最大值
                    max = array[i];
                    maxIndex = i;
                }
            }
            //如果存在最大值则交换
            if (max != array[index]) {
                int temp = array[index];
                array[index] = array[maxIndex];
                array[maxIndex] = temp;
            }
            //重复以上步骤,一次遍历找出一个最大值，所以index-1
            selectionSort(array, index - 1);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{789, 555, 554, 11, 10, 456, 789, 1234, 562, 4502, 123, 4556, 4451, 3325, 2345, 4531};
        selectionSort(a, a.length - 1);
        for (int i : a) {
            System.out.print(i+" ");
        }
    }
}
