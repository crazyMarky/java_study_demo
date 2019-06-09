package com.jsd.datastructure.tree;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 一个简单的二叉树三种遍历策略展示：
     前序遍历：根节点 左子树 右子树
     中序遍历：左子树 根节点 右子树
     后序遍历：左子树 右子树 根节点
    采用递归的方式实现和采用栈来实现
     资料来源：漫画算法
 * Created by liaoh on 2019/6/2.
 */
public class BinTree {

    /**
     * 节点类，节点拥有数据，并包含左右子树的引用
     */
    private static class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data){
            this.data =data;
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
        System.out.println(treeNode.data);
        preOrderPrint(treeNode.leftChild);
        preOrderPrint(treeNode.rightChild);
    }

    /**
     * 深度优先遍历
     * 前序遍历打印--用栈实现
     * 整体思路：
     * 将父节点入栈，去找左孩子，如果左孩子遍历完了，父节点出栈，去找右孩子
     */
    public static void preOrderStackPrint(TreeNode treeNode){
        //栈
        Stack<TreeNode> treeNodes = new Stack<>();
        while (treeNode!=null||!treeNodes.isEmpty()){
            while (treeNode!=null){
                System.out.println(treeNode.data);
                //将父节点入栈
                treeNodes.push(treeNode);
                //获得左孩子
                treeNode = treeNode.leftChild;
            }
            if (!treeNodes.isEmpty()){
                //父节点出栈
                treeNode = treeNodes.pop();
                //拿到右孩子
                treeNode = treeNode.rightChild;
            }
        }
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
        System.out.println(treeNode.data);
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
        System.out.println(treeNode.data);
    }

    /**
     * 层序遍历
     * 思想：广度优先遍历，输出父节点，就接着输出左右孩子
     * 实现方式：队列
     * @param node
     */
    public static void  afterOderQueuePrint(TreeNode node){
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        //根节点入队
        nodeQueue.offer(node);
        //只要队列没空就继续循环
        while(!nodeQueue.isEmpty()){
            //出队
            TreeNode poll = nodeQueue.poll();
            System.out.println(poll.data);
            //拿左孩子，有就入队
            if (poll.leftChild!=null){
                nodeQueue.offer(poll.leftChild);
            }
            //拿右孩子，有就入队
            if (poll.rightChild!=null){
                nodeQueue.offer(poll.rightChild);
            }
        }
    }

    public static void main(String[] args) {
        //创建一个数组
        Integer[] integers = {1, 2, 3 , null, 4, 5, null, 6,7, 8,null,9,10,11,null,12,13,14,15};
        //空的节点用x符号表示
        //                  1
        //         2                 3
        //      x     4         5       x
        //          6   7     8   x
        //        9   10 11 x 12 13
        //      14  15
        LinkedList<Integer> integers1 = new LinkedList<>(Arrays.asList(integers));
        TreeNode binTree = createBinTree(integers1);
        System.out.println("前序遍历：");
        preOrderPrint(binTree);
        System.out.println("中序遍历：");
        inOrderPrint(binTree);
        System.out.println("后序遍历：");
        afterOderPrint(binTree);
        System.out.println("深度优先遍历--栈实现：");
        preOrderStackPrint(binTree);
        System.out.println("广度优先遍历--队列实现");
        afterOderQueuePrint(binTree);
    }
}
