package com.jsd.algorithms.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 　快速排序是对冒泡排序的一种改进，由C. A. R. Hoare在1962年提出的一种划分交换排序，采用的是分治策略（一般与递归结合使用），以减少排序过程中的比较次数。
 *
 * 　　①、快速排序的基本思路
 * 　　一、先通过第一趟排序，将数组原地划分为两部分，其中一部分的所有数据都小于另一部分的所有数据。原数组被划分为2份
 *
 * 　　二、通过递归的处理， 再对原数组分割的两部分分别划分为两部分，同样是使得其中一部分的所有数据都小于另一部分的所有数据。 这个时候原数组被划分为了4份
 *
 * 　　三、就1,2被划分后的最小单元子数组来看，它们仍然是无序的，但是！ 它们所组成的原数组却逐渐向有序的方向前进。
 *
 * 　　四、这样不断划分到最后，数组就被划分为多个由一个元素或多个相同元素组成的单元，这样数组就有序了。
 */
public class QuickSort {
    //测试
    public static void main(String[] args) {
        //int[] array = {7,3,5,2,9,8,6,1,4,Repository7};
        int[] array = {9,9,8,7,6,5,4,3,2,1};
        sort(array);
        //打印结果为：1 2 3 4 5 6 7 7 8 9
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array){
        recQuickSort(array, 0, array.length-1);
    }

    private static void recQuickSort(int[] array,int left,int right){
        if(right <= left){
            return;//终止递归
        }else{
            int partition = partitionIt(array,left,right);
            recQuickSort(array,left,partition-1);// 对上一轮排序(切分)时，基准元素左边的子数组进行递归
            recQuickSort(array,partition+1,right);// 对上一轮排序(切分)时，基准元素右边的子数组进行递归
        }
    }

    private static int partitionIt(int[] array,int left,int right){
        //为什么 j加一个1，而i没有加1,是因为下面的循环判断是从--j和++i开始的.
        //而基准元素选的array[left],即第一个元素，所以左游标从第二个元素开始比较
        int i = left;
        int j = right+1;
        int pivot = array[left];// pivot 为选取的基准元素（头元素）
        while(true){
            while(i<right && array[++i] < pivot){}

            while(j > 0 && array[--j] > pivot){}

            if(i >= j){// 左右游标相遇时候停止， 所以跳出外部while循环
                break;
            }else{
                swap(array, i, j);// 左右游标未相遇时停止, 交换各自所指元素，循环继续
            }
        }
        swap(array, left, j);//基准元素和游标相遇时所指元素交换，为最后一次交换
        return j;// 一趟排序完成， 返回基准元素位置(注意这里基准元素已经交换位置了)
    }

    //数组array中下标为i和j位置的元素进行交换
    private static void swap(int[] array , int i , int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
