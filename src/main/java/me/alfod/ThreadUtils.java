package me.alfod;

import java.text.DecimalFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Yang Dong
 * @createTime 2018/3/7  16:19
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2018/3/7  16:19
 * @note
 */
public class ThreadUtils {
    private final Integer count = 10_000;

    public static void run(Runnable runnable, int times, double interval) {
        if (runnable == null) {
            throw new RuntimeException("runnable is null");
        }
        if (times < 1) {
            times = 1;
        }

        Thread[] threads = new Thread[times];
        InnerRunnable innerRunnable;
        CountDownLatch remainThread = new CountDownLatch(times);
        CountDownLatch successNumber = new CountDownLatch(times);
        CyclicBarrier waitArea = new CyclicBarrier(times);
        for (int i = 0; i < times; i++) {
            innerRunnable = new InnerRunnable(remainThread, successNumber, runnable, waitArea);
            threads[i] = new Thread(innerRunnable, "JTest-thread-" + i);
        }
        for (int i = 0; i < times; i++) {
            threads[i].start();
        }
        try {
            remainThread.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        print();
        long failed = (times - successNumber.getCount());
        long success = successNumber.getCount();
        double rate = (double) success / times;
        print("failed: " + failed);
        print("success: " + success);
        print("rate: " + new DecimalFormat("#.00%").format(rate));

        print();
    }

    private static void print(Object o) {
        System.out.println(o);
    }

    private static void print() {
        System.out.println();
    }
}
