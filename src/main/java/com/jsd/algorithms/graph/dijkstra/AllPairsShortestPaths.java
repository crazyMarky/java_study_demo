package com.jsd.algorithms.graph.dijkstra;

public class AllPairsShortestPaths {

    static int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        //创建邻接矩阵
        int[] d = new int[]{0, 3, 4, MAX, MAX, MAX, MAX};
        int[] c = new int[]{0, 0, 5, 6, 10, MAX, MAX};
        int[] e = new int[]{0, 0, 0, 2, MAX, 8, MAX};
        int[] f = new int[]{0, 0, 0, 0, 7, 9, 16};
        int[] b = new int[]{0, 0, 0, 0, 0, MAX, 12};
        int[] g = new int[]{0, 0, 0, 0, 0, 0, 14};
        int[] a = new int[]{0, 0, 0, 0, 0, 0, 0};
        int[][] graph=new int[7][7];
        graph[0]=d;
        graph[1]=c;
        graph[2]=e;
        graph[3]=f;
        graph[4]=b;
        graph[5]=g;
        graph[6]=a;
        for (int[] ints : graph) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println(" ");
        }
    }

    public void dijkstraAlgorithms(int[][] graph){
       //取出顶点
        int[] d = graph[1];
    }
}
