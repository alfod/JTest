package me.alfod;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Yang Dong
 * @createTime 2018/3/9  10:47
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2018/3/9  10:47
 * @note
 */
class InnerRunnable implements Runnable {

    /**
     * 剩余待执行的线程数量
     */
    private final CountDownLatch remainThreadCount;
    /**
     * 执行成功的线程数量
     */
    private final CountDownLatch successNumber;

    /**
     * 传入的可执行方法
     */
    private final Runnable runnable;

    /**
     * 状态同步机, 确保所有线程同时执行, 并发操作
     */
    private final CyclicBarrier synchro;

    public InnerRunnable(CountDownLatch remainThreadCount, CountDownLatch successNumber, Runnable runnable, CyclicBarrier synchro) {
        this.remainThreadCount = remainThreadCount;
        this.successNumber = successNumber;
        this.runnable = runnable;
        this.synchro = synchro;
    }

    @Override
    public void run() {
        try {
            synchro.await();
            runnable.run();
        } catch (Exception e) {
            successNumber.countDown();
            throw new RuntimeException(e);
        } finally {
            remainThreadCount.countDown();
        }

    }
}
