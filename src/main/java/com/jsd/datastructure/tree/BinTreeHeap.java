package com.jsd.datastructure.tree;

import java.util.Arrays;

/**
 * 二叉堆
 * 实现原理：数组存储
 * 主要实现二叉堆的自我调整
 * Created by liaoh on 2019/6/8.
 */
public class BinTreeHeap {
    /**
     * 上浮调整
     * @param array
     */
   public static void upAdjust(int[] array){
       //获取最后的节点
       int childIndex = array.length-1;
       //获取最后节点的父节点
       int parentIndex = (childIndex-1)/2;
       //保存子节点
       int temp = array[childIndex];
       //与父节点对比，如果父节点大于子节点
       while(childIndex>0&&temp<array[parentIndex]){
            //父子节点交换值
           array[childIndex]=array[parentIndex];
           //交换下标
           childIndex=parentIndex;
           //计算新的父节点
           parentIndex=(parentIndex-1)/2;
       }
       array[childIndex] = temp ;
   }

   public static void downAdjust(int[] array,int parentIndex,int len){
       //保存节点
       int temp = array[parentIndex];
       int childIndex = 2 * parentIndex +1 ;
       while (childIndex<len){
           //如果有右孩子，且右孩子比左孩子大，则先访问右孩子
           if (childIndex+1<len&&array[childIndex+1]<array[childIndex]){
               childIndex++;
           }
           //如果父节点小于左右孩子
           if (temp<=array[childIndex]){
               break;
           }
           array[parentIndex]=array[childIndex];
           parentIndex=childIndex;
           childIndex= 2*childIndex +1 ;
       }
       //交换值
       array[parentIndex]=temp;
   }

    /**
     * 构建堆
     * @param array
     */
   public static void buildHeap(int[] array){
       // 从最后一个非叶子节点开始，做下沉调整
       //最后一个节点是array.length-1
       //最后一个非叶子节点是
       for (int i=(array.length-2)/2;i>=0;i--){
           downAdjust(array,i,array.length);
       }
   }


    public static void main(String[] args) {
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,0};
        //上浮元素
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        array= new int[]{7,1,3,10,5,2,8,9,6};
        //构建堆
        buildHeap(array);
        System.out.println(Arrays.toString(array));

    }
}
