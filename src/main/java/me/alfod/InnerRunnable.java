package me.alfod;

import java.util.concurrent.CountDownLatch;

/**
 * @author Yang Dong
 * @createTime 2018/3/9  10:47
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2018/3/9  10:47
 * @note
 */
class InnerRunnable implements Runnable {

    /**
     * 剩余待执行的线程
     */
    private final CountDownLatch remainThreadCount;
    /**
     * 方法是否执行成功
     */
    private final CountDownLatch successNumber;

    /**
     * 传入的可执行方法
     */
    private final Runnable runnable;

    public InnerRunnable(CountDownLatch remainThreadCount, CountDownLatch successNumber, Runnable runnable) {
        this.remainThreadCount = remainThreadCount;
        this.successNumber = successNumber;
        this.runnable = runnable;
    }

    @Override
    public void run() {
        try {
            runnable.run();
        } catch (Exception e) {
            successNumber.countDown();
            throw e;
        } finally {
            remainThreadCount.countDown();
        }

    }
}
