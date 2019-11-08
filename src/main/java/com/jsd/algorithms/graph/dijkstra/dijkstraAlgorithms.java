package com.jsd.algorithms.graph.dijkstra;

public class dijkstraAlgorithms {

    static int MAX = Integer.MAX_VALUE;

    static int[] visit;

    static int[] unvisit;

    static int[] shortPath;

    public static void main(String[] args) {
        //创建邻接矩阵
        int[] a = new int[]{0, 6, 1, MAX, MAX};
        int[] b = new int[]{6, 0, 2, 2, 5};
        int[] d = new int[]{1, 2, 0, 1, MAX};
        int[] e = new int[]{MAX, 2, 1, 0, 5};
        int[] c = new int[]{MAX, 5, MAX, 5, MAX};
        int[][] graph = new int[6][6];
        graph[0] = a;
        graph[1] = b;
        graph[2] = d;
        graph[3] = e;
        graph[4] = c;
        //创建两个数组用于储存两个集合，一个用于储存已经遍历的集合，一个用于未遍历的集合
        unvisit = new int[]{0, 1, 2, 3, 4, 5, 6};
        dijkstraAlgorithms(graph);
        for (int i : shortPath) {
            System.out.print(i + " ");
        }

    }

    public static void dijkstraAlgorithms(int[][] graph) {
        //以下代码中0-6分别表示d-g
        //第一步，需要从图中，初始化起点，我们从d点开始寻找最短路径
        shortPath = new int[graph.length - 1];
        visit = new int[graph.length - 1];
        for (int i = 0; i < shortPath.length; i++) {
            if (i == 0) {
                //把起点标记为负一
                shortPath[0] = 0;
            } else {
                //将其他点都标记为最大值，表示未知当前的距离
                shortPath[i] = MAX;
            }
        }
        //等于-1的情况说明是起点
        //把当前起点的距离初始化为0
        //不是起点的情况下，我们需要对做以下几步操作：在当前点中，找到相邻的点，然后计算记录起点的距离，和路径表中的距离作比较
        //比他小则更新距离表
        int preVerte = 0;
        updateShortPath(graph, preVerte);
    }

    private static void updateShortPath(int[][] graph, int preVerte) {
        if (visit[4] == 1) {
            return;
        }
        int[] neiber = graph[preVerte];
        int shortPath1 = MAX;
        int preVerte0 = preVerte;
        for (int i = 0; i < neiber.length; i++) {
            int path = neiber[i];
            if (path == 0 || path == MAX) {
                //当距离比较大或者=0的时候，代表不相连
                continue;
            }
            //查看当前点是否被遍历过
            if (isVisit(i)) {
                continue;
            }
            //获取当前的节点与起点的最终距离
            int nowPath = shortPath[preVerte0] + path;
            //如果当前节点与距离表中的节点相比较小的话，则更新这个距离表
            if (nowPath < shortPath[i]) {
                //更新距离表
                shortPath[i] = nowPath;
            }
            //选出当前相邻路径中较短的路径
            if (nowPath < shortPath1) {
                //赋值当前点相邻的最短路径
                shortPath1 = nowPath;
                //赋值前一节点
                preVerte = i;
            }
        }
        //更新上一节点=1
        visit[preVerte0] = 1;
        //未查阅的移除上一节点
        unvisit[preVerte0] = -1;
        //检查是否终点
        if (isLast(neiber)) {
            visit[preVerte0] = 1;
            unvisit[preVerte0] = -1;
        }
        updateShortPath(graph, preVerte);

    }

    /**
     * 用于查找当前节点是否被遍历过
     *
     * @param index
     * @return
     */
    public static boolean isVisit(int index) {
        return visit[index] == 1;
    }

    /**
     * 用于查找当前节点是否终点
     *
     * @param neiber
     * @return
     */
    public static boolean isLast(int[] neiber) {
        boolean b = false;
        for (int i : neiber) {
            if (i != 0) {
                b = true;
            }
        }
        return b;
    }
}
