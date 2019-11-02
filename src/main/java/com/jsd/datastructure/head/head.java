package com.jsd.datastructure.head;

import java.util.ArrayList;

public class head {

    public static ArrayList<Integer> createHead(int[] a) {
        ArrayList<Integer> head = new ArrayList();
        for (int num : a) {
            //赋值到最后一个节点上面
            int index = head.size();
            head.add(num);
            shiftUp(index, num, head);
        }
        return head;
    }

    public static void shiftUp(int index, int num, ArrayList<Integer> head) {
        //获取父节点
        int parent = getParent(index);
        //和父节点做比较，如果比父节点大则交换
        if (parent != -1) {
            int parentNum = head.get(parent);
            if (parentNum < num) {
                arraySwap(index, head, parent);
                shiftUp(parent, num, head);
            }
        }
    }

    private static void arraySwap(int index, ArrayList<Integer> head, int parent) {
        int temp = 0;
        temp = head.get(parent);
        head.set(parent, head.get(index));
        head.set(index, temp);
    }

    public static void shiftDown(int index, ArrayList<Integer> head) {
        int num = head.get(index);
        int leftChil = getLeftChil(index);
        int rightChil = getRightChil(index);
        int maxIndex = getMaxChilIndex(leftChil, rightChil, head);
        if (maxIndex != -1) {
            if (num < head.get(maxIndex)) {
                //比子节点最大的小的时候要交换
                arraySwap(index, head, maxIndex);
                shiftDown(maxIndex, head);
            }
        }
    }

    public static int getMaxChilIndex(int leftChil, int rightChil, ArrayList<Integer> head) {
        int index = -1;
        if (leftChil < head.size() - 1 && rightChil < head.size()) {
            int lefNum = head.get(leftChil);
            int righNum = head.get(rightChil);
            if (lefNum >= righNum) {
                index = leftChil;
            } else {
                index = rightChil;
            }
        }else if (leftChil <= head.size() - 1){
            index=leftChil;
        }else if (rightChil <= head.size() - 1){
            index=rightChil;
        }
        return index;
    }

    public static int getParent(int index) {
        int parIndex = -1;
        //左孩子和右孩子计算的方式不一样
        if (isLeftChil(index)) {
            parIndex = (index - 1) >> 1;
        } else {
            parIndex = (index - 2) >> 1;
        }
        return parIndex;
    }

    public static int getLeftChil(int index) {
        return (index << 1) + 1;
    }

    public static int getRightChil(int index) {
        return (index << 1) + 2;
    }

    public static boolean isLeftChil(int index) {
        boolean b = false;
        if (index % 2 != 0) {
            b = true;
        }
        return b;
    }

    public static int remove(ArrayList<Integer> head) {
        int max = head.get(0);
        arraySwap(0, head, head.size() - 1);
        head.remove(head.size() - 1);
        shiftDown(0, head);
        return max;
    }

    public static void main(String[] args) {
        int[] a = new int[]{789,555,554,11,10,456,789,1234,562,4502,123,4556,4451,3325,2345,4531};
        ArrayList<Integer> head = createHead(a);
        System.out.println(head.toString());
        int max = remove(head);
        System.out.println(max + "");
        System.out.println(head.toString());
    }


}
