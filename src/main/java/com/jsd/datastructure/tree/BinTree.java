package com.jsd.datastructure.tree;


import java.util.Arrays;
import java.util.LinkedList;

/**
 * 一个简单的二叉树三种遍历策略展示：
     前序遍历：根节点 左子树 右子树
     中序遍历：左子树 根节点 右子树
     后序遍历：左子树 右子树 根节点
    主要采用递归的方式实现
 * Created by liaoh on 2019/6/2.
 */
public class BinTree {

    /**
     * 节点类，节点拥有数据，并包含左右子树的引用
     */
    private static class TreeNode{
        int date ;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data){
            this.date=data;
        }
    }

    /**
     * 根据输入的数组，递归创建二叉树节点
     * @param inputList
     * @return
     */
    public static TreeNode createBinTree(LinkedList<Integer> inputList) {
        TreeNode node = null ;
        if (inputList==null || inputList.isEmpty()){
            return null;
        }
        Integer data = inputList.removeFirst();
        if (null!=data){
            node = new TreeNode(data);
            node.leftChild=createBinTree(inputList);
            node.rightChild=createBinTree(inputList);
        }
        return node ;
    }

    /**
     * 前序遍历打印
     * @param treeNode
     */
    public  static void preOrderPrint(TreeNode treeNode){
        if (null == treeNode){
            return;
        }
        System.out.println(treeNode.date);
        preOrderPrint(treeNode.leftChild);
        preOrderPrint(treeNode.rightChild);
    }

    /**
     * 中序遍历打印
      * @param treeNode
     */

    public static void inOrderPrint(TreeNode treeNode){
        if (null == treeNode){
            return;
        }
        inOrderPrint(treeNode.leftChild);
        System.out.println(treeNode.date);
        inOrderPrint(treeNode.rightChild);
    }

    /**
     * 后序遍历打印
     * @param treeNode
     */
    public static void afterOderPrint(TreeNode treeNode){
        if (null==treeNode){
            return;
        }
        afterOderPrint(treeNode.leftChild);
        afterOderPrint(treeNode.rightChild);
        System.out.println(treeNode.date);
    }

    public static void main(String[] args) {
        //创建一个数组
        Integer[] integers = {3, 2, 9, null, null, 10, null};
        LinkedList<Integer> integers1 = new LinkedList<>(Arrays.asList(integers));
        TreeNode binTree = createBinTree(integers1);
        System.out.println("前序遍历：");
        preOrderPrint(binTree);
        System.out.println("中序遍历：");
        inOrderPrint(binTree);
        System.out.println("后序遍历：");
        afterOderPrint(binTree);
    }
}