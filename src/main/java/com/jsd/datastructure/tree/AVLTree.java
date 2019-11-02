package com.jsd.datastructure.tree;

public class AVLTree {
    //当前节点的数值
    private Integer value;
    //左子节点
    private AVLTree leftTree;
    //右子节点
    private AVLTree rigthTree;

    public static void inTree(AVLTree tree, int value) {

        //如果当前节点的值为空，第一个节点
        if (isEmpty(tree.value)) {
            tree.value = value;
            tree.leftTree = new AVLTree();
            tree.rigthTree = new AVLTree();
            //结束递归
            return;
        }
        //当节点不为空的时候，说明当前位置已经被存放
        //判断值和当前节点的大小,大则递归子节点
        if (tree.value > value) {
            inTree(tree.leftTree, value);
        } else {
            inTree(tree.rigthTree, value);
        }
    }

    public static void inTree(AVLTree tree, int[] value) {
        for (int i : value) {
            inTree(tree, i);
        }
    }

    //前序遍历
    public static void beforeOut(AVLTree tree) {
        if (tree == null) {
            return;
        }
        //输出自己，先输出左节点再输出右节点
        System.out.println(tree.value);
        //遍历左树
        if (!isEmpty(tree.leftTree.value)) {
            beforeOut(tree.leftTree);
        }
        //遍历右树
        if (!isEmpty(tree.rigthTree.value)) {
            beforeOut(tree.rigthTree);
        }
    }

    public static boolean isEmpty(AVLTree tree) {
        boolean b = false;
        if (tree == null) {
            b = true;
        }
        return b;
    }

    public static boolean isEmpty(Integer value) {
        boolean b = false;
        if (value == null) {
            b = true;
        }
        return b;
    }

    public static void main(String[] args) {
        int[] test = new int[]{1, 5, 3, 2, 56, 34, 78, 65, 23, 67, 888, 5656, 444, 9090, 676767, 5656565, 4545, 3232, 123123, 234234, 34534};
        AVLTree tree = new AVLTree();
        inTree(tree, test);
        beforeOut(tree);
    }
}
