package com.jsd.algorithms.serach;

public class BinarySearch {

    /**
     * 二分查找
     *
     * @param head      开始的坐标
     * @param tail      结束的坐标
     * @param array     数组
     * @param searchNum 查找的数
     * @return
     */
    public static int search(int head, int tail, int[] array, int searchNum) {
        //取两者的中间数
        int length = tail + head;
        int mid = length >> 1;
        //递归结束的标志，开始的标志和最后的标志相同，说明二分查找完了
        if (head == tail) {
            return -1;
        } else if (array[mid] > searchNum) {
            //比他大则往左边走
            return search(head, mid, array, searchNum);
        } else if (array[mid] < searchNum) {
            //比他小则往右边走
            return search(mid + 1, tail, array, searchNum);
        } else {
            //相等则直接返回这个坐标
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] i = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int search = search(0, i.length - 1, i, 10);
        System.out.println(search);
    }
}
