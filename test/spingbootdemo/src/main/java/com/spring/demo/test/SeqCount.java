package com.spring.demo.test;
/**
 * 线程同步机制是多个线程共享同一个变量，而ThreadLocal是为每个线程创建一个单独的变量副本，
 * 每个线程都可以改变自己的变量副本而不影响其它线程所对应的副本。
 * @author lcb
 * ThreadLocal不是用来解决共享变量的问题，也不是协调线程同步，他是为了方便各线程管理自己的状态而引用的一个机制。
 * 每个ThreadLocal内部都有一个ThreadLocalMap,他保存的key是ThreadLocal的实例，他的值是当前线程的局部变量的副本的值。
 */
public class SeqCount {

    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    public int nextSeq() {
        seqCount.set(seqCount.get() +1);
        return seqCount.get();
    }

    public static void main(String [] args) {
        SeqCount seqCount = new SeqCount();

        SeqThread seqThread1 = new SeqThread(seqCount);
        SeqThread seqThread2 = new SeqThread(seqCount);
        SeqThread seqThread3 = new SeqThread(seqCount);
        SeqThread seqThread4 = new SeqThread(seqCount);

        seqThread1.start();
        seqThread2.start();
        seqThread3.start();
        seqThread4.start();
    }

    public static class SeqThread extends Thread {

        private SeqCount seqCount;

        public SeqThread(SeqCount seqCount) {
            this.seqCount = seqCount;
        }

        @Override
        public void run() {
            for (int i=0; i<3; i++) {
                System.out.println(Thread.currentThread().getName()+" seqCount:"+seqCount.nextSeq());
            }
        }
    }
 }
