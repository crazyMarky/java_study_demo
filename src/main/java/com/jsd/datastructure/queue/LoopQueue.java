package com.jsd.datastructure.queue;

/**
 * 循环队列的实现
 * 循环队列就是容量固定，下标可循环的队列
 * 补充资料https://blog.csdn.net/return9/article/details/79672708
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
            throw new Exception("queue is full , can not push element : "+ele);
        }
        array[rear]=ele;
        //队尾下标+1
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
        //下标+1
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

    public void printStatus(){
        System.out.println("队头下标："+this.front+"\t队尾下标："+this.rear+"\t目前容量："+(this.rear-this.front+this.array.length)%this.array.length);
    }

    public static void main(String[] args) throws Exception {
        //循环队列的实现
        LoopQueue loopQueue = new LoopQueue(6);
        loopQueue.push(1);
        loopQueue.printStatus();
        loopQueue.push(5);
        loopQueue.printStatus();
        loopQueue.push(4);
        loopQueue.printStatus();
        loopQueue.push(3);
        loopQueue.printStatus();
        loopQueue.push(6);
        loopQueue.printStatus();
        loopQueue.printQueue();
        System.out.println("-----------------");
        Integer pop = loopQueue.pop();
        loopQueue.printStatus();
        Integer pop1 = loopQueue.pop();
        loopQueue.printStatus();
        loopQueue.printQueue();
        System.out.println("-----------------");
        loopQueue.push(7);
        loopQueue.printStatus();
        loopQueue.push(8);
        loopQueue.printStatus();
        loopQueue.push(9);
        loopQueue.printStatus();
        loopQueue.push(10);
        loopQueue.printStatus();
        loopQueue.push(11);
        loopQueue.printStatus();
        loopQueue.push(12);
        loopQueue.printStatus();
        loopQueue.printQueue();
    }

}
