package com.jsd.datastructure.queue;

/**
 * 循环队列的实现
 * Created by liaoh on 2019/6/9.
 */
public class LoopQueue {
    //数据
    private int[] array ;
    //队头
    private int front;
    //队尾
    private int rear;

    public LoopQueue() {
        this.array=new int[16];
    }

    public LoopQueue(int init){
        this.array=new int[init];
    }

    /**
     * 入队
     */
    public void push(int ele) throws Exception {
        if ((rear+1)%array.length== front){
            throw new Exception("queue is full");
        }
        array[rear]=ele;
        rear=(rear+1)%array.length;
    }

    /**
     * 出队
     * @return
     * @throws Exception
     */
    public Integer pop() throws Exception{
        if (rear==front){
            throw new Exception("queue is empty");
        }
        //读出元素
        int ele = array[front];
        //下标移动
        front = (front+1)%array.length;
        return ele;
    }

    /**
     * 输出队列
     */
    public void printQueue(){
        for (int i = front; i != rear; i=(i+1)%array.length) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        //循环队列的实现
        LoopQueue loopQueue = new LoopQueue(6);
        loopQueue.push(1);
        loopQueue.push(5);
        loopQueue.push(4);
        loopQueue.push(3);
        loopQueue.push(6);
        loopQueue.printQueue();
        System.out.println("-----------------");
        Integer pop = loopQueue.pop();
        Integer pop1 = loopQueue.pop();
        loopQueue.printQueue();
    }

}
