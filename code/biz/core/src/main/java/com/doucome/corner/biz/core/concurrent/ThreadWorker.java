package com.doucome.corner.biz.core.concurrent;

/**
 * 
 * @author ib 2012-8-25 ÏÂÎç9:05:34
 */
public class ThreadWorker<T> implements Runnable {

    private AbstractHandler<T> handler;

    private T                  t;

    public ThreadWorker(AbstractHandler<T> handler, T t){
        this.handler = handler;
        this.t = t;
    }

    @Override
    public void run() {
        handler.handle(t);
    }

}
